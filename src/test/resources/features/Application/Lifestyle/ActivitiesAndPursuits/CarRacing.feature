Feature: tbc
  In order to complete the application and provided all required detail
  As authenticated member
  I want to be presented with the required questions and sections

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
      | contact method | best time to contact | resident | other cover | claimed benefits | has advisor |
      | phone          | 11am - 2pm           | yes      | no          | no               | no          |
    And with the following lifestyle activities:
| Aviation            | Extreme sports        | Combat sports | Motor sports       | Riding                     | Snow sports   | Team sports | Water sports          |
| aviation private    | hand gliding          | Martial Arts  | Motor racing       | Cycling                    | Snow boarding | Football    | Long-distance sailing |
| aviation commercial | Parachuting/Skydiving | Boxing        | Car racing         | Horse riding/Equestrianism | Snow skiing   |             | Scuba diving          |
|                     | Mountaineering        | Wrestling     | Motorcycle racing  | Mountain Biking            |               |             | Powerboat racing      |
|                     | Bungy jumping         |               | Rallying(off-road) |                            |               |             | Canoeing/Kayaking     |
|                     | Rock Climbing         |               | Trail BIke riding  |                            |               |             | Water skiing          |
|                     | Base jumping          |               |                    |                            |               |             | White water rafting   |
|                     | Sailboarding          |               |                    |                            |               |             |                       |



  @LQA370 @authenticated @shortform @saldi
  Scenario: Validate the Application questions data on Activities & pursuits - car racing
    Given I am on Application lifestyle car racing page
    Then the lifestyle question asked is Select the type of car racing you participate in.
    When I participate in vintage car racing
    And I try to go to next page on application
    Then car racing page is not displayed anymore

  @LQA370 @authenticated @shortform @saldi
  Scenario: Validate user should be able to add multiple license
    Given I am on Application lifestyle car racing page
    Then the lifestyle question asked is Select the type of car racing you participate in.
    When I participate in other car racing
    And I try to go to next page on application
    Then the lifestyle text asked is Do you have a motor racing license (e.g CAMS, ANDRA)? If Yes, please state classification.
    When I enter the license 1 following data:
| license type | classification |
| car          | midsize        |
    When I want to add another license
    Then the license number 1 should collapse into an accordion
    And a new list of questions for license number 2 should be revealed below
    When I enter the license 2 following data:
| license type | classification |
| truck        | large size     |
    When I want to add another license
    Then the license number 2 should collapse into an accordion
    And a new list of questions for license number 3 should be revealed below

  @LQA393 @authenticated @shortform @saldi
  Scenario Outline: Validate user should be able to add multiple vehicles
    Given I am on Application lifestyle car racing page
    When I participate in other car racing
    And I try to go to next page on application
    Then the lifestyle text asked is Specify type of vehicle driven, including engine size.
    When I enter the vehicle 1 following data:
| make model | engine size | top speed |
| audi       | 3.0 litre   | 300       |
    When I want to add another vehicle
    Then the vehicle number 1 should collapse into an accordion
    And a new list of questions for vehicle number 2 should be revealed below
    When I enter the vehicle 2 following data:
| make model | engine size | top speed |
| bmw        | 3.5 litre   | 350       |
    When I want to add another vehicle
    Then the vehicle number 2 should collapse into an accordion
    And a new list of questions for vehicle number 3 should be revealed below

  @LQA370 @authenticated @shortform @saldi
  Scenario Outline: Validate user should be able to remove multiple licenses based on confirmation
    Given I am on Application lifestyle car racing page
    When I participate in other car racing
    And I try to go to next page on application
    When I enter the license 1 following data:
      | license type | classification |
      | car          | midsize        |
    When I want to add another license
    Then the license number 1 should collapse into an accordion
    And a new list of questions for license number 2 should be revealed below
    When I enter the license 2 following data:
      | license type | classification |
      | truck        | large size     |
    When I want to add another license
    Then the license number 2 should collapse into an accordion
    And a new list of questions for license number 3 should be revealed below
    When I remove license number 2 with <confirm>
    Then this license should be <removal> from the car racing page
    Examples:
