package com.test.wootitude_test.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Validation7CharPage {

    private final WebDriver driver;
    private final By inputField= By.name("characters");
    private final By submitButton=By.name("validate");
    private final By resultText= By.name("validation_message");

    public Validation7CharPage(WebDriver driver) {
        this.driver = driver;
    }
    public void setInputValue(String value) {
        WebElement inputElement = driver.findElement(inputField);
        inputElement.clear();
        inputElement.sendKeys(value);
    }

    public void clickSubmit() {
        WebElement submitElement = driver.findElement(submitButton);
        submitElement.click();
    }

    public String getValidationResult() {
        WebElement resultElement = driver.findElement(resultText);
        return resultElement.getAttribute("value");
    }
}
