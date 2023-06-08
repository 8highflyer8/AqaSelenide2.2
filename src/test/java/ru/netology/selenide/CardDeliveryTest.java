package ru.netology.selenide;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

class CardDeliveryTest {
    String generateDate(int daysToAdd, String pattern) {
        return LocalDate.now().plusDays(daysToAdd).format(DateTimeFormatter.ofPattern(pattern));
    }


    @Test
    void shouldRegisterMeeting() {

        open("http://localhost:9999");


        $("[data-test-id='city'] input").setValue("Екатеринбург");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(generateDate(3, "dd.MM.yyyy"));
        $("[data-test-id='name'] input").setValue("Петров Василий");
        $("[data-test-id='phone'] input").setValue("+79123456789");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(".notification__title").shouldHave(visible.text("Успешно!"), Duration.ofMillis(15_000));
        $(".notification__content").shouldHave(visible.text("Встреча успешно забронирована на " +
                generateDate(3, "dd.MM.yyyy")), Duration.ofMillis(15_000));

    }
}
