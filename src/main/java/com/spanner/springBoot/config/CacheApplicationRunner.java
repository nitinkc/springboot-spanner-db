package com.spanner.springBoot.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.spanner.springBoot.model.refdata.ReferenceDataBase;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cloud.gcp.data.spanner.core.SpannerTemplate;
import org.springframework.cloud.gcp.data.spanner.core.mapping.PrimaryKey;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Slf4j
@Component
public class CacheApplicationRunner implements ApplicationRunner {

    private final SpannerTemplate spannerTemplate;
    private final EhCacheCacheManager ehCacheCacheManager;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        long startTime = System.currentTimeMillis();

        RefCacheConfig.CLASS_MAP.entrySet().forEach(entry -> {
            ehCacheCacheManager.getCache(entry.getKey());
            Cache refCache = ehCacheCacheManager.getCache(entry.getKey());
            try {
                List<? extends ReferenceDataBase> referenceData = spannerTemplate.readAll(entry.getValue());
                log.info("Loaded data for {}, and size is {}",entry.getValue().getClass().getSimpleName(), referenceData.size() );
                referenceData.forEach(refData -> {
                    refCache.put(RefDataKey.getKey(refData),refData );
                });
            }catch (Exception ex){
                log.warn("exception occur is {}", ex.getMessage());
            }
        });

        log.info("Ref data load is completed,total time take to load is {} ms", System.currentTimeMillis() - startTime);
    }

    public interface RefDataKey {
        static String getKey(ReferenceDataBase base) {
            final List<Field> fields = Stream.of(base.getClass().getDeclaredFields())
                    .map(f -> Pair.of(f, f.getAnnotation(PrimaryKey.class)))
                    .filter(p -> p.getRight() != null)
                    .sorted(Comparator.comparing(p -> Math.max(p.getRight().keyOrder(), p.getRight().value())))
                    .map(Pair::getLeft)
                    .peek(f -> f.setAccessible(true))
                    .collect(Collectors.toList());
            return fields.stream()
                    .map(f -> ReflectionUtils.getField(f, base))
                    .map(Objects::toString)
                    .collect(Collectors.joining(":"));
        }
    }
}
