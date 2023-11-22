package com.softserve.config;

import com.softserve.teachua.service.VersionService;
import com.softserve.teachua.service.impl.PropertiesServiceImpl;
import com.softserve.teachua.service.impl.VersionServiceImpl;
import org.junit.jupiter.api.*;

import java.io.FileOutputStream;
import java.util.Properties;

public class VersionCreateTest {

    @BeforeAll
    public static void setup() {
        System.out.println("@BeforeAll executed");
    }

    @AfterAll
    public static void tear() {
        System.out.println("@AfterAll executed");
    }

    @BeforeEach
    public void setupThis() {
        System.out.println("\t@BeforeEach executed");
    }

    @AfterEach
    public void tearThis() {
        System.out.println("\t@AfterEach executed");
    }

    //@Test
    public void testOne() {
        System.out.println("\t\t@Test testOne()");
        Properties appProps = new Properties();
        appProps.setProperty("commitNumber", "commitNumber5");
        appProps.setProperty("commitDate", "commitDate5");
        appProps.setProperty("buildDate", "buildDate5");
        //
        String path = this.getClass().getResource("/").getPath();
        try (FileOutputStream fileOutputStream = new FileOutputStream(path + "my.properties")) {
            appProps.store(fileOutputStream, "Comments");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testTwo() {
        System.out.println("\t\t@Test testTwo()");
        //PropertiesService propertiesService = new PropertiesServiceImpl();
        //propertiesService.writeProperties("my.properties");
        //
        VersionService versionService = new VersionServiceImpl(new PropertiesServiceImpl());
        versionService.setVersion();
        //versionService.getCommit();
    }

}
