package au.com.aia.lifeappautomatedtests.pages.Application.Lifestyle.ActivitiesAndPursuits;

import au.com.aia.lifeappautomatedtests.pages.BasePage;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by aadm234 on 9/08/2017.
 */
public class TrailBikePage extends BasePage {
    public TrailBikePage(WebDriver driver) {
        super(driver);
    }

    String bikeIndex;

    @FindBy(xpath = "//div[contains(concat(' ', @class, ' '), ' competeRadio ')]//label[.='Yes']/..//preceding-sibling::div/input")
    public WebElementFacade rdCompeteYes;

    @FindBy(xpath = "//div[contains(concat(' ', @class, ' '), ' competeRadio ')]//label[.='No']/..//preceding-sibling::div/input")
    public WebElementFacade rdCompeteNo;

    @FindBy(xpath = "//button[@aria-label='Add another motorbike']")
    public WebElementFacade btnAddAnotherMotorbike;

//    @FindBy(xpath = "//span[contains(text(),'Motorbike')]/../../..")
//    public List<WebElementFacade> allMotorbikePanel;

//    @FindBy(xpath = "//textarea[@aria-label='Specify the type of events participated in']")
//    public WebElementFacade txtEventTypes;

    @FindBy(xpath = "//div[contains(concat(' ', @class, ' '), ' trailbikingParticipationRadio ')]//label[.='Yes']/..//preceding-sibling::div/input")
    public WebElementFacade rdParticipationYes;

    @FindBy(xpath = "//div[contains(concat(' ', @class, ' '), ' trailbikingParticipationRadio ')]//label[.='No']/..//preceding-sibling::div/input")
    public WebElementFacade rdParticipationNo;

//    @FindBy(css = "div[class~='trailbikingParticipationDetails'] textarea[aria-label='Please provide details:']")
//    public WebElementFacade txtParticipationDetails;

    @FindBy(xpath = "//select[@aria-label='Frequency']")
    public WebElementFacade lstFrequency;

    @FindBy(xpath = "//textarea[@aria-label='Provide details on locations where you trail bike ride. i.e. Terrain types']")
    public WebElementFacade txtLocations;

    @FindBy(xpath = "//div[contains(concat(' ', @class, ' '), ' trailbikingInvolvedInAccident ')]//label[.='Yes']/..//preceding-sibling::div/input")
    public WebElementFacade rdAccidentYes;

    @FindBy(xpath = "//div[contains(concat(' ', @class, ' '), ' trailbikingInvolvedInAccident ')]//label[.='No']/..//preceding-sibling::div/input")
    public WebElementFacade rdAccidentNo;

    @FindBy(css = "div[class~='trailbikingAccidentDetails'] textarea[aria-label='Please provide details']")
    public WebElementFacade txtAccidentDetails;


    public void setMotorBikeIndex(String bikeIndex){
        this.bikeIndex = bikeIndex;
    }

    public void addMotorBike(){
        String pageID = Serenity.getCurrentSession().get("pageID").toString();
        String loc = "//button[contains(@id,'"+pageID+"') and @aria-label='Add another motorbike']";
        WebElementFacade btnAddBike = findBy(loc);
        scrollToElementView(By.xpath(loc));
        btnAddBike.waitUntilVisible().click();
    }

    public void validateMotorbikeIsCollapsed(String index){
        String pageID = Serenity.getCurrentSession().get("pageID").toString();
        String loc = "//button[contains(@id,'"+pageID+"') and @aria-label='Add another motorbike']";
        WebElementFacade btnAddBike = findBy(loc);
        scrollToElement(btnAddBike);
//        scrollToElement(btnAddAnotherMotorbike);
        String path = "//span[contains(text(),'Motorbike "+index+"')]/../../../..//div[@class='stepped']";
        assertThat(withTimeoutOf(15, TimeUnit.SECONDS).find(path).isVisible()).as("Validate motorbike accordion is collapsed").isTrue();
    }

