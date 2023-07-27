package ru.yandex.prakticum.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static ru.yandex.prakticum.ConstantsURL.MAIN_PAGE_URL;

public class OrderPage {
    private WebDriver driver;

    // Поле ввода имени
    private By nameInput = By.xpath(".//input[@*='* Имя']");

    // Поле ввода фамилии
    private By surnameInput = By.xpath(".//input[@*='* Фамилия']");

    // Поле ввода адреса
    private By addressInput = By.xpath(".//input[@*='* Адрес: куда привезти заказ']");

    // Поле выбора станции метро
    private By metroSelectInput = By.xpath(".//input[@*='* Станция метро']");

    // Поле ввода номера телефона
    private By phoneNumberInput = By.xpath(".//input[@*='* Телефон: на него позвонит курьер']");

    // Кнопка Далее на форме создания заказа
    private By orderFormNextButton = By.xpath(".//button[text()='Далее']");


    //HomePage
    //Кнопка "Заказать" с домашней страницы
    private By orderButtonHomePage = By.xpath(".//div[@class='Home_FinishButton__1_cWm']//button[contains(@class, 'Button_Button__ra12g') and (text() = 'Заказать')]");

    //Заголовок страницы
    //Кнопка "Заказать" в заголовке
    private By orderButtonHeader = By.xpath(".//div[@class='Header_Header__214zg']//button[contains(@class, 'Button_Button__ra12g') and (text() = 'Заказать')]");



    // Поле выбора даты доставки
    private By deliveryDateInput = By.xpath(".//input[@*='* Когда привезти самокат']");

    // Поле выбора срока аренды
    private By rentPeriodSelect = By.xpath(".//div[text()='* Срок аренды']");

    // Чекбокс цвета самоката "чёрный жемчуг"
    private By scooterBlackColourCheckbox = By.id("black");

    // Чекбокс цвета самоката "серая безысходность"
    private By scooterGreyColourCheckbox = By.id("grey");

    // Поле комментария для курьера
    private By commentToCourierInput = By.xpath(".//input[@*='Комментарий для курьера']");

    // Кнопка Заказать в форме заказа
    private By orderButton = By.xpath(".//button[contains(@class, 'Button_Middle__1CSJM') and text()='Заказать']");

    // Кнопка Да на форме подтверждения создания заказа
    private By confirmButton = By.xpath(".//button[text()='Да']");

    // Кнопка Посмотреть статус на форме подтверждения
    private By checkStatusButton = By.xpath(".//button[text()='Посмотреть статус']");



    public void open() {
        driver.get(MAIN_PAGE_URL);
    }
    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setName(String name) {
        driver.findElement(nameInput).sendKeys(name);
    }

    public void setSurname(String surname) {
        driver.findElement(surnameInput).sendKeys(surname);
    }

    public void setAddress(String address) {
        driver.findElement(addressInput).sendKeys(address);
    }
    public void selectMetroStationByName(String metroName) {
        driver.findElement(metroSelectInput).sendKeys(metroName);
        By locator = By.className("select-search__select");
        driver.findElement(locator).click();
    }
    public void setPhoneNumber(String phoneNumber) {
        driver.findElement(phoneNumberInput).sendKeys(phoneNumber);
    }
    public void selectDeliveryDate(String date) {
        driver.findElement(deliveryDateInput).sendKeys(date);
        By locator = By.xpath(".//div[contains(@class, 'react-datepicker__day--selected')]");
        driver.findElement(locator).click();
    }
    public void scrollToOrderButton(){
        WebElement element = driver.findElement(orderButtonHomePage);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }
    public void selectRentPeriod(String rentPeriod) {
        driver.findElement(rentPeriodSelect).click();
        driver.findElement(By
                .xpath(String.format(".//div[@class='Dropdown-option' and text()='%s']", rentPeriod))).click();
    }

    //Нажать на кнопку создания заказа в заголовке
    public void clickOrderButtonOnHeader(){
        driver.findElement(orderButtonHeader).click();
    }
    //Нажать на кнопку создания заказа на главной странице
    public void clickOrderButtonOnHomePage() {
        driver.findElement(orderButtonHomePage).click();
    }
    public void selectScooterColour(String colour) {
        if (colour.equals("чёрный жемчуг")) {
            driver.findElement(scooterBlackColourCheckbox).click();
        } else if (colour.equals("серая безысходность")) {
            driver.findElement(scooterGreyColourCheckbox).click();
        }
    }

    public void setCommentToCourier(String commentToCourier) {
        driver.findElement(commentToCourierInput).sendKeys(commentToCourier);
    }

    public void clickNextButton() {
        driver.findElement(orderFormNextButton).click();
    }

    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    public void clickApproveButton() {
        driver.findElement(confirmButton).click();
    }

    // Локатор модального окна оформленного заказа
    private By orderModalHeader = By.xpath("//div[contains(@class, 'Order_ModalHeader__3FDaJ') and contains(text(), 'Заказ оформлен')]");

    //Ожидание видимости модального окна об успешном оформлении заказа
    public void waitVisibleOrderModalHeader() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(orderModalHeader));
    }
    public boolean isOrderModalHeaderVisible() {
        waitVisibleOrderModalHeader();
        return driver.findElement(orderModalHeader).isDisplayed();
    }
}
