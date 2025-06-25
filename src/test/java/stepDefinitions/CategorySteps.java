package stepDefinitions;

import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CategoryPage;
import pages.Homepage;
import pages.SignInPage;

public class CategorySteps {

	WebDriver driver = Hooks.driver;
	Homepage homepage;
	CategoryPage cat;
	SignInPage sign;
	
	public CategorySteps() {
		homepage = new Homepage(driver);
		cat = new CategoryPage(driver);
		sign=new SignInPage(driver);
	}


	@Given("User is on homepage")
	public void user_is_on_homepage() {
	   homepage.clickSignIn();
	}

	@When("User logins successfully with valid email id and password")
	public void user_logins_successfully_with_valid_email_id_and_password() {
	   sign.logincred();
	   sign.clickSignin();
	   assertTrue(homepage.isAccountDisplayed(), "Login unsuccessful");
	}

	@When("User clicks on {string} Category")
	public void user_clicks_on_category(String category) {
		if (category.equals("Women")) {
			homepage.ClickOnWomenCategory();
		}
	}

	@Then("Product list should be displayed for {string} category")
	public void product_list_should_be_displayed_for_category(String string) {
		Assert.assertTrue(cat.isProductListDisplayed(), "Product count mismatch");
	}

	@When("User hovers on {string} Category")
	public void user_hovers_on_category(String category) {
		if (category.equals("Women")) {
			homepage.onHoverWomen();
		}
	}

	@When("Clicks on {string} sub category")
	public void clicks_on_sub_category(String subCategory) {
		if (subCategory.equals("Tops")) {
			homepage.clickSubCategoryTop();
		}
	}

	@Then("Product list for {string} sub category  should be displayed")
	public void product_list_for_sub_category_should_be_displayed(String string) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul.product_list")));
		Assert.assertTrue(cat.isProductListDisplayed(), "Product count mismatch");
	}

	@When("User navigates on {string} category")
	public void user_navigates_on_category(String category) {
		if(category.equals("Dresses")) {
			homepage.clickCategory(category);
		}
	   	}

	@Then("Breadcrumb navigation should be {string}")
	public void breadcrumb_navigation_should(String category) {
	    if(category.equals("Dresses")) {
	    	Assert.assertTrue(cat.isBreadCrumbDisplayed(category), "Incorrect Bread Crumb displayed");
	    }
	}

	@When("Apply Filter {string}")
	public void apply_filter(String filter) {
	   if(filter.equals("S")) {
		   cat.selectSize(filter);
	   }
	    if(filter.equalsIgnoreCase("Yellow")) {
		   cat.selectColor(filter);
	   }
	}

	
	@Then("Only Products with size {string} and color {string} should be displayed")
	public void only_products_with_size_and_color_should_be_displayed(String size, String color) {
		boolean isSizeApplied = cat.isFilterApplied("Size: " + size);
	    boolean isColorApplied = cat.isFilterApplied("Color: " + color);
	    Assert.assertTrue(isSizeApplied && isColorApplied, 
	        "Expected filters not applied: Size[" + size + "] Color[" + color + "]");
		Assert.assertTrue(cat.isProductListDisplayed(), "Product count mismatch");

	}

	@When("Clicks on List View")
	public void clicks_on_list_view() {
	    cat.clickList();
	}

	@Then("Products should be displayed in list view.")
	public void products_should_be_displayed_in_list_view() {
	    Assert.assertTrue(cat.isListViewDisplayed(), "List View is not displayed");
	}



}
