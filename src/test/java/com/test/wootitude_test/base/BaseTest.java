package com.test.wootitude_test.base;

import com.test.wootitude_test.config.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BaseTest {

    @Autowired
    protected Configuration configuration;
    protected static final Logger logger = LogManager.getLogger();

    protected void StringAssert(String expected, String actual, String inputValue){
        logger.info("Input value: {}\nActual: {}\nExpected: {}", inputValue, actual, expected);
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
