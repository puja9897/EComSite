Feature: Category navigation

Background: 
Given User is on homepage
When User logins successfully with valid email id and password

Scenario: Navigate to a Top-level category

When User clicks on "Women" Category
Then Product list should be displayed for "Women" category

Scenario: Navigate to a sub-category

When User hovers on "Women" Category
And Clicks on "Tops" sub category
Then Product list for "Tops" sub category  should be displayed

Scenario: Verify breadcrumb navigation
When User navigates on "Dresses" category
Then Breadcrumb navigation should be "Home>Dresses"


Scenario: Filter products by Size and Color
When User clicks on "Women" Category
And Apply Filter "S"
And Apply Filter "Yellow"
Then Only Products with size "S" and color "Yellow" should be displayed 

Scenario: View Products in List
When User clicks on "Women" Category
And Clicks on List View
Then Products should be displayed in list view. 