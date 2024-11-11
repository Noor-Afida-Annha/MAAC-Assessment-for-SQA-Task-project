package TestRunner;

import Pages.UserRegistration;
import SetUp.UserModel;
import SetUp.SetUp;
import Utils.Email_create_verify;
import Utils.Utilsf;
import com.github.javafaker.Faker;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;


public class UserRegTestRunner extends SetUp {
    UserRegistration userRegistration;

    @Test
    public void createUser() throws Exception {
        userRegistration = new UserRegistration(driver);
        //Thread.sleep(10000);
        userRegistration.addClick.get(1).click();
        userRegistration.menuBarRegBtn.get(1).click();
        Faker faker = new Faker();
        UserModel model = new UserModel();
        model.setFullname(faker.name().fullName());
        String disposableEmail = Email_create_verify.generateRandomEmail();
        model.setEmail(disposableEmail);


        String phoneNumber = "01502"+ Utilsf.generateRandomId(100000,999999);
        model.setPhone(phoneNumber);
        model.setPassword("P@ssword123");

        userRegistration.createNewUser(model);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement successRegElem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[contains(text(),'Check your email')]")));
        System.out.println(successRegElem.getText());
        String textTitle= successRegElem.getText();
        Assert.assertTrue(textTitle.contains("Check your email"));


        Utilsf.saveUsers(model);

        Email_create_verify.verifyEmail(disposableEmail);
    }


}
