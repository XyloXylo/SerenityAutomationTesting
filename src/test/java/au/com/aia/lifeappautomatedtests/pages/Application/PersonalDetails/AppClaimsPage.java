package au.com.aia.lifeappautomatedtests.pages.Application.PersonalDetails;

import au.com.aia.lifeappautomatedtests.pages.BasePage;
import au.com.aia.lifeappautomatedtests.utility.UnderWriting;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by aadm234 on 9/08/2017.
 */
public class AppClaimsPage extends BasePage {
    public AppClaimsPage(WebDriver driver) {
        super(driver);
    }

    String indexOfClaim;
    String txtCompanyName = "//input[@aria-label='Name of company']";
    String txtClaimYear = "//input[@aria-label='Year of the claim']";
    String txtClaimAmount = "//input[@aria-label='Amount']";
    String txtClaimReason = "//textarea[@aria-label='Reason for claim']";

    String claimFinalisedYes = "//div[contains(concat(' ', @class, ' '), ' claimFinalised ')]//input[@aria-label='YES']";
    String claimFinalisedNo = "//div[contains(concat(' ', @class, ' '), ' claimFinalised ')]//input[@aria-label='NO']";
    String fullyRecoveredYes = "//div[contains(concat(' ', @class, ' '), ' fullyRecovered ')]//input[@aria-label='YES']";
    String fullyRecoveredNo = "//div[contains(concat(' ', @class, ' '), ' fullyRecovered ')]//input[@aria-label='NO']";

    String dtDayOfLastSymptom = "//input[@placeholder='DD']";
    String dtMonthOfLastSymptom = "//input[@placeholder='MM']";
    String dtYearOfLastSymptom = "//label[text()='Year']//following-sibling::input[@placeholder='YYYY']";


    @FindBy(xpath = "//p[text()='Claims']")
    public WebElementFacade claimsHeader;

    @FindBy(css = "div[class~='claimedBenefitsBefore'] input[aria-label='YES']")
    public WebElementFacade rdClaimedBenefitYes;

    @FindBy(css = "div[class~='claimedBenefitsBefore'] input[aria-label='NO']")
    public WebElementFacade rdClaimedBenefitNo;

    @FindBy(xpath = "//button[@aria-label='Add another claim?']")
    public WebElementFacade btnAddAnotherClaim;

    @FindBy(xpath = "//span[contains(text(),'Claim')]/../../..")
    public List<WebElementFacade> allClaimsPanel;

    public void setClaimIndex(String indexOfClaim){
        this.indexOfClaim = indexOfClaim;
    }

    public void selectIfClaimedBenefits(Map<String, String> claimData){
        if (claimData.get("claimed benefits").equalsIgnoreCase("yes")){
            rdClaimedBenefitYes.waitUntilVisible().click();
            UnderWriting underWriting = new UnderWriting();
            underWriting.setRequiresUnderWriting(true);
        }else{
            rdClaimedBenefitNo.waitUntilPresent();
            rdClaimedBenefitNo.waitUntilVisible().click();
        }
    }

    public void removeClaims(String confirmation){
        //Get the total count of claims added
        System.out.println("All Claims Added: "+allClaimsPanel.size());
        Serenity.setSessionVariable("totalclaims").to(allClaimsPanel.size());

        String claimsPanel = "//span[contains(text(),'Claim "+indexOfClaim+"')]/../../..";
        String path = claimsPanel+"//button[text()='Remove']";
        scrollToElementView(By.xpath(path));
        WebElementFacade btnRemoveClaims = findBy(path);
        btnRemoveClaims.waitUntilVisible().click();
        //Handle the confirmation mesage - LQA371
        if (confirmation.toLowerCase().contains("no")){
            deleteInformation("no");
        }else{
            deleteInformation("yes");
        }

        removeAccordionItemYes.waitUntilNotVisible();
    }

    public void validateClaimIsRemoved(){
        System.out.println("Claims after deletion: "+allClaimsPanel.size());
        assertThat(Integer.parseInt(Serenity.getCurrentSession().get("totalclaims").toString())-allClaimsPanel.size()).as("Validate claim is deleted successfully").isEqualTo(1);
    }

    public void validateClaimIsNotRemoved(){
        System.out.println("Claims after deletion: "+allClaimsPanel.size());
        assertThat(Integer.parseInt(Serenity.getCurrentSession().get("totalclaims").toString())-allClaimsPanel.size()).as("Validate claim is not deleted").isEqualTo(0);
    }


    public void enterClaimsData(Map<String, String> claimsData){
        String claimsPanel = "//span[contains(text(),'Claim "+indexOfClaim+"')]/../../..";
        WebElementFacade companyName = findBy(claimsPanel+txtCompanyName);
        WebElementFacade claimYear = findBy(claimsPanel+txtClaimYear);
        WebElementFacade claimAmount = findBy(claimsPanel+txtClaimAmount);
        WebElementFacade claimReason = findBy(claimsPanel+txtClaimReason);
        WebElementFacade claimFinalYes = findBy(claimsPanel+claimFinalisedYes);
        WebElementFacade claimFinalNo = findBy(claimsPanel+claimFinalisedNo);
        WebElementFacade fullRecoverYes = findBy(claimsPanel+fullyRecoveredYes);
        WebElementFacade fullRecoverNo = findBy(claimsPanel+fullyRecoveredNo);
        WebElementFacade dayOfLastSymptom = findBy(claimsPanel+dtDayOfLastSymptom);
        WebElementFacade monthOfLastSymptom = findBy(claimsPanel+dtMonthOfLastSymptom);
        WebElementFacade yearOfLastSymptom = findBy(claimsPanel+dtYearOfLastSymptom);

        companyName.waitUntilVisible().sendKeys(claimsData.get("company name"));
        claimYear.waitUntilVisible().sendKeys(claimsData.get("claim year"));
        claimAmount.waitUntilVisible().sendKeys(claimsData.get("claim amount"));
        claimReason.waitUntilVisible().sendKeys(claimsData.get("reason for claim"));
        if (claimsData.get("claim finalised").equalsIgnoreCase("yes")){
            claimFinalYes.waitUntilVisible().click();
        }else{
            claimFinalNo.waitUntilVisible().click();
        }
        if (claimsData.get("fully recovered").equalsIgnoreCase("yes")){
            fullRecoverYes.click();
        }else{
            fullRecoverNo.click();
        }

        //break the date into dd,mm,yyyy
        String dtTestData[] = claimsData.get("last symptoms date").split("/");
        String dd = dtTestData[0];
        String mm = dtTestData[1];
        String yyyy = dtTestData[2];
        dayOfLastSymptom.sendKeys(dd);
        dayOfLastSymptom.sendKeys(Keys.TAB);
        monthOfLastSymptom.sendKeys(mm);
        monthOfLastSymptom.sendKeys(Keys.TAB);
        yearOfLastSymptom.waitUntilPresent();
        yearOfLastSymptom.waitUntilClickable();
        yearOfLastSymptom.sendKeys(yyyy);
    }

    public void validateClaimIsCollapsed(String claimIndex){
        findBy("//span[contains(text(),'Claim "+claimIndex+"')]/../../../..//div[@class='stepped']").shouldBeVisible();
    }

    public void validateNewClaimQuestionsAreAdded(String claimIndex){
        findBy("//span[contains(text(),'Claim "+claimIndex+"')]/../../../..//div[@class='active']").shouldBeVisible();
    }

}

