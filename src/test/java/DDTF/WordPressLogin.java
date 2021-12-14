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

    //Making WebDriver's global variable//
    WebDriver driver;


    //Getting data from Excel sheet//
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


    //Taking data from dataProvider and putting in selenium test//
    //Testing a dummy login page using selenium webdriver//
    @Test(dataProvider = "wordpressData")

    public void loginToWordPress(String username, String password) throws InterruptedException
    {
        System.out.println(username);
        System.out.println(password);

        //Providing path of chromedriver//
        System.setProperty("webdriver.chrome.driver", "src/test/chromedriver_linux64/chromedriver");

        //Opening the chrome browser//
        driver = new ChromeDriver();

        //Maximizing the browser//
        driver.manage().window().maximize();

        //Putting implicit wait//
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Opening dummy login website//
        driver.get("https://learner.demo.edunext.co/login?next=%2F");

        //Locating different login fields//
        driver.findElement(By.cssSelector("#login-email")).sendKeys(username);
        driver.findElement(By.cssSelector("#login-password")).sendKeys(password);
        driver.findElement(By.xpath("//button[contains(text(),'Sign in')]")).click();
        Thread.sleep(2000);
    }


    //This tearDown function quits the driver after test executed//
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
