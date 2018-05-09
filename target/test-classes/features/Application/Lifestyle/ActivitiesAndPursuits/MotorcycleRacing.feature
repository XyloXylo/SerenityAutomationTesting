Feature: Motorcycle racing
  In order to continue with my application
  As authenticated member
  I want to enter the details of any Motorcycle racing activities I participate in

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
| contact method | best time to contact | resident | other cover | claimed benefits | has advisor | racing car type | horse involved | fly outside australia | aviation private form | aviation commercial form | hours flown | expected hours to fly | currently kayak | sailing type                | no of bungy jumps | cycle-racing participation | no of parachuting jumps |
| phone          | 11am - 2pm           | yes      | no          | no               | no          | vintage         | no             | yes                   | Gyrocopter            | Rotary                   | 23          | 55                    | no              | Pleasure (Inshore/Offshore) | One off jump      | no                         | One-off jump            |
    And with the following rallying data:
      | license type | classification | make model | engine size | top speed | event type               | event count | event participation | any accident |
      | car          | midsize        | audi       | 3.0 litre   | 300       | raceOmania, RacingFreaks | 3           | no                  | no           |
    And with the following trail bike data:
      | compete | make model | engine size | top speed | event type               | event participation   | participation details            |
      | yes     | honda      | 2000cc      | 300       | raceOmania, RacingFreaks | <event participation> | I am doing raceOmania every year |
    And with the following hand gliding data:
      | member | number of flights | participate | participation details |
      | yes    | 2                 | yes         | winner                |
    And I am on Application lifestyle motorcycle racing page

  @LQA411 @authenticated @shortform @saldi
  Scenario: Validate the Application questions data on Activities & pursuits - motorcycle racing
    Then the lifestyle text asked is Specify type of motorcycle you race (including engine capacity).
    Then the lifestyle motorcycle-racing question asked is Specify type of events participated in (e.g speedway).
    Then the lifestyle motorcycle-racing text asked is Do you currently (or intend to) take part in:
    Then the lifestyle motorcycle-racing question asked is Have you ever been involved in any accident/mishap whilst participating in this activity? If yes, details please.

  @LQA411 @authenticated @shortform @saldi
  Scenario: Validate user should be able to add multiple motorbikes for motorcycle racing
    When I enter the motorbike 1 following data:
      | make model | engine size | top speed |
      | honda      | 2000cc      | 300       |
    Then I should not be able to remove this motorbike
    When I want to add another motorbike
    Then the motorbike number 1 should collapse into an accordion
    And a new list of questions for motorbike number 2 should be revealed below
    When I enter the motorbike 2 following data:
      | make model | engine size | top speed |
      | yamaha     | 2400cc      | 350       |
    When I want to add another motorbike
    Then the motorbike number 2 should collapse into an accordion
    And a new list of questions for motorbike number 3 should be revealed below

  @LQA411 @authenticated @shortform @saldi
  Scenario Outline: Validate user should be able to remove multiple motorbikes based on confirmation for motorcycle racing
    When I enter the motorbike 1 following data:
      | make model | engine size | top speed |
      | honda      | 2000cc      | 300       |
    When I want to add another motorbike
    Then the motorbike number 1 should collapse into an accordion
    And a new list of questions for motorbike number 2 should be revealed below
    When I enter the motorbike 2 following data:
      | make model | engine size | top speed |
      | yamaha     | 2400cc      | 350       |
    When I want to add another motorbike
    Then the motorbike number 2 should collapse into an accordion
    And a new list of questions for motorbike number 3 should be revealed below
    When I remove motorbike number 2 with <confirm>
    Then this motorbike should be <removal> from the motorcycle racing page
    Examples:
      | confirm         | removal     |
      | confirmation    | removed     |
      | no confirmation | not removed |

  @LQA411 @authenticated @shortform @saldi
  Scenario: Validate user should be able to add maximum of 5 motorbikes for motorcycle racing
    When I enter the motorbike 1 following data:
      | make model | engine size | top speed |
      | honda      | 2000cc      | 300       |
    When I want to add another motorbike
    When I enter the motorbike 2 following data:
      | make model | engine size | top speed |
      | honda      | 2000cc      | 300       |
    When I want to add another motorbike
    When I enter the motorbike 3 following data:
      | make model | engine size | top speed |
      | honda      | 2000cc      | 300       |
    When I want to add another motorbike
    When I enter the motorbike 4 following data:
      | make model | engine size | top speed |
      | honda      | 2000cc      | 300       |
    When I want to add another motorbike
    When I enter the motorbike 5 following data:
      | make model | engine size | top speed |
      | honda      | 2000cc      | 300       |
    Then I should not be able to add any further motorbikes


  @LQA411 @authenticated @shortform @saldi
  Scenario Outline: Validate user should be able to go to next page from motorcycle page
    When I enter the motorcycle racing following data:
| make model | engine size | top speed | event type               | event participation   | participation details            | any accident   | accident details                     |
| honda      | 2000cc      | 300       | raceOmania, RacingFreaks | <event participation> | I am doing raceOmania every year | <any accident> | in 2002, over the steep hills in sfo |
    And I try to go to next page on application
    Then motorcycle racing page is not displayed anymore
    Examples:
| event participation | any accident |
| yes                 | yes          |
| no                  | no           |