    public void enterMotorBikeData(Map<String, String> vehicleData){
        String pageID = Serenity.getCurrentSession().get("pageID").toString();
        String motorbikePanel = "//span[contains(text(),'Motorbike "+bikeIndex+"')]/../../..";
        String txtMakeModel = "//input[contains(@id,'"+pageID+"') and @aria-label='Make/ Model']";
        String txtEngineSize = "//input[contains(@id,'"+pageID+"') and @aria-label='Engine size']";
        String txtTopSpeed = "//input[contains(@id,'"+pageID+"') and @aria-label='Top speed (Km/hour)']";

        WebElementFacade makeModel = findBy(motorbikePanel+txtMakeModel);
        WebElementFacade engineSize = findBy(motorbikePanel+txtEngineSize);
        WebElementFacade topSpeed = findBy(motorbikePanel+txtTopSpeed);

        scrollToElementView(By.xpath(motorbikePanel+txtMakeModel));
        makeModel.sendKeys(vehicleData.get("make model"));
        engineSize.sendKeys(vehicleData.get("engine size"));
        topSpeed.sendKeys(vehicleData.get("top speed"));
    }

    public void enterTrailBikeData(Map<String, String> data){
        lstFrequency.waitUntilVisible().selectByValue(data.get("frequency"));
        if (data.get("locations").contains(",")){
            String[] partsEventType = data.get("locations").split(",");
            for (int i=0; i<partsEventType.length; i++){
                txtLocations.sendKeys(Keys.CONTROL, Keys.HOME);
                txtLocations.sendKeys(partsEventType[i]);
                txtLocations.sendKeys(Keys.ENTER);
            }
        }else{
            txtLocations.type(data.get("locations"));
        }
        if (data.get("accident").equalsIgnoreCase("yes")){
            rdAccidentYes.waitUntilVisible().click();
            txtAccidentDetails.sendKeys(data.get("accident details"));
        }else{
            rdAccidentNo.waitUntilVisible().click();
        }
    }

    public void validateNewMotorbikeQuestionsAreAdded(String index){
        WebElementFacade newMotorbikeEntry = findBy("//span[contains(text(),'Motorbike "+index+"')]/../../../..//div[@class='active']");
        assertThat(newMotorbikeEntry.isVisible()).as("Validate new motorbike entry gets added").isTrue();
    }

    public void removeMotorbikes(String confirmOption){
        String pageID = Serenity.getCurrentSession().get("pageID").toString();
        String loc = "//span[contains(text(),'Motorbike') and contains(@data-guide-id,'"+pageID+"')]";


        System.out.println("All Motorbike Added: "+findAll(loc).size());
//        System.out.println("All Motorbike Added: "+allMotorbikePanel.size());
//        Serenity.setSessionVariable("totalmotorbikes").to(allMotorbikePanel.size());
        Serenity.setSessionVariable("totalmotorbikes").to(findAll(loc).size());

//        String motorbikePanel = "//span[contains(text(),'Motorbike "+bikeIndex+"')]/../../..";
        String motorbikePanel = "//span[contains(text(),'Motorbike "+bikeIndex+"') and contains(@data-guide-id,'motorcycle-racing')]/../../..";
        String path = motorbikePanel+"//button[text()='Remove']";
        scrollToElementView(By.xpath(path));
        WebElementFacade btnRemoveMotorbike = findBy(path);
        btnRemoveMotorbike.waitUntilVisible().click();
        if (confirmOption.toLowerCase().contains("no")){
            deleteInformation("no");
        }else{
            deleteInformation("yes");
        }

        removeAccordionItemYes.waitUntilNotVisible();

    }

    public void validateMotorbikeIsNotRemoved(){
        String pageID = Serenity.getCurrentSession().get("pageID").toString();
        String loc = "//span[contains(text(),'Motorbike') and contains(@data-guide-id,'"+pageID+"')]";

        System.out.println("Motorbike after deletion: "+findAll(loc).size());
//        System.out.println("Motorbike after deletion: "+allMotorbikePanel.size());
        assertThat(Integer.parseInt(Serenity.getCurrentSession().get("totalmotorbikes").toString())-findAll(loc).size()).as("Validate motorbike is not deleted").isEqualTo(0);
    }

