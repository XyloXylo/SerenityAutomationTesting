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
public class CarRacingPage extends BasePage {
    public CarRacingPage(WebDriver driver) {
        super(driver);
    }

    String licenseIndex;
    String vehicleIndex;
    String txtLicenseType = "//input[@aria-label='License type']";
    String txtLicenseClassification = "//input[@aria-label='Classification']";
    String txtMakeModel = "//input[@aria-label='Make/ Model']";
    String txtEngineSize = "//input[@aria-label='Engine size']";
    String txtTopSpeed = "//input[@aria-label='Top speed (Km/hour)']";



    @FindBy(xpath = "//label[text()='Vintage/ Veterans/ Historic only']/../..//input")
    public WebElementFacade rdVintageCarRacing;

    @FindBy(xpath = "//label[text()='Other (i.e. Sports cars/ Touring cars/ Drag racing/ Karting/ Speedway racing)']/../..//input")
    public WebElementFacade rdOtherCarRacing;

    public void setLicenseIndex(String licenseIndex){
        this.licenseIndex = licenseIndex;
    }

    public void setVehicleIndex(String vehicleIndex){
        this.vehicleIndex = vehicleIndex;
    }

    public ExpectedCondition waitIsActionable(WebElementFacade elementFacade) {
        return driver -> (elementFacade.isPresent() && elementFacade.isVisible());
    }

