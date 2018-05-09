Feature: Personal details advisor
  In order to continue with the application
  As authenticated member
  I want to enter the details of my Advisor

  Background:
    Given I am on Home Page
    And I am an authenticated member

  @LQA307 @authenticated @bothform @saldi
  Scenario Outline: Validate the Application questions data on Personal Details - advisor
    Given the following user data:
      | Age | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee | contact method | best time to contact | resident | other cover |claimed benefits|
      | 54  | Female | 55000:Per year | Actor      | No                               | No                | phone          | 11am - 2pm           | yes      | no          |no              |
    And with the following death cover data:
      | death cover type | death current cover | death calculated cover | quoted death cover amount   |
      | units            | 170000              | 155000                 | <quoted death cover amount> |
    And I am on Application personal details advisor page
    Then the personal details question asked is Do you have an advisor?
    When I select my advisor as no
    And I try to go to next page on application
    Then employment page is displayed
Examples:
| quoted death cover amount |
| 4122300                   |
| 27300                     |

  @LQA307 @authenticated @bothform @saldi
  Scenario Outline: Validate the question data if the user has an advisor and wants to receive communication only to him
    Given the following user data:
      | Age | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee | contact method | best time to contact | resident | other cover |claimed benefits|
      | 54  | Female | 55000:Per year | Actor      | No                               | No                | phone          | 11am - 2pm           | yes      | no          |no              |
    And with the following death cover data:
      | death cover type | death current cover | death calculated cover | quoted death cover amount   |
      | units            | 170000              | 155000                 | <quoted death cover amount> |
    And I am on Application personal details advisor page
    When I select my advisor as yes
    Then this reflexive question is asked Would you like all communications regarding the application to be sent to both you and your advisor or just you?
    When I want only me to receive the communication
    And I try to go to next page on application
    Then employment page is displayed
    Examples:
      | quoted death cover amount |
      | 4122300                   |
      | 27300                     |


  @LQA307 @authenticated @bothform @saldi
  Scenario Outline: Validate the question data if the user has an advisor and wants to receive communication to both
    Given the following user data:
      | Age | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee | contact method | best time to contact | resident | other cover |claimed benefits|
      | 54  | Female | 55000:Per year | Actor      | No                               | No                | phone          | 11am - 2pm           | yes      | no          |no              |
    And with the following death cover data:
      | death cover type | death current cover | death calculated cover | quoted death cover amount   |
      | units            | 170000              | 155000                 | <quoted death cover amount> |
    And I am on Application personal details advisor page
    When I select my advisor as yes
    Then this reflexive question is asked Would you like all communications regarding the application to be sent to both you and your advisor or just you?
    When I want both to receive the communication
    And I try to go to next page on application
    Then the following titles should be listed:
| Miss     |
| Mr       |
| Mrs      |
| Ms       |
| Archdea  |
| Assoc    |
| Brother  |
| Captain  |
| Dr       |
| Fath     |
| Fr       |
| Friar    |
| Hon      |
| Lady     |
| Mother   |
| Prof     |
| R Rev    |
| Rev Can  |
| Rev Dr   |
| Rn       |
| Rv       |
| Sir      |
| Sister   |
| Sr       |
| Venrble  |
| Very Rev |
    And the following states should be listed:
| VIC   |
| NSW   |
| QLD   |
| NT    |
| SA    |
| TAS   |
| ACT   |
| Other |
    Examples:
      | quoted death cover amount |
      | 4122300                   |
      | 27300                     |

  @LQA307 @authenticated @bothform @saldi
  Scenario Outline: Validate the postcode field is mandatory if the selected state is not Other
    Given the following user data:
      | Age | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee | contact method | best time to contact | resident | other cover |claimed benefits|
      | 54  | Female | 55000:Per year | Actor      | No                               | No                | phone          | 11am - 2pm           | yes      | no          |no              |
    And with the following death cover data:
      | death cover type | death current cover | death calculated cover | quoted death cover amount   |
      | units            | 170000              | 155000                 | <quoted death cover amount> |
    And I am on Application personal details advisor page
    And I select my advisor as yes
    And I want both to receive the communication
    And I try to go to next page on application
    When I enter the following advisor data:
| title | surname   | given name | street number | street     | suburb    | state | postcode | phone number | email   | hesta advisor |
| Mr    | giovancci | david      | 112           | collins st | melbourne | VIC   |          | 1231231234   | a@a.com | yes           |
    Then I am not asked for my country
    And I try to go to next page on application
    Then This text is displayed Please provide a postcode
    Examples:
      | quoted death cover amount |
      | 4122300                   |
      | 27300                     |

  @LQA307 @authenticated @bothform @saldi
  Scenario Outline: Validate the postcode field is non-mandatory if the selected state is Other
    Given the following user data:
      | Age | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee | contact method | best time to contact | resident | other cover |claimed benefits|
      | 54  | Female | 55000:Per year | Actor      | No                               | No                | phone          | 11am - 2pm           | yes      | no          |no              |
    And with the following death cover data:
      | death cover type | death current cover | death calculated cover | quoted death cover amount   |
      | units            | 170000              | 155000                 | <quoted death cover amount> |
    And I am on Application personal details advisor page
    And I select my advisor as yes
    And I want both to receive the communication
    And I try to go to next page on application
    When I enter the following advisor data:
| title | surname   | given name | street number | street     | suburb    | state | postcode | country | phone number | email   | hesta advisor |
| Mr    | giovancci | david      | 112           | collins st | melbourne | Other |          | usa     | 1231231234   | a@a.com | yes           |
    Then I am asked for my country
    And I try to go to next page on application
    Then employment page is displayed
    Examples:
      | quoted death cover amount |
      | 4122300                   |
      | 27300                     |