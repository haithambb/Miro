import components.SignUpPageComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import providers.SignUpDataProvider;


public class SignUpPageTests {

    private WebDriver driver;
    private SignUpPageComponents signUpPageComponents;

    @BeforeClass
    public void testSetup() {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver = new ChromeDriver();
    }

    @BeforeMethod
    public void openBrowser() {
        driver.get("https://miro.com/signup/");
        signUpPageComponents = new SignUpPageComponents(driver);
    }

    @Test(
            description = "Check that messages for empty required fields are being displayed"
    )
    public void TestErrorMessagesForEmptyRequiredFields() {
        Assert.assertFalse(signUpPageComponents.getNameErrorMessage().isDisplayed());
        Assert.assertFalse(signUpPageComponents.getEmailErrorMessage().isDisplayed());
        Assert.assertFalse(signUpPageComponents.getPasswordErrorMessage().isDisplayed());
        Assert.assertFalse(signUpPageComponents.getTermsErrorMessage().isDisplayed());

        signUpPageComponents.getRegularSignUpButton().click();

        Assert.assertEquals(signUpPageComponents.getNameErrorMessage().getText(), "Please enter your name.");
        Assert.assertEquals(signUpPageComponents.getEmailErrorMessage().getText(), "Please enter your email address.");
        Assert.assertEquals(signUpPageComponents.getPasswordErrorMessage().getText(), "Please enter your password.");
        Assert.assertEquals(signUpPageComponents.getTermsErrorMessage().getText(), "Please agree with the Terms to sign up.");
    }

    @Test(
            description = "Test that regular (work email) sign up flow is working as expected"
    )
    public void TestRegularSignUpFlowWithAlreadyExistingUser() {
        signUpPageComponents.typeName("Name");
        String email = String.format("user@email.com", System.currentTimeMillis());
        signUpPageComponents.typeEmail(email);
        signUpPageComponents.typePassword("123456789");
        signUpPageComponents.checkTermsCheckbox();
        signUpPageComponents.getRegularSignUpButton().click();

        Assert.assertEquals(signUpPageComponents.getEmailErrorMessage().getText(), "Sorry, this email is already registered");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        Assert.assertEquals(driver.getCurrentUrl(), "https://miro.com/signup/");
    }

    @Test(
            description = "Test that regular (work email) sign up flow is working as expected"
    )
    public void TestRegularSignUpFlowWithNewUser() {
        signUpPageComponents.typeName("Name");
        String email = String.format("user_%d@email.com", System.currentTimeMillis());
        signUpPageComponents.typeEmail(email);
        signUpPageComponents.typePassword("123456789");
        signUpPageComponents.checkTermsCheckbox();
        signUpPageComponents.getRegularSignUpButton().click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("signup__title-form")));
        Assert.assertEquals(driver.getCurrentUrl(), "https://miro.com/email-confirm/");
        String confirmationMessage = driver.findElement(By.className("signup__title-form")).getText();
        Assert.assertEquals(confirmationMessage, "Check your email");
    }

    @Test(
            description = "Test that social sign up flows are working as expected",
            dataProvider = "SocialSignUpDataProvider",
            dataProviderClass = SignUpDataProvider.class
    )
    public void TestSocialSignUpFlow(String socialSignUp, String signUpUrl) {
        signUpPageComponents.clickSocialSignUpButton(socialSignUp);
        signUpPageComponents.checkSignUpTermsCheckboxInDialog();
        signUpPageComponents.getContinueToSignUpButtonInDialog().click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlContains(signUpUrl));
    }

    @Test(
            description = "Test that 'Terms' and 'Privacy Policy' links are working as expected",
            enabled = false
    )
    public void TestTermsAndPrivacyPolicyLinks(String socialSignUp, String signUpUrl) {
        // TODO implement a check of the links
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
