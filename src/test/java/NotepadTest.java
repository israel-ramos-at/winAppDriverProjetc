import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URL;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

public class NotepadTest {

    private static WindowsDriver notepadSession = null;

    public static String getDate(){
        String date = LocalDate.now().toString();
        System.out.println(date);
        return date;
    }

    @BeforeClass
    public static void setUp(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", "C:\\Windows\\System32\\notepad.exe");
        capabilities.setCapability("platformName", "Windows");
        capabilities.setCapability("deviceName", "WindowsPC");
        try {
            notepadSession = new WindowsDriver(new URL("http://127.0.0.1:4723"), capabilities);
        }catch (Exception e){
            e.printStackTrace();
        }
        notepadSession.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void cleanApp(){
        notepadSession.quit();
        setUp();
    }

    @AfterSuite
    public void tearDown(){
        notepadSession.quit();
    }

    @Test
    public void checkAboutNotepadVersion(){
        notepadSession.findElementByName("Help").click();
        notepadSession.findElementByName("About Notepad").click();
        notepadSession.findElementByName("OK").click();
    }

    @Test
    public void enterSomeText(){
        notepadSession.findElementByClassName("Edit").sendKeys("mmmmm");
        try {
            Thread.sleep(3000);

        }catch (Exception e){

        }
        notepadSession.findElementByClassName("Edit").clear();

//        notepadSession.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

}
