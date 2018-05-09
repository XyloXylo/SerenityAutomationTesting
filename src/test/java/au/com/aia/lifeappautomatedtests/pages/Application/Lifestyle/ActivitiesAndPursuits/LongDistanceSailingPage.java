package au.com.aia.lifeappautomatedtests.pages.Application.Lifestyle.ActivitiesAndPursuits;

import au.com.aia.lifeappautomatedtests.pages.BasePage;
import au.com.aia.lifeappautomatedtests.utility.UnderWriting;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by aadm234 on 9/08/2017.
 */
public class LongDistanceSailingPage extends BasePage {
    public LongDistanceSailingPage(WebDriver driver) {
        super(driver);
    }

    String voyageIndex;
    String txtNumberOfCrew = "//input[@aria-label='Typical number of crew on Voyages']";
    String txtVoyageDuration = "//input[@aria-label='Typical duration of Voyages']";
    String txtVoyageLocation = "//textarea[@aria-label='Please provide details of locations.']";

    @FindBy(xpath = "//button[@aria-label='Add another voyage']")
    public WebElementFacade btnAddAnotherVoyage;

    @FindBy(xpath = "//span[contains(text(),'Voyage')]/../../..")
    public List<WebElementFacade> allVoyagePanel;

    @FindBy(xpath = "//textarea[@aria-label='Name of race/ race series']")
    public WebElementFacade txtNameOfRace;

    public void setVoyageIndex(String voyageIndex){
        this.voyageIndex = voyageIndex;
    }

    public void validateVoyageIsCollapsed(String index){
        scrollToElement(btnAddAnotherVoyage);
        String path = "//span[contains(text(),'Voyage "+index+"')]/../../../..//div[@class='stepped']";
        assertThat(withTimeoutOf(15, TimeUnit.SECONDS).find(path).isVisible()).as("Validate voyage accordion is collapsed").isTrue();
    }

    public void validateNewVoyageQuestionsAreAdded(String index){
        WebElementFacade newVoyageEntry = findBy("//span[contains(text(),'Voyage "+index+"')]/../../../..//div[@class='active']");
        assertThat(newVoyageEntry.isVisible()).as("Validate new voyage entry gets added").isTrue();
    }

    public void selectTypeOfSailing(String type){
        UnderWriting underWriting = new UnderWriting();
        WebElementFacade webElementFacade = findBy("//div[contains(@id,'longDistanceSailing')]//input[@aria-label='"+type+"']");
        webElementFacade.waitUntilVisible().click();
        if (type.toLowerCase().contains("pleasure (trans-ocean)") || type.toLowerCase().contains("racing (offshore)") || type.toLowerCase().contains("racing (trans-ocean)")){
            underWriting.setRequiresUnderWriting(true);
        }
    }

    public void validateVoyageIsRemoved(){
        List<WebElementFacade> elementFacade = findAll("//span[contains(text(),'Voyage')]/../../..");
        System.out.println("Voyage after deletion: "+elementFacade.size());
        assertThat(Integer.parseInt(Serenity.getCurrentSession().get("totalvoyages").toString())-elementFacade.size()).as("Validate voyage is deleted successfully").isEqualTo(1);
    }

    public void validateVoyageIsNotRemoved(){
        List<WebElementFacade> elementFacade = findAll("//span[contains(text(),'Voyage')]/../../..");
        System.out.println("Voyage after deletion: "+elementFacade.size());
        assertThat(Integer.parseInt(Serenity.getCurrentSession().get("totalvoyages").toString())-elementFacade.size()).as("Validate voyage is not deleted").isEqualTo(0);
    }

    public void validateLastRemainingVoyageCannotBeRemoved(){
        scrollToElement(btnAddAnotherVoyage);
        String voyagePanel = "//span[contains(text(),'Voyage "+voyageIndex+"')]/../../..";
        String path = voyagePanel+"//button[text()='Remove']";
        findBy(path).shouldNotBeVisible();
    }

    public void removeVoyages(String confirmOption){
        System.out.println("All Voyages Added: "+allVoyagePanel.size());
        Serenity.setSessionVariable("totalvoyages").to(allVoyagePanel.size());

        String voyagePanel = "//span[contains(text(),'Voyage "+voyageIndex+"')]/../../..";
        String path = voyagePanel+"//button[text()='Remove']";
        scrollToElementView(By.xpath(path));
        WebElementFacade btnRemoveVoyage = findBy(path);
        btnRemoveVoyage.waitUntilVisible().click();
        if (confirmOption.toLowerCase().contains("no")){
            deleteInformation("no");
        }else{
            deleteInformation("yes");
        }

        removeAccordionItemYes.waitUntilNotVisible();

    }

    public void enterLongDistanceSailingData(Map<String, String> data){

        String voyagePanel = "//span[contains(text(),'Voyage "+voyageIndex+"')]/../../..";
        WebElementFacade numberOfCrew = findBy(voyagePanel+txtNumberOfCrew);
        WebElementFacade voyageDuration = findBy(voyagePanel+txtVoyageDuration);
        WebElementFacade voyageLocation = findBy(voyagePanel+txtVoyageLocation);

        numberOfCrew.waitUntilVisible().sendKeys(data.get("crew number"));
        voyageDuration.waitUntilVisible().sendKeys(data.get("voyage duration"));
        voyageLocation.sendKeys(data.get("voyage location details"));

        if (data.get("race series")!=null){
            txtNameOfRace.sendKeys(data.get("race series"));
        }

    }

}

