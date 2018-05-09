package au.com.aia.lifeappautomatedtests.steps.serenity_steps.Quote;

import au.com.aia.lifeappautomatedtests.pages.Quote.AssumptionsPage;
import au.com.aia.lifeappautomatedtests.pages.Quote.QuotePage;
import au.com.aia.lifeappautomatedtests.steps.serenity_steps.generic.BaseSerenitySteps;
import au.com.aia.lifeappautomatedtests.utility.DateUtil;
import au.com.aia.lifeappautomatedtests.utility.ExcelWriter;
import cucumber.api.DataTable;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by aadm234 on 1/08/2017.
 */
public class AssumptionsSerenitySteps extends BaseSerenitySteps {

    QuotePage quotePage;
    AssumptionsPage assumptionsPage;



    @Step
    public void validateAssumptionDefaultValue(String assumptionName, String assumptionValue){
        assumptionsPage.validateAssumption(assumptionName, assumptionValue);
    }

    @Step
    public void resetAssumptions(){
        assumptionsPage.btnResetValues.waitUntilClickable().click();
    }

    @Step
    public void saveAssumptions(){
        assumptionsPage.scrollToElement(assumptionsPage.btnSaveValues);
        assumptionsPage.btnSaveValues.waitUntilClickable().click();
        //Pending: The Assumptions box needs to be waited for closing before moving on
    }

    @Step
    public void enterAssumptionValues(List<Map<String,String>> assumptionDataValues){
        for(Map<String,String> fieldValue: assumptionDataValues){
            assumptionsPage.enterAssumptionData(fieldValue.get("Assumption"), fieldValue.get("Assumption Value"));
        }
    }

    @Step
    public void validateTextDisplayedOnModal(String text){
        assumptionsPage.verifyTextOnAssumptionsModal(text);
    }

}

