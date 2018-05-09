Feature: Long distance sailing
  In order to continue with my application
  As authenticated member
  I want to enter the details of any Long Distance Sailing activities I participate in

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
| contact method | best time to contact | resident | other cover | claimed benefits | has advisor | racing car type | horse involved | fly outside australia | aviation private form |aviation commercial form | hours flown | expected hours to fly |currently kayak|
| phone          | 11am - 2pm           | yes      | no          | no               | no          | vintage         | no             | yes                   | Gyrocopter            |Rotary                   | 23          | 55                    |no             |
    And with the following rallying data:
      | license type | classification | make model | engine size | top speed | event type               | event count | event participation | any accident |
      | car          | midsize        | audi       | 3.0 litre   | 300       | raceOmania, RacingFreaks | 3           | no                  | no           |
    And with the following trail bike data:
      | compete | make model | engine size | top speed | event type               | event participation   | participation details            |
      | yes     | honda      | 2000cc      | 300       | raceOmania, RacingFreaks | <event participation> | I am doing raceOmania every year |
    And with the following hand gliding data:
      | member | number of flights | participate | participation details |
      | yes    | 2                 | yes         | winner                |
    And I am on Application lifestyle sailing page

  @LQA388 @authenticated @shortform @saldi
  Scenario: Validate the Application questions data on Activities & pursuits - long distance sailing
    Then the lifestyle question asked is Select the type of sailing you participate in:
    And these are the lifestyle longDistanceSailing options given to me to choose from Pleasure (Inshore/Offshore),Pleasure (Trans-ocean),Racing (Offshore),Racing (Trans-ocean)
    When I choose any sailing type corresponding question is asked to me
| sailing type           | reflexive       | field heading             |
| Pleasure (Trans-ocean) | Please provide: | Name of race/ race series |
| Racing (Offshore)      | Please provide: | Name of race/ race series |
| Racing (Trans-ocean)   | Please provide: | Name of race/ race series |
#    When I participate in Pleasure  (Trans-ocean) sailing type
#    Then the lifestyle accordion asked is Please provide:
#    And the lifestyle question asked is Name of race/ race series

  @LQA388 @authenticated @shortform @saldi
  Scenario Outline: Validate user should be able to add & remove multiple long distance sailing voyages
    When I participate in <sailing type> sailing type
    When I enter the voyage 1 following data:
| crew number | voyage duration | voyage location details |
| 33          | 2               | phillipines             |
    Then I should not be able to remove this voyage
    When I want to add another voyage
    Then the voyage number 1 should collapse into an accordion
    And a new list of questions for voyage number 2 should be revealed below
    When I enter the voyage 2 following data:
      | crew number | voyage duration | voyage location details |
      | 52          | 5               | phillipines             |
    When I want to add another voyage
    Then the voyage number 2 should collapse into an accordion
    And a new list of questions for voyage number 3 should be revealed below
    When I remove voyage number 2 with <confirm>
    Then this voyage should be <removal> from the long distance sailing page
    Examples:
| sailing type           |confirm         | removal     |
| Pleasure (Trans-ocean) |confirmation    | removed     |
| Racing (Offshore)      |no confirmation | not removed |
| Racing (Trans-ocean)   |confirmation    | removed     |

  @LQA388 @authenticated @shortform @saldi
  Scenario: Validate user should be able to add maximum of 5 voyages
    When I participate in Racing (Offshore) sailing type
    When I enter the voyage 1 following data:
      | crew number | voyage duration | voyage location details |
      | 33          | 2               | phillipines             |
    When I want to add another voyage
    When I enter the voyage 2 following data:
      | crew number | voyage duration | voyage location details |
      | 33          | 2               | phillipines             |
    When I want to add another voyage
    When I enter the voyage 3 following data:
      | crew number | voyage duration | voyage location details |
      | 33          | 2               | phillipines             |
    When I want to add another voyage
    When I enter the voyage 4 following data:
      | crew number | voyage duration | voyage location details |
      | 33          | 2               | phillipines             |
    When I want to add another voyage
    When I enter the voyage 5 following data:
      | crew number | voyage duration | voyage location details |
      | 33          | 2               | phillipines             |
    Then I should not be able to add any further voyages

  @LQA388 @authenticated @shortform @saldi
  Scenario: Validate user should be able to go to next page from long distance sailing page
    When I enter the sailing following data:
| sailing type      | crew number | voyage duration | voyage location details | race series |
| Racing (Offshore) | 50          | 2               | usa                     | pro         |
    And I try to go to next page on application
    Then sailing page is not displayed anymore