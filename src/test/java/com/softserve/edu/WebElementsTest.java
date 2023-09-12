package com.softserve.edu;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.time.Duration;

public class WebElementsTest {
    private final String BASE_URL = "http://taqc-opencart.epizy.com/";
    //private final String BASE_URL = "https://demo.opencart.com/";
    private final Long IMPLICITLY_WAIT_SECONDS = 10L;
    private final Long ONE_SECOND_DELAY = 1000L;
    private WebDriver driver;

    private void presentationSleep() {
        presentationSleep(1);
    }

    private void presentationSleep(int seconds) {
        try {
            Thread.sleep(seconds * ONE_SECOND_DELAY); // For Presentation ONLY
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @BeforeSuite
    public void beforeSuite() {
        WebDriverManager.chromedriver().setup();
        // WebDriverManager.firefoxdriver().setup();
    }

    @BeforeClass
    public void beforeClass() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        //driver = new ChromeDriver();
        //
        //driver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT_SECONDS, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_SECONDS)); // 0 by default
        driver.manage().window().maximize();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        presentationSleep(); // For Presentation ONLY
        // driver.close();
        if (driver != null) {
            driver.quit(); // close()
        }
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get(BASE_URL);
        presentationSleep(); // For Presentation ONLY
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        presentationSleep(); // For Presentation ONLY
        // logout;
        // Save Screen;
        if (!result.isSuccess()) {
            String testName = result.getName();
            System.out.println("***TC error, name = " + testName + " ERROR");
            // Take Screenshot, save sourceCode, save to log, prepare report, Return to;
            // previous state, logout, etc.
            // takeScreenShot(testName);
            // takePageSource(testName);
            // driver.manage().deleteAllCookies(); // clear cache; delete cookie; delete session;
        }
        // driver.findElement(By.cssSelector("#logo .img-responsive")).click();
        // driver.findElement(By.cssSelector("#logo > a")).click();
        //driver.findElement(By.xpath("//img[contains(@src, '/logo.png')]/..")).click();
        presentationSleep(); // For Presentation ONLY
    }


    @Test
    public void checkExistWebElement() throws Exception {
        //
        System.out.println("1. isVisible Login Link = "
                + driver.findElement(By.cssSelector("div#top-links a[href*='route=account/login']")).isDisplayed());
        presentationSleep(); // For Presentation ONLY
        //
        driver.findElement(By.cssSelector("a[title='My Account']")).click();
        presentationSleep(); // For Presentation ONLY
        //
        System.out.println("2. isVisible Login Link = "
                + driver.findElement(By.cssSelector("div#top-links a[href*='route=account/login']")).isDisplayed());
        presentationSleep(); // For Presentation ONLY
        //
        driver.findElement(By.cssSelector("div#top-links a[href*='route=account/login']")).click();
        presentationSleep(); // For Presentation ONLY
        //
        // /*-
        driver.findElement(By.id("input-email")).click();
        driver.findElement(By.id("input-email")).clear();
        driver.findElement(By.id("input-email")).sendKeys("haha");
        //
        String content = driver.findElement(By.id("input-email")).getAttribute("value");
        System.out.println("***content email = " + content);
        presentationSleep(2); // For Presentation ONLY
        //
        // Refresh some webelements
        driver.navigate().refresh();
        presentationSleep(); // For Presentation ONLY
        //
        driver.findElement(By.id("input-email")).sendKeys("bebebe");
        presentationSleep(); // For Presentation ONLY
        // */
        /*-
        WebElement email = driver.findElement(By.id("input-email"));
        //
        email.click();
        email.clear();
        email.sendKeys("haha");
        String content = email.getAttribute("value");
        System.out.println("content email = " + content);
        presentationSleep(); // For Presentation ONLY
        //
        // Refresh some webelements
        driver.navigate().refresh();
        presentationSleep(); // For Presentation ONLY
        email.sendKeys("bebebe"); // Runtime Error
        presentationSleep(); // For Presentation ONLY
        */
        //
        // driver.findElement(By.id("input-email")).sendKeys("password");
        driver.findElement(By.id("input-password")).click();
        driver.findElement(By.id("input-password")).clear();
        driver.findElement(By.id("input-password")).sendKeys("password");
        content = driver.findElement(By.id("input-password")).getAttribute("value");
        System.out.println("content password = " + content);
        presentationSleep(4); // For Presentation ONLY
        //
    }

}
