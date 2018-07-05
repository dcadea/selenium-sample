package club.cheapok.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class SeleniumTest {

    private static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testSomething() {
        driver.get("https://www.youtube.com");
        assertThat(driver.getTitle()).isEqualTo("YouTube");
    }

    @Test
    public void searchSomething() {
        driver.get("https://www.youtube.com");
        driver.findElement(By.id("search"))
              .sendKeys("SmarterEveryDay");
        driver.findElement(By.id("search-icon-legacy"))
              .click();
        driver.findElement(By.xpath("//ytd-channel-renderer/a[@class='yt-simple-endpoint style-scope ytd-channel-renderer']"))
              .click();
        final String subs = driver.findElement(By.id("subscriber-count")).getText();

        assertThat(subs).endsWith("subscribers");
    }

    @AfterClass
    public static void tearDown() {
        driver.close();
    }

}
