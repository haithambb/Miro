package providers;

import constants.SignUpConstants;
import org.testng.annotations.DataProvider;

public class SignUpDataProvider {
    @DataProvider(name = "SocialSignUpDataProvider")
    public static Object[][] getSocialSignUpDataProvider() {
        return new Object[][]{
                {SignUpConstants.GOOGLE, "accounts.google.com"},
                {SignUpConstants.SLACK, "slack.com/workspace-signin"},
                {SignUpConstants.OFFICE365, "login.microsoftonline.com"},
                {SignUpConstants.APPLE, "appleid.apple.com"},
                {SignUpConstants.FACEBOOK, "www.facebook.com/login.php"}
        };
    }

    // TODO Use this data provider to check legal links on the sign up page
    @DataProvider(name = "LegalLinksDataProvider")
    public static Object[][] getLegalLinksDataProvider() {
        return new Object[][]{
                {SignUpConstants.TERMS, "https://miro.com/legal/terms-of-service/"},
                {SignUpConstants.PRIVACY, "https://miro.com/legal/privacy-policy/"},
        };
    }
}
