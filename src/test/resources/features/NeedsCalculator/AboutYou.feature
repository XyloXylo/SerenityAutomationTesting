Feature: About You
  Enter personal question(About you)

  Background:
    Given I am on Home Page

  @LQA-170 @AboutYou
  Scenario Outline: Verify Date of birth and Gender
    Given I am an public member
    Given I am on Insurance Needs Calculator Welcome Page
    When I click Calculate My Cover
    Then I close disclaimer
    Given I am on Needs Calculator form
    Then I enter Date of Birth <Age> and Select gender <Gender>
    Examples:
      | Age | Gender          |
      | 25  | Female          |
      | 52  | Male            |
      | 35  | Intersex:Male   |
      | 22  | Intersex:Female |

