package site.stellarburgers.app;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class RegistrationPage {

    @FindBy(how = How.XPATH, using = "//*[@id=\"root\"]/div/main/div/form/fieldset[1]/div/div/input")
    private SelenideElement nameField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"root\"]/div/main/div/form/fieldset[2]/div/div/input")
    private SelenideElement emailField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"root\"]/div/main/div/form/fieldset[3]/div/div/input")
    private SelenideElement passwordField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"root\"]/div/main/div/form/button")
    private SelenideElement registrationButton;

    @FindBy(how = How.XPATH, using = "//*[@id=\"root\"]/div/main/div/div/p/a")
    private SelenideElement loginButton;

    @FindBy(how = How.XPATH, using = "//*[@id=\"root\"]/div/main/div/form/fieldset[3]/div/p")
    private SelenideElement errorInvalidPassword;

    @Step("Заполнение данными поля 'Имя'")
    public void setName(String username) {
        nameField.setValue(username);
    }

    @Step("Заполнение данными поля 'Email'")
    public void setEmail(String email) {
        emailField.setValue(email);
    }

    @Step("Заполнение данными поля 'Пароль'")
    public void setPassword(String password) {
        passwordField.setValue(password);
    }

    @Step("Клик по кнопке 'Зарегистрироваться'")
    public AuthorizationPage clickRegistrationButton() {
        registrationButton.click();
        AuthorizationPage authorizationPage = Selenide.page(AuthorizationPage.class);
        return authorizationPage;
    }

    @Step("Клик по кнопке 'Войти'")
    public void clickLoginButton() {
        loginButton.click();
    }

    @Step("Заполнение всех необходимых полей для регистрации и нажатие на кнопку 'Зарегистрироваться'")
    public void registration(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegistrationButton();
    }

    @Step("Получение сообщения о вводе некорректного пароля на странице 'Регистрация'")
    public String getErrorMessageAboutAnInvalidPassword() {
        return errorInvalidPassword.getText();
    }
}