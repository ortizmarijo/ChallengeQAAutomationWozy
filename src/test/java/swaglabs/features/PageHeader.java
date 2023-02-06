package swaglabs.features;

import net.serenitybdd.core.pages.PageComponent;

public class PageHeader extends PageComponent {
    public String title() {
        return $(".title").getText();
    }
    public String titleThanks() {
            return $(".complete-header").getText();
    }

    public String pageTitle() {
        return getDriver().getTitle();
    }
}