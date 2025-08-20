
# Generating Allure Report
## How to Generate Allure Report
Run an Allure report on PowerShell with the below command:
```allure serve allure-results
```
This will start a local server and open the Allure report in your default web browser.

# Chaining Class Concept 
This framework uses a chaining class concept to allow for a more readable and maintainable code structure.
Chaining classes enable you to call multiple methods in a single line, making the code more fluent and expressive.
## How to Use Chaining Class
-Each Page Object method returns either:
-**'this'** When the flow stays on the same page. (validation, checks, etc.)
-**The next Page Object** When the flow navigates to a different page.(e.g. , LoginPage -> AccountPage-> TradePage)

### Example of Chaining Class Usage
```new LoginPage(driver)
    .enterUsername("admin")
    .enterPassword("password")
    .clickLoginButton();
    .verifyTradePageIsDisplayed();
```
# Page Object Model (POM)
## What is Page Object Model?
Page Object Model (POM) is a design pattern that enhances test maintenance and reduces code duplication
by creating an object repository for web UI elements. Each page of the application is represented by a
separate class, which contains methods to interact with the elements on that page.
## How to Use Page Object Model
1. Create a class for each page of the application.
2. Define methods in each class to interact with the elements on that page.
3. Use these methods in your test cases to perform actions on the page.
4. Use the chaining class concept to make your test cases more readable and maintainable.
5. Use the `@FindBy` annotation to locate elements on the page.
6. Use the `PageFactory` to initialize the page objects.
7. Use the `@BeforeMethod` annotation to initialize the page objects before each test method.
8. Use the `@AfterMethod` annotation to perform any cleanup after each test method.
9. Use the `@Test` annotation to define your test methods.
10. Use the `@Parameters` annotation to pass parameters to your test methods.
11. Use the `@DataProvider` annotation to provide data for your test methods.
12. Use the `@Listeners` annotation to add listeners to your test methods.
13. 