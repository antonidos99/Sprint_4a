package ru.yandex.prakticum.PageObject;



import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static ru.yandex.prakticum.ConstantsURL.MAIN_PAGE_URL;


//Тут создаю локаторы
public class MainPage {
    private WebDriver driver;


    //Заголовок страницы
    //Кнопка "Заказать" в заголовке
    private By orderButtonHeader = By.xpath(".//div[@class='Header_Header__214zg']//button[contains(@class, 'Button_Button__ra12g') and (text() = 'Заказать')]");

    //Заголовок страницы
    // Кнопка "Статус заказа"
    private By orderStatusButton = By.className("Header_Link__1TAG7");

    //Заголовок страницы
    // Кнопка GO для ввода номера заказа
    private By goButton = By.xpath(".//button[contains(@class, 'Button_Button__ra12g') and (text() = 'Go!')]");

    //Заголовок страницы
    //Поле ввода номера заказа
    private By enterOrderNumber = By.xpath(".//input[contains(@class, 'Input_Input__1iN_Z') and (@placeholder = 'Введите номер заказа')]");

    //Заголовок страницы
    //Лого яндекса
    private By yandexLogo = By.className("Header_LogoYandex__3TSOI");

    //Заголовок страницы
    //Лого самоката
    private By scooterLogo = By.className("Header_LogoScooter__3lsAR");


    //HomePage
    //Кнопка "Заказать" с домашней страницы
    private By orderButtonHomePage = By.xpath(".//div[@class='Home_FinishButton__1_cWm']//button[contains(@class, 'Button_Button__ra12g') and (text() = 'Заказать')]");



    // Кнопки вопросов о важном
    private By questionButtons = By.className("accordion__item");


    // Поле с текстом ответа
    private By faqAnswerText = By.className("accordion__panel");

    // Окно "Такого заказа нет"
    private By noOrderFrame = By.xpath(".//img[@alt = 'Not found']");


    //Методы
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }


    //Нажать на кнопку создания заказа в заголовке
    public void clickOrderButtonOnHeader(){
        driver.findElement(orderButtonHeader).click();
    }

    //Нажать на кнопку статуса заказа
    public void clickOrderStatusButton() {
        driver.findElement(orderStatusButton).click();
    }
    //Нажать на кнопку GO
    public void clickGoButton() {
        driver.findElement(goButton).click();
    }

    //Нажать на лого яндекса
    public void clickYandexLogo() {
        driver.findElement(yandexLogo).click();
    }

    //Нажать на лого самоката
    public void clickScooterLogo() {
        driver.findElement(scooterLogo).click();
    }


    //Нажатия на Кнопки вопросов о важном
    public void clickQuestionButtonByIndex(int index) {
        driver.findElements(questionButtons).get(index).click();
    }

    //Получение текста ответов в вопросах о важном

    public String getAnswerByIndex(int index) {
        return driver.findElements(questionButtons).get(index).findElement(faqAnswerText).getText();
    }

    public void scrollToQuestions(){
        WebElement element = driver.findElement(questionButtons);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void open() {
        driver.get(MAIN_PAGE_URL);
    }

    //Ввод номера заказа с ожиданием
    public void setOrderNumber(String orderNumber) {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(enterOrderNumber)).sendKeys(orderNumber);
    }
    //Нажать на кнопку создания заказа на главной странице
    public void clickOrderButton(int index) {
        driver.findElement(orderButtonHomePage).click();
    }

    //Проверка на noOrderFrame
    public boolean orderNotExist() {
        return new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.presenceOfElementLocated(noOrderFrame)).isDisplayed();
    }





}
