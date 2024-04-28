package ru.yandex.praktikum.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {
    private final WebDriver webDriver;
    // Поле "Имя" на форме заказа
    private final By nameInputLocator = By.xpath("//input[@placeholder='* Имя']");
    // Поле "Фамилия" на форме заказа
    private final By surnameInputLocator = By.xpath("//input[@placeholder='* Фамилия']");
    // Поле "Адрес: куда привезти заказ" на форме заказа
    private final By addressInputLocator = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    // Поле "Станция метро" на форме заказа
    private final By subwayStationInputLocator = By.xpath("//input[@placeholder='* Станция метро']");
    // Поле "Телефон: на него позвонит курьер" на форме заказа
    private final By phoneNumberInputLocator = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    // Кнопка "Далее" на форме заказа
    private final By nextButtonLocator = By.xpath("//button[text()='Далее']");
    // Поле "Когда привезти самокат" на форме заказа
    private final By dateInputLocator = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    // Поле "Срок аренды" на форме заказа
    private final By rentPeriodlocator = By.xpath("//div[text()='* Срок аренды']");
    // Поле "Комментарий для курьера" на форме заказа
    private final By commentInputLocator = By.xpath("//input[@placeholder='Комментарий для курьера']");
    // Кнопка "Заказать" на форме заказа
    private final By orderButtonLocator = By.xpath("//div[contains(@class, 'Order_Buttons__')]//button[text() = 'Заказать']");
    // Кнопка "Да" на форме заказа
    private final By yesButtonLocator = By.xpath("//button[text()='Да']");
    // Оповещение о создании заказа
    private final By checkStatusLocator = By.xpath("//button[text()='Посмотреть статус']");


    public OrderPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void fillCustomerInfo(String name, String surname, String address, String subwayStation, String phoneNumber){
        WebElement nameInput = webDriver.findElement(nameInputLocator);
        nameInput.sendKeys(name);

        WebElement surnameInput = webDriver.findElement(surnameInputLocator);
        surnameInput.sendKeys(surname);

        WebElement addressInput = webDriver.findElement(addressInputLocator);
        addressInput.sendKeys(address);

        WebElement subwayStationInput = webDriver.findElement(subwayStationInputLocator);
        subwayStationInput.click();

        // Элемент выпадающего списка "Станция метро" на форме заказа
        String stationMenuItemLocator = "//div[text()='%s']";
        WebElement stationMenuItem = webDriver.findElement(By.xpath((String.format(stationMenuItemLocator, subwayStation))));

        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();",stationMenuItem);

        stationMenuItem.click();

        WebElement phoneNumberInput = webDriver.findElement(phoneNumberInputLocator);
        phoneNumberInput.sendKeys(phoneNumber);
    }

    public void clickNextButton(){
        WebElement nextButton = webDriver.findElement(nextButtonLocator);
        nextButton.click();
    }

    public void fillRentInfo(String date, String rentPeriod, String color, String comment){
        WebElement dateInput = webDriver.findElement(dateInputLocator);
        dateInput.sendKeys(date, Keys.ENTER);

        WebElement rentPeriodInput = webDriver.findElement(rentPeriodlocator);
        rentPeriodInput.click();

        // Пункт выпадающего списка "Срок аренды" на форме заказа
        String rentPeriodMenuItemLocator = "//div[text()='%s']";
        WebElement rentPeriodMenuItem = webDriver.findElement(By.xpath((String.format(rentPeriodMenuItemLocator, rentPeriod))));

        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();",rentPeriodMenuItem);

        rentPeriodMenuItem.click();

        // Чек-бокс цвета самоката  на форме заказа
        String colorInputItemLocator = "//label[text()='%s']";
        WebElement colorInputItem = webDriver.findElement(By.xpath((String.format(colorInputItemLocator, color))));
        colorInputItem.click();

        WebElement commentInput = webDriver.findElement(commentInputLocator);
        commentInput.sendKeys(comment);
    }

    public void clickOrderButton(){
        WebElement orderButton = webDriver.findElement(orderButtonLocator);
        orderButton.click();
    }

    public void clickYesButton(){
        WebElement yesButton = webDriver.findElement(yesButtonLocator);
        yesButton.click();
    }

    public boolean createdOrderIsDisplayed (){
        new WebDriverWait(webDriver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(webDriver.findElement(checkStatusLocator)));

        WebElement createdOrderNotification = webDriver.findElement(checkStatusLocator);
        return(createdOrderNotification.isDisplayed());
    }
}
