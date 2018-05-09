Feature: Cycling
  In order to continue with my application
  As authenticated member
  I want to enter the details of any cycling jumping activities I participate in

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
| contact method | best time to contact | resident | other cover | claimed benefits | has advisor | racing car type | horse involved | fly outside australia | aviation private form | aviation commercial form | hours flown | expected hours to fly | currently kayak | sailing type                | no of bungy jumps |
| phone          | 11am - 2pm           | yes      | no          | no               | no          | vintage         | no             | yes                   | Gyrocopter            | Rotary                   | 23          | 55                    | no              | Pleasure (Inshore/Offshore) | One off jump      |
    And with the following rallying data:
      | license type | classification | make model | engine size | top speed | event type               | event count | event participation | any accident |
      | car          | midsize        | audi       | 3.0 litre   | 300       | raceOmania, RacingFreaks | 3           | no                  | no           |
    And with the following trail bike data:
      | compete | make model | engine size | top speed | event type               | event participation   | participation details            |
      | yes     | honda      | 2000cc      | 300       | raceOmania, RacingFreaks | <event participation> | I am doing raceOmania every year |
    And with the following hand gliding data:
      | member | number of flights | participate | participation details |
      | yes    | 2                 | yes         | winner                |
    And I am on Application lifestyle cycling page

  @LQA436 @authenticated @shortform @saldi
  Scenario Outline: Validate the Application questions data on Activities & pursuits - cycling
    Then the lifestyle question asked is Do you participate in any cycle-racing/ competitive events?
    When I enter the cycling following data:
| cycle-racing participation |
| <participation>            |
    And I try to go to next page on application
    Then cycling page is not displayed anymore
    Examples:
| participation |
| yes           |
| no            |