package au.com.aia.lifeappautomatedtests;

import com.browserstack.local.Local;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BrowserStackSerenityTest {

    private static final String TASKLIST = "tasklist";
    private static final String KILL = "taskkill /F /IM ";

    /* This class sets up the Local test binary */

     static Local bsLocal;

    public static void setUp() throws Exception {
        EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();

        String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
        if(accessKey == null) {
            System.out.println("accessKey is null, read from serenity props");
            accessKey = (String) environmentVariables.getProperty("browserstack.key");
            System.out.println("accessKey="+accessKey);
        }

        String key = "browserstack.local";
        boolean is_local = environmentVariables.getProperty(key) != null && environmentVariables.getProperty(key).equals("true");

        if(is_local){
            bsLocal = new Local();
            Map<String, String> bsLocalArgs = new HashMap<String, String>();
            bsLocalArgs.put("key", accessKey);
            bsLocalArgs.put("proxyHost", "bluecoatvip.aia.biz");
            bsLocalArgs.put("proxyPort", "8080");
            //Dont start the binary if it's running already
            if (bsLocal.isRunning()){
                System.out.println("\n ** Local Binary is already running, dont tunnel again.");
            }else{
                System.out.println("\n\n LOCAL BINARY NOT RUNNING, ABOUT TO START\n\n");
                bsLocal.start(bsLocalArgs);
                System.out.println("\n**Local Binary started");
            }

        }
    }

//    @AfterClass
//    public static void tearDown() throws Exception {
//        if(bsLocal != null) {
//            bsLocal.stop();
//        }
//    }

    public static boolean isProcessRunning(String serviceName) throws Exception {

        Process p = Runtime.getRuntime().exec(TASKLIST);
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                p.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {

            System.out.println(line);
            if (line.contains(serviceName)) {
                return true;
            }
        }

        return false;

    }

    public static void killProcess(String serviceName) throws Exception {
        Runtime.getRuntime().exec(KILL + serviceName);
    }
}
