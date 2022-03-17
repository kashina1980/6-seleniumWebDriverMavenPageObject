package mantis.tests;

import mantis.pages.MantisSite;
import mantis.pages.ReportIssuesPage;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IssuesTest extends BaseTest {
    private MantisSite mantisSite;
    public String idNewIssue;

    @Test
    public void checkIssuesNumber() {
        mantisSite = new MantisSite(driver);
        mantisSite.login("admin", "admin20");

        mantisSite.getMainPage().goToViewIssuesPage();
        int actualIssuesNumber = mantisSite.getViewIssuesPage().countIssues();

        Assertions.assertEquals(50, actualIssuesNumber);
    }

    @Test
    public void checkCreateNewIssues() {
        mantisSite = new MantisSite(driver);
        mantisSite.login("admin", "admin20");

        mantisSite.getMainPage().goToReportIssuesPage();
        mantisSite.getReportIssuesPage().createIssues("new bug", "description");
        mantisSite.getMainPage().goToViewIssuesPage();
        String actualIssueSummary = mantisSite.getViewIssuesPage().getNewIssueSummary();
        Assertions.assertEquals("new bug", actualIssueSummary);
        String idNewIssue = mantisSite.getViewIssuesPage().getLastIssueId();
        System.out.println(idNewIssue);
    }

    @Test
    public void checkDeleteNewIssues() {
        mantisSite = new MantisSite(driver);
        mantisSite.login("admin", "admin20");

        mantisSite.getMainPage().goToViewIssuesPage();
        String idNewIssue = mantisSite.getViewIssuesPage().getLastIssueId();
        mantisSite.getViewIssuesPage().goToViewIssuesDetailsPage();
        mantisSite.getViewIssuesDetailsPage().deleteNewIssue();
        mantisSite.getMainPage().goToViewIssuesPage();
        String actualFirstIssueID = mantisSite.getViewIssuesPage().getLastIssueId();
        System.out.println(idNewIssue);
        System.out.println(actualFirstIssueID);
        Assertions.assertNotEquals(idNewIssue, actualFirstIssueID);
    }

    @Test
    public void checkCreateAndDeleteNewIssues() {
        mantisSite = new MantisSite(driver);
        mantisSite.login("admin", "admin20");
        SoftAssertions softAssert = new SoftAssertions();

        mantisSite.getMainPage().goToReportIssuesPage();
        mantisSite.getReportIssuesPage().createIssues("new bug", "description");
        mantisSite.getMainPage().goToViewIssuesPage();
        String actualIssueSummary = mantisSite.getViewIssuesPage().getNewIssueSummary();
        softAssert.assertThat(actualIssueSummary).isEqualTo("new bug");
        String idNewIssue = mantisSite.getViewIssuesPage().getLastIssueId();

        mantisSite.getViewIssuesPage().goToViewIssuesDetailsPage();
        mantisSite.getViewIssuesDetailsPage().deleteNewIssue();
        mantisSite.getMainPage().goToViewIssuesPage();
        String actualFirstIssueID = mantisSite.getViewIssuesPage().getLastIssueId();
        System.out.println(idNewIssue);
        System.out.println(actualFirstIssueID);
        softAssert.assertThat(idNewIssue).isNotEqualTo(actualFirstIssueID);

        softAssert.assertAll();
    }
}
