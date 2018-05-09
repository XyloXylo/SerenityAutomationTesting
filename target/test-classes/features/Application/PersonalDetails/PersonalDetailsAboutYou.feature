Feature: Personal Details AboutYou
  In order to continue with the application
  As authenticated member
  I want to enter/view my personal details

  Background:
    Given I am on Home Page

  @LQA40 @PersonalDetailsAboutYou @authenticated @pending
  Scenario Outline: Validate the user is presented with the About You questions data on Personal Details page on Application
    Given I am an authenticated member
    And the following user data:
      | Age   | Gender   | Income   | Occupation   | Less than 15 hours work per week   | Contract employee   |
      | <Age> | <Gender> | <Income> | <Occupation> | <Less than 15 hours work per week> | <Contract employee> |
    And I am on Application personal details about you page
    Examples:
      | Age | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee |
      | 35  | Female | 55000:Per year | Actor      | No                               | No                |