    @SuppressWarnings("Duplicates")
    public void enterEventData(Map<String, String> eventData){
        WebElementFacade txtTypeOfEvents;
        WebElementFacade txtHowManyEvents;
        WebElementFacade rdParticipationYes = null;
        WebElementFacade rdParticipationNo = null;
        WebElementFacade txtParticipationDetails = null;
        WebElementFacade rdInvolvedInAccidentYes = null;
        WebElementFacade txtAccidentDetails = null;
        WebElementFacade rdInvolvedInAccidentNo = null;
        String pageID = Serenity.getCurrentSession().get("pageID").toString();
        if (pageID.contains("car-racing") || pageID.contains("motorcycle-racing")){
            txtTypeOfEvents = findBy("//div[contains(@id,'"+pageID+"')]//textarea[@aria-label='Specify type of events participated in (e.g speedway).']");
        }else{
            txtTypeOfEvents = findBy("//div[contains(@id,'"+pageID+"')]//textarea[@aria-label='Specify type of events participated in.']");
        }

        if (eventData.get("event type").contains(",")){
            String[] partsEventType = eventData.get("event type").split(",");
            for (int i=0; i<partsEventType.length; i++){
                txtTypeOfEvents.sendKeys(Keys.CONTROL, Keys.HOME);
                txtTypeOfEvents.sendKeys(partsEventType[i]);
                txtTypeOfEvents.sendKeys(Keys.ENTER);
            }
        }else{
            txtTypeOfEvents.type(eventData.get("event type"));
        }

        if (pageID.contains("car-racing")){
            txtHowManyEvents = findBy("//div[contains(@id,'"+pageID+"')]//input[@aria-label='numberOfEvents']");
            txtHowManyEvents.sendKeys(eventData.get("event count"));
        }else if (pageID.contains("rallying")){
            txtHowManyEvents = findBy("//div[contains(@id,'"+pageID+"')]//input[@aria-label='rallyingNumberOfEvents']");
            txtHowManyEvents.sendKeys(eventData.get("event count"));
        }

//        txtHowManyEvents.sendKeys(eventData.get("event count"));
        if (eventData.get("event participation").equalsIgnoreCase("yes")){
            if (pageID.contains("car-racing")){
                rdParticipationYes = findBy("div[id*='"+pageID+"'] div[class~='participationRadio'] input[aria-label='Yes']");
            }else if (pageID.contains("rallying")){
                rdParticipationYes = findBy("div[id*='"+pageID+"'] div[class~='rallyingParticipationRadio'] input[aria-label='Yes']");
            }else if (pageID.contains("motorcycle-racing")){
                rdParticipationYes = findBy("//div[contains(concat(' ', @class, ' '), ' motorcycleRacingParticipationRadio ')]//label[.='Yes']/..//preceding-sibling::div/input");
            }

            rdParticipationYes.click();
            scrollToElement(btnNext);

            if (pageID.contains("car-racing")){
                txtParticipationDetails = findBy("div[id*='"+pageID+"'] div[class~='participationDetails'] textarea[aria-label='Please provide details']");
            }else if (pageID.contains("rallying")){
                txtParticipationDetails = findBy("div[id*='"+pageID+"'] div[class~='rallyingParticipationDetails'] textarea[aria-label='Please provide details']");
            }else if (pageID.contains("motorcycle-racing")){
                txtParticipationDetails = findBy("div[id*='"+pageID+"'] div[class~='motorcycleRacingParticipationDetails'] textarea[aria-label='Please provide details:']");
            }

            txtParticipationDetails.waitForCondition().until(waitIsActionable(txtParticipationDetails));
            scrollToElement(txtParticipationDetails);

            txtParticipationDetails.sendKeys(eventData.get("participation details"));
        }else{
            if (pageID.contains("car-racing")){
                rdParticipationNo = findBy("div[id*='"+pageID+"'] div[class~='participationRadio'] input[aria-label='No']");
            }else if (pageID.contains("rallying")){
                rdParticipationNo = findBy("div[id*='"+pageID+"'] div[class~='rallyingParticipationRadio'] input[aria-label='No']");
            }else if (pageID.contains("motorcycle-racing")){
                rdParticipationNo = findBy("//div[contains(concat(' ', @class, ' '), ' motorcycleRacingParticipationRadio ')]//label[.='No']/..//preceding-sibling::div/input");
            }

            scrollToElement(rdParticipationNo);
            rdParticipationNo.click();
        }

        if (eventData.get("any accident").equalsIgnoreCase("yes")){
            if (pageID.contains("car-racing")){
                rdInvolvedInAccidentYes = findBy("div[id*='"+pageID+"'] div[class~='involvedInAccident'] input[aria-label='Yes']");
            }else if (pageID.contains("rallying")){
                rdInvolvedInAccidentYes = findBy("div[id*='"+pageID+"'] div[class~='rallyingInvolvedInAccident'] input[aria-label='Yes']");
            }else if (pageID.contains("motorcycle-racing")){
                rdInvolvedInAccidentYes = findBy("//div[contains(concat(' ', @class, ' '), ' motorcycleRacingInvolvedInAccident ')]//label[.='Yes']/..//preceding-sibling::div/input");
            }

            scrollToElement(rdInvolvedInAccidentYes);
            rdInvolvedInAccidentYes.click();
            if (pageID.contains("car-racing")){
                txtAccidentDetails = findBy("div[id*='"+pageID+"'] div[class~='accidentDetails'] textarea[aria-label='Please provide details']");
            }else if (pageID.contains("rallying")){
                txtAccidentDetails = findBy("div[id*='"+pageID+"'] div[class~='rallyingAccidentDetails'] textarea[aria-label='Please provide details']");
            }else if (pageID.contains("motorcycle-racing")){
                txtAccidentDetails = findBy("div[id*='"+pageID+"'] div[class~='motorcycleRacingAccidentDetails'] textarea[aria-label='Please provide details']");
            }

            txtAccidentDetails.waitForCondition().until(waitIsActionable(txtAccidentDetails));
            scrollToElement(txtAccidentDetails);
            txtAccidentDetails.sendKeys(eventData.get("accident details"));
        }else{
            if (pageID.contains("car-racing")){
                rdInvolvedInAccidentNo = findBy("div[id*='"+pageID+"'] div[class~='involvedInAccident'] input[aria-label='No']");
            }else if (pageID.contains("rallying")){
                rdInvolvedInAccidentNo = findBy("div[id*='"+pageID+"'] div[class~='rallyingInvolvedInAccident'] input[aria-label='No']");
            }else if (pageID.contains("motorcycle-racing")){
                rdInvolvedInAccidentNo = findBy("//div[contains(concat(' ', @class, ' '), ' motorcycleRacingInvolvedInAccident ')]//label[.='No']/..//preceding-sibling::div/input");
            }

            scrollToElement(rdInvolvedInAccidentNo);
            rdInvolvedInAccidentNo.click();
        }
    }

    public WebElementFacade getBtnAddAnotherLicense(){
        String pageID = Serenity.getCurrentSession().get("pageID").toString();
        System.out.println("PageID:"+pageID);
        String loc = "//div[contains(@id,'"+pageID+"')]//button[@aria-label='Add another license']";
        WebElementFacade btnAddAnotherLicense = findBy(loc);
        scrollToElementView(By.xpath(loc));
//        btnAddAnotherLicense.waitUntilVisible();
        return btnAddAnotherLicense;
    }

