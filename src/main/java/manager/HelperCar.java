package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HelperCar extends HelperBase {
    public HelperCar(WebDriver wd) {
        super(wd);
    }

    public void submit() {
        click(By.xpath("//*[@type='submit']"));
    }

    public void searchCurrentMonth(String city, String dateFrom, String dateTo) {
        typeCity(city);
        clearTextBox(By.id("dates"));
        click(By.id("dates"));
        //"9/10/2025","9/26/2025"
        String[] from = dateFrom.split("/");//["9"]["10"]["2025"]
        String locatorFrom = "//span[text()='" + from[1] + "']";
        click(By.xpath(locatorFrom));

        String[] to = dateTo.split("/");
        String locatorTo = "//span[text()='" + to[1] + "']";
        click(By.xpath(locatorTo));
    }

    private void typeCity(String city) {
        clearTextBox(By.id("city"));
        type(By.id("city"), city);
        click(By.id("city-suggestions"));
    }

    public boolean isListOfCarsAppeared() {
        return isElementPresent(By.cssSelector("a.car-container"));
    }

    public void searchCurrentYear(String city, String dateFrom, String dateTo) {
        typeCity(city);
        clearTextBox(By.id("dates"));
        click(By.id("dates"));
        //"10/5/2025","11/15/2025"

        LocalDate now = LocalDate.now(); //2025-09-03
        System.out.println(now);
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        LocalDate from = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("M/d/yyyy"));
        LocalDate to = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("M/d/yyyy"));
//        LocalDate from1 = LocalDate.parse("2013:23/05",DateTimeFormatter.ofPattern("yyyy:dd/MM"));
//        System.out.println(from);
//        System.out.println(from1);

        int diffMonth = from.getMonthValue() - month;
        if (diffMonth > 0) {
            clickNextMonthBtn(diffMonth);
        }
        click(By.xpath("//span[text()='" + from.getDayOfMonth() + "']"));

        diffMonth = to.getMonthValue() - from.getMonthValue();
        if (diffMonth > 0) {
            clickNextMonthBtn(diffMonth);
        }
        click(By.xpath("//span[text()='" + to.getDayOfMonth() + "']"));
    }

    private void clickNextMonthBtn(int diffMonth) {
        for (int i = 0; i < diffMonth; i++) {
            click(By.cssSelector(".rdrNextPrevButton.rdrNextButton"));
        }
    }

    public void searchAnyPeriod(String city, String dateFrom, String dateTo) {
        typeCity(city);
        clearTextBox(By.id("dates"));
        click(By.id("dates"));

        LocalDate now = LocalDate.now();
        LocalDate from = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("M/d/yyyy"));
        LocalDate to = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("M/d/yyyy"));

        int diffYear;
        int diffMonth;

        //***from
        diffYear = from.getYear() - now.getYear();
        if (diffYear == 0) {
            diffMonth = from.getMonthValue() - now.getMonthValue();
        } else {
            diffMonth = 12 - now.getMonthValue() + from.getMonthValue();
        }
        clickNextMonthBtn(diffMonth);
        click(By.xpath("//span[text()='" + from.getDayOfMonth() + "']"));
        //***to
        diffYear = to.getYear() - from.getYear();
        if (diffYear == 0) {
            diffMonth = to.getMonthValue() - from.getMonthValue();
        } else {
            diffMonth = 12 - from.getMonthValue() + to.getMonthValue();
        }
        clickNextMonthBtn(diffMonth);
        click(By.xpath("//span[text()='" + to.getDayOfMonth() + "']"));
    }
}