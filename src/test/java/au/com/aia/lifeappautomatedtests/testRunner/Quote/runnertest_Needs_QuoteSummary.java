package au.com.aia.lifeappautomatedtests.testRunner.Quote;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;


@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = {"src/test/resources/features/Quote/PersonalDetails.feature"},
        glue = {"au.com.aia.lifeappautomatedtests.steps.stepDefs","au.com.aia.lifeappautomatedtests.hooks" },
        tags = {"~@Ignore", "~@Pending", "~@pending","@Poonam"}
)

public class runnertest_Needs_QuoteSummary {
    static long startTime;
    static long endTime;

    @BeforeClass
    public static void startRunCukesTest() throws Exception {
        System.out.println(">>>>> STARTING RUN CUKES TEST SUITE: NeedsQuoteSummary.feature <<<<<");
        startTime = System.currentTimeMillis();
        System.out.println("SUITE NeedsQuoteSummary START: " + startTime);
    }

    @AfterClass
    public static void endRunCukesTest() throws Exception {
        System.out.println(">>>>> FINISHED RUN CUKES TEST SUITE: NeedsQuoteSummary.feature <<<<<");
        endTime = (System.currentTimeMillis() - startTime)/1000;
        System.out.println("SUITE NeedsQuoteSummary END: " + endTime);
    }
}
