package constructorAndLogo;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import site.stellarburgers.app.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class switchingBetweenDesignerTabsTest {

    MainPage mainPage = page(MainPage.class);
    private final String URL_MAIN_PAGE = "https://stellarburgers.nomoreparties.site";

    @Before
    public void setUp() {
        Configuration.headless = true;
        open(URL_MAIN_PAGE);
    }

    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    @Description("Проверяем, что в конструкторе можно переключиться на раздел 'Соусы'")
    public void goToTheSaucesTab() {
        mainPage.clickSausesButton();
        String actual = mainPage.getClassSaucesButton();
        String expected = "tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect";
        Assert.assertEquals("Данные не совпадают", actual, expected);
    }

    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    @Description("Проверяем, что в конструкторе можно переключиться на раздел 'Начинки'")
    public void goToTheFillingsSection() {
        mainPage.clickFillingButton();
        String actual = mainPage.getClassFillingButton();
        String expected = "tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect";
        Assert.assertEquals("Данные не совпадают", actual, expected);
    }

    @Test
    @DisplayName("Переход к разделу 'Булки'")
    @Description("Проверяем, что в конструкторе можно переключиться на раздел 'Булки'")
    public void goToTheBunsSection() {
        mainPage.clickFillingButton();
        mainPage.clickBunsButton();
        String actual = mainPage.getClassBunsButton();
        String expected = "tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect";
        Assert.assertEquals("Данные не совпадают", actual, expected);
    }
}
