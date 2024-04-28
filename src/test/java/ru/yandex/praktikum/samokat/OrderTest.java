package ru.yandex.praktikum.samokat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.WebDriverFactory;
import ru.yandex.praktikum.page.MainPage;
import ru.yandex.praktikum.page.OrderPage;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderTest {
    private static final String BROWSER = "chrome";
    private WebDriver webDriver;

    private final int indexButton;
    private final String name;
    private final String surname;
    private final String address;
    private final String subwayStation;
    private final String phoneNumber;
    private final String date;
    private final String rentPeriod;
    private final String color;
    private final String comment;

    public OrderTest(int indexButton, String name, String surname, String address, String subwayStation, String phoneNumber, String date, String rentPeriod, String color, String comment) {
        this.indexButton = indexButton;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.subwayStation = subwayStation;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.rentPeriod = rentPeriod;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] data(){
        return new Object[][]{
                {0,"Аня","Петрова", "Нагатинская 1", "Павелецкая", "89991112222", "30.06.2024", "сутки", "чёрный жемчуг", "Позвоните заранее, пожалуйста"},
                {1,"Петя","Краснов", "Ленина 56", "Сокольники", "89993334444", "01.05.2024", "шестеро суток", "серая безысходность", "Бесконатная доставка"},
        };
    }

    @Before
    public void setup() {

        webDriver = WebDriverFactory.getWebDriver(BROWSER);
        webDriver.get("https://qa-scooter.praktikum-services.ru");
    }

    @Test
    public void createOrderTest() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.closeCookiesWindow();
        mainPage.clickCreateOrder(indexButton);

        OrderPage orderPage = new OrderPage(webDriver);
        orderPage.fillCustomerInfo(name, surname, address, subwayStation, phoneNumber);
        orderPage.clickNextButton();
        orderPage.fillRentInfo(date, rentPeriod, color, comment);
        orderPage.clickOrderButton();
        orderPage.clickYesButton();
        assertTrue(orderPage.createdOrderIsDisplayed());
    }

    @After
    public void tearDown(){
        webDriver.quit();
    }
}
