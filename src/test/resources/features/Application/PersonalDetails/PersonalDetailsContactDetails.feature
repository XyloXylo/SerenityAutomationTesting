Feature: Personal details contact details
  In order to continue with the application
  As authenticated member
  I want to enter/view my personal details

  Background:
    Given I am on Home Page

  @LQA279 @authenticated @bothform @saldi
  Scenario Outline: Validate the Application questions data on Personal Details - contact
    Given I am an authenticated member
    And the following user data:
      | Age | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee |
      | 54  | Female | 55000:Per year | Actor      | No                               | No                |
    And with the following death cover data:
| death cover type | death current cover | death calculated cover | quoted death cover amount   |
| units            | 170000              | 155000                 | <quoted death cover amount> |
    And I am on Application personal details contact details page
    When I enter the following contact data:
| contact method | best time to contact |
| <contactBy>    | <contactTime>        |
    When I try to go to next page on application
#    Then Residency page should be <display> on application
    Then this field Country of birth is <display> on residency page
  Examples:
| scenario | contactBy | contactTime | quoted death cover amount | display       |
| long     | phone     | 8am - 11am  | 4122300                   | displayed     |
| short    | phone     | 11am - 2pm  | 27300                     | not displayed |
#| phone     | 2pm - 5pm            | 4122300                   | displayed     |
#| phone     | 5pm - 8pm            | 27300                     | not displayed |
#| phone     | Anytime (8am to 8pm) | 4122300                   | displayed     |
#| mobile    | 8am - 11am           | 27300                     | not displayed |
#| mobile    | 11am - 2pm           | 4122300                   | displayed     |
#| mobile    | 2pm - 5pm            | 27300                     | not displayed |
#| mobile    | 5pm - 8pm            | 4122300                   | displayed     |
#| mobile    | Anytime (8am to 8pm) | 27300                     | not displayed |

  @LQA279 @authenticated @bothform @saldi
  Scenario Outline: Validate the time to contact dropdown values
    Given I am an authenticated member
    And the following user data:
      | Age | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee |
      | 54  | Female | 55000:Per year | Actor      | No                               | No                |
    And with the following death cover data:
| death cover type | death current cover | death calculated cover | quoted death cover amount   |
| units            | 170000              | 155000                 | <quoted death cover amount> |
    And I am on Application personal details contact details page
    Then the following contact times should be listed:
      | 8am - 11am           |
      | 11am - 2pm           |
      | 2pm - 5pm            |
      | 5pm - 8pm            |
      | Anytime (8am to 8pm) |
    Examples:
| scenario | quoted death cover amount |
| long     | 4122300                   |
| short    | 27300                     |

  @LQA64 @authenticated @bothform @saldi @pending
  Scenario Outline: Validate user should be able to save progress on Contact details
    Given I am an authenticated member
    And the following user data:
      | Age | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee |
      | 54  | Female | 55000:Per year | Actor      | No                               | No                |
    And with the following death cover data:
      | death cover type | death current cover | death calculated cover | quoted death cover amount   |
      | units            | 170000              | 155000                 | <quoted death cover amount> |
    And I am on Application personal details contact details page
    And I enter the following contact data:
      | contact method | best time to contact |
      | <contactBy>    | <contactTime>        |
    When I want to save my application progress till now
    Then Your application has been saved modal for application should be displayed
    And this text is displayed on Save application modal You've successfully saved your application. Reference number
    Then the application contact details data should be saved in backend
    Examples:
      | scenario | contactBy | contactTime | quoted death cover amount | display       |
      | long     | phone     | 8am - 11am  | 4122300                   | displayed     |
      | short    | phone     | 11am - 2pm  | 27300                     | not displayed |