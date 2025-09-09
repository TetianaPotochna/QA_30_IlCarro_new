package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class ApplicationManager {
    WebDriver wd;

    HelperCar helperCar;
    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);

    public void unit() {
        wd = new ChromeDriver();
        logger.info("All tests runs in Chrome Browser");
        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wd.navigate().to("https://icarro-v1.netlify.app/");
        logger.info("The link--->" + wd.getCurrentUrl());

        helperCar = new HelperCar(wd);
    }

    public void stop() {
        wd.quit();
    }



    public HelperCar getHelperCar() {
        return helperCar;
    }
}
