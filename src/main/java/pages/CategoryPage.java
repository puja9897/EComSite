package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.BaseUtilities;

public class CategoryPage {

	WebDriver driver;

	BaseUtilities utility;

	@FindBy(xpath = "//ul[@class='product_list grid row']/li/div/div/div/a/img")
	private List<WebElement> products;
	@FindBy(css = "span.heading-counter")
	private WebElement ProductCount;
	@FindBy(xpath = "//div[@class='breadcrumb clearfix']//span[@class='navigation_page']")
	private WebElement breadCrumb;
	@FindBy(xpath = "//ul[@id='ul_layered_id_attribute_group_1']//label")
	private List<WebElement> size;
	@FindBy(xpath = "//ul[@id='ul_layered_id_attribute_group_3']/li/input/following-sibling::label/a")
	private List<WebElement> colors;
	@FindBy(xpath = "//div[@id='enabled_filters']")
	private WebElement enabledFilters;
	@FindBy(xpath = "//span[text()='Size']")
	private WebElement sizeLabel;
	@FindBy(xpath="//a[@title='List']/i") private WebElement list;
	@FindBy(xpath="//ul[@class='product_list row list']") private WebElement listView;
	
	
	

	public CategoryPage(WebDriver driver) {
		this.driver = driver;
		this.utility = new BaseUtilities(driver);
		PageFactory.initElements(driver, this);
	}

	public boolean isProductListDisplayed() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", ProductCount);
		String ProdCount = ProductCount.getText().replaceAll("[^0-9]", "");
		int expectedCount = Integer.parseInt(ProdCount);
		System.out.println("Expected product count: " + expectedCount);
		System.out.println("Actual product count: " + products.size());

		return expectedCount == products.size();
	}

	public boolean isBreadCrumbDisplayed(String subCategory) {
		String actualSubcategory = breadCrumb.getText().trim();
		if (actualSubcategory.equalsIgnoreCase(subCategory)) {

			System.out.println("Breadcrumb navigation is proper for : " + actualSubcategory);
			return true;
		} else {
			System.out.println(
					"Breadcrumb navigation is not proper for : " + subCategory + " Found for " + actualSubcategory);

			return false;
		}

	}

	public void selectSize(String expectedSize) {

		try {
			utility.scrollToElement(sizeLabel);

			for (WebElement s : size) {

				String actualSize = s.getText().trim().substring(0, 1);
				System.out.println(actualSize);
				if (actualSize.equalsIgnoreCase(expectedSize)) {

					WebElement checkbox = s.findElement(By.xpath(".//preceding-sibling::div//input"));
					checkbox.click();
					// After clicking filter
					WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

					wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("enabled_filters"), "Size: S"));

					System.out.println("Size filter applied, size selected : " + actualSize);
					return;
				}
			}
			System.out.println("Size not found : " + expectedSize);
		} catch (Exception e) {
			System.out.println("Error selecting size : " + e.getMessage());
		}
	}

	public void selectColor(String expectedColor) {
		try {
			for (WebElement color : colors) {
				String actualColor = color.getText().trim().replaceAll("\\s*\\(\\d+\\)", "");
				System.out.println(actualColor);
				if (actualColor.equalsIgnoreCase(expectedColor)) {
					color.click();
					// After clicking filter
					WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

					wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("enabled_filters"),
							"Color: Yellow"));

					System.out.println("Color filter applied, color selected : " + actualColor);
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("Error selecting color : " + e.getMessage());
		}
	}

	public boolean isFilterApplied(String expectedFilter) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='enabled_filters']")));
			utility.scrollToElement(driver.findElement(By.id("enabled_filters")));
			Thread.sleep(2000);
			List<WebElement> filters = driver.findElements(By.xpath("//div[@id='enabled_filters']/ul/li"));
			for (WebElement filter : filters) {
				String actualFilter = filter.getText().trim();
				if (actualFilter.contains(expectedFilter)) {
					System.out.println(actualFilter + " is applied");
					return true;
				}
			}

			System.out.println(expectedFilter + " does not applied");

			return false;
		} catch (Exception e) {
			System.out.println("Error checking applied filters : " + e.getMessage());
			return false;
		}

	}
	
	public void clickList() {
		list.click();
		utility.waitTillVisibilityOfElement(listView);

	}
	
	public boolean isListViewDisplayed() {
		return listView.isDisplayed();
	}
}
