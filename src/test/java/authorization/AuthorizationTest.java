package authorization;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import site.stellarburgers.Credentials;
import site.stellarburgers.User;
import site.stellarburgers.UserGenerator;
import site.stellarburgers.api.UserClient;
import site.stellarburgers.app.*;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class AuthorizationTest {

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
        Configuration.headless = true;
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
    @DisplayName("Вход по кнопке 'Войти в аккаунт'")
    @Description("Проверяем возможность авторизоваться в аккаунт используя для этого кнопку 'Войти в аккаунт' на главной странице")
    public void logInUsingTheLogInToAccountButton() {
        header.clickButtonOnTheLogo();
        mainPage.clickSignInToAccountButton();
        authorizationPage.login(credentials.getEmail(), credentials.getPassword());
        header.clickPersonalAccountButton();
        String actual = profilePage.getNameExitButton();
        String expected = "Выход";
        Assert.assertEquals("Данные не совпадают", actual, expected);
    }

    @Test
    @DisplayName("Вход через кнопку 'Личный кабинет'")
    @Description("Проверяем возможность авторизоваться в аккаунт используя для этого кнопку 'Личный кабинет' в хедере")
    public void loginViaThePersonalAccountButton() {
        header.clickButtonOnTheLogo();
        header.clickPersonalAccountButton();
        authorizationPage.login(credentials.getEmail(), credentials.getPassword());
        header.clickPersonalAccountButton();
        String actual = profilePage.getNameExitButton();
        String expected = "Выход";
        Assert.assertEquals("Данные не совпадают", actual, expected);
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Проверяем возможность авторизоваться в аккаунт используя для этого кнопку на странице регистрации")
    public void loginViaTheButtonOnTheRegistrationPage() {
        authorizationPage.clickRegistrationButton();
        registrationPage.clickLoginButton();
        authorizationPage.login(credentials.getEmail(), credentials.getPassword());
        header.clickPersonalAccountButton();
        String actual = profilePage.getNameExitButton();
        String expected = "Выход";
        Assert.assertEquals("Данные не совпадают", actual, expected);
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Проверяем возможность авторизоваться в аккаунт используя для этого кнопку 'Войти' со страницы 'Восстановление пароля'")
    public void LoginViaTheButtonInThePasswordRecoveryForm() {
        authorizationPage.clickRestorePasswordButton();
        passwordRecoveryPage.clickLogInButton();
        authorizationPage.login(credentials.getEmail(), credentials.getPassword());
        header.clickPersonalAccountButton();
        String actual = profilePage.getNameExitButton();
        String expected = "Выход";
        Assert.assertEquals("Данные не совпадают", actual, expected);
    }
}
