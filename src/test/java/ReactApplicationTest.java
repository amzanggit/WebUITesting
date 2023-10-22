import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class ReactApplicationTest extends drivers.DriverFactory {
    private static final String BASE_URL = " http://sdetchallenge.fetch.com/";

    List<Integer> numbers;

    @BeforeClass
    public void visitWebPage() {
        driver.navigate().to(BASE_URL);
    }

    // Test reset button before every try
    @Test(priority = 0, enabled = true)
    public void testResetButton() {
        driver.findElement(By.xpath("//div[4]//button[1]")).click();
    }

    // Test filling out grid with bar numbers and weighing
    @Test(priority = 1, enabled = true)
    public void iPlaceBarsOnTheBowlsAndWeigh() {
        // Use GoldBarFinder algorithm to weigh the first two groups of bars
        // randomly pick 3 numbers to first and second group
        List<Integer> numbers = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8);
        Collections.shuffle(numbers);

        driver.findElement(By.xpath("//input[@id='left_0']")).sendKeys(numbers.get(0).toString());
        driver.findElement(By.xpath("//input[@id='left_1']")).sendKeys(numbers.get(1).toString());
        driver.findElement(By.xpath("//input[@id='left_2']")).sendKeys(numbers.get(2).toString());

        driver.findElement(By.xpath("//input[@id='right_0']")).sendKeys(numbers.get(3).toString());
        driver.findElement(By.xpath("//input[@id='right_1']")).sendKeys(numbers.get(4).toString());
        driver.findElement(By.xpath("//input[@id='right_2']")).sendKeys(numbers.get(5).toString());

        // click the weigh button
        driver.findElement(By.xpath("//button[@id='weigh']")).click();

        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);

        // check if the result is out
        WebElement result = driver.findElement(By.xpath("//div[@class='result']//button[@id='reset']"));
        String resultText = result.getText();

        // check if result content is < or > or =
        boolean isNotExpectedValue = "?".equals(resultText);
        Assert.assertTrue(isNotExpectedValue, "The measurement result is one of the expected values.");

        // check if the weighing list is showing
        WebElement weighingList = driver.findElement(By.xpath("//div[@class='game-info']/ol"));
        List<WebElement> listItems = weighingList.findElements(By.tagName("li"));

        // check if the weighing list is not empty
        Assert.assertFalse(listItems.isEmpty(), "Weighing list is empty.");

        // print out the results from each list item
        for (WebElement listItem : listItems) {
            System.out.println(listItem.getText());
        }

        List<WebElement> coinButtons = driver.findElements(By.cssSelector(".coins .square"));
        // randomly choose a number to click
        Collections.shuffle(coinButtons);
        WebElement randomCoinButton = coinButtons.get(0);
        randomCoinButton.click();
        //driver.findElement(By.id("coin_0")).click();

        // Switch to the alert
        Alert alert = driver.switchTo().alert();
        String actualAlertText = alert.getText();
        System.out.println("Received alert message: " + actualAlertText);

        // List of expected alert messages
        List<String> expectedAlertTexts = Arrays.asList("Oops! Try Again!", "Yay! You find it!");

        // Check if the actual alert text is one of the expected texts
        boolean isExpectedText = expectedAlertTexts.contains(actualAlertText);

        Assert.assertTrue(isExpectedText, "Unexpected alert text: " + actualAlertText);

        alert.accept();
    }
}