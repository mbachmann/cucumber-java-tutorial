package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckboxesPage extends BasePage {
	private final By checkboxes = By.cssSelector("#checkboxes input[type='checkbox']");

	public CheckboxesPage(WebDriver driver) { super(driver); }

	public void open(String baseUrl) {
		// Visit the checkboxes page directly
		driver.get("https://the-internet.herokuapp.com/checkboxes");
	}

	private List<WebElement> all() {
		return driver.findElements(checkboxes);
	}

	public void setStateByIndex(int oneBasedIndex, boolean checked) {
		WebElement cb = all().get(oneBasedIndex - 1);
		if (cb.isSelected() != checked) cb.click();
	}

	public boolean isChecked(int oneBasedIndex) {
		return all().get(oneBasedIndex - 1).isSelected();
	}
}