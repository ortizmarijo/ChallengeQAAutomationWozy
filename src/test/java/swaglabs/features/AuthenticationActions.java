package swaglabs.features;

import net.serenitybdd.core.steps.UIInteractions;
import net.thucydides.core.annotations.Step;
import org.junit.jupiter.api.DisplayName;

/**
 * UIInteractionSteps let us define action classes which regroup related actions.
 * The @Step annotation is used to indicate that this action will appear as a step in the reports.
 */
public class AuthenticationActions extends UIInteractions {
    @Step("^Que un user ingresa sus credenciales en '{0}'$")
    public void toTheHomePage(String url) {
        openUrl(url);
        authenticate();

    }

    public void authenticate() {
        $("#user-name").sendKeys("standard_user");
        $("#password").sendKeys("secret_sauce");
        $("#login-button").click();
    }
}
