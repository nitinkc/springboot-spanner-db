package com.spanner.springBoot.dao;

import com.google.cloud.spanner.Key;
import com.google.cloud.spanner.KeySet;
import com.spanner.springBoot.config.CacheApplicationRunner;
import com.spanner.springBoot.model.refdata.RefDataReadDao;
import com.spanner.springBoot.model.refdata.ReferenceDataBase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.Element;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cloud.gcp.data.spanner.core.SpannerQueryOptions;
import org.springframework.cloud.gcp.data.spanner.core.SpannerReadOptions;
import org.springframework.cloud.gcp.data.spanner.core.SpannerTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.isNull;


@Slf4j
@Repository
@RequiredArgsConstructor
public  class SpannerRefDataReadDao implements RefDataReadDao {

    private final SpannerTemplate spannerTemplate;
    private final CacheManager cacheManager;

    private static final SpannerQueryOptions SPANNER_OPTIONS = new SpannerQueryOptions();
    private static final SpannerReadOptions SPANNER_READ_OPTIONS = new SpannerReadOptions();

    static {
        SPANNER_OPTIONS.setAllowPartialRead(true);
        SPANNER_READ_OPTIONS.setAllowPartialRead(true);
    }

    @Override
    public <K extends ReferenceDataBase> K findById(Class<K> theClass, String id){
        Cache cache = cacheManager.getCache(theClass.getSimpleName());
        Object nativeCache = cache.getNativeCache();
        net.sf.ehcache.Ehcache ehCache = (net.sf.ehcache.Ehcache) nativeCache;
       return Optional.ofNullable(ehCache.get(id))
               .filter(Objects::nonNull)
               .map(e -> (K) e.getObjectValue())
               .orElse( spannerTemplate.read(theClass, Key.of(id)));
    }

    @Override
    public <K extends ReferenceDataBase> List<K> findByIds(Class<K> theClass, Collection<String> ids) {
        Cache cache = cacheManager.getCache(theClass.getSimpleName());
        Object nativeCache = cache.getNativeCache();
        net.sf.ehcache.Ehcache ehCache = (net.sf.ehcache.Ehcache) nativeCache;
        Map<Object, Element> elementMap = ehCache.getAll(ids);
        List<K> collect = elementMap.values().stream()
                .filter(o -> Objects.nonNull(o))
                .map(o -> (K)o.getObjectValue())
                .collect(Collectors.toList());

        if (collect.size() == ids.size()){
            return elementMap.values().stream().filter(o -> Objects.nonNull(o)).map(o -> (K)o.getObjectValue()).collect(Collectors.toList());
        } else{
            List<String> missElements = ids.stream().filter(id -> null == elementMap.get(id)).collect(Collectors.toList());
            KeySet.Builder builder = KeySet.newBuilder();
            missElements.stream().filter(Objects::nonNull).distinct().map(String::valueOf).map(Key::of).forEach(builder::addKey);
            List<K> read = spannerTemplate.read(theClass, builder.build());
            if (CollectionUtils.isNotEmpty(read)){
                read.stream().filter(Objects::nonNull).forEach(elemnt -> cache.put(CacheApplicationRunner.RefDataKey.getKey(elemnt), elemnt));
                collect.addAll(read);
            }
            return collect;
        }
    }

    @Override
    public <K extends ReferenceDataBase> List<K> findAll(Class<K> theClass) {
        Cache cache = cacheManager.getCache(theClass.getSimpleName());
        Object nativeCache = cache.getNativeCache();
        net.sf.ehcache.Ehcache ehCache = (net.sf.ehcache.Ehcache) nativeCache;
        List<K> allRefData = ehCache.getAll(ehCache.getKeys()).values().stream().filter(Objects::nonNull).map(o -> (K) o.getObjectValue()).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(allRefData)){
            return allRefData;
        }else {
           return spannerTemplate.readAll(theClass);
        }
    }

    @Override
    public <K extends ReferenceDataBase> List<K> findByCategoryRefData(Class<K> theClass, Collection<String> category) {
        //todo  in future will think about this, as of now plans to develop this method
        return null;
    }

    private <K extends ReferenceDataBase> Stream<K> stream(Class<K> theClass) {
        return this.findAll(theClass).stream();
    }

    static <T> Predicate<T> nullOrEquals(Object orig, Function<T, ?> extractor){
        return t -> isNull(orig) || Objects.equals(orig, extractor.apply(t));
    }
    static <T> Predicate<T> nullOrLike(String orig, Function<T, String> extractor){
        return t -> isNull(orig) || StringUtils.containsIgnoreCase(extractor.apply(t), orig);
    }
    static <T> Predicate<T> equals(Object orig, Function<T, ?> extractor){
        return t -> Objects.equals(orig, extractor.apply(t));
    }
    static <T, V> Predicate<T> contains(Set<V> orig, Function<T, V> extractor){
        return t -> orig.contains(extractor.apply(t));
    }

    static <T> Predicate<T> like(String orig, Function<T, String> extractor){
        return t -> StringUtils.containsIgnoreCase(extractor.apply(t), orig);
    }

}
