import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class BMICalculatorTest {

    WebDriver browser;

    @Test//аннотация
    public void calculateKgCMSNormal() {
        //-1. Установить переменную среды
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        //0. Открыть браузер
        browser = new ChromeDriver();
        //1. Открыть сайт https://healthunify.com/bmicalculator/
        browser.get("https://healthunify.com/bmicalculator/");
        //2. Ввести вес 83
        browser.findElement(By.name("wg")).sendKeys("83");
        //3. Ввести рост 185
        browser.findElement(By.name("ht")).sendKeys("185");
        //4. Нажать кнопку Calculate
        browser.findElement(By.name("cc")).click();
        //5. Проверить категорию (индексы)
        String category = browser.findElement(By.name("desc")).getAttribute("value");

        Assert.assertEquals(category, "Your category is Normal",
                "Категория не соответсвует ожидаемой");
    }

    @Test
    public void calculateKgCMSObese() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        browser = new ChromeDriver();
        browser.get("https://healthunify.com/bmicalculator/");
        browser.findElement(By.name("wg")).sendKeys("83");
        browser.findElement(By.name("ht")).sendKeys("150");
        browser.findElement(By.name("cc")).click();
        String category = browser.findElement(By.name("desc")).getAttribute("value");
        Assert.assertEquals(category, "Your category is Obese",
                "Категория не соответсвует ожидаемой");
    }

    @Test
    public void calculateKgCMSUnderweight() { //18.5 граница между Underweight и Normal

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        browser = new ChromeDriver();
        browser.get("https://healthunify.com/bmicalculator/");
        browser.findElement(By.name("wg")).sendKeys("47");
        browser.findElement(By.name("ht")).sendKeys("160.0");
        browser.findElement(By.name("cc")).click();

        String category = browser.findElement(By.name("desc")).getAttribute("value");

        Assert.assertEquals(category, "Your category is Underweight",
                "Категория не соответсвует ожидаемой");
    }

    @Test
    public void weightFieldIsEmpty(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        browser = new ChromeDriver();
        browser.get("https://healthunify.com/bmicalculator/");
        browser.findElement(By.name("wg")).sendKeys("");
        browser.findElement(By.name("ht")).sendKeys("157");
        browser.findElement(By.name("cc")).click();
        Alert alert = browser.switchTo().alert();
        String text = alert.getText();
        Assert.assertEquals(text,"Enter the value for weight",
                "нет сообщения о том что поле \"Weight:\"не заполненно");
    }

    @Test
    public void heightFieldIsMinValue(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        browser = new ChromeDriver();
        browser.get("https://healthunify.com/bmicalculator/");
        browser.findElement(By.name("wg")).sendKeys("47");
        browser.findElement(By.name("ht")).sendKeys("");
        browser.findElement(By.name("cc")).click();
        Alert alert = browser.switchTo().alert();
        String text = alert.getText();
        Assert.assertEquals(text,"Height should be taller than 33cms",
                "нет сообщения о том что поле \"Height\"не заполненно");
    }

        @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        //6. Закрыть браузер
        browser.quit();
    }
}