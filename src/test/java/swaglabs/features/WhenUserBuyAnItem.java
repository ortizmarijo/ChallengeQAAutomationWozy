package swaglabs.features;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Managed;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SerenityJUnit5Extension.class)
class WhenUserBuyAnItem {

    /**
     * Define the webdriver instance to be used for these tests
     */
    @Managed(driver = "chrome")
    WebDriver driver;

    AuthenticationActions authentication;
    PageHeader pageHeader;
    CatalogActions catalog;

    @Test
    @DisplayName("Compra exitosa")
    void searchBySingleKeyword() {
        authentication.toTheHomePage("https://www.saucedemo.com/");
        Serenity.reportThat("The first heading should be 'Swag Labs'",
                () -> assertThat(pageHeader.pageTitle()).isEqualToIgnoringCase("Swag Labs")
        );
        catalog.toSelectCheapestProduct();
        catalog.toMakeAnOrder();
    }
}
