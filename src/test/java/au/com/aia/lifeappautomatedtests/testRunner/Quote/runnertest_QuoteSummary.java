package au.com.aia.lifeappautomatedtests.testRunner.Quote;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;


@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = {"src/test/resources/features/Quote/QuoteSummary.feature"},
        glue = {"au.com.aia.lifeappautomatedtests.steps.stepDefs","au.com.aia.lifeappautomatedtests.hooks" },
        tags = {"~@Ignore", "~@Pending", "~@pending"}
)

public class runnertest_QuoteSummary {
    static long startTime;
    static long endTime;

    @BeforeClass
    public static void startRunCukesTest() throws Exception {
        System.out.println(">>>>> STARTING RUN CUKES TEST SUITE: QuoteSummary.feature <<<<<");
        startTime = System.currentTimeMillis();
        System.out.println("SUITE QuoteSummary START: " + startTime);
    }

    @AfterClass
    public static void endRunCukesTest() throws Exception {
        System.out.println(">>>>> FINISHED RUN CUKES TEST SUITE: QuoteSummary.feature <<<<<");
        endTime = (System.currentTimeMillis() - startTime)/1000;
        System.out.println("SUITE QuoteSummary END: " + endTime);
    }
}