    public void validateMotorbikeIsRemoved(){
        String pageID = Serenity.getCurrentSession().get("pageID").toString();
        String loc = "//span[contains(text(),'Motorbike') and contains(@data-guide-id,'"+pageID+"')]";

        System.out.println("Motorbike after deletion: "+findAll(loc).size());
//        System.out.println("Motorbike after deletion: "+allMotorbikePanel.size());
        assertThat(Integer.parseInt(Serenity.getCurrentSession().get("totalmotorbikes").toString())-findAll(loc).size()).as("Validate motorbike is deleted successfully").isEqualTo(1);
    }

    public void validateLastRemainingMotorbikeCannotBeRemoved(){
        String pageID = Serenity.getCurrentSession().get("pageID").toString();
        String loc = "//button[contains(@id,'"+pageID+"') and @aria-label='Add another motorbike']";
        WebElementFacade btnAddBike = findBy(loc);
        scrollToElementView(By.xpath(loc));

//        scrollToElement(btnAddAnotherMotorbike);
        String motorbikePanel = "//span[contains(text(),'Motorbike "+bikeIndex+"')]/../../..";
        String path = motorbikePanel+"//button[text()='Remove']";
        findBy(path).shouldNotBeVisible();
    }

    public ExpectedCondition waitIsActionable(WebElementFacade elementFacade) {
        return driver -> (elementFacade.isPresent() && elementFacade.isVisible());
    }

    @SuppressWarnings("Duplicates")
    public void enterEventData(Map<String, String> eventData){
        String pageID = Serenity.getCurrentSession().get("pageID").toString();
        String loc = "//textarea[contains(@aria-label,'type of events participated in') and contains(@id,'"+pageID+"')]";
        WebElementFacade txtEventTypes =  findBy(loc);

        if (eventData.get("event type").contains(",")){
            String[] partsEventType = eventData.get("event type").split(",");
            for (int i=0; i<partsEventType.length; i++){
                txtEventTypes.sendKeys(Keys.CONTROL, Keys.HOME);
                txtEventTypes.sendKeys(partsEventType[i]);
                txtEventTypes.sendKeys(Keys.ENTER);
            }
        }else{
            txtEventTypes.type(eventData.get("event type"));
        }

        String classRadioText;
        String classDetailsText;
        if (pageID.toLowerCase().contains("trail")){
            classRadioText = "trailbikingParticipationRadio";
            classDetailsText = "trailbikingParticipationDetails";
        }else{
            classRadioText = "motorcycleRacingParticipationRadio";
            classDetailsText = "motorcycleRacingParticipationDetails";
        }

        if (eventData.get("event participation").equalsIgnoreCase("yes")){
            String locParticipateYes = "//div[contains(concat(' ', @class, ' '), ' "+classRadioText+" ')]//label[.='Yes']/..//preceding-sibling::div/input";
            scrollToElementView(By.xpath(locParticipateYes));
            WebElementFacade rdParticipationYes = findBy(locParticipateYes);

            rdParticipationYes.click();
            scrollToElement(btnNext);

            String locParticipateDetailsYes = "div[class~='"+classDetailsText+"'] textarea[aria-label='Please provide details:']";
            scrollToElementView(By.cssSelector(locParticipateDetailsYes));
            WebElementFacade txtParticipationDetails = findBy(locParticipateDetailsYes);

            txtParticipationDetails.waitForCondition().until(waitIsActionable(txtParticipationDetails));
            scrollToElement(txtParticipationDetails);

            txtParticipationDetails.sendKeys(eventData.get("participation details"));
        }else{
//            scrollToElement(rdParticipationNo);
            String locParticipateYes = "//div[contains(concat(' ', @class, ' '), ' "+classRadioText+" ')]//label[.='No']/..//preceding-sibling::div/input";
            scrollToElementView(By.xpath(locParticipateYes));
            WebElementFacade rdParticipationNo = findBy(locParticipateYes);
            rdParticipationNo.click();
//            rdParticipationNo.click();
        }
    }

}

