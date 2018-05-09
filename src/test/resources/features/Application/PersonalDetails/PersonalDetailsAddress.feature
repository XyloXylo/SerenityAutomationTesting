Feature: Personal details address
  In order to continue with the application
  As authenticated member
  I want to enter/view my residential and postal address

  Background:
    Given I am on Home Page

  @LQA280 @authenticated @pending
  Scenario: Validate the Application questions data on Personal Details - address
    Given I am an authenticated member
    And the following user data:
      | Age | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee |
      | 35  | Female | 55000:Per year | Actor      | No                               | No                |
    And I am on Application personal details address page
    When I enter the following residential address data:
| street number | street name | suburb    | state | postcode | mail address same |
| 1             | inkerman    | melbourne | VIC   | 3000     | yes               |
    When I try to go to next page on application
#    Then Address page should be displayed on application

  @LQA280 @authenticated @pending
  Scenario: Validate user should be able to provide any country if not Australia on address page
    Given I am an authenticated member
    And the following user data:
      | Age | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee |
      | 35  | Female | 55000:Per year | Actor      | No                               | No                |
    And I am on Application personal details address page
    When I enter the following residential address data:
| street number | street name | suburb    | state | postcode | mail address same | country |
| 1             | inkerman    | melbourne | Other | 3000     | yes               | usa     |
    When I try to go to next page on application
#    Then Address page should be displayed on application

  @LQA280 @authenticated @pending
  Scenario: Validate user is able to provide postal address if different from mailing
    Given I am an authenticated member
    And the following user data:
      | Age | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee |
      | 35  | Female | 55000:Per year | Actor      | No                               | No                |
    And I am on Application personal details address page
    And I enter the following residential address data:
| street number | street name | suburb    | state | postcode | mail address same |
| 1             | inkerman    | melbourne | VIC   | 3000     | no                |
    And I enter the following postal address data:
| street number | street name | suburb    | state | postcode | country |
| 12            | collins st  | melbourne | Other | 3000     | Ireland |
    When I try to go to next page on application
#    Then Address page should be displayed on application

  @LQA280 @authenticated @pending
  Scenario: Validate postcode is non-mandatory if state is Other
    Given I am an authenticated member
    And the following user data:
      | Age | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee |
      | 35  | Female | 55000:Per year | Actor      | No                               | No                |
    And I am on Application personal details address page
    And I enter the following residential address data:
| street number | street name | suburb    | state | postcode | mail address same | country |
| 1             | inkerman    | melbourne | Other |          | no                | usa     |
    And I enter the following postal address data:
| street number | street name | suburb    | state | postcode | country |
| 12            | collins st  | melbourne | Other |          | Ireland |
    When I try to go to next page on application
#    Then Address page should be displayed on application