package site.stellarburgers.app;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ProfilePage {

    @FindBy(how = How.XPATH, using = "//*[@id=\"root\"]/div/main/div/nav/ul/li[3]/button")
    private SelenideElement exitButton;

    @FindBy(how = How.XPATH, using = "//*[@id=\"root\"]/div/main/div/div/div/ul/li[1]/div/div/input")
    private SelenideElement nameField;

    @Step("Получить текст с кнопки 'Выйти'")
    public String getNameExitButton() {
        return exitButton.getText();
    }

    @Step("Получить имя пользователя из поле 'Имя'")
    public String getNameFromNameField() {
        return nameField.getText();
    }

    @Step("Клик по кнопке 'Выйти'")
    public void clickExitButton() {
        exitButton.click();
    }
}
