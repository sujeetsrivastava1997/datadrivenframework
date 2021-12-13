package DDTF;

import lib.ExcelDataConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class WordPressLogin {
    WebDriver driver;

    @DataProvider(name = "wordpressData")
    public Object[][] passData() {
        ExcelDataConfig config = new ExcelDataConfig("TestData/testdata.xlsx");
        int rows = config.getRowCount(0);
        Object[][] data = new Object[rows][2];
        for (int i = 0; i < rows; i++) {
            data[i][0] = config.getData(0, i, 0);
            data[i][1] = config.getData(0, i, 1);
        }
        return data;
    }

    @Test(dataProvider = "wordpressData")
    public void loginToWordPress(String username, String password) throws InterruptedException {
//        System.setProperty("webdriver.chrome.driver","/home/knoldus/Documents/selenium/chromedriver_linux64/chromedriver");
        System.out.println(username);
        System.out.println(password);
        System.setProperty("webdriver.chrome.driver", "src/test/chromedriver_linux64/chromedriver");
        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://learner.demo.edunext.co/");

//        driver.findElement(By.cssSelector("#login-email")).sendKeys(username);
//        driver.findElement(By.cssSelector("#login-password")).sendKeys(password);
//        driver.findElement(By.xpath("//button[contains(text(),'Sign in')]")).click();
//        Thread.sleep(5000);
//        System.out.println(driver.getTitle());
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }


}
