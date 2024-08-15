package org.example.appmanager;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Browser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    private final Properties properties;
    protected WebDriver driver;
    private String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

        if (browser.equals(Browser.EDGE.browserName())) {
            driver = new EdgeDriver();
        } else if (browser.equals(Browser.CHROME.browserName())) {
            driver = new ChromeDriver();
        } else if (browser.equals(Browser.IE.browserName())) {
            driver = new InternetExplorerDriver();
        }

        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        driver.get(properties.getProperty("web.baseUrl"));
        driver.manage().window().setSize(new Dimension(1604, 865));
    }

    public void stop() {
        driver.quit();
    }
}
