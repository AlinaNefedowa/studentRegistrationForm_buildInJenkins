package guru.qa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentRegistrationFormTest extends TestBase {

    String firstName = "Alisa";
    String lastName = "Berg";
    String email = "alisa.berg@gmail.com";
    String gender = "Female";
    String mobile = "9213332221";
    String dateOfBirth = "12 April,1992";
    String subjects = "Computer science";
    String hobby1 = "Sports";
    String hobby2 = "Reading";
    String picture = "Picture.jpeg";
    String currentAddress = "Operngasse 15, 7";
    String state = "Haryana";
    String city = "Karnal";

    @Test
    @Owner("AlinaNefedowa")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Student registration form")
    @Story("Filling the Student registration form")
    @Link(value = "Demoqa", url = "https://demoqa.com")
    @DisplayName("Successful filling of the Student registration form")

    void fillFormTest() {
        step("Open registration form", () -> {
            open("/automation-practice-form");
        });

        step("Hide the footer and banner", () -> {
            Selenide.executeJavaScript("document.querySelector(\"footer\").hidden = 'true';" +
                    "document.querySelector(\"#fixedban\").hidden = 'true'");
        });

        step("Fill in the first name", () -> {
            $("#firstName").setValue(firstName);
        });

        step("Fill in the last name", () -> {
            $("#lastName").setValue(lastName);
        });

        step("Fill in the user e-mail", () -> {
            $("#userEmail").setValue(email);
        });

        step("Fill in the gender", () -> {
            $(byText(gender)).click();
        });

        step("Fill in user number", () -> {
            $("#userNumber").setValue(mobile);
        });

        step("Fill in the date of birth", () -> {
            $("#dateOfBirthInput").click();
        });

        step("Fill in the date of birth", () -> {
            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").selectOption("April");
            $(".react-datepicker__year-select").selectOption("1992");
            $("[aria-label$='April 12th, 1992']").click();
        });

        step("Fill in the subjects", () -> {
            $("#subjectsInput").setValue(subjects).pressEnter();
        });

        step("Fill in the hobbies", () -> {
            $(byText(hobby1)).click();
            $(byText(hobby2)).click();
        });

        step("Upload the picture", () -> {
            $("#uploadPicture").uploadFromClasspath("img/" + picture);
        });

        step("Fill in the current address", () -> {
            $("#currentAddress").setValue(currentAddress);
        });

        step("Scroll the page", () -> {
            $("#stateCity-wrapper").scrollIntoView(true);
        });

        step("Fill in the state and city", () -> {
            $("#state").click();
            $("#stateCity-wrapper").$(byText(state)).click();
            $("#city").click();
            $("#city").$(byText(city)).click();
        });

        step("Click \"Submit\" button", () -> {
            $("#submit").click();
        });

        step("Verify form data", () -> {
            $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
            $(".modal-body").shouldHave(
                    text(firstName + " " + lastName),
                    text(email),
                    text(gender),
                    text(mobile),
                    text(dateOfBirth),
                    text(subjects),
                    text(hobby1 + ", " + hobby2),
                    text(picture),
                    text(currentAddress),
                    text(state + " " + city)
            );
        });
    }
}