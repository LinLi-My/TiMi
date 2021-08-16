package com.ml.timi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:pay.properties")
public class WXConfig {
    @Value("${a.q}")
    public String q;
    @Value("${a.w}")
    public String w;


}
