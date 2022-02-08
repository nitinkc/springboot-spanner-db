package com.spanner.springBoot.Utilities;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.springframework.core.io.ClassPathResource;

public final class SqlFileUtil {
        public static String getSQLQuery(String path) {
            try {
                return IOUtils.toString(new ClassPathResource(path).getInputStream(), StandardCharsets.UTF_8);
            } catch (IOException e) {
                throw new IllegalStateException("Can't read sql file", e);
            }
        }
    }
