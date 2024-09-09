package org.example.tests;

import org.example.appmanager.ApplicationManager;
import org.openqa.selenium.remote.Browser;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;

public class TestBase {

    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", Browser.CHROME.browserName()));

    @BeforeSuite
    public void setUp() throws IOException {
        app.init();
        app.getFtpHelper().upload(new File("src/test/resources/config_inc.php"), "config_inc.php",
                "config_inc.php.bak");
    }

    @AfterSuite
    public void tearDown() throws IOException {
        app.getFtpHelper().restore("config_inc.php.bak", "config_inc.php");
        app.stop();
    }
}
