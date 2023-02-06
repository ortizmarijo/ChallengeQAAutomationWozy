package swaglabs.features;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.ListOfWebElementFacades;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.steps.UIInteractions;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CatalogActions  extends UIInteractions {
    PageHeader pageHeader;

    public List<String> productTitles() {
        return findAll(".inventory_item_name").texts();
    }


    public ListOfWebElementFacades products() {
        return findAll(".inventory_item_description");
    }

    @FindBy(css="div[id='shopping_cart_container']")
    private WebElementFacade viewCartButton;
    public void getViewCartButton() {
        viewCartButton.click();
        Serenity.reportThat("The first heading should be 'Your Cart'",
                () -> assertThat(pageHeader.title()).isEqualToIgnoringCase("Your Cart")
        );
    }

    @FindBy(id="first-name")
    private WebElementFacade firstNameLoc;
    public void fillFirstName(String firstName) {
        firstNameLoc.clear();
        firstNameLoc.sendKeys(firstName);
    }

    @FindBy(id="last-name")
    private WebElementFacade lastNameLoc;
    public void fillLastName(String lastName) {
        lastNameLoc.clear();
        lastNameLoc.sendKeys(lastName);
    }

    @FindBy(id="postal-code")
    private WebElementFacade postalCodeLoc;
    public void fillPostalCode(String postalCode) {
        postalCodeLoc.clear();
        postalCodeLoc.sendKeys(postalCode);
    }

    @FindBy(id="continue")
    private WebElementFacade continueButton;
    public void getContinueButton() {
        waitFor(continueButton);
        continueButton.click();
        Serenity.reportThat("The first heading should be 'Checkout: Overview'",
                () -> assertThat(pageHeader.title()).isEqualToIgnoringCase("Checkout: Overview")
        );
    }

    @FindBy(id="finish")
    private WebElementFacade finishButton;
    public void getFinishButton() {
        waitFor(finishButton);
        finishButton.click();
        Serenity.reportThat("The first heading should be 'Checkout: Overview'",
                () -> assertThat(pageHeader.titleThanks()).isEqualToIgnoringCase("THANK YOU FOR YOUR ORDER")
        );
    }

    @FindBy(id="checkout")
    private WebElementFacade checkoutButton;
    public void getCheckoutButton() {
        waitFor(checkoutButton);
        checkoutButton.click();
        Serenity.reportThat("The first heading should be 'Checkout: Your Information'",
                () -> assertThat(pageHeader.title()).isEqualToIgnoringCase("Checkout: Your Information")
        );
    }



    @Step("^Seleccione el producto con el menor precio$")
    public void toSelectCheapestProduct() {
        ListOfWebElementFacades productsPrice = products();

        productsPrice.size();
        double cheapestPrice = 0;
        WebElementFacade cheapestItem = null;
        for(WebElementFacade elem : productsPrice){
            double price = Double.parseDouble(elem.findBy(".inventory_item_price").getText().replace("$",""));

            if(price > 0 && cheapestPrice == 0){
                cheapestPrice = price;
                cheapestItem = elem;

            }else if(price<cheapestPrice){
                cheapestPrice = price;
                cheapestItem = elem;
            }

        }
        cheapestItem.findBy(".//button[contains(text(), 'Add to cart')]").click();


    }

    @Step("^realizara la orden/pedido de manera exitosa$")
    public void toMakeAnOrder() {
        getViewCartButton();
        getCheckoutButton();

        fillFirstName("Maria Jose");
        fillLastName("Ortiz Rodriguez");
        fillPostalCode("1123");

        getContinueButton();
        getFinishButton();
    }


}