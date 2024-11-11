package SetUp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EmailGenerateNverify extends SetUp {
    WebDriver driver;
    String tempEmail;
    public EmailGenerateNverify(WebDriver driver) {
        this.driver = driver;
    }

    // Method to generate a new temporary email
    public String generateTemporaryEmail() {
        driver.get("https://internxt.com/temporary-email");

        //WebDriverWait wait1 = new WebDriverWait(driver,Duration.ofSeconds(10));
        // generateClick = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("(//p)[4]")));
        //generateClick.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement emailElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//p)[3]"))); // Replace with the actual ID or locator for the email address element

        tempEmail = emailElement.getText();  // Retrieve the generated email address
        System.out.println("Generated temporary email: " + tempEmail);

        return tempEmail;
    }

    // Method to access the inbox and verify email
    public void verifyEmailInInbox() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        //WebElement refreshClk = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//p)[8]")));
        //refreshClk.click();
        WebElement inboxItem = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button)[9]"))); // Adjust the locator based on the inbox structure
        inboxItem.click();  // Open the email

        WebElement verificationLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(), 'Click Here')]"))); // Adjust based on the verification link
        verificationLink.click();  // Click the verification link
    }
}


