Feature: Personal details other cover
  In order to continue with the application
  As authenticated member
  I want to enter the details of any cover I have had

  Background:
    Given I am on Home Page
    And I am an authenticated member

  @LQA306 @authenticated @longform @saldi
  Scenario Outline: Validate the Application questions data on Personal Details - claims
    Given the following user data:
| Age | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee | contact method | best time to contact | resident | other cover |
| 54  | Female | 55000:Per year | Actor      | No                               | No                | phone          | 11am - 2pm           | yes      | no          |
    And with the following death cover data:
| death cover type | death current cover | death calculated cover | quoted death cover amount   |
| units            | 170000              | 155000                 | <quoted death cover amount> |
    And I am on Application personal details claims page
    When I select my claim as no
    And I try to go to next page on application
    Then advisor page is displayed
    Examples:
| quoted death cover amount |
| 4122300                   |

  @LQA306 @authenticated @longform @saldi
  Scenario: Validate user should be able to add claim
    Given the following user data:
      | Age | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee | contact method | best time to contact | resident | other cover |
      | 54  | Female | 55000:Per year | Actor      | No                               | No                | phone          | 11am - 2pm           | yes      | no          |
    And with the following death cover data:
| death cover type | death current cover | death calculated cover | quoted death cover amount |
| units            | 170000              | 155000                 | 4122300                   |
    And I am on Application personal details claims page
    When I select my claim as yes
    And I try to go to next page on application
    When I enter the claim number 1 following data:
| company name   | claim year | claim amount | reason for claim | claim finalised | fully recovered | last symptoms date |
| dt electronics | 1993       | 50000        | personal reasons | no              | yes             | 12/09/1981         |
    When I try to go to next page on application
    Then advisor page should be displayed on application

  @LQA306 @authenticated @longform @saldi
  Scenario: Validate user should be able to add multiple claims
    Given the following user data:
      | Age | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee | contact method | best time to contact | resident | other cover |
      | 54  | Female | 55000:Per year | Actor      | No                               | No                | phone          | 11am - 2pm           | yes      | no          |
    And with the following death cover data:
      | death cover type | death current cover | death calculated cover | quoted death cover amount |
      | units            | 170000              | 155000                 | 4122300                   |
    And I am on Application personal details claims page
    When I select my claim as yes
    And I try to go to next page on application
    When I enter the claim number 1 following data:
      | company name   | claim year | claim amount | reason for claim | claim finalised | fully recovered | last symptoms date |
      | dt electronics | 1993       | 50000        | personal reasons | no              | yes             | 12/09/1981         |
    When I want to add another claim
    Then the claim number 1 should collapse into an accordion
    And a new list of questions for claim number 2 should be revealed below
    When I enter the claim number 2 following data:
| company name | claim year | claim amount | reason for claim | claim finalised | fully recovered | last symptoms date |
| anz          | 1982       | 25000        | personal reasons | yes             | no              | 01/12/1988         |
    When I want to add another claim
    Then the claim number 2 should collapse into an accordion
    And a new list of questions for claim number 3 should be revealed below

  @LQA306 @authenticated @longform @saldi @LQA371
  Scenario: Validate user should be able to remove multiple claims
    Given the following user data:
      | Age | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee | contact method | best time to contact | resident | other cover |
      | 54  | Female | 55000:Per year | Actor      | No                               | No                | phone          | 11am - 2pm           | yes      | no          |
    And with the following death cover data:
      | death cover type | death current cover | death calculated cover | quoted death cover amount |
      | units            | 170000              | 155000                 | 4122300                   |
    And I am on Application personal details claims page
    When I select my claim as yes
    And I try to go to next page on application
    When I enter the claim number 1 following data:
      | company name   | claim year | claim amount | reason for claim | claim finalised | fully recovered | last symptoms date |
      | dt electronics | 1993       | 50000        | personal reasons | no              | yes             | 12/09/1981         |
    When I want to add another claim
    Then the claim number 1 should collapse into an accordion
    And a new list of questions for claim number 2 should be revealed below
    When I enter the claim number 2 following data:
      | company name | claim year | claim amount | reason for claim | claim finalised | fully recovered | last symptoms date |
      | anz          | 1982       | 25000        | personal reasons | yes             | no              | 01/12/1988         |
    When I want to add another claim
    Then the claim number 2 should collapse into an accordion
    And a new list of questions for claim number 3 should be revealed below
    When I remove claim number 2 with confirmation
    Then this claim should be removed from the claim page

  @LQA306 @authenticated @longform @saldi @LQA371
  Scenario: Validate user should be able to revert if happen to click remove claim
    Given the following user data:
      | Age | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee | contact method | best time to contact | resident | other cover |
      | 54  | Female | 55000:Per year | Actor      | No                               | No                | phone          | 11am - 2pm           | yes      | no          |
    And with the following death cover data:
      | death cover type | death current cover | death calculated cover | quoted death cover amount |
      | units            | 170000              | 155000                 | 4122300                   |
    And I am on Application personal details claims page
    When I select my claim as yes
    And I try to go to next page on application
    When I enter the claim number 1 following data:
      | company name   | claim year | claim amount | reason for claim | claim finalised | fully recovered | last symptoms date |
      | dt electronics | 1993       | 50000        | personal reasons | no              | yes             | 12/09/1981         |
    When I want to add another claim
    Then the claim number 1 should collapse into an accordion
    And a new list of questions for claim number 2 should be revealed below
    When I enter the claim number 2 following data:
      | company name | claim year | claim amount | reason for claim | claim finalised | fully recovered | last symptoms date |
      | anz          | 1982       | 25000        | personal reasons | yes             | no              | 01/12/1988         |
    When I want to add another claim
    Then the claim number 2 should collapse into an accordion
    And a new list of questions for claim number 3 should be revealed below
    When I remove claim number 2 with no confirmation
    Then this claim should be not removed from the claim page