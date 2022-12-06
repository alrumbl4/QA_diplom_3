package registration;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import site.stellarburgers.*;
import site.stellarburgers.api.UserClient;
import site.stellarburgers.app.AuthorizationPage;
import site.stellarburgers.app.Header;
import site.stellarburgers.app.RegistrationPage;

import static com.codeborne.selenide.Selenide.*;

public class RegistrationTest {

    private UserClient userClient;
    private User user;
    private Credentials credentials;
    private String accessToken;
    private final String URL_MAIN_PAGE = "https://stellarburgers.nomoreparties.site";
    Header header = page(Header.class);
    AuthorizationPage authorizationPage = page(AuthorizationPage.class);
    RegistrationPage registrationPage = page(RegistrationPage.class);

    @Before
    public void setUp() {
        userClient = new UserClient();
        user = UserGenerator.getDefaultUser();
        credentials = Credentials.from(user);
        //Для тестов через яндекс браузер нужно раскоментировать строки ниже
        /*System.setProperty("webdriver.chrome.driver","/Users/alrum/Documents/WebDriver/bin/chromedriver");
        Configuration.browserBinary = "/Applications/Yandex.app/Contents/MacOS/Yandex";*/
        Configuration.headless = true;
        open(URL_MAIN_PAGE);
    }


    @Test
    @DisplayName("Успешная регистрация пользователя")
    @Description("Проверяем возможность зарегистрировать нового пользователя в системе")
    public void registrationNewUser() {
        header.clickPersonalAccountButton();
        authorizationPage.clickRegistrationButton();
        registrationPage.registration(user.getName(), user.getEmail(), user.getPassword());
        String actual = authorizationPage.getAuthorizationHeaderText();
        String expected = "Вход";
        Assert.assertEquals("Значения не совпадают", actual, expected);
        ValidatableResponse validatableResponse = userClient.authorizationUser(credentials);
        accessToken = validatableResponse.extract().path("accessToken");
        userClient.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Регистрация пользователя с вводом невалидного пароля")
    @Description("Проверяем, что если при регистрации пользователя указать менее 6ти символов отобразиться ошибка")
    public void registeringUserWithAnInvalidPassword() {
        header.clickPersonalAccountButton();
        authorizationPage.clickRegistrationButton();
        user.setPassword("123");
        registrationPage.registration(user.getName(), user.getEmail(), user.getPassword());
        String actual = registrationPage.getErrorMessageAboutAnInvalidPassword();
        String expected = "Некорректный пароль";
        Assert.assertEquals("Значения не совпадают", actual, expected);
    }
}
