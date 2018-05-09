package au.com.aia.lifeappautomatedtests.pages.Application.PersonalDetails;

import au.com.aia.lifeappautomatedtests.pages.BasePage;
import cucumber.api.DataTable;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by aadm234 on 9/08/2017.
 */
public class OtherCoverSpecialTermsPage extends BasePage {
    public OtherCoverSpecialTermsPage(WebDriver driver) {
        super(driver);
    }

    String index;
    String lstTypeOfCover = "//select[@aria-label='Type of cover']";
    String lstTypeOfDecision = "//select[@aria-label='Type of decision']";
    String txtDecisionYear = "//input[@aria-label='Year of decision']";
    String txtReasonForDecision = "//textarea[@aria-label='Reason for decision']";

    @FindBy(xpath = "//label[text()='Have you ever been declined, deferred or accepted on special terms for life, disability or trauma insurance?']")
    public WebElementFacade lblMainQ;

    @FindBy(xpath = "//a[text()='Member portal']")
    public WebElementFacade lblHeader;

//    @FindBy(xpath = "//div[contains(concat(' ', @class, ' '), ' declined ') and contains(@style,'yes.PNG')]")
    @FindBy(css = "div[class~='declined'] input[aria-label='YES']")
    public WebElementFacade rdDeclinedYes;

//    @FindBy(xpath = "//div[contains(concat(' ', @class, ' '), ' declined ') and contains(@style,'no.PNG')]")
    @FindBy(css = "div[class~='declined'] input[aria-label='NO']")
    public WebElementFacade rdDeclinedNo;

    @FindBy(xpath = "//button[@aria-label='Add another?']")
    public WebElementFacade btnAddAnother;

    @FindBy(xpath = "//span[contains(text(),'Special Term - Cover Details')]/../../..")
    public List<WebElementFacade> allSpecialTermsPanel;

    public void setSpecialTermsIndex(String index){
        this.index = index;
    }

    public void validateTermIsRemoved(){
        System.out.println("Terms after deletion: "+allSpecialTermsPanel.size());
        assertThat(Integer.parseInt(Serenity.getCurrentSession().get("totalterms").toString())-allSpecialTermsPanel.size()).as("Validate term is deleted successfully").isEqualTo(1);
    }

    public void validateLastRemainingTermCannotBeRemoved(){
        scrollToElement(lblMainQ);
        String specialTermsPanel = "//span[contains(text(),'Special Term - Cover Details "+index+"')]/../../..";
        String path = specialTermsPanel+"//button[text()='Remove']";
        findBy(path).shouldNotBeVisible();
    }

    public void validateTermIsNotRemoved(){
        System.out.println("Terms after deletion: "+allSpecialTermsPanel.size());
        assertThat(Integer.parseInt(Serenity.getCurrentSession().get("totalterms").toString())-allSpecialTermsPanel.size()).as("Validate term is not deleted").isEqualTo(0);
    }

    public void removeSpecialTerms(String confirmOption){
        //Get the total count of terms added
        System.out.println("All Terms Added: "+allSpecialTermsPanel.size());
        Serenity.setSessionVariable("totalterms").to(allSpecialTermsPanel.size());

        String specialTermsPanel = "//span[contains(text(),'Special Term - Cover Details "+index+"')]/../../..";
        String path = specialTermsPanel+"//button[text()='Remove']";
        scrollToElementView(By.xpath(path));
        WebElementFacade btnRemoveTerms = findBy(path);
        btnRemoveTerms.waitUntilVisible().click();
        //Handle the confirmation mesage - LQA371
        if (confirmOption.toLowerCase().contains("no")){
            deleteInformation("no");
        }else{
            deleteInformation("yes");
        }

        removeAccordionItemYes.waitUntilNotVisible();

    }

    public void validateSpecialTermIsCollapsed(String termIndex){
        scrollToElement(lblMainQ);
        scrollToElement(lblHeader);
        String path = "//span[contains(text(),'Special Term - Cover Details "+termIndex+"')]/../../../..//div[@class='stepped']";
//        scrollToElementView(By.xpath(path));
        withTimeoutOf(15, TimeUnit.SECONDS).find(path).shouldBeVisible();
//        findBy(path).waitUntilPresent();
//        findBy("//span[contains(text(),'Special Term - Cover Details "+termIndex+"')]/../../../..//div[@class='stepped']").shouldBeVisible();
//        findBy(path).shouldBeVisible();
    }

    public void validateNewSpecialTermQuestionsAreAdded(String termIndex){
        findBy("//span[contains(text(),'Special Term - Cover Details "+termIndex+"')]/../../../..//div[@class='active']").shouldBeVisible();
    }

    public void enterSpecialTermsData(Map<String, String> termsData){
        String specialTermsPanel = "//span[contains(text(),'Special Term - Cover Details "+index+"')]/../../..";
        WebElementFacade coverType = findBy(specialTermsPanel+lstTypeOfCover);
        WebElementFacade decisionType = findBy(specialTermsPanel+lstTypeOfDecision);
        WebElementFacade decisionYear = findBy(specialTermsPanel+txtDecisionYear);
        WebElementFacade reasonDecision = findBy(specialTermsPanel+txtReasonForDecision);


        coverType.waitUntilVisible().selectByValue(termsData.get("cover type"));
        decisionType.waitUntilVisible().selectByValue(termsData.get("decision type"));
        decisionYear.clear();
        decisionYear.sendKeys(termsData.get("decision year"));
        reasonDecision.sendKeys(termsData.get("reason for decision"));

    }

    @SuppressWarnings("Duplicates")
    public void validateCoverTypes(DataTable coverTypes){
        WebElementFacade specialTermsPanel = findBy("//span[contains(text(),'Special Term - Cover Details "+index+"')]/../../..");
        List<String> displayedCoverTypes = specialTermsPanel.then().findBy(By.xpath(lstTypeOfCover)).getSelectOptions();
        displayedCoverTypes.remove(0);

        List<List<String>> dataFieldValues = coverTypes.raw();

        List<String> listOfCoverTypes = new ArrayList<>();
        for (int i=0; i<dataFieldValues.size();i++){
            listOfCoverTypes.add(dataFieldValues.get(i).get(0));
        }

        assertThat(listOfCoverTypes).as("Validate Cover Types list").isEqualTo(displayedCoverTypes);
    }

    public void validateDecisionTypes(DataTable decisionTypes){
        WebElementFacade specialTermsPanel = findBy("//span[contains(text(),'Special Term - Cover Details "+index+"')]/../../..");
        List<String> displayedDecisionTypes = specialTermsPanel.then().findBy(By.xpath(lstTypeOfDecision)).getSelectOptions();
        displayedDecisionTypes.remove(0);

        List<List<String>> dataFieldValues = decisionTypes.raw();

        List<String> listOfDecisionTypes = new ArrayList<>();
        for (int i=0; i<dataFieldValues.size();i++){
            listOfDecisionTypes.add(dataFieldValues.get(i).get(0));
        }

        assertThat(listOfDecisionTypes).as("Validate Decision Types list").isEqualTo(displayedDecisionTypes);
    }

}

