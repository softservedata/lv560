package com.example.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
public class ApplTest {

    @Test
    public void checkEnvironmentVariables(){
        //
        log.info("JDBC_USERNAME = " + System.getenv("JDBC_USERNAME"));
        log.info("JDBC_PASSWORD = " + System.getenv("JDBC_PASSWORD"));
        //

        boolean isCredential = true;
        isCredential = isCredential && (System.getenv("JDBC_USERNAME") != null) && !System.getenv("JDBC_USERNAME").isEmpty();
        isCredential = isCredential && (System.getenv("JDBC_PASSWORD") != null) && !System.getenv("JDBC_PASSWORD").isEmpty();

        Assertions.assertTrue(isCredential);
    }
}
