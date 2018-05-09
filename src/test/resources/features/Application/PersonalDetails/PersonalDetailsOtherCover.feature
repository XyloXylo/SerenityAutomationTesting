Feature: Personal details other cover
  In order to continue with the application
  As authenticated member
  I want to enter the details of any cover I have had

  Background:
    Given I am on Home Page

  @LQA305 @authenticated @longform @saldi
  Scenario Outline: Validate the Application questions data on Personal Details - other cover
    Given I am an authenticated member
    And the following user data:
| Age | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee | contact method | best time to contact | resident |
| 54  | Female | 55000:Per year | Actor      | No                               | No                | phone          | 11am - 2pm           | yes      |
    And with the following death cover data:
      | death cover type | death current cover | death calculated cover | quoted death cover amount |
      | units            | 170000              | 155000                 | 4122300                   |
    And I am on Application personal details other cover page
    Then the personal details question asked is Do you have, or are you applying for life, disability or trauma insurance on your life other than this application (including any pending applications held with any insurer)?
#    Then the personal details question is asked
    When I select my other cover as <other cover>
    And I try to go to next page on application
    Then <pageName> page is displayed
    Examples:
| other cover | pageName |
| no          | claims   |
| yes         | policy   |


  @LQA305 @authenticated @longform @saldi
  Scenario: Validate amount of cover should be displayed for the selected cover type on policy
    Given I am an authenticated member
    And the following user data:
      | Age | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee |contact method | best time to contact | resident |other cover|
      | 54  | Female | 55000:Per year | Actor      | No                               | No                |phone          | 11am - 2pm           | yes      |yes        |
    And with the following death cover data:
      | death cover type | death current cover | death calculated cover | quoted death cover amount |
      | units            | 170000              | 155000                 | 4122300                   |
    And I am on Application personal details policy page
    When I enter the policy 1 following data:
| policy number | commencing date | policy holder name | insurer | replacing cover | cover type   |
| 123456        | 01021981        | test               | aia     | yes             | Death,IP,TPD |
    Then the amount of cover for Death is displayed on policy page
    And the amount of cover for IP is displayed on policy page
    And the amount of cover for TPD is displayed on policy page

  @LQA305 @authenticated @longform @saldi
  Scenario: Validate waiting and benefit period is displayed if the selected cover is IP on policy
    Given I am an authenticated member
    And the following user data:
      | Age | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee |contact method | best time to contact | resident |other cover|
      | 54  | Female | 55000:Per year | Actor      | No                               | No                |phone          | 11am - 2pm           | yes      |yes        |
    And with the following death cover data:
      | death cover type | death current cover | death calculated cover | quoted death cover amount |
      | units            | 170000              | 155000                 | 4122300                   |
    And I am on Application personal details policy page
    When I enter the policy 1 following data:
| policy number | commencing date | policy holder name | insurer | replacing cover | cover type |
| 123456        | 01021981        | test               | aia     | no              | IP         |
    And the amount of cover for IP is displayed on policy page
    Then waiting period is displayed on policy page
    And benefit period is displayed on policy page

  @LQA305 @authenticated @longform @saldi
  Scenario: Validate the dropdown values for waiting and benefit period if the selected cover is IP on policy
    Given I am an authenticated member
    And the following user data:
      | Age | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee |contact method | best time to contact | resident |other cover|
      | 54  | Female | 55000:Per year | Actor      | No                               | No                |phone          | 11am - 2pm           | yes      |yes        |
    And with the following death cover data:
      | death cover type | death current cover | death calculated cover | quoted death cover amount |
      | units            | 170000              | 155000                 | 4122300                   |
    And I am on Application personal details policy page
    When I enter the policy 1 following data:
      | policy number | commencing date | policy holder name | insurer | replacing cover | cover type |
      | 123456        | 01021981        | test               | aia     | yes             | IP         |
    And the amount of cover for IP is displayed on policy page
    Then the following waiting periods should be listed:
| 30 days |
| 60 days |
| 90 days |
    And the following benefit periods should be listed:
| To age 60           |
| To age 67           |
| 2 Years (expiry 60) |
| 2 Years (expiry 67) |

  @LQA305 @authenticated @longform @saldi
  Scenario: Validate user should be able to add policy
    Given I am an authenticated member
    And the following user data:
      | Age | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee |contact method | best time to contact | resident |other cover|
      | 54  | Female | 55000:Per year | Actor      | No                               | No                |phone          | 11am - 2pm           | yes      |yes        |
    And with the following death cover data:
      | death cover type | death current cover | death calculated cover | quoted death cover amount |
      | units            | 170000              | 155000                 | 4122300                   |
    And I am on Application personal details policy page
    When I enter the policy 1 following data:
