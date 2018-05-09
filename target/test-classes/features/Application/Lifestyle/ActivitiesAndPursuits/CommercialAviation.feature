Feature: Commercial aviation
  In order to continue with my application
  As authenticated member
  I want to enter the details of any commercial aviation activities I participate in

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
| contact method | best time to contact | resident | other cover | claimed benefits | has advisor | racing car type | horse involved | fly outside australia | aviation private form |
| phone          | 11am - 2pm           | yes      | no          | no               | no          | vintage         | no             | yes                   | Gyrocopter            |
    And with the following rallying data:
      | license type | classification | make model | engine size | top speed | event type               | event count | event participation | any accident |
      | car          | midsize        | audi       | 3.0 litre   | 300       | raceOmania, RacingFreaks | 3           | no                  | no           |
    And with the following trail bike data:
      | compete | make model | engine size | top speed | event type               | event participation   | participation details            |
      | yes     | honda      | 2000cc      | 300       | raceOmania, RacingFreaks | <event participation> | I am doing raceOmania every year |
    And with the following hand gliding data:
      | member | number of flights | participate | participation details |
      | yes    | 2                 | yes         | winner                |
    And I am on Application lifestyle commercial aviation page

  @LQA379 @authenticated @shortform @saldi
  Scenario: Validate the Application questions data on Activities & pursuits - commercial aviation
    Then the lifestyle commercialAviation question asked is Do you fly outside Australia?
    And the lifestyle commercialAviation question asked is What form of aviation do you participate in or intend to participate in?
    And these are the lifestyle commercialAviation options given to me to choose from Fixed wing,Rotary,Agricultural,Aerobatics/Stunt flying/Exhibitions,Hot air Balloon
    Then the lifestyle text asked is Approximately, how many hours did you fly in the last 12 months?
    And the lifestyle question asked is Hours flown
    Then the lifestyle commercialAviation text asked is How many hours do you expect to fly in the next 12 months?
    And the lifestyle question asked is Hours expected

  @LQA379 @authenticated @shortform @saldi
  Scenario: Validate selected options should give only corresponding reflexives on Commercial Aviation
    When I do not fly outside Australia for commercial aviation
    When I participate in Aerobatics/Stunt flying/Exhibitions aviation commercial
    Then the lifestyle commercialAviation question asked is Provide details of the aerobatics/stunt flying/exhibitions participated in.

  @LQA379 @authenticated @shortform @saldi
  Scenario: Validate user should be able to go to next page from commercial aviation page
    When I enter the commercial aviation following data:
| fly outside australia | aviation commercial form | hours flown | expected hours to fly |
| yes                   | Rotary                   | 23          | 55                    |
    And I try to go to next page on application
    Then aviation commercial page is not displayed anymore