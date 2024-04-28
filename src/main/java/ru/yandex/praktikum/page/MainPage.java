package ru.yandex.praktikum.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private final WebDriver webDriver;

    // Верхняя кнопка "Заказать"
    private final By createOrderTopButtonLocator = By.xpath("//div[contains(@class, 'Header')]//button[text()='Заказать']");
    // Нижняя кнопка "Заказать"
    private final By createOrderBottomButtonLocator = By.xpath("//div[contains(@class, 'Home_FinishButton')]//button[text()='Заказать']");
    // Кнопка закрытия предупреждения о куках
    private final By closeCookiesButtonLocator = By.id("rcc-confirm-button");

    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }


    public void clickCreateOrder(int indexButton) {
        if (indexButton == 0) {
            WebElement createOrderButton = webDriver.findElement(createOrderTopButtonLocator);
            createOrderButton.click();
        } else {
            WebElement createOrderButton = webDriver.findElement(createOrderBottomButtonLocator);
            createOrderButton.click();
        }

    }

    public void closeCookiesWindow() {
        WebElement closeCookiesButton = webDriver.findElement(closeCookiesButtonLocator);
        closeCookiesButton.click();
    }

    public void openQuestion(int index) {
        // Вопрос в меню FAQ
        String questionAccordionLocator = "accordion__heading-%s";
        WebElement question = webDriver.findElement(By.id(String.format(questionAccordionLocator, index)));

        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();",question);

        new WebDriverWait(webDriver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(question));
        question.click();
    }

    public  boolean answerIsDisplayed (String expectedAnswer) {
        // Ответ в меню FAQ
        String answerAccordionLocator = "//div[contains(@id, 'accordion__panel')][.='%s']";
        WebElement answer = webDriver.findElement(By.xpath(String.format(answerAccordionLocator, expectedAnswer)));
        return answer.isDisplayed();
    }
}
