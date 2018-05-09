Feature: Canoeing & kayaking
  In order to continue with my application
  As authenticated member
  I want to enter the details of any Canoeing/Kayaking activities I participate in

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
| contact method | best time to contact | resident | other cover | claimed benefits | has advisor | racing car type | horse involved | fly outside australia | aviation private form |aviation commercial form | hours flown | expected hours to fly |
| phone          | 11am - 2pm           | yes      | no          | no               | no          | vintage         | no             | yes                   | Gyrocopter            |Rotary                   | 23          | 55                    |
    And with the following rallying data:
      | license type | classification | make model | engine size | top speed | event type               | event count | event participation | any accident |
      | car          | midsize        | audi       | 3.0 litre   | 300       | raceOmania, RacingFreaks | 3           | no                  | no           |
    And with the following trail bike data:
      | compete | make model | engine size | top speed | event type               | event participation   | participation details            |
      | yes     | honda      | 2000cc      | 300       | raceOmania, RacingFreaks | <event participation> | I am doing raceOmania every year |
    And with the following hand gliding data:
      | member | number of flights | participate | participation details |
      | yes    | 2                 | yes         | winner                |
    And I am on Application lifestyle canoeing page

  @LQA385 @authenticated @shortform @saldi
  Scenario: Validate the Application questions data on Activities & pursuits - canoeing & kayaking
    Then the lifestyle question asked is Do you currently (or intend to)  kayak / canoe more than 1 km offshore?
    When I do kayak more than 1 km offshore
    Then the lifestyle text asked is Typical distance of your voyages
    And the lifestyle question asked is Kilometers
    And the lifestyle question asked is Provide the typical locations you canoe in. i.e. Ocean, river, lake

  @LQA385 @authenticated @shortform @saldi
  Scenario: Validate user should be able to go to next page from commercial kayaking page
    When I enter the kayaking following data:
| currently kayak | kilometers | canoe locations |
| yes             | 50         | Michigan        |
    And I try to go to next page on application
    Then canoeing page is not displayed anymore