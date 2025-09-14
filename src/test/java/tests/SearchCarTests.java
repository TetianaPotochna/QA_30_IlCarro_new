package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchCarTests extends TestBase{

    @Test
    public void searchCurrentMonthSuccess(){
        app.getHelperCar().searchCurrentMonth("Haifa","9/10/2025","9/15/2025");
        app.getHelperCar().getScreen("src/test/screenshots/current.png");
        app.getHelperCar().pause(15000);
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }


    @Test
    public void searchCurrentYearSuccess(){
        app.getHelperCar().searchCurrentYear("Haifa","10/5/2025","11/15/2025");
        app.getHelperCar().getScreen("src/test/screenshots/currentYear.png");
        app.getHelperCar().pause(10000);
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }


    @Test
    public void searchAnyPeriodSuccess() {
        app.getHelperCar().searchAnyPeriod("Haifa", "11/15/2025", "3/8/2026");
        app.getHelperCar().getScreen("src/test/screenshots/anyPeriod.png");
        app.getHelperCar().pause(10000);
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }

}