| policy number | commencing date | policy holder name | insurer | replacing cover | cover type   |death cover|ip cover|tpd cover|waiting period|benefit period|
| 123456        | 01021981        | test               | aia     | no              | Death,IP,TPD |300000     |200000  |500000   |30 days       |To age 67     |
    When I try to go to next page on application
    Then Special Terms page should be displayed on application

  @LQA305 @authenticated @longform @saldi
  Scenario: Validate user should be able to add multiple policies
    Given I am an authenticated member
    And the following user data:
      | Age | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee |contact method | best time to contact | resident |other cover|
      | 54  | Female | 55000:Per year | Actor      | No                               | No                |phone          | 11am - 2pm           | yes      |yes        |
    And with the following death cover data:
      | death cover type | death current cover | death calculated cover | quoted death cover amount |
      | units            | 170000              | 155000                 | 4122300                   |
    And I am on Application personal details policy page
    When I enter the policy 1 following data:
      | policy number | commencing date | policy holder name | insurer | replacing cover | cover type   |death cover|ip cover|tpd cover|waiting period|benefit period|
      | 123456        | 01021981        | test               | aia     | no              | Death,IP,TPD |300000     |200000  |500000   |30 days       |To age 67     |
    When I want to add another policy
    Then the policy number 1 should collapse into an accordion
    And a new list of questions for policy number 2 should be revealed below
    When I enter the policy 2 following data:
| policy number | commencing date | policy holder name | insurer | replacing cover | cover type | death cover |
| 12            | 02031972        | testsecond         | aia     | yes             | Death      | 10000       |
    When I want to add another policy
    Then the policy number 2 should collapse into an accordion
    And a new list of questions for policy number 3 should be revealed below

  @LQA305 @authenticated @longform @saldi @LQA371
  Scenario: Validate user should be able to remove multiple policies
    Given I am an authenticated member
    And the following user data:
      | Age | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee |contact method | best time to contact | resident |other cover|
      | 54  | Female | 55000:Per year | Actor      | No                               | No                |phone          | 11am - 2pm           | yes      |yes        |
    And with the following death cover data:
      | death cover type | death current cover | death calculated cover | quoted death cover amount |
      | units            | 170000              | 155000                 | 4122300                   |
    And I am on Application personal details policy page
    When I enter the policy 1 following data:
      | policy number | commencing date | policy holder name | insurer | replacing cover | cover type   |death cover|ip cover|tpd cover|waiting period|benefit period|
      | 123456        | 01021981        | test               | aia     | no              | Death,IP,TPD |300000     |200000  |500000   |30 days       |To age 67     |
    When I want to add another policy
    Then the policy number 1 should collapse into an accordion
    And a new list of questions for policy number 2 should be revealed below
    When I enter the policy 2 following data:
      | policy number | commencing date | policy holder name | insurer | replacing cover | cover type | death cover |
      | 12            | 02031972        | testsecond         | aia     | yes             | Death      | 10000       |
    When I want to add another policy
    Then the policy number 2 should collapse into an accordion
    And a new list of questions for policy number 3 should be revealed below
    When I remove policy number 2 with confirmation
    Then this policy should be removed from the policy page

  @LQA305 @authenticated @longform @saldi @LQA371
  Scenario: Validate user should be able to revert if happen to click remove policy
    Given I am an authenticated member
    And the following user data:
      | Age | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee |contact method | best time to contact | resident |other cover|
      | 54  | Female | 55000:Per year | Actor      | No                               | No                |phone          | 11am - 2pm           | yes      |yes        |
    And with the following death cover data:
      | death cover type | death current cover | death calculated cover | quoted death cover amount |
      | units            | 170000              | 155000                 | 4122300                   |
    And I am on Application personal details policy page
    When I enter the policy 1 following data:
      | policy number | commencing date | policy holder name | insurer | replacing cover | cover type   |death cover|ip cover|tpd cover|waiting period|benefit period|
      | 123456        | 01021981        | test               | aia     | no              | Death,IP,TPD |300000     |200000  |500000   |30 days       |To age 67     |
    When I want to add another policy
    Then the policy number 1 should collapse into an accordion
    And a new list of questions for policy number 2 should be revealed below
    When I enter the policy 2 following data:
      | policy number | commencing date | policy holder name | insurer | replacing cover | cover type | death cover |
      | 12            | 02031972        | testsecond         | aia     | yes             | Death      | 10000       |
    When I want to add another policy
    Then the policy number 2 should collapse into an accordion
    And a new list of questions for policy number 3 should be revealed below
    When I remove policy number 2 with no confirmation
    Then this policy should be not removed from the policy page

