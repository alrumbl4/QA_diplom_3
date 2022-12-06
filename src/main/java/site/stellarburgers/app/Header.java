package site.stellarburgers.app;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class Header {

    @FindBy(how = How.XPATH,using = "//*[@id=\"root\"]/div/header/nav/ul/li[1]/a")
    private SelenideElement constructorButton;

    @FindBy(how = How.XPATH,using = "//*[@id=\"root\"]/div/header/nav/div/a")
    private SelenideElement buttonOnTheLogo;

    @FindBy(how = How.XPATH,using = "//*[@id=\"root\"]/div/header/nav/a/p")
    private SelenideElement personalAccountButton;

    @Step("Клик по кнопке 'конструктор'")
    public void clickConstructorButton() {
        constructorButton.click();
    }

    @Step("Клик по логотипу")
    public void clickButtonOnTheLogo() {
        buttonOnTheLogo.click();
    }

    @Step("Клик по кнопке 'личный кабинет'")
    public AuthorizationPage clickPersonalAccountButton() {
        personalAccountButton.click();
        AuthorizationPage authorizationPage = Selenide.page(AuthorizationPage.class);
        return authorizationPage;
    }
}
