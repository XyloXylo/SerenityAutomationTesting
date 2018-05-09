package au.com.aia.lifeappautomatedtests.pages.Application.PersonalDetails;

import au.com.aia.lifeappautomatedtests.pages.BasePage;
import au.com.aia.lifeappautomatedtests.utility.UnderWriting;
import cucumber.api.DataTable;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by aadm234 on 9/08/2017.
 */
public class OtherCoverPolicyPage extends BasePage {
    public OtherCoverPolicyPage(WebDriver driver) {
        super(driver);
    }

    String indexOfPolicy;
    String lstBenefitPeriod = "//select[@aria-label='Benefit period?']";
    String lstWaitingPeriod = "//select[@aria-label='Waiting period?']";
    String txtPolicyNumber = "//input[@aria-label='Policy Number']";
    String dtCommencingDate = "//input[contains(@aria-label,'Commencing date')]";
    String txtPolicyHolder = "//input[@aria-label='Name of policy holder']";
    String txtInsurer = "//input[@aria-label='Insurer']";

    String rdReplaceCoverYes = "//label[contains(text(),'Are you replacing this cover with this application?')]/..//following-sibling::div[contains(concat(' ', @class, ' '), ' guideRadioButtonGroupItems ')]//input[@aria-label='YES']";
    String rdReplaceCoverNo = "//label[contains(text(),'Are you replacing this cover with this application?')]/..//following-sibling::div[contains(concat(' ', @class, ' '), ' guideRadioButtonGroupItems ')]//input[@aria-label='NO']";

    String txtDeathCoverAmount = "//input[@aria-label='Death']";
    String txtIpCoverAmount = "//input[@aria-label='IP']";
    String txtTpdCoverAmount = "//input[@aria-label='TPD']";

    @FindBy(xpath = "//label[text()='Do you have, or are you applying for life, disability or trauma insurance on your life other than this application (including any pending applications held with any insurer)?']")
    public WebElementFacade lblMainQ;

//    @FindBy(xpath = "//div[contains(concat(' ', @class, ' '), ' isAnyOtherInsurance ') and contains(@style,'yes.PNG')]")
    @FindBy(css = "div[class~='isAnyOtherInsurance'] input[aria-label='YES']")
    public WebElementFacade rdOtherCoverYes;

//    @FindBy(xpath = "//div[contains(concat(' ', @class, ' '), ' isAnyOtherInsurance ') and contains(@style,'no.PNG')]")
    @FindBy(css = "div[class~='isAnyOtherInsurance'] input[aria-label='NO']")
    public WebElementFacade rdOtherCoverNo;

    @FindBy(xpath = "//button[@aria-label='Add another policy?']")
    public WebElementFacade btnAddAnotherPolicy;

    @FindBy(xpath = "//span[contains(text(),'Policy')]/../../..")
    public List<WebElementFacade> allPoliciesPanel;

    @FindBy(xpath = "//div[contains(concat(' ', @class, ' '), ' otherCoverPage2_frag ')]")
    public WebElementFacade policyPage;

    public void enterOtherCoverData(Map<String, String> otherCoverData){
        if (otherCoverData.get("other cover").equalsIgnoreCase("yes")){
            rdOtherCoverYes.waitUntilVisible().click();
            UnderWriting underWriting = new UnderWriting();
            underWriting.setRequiresUnderWriting(true);
        }else{
            System.out.println("Click no for other cover");
            rdOtherCoverNo.waitUntilPresent();
            rdOtherCoverNo.waitUntilVisible().click();
            System.out.println("click done");
        }
    }

    public void setIndexOfPolicy(String indexOfPolicy){
        this.indexOfPolicy = indexOfPolicy;
    }

    public void validatePolicyIsRemoved(){
        System.out.println("Policies after deletion: "+allPoliciesPanel.size());
        assertThat(Integer.parseInt(Serenity.getCurrentSession().get("totalpolicy").toString())-allPoliciesPanel.size()).as("Validate policy is deleted successfully").isEqualTo(1);
    }

    public void validatePolicyIsNotRemoved(){
        System.out.println("Policies after deletion: "+allPoliciesPanel.size());
        assertThat(Integer.parseInt(Serenity.getCurrentSession().get("totalpolicy").toString())-allPoliciesPanel.size()).as("Validate policy is not deleted").isEqualTo(0);
    }

    public void removePolicy(String confirmOption){
        //Get the total count of policies added
        System.out.println("All Policies Added: "+allPoliciesPanel.size());
        Serenity.setSessionVariable("totalpolicy").to(allPoliciesPanel.size());

        String policyPanel = "//span[contains(text(),'Policy "+indexOfPolicy+"')]/../../..";
        String path = policyPanel+"//button[text()='Remove']";
        scrollToElementView(By.xpath(path));
        WebElementFacade btnRemovePolicy = findBy(path);
        btnRemovePolicy.waitUntilVisible().click();
        //Handle the confirmation mesage - LQA371
        if (confirmOption.toLowerCase().contains("no")){
            deleteInformation("no");
        }else{
            deleteInformation("yes");
        }

        removeAccordionItemYes.waitUntilNotVisible();
    }

