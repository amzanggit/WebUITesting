import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class MyTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    private static final String BASE_URL = " http://sdetchallenge.fetch.com/";

    @BeforeClass
    public void visitWebPage() {
        driver.navigate().to(BASE_URL);
    }

    // Test button clicks for reset
    @Test(priority = 0, enabled = true)
    public void testButton() {
        driver.findElement(By.xpath("//div[4]//button[1]")).click();
    }

    // Test filling out grid with bar numbers
    //
}
