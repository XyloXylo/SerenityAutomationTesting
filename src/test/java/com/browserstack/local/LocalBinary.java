package com.browserstack.local;

import com.browserstack.local.LocalException;
import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Pattern;



import org.apache.commons.io.FileUtils;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;
import java.net.URL;
import java.util.regex.Pattern;

class LocalBinary {

    private static final String BIN_URL = "https://s3.amazonaws.com/browserStack/browserstack-local/";

    private String httpPath;

    private String binaryPath;

    private boolean isOSWindows;

    private final String orderedPaths[] = {
            System.getProperty("user.home") + "/.browserstack",
            System.getProperty("user.dir"),
            System.getProperty("java.io.tmpdir")
    };

    LocalBinary() throws LocalException {
        initialize();
        getBinary();
        checkBinary();
    }

    private void initialize() throws LocalException {
        String osname = System.getProperty("os.name").toLowerCase();
        isOSWindows = osname.contains("windows");
        String binFileName;

//        osname = "linux";

        if (isOSWindows) {
            System.out.println("*** WINDOWS !!! \n\n");
            binFileName = "BrowserStackLocal.exe";
        } else if (osname.contains("mac") || osname.contains("darwin")) {
            binFileName = "BrowserStackLocal-darwin-x64";
        } else if (osname.contains("linux")) {
            System.out.println("*** LINUX !!! \n\n");
            String arch = System.getProperty("os.arch");
            binFileName = "BrowserStackLocal-linux-" + (arch.contains("64") ? "x64" : "ia32");
            System.out.println("binFileName: "+binFileName);
        } else {
            throw new LocalException("Failed to detect OS type");
        }

        httpPath = BIN_URL + binFileName;
        System.out.println("httpPath: "+httpPath);
    }

    private void checkBinary() throws LocalException{
        System.out.println("inside checkBinary");
        boolean binaryWorking = validateBinary();

        if(!binaryWorking){
            System.out.println("Binary is not working");
            File binary_file = new File(binaryPath);
            if (binary_file.exists()) {
                binary_file.delete();
            }
            getBinary();
            if(!validateBinary()){
                throw new LocalException("BrowserStackLocal binary is corrupt");
            }
        }
        System.out.println("Binary worked");
    }

    private boolean validateBinary() throws LocalException{
        System.out.println("inside validateBinary");
        Process process;
        try {

            process = new ProcessBuilder(binaryPath,"--version").start();

            BufferedReader stdoutbr = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String stdout="",line="";

            while ((line = stdoutbr.readLine()) != null) {
                stdout += line;
            }
            process.waitFor();

            boolean validBinary = Pattern.matches("BrowserStack Local version \\d+\\.\\d+", stdout);

            return validBinary;
        }catch(IOException ex){
            throw new LocalException(ex.toString());
        }
        catch(InterruptedException ex){
            throw new LocalException(ex.toString());
        }
    }

    private void getBinary() throws LocalException {
        String destParentDir = getAvailableDirectory();
        System.out.println("destParentDir: "+destParentDir);
        binaryPath = destParentDir + "/BrowserStackLocal";
        System.out.println("binaryPath: "+binaryPath);

        if (isOSWindows) {
            System.out.println("Windows getBinary");
            binaryPath += ".exe";
        }

        if (!new File(binaryPath).exists()) {
            System.out.println("File doesn't exist at "+binaryPath +", so need to download the binary.");
            downloadBinary(destParentDir);
        }
    }

    private String getAvailableDirectory() throws LocalException {
        int i = 0;
        while (i < orderedPaths.length) {
            String path = orderedPaths[i];
            if (makePath(path))
                return path;
            else
                i++;
        }

        throw new LocalException("Error trying to download BrowserStackLocal binary");
    }

    private boolean makePath(String path) {
        try {
            if (!new File(path).exists())
                new File(path).mkdirs();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void downloadBinary(String destParentDir) throws LocalException {
        try {
            System.out.println("inside downloadBinary");
            if (!new File(destParentDir).exists())
                new File(destParentDir).mkdirs();

            System.out.println("mkdir complete at:"+destParentDir);
            URL url = new URL(httpPath);
            String source = destParentDir + "/BrowserStackLocal";
            System.out.println("source:"+source);
            if (isOSWindows) {
                System.out.println("windows downloadBinary");
                source += ".exe";
            }

            File f = new File(source);
            System.out.println("copyURLToFile start, URL: "+url);
            FileUtils.copyURLToFile(url, f);
            System.out.println("copyURLToFile finish");

            changePermissions(binaryPath);
        } catch (Exception e) {
            throw new LocalException("Error trying to download BrowserStackLocal binary");
        }
    }

    private void changePermissions(String path) {
        System.out.println("inside changePermissions");
        File f = new File(path);
        f.setExecutable(true, true);
        f.setReadable(true, true);
        f.setWritable(true, true);
        System.out.println("finish changePermissions");
    }

    public String getBinaryPath() {
        return binaryPath;
    }
}
