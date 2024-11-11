package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SignUpPage {
    @FindBy(className = "btn-close")
    public List<WebElement> addClick;

    @FindBy(className = "d-flex")
    List<WebElement> menuBarBtn;

    @FindBy(id="email")
    WebElement txtUsername;

    @FindBy(id="password")
    WebElement txtPassword;

    @FindBy(className="account-access-submit-button")
    public  List<WebElement> loginBtn;


    public SignUpPage(WebDriver driver){
        PageFactory.initElements(driver,this) ;
    }

    public void doSignUp(String email, String password){
        addClick.get(1).click();
        menuBarBtn.get(0).click();
        txtUsername.sendKeys(email);
        txtPassword.sendKeys(password);
        loginBtn.get(0).click();
    }
}
