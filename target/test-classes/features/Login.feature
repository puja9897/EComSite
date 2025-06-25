Feature: User login

Scenario: User should be able to login with valid credentials.
Given User is in login page
When User enters email id and password
And click on Sign in button
Then USer is able to login successfully