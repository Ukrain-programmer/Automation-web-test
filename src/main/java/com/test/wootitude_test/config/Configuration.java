package com.test.wootitude_test.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@org.springframework.context.annotation.Configuration
@ConfigurationProperties(prefix = "links")
public class Configuration {

    private String validation7chars;

}
