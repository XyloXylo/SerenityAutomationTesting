package au.com.aia.lifeappautomatedtests.steps.serenity_steps.Quote;

import au.com.aia.lifeappautomatedtests.pages.Quote.BeforeWeBeginPage;
import au.com.aia.lifeappautomatedtests.pages.Quote.QuotePage;
import au.com.aia.lifeappautomatedtests.steps.serenity_steps.generic.BaseSerenitySteps;
import au.com.aia.lifeappautomatedtests.utility.TestDataLifeApp;
import cucumber.api.DataTable;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.csv.converters.IntegerTypeConverter;


import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by aadm234 on 1/08/2017.
 */
public class BeforeWeBeginSerenitySteps extends BaseSerenitySteps {

    QuotePage quotePage;
    BeforeWeBeginPage beforeWeBeginPage;

    @Step
    public void validateBeforeWeBeginModalIsDisplayed(String title){
//        assertThat(beforeWeBeginPage.lblModalTitle.waitUntilVisible().getText()).isEqualToIgnoringCase(title);
    }


    @Step
    public void answerLumpSumReceivedOrNot(String answer){
        if (answer.contains("not")){
            beforeWeBeginPage.btnModalNo.waitUntilClickable().click();
        }else{
            beforeWeBeginPage.btnModalYes.waitUntilClickable().click();
        }
    }

    @Step
    public void validateBeforeWeBeginPageIsDisplayed(){
        assertThat(beforeWeBeginPage.lblPageTitle.waitUntilVisible().getText()).contains("Before you begin");
    }

    @Step
    public void validateTextDisplayedOnModal(String text){
        beforeWeBeginPage.verifyText(text);
    }

    @Step
    public void validateLongOrShortForm(String applicationType){
        String appType = beforeWeBeginPage.getItemFromLocalStorage("APPLICATION_TYPE");
        System.out.println("\n*APPLICATION TYPE = "+appType +" *\n");
        assertThat(appType).containsIgnoringCase(applicationType);
    }


    @Step
    public void validateTextDisplayedOnModalForIneligible(String text){
        beforeWeBeginPage.verifyIneligibleText(text);
    }

    @Step
    public void beginApplication(){
        beforeWeBeginPage.btnBeginApplication.waitUntilClickable().click();
//        isLongForm();
    }


    //Decide if it's long or short
    //Short form: If the member is younger than 55 and is requesting cover of less than $1.25 million
    //Long Form: 1. If the member is over 55 OR
    //   2.    If the member is requesting more than $1.25 million in Death or TPD cover; OR
    //   3.    If the member is requesting more than $10,000 per month for IP; OR
    //   4.    if member has previously opted out (get from LINK)
//    @Step
//    public void isLongForm(){
//        boolean isLong = false;
//
//        List<Map<String, String>> lifeAppTestData = TestDataLifeApp.getLifeAppTestUserData().asMaps(String.class, String.class);
//        for(Map<String,String> fieldValue: lifeAppTestData) {
//            if (Integer.parseInt(fieldValue.get("Age"))>=55){
//                isLong = true;
//            }else if (Integer.parseInt(fieldValue.get("Age"))<55){
//                //Check for requesting cover per product
//                //Death
//                if (TestDataLifeApp.getDeathCoverIncluded()){
//                    if (Integer.parseInt(fieldValue.get("quoted death cover amount"))>=1250000){
//                        isLong = true;
//                        break;
//                    }
//                }
//                //TPD
//                if (TestDataLifeApp.getTpdCoverIncluded()){
//                    if (Integer.parseInt(fieldValue.get("quoted tpd cover amount"))>=1250000){
//                        isLong = true;
//                        break;
//                    }
//                }
//                //IP
//                if (TestDataLifeApp.getIpCoverIncluded()){
//                    if (Integer.parseInt(fieldValue.get("quoted ip cover amount"))>=10000){
//                        isLong = true;
//                        break;
//                    }
//                }
//
//            }
//        }
//
//        Serenity.setSessionVariable("isLongForm").to(isLong);
//    }



}

