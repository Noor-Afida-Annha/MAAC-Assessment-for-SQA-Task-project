package SetUp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;

import java.time.Duration;

public class SetUp {

    public static WebDriver driver;
    @BeforeTest
    public void setup(){
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get("https://www.xampro.org/");

}

    //@AfterMethod
    public void closeBrowser(){
        driver.quit();
    }
}
