package logOut;

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
import site.stellarburgers.app.AuthorizationPage;
import site.stellarburgers.app.Header;
import site.stellarburgers.app.MainPage;
import site.stellarburgers.app.ProfilePage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

@DisplayName("Выход из аккаунта")
public class logOutTest {

    private UserClient userClient = new UserClient();
    private User user = UserGenerator.getDefaultUser();
    private Credentials credentials = Credentials.from(user);
    private String accessToken;
    private final String URL_MAIN_PAGE = "https://stellarburgers.nomoreparties.site";
    MainPage mainPage = page(MainPage.class);
    Header header = page(Header.class);
    AuthorizationPage authorizationPage = page(AuthorizationPage.class);
    ProfilePage profilePage = page(ProfilePage.class);

    @Before
    public void setUp() {
        ValidatableResponse responseRegistration = userClient.registrationUser(user);
        accessToken = responseRegistration.extract().path("accessToken");
        Configuration.headless = true;
        open(URL_MAIN_PAGE);
    }

    @After
    public void deleteUser() {
        userClient.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Выход из аккаунта")
    @Description("Проверяем возможность выйти из аккаунта при клике на кнопку 'Выйти' в личном кабинете")
    public void logOutOfYourAccount() {
        mainPage.clickSignInToAccountButton();
        authorizationPage.login(credentials.getEmail(), credentials.getPassword());
        header.clickPersonalAccountButton();
        profilePage.clickExitButton();
        String actual = authorizationPage.getAuthorizationHeaderText();
        String expected = "Вход";
        Assert.assertEquals("Данные не воспадают", actual, expected);
    }
}
