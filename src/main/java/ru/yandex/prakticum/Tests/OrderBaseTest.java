package ru.yandex.prakticum.Tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import ru.yandex.prakticum.ConstantsURL;
import ru.yandex.prakticum.PageObject.MainPage;
import ru.yandex.prakticum.PageObject.OrderPage;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class OrderBaseTest extends BaseTest {
    //Поля класса
    private String name;
    private String lastName;
    private String address;
    private String phone;
    private String orderDate;
    private String metroStation;
    private String comment;
    private String rentPeriod;
    private String colorCheckBox;
    private boolean isVisibleExpected;


    public OrderBaseTest(String name, String lastName, String address,
                         String phone, String orderDate, String metroStation, String comment, String rentPeriod, String colorCheckBox, boolean isVisibleExpected) {

        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.orderDate = orderDate;
        this.metroStation = metroStation;
        this.comment = comment;
        this.rentPeriod = rentPeriod;
        this.colorCheckBox = colorCheckBox;
        this.isVisibleExpected = isVisibleExpected;
    }

    //Параметризация
    @Parameterized.Parameters
    public static Object[][] getOrderData() {

        return new Object[][]{
                {"Яяяяяя", "Устал", "Писать автотесты", "+79852121212", "01.01.2023", "Текстильщики","Комент", "сутки","серая безысходность", true},
                {"Дмитрий", "Антонов", "Москва 22 33", "+79852121212", "03.04.2025","Кузьминки", "Позвоните по прибытию","трое суток","чёрный жемчуг", true}
        };
    }

    @Test
    public void makeOrderTestWithTopButton() {
        OrderPage orderPage = new OrderPage(driver);
        //Открыть страницу
        orderPage.open();
        //Нажимаем кнопку Заказать в хедере
        orderPage.clickOrderButtonOnHeader();
        //Заполняем поля ввода на первой странице оформления заказа
        orderPage.setName(name);
        orderPage.setSurname(lastName);
        orderPage.setAddress(address);
        orderPage.selectMetroStationByName(metroStation);
        orderPage.setPhoneNumber(phone);
        orderPage.clickNextButton();
        orderPage.selectDeliveryDate(orderDate);
        orderPage.selectRentPeriod(rentPeriod);
        orderPage.selectScooterColour(colorCheckBox);
        orderPage.setCommentToCourier(comment);
        orderPage.clickOrderButton();
        orderPage.clickApproveButton();
        //Осуществляем запись фактического результата видимости модального окна с оформленным заказом
        boolean isVisibleActual = orderPage.isOrderModalHeaderVisible();
        //Сравниваем ожидаемый и фактический результаты
        assertEquals("Модальное окно с оформленным заказом не отображается", isVisibleExpected, isVisibleActual);
    }

    @Test
    public void makeOrderTestWithBottomButton() {
        OrderPage orderPage = new OrderPage(driver);
        //Открыть страницу
        orderPage.open();
        //скролим к кнопке заказа в основной части страницы
        orderPage.scrollToOrderButton();
        orderPage.clickOrderButtonOnHomePage();
        //Заполняем поля ввода на первой странице оформления заказа
        orderPage.setName(name);
        orderPage.setSurname(lastName);
        orderPage.setAddress(address);
        orderPage.selectMetroStationByName(metroStation);
        orderPage.setPhoneNumber(phone);
        orderPage.clickNextButton();
        orderPage.selectDeliveryDate(orderDate);
        orderPage.selectRentPeriod(rentPeriod);
        orderPage.selectScooterColour(colorCheckBox);
        orderPage.setCommentToCourier(comment);
        orderPage.clickOrderButton();
        orderPage.clickApproveButton();
        //Осуществляем запись фактического результата видимости модального окна с оформленным заказом
        boolean isVisibleActual = orderPage.isOrderModalHeaderVisible();
        //Сравниваем ожидаемый и фактический результаты
        assertEquals("Модальное окно с оформленным заказом не отображается", isVisibleExpected, isVisibleActual);
    }

}