| quoted death cover amount | confirm         | removal     |
| 27300                     | confirmation    | removed     |
| 27300                     | no confirmation | not removed |

  @LQA393 @authenticated @shortform @saldi
  Scenario Outline: Validate user should be able to remove multiple vehicles based on confirmation
    Given I am on Application lifestyle car racing page
    When I participate in other car racing
    And I try to go to next page on application
    Then the lifestyle text asked is Specify type of vehicle driven, including engine size.
    When I enter the vehicle 1 following data:
      | make model | engine size | top speed |
      | audi       | 3.0 litre   | 300       |
    When I want to add another vehicle
    Then the vehicle number 1 should collapse into an accordion
    And a new list of questions for vehicle number 2 should be revealed below
    When I enter the vehicle 2 following data:
      | make model | engine size | top speed |
      | bmw        | 3.5 litre   | 350       |
    When I want to add another vehicle
    Then the vehicle number 2 should collapse into an accordion
    And a new list of questions for vehicle number 3 should be revealed below
    When I remove vehicle number 2 with <confirm>
    Then this vehicle should be <removal> from the car racing page
    Examples:
      | quoted death cover amount | confirm         | removal     |
      | 27300                     | confirmation    | removed     |
      | 27300                     | no confirmation | not removed |

  @LQA370 @authenticated @shortform @saldi
  Scenario: Validate user should not be able to remove the if there's only single license
    Given I am on Application lifestyle car racing page
    When I participate in other car racing
    And I try to go to next page on application
    When I enter the license 1 following data:
      | license type | classification |
      | car          | midsize        |
    Then I should not be able to remove this license

  @LQA393 @authenticated @shortform @saldi
  Scenario: Validate user should not be able to remove the if there's only single vehicle
    Given I am on Application lifestyle car racing page
    When I participate in other car racing
    And I try to go to next page on application
    Then the lifestyle text asked is Specify type of vehicle driven, including engine size.
    When I enter the vehicle 1 following data:
      | make model | engine size | top speed |
      | audi       | 3.0 litre   | 300       |
    Then I should not be able to remove this vehicle

  @manual @LQA393 @LQA370 @LQA377 @LQA388
  Scenario: Once I add the 2nd license or vehicle or motorbike or voyage, the remove button should reappear

  @LQA370 @authenticated @shortform @saldi
  Scenario: Validate user should be able to add maximum of 5 license
    Given I am on Application lifestyle car racing page
    When I participate in other car racing
    And I try to go to next page on application
    When I enter the license 1 following data:
      | license type | classification |
      | car          | midsize        |
    When I want to add another license
    When I enter the license 2 following data:
      | license type | classification |
      | truck        | large size     |
    When I want to add another license
    When I enter the license 3 following data:
      | license type | classification |
      | car          | midsize        |
    When I want to add another license
    When I enter the license 4 following data:
      | license type | classification |
      | car          | midsize        |
    When I want to add another license
    When I enter the license 5 following data:
      | license type | classification |
      | car          | midsize        |
    Then I should not be able to add any further licenses

  @LQA393 @authenticated @shortform @saldi
  Scenario: Validate user should be able to add maximum of 5 vehicles
    Given I am on Application lifestyle car racing page
    When I participate in other car racing
    And I try to go to next page on application
    When I enter the vehicle 1 following data:
      | make model | engine size | top speed |
      | audi       | 3.0 litre   | 300       |
    When I want to add another vehicle
    When I enter the vehicle 2 following data:
      | make model | engine size | top speed |
      | audi       | 3.0 litre   | 300       |
    When I want to add another vehicle
    When I enter the vehicle 3 following data:
      | make model | engine size | top speed |
      | audi       | 3.0 litre   | 300       |
    When I want to add another vehicle
    When I enter the vehicle 4 following data:
      | make model | engine size | top speed |
      | audi       | 3.0 litre   | 300       |
    When I want to add another vehicle
    When I enter the vehicle 5 following data:
      | make model | engine size | top speed |
      | audi       | 3.0 litre   | 300       |
    Then I should not be able to add any further vehicles

  @LQA368 @authenticated @shortform @saldi
  Scenario: Validate the Application questions data on Activities & pursuits - car racing events page 2
    Given I am on Application lifestyle car racing page
    Then the lifestyle question asked is Select the type of car racing you participate in.
    When I participate in other car racing
    And I try to go to next page on application
    When I enter the car racing 1 following data:
      | license type | classification |make model | engine size | top speed |
      | car          | midsize        |audi       | 3.0 litre   | 300       |
    And I try to go to next page on application
    Then the lifestyle text asked is Tell us more about the events you participate in.
    And the lifestyle question asked is Specify type of events participated in (e.g speedway).
    Then the lifestyle text asked is How many events do you participate in per year?
    Then the lifestyle text asked is Do you currently, or intend to take part in:
    And the lifestyle question asked is Have you ever been involved in any accident/mishap whilst participating in this activity?

  @LQA368 @authenticated @shortform @saldi
  Scenario Outline: Validate user should be able to go to Rallying page from car racing events page 2
    Given I am on Application lifestyle car racing page
    Then the lifestyle question asked is Select the type of car racing you participate in.
    When I participate in other car racing
    And I try to go to next page on application
    When I enter the car racing 1 following data:
      | license type | classification |make model | engine size | top speed |
      | car          | midsize        |audi       | 3.0 litre   | 300       |
    And I try to go to next page on application
    When I enter the car events racing following data:
| event type               | event count | event participation   | participation details            | any accident   | accident details                     |
| raceOmania, RacingFreaks | 3           | <event participation> | I am doing raceOmania every year | <any accident> | in 2002, over the steep hills in sfo |
    And I try to go to next page on application
    Then car racing page is not displayed anymore
    Examples:
| quoted death cover amount | event participation | any accident |
| 27300                     | no                  | no           |
| 27300                     | yes                 | yes          |