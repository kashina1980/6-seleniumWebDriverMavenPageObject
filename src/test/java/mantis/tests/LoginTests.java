package mantis.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import mantis.pages.MantisSite;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTests extends BaseTest {

    private MantisSite mantisSite;

    @Test
    public void loginUrlTest() {
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertEquals("https://academ-it.ru/mantisbt/login_page.php", currentUrl);
    }

    @Test
    public void loginTitleTest() {
        String currentTitle = driver.getTitle();
        Assertions.assertEquals("MantisBT", currentTitle);
    }

    @Test
    public void successfulLoginTest() throws InterruptedException {
        mantisSite = new MantisSite(driver);
        mantisSite.login("admin", "admin20");

        String currentUserName = mantisSite.getMainPage().getUserName();
        Assertions.assertEquals("admin", currentUserName);
        Thread.sleep(1000);
    }

    @Test
    public void checkMainPageBlocksLoadingTest() throws InterruptedException {
        mantisSite = new MantisSite(driver);
        mantisSite.login("admin", "admin20");

        Assertions.assertTrue(mantisSite.getMainPage().isAssignedToMeBlockDisplayed());
        Assertions.assertTrue(mantisSite.getMainPage().isUnassignedBlockDisplayed());
        Assertions.assertTrue(mantisSite.getMainPage().isTimeBlockDisplayed());

        Assertions.assertTrue(mantisSite.getMainPage().getTitleAssignedToMeBlock().contains("Assigned to Me"));
    }

    @Test
    public void checkMainPageBlocksLoadingTestWithSoftAssert() throws InterruptedException {
        mantisSite = new MantisSite(driver);
        mantisSite.login("admin", "admin20");

//        String title = mantisSite.getMainPage().getTitleAssignedToMeBlock();
        SoftAssertions softAssert = new SoftAssertions();

        softAssert.assertThat(mantisSite.getMainPage().isAssignedToMeBlockDisplayed()).isEqualTo(true);
        softAssert.assertThat(mantisSite.getMainPage().getTitleAssignedToMeBlock()).isEqualTo("Assigned to Me (Unresolved)");
        softAssert.assertThat(mantisSite.getMainPage().isUnassignedBlockDisplayed()).isEqualTo(true);

        softAssert.assertAll();
    }
}
