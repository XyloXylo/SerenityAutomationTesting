Feature: Save quote
  In order to view it next time I'm logged in
  As a authenticated member
  I want to have the ability to save my application

  Background:
    Given I am on Home Page

  @LQA63 @authenticated @saldi
  Scenario Outline: Validate quote data is saved from quote summary for all cover types
    Given I am an authenticated member
    And the following user data:
      | Age   | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee |
      | <Age> | Female | 55000:Per year | Actor      | No                               | No                |
    And with the following death cover data:
| death cover type   | death current cover | death calculated cover | quoted death cover amount   |
| <death cover type> | 0                   | 155000                 | <quoted death cover amount> |
    And with the following tpd cover data:
| tpd cover type   | tpd current cover | tpd calculated cover | quoted tpd cover amount   |
| <tpd cover type> | 0                 | 155000               | <quoted tpd cover amount> |
    And with the following ip cover data:
| ip current cover | ip calculated cover | quoted ip cover amount   |
| 0                | 155000              | <quoted ip cover amount> |
    And I am on Quote Summary page
    When I update the ip waiting period to 90 days
    And I save the quote from quote summary
    Then Your quote has been saved modal should be displayed
    And this text is displayed on Save quote modal You've successfully saved your quote. Reference number
#    Then the quote data should be saved in backend
    Examples:
| Age | death cover type | quoted death cover amount | tpd cover type | quoted tpd cover amount | quoted ip cover amount |
| 54  | units            | 4122300                   | units          | 327600                  | 3800                   |
| 54  | fixed            | 14000                     | fixed          | 15000                   | 950                    |
| 54  | fixed            | 14000                     | units          | 327600                  | 950                    |


  @LQA271 @LQA352 @AutoSave-Quote @authenticated @psy  @LQA412
  Scenario Outline: AutoSave quote-Validate quote data is saved when Apply cover button is clicked
    Given I am an authenticated member
    And the following user data:
      | Age   | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee |
      | <Age> | Female | 55000:Per year | Actor      | No                               | No                |
    And with the following death cover data:
      | death cover type   | death current cover | death calculated cover | quoted death cover amount   |
      | <death cover type> | 0                   | 155000                 | <quoted death cover amount> |
    And with the following tpd cover data:
      | tpd cover type   | tpd current cover | tpd calculated cover | quoted tpd cover amount   |
      | <tpd cover type> | 0                 | 155000               | <quoted tpd cover amount> |
    And with the following ip cover data:
      | ip current cover | ip calculated cover | quoted ip cover amount   |
      | 0                | 155000              | <quoted ip cover amount> |
    And I am on Quote Summary page
    When I update the ip waiting period to 90 days
    And I click on Apply for Cover from quote summary
    Then I should see Before we begin your application modal open
    And Verify Auto Save message  You've successfully saved your quote. Reference number displayed
#    Then the quote data should be saved in backend
    Examples:
      | Age | death cover type | quoted death cover amount | tpd cover type | quoted tpd cover amount | quoted ip cover amount |
      | 54  | units            | 4122300                   | units          | 327600                  | 3800                   |
      | 44  | fixed            | 14000                     | fixed          | 15000                   | 950                    |
      | 64  | fixed            | 14000                     | units          | 327600                  | 950                    |