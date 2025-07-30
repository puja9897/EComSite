package pages;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Homepage {

	WebDriver driver;

	public Homepage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ul[@class='sf-menu clearfix menu-content sf-js-enabled sf-arrows']/li/a")
	private List<WebElement> Categories;
	@FindBy(xpath = "//a[text()='Women']")
	private WebElement WomenCategory;

	@FindBy(xpath = "//a[@title='Tops']")
	private WebElement Tops;

	@FindBy(className = "login")
	private WebElement signInBtn;

	@FindBy(css = "a.account")
	private WebElement accountDetails;

	
	
	public void clickCategory(String selectCategory) {
		int retryCount = 0;
		while (retryCount < 3) {
			try {
	            List<WebElement> categoryList = driver.findElements(By.xpath(
	                    "//ul[@class='sf-menu clearfix menu-content sf-js-enabled sf-arrows']/li/a"));


				for (WebElement category : categoryList) {

					String categoryText = category.getText().trim();
					if (categoryText.equalsIgnoreCase(selectCategory)) {

						WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
						wait.until(ExpectedConditions.elementToBeClickable(category)).click();
						System.out.println("Clicked on Category: " + category);
						return;
					}
				}
			} catch (StaleElementReferenceException e) {
				System.out.println("Stale Element Exception...Attempted to retry :"+retryCount+1);
			}
			retryCount++;
		}
	}

	public boolean isAccountDisplayed() {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.visibilityOf(accountDetails));
	        return accountDetails.isDisplayed();
	    } catch (NoSuchElementException e) {
	        return false;
	    }
	}

	
	public boolean isAuthFail() {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	        WebElement error = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ol/li")));
	        

	        return error.isDisplayed();
	    } catch (NoSuchElementException e) {
	        return false;
	    }
	}

	public void clickSignIn() {
		signInBtn.click();
	}

	public void ClickOnWomenCategory() {
		WomenCategory.click();
	}

	public void onHoverWomen() {
		Actions action = new Actions(driver);
		action.moveToElement(WomenCategory).perform();
	}

	public void clickSubCategoryTop() {
		Tops.click();
	}
}
