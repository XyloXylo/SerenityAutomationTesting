Feature: Horse riding
  In order to continue with my application
  As authenticated member
  I want to enter the details of any horse riding activities I participate in

  Background:
    Given I am on Home Page
    And I am an authenticated member

  @LQA387 @authenticated @shortform @saldi
  Scenario Outline: Validate the Application questions data on Activities & pursuits - horse riding
    Given the following user data:
      | Age | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee |
      | 54  | Female | 55000:Per year | Actor      | No                               | No                |
    And with the following death cover data:
      | death cover type | death current cover | death calculated cover | quoted death cover amount   |
      | units            | 170000              | 155000                 | <quoted death cover amount> |
    And with the following application data:
| contact method | best time to contact | resident | other cover | claimed benefits | has advisor |racing car type|
| phone          | 11am - 2pm           | yes      | no          | no               | no          |vintage        |
    And with the following rallying data:
| license type | classification | make model | engine size | top speed | event type               | event count | event participation | any accident |
| car          | midsize        | audi       | 3.0 litre   | 300       | raceOmania, RacingFreaks | 3           | no                  | no           |
    And with the following trail bike data:
| compete | make model | engine size | top speed | event type               | event participation   | participation details            |
| yes     | honda      | 2000cc      | 300       | raceOmania, RacingFreaks | <event participation> | I am doing raceOmania every year |
    And with the following hand gliding data:
| member | number of flights | participate | participation details |
| yes    | 2                 | yes         | winner                |
    And I am on Application lifestyle horse riding page
    Then the lifestyle text asked is Does your horse riding involve:
    And these rows are displayed for this lifestyle accordion Showjumping,Gymkhana vaulting,Endurance events,Steeplechasing
    When I enter the horse riding following data:
| horse involved   |
| <horse involved> |
    And I try to go to next page on application
    Then horse riding page is not displayed anymore
    Examples:
| quoted death cover amount | horse involved |
| 27300                     | yes            |
| 27300                     | no             |