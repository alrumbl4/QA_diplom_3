package site.stellarburgers.app;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class MainPage {

    @FindBy(how = How.XPATH,using = "//*[@id=\"root\"]/div/main/section[2]/div/button")
    private SelenideElement signInToAccountButton;

    @FindBy(how = How.XPATH,using = "//*[@id=\"root\"]/div/main/section[1]/div[1]/div[1]")
    private SelenideElement bunsButton;

    @FindBy(how = How.XPATH,using = "//*[@id=\"root\"]/div/main/section[1]/div[1]/div[2]")
    private SelenideElement sausesButton;

    @FindBy(how = How.XPATH,using = "//*[@id=\"root\"]/div/main/section[1]/div[1]/div[3]")
    private SelenideElement fillingButton;

    @FindBy(how = How.XPATH,using = "//*[@id=\"root\"]/div/main/section[1]/h1")
    private SelenideElement collectTheBurgerHeadline;

    @Step("Клик по кнопке 'Войти в аккаунт'")
    public AuthorizationPage clickSignInToAccountButton() {
        signInToAccountButton.click();
        AuthorizationPage authorizationPage = Selenide.page(AuthorizationPage.class);
        return authorizationPage;
    }

    @Step("Клик по кнопке 'Булки'")
    public void clickBunsButton() {
        bunsButton.click();
    }

    @Step("Клик по кнопке 'Соусы'")
    public void clickSausesButton() {
        sausesButton.click();
    }

    @Step("Клик по кнопке 'Начинки'")
    public void clickFillingButton() {
        fillingButton.click();
    }

    @Step("Получить текст заголовока 'Собери бургер'")
    public String getTheTextCollectTheBurger() {
        return collectTheBurgerHeadline.getText();
    }
}
