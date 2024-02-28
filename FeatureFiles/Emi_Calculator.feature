Feature: EMI Calculator

  Scenario: EMI Calculator Validation
    Given the user is on carLoan page
    When user enter the valid credentials(amount:"1500000",interest:"9.5",term:"1")
    Then user move to the table
    And user should see the principal amount and interest amount
    Given the user click the homeLoan menu
    When the user enter the valid credentials
    Then the user move to payment schedule table
    And the user extract the data
    Given the user open the loan calculator
    When the user enter the inputs
    Then the user check the UI
    And user check the change in slider
    Given the user is on emi Loan Calculator
    When user enter the loan valid credentials
    Then the user check the loan UI
    And user check the change in slider
    Given the user open the loan Tenure calculator
    Then the user check the loan Tenure UI
