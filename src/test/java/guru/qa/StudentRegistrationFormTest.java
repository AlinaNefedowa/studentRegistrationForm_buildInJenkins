package guru.qa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

    public class StudentRegistrationFormTest {

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

        @BeforeAll
        static void setUp() {
            Configuration.baseUrl = "https://demoqa.com";
            //Configuration.holdBrowserOpen = true;
        }

        @Test
        void fillFormTest() {

            open("/automation-practice-form");

            //Hide the footer and banner
            Selenide.executeJavaScript("document.querySelector(\"footer\").hidden = 'true';" +
                    "document.querySelector(\"#fixedban\").hidden = 'true'");

            $("#firstName").setValue(firstName);
            $("#lastName").setValue(lastName);
            $("#userEmail").setValue(email);
            $(byText(gender)).click();
            $("#userNumber").setValue(mobile);
            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").selectOption("April");
            $(".react-datepicker__year-select").selectOption("1992");
            $("[aria-label$='April 12th, 1992']").click();
            $("#subjectsInput").setValue(subjects).pressEnter();
            $(byText(hobby1)).click();
            $(byText(hobby2)).click();
            $("#uploadPicture").uploadFromClasspath("img/"+picture);
            $("#currentAddress").setValue(currentAddress);

            //Scroll the page
            $("#stateCity-wrapper").scrollIntoView(true);

            $("#state").click();
            $("#stateCity-wrapper").$(byText(state)).click();
            $("#city").click();
            $("#city").$(byText(city)).click();

            //Click "Submit" button
            $("#submit").click();

            //Click "Submit" with changed page zoom
            // executeJavaScript("document.body.style.zoom='0.75';document.getElementById(\"submit\").click()");

            //Assertions

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
        }
    }
