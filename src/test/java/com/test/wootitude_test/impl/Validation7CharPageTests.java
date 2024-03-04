package com.test.wootitude_test.impl;

import com.test.wootitude_test.base.BaseTest;
import com.test.wootitude_test.pageobject.Validation7CharPage;
import com.test.wootitude_test.statics.Constants;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Arrays;
import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Validation7CharPageTests extends BaseTest {

	private WebDriver driver;
	private Validation7CharPage validation7CharPage;
	protected static final String prohibitedSymbols = "!#№$;%:^?&*()+={}[]~`\\|/,<>";
	protected static final Integer stringLength = 7;


	@BeforeAll
	public void setUp() {
		ChromeOptions options = new ChromeOptions();
		driver = new ChromeDriver(options);
		driver.get(configuration.getValidation7chars());
		validation7CharPage = new Validation7CharPage(driver);

	}

	@ParameterizedTest
	@ValueSource(strings = {"Aa0*123", "BBBBBBB", "1111111", "qqqqqqq", "*******"})
	public void testValidInput(String str) {
		validation7CharPage.setInputValue(str);
		validation7CharPage.clickSubmit();
		String result = validation7CharPage.getValidationResult();
		StringAssert( Constants.VALID, result, str);
	}


	@ParameterizedTest
	@MethodSource("provideProhibitedSymbols")
	public void invalidProhibitedSymbols(String str) {
		validation7CharPage.setInputValue(String.format("%s%s", getRandomString(stringLength - 1), str));
		validation7CharPage.clickSubmit();
		String result = validation7CharPage.getValidationResult();
		StringAssert( Constants.INVALID, result, str);
	}

	@ParameterizedTest
	@ValueSource(strings = {"", "Aa012345", "b", ""})
	public void invalidLength(String str) {
		validation7CharPage.setInputValue(str);
		validation7CharPage.clickSubmit();
		String result = validation7CharPage.getValidationResult();
		StringAssert(Constants.INVALID, result, str);
	}

	@Test
	public void invalidCyrillicLetters(){
		var valueToTest = "АБВГДЕЁ";
		validation7CharPage.setInputValue(valueToTest);
		validation7CharPage.clickSubmit();
		String result = validation7CharPage.getValidationResult();
		StringAssert( Constants.INVALID, result, valueToTest);
	}
	@AfterAll
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	protected Stream<Arguments> provideProhibitedSymbols() {
		return Arrays.stream(prohibitedSymbols.split("")).map(Arguments::of);
	}
	protected String getRandomString(Integer length) {
		return RandomStringUtils.randomAlphabetic(length);

	}

}
