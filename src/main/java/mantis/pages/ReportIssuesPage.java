package mantis.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ReportIssuesPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(css = "input#summary")
    private WebElement summaryField;

    @FindBy(css = "textarea#description")
    private WebElement descriptionField;

//    @FindBy(css = "input[value='Submit Issue']")
//    private WebElement submitButton;

    public ReportIssuesPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30, 500);
        PageFactory.initElements(driver, this);
    }

    public void createIssues(String summary, String description) {
        summaryField.sendKeys(summary);
        descriptionField.sendKeys(description);
        WebElement button = driver.findElement(By.cssSelector("input[value='Submit Issue']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        button.click();
    }


}