    public void enterOtherPolicyData(Map<String, String> otherPolicyData){
        String policyPanel = "//span[contains(text(),'Policy "+indexOfPolicy+"')]/../../..";
        WebElementFacade policyNumber = findBy(policyPanel+txtPolicyNumber);
        WebElementFacade commencingDate = findBy(policyPanel+dtCommencingDate);
        WebElementFacade policyHolder = findBy(policyPanel+txtPolicyHolder);
        WebElementFacade insurer = findBy(policyPanel+txtInsurer);
        WebElementFacade replaceCoverYes = findBy(policyPanel+rdReplaceCoverYes);
        WebElementFacade replaceCoverNo = findBy(policyPanel+rdReplaceCoverNo);
        WebElementFacade deathCoverAmount = findBy(policyPanel+txtDeathCoverAmount);
        WebElementFacade ipCoverAmount = findBy(policyPanel+txtIpCoverAmount);
        WebElementFacade tpdCoverAmount = findBy(policyPanel+txtTpdCoverAmount);
        WebElementFacade waitingPeriod = findBy(policyPanel+lstWaitingPeriod);
        WebElementFacade benefitPeriod = findBy(policyPanel+lstBenefitPeriod);

        policyNumber.clear();
        policyNumber.sendKeys(otherPolicyData.get("policy number"));
        commencingDate.sendKeys(otherPolicyData.get("commencing date"));
        policyHolder.clear();
        policyHolder.sendKeys(otherPolicyData.get("policy holder name"));
        insurer.clear();
        insurer.sendKeys(otherPolicyData.get("insurer"));

        if (otherPolicyData.get("replacing cover").equalsIgnoreCase("yes")){
            replaceCoverYes.click();
        }else{
            replaceCoverNo.click();
        }

        //break the cover types combo
        if (otherPolicyData.get("cover type").contains(",")){
            //break it
            String[] partsCoverType = otherPolicyData.get("cover type").split(",");
            for (int i=0; i<partsCoverType.length; i++){
                findBy(policyPanel+"//label[text()='"+partsCoverType[i]+"']/../preceding-sibling::div/input").waitUntilVisible().click();
            }
        }else{
                findBy(policyPanel+"//label[text()='"+otherPolicyData.get("cover type")+"']/../preceding-sibling::div/input").waitUntilVisible().click();
        }


        if (otherPolicyData.get("death cover")!=null){
            deathCoverAmount.clear();
            deathCoverAmount.sendKeys(otherPolicyData.get("death cover"));
        }
        if (otherPolicyData.get("ip cover")!=null && otherPolicyData.get("cover type").contains("IP")){
            ipCoverAmount.clear();
            ipCoverAmount.sendKeys(otherPolicyData.get("ip cover"));
            waitingPeriod.selectByValue(otherPolicyData.get("waiting period"));
            benefitPeriod.selectByValue(otherPolicyData.get("benefit period"));
        }
        if (otherPolicyData.get("tpd cover")!=null){
            tpdCoverAmount.clear();
            tpdCoverAmount.sendKeys(otherPolicyData.get("tpd cover"));
        }

    }


    public void validateCoverAmountsAreDisplayed(String coverType){
        WebElementFacade policyPanel = findBy("//span[contains(text(),'Policy "+indexOfPolicy+"')]/../../..");
        policyPanel.then().findBy("//input[@aria-label='"+coverType+"']").shouldBeVisible();
    }

    public void validatePolicyIsCollapsed(String policyIndex){
        findBy("//span[contains(text(),'Policy "+policyIndex+"')]/../../../..//div[@class='stepped']").shouldBeVisible();
    }

    public void validateNewPolicyQuestionsAreAdded(String policyIndex){
        findBy("//span[contains(text(),'Policy "+policyIndex+"')]/../../../..//div[@class='active']").shouldBeVisible();
    }

    public void validateWaitingPeriodDisplay(boolean display){
        WebElementFacade policyPanel = findBy("//span[contains(text(),'Policy "+indexOfPolicy+"')]/../../..");
        if (display){
            policyPanel.then().findBy(By.xpath(lstWaitingPeriod)).shouldBeVisible();
        }else{
            policyPanel.then().findBy(By.xpath(lstWaitingPeriod)).shouldNotBeVisible();
        }

    }

    public void validateBenefitPeriodDisplay(boolean display){
        WebElementFacade policyPanel = findBy("//span[contains(text(),'Policy "+indexOfPolicy+"')]/../../..");
        if (display){
            policyPanel.then().findBy(By.xpath(lstBenefitPeriod)).shouldBeVisible();
        }else{
            policyPanel.then().findBy(By.xpath(lstBenefitPeriod)).shouldNotBeVisible();
        }
    }

    public void validateWaitingTimes(DataTable waitTimes){
        WebElementFacade policyPanel = findBy("//span[contains(text(),'Policy "+indexOfPolicy+"')]/../../..");
        List<String> displayedWaitTimesList = policyPanel.then().findBy(By.xpath(lstWaitingPeriod)).getSelectOptions();
        displayedWaitTimesList.remove(0);
        List<List<String>> dataFieldValues = waitTimes.raw();
        List<String> listOfWaits = new ArrayList<>();
        for (int i=0; i<dataFieldValues.size();i++){
            listOfWaits.add(dataFieldValues.get(i).get(0));
        }

        assertThat(listOfWaits).as("Validate Waiting Times list").isEqualTo(displayedWaitTimesList);
    }

    public void validateBenefitPeriods(DataTable benefitPeriod){
        WebElementFacade policyPanel = findBy("//span[contains(text(),'Policy "+indexOfPolicy+"')]/../../..");
        List<String> displayedBenefitsList = policyPanel.then().findBy(By.xpath(lstBenefitPeriod)).getSelectOptions();
        displayedBenefitsList.remove(0);
        List<List<String>> dataFieldValues = benefitPeriod.raw();
        List<String> listOfBenefits = new ArrayList<>();
        for (int i=0; i<dataFieldValues.size();i++){
            listOfBenefits.add(dataFieldValues.get(i).get(0));
        }

        assertThat(listOfBenefits).as("Validate Benefits list").isEqualTo(displayedBenefitsList);
    }

}

