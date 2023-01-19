package site.stellarburgers.app;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class PasswordRecoveryPage {

    @FindBy(how = How.XPATH, using = "//*[@id=\"root\"]/div/main/div/div/p/a")
    private SelenideElement logInButton;

    @Step("Клик по кнопке 'Войти'")
    public void clickLogInButton() {
        logInButton.click();
    }


}
