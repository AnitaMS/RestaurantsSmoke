package com.restaurants.smoke;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

public class Restaurants {
	
	WebDriver driver;
	JavascriptExecutor js;
	final String randomEmail = UUID.randomUUID().toString() + "@example.com";

	@FindBy(xpath = ".//*[@class='img-responsive ember-view']")
	List<WebElement> links;

	@FindBy(xpath = ".//*[@class='ember-text-field ember-view']")
	WebElement datePicker;

	@FindBy(xpath = ".//*[contains(text(), 'Find a table')]")
	WebElement findATableButton;

	@FindBy(xpath = ".//a[contains(text(), '9:30 AM')]")
	WebElement reservationTime;

	@FindBy(xpath = ".//a[contains(text(), 'Create account')]")
	WebElement createAccount;

	@FindBy(xpath = "//input[@placeholder='First Name']")
	WebElement firstName;

	@FindBy(xpath = "//input[@placeholder='Last Name']")
	WebElement lastName;

	@FindBy(xpath = "//input[@placeholder='Email']")
	WebElement emailR;

	@FindBy(xpath = "//input[@placeholder='Password']")
	WebElement password;

	@FindBy(xpath = "//input[@placeholder='Confirm Password']")
	WebElement confirmPassword;

	@FindBy(xpath = ".//*[@class='btn btn-primary']")
	WebElement createAccountButton;

	@FindBy(xpath = ".//button[contains(text(), 'Complete reservation')]")
	WebElement completeReservationButton;

	@FindBy(xpath = "//td[1]")
	List<WebElement> reservationList;
	
	@FindBy(xpath = ".//div[@class='col-md-12 col-sm-12 col-xs-12 page-title v-align-center'][contains(.,'My')]")
	WebElement myRegistrationText;

	
	@FindBy(xpath = "//input[@placeholder='Time']")
	WebElement selectTime;
	
	
	//////////////////////////////////////////
	public Restaurants(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void clickWorkingHours() throws InterruptedException {
		//JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("window.scrollBy(0,300)", "");
		//Thread.sleep(3000);
		Random r = new java.util.Random();
		List<WebElement> workingHours = driver.findElements(By.xpath(".//*[contains(text(), ' PM')]"));
		if (!workingHours.isEmpty()) {
			WebElement randomElement = workingHours.get(r.nextInt(workingHours.size()));
			randomElement.click();
		}
	}
	
	
	
	

	public void clickSelectTime(){
		selectTime.click();
	}
	
	public void clearDateField() {
		datePicker.clear();
	}

	public void clickRestaurant() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300)", "");
		Thread.sleep(3000);
		Random r = new java.util.Random();
		List<WebElement> links = driver.findElements(By.xpath(".//*[@class='img-responsive ember-view']"));
		if (!links.isEmpty()) {
			WebElement randomElement = links.get(r.nextInt(links.size()));
			randomElement.click();
		}
	}

	public void setDate() throws ParseException {
		String date1 = new SimpleDateFormat("MMM dd,yyyy").format(new Date());
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(sdf.parse(date1));
		c.add(Calendar.DATE, 2);
		date1 = sdf.format(c.getTime());
		datePicker.sendKeys(date1);
	}

	public void clickFindATableButton() throws InterruptedException {
		findATableButton.submit();
		Thread.sleep(2000);
	}

	public void selectReservationTime() throws InterruptedException {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,10)", "");
		Thread.sleep(3000);
		Random r = new java.util.Random();
		List<WebElement> links = driver.findElements(By.xpath(".//a[@class='btn btn-primary']"));
		if (!links.isEmpty()) {
			WebElement randomElement = links.get(r.nextInt(links.size()));
		//	randomElement.click();
			js.executeScript("arguments[0].click();", randomElement);
		}		
		///JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("window.scrollBy(0,100)", "");
		//Thread.sleep(1000);
		//js.executeScript("arguments[0].click();", reservationTime);
	}

	public void clickCreateAccount() {
		createAccount.click();
	}

	public void setFirstName(String name) {
		firstName.sendKeys(name);
	}

	public void setLastName(String name) {
		lastName.sendKeys(name);
	}

	public void setEmailR2(String email) {
		email = randomEmail;
		emailR.sendKeys(email);
	}

	public void setPassword(String text) {
		password.sendKeys(text);
	}

	public void setConfirmPassword(String text) {
		confirmPassword.sendKeys(text);
	}

	public void clickCreateAccountButton() {
		createAccountButton.click();
	}

	public void clickCompleteReservationButton() {
		completeReservationButton.click();
	}

	public void verifyReservationIsCreated() {
		String actual = myRegistrationText.getText();
		String expected = "My reservations";
		 Reporter.log("|Actual value = " + actual, true);
	     Reporter.log("|Expected value = " + expected, true);
	    Assert.assertEquals(actual, actual);
		if (actual.equals(expected)) {
			Reporter.log("|PASS  Verifying reservation is successfful");
		} else {	
	        Assert.fail();
			Reporter.log("|FAIL  Verifying reservation is successfful", true);
		}	
	   }

}
