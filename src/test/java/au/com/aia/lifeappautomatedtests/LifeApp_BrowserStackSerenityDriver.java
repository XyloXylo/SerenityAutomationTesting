package au.com.aia.lifeappautomatedtests;

import net.serenitybdd.core.Serenity;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;
import net.thucydides.core.webdriver.DriverSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.Iterator;

/**
 * Created by aadm146 on 24/10/2016.
 */
public class LifeApp_BrowserStackSerenityDriver implements DriverSource {

    @Override
    public WebDriver newDriver(){

        System.out.println("\n\n ***Inside LifeApp_BrowserStackSerenityDriver \n\n");

        WebDriver _d;
        EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();

        String username = System.getenv("BROWSERSTACK_USER");
        System.out.println("BS Username from Jenkins:"+username);
        if(username == null) {
            username = (String) environmentVariables.getProperty("browserstack.user");
        }

        String accessKey = System.getenv("BROWSERSTACK_ACCESSKEY");
        System.out.println("BS User Key from Jenkins:"+accessKey);
        if(accessKey == null) {
            accessKey = (String) environmentVariables.getProperty("browserstack.key");
        }

        String browserstackLocal = System.getenv("BROWSERSTACK_LOCAL");
        System.out.println("BS Local flag from Jenkins:"+browserstackLocal);

        String browserstackLocalIdentifier = System.getenv("BROWSERSTACK_LOCAL_IDENTIFIER");
        System.out.println("BS Local Identifier from Jenkins:"+browserstackLocalIdentifier);


        String environment = System.getProperty("environment");
        System.out.println("\n\n *** ENVIRONMENT:"+environment +" \n\n");
        DesiredCapabilities capabilities = new DesiredCapabilities();

        Iterator it = environmentVariables.getKeys().iterator();

        //***Always Keep the LOCAL settings ON
        System.setProperty("browserstack.local", "true");
        capabilities.setCapability("browserstack.local", "true");

        //Local Identifier
        capabilities.setCapability("browserstack.localIdentifier", browserstackLocalIdentifier);

        //For Visual Logs
        capabilities.setCapability("browserstack.debug", "false");

        //video recording
        capabilities.setCapability("browserstack.video", "true");

        //Set the environment accordingly
        if (environment.equalsIgnoreCase("desktop")){
            System.out.println("\n\n ** SETTING DESKTOP CAPABILITIES !!");
            System.out.println("OS:"+System.getProperty("osName"));
            System.out.println("OS_VERSION:"+System.getProperty("osVersion"));
            System.out.println("BROWSER:"+System.getProperty("browserName"));
            System.out.println("BROWSER_VERSION:"+System.getProperty("browserVersion"));

            capabilities.setCapability("os",System.getProperty("osName"));
            capabilities.setCapability("os_version",System.getProperty("osVersion"));
            capabilities.setCapability("browser",System.getProperty("browserName"));
            capabilities.setCapability("browser_version",System.getProperty("browserVersion"));

        }else if (environment.equalsIgnoreCase("mobile")){
            System.out.println("\n\n ** SETTING MOBILE CAPABILITIES !!");
            capabilities.setCapability("browserName",System.getProperty("browserName"));
            capabilities.setCapability("platform",System.getProperty("platform"));
            capabilities.setCapability("device",System.getProperty("device"));

//            capabilities.setCapability("realMobile", "true");
        }


        while(it.hasNext()){
            String key = (String) it.next();

            if(key.equals("browserstack.user") || key.equals("browserstack.key") || key.equals("browserstack.server")){
                continue;
            }
            else if(key.startsWith("browserstack.")){
                capabilities.setCapability(key.replace("browserstack.", ""), environmentVariables.getProperty(key));
            }
        }

        try {
            //Append the scenarioName to the BrowserStack Session Name
            capabilities.setCapability("name", Serenity.getCurrentSession().get("scenarioName").toString());
            //Added ACCEPT_SSL_CERTS for SSL issue on FF
            capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS,true);
            System.out.println("Going to set the RemoteWebDriver");
            _d = new RemoteWebDriver(new URL("http://"+username+":"+accessKey+"@"+environmentVariables.getProperty("browserstack.server")+"/wd/hub"), capabilities);
            System.out.println("RemoteWebDriver set!");
//            String sessionID = ((RemoteWebDriver) _d).getSessionId().toString();
//            Serenity.setSessionVariable("sessionID").to(sessionID);
            return _d;

        }
        catch(Exception e){
            return null;
        }

    }


    @Override
    public boolean takesScreenshots() {
        return true;
    }

}