    public WebElementFacade getBtnAddAnotherVehicle(){
        String pageID = Serenity.getCurrentSession().get("pageID").toString();
        System.out.println("PageID:"+pageID);
        String loc = "//div[contains(@id,'"+pageID+"')]//button[@aria-label='Add another vehicle']";
//        WebElementFacade btnAddAnotherVehicle = findBy("//div[contains(@id,'"+pageID+"')]//button[@aria-label='+ Add another']");
        WebElementFacade btnAddAnotherVehicle = findBy(loc);
        scrollToElementView(By.xpath(loc));
//        btnAddAnotherVehicle.waitUntilVisible();
        return btnAddAnotherVehicle;
    }

    public void enterLicenseData(Map<String, String> licenseData){
        String pageID = Serenity.getCurrentSession().get("pageID").toString();

        String licensePanel = "//div[contains(@id,'"+pageID+"')]//span[contains(text(),'License "+licenseIndex+"')]/../../..";
        WebElementFacade licenseType = findBy(licensePanel+txtLicenseType);
        WebElementFacade licenseClassification = findBy(licensePanel+txtLicenseClassification);

        licenseType.sendKeys(licenseData.get("license type"));
        licenseClassification.sendKeys(licenseData.get("classification"));
    }

    public void enterVehicleData(Map<String, String> vehicleData){
        String pageID = Serenity.getCurrentSession().get("pageID").toString();

        String vehiclePanel = "//div[contains(@id,'"+pageID+"')]//span[contains(text(),'Vehicle "+vehicleIndex+"')]/../../..";
        WebElementFacade makeModel = findBy(vehiclePanel+txtMakeModel);
        WebElementFacade engineSize = findBy(vehiclePanel+txtEngineSize);
        WebElementFacade topSpeed = findBy(vehiclePanel+txtTopSpeed);

        makeModel.sendKeys(vehicleData.get("make model"));
        engineSize.sendKeys(vehicleData.get("engine size"));
        topSpeed.sendKeys(vehicleData.get("top speed"));
    }

    public void validateLicenseIsNotRemoved(){
        String pageID = Serenity.getCurrentSession().get("pageID").toString();
        List<WebElementFacade> elementFacade = findAll("//div[contains(@id,'"+pageID+"')]//span[contains(text(),'License')]/../../..");
        System.out.println("License after deletion: "+elementFacade.size());
        assertThat(Integer.parseInt(Serenity.getCurrentSession().get("totallicense").toString())-elementFacade.size()).as("Validate license is not deleted").isEqualTo(0);
    }

    public void validateVehicleIsNotRemoved(){
        String pageID = Serenity.getCurrentSession().get("pageID").toString();
        List<WebElementFacade> elementFacade = findAll("//div[contains(@id,'"+pageID+"')]//span[contains(text(),'Vehicle')]/../../..");
        System.out.println("Vehicle after deletion: "+elementFacade.size());
        assertThat(Integer.parseInt(Serenity.getCurrentSession().get("totalvehicles").toString())-elementFacade.size()).as("Validate vehicle is not deleted").isEqualTo(0);
    }

    public void validateLicenseIsRemoved(){
        String pageID = Serenity.getCurrentSession().get("pageID").toString();
        List<WebElementFacade> elementFacade = findAll("//div[contains(@id,'"+pageID+"')]//span[contains(text(),'License')]/../../..");
        System.out.println("License after deletion: "+elementFacade.size());
        assertThat(Integer.parseInt(Serenity.getCurrentSession().get("totallicense").toString())-elementFacade.size()).as("Validate license is deleted successfully").isEqualTo(1);
    }

    public void validateVehicleIsRemoved(){
        String pageID = Serenity.getCurrentSession().get("pageID").toString();
        List<WebElementFacade> elementFacade = findAll("//div[contains(@id,'"+pageID+"')]//span[contains(text(),'Vehicle')]/../../..");
        System.out.println("Vehicle after deletion: "+elementFacade.size());
        assertThat(Integer.parseInt(Serenity.getCurrentSession().get("totalvehicles").toString())-elementFacade.size()).as("Validate vehicle is deleted successfully").isEqualTo(1);
    }

    public void removeLicenses(String confirmOption){
        String pageID = Serenity.getCurrentSession().get("pageID").toString();
        List<WebElementFacade> elementFacade = findAll("//div[contains(@id,'"+pageID+"')]//span[contains(text(),'License')]/../../..");
        System.out.println("All License Added: "+elementFacade.size());
        Serenity.setSessionVariable("totallicense").to(elementFacade.size());

        String licensePanel = "//div[contains(@id,'"+pageID+"')]//span[contains(text(),'License "+licenseIndex+"')]/../../..";
        String path = licensePanel+"//button[text()='Remove']";
        scrollToElementView(By.xpath(path));
        WebElementFacade btnRemoveLicense = findBy(path);
        btnRemoveLicense.waitUntilVisible().click();
        if (confirmOption.toLowerCase().contains("no")){
            deleteInformation("no");
        }else{
            deleteInformation("yes");
        }

        removeAccordionItemYes.waitUntilNotVisible();

    }

