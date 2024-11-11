package TestRunner;

import Pages.SignUpPage;
import SetUp.SetUp;
import Utils.Utilsf;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class SignUpTestRunner extends SetUp {
    SignUpPage signUpPage;


    @Test
    public void userSignUp() throws IOException, ParseException, InterruptedException {
        signUpPage = new SignUpPage(driver);
        JSONArray userArray = Utilsf.readJSONData();
        JSONObject userObj = (JSONObject) userArray.get(userArray.size()-1);

        String email = userObj.get("email").toString();
        String password = userObj.get("password").toString();
        signUpPage.doSignUp(email,password);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement successRegElem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("p")));
        System.out.println(successRegElem.getText());
        String textTitle= successRegElem.getText();
        Assert.assertTrue(textTitle.contains("Update your profile to get exciting offers."));
    }




}
