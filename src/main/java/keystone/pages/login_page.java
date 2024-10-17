package keystone.pages;

import keystone.common.BrowserFactory;
import keystone.elements.*;
import keystone.elements.Button;
import keystone.utils.ConfigManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.awt.*;

public class login_page {

    private  keystone.elements.Button signInButton = new Button(By.xpath("//button[@id='SignInButton']"), "signInButton");
    private  keystone.elements.Button acceptAllCoockiesButton = new Button(By.xpath("//button[@id='onetrust-accept-btn-handler']"), "signInButton");
    private  keystone.elements.Input usernameInput = new Input(By.xpath("//input[@id='Username']"), "usernameInput");
    private  keystone.elements.Input passwordInput = new Input(By.xpath("//input[@id='Password']"), "passwordInput");

    public void populateNameANdPass() {
        usernameInput.enterText("120704");
        passwordInput.enterText("viwdEw-3fobdy-jyspud");
    }

    public void clickSignInButton() {
        signInButton.click();
    }
    public void clickAcceptAllCoockiesButton() {
        acceptAllCoockiesButton.click();
    }


    public boolean checkSignInButtonClickable() {
        return signInButton.isClickable();
    }
    public boolean checkAcceptAllCoockiesButton() {
        return acceptAllCoockiesButton.isClickable();
    }

    public Button getSignInButton() {
        return signInButton;
    }

    public void setSignInButton(Button signInButton) {
        this.signInButton = signInButton;
    }

    public Input getUsernameInput() {
        return usernameInput;
    }

    public void setUsernameInput(Input usernameInput) {
        this.usernameInput = usernameInput;
    }

    public Input getPasswordInput() {
        return passwordInput;
    }

    public void setPasswordInput(Input passwordInput) {
        this.passwordInput = passwordInput;
    }

    public void wait(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
