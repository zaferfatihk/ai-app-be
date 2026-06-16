package com.ai_app_be.support;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public final class TestUrlUtils {
    private TestUrlUtils() {
    }

    public static String encodeQueryParam(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }
}
