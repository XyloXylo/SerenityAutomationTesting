Feature: Welcome Page
  Verify Needs calculator welcome page

  Background:
    Given I am on Home Page

  @LQA-14 @WelcomePage
  Scenario: Needs calculator welcome page
    Given I am an public member
    Given I am on Insurance Needs Calculator Welcome Page
    Then This text is displayed Get a better understanding of what cover you might need if anything happens to you.
    Then This text is displayed You'll learn the amount of Death cover, Total and permanent disability and Income protection you might need.
    Then Text This will take around 2 minutes is displayed
    Then Text You will be asked some personal and financial questions text is displayed
    Then Text Some answer are optional but the more you do, the more accurate the outcome is displayed
    Then This text is displayed So you know, our calculator is based on a combination your inputs and our assumptions. You can amend these assumptions at the end of the process.
    Then I click Calculate My Cover
