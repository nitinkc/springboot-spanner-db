package com.spanner.springBoot.config;
import lombok.extern.slf4j.Slf4j;
import com.spanner.springBoot.model.refdata.ReferenceDataBase;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.SearchAttribute;
import net.sf.ehcache.config.Searchable;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cloud.gcp.data.spanner.core.mapping.Table;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
@RestController
@RequestMapping("/cache")
@Slf4j
@EnableScheduling
public class RefCacheConfig extends CachingConfigurerSupport {

    //TODO: put these values in App yml
    private long timeToLiveDays = 7;
    private long maxEntriesLocalHeap = 100000;

    public static final Map<String, Class<? extends ReferenceDataBase>> CLASS_MAP;

    public static final String CATEGORY_NAME="CategoryName";

    static {
        CLASS_MAP = new HashMap<>();
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .forPackage("com.spanner.springBoot.model.refdata"));//TODO: put the package in App yml

        Set<Class<?>> refClasses = reflections.getTypesAnnotatedWith(Table.class);
        reflections.getSubTypesOf(ReferenceDataBase.class).stream()
                .filter(refClasses::contains)
                .forEach(theClass -> {
                    CLASS_MAP.put(theClass.getSimpleName(), theClass);
                });
        log.debug("CLASS_MAP: {}", CLASS_MAP);
    }

    @Bean
    public net.sf.ehcache.CacheManager ehCacheManager(){

        CacheConfiguration refIdCache = new CacheConfiguration();
        refIdCache.setName("refIdCache");//TODO: put these values in App yml
        refIdCache.setMemoryStoreEvictionPolicy("LRU");//One of "LRU", "LFU" or "FIFO" TODO: put these values in App yml
        refIdCache.setMaxEntriesLocalHeap(maxEntriesLocalHeap);
        refIdCache.setTimeToLiveSeconds( TimeUnit.DAYS.toSeconds(timeToLiveDays));

        net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
        config.addCache(refIdCache);

     //Load ref all data
        CLASS_MAP.entrySet()
             .forEach(entry -> {
                 CacheConfiguration refConfig = new CacheConfiguration();
                 refConfig.setName(entry.getKey());
                 refConfig.setMemoryStoreEvictionPolicy("LRU");
                 refConfig.setMaxEntriesLocalHeap(maxEntriesLocalHeap);
                 refConfig.setTimeToLiveSeconds(TimeUnit.DAYS.toSeconds(7));
                 refConfig.setEternal(Boolean.FALSE);
                 Searchable searchable = new Searchable();
                 searchable.allowDynamicIndexing(true);
                 searchable.addSearchAttribute(new SearchAttribute().name(CATEGORY_NAME).expression("value.getCategoryName()"));//TODO: Check this
                 refConfig.addSearchable(searchable);

                 config.addCache(refConfig);
             });

        return net.sf.ehcache.CacheManager.newInstance(config);

    }

    @Bean
    @Override
    public CacheManager cacheManager() {
        return new EhCacheCacheManager(ehCacheManager());
    }

}
