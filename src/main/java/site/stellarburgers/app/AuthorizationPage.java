package site.stellarburgers.app;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AuthorizationPage {

    @FindBy(how = How.XPATH, using = "//*[@id=\"root\"]/div/main/div/form/fieldset[1]/div/div/input")
    private SelenideElement emailField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"root\"]/div/main/div/form/fieldset[2]/div/div/input")
    private SelenideElement passwordField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"root\"]/div/main/div/form/button")
    private SelenideElement loginButton;

    @FindBy(how = How.XPATH, using = "//*[@id=\"root\"]/div/main/div/div/p[1]/a")
    private SelenideElement registrationButton;

    @FindBy(how = How.XPATH, using = "//*[@id=\"root\"]/div/main/div/div/p[2]/a")
    private SelenideElement restorePasswordButton;

    @FindBy(how = How.XPATH, using = ".//h2[.='Вход']")
    private SelenideElement authorizationHeaderText;

    @Step("Заполнение данными поля 'Email'")
    public void setEmail(String email) {
        emailField.setValue(email);
    }

    @Step("Заполнение данными поля 'Пароль'")
    public void setPassword(String password) {
        passwordField.setValue(password);
    }

    @Step("Клик по кнопке 'Войти'")
    public void clickLoginButton() {
        loginButton.click();
    }

    @Step("Клик по кнопке 'Зарегистрироваться'")
    public void clickRegistrationButton() {
        registrationButton.click();
    }

    @Step("Клик по кнопке 'Восстановить пароль'")
    public void clickRestorePasswordButton() {
        restorePasswordButton.click();
    }

    @Step("Заполнение всех необходимых полей для авторизации и нажатие на кнопку 'Войти'")
    public void login(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickLoginButton();
    }

    @Step("Получение текста заголовка на странице 'Авторизация'")
    public String getAuthorizationHeaderText() {
        return authorizationHeaderText.getText();
    }
}
