Feature: Personal details other cover
  In order to continue with the application
  As authenticated member
  I want to enter the details of any cover I have had

  Background:
    Given I am on Home Page
    And I am an authenticated member
    And the following user data:
      | Age | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee |contact method | best time to contact | resident |other cover|
      | 54  | Female | 55000:Per year | Actor      | No                               | No                |phone          | 11am - 2pm           | yes      |yes        |
    And with the following death cover data:
      | death cover type | death current cover | death calculated cover | quoted death cover amount |
      | units            | 170000              | 155000                 | 4122300                   |
    And I am on Application personal details policy page
    And I enter the policy 1 following data:
      | policy number | commencing date | policy holder name | insurer | replacing cover | cover type   |death cover|ip cover|tpd cover|waiting period|benefit period|
      | 123456        | 01021981        | test               | aia     | no              | Death,IP,TPD |300000     |200000  |500000   |30 days       |To age 67     |
    And I try to go to next page on application
    And Special Terms page should be displayed on application

  @LQA315 @authenticated @longform @saldi
  Scenario: Validate the Application questions data on Personal Details - special terms
    Then the personal details question asked is Have you ever been declined, deferred or accepted on special terms for life, disability or trauma insurance?
    When I select my special terms as no
    And I try to go to next page on application
    Then claims page is displayed

  @LQA315 @authenticated @longform @saldi
  Scenario: Validate the dropdown values for type of cover and type of decision
    When I select my special terms as yes
    Then the following type of cover should be listed:
| Death cover                |
| Disability (TPD or IP/SCI) |
| Trauma                     |
    And the following type of decision should be listed:
| Declined                  |
| Deferred                  |
| Accepted on special terms |

  @LQA315 @authenticated @longform @saldi
  Scenario: Validate user should be able to add special terms
    When I select my special terms as yes
    When I enter the special terms 1 following data:
| cover type | decision type | decision year | reason for decision |
| Trauma     | Deferred      | 1971          | personal reasons    |
    When I try to go to next page on application
    Then claims page should be displayed on application

  @LQA315 @authenticated @longform @saldi
  Scenario: Validate user should be able to add multiple special terms
    When I select my special terms as yes
    When I enter the special terms 1 following data:
      | cover type | decision type | decision year | reason for decision |
      | Trauma     | Deferred      | 1971          | personal reasons    |
    When I want to add another special term
    Then the special term number 1 should collapse into an accordion
    And a new list of questions for special term number 2 should be revealed below
    When I enter the special terms 2 following data:
| cover type  | decision type | decision year | reason for decision |
| Death cover | Declined      | 1979          | personal reasons    |
    When I want to add another special term
    Then the special term number 2 should collapse into an accordion
    And a new list of questions for special term number 3 should be revealed below

  @LQA315 @authenticated @longform @saldi @LQA371
  Scenario: Validate user should be able to remove multiple special terms
    When I select my special terms as yes
    When I enter the special terms 1 following data:
      | cover type | decision type | decision year | reason for decision |
      | Trauma     | Deferred      | 1971          | personal reasons    |
    When I want to add another special term
    Then the special term number 1 should collapse into an accordion
    And a new list of questions for special term number 2 should be revealed below
    When I enter the special terms 2 following data:
      | cover type  | decision type | decision year | reason for decision |
      | Death cover | Declined      | 1979          | personal reasons    |
    When I want to add another special term
    Then the special term number 2 should collapse into an accordion
    And a new list of questions for special term number 3 should be revealed below
    When I remove special term number 2 with confirmation
    Then this term should be removed from the special term page

  @LQA315 @authenticated @longform @saldi @LQA371
  Scenario: Validate user should be able to revert if happen to click remove special term
    When I select my special terms as yes
    When I enter the special terms 1 following data:
      | cover type | decision type | decision year | reason for decision |
      | Trauma     | Deferred      | 1971          | personal reasons    |
    When I want to add another special term
    Then the special term number 1 should collapse into an accordion
    And a new list of questions for special term number 2 should be revealed below
    When I enter the special terms 2 following data:
      | cover type  | decision type | decision year | reason for decision |
      | Death cover | Declined      | 1979          | personal reasons    |
    When I want to add another special term
    Then the special term number 2 should collapse into an accordion
    And a new list of questions for special term number 3 should be revealed below
    When I remove special term number 2 with no confirmation
    Then this term should be not removed from the special term page

  @LQA371 @authenticated @longform @saldi
  Scenario: Validate user should not be able to remove the if there's only single special term
    Given I select my special terms as yes
    And I enter the special terms 1 following data:
      | cover type | decision type | decision year | reason for decision |
      | Trauma     | Deferred      | 1971          | personal reasons    |
    Then I should not be able to remove this special term

    @manual
    Scenario: Once I add the 2nd accordion, the remove button should reappear