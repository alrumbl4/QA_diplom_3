package constructor;

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

public class ConstructorTest {

    private UserClient userClient;
    private User user;
    private Credentials credentials;
    private String accessToken;
    private final String URL_MAIN_PAGE = "https://stellarburgers.nomoreparties.site";
    Header header = page(Header.class);
    MainPage mainPage = page(MainPage.class);
    AuthorizationPage authorizationPage = page(AuthorizationPage.class);
    RegistrationPage registrationPage = page(RegistrationPage.class);
    ProfilePage profilePage = page(ProfilePage.class);
    PasswordRecoveryPage passwordRecoveryPage = page(PasswordRecoveryPage.class);

    @Before
    public void setUp() {
        //Для тестов через яндекс браузер нужно раскоментировать строки ниже
        /*System.setProperty("webdriver.chrome.driver","/Users/alrum/Documents/WebDriver/bin/chromedriver");
        Configuration.browserBinary = "/Applications/Yandex.app/Contents/MacOS/Yandex";*/
        //Configuration.headless = true;
        userClient = new UserClient();
        user = UserGenerator.getDefaultUser();
        credentials = Credentials.from(user);
        open(URL_MAIN_PAGE);
        header.clickPersonalAccountButton();
        authorizationPage.clickRegistrationButton();
        registrationPage.registration(user.getName(), user.getEmail(), user.getPassword());
        ValidatableResponse validatableResponse = userClient.authorizationUser(credentials);
        accessToken = validatableResponse.extract().path("accessToken");
    }

    @After
    public void deleteUser() {
        userClient.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор")
    @Description("Проверяем, что после авторизации имеется возможность перейти в конструктор бургеров")
    public void transitionToTheConstructor() {
        authorizationPage.login(credentials.getEmail(), credentials.getPassword());
        header.clickPersonalAccountButton();
        header.clickConstructorButton();
        String actual = mainPage.getTheTextCollectTheBurger();
        String expected = "Соберите бургер";
        Assert.assertEquals("Данные не совпадают", actual, expected);
    }
}
