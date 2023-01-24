package constructorAndLogo;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import site.stellarburgers.Credentials;
import site.stellarburgers.User;
import site.stellarburgers.UserGenerator;
import site.stellarburgers.api.UserClient;
import site.stellarburgers.app.*;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

@DisplayName("Переход на страницу конструктора")
public class ConstructorAndLogoTest {

    private UserClient userClient;
    private User user;
    private Credentials credentials;
    private String accessToken;
    private final String URL_MAIN_PAGE = "https://stellarburgers.nomoreparties.site";
    Header header = page(Header.class);
    MainPage mainPage = page(MainPage.class);
    AuthorizationPage authorizationPage = page(AuthorizationPage.class);

    @Before
    public void setUp() {
        //Для тестов через яндекс браузер нужно раскоментировать строки ниже
        /*System.setProperty("webdriver.chrome.driver","/Users/alrum/Documents/WebDriver/bin/chromedriver");
        Configuration.browserBinary = "/Applications/Yandex.app/Contents/MacOS/Yandex";*/
        Configuration.headless = true;
        userClient = new UserClient();
        user = UserGenerator.getDefaultUser();
        credentials = Credentials.from(user);
        ValidatableResponse responseRegistration = userClient.registrationUser(user);
        accessToken = responseRegistration.extract().path("accessToken");
        open(URL_MAIN_PAGE);
    }

    @After
    public void deleteUser() {
        userClient.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор")
    @Description("Проверяем, что со страницы профиля пользователя можно перейти в конструктор бургеров")
    public void transitionToTheConstructor() {
        header.clickPersonalAccountButton();
        authorizationPage.login(credentials.getEmail(), credentials.getPassword());
        header.clickPersonalAccountButton();
        header.clickConstructorButton();
        String actual = mainPage.getTheTextCollectTheBurger();
        String expected = "Соберите бургер";
        Assert.assertEquals("Данные не совпадают", actual, expected);
    }

    @Test
    @DisplayName("Переход на главную страницу по клику на логотип")
    @Description("Проверяем, что со страницы профиля пользователя можно перейти на главную страницу кликнув на логотип")
    public void goToTheMainPageByClickingOnTheLogo() {
        header.clickPersonalAccountButton();
        authorizationPage.login(credentials.getEmail(), credentials.getPassword());
        header.clickPersonalAccountButton();
        header.clickButtonOnTheLogo();
        String actual = mainPage.getTheTextCollectTheBurger();
        String expected = "Соберите бургер";
        Assert.assertEquals("Данные не совпадают", actual, expected);
    }
}
