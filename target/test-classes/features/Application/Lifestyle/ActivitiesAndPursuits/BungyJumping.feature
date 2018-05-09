Feature: Bungy jumping
  In order to continue with my application
  As authenticated member
  I want to enter the details of any bungy jumping activities I participate in

  Background:
    Given I am on Home Page
    And I am an authenticated member
    Given the following user data:
      | Age | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee |
      | 54  | Female | 55000:Per year | Actor      | No                               | No                |
    And with the following death cover data:
| death cover type | death current cover | death calculated cover | quoted death cover amount |
| units            | 170000              | 155000                 | 27300                     |
    And with the following application data:
| contact method | best time to contact | resident | other cover | claimed benefits | has advisor | racing car type | horse involved | fly outside australia | aviation private form | aviation commercial form | hours flown | expected hours to fly | currently kayak | sailing type                |
| phone          | 11am - 2pm           | yes      | no          | no               | no          | vintage         | no             | yes                   | Gyrocopter            | Rotary                   | 23          | 55                    | no              | Pleasure (Inshore/Offshore) |
    And with the following rallying data:
      | license type | classification | make model | engine size | top speed | event type               | event count | event participation | any accident |
      | car          | midsize        | audi       | 3.0 litre   | 300       | raceOmania, RacingFreaks | 3           | no                  | no           |
    And with the following trail bike data:
      | compete | make model | engine size | top speed | event type               | event participation   | participation details            |
      | yes     | honda      | 2000cc      | 300       | raceOmania, RacingFreaks | <event participation> | I am doing raceOmania every year |
    And with the following hand gliding data:
      | member | number of flights | participate | participation details |
      | yes    | 2                 | yes         | winner                |
    And I am on Application lifestyle bungy jumping page

  @LQA382 @authenticated @shortform @saldi
  Scenario: Validate the Application questions data on Activities & pursuits - bungy jumping
    Then the lifestyle question asked is Specify the number of bungy jumps you complete per annum.
    And these are the lifestyle bungy-jumping options given to me to choose from One off jump,Less than or equal to 10,More than 10
    When I enter the bungy jumping following data:
| no of bungy jumps |
| One off jump      |
    And I try to go to next page on application
    Then bungy page is not displayed anymore