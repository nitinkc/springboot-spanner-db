package com.spanner.springBoot.controller;


import com.spanner.springBoot.config.CacheApplicationRunner;
import com.spanner.springBoot.config.RefCacheConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.spanner.springBoot.model.refdata.ReferenceDataBase;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cloud.gcp.data.spanner.core.SpannerTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/cache")
@RequiredArgsConstructor
@Component
public class CacheEndpoint {

    private final CacheManager cacheManager;
    private final CacheApplicationRunner cacheApplicationRunner;
    private final SpannerTemplate spannerTemplate;

    @GetMapping(path = "/clear")
    @Scheduled(cron="0 0 9 ? * SUN", zone="GMT") // run every sunday 12AM GMT
    public void clearCache() throws Exception {
        log.info("Clearing all caches");
        cacheManager.getCacheNames().stream()
                .forEach(cache -> {
                    System.out.println("Clearing cache for ::" + cache);
                    cacheManager.getCache(cache).clear();
                });
        log.info("Cache is cleared");

        cacheApplicationRunner.run(null);
        log.info("Ref data cache is refreshed");
    }

    @GetMapping(path = "/clear/{cacheName}")
    public void clearCacheByCache(@PathVariable String cacheName){
        Cache cache = cacheManager.getCache(cacheName);
        cache.clear();
        log.info("Cache is cleared");
        List<? extends ReferenceDataBase> referenceData = spannerTemplate.readAll(RefCacheConfig.CLASS_MAP.get(cacheName));
        referenceData.forEach(refData -> cache.put(CacheApplicationRunner.RefDataKey.getKey(refData),refData ));
        log.info("Cache is loaded for "+cacheName);
    }
}