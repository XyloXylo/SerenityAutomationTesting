Feature: Welcome Screen
  Verify Quote welcome screen

  Background:
    Given I am on Home Page

  @LQA-118 @QuoteWelcomeScreen
  Scenario: Quote welcome page
    Given I am an public member
    Given I am on Quote Welcome Page
    Then Verify Quote Welcome Page
    Then I click Get A Quote
