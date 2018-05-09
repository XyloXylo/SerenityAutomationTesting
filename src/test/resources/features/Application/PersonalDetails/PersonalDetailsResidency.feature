Feature: Personal details residency
  In order to continue with the application
  As authenticated member
  I want to enter my residency details

  Background:
    Given I am on Home Page

  @LQA290 @authenticated @bothform @saldi
  Scenario Outline: Validate the Application questions data on Personal Details - residency
    Given I am an authenticated member
    And the following user data:
      | Age | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee |
      | 54  | Female | 55000:Per year | Actor      | No                               | No                |
    And with the following death cover data:
| death cover type | death current cover | death calculated cover | quoted death cover amount   |
| units            | 170000              | 155000                 | <quoted death cover amount> |
    And with the following contact details data:
| contact method | best time to contact |
| phone          | 11am - 2pm           |
    And I am on Application personal details residency page
    Then This text is displayed Australian or New Zealand citizen or an Australian permanent resident
    Then this field <field name> is <field display> on residency page
    When I enter the following residency data:
| resident |
| yes      |
    When I try to go to next page on application
    Then <pagename> page should be displayed on application
  Examples:
| scenario | field name       | field display | quoted death cover amount | pagename    |
| long     | Country of birth | displayed     | 4122300                   | Other Cover |
| short    | Country of birth | not displayed | 27300                     | Advisor     |


  @LQA290 @authenticated @bothform @saldi
  Scenario Outline: Validate residency reflexive questions if the user is not a resident
    Given I am an authenticated member
    And the following user data:
      | Age | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee |
      | 54  | Female | 55000:Per year | Actor      | No                               | No                |
    And with the following death cover data:
| death cover type | death current cover | death calculated cover | quoted death cover amount   |
| units            | 170000              | 155000                 | <quoted death cover amount> |
    And with the following contact details data:
      | contact method | best time to contact |
      | phone          | 11am - 2pm           |
    And I am on Application personal details residency page
    When I enter the following residency data:
| resident |
| no       |
    Then this reflexive question is asked What type of visa do you hold including subclass?
    And this reflexive question is asked What is the visa subclass?
    Examples:
| scenario | quoted death cover amount |
| long     | 4122300                   |
| short    | 27300                     |

  @LQA290 @authenticated @bothform @saldi
  Scenario Outline: Validate user should be able to move from residency question if non-resident
    Given I am an authenticated member
    And the following user data:
      | Age | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee |
      | 54  | Female | 55000:Per year | Actor      | No                               | No                |
    And with the following death cover data:
| death cover type | death current cover | death calculated cover | quoted death cover amount   |
| units            | 170000              | 155000                 | <quoted death cover amount> |
    And with the following contact details data:
      | contact method | best time to contact |
      | phone          | 11am - 2pm           |
    And I am on Application personal details residency page
    When I enter the following residency data:
| resident | visa type | visa subclass |
| no       | work      | 457           |
    When I try to go to next page on application
#    Then Other Cover page should be <page display> on application
    Then <pagename> page should be displayed on application
    Examples:
| scenario | quoted death cover amount | pagename    |
#| long     | 4122300                   | Other Cover |
| short    | 27300                     | Advisor     |
