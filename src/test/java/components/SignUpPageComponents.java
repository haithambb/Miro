package components;

import constants.SignUpConstants;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPageComponents {

    private final WebDriver driver;
    @FindBy(name = "signup[name]")
    private WebElement nameTextInput;
    @FindBy(name = "signup[email]")
    private WebElement emailTextInput;
    @FindBy(name = "signup[password]")
    private WebElement passwordTextInput;
    @FindBy(name = "signup[terms]")
    private WebElement termsCheckboxInput;
    @FindBy(name = "signup[subscribe]")
    private WebElement subscribeCheckboxInput;
    @FindBy(className = "signup__submit")
    private WebElement regularSignUpButton;
    // @Parameters({"Google", "Slack", "Office365", "Apple", "Facebook"})
    @FindBy(id = "kmq-google-button")
    private WebElement googleSignUpButton;
    @FindBy(id = "kmq-slack-button")
    private WebElement slackSignUpButton;
    @FindBy(id = "kmq-office365-button")
    private WebElement office365SignUpButton;
    @FindBy(id = "apple-auth")
    private WebElement appleSignUpButton;
    @FindBy(className = "signup__btn--facebook")
    private WebElement facebookSignUpButton;
    @FindBy(className = "socialtos__btn")
    private WebElement continueToSignUpButtonInDialog;
    @FindBy(id = "tos-signup-terms")
    private WebElement signUpTermsCheckboxInDialog;
    @FindBy(id = "nameError")
    private WebElement nameErrorMessage;
    @FindBy(id = "emailError")
    private WebElement emailErrorMessage;
    @FindBy(css = "#signup-form-password[role=alert]")
    private WebElement passwordErrorMessage;
    @FindBy(id = "termsError")
    private WebElement termsErrorMessage;

    public SignUpPageComponents(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getNameInputValue() {
        return nameTextInput.getAttribute("value");
    }

    public void typeName(String value) {
        nameTextInput.sendKeys(value);
    }

    public String getEmailInputValue() {
        return emailTextInput.getAttribute("value");
    }

    public void typeEmail(String value) {
        emailTextInput.sendKeys(value);
    }

    public String getPasswordInputValue() {
        return passwordTextInput.getAttribute("value");
    }

    public void typePassword(String value) {
        passwordTextInput.sendKeys(value);
    }

    public boolean getTermsCheckboxValue() {
        return termsCheckboxInput.isSelected();
    }

    public void checkTermsCheckbox() {
        if (!termsCheckboxInput.isSelected()) {
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("document.getElementById('signup-terms').click();");
        }
    }

    public void checkSignUpTermsCheckboxInDialog() {
        if (!signUpTermsCheckboxInDialog.isSelected()) {
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("document.getElementById('tos-signup-terms').click();");
        }
    }

    public boolean getSubscribeCheckboxValue() {
        return termsCheckboxInput.isSelected();
    }

    public void checkSubscribeCheckbox() {
        if (!termsCheckboxInput.isSelected())
            termsCheckboxInput.click();
    }

    public WebElement getRegularSignUpButton() {
        return regularSignUpButton;
    }

    public WebElement getNameErrorMessage() {
        return nameErrorMessage;
    }

    public WebElement getEmailErrorMessage() {
        return emailErrorMessage;
    }

    public WebElement getPasswordErrorMessage() {
        return passwordErrorMessage;
    }

    public WebElement getTermsErrorMessage() {
        return termsErrorMessage;
    }

    public WebElement getGoogleSignUpButton() {
        return googleSignUpButton;
    }

    public WebElement getContinueToSignUpButtonInDialog() {
        return continueToSignUpButtonInDialog;
    }

    public void clickSocialSignUpButton(String socialSignUpButtonName) {
        WebElement socialSignUpButton;
        switch (socialSignUpButtonName) {
            case SignUpConstants.GOOGLE:
                socialSignUpButton = googleSignUpButton;
                break;
            case SignUpConstants.SLACK:
                socialSignUpButton = slackSignUpButton;
                break;
            case SignUpConstants.OFFICE365:
                socialSignUpButton = office365SignUpButton;
                break;
            case SignUpConstants.APPLE:
                socialSignUpButton = appleSignUpButton;
                break;
            case SignUpConstants.FACEBOOK:
                socialSignUpButton = facebookSignUpButton;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + socialSignUpButtonName);
        }
        socialSignUpButton.click();
    }
}
