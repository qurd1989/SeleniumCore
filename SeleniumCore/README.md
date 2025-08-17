
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