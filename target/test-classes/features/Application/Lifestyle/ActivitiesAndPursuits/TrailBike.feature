Feature: Trail bike
  In order to continue with my application
  As authenticated member
  I want to enter the details of the Trail Bike riding activities I participate in

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
      | contact method | best time to contact | resident | other cover | claimed benefits | has advisor |racing car type|
      | phone          | 11am - 2pm           | yes      | no          | no               | no          |vintage        |
    And with the following rallying data:
      | license type | classification | make model | engine size | top speed | event type               | event count | event participation | any accident |
      | car          | midsize        | audi       | 3.0 litre   | 300       | raceOmania, RacingFreaks | 3           | no                  | no           |
    And I am on Application lifestyle trail bike page

  @LQA377 @authenticated @shortform @saldi
  Scenario: Validate the Application questions data on Activities & pursuits - trail bike, when user does compete
    And the lifestyle question asked is Do you compete in this activity?
    When my answer to compete in this trail bike activity is yes
    Then the lifestyle text asked is Specify the type of motorcycle you race
    And the lifestyle question asked is Specify the type of events participated in
    Then the lifestyle trail-bike text asked is Do you currently, or intend to take part in:

  @LQA377 @authenticated @shortform @saldi
  Scenario: Validate user should be able to add multiple motorbike
    When my answer to compete in this trail bike activity is yes
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

  @LQA377 @authenticated @shortform @saldi
  Scenario Outline: Validate user should be able to remove multiple motorbikes based on confirmation
    When my answer to compete in this trail bike activity is yes
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
    Then this motorbike should be <removal> from the trail bike page
    Examples:
      | confirm         | removal     |
      | confirmation    | removed     |
      | no confirmation | not removed |

  @LQA377 @authenticated @shortform @saldi
  Scenario: Validate user should not be able to remove if there's only single motorbike
    When my answer to compete in this trail bike activity is yes
    When I enter the motorbike 1 following data:
      | make model | engine size | top speed |
      | honda      | 2000cc      | 300       |
    Then I should not be able to remove this motorbike

  @LQA377 @authenticated @shortform @saldi
  Scenario: Validate user should be able to add maximum of 5 motorbikes
    When my answer to compete in this trail bike activity is yes
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

  @LQA377 @authenticated @shortform @saldi
  Scenario Outline: Validate user should be able to go to next page from trail bike page
    When my answer to compete in this trail bike activity is yes
    And I enter the trail bike following data:
      | make model | engine size | top speed |event type               | event participation   | participation details            |
      | honda      | 2000cc      | 300       |raceOmania, RacingFreaks | <event participation> | I am doing raceOmania every year |
    And I try to go to next page on application
    Then car racing page is not displayed anymore
    Examples:
      | event participation |
      | no                  |
      | yes                 |

  @LQA376 @authenticated @shortform @saldi
  Scenario: Validate the Application questions data on Activities & pursuits - trail bike, when user does not compete
    Given the lifestyle question asked is Do you compete in this activity?
    When my answer to compete in this trail bike activity is no
    Then the lifestyle text asked is How often do you trail bike ride per year
    And the lifestyle question asked is Provide details on locations where you trail bike ride. i.e. Terrain types
    And the lifestyle trail-bike question asked is Have you ever been involved in any accidents/mishap whilst participating in this activity?
    And the following frequency should be listed:
| Atleast once a year |
| Daily               |
| Weekly              |
| Monthly             |

  @LQA376 @authenticated @shortform @saldi
  Scenario Outline: Validate user should be able to go to next page from trail bike page 2
    When my answer to compete in this trail bike activity is no
    And I enter the trail bike following data:
| frequency | locations           | accident   | accident details      |
| Weekly    | STEPPE,HILL, DESERT | <accident> | autopilot mode failed |
    And I try to go to next page on application
    Then car racing page is not displayed anymore
    Examples:
| accident |
| no       |
| yes      |