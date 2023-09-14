package com.softserve.edu;

import com.softserve.edu.data.Product;
import com.softserve.edu.data.ProductRepository;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.stream.Stream;

public class SearchSecondTest {
    private static final String BASE_URL = "http://taqc-opencart.epizy.com/";
    private static final Long IMPLICITLY_WAIT_SECONDS = 10L;
    private static final Long ONE_SECOND_DELAY = 1000L;
    private static WebDriver driver;


    private static void presentationSleep() {
        presentationSleep(1);
    }

    private static void presentationSleep(int seconds) {
        try {
            Thread.sleep(seconds * ONE_SECOND_DELAY); // For Presentation ONLY
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /*
    private static Stream<Arguments> sumProvider() {
        return Stream.of(
                Arguments.of(1, 1, 2),
                Arguments.of(2, 3, 5)
        );
    }

    @DisplayName("Should calculate the correct sum")
    @ParameterizedTest(name = "{index} => a={0}, b={1}, sum={2}")
    @MethodSource("sumProvider")
    void sum3(int a, int b, int sum) {
        Assertions.assertEquals(sum, a + b);
    }
    */

    @BeforeAll
    public static void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_SECONDS));
        driver.manage().window().maximize();
        presentationSleep(); // For Presentation ONLY
        //
        System.out.println("@BeforeAll executed");
    }

    @AfterAll
    public static void tear() {
        driver.quit();
        //
        System.out.println("@AfterAll executed");
    }

    @BeforeEach
    public void setupThis() {
        driver.get(BASE_URL);
        presentationSleep(); // For Presentation ONLY
        //
        System.out.println("\t@BeforeEach executed");
    }

    @AfterEach
    public void tearThis() {
        // Take Screenshot, save sourceCode, save to log, prepare report, Return to;
        // previous state, logout, etc.
        //
        System.out.println("\t@AfterEach executed");
    }


    private static Stream<Arguments> products() {
        return Stream.of(
                Arguments.of(ProductRepository.get().mac())
        );
    }

    @DisplayName("Check product exist")
    @ParameterizedTest(name = "{index} => product={0}")
    @MethodSource("products")
    void checkProducts(Product product) {
        System.out.println("\tproduct = " + product);
        //
        // Choose Curency
        driver.findElement(By.cssSelector("button.btn.btn-link.dropdown-toggle")).click();
        presentationSleep(); // For Presentation ONLY
        //
        //driver.findElement(By.cssSelector("button[name='USD']")).click();
        driver.findElement(By.name(product.getCurency())).click();
        presentationSleep(); // For Presentation ONLY
        //
        // Steps
        //driver.findElement(By.cssSelector("#search > input")).click();
        driver.findElement(By.name("search")).click();
        //driver.findElement(By.cssSelector("#search > input")).clear();
        driver.findElement(By.name("search")).clear();
        //driver.findElement(By.cssSelector("#search > input")).sendKeys("mac");
        driver.findElement(By.name("search")).sendKeys(product.getSearchText());
        //driver.findElement(By.name("search")).sendKeys("mac", Keys.ENTER);
        presentationSleep(); // For Presentation ONLY
        //
        driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();
        presentationSleep(); // For Presentation ONLY
        //
        // Check
        WebElement price = driver.findElement(By.xpath("//a[text()='"
                + product.getName() + "']/../following-sibling::p[@class='price']"));
        //
        // Scrolling by Actions class
//        Actions action = new Actions(driver);
//        action.moveToElement(price).perform();
//        presentationSleep(4); // For Presentation ONLY
        //
        // Scrolling by JavaScript injection
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", price);
        presentationSleep(4); // For Presentation ONLY
        //
        Assertions.assertTrue(price.getText().contains(product.getPrice()));
        System.out.println("***contains: " + price.getText());
        presentationSleep(); // For Presentation ONLY
    }

}
