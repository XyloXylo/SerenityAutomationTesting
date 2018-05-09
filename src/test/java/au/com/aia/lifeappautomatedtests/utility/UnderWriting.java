package au.com.aia.lifeappautomatedtests.utility;

import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;

import java.io.Serializable;

/**
 * Created by aadm146 on 6/01/2017.
 */
public class UnderWriting implements Serializable{
    private boolean blnRequiresUnderWriting;

    public Boolean getRequiresUnderWriting(){
        return blnRequiresUnderWriting;
    }

    public void setRequiresUnderWriting(boolean blnRequiresUnderWriting){
        this.blnRequiresUnderWriting = blnRequiresUnderWriting;
        System.out.println("\n** UnderWriting required **\n");
    }

}