    public void removeVehicles(String confirmOption){
        String pageID = Serenity.getCurrentSession().get("pageID").toString();
        List<WebElementFacade> elementFacade = findAll("//div[contains(@id,'"+pageID+"')]//span[contains(text(),'Vehicle')]/../../..");
        System.out.println("All Vehicle Added: "+elementFacade.size());
        Serenity.setSessionVariable("totalvehicles").to(elementFacade.size());

        String vehiclePanel = "//div[contains(@id,'"+pageID+"')]//span[contains(text(),'Vehicle "+vehicleIndex+"')]/../../..";
        String path = vehiclePanel+"//button[text()='Remove']";
        scrollToElementView(By.xpath(path));
        WebElementFacade btnRemoveVehicle = findBy(path);
        btnRemoveVehicle.waitUntilVisible().click();
        if (confirmOption.toLowerCase().contains("no")){
            deleteInformation("no");
        }else{
            deleteInformation("yes");
        }

        removeAccordionItemYes.waitUntilNotVisible();

    }

    public void validateLastRemainingLicenseCannotBeRemoved(){
        String pageID = Serenity.getCurrentSession().get("pageID").toString();
        if (pageID.contains("car-racing")){
            scrollToElement(rdVintageCarRacing);
        }

        String licensePanel = "//div[contains(@id,'"+pageID+"')]//span[contains(text(),'License "+licenseIndex+"')]/../../..";
        String path = licensePanel+"//button[text()='Remove']";
        scrollToElementView(By.xpath(path));
        findBy(path).shouldNotBeVisible();
    }

    public void validateLastRemainingVehicleCannotBeRemoved(){
        String pageID = Serenity.getCurrentSession().get("pageID").toString();
        WebElementFacade btnAddAnotherLicense = findBy("//div[contains(@id,'"+pageID+"')]//button[@aria-label='Add another license']");
        scrollToElement(btnAddAnotherLicense);
        String vehiclePanel = "//div[contains(@id,'"+pageID+"')]//span[contains(text(),'Vehicle "+vehicleIndex+"')]/../../..";
        String path = vehiclePanel+"//button[text()='Remove']";
        findBy(path).shouldNotBeVisible();
    }

    public void validateLicenseIsCollapsed(String index){
        String pageID = Serenity.getCurrentSession().get("pageID").toString();
        if (pageID.contains("car-racing")){
            scrollToElement(rdOtherCarRacing);
        }

        String path = "//div[contains(@id,'"+pageID+"')]//span[contains(text(),'License "+index+"')]/../../../..//div[@class='stepped']";
        assertThat(withTimeoutOf(15, TimeUnit.SECONDS).find(path).isVisible()).as("Validate license accordion is collapsed").isTrue();
    }

    public void validateVehicleIsCollapsed(String index){
        String pageID = Serenity.getCurrentSession().get("pageID").toString();
        WebElementFacade btnAddAnotherLicense = findBy("//div[contains(@id,'"+pageID+"')]//button[@aria-label='Add another license']");
        scrollToElement(btnAddAnotherLicense);
        String path = "//div[contains(@id,'"+pageID+"')]//span[contains(text(),'Vehicle "+index+"')]/../../../..//div[@class='stepped']";
        assertThat(withTimeoutOf(15, TimeUnit.SECONDS).find(path).isVisible()).as("Validate vehicle accordion is collapsed").isTrue();
    }

    public void validateNewLicenseQuestionsAreAdded(String index){
        String pageID = Serenity.getCurrentSession().get("pageID").toString();
        WebElementFacade newLicenseEntry = findBy("//div[contains(@id,'"+pageID+"')]//span[contains(text(),'License "+index+"')]/../../../..//div[@class='active']");
        assertThat(newLicenseEntry.isVisible()).as("Validate new license entry gets added").isTrue();
    }

    public void validateNewVehicleQuestionsAreAdded(String index){
        String pageID = Serenity.getCurrentSession().get("pageID").toString();
        WebElementFacade newVehicleEntry = findBy("//div[contains(@id,'"+pageID+"')]//span[contains(text(),'Vehicle "+index+"')]/../../../..//div[@class='active']");
        assertThat(newVehicleEntry.isVisible()).as("Validate new vehicle entry gets added").isTrue();
    }

}

