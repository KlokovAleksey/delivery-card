package ru.netology;


import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    @Test
    void shouldSubmitForm() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Майкоп");
        String localDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").setValue(localDate);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[name=phone]").setValue("+79005555555");
        $("[data-test-id=agreement]").click();
        $$("[type=button]").get(1).click();
        $("[data-test-id=notification] .notification__content").waitUntil(Condition.visible, 15000).shouldHave(exactText("Встреча успешно забронирована на " + localDate));

    }
}
