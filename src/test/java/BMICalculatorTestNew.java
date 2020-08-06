import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class BMICalculatorTestNew {

    WebDriver browser;

    @Test
    public void units(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        browser = new ChromeDriver();
        browser.get("https://healthunify.com/bmicalculator/");
        browser.findElement(By.name("wg")).sendKeys("47");
        browser.findElement(By.name("ht")).sendKeys("158");
        browser.findElement(By.name("cc")).click();

        String siUnits = browser.findElement(By.name("si")).getAttribute("value");
        String usUnits = browser.findElement(By.name("us")).getAttribute("value");
        String ukUnits = browser.findElement(By.name("uk")).getAttribute("value");

        Assert.assertEquals(siUnits, "18.83",//(47/1.58/1.58 = 18,83)
                "SIUnits не работает");
        Assert.assertEquals(usUnits, "19.14",//(47/1.58/1.58 = 18,83)
                "USUnits не работает");
        Assert.assertEquals(ukUnits, "119.57",//(47/1.58/1.58 = 18,83)
                "UKUnits не работает");
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        //6. Закрыть браузер
        browser.quit();
    }
}
