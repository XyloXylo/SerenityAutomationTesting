Feature: Aviation private
  In order to continue with my application
  As authenticated member
  I want to enter the details of any private aviation activities I participate in

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
      | contact method | best time to contact | resident | other cover | claimed benefits | has advisor |racing car type|horse involved|
      | phone          | 11am - 2pm           | yes      | no          | no               | no          |vintage        |no            |
    And with the following rallying data:
      | license type | classification | make model | engine size | top speed | event type               | event count | event participation | any accident |
      | car          | midsize        | audi       | 3.0 litre   | 300       | raceOmania, RacingFreaks | 3           | no                  | no           |
    And with the following trail bike data:
      | compete | make model | engine size | top speed | event type               | event participation   | participation details            |
      | yes     | honda      | 2000cc      | 300       | raceOmania, RacingFreaks | <event participation> | I am doing raceOmania every year |
    And with the following hand gliding data:
      | member | number of flights | participate | participation details |
      | yes    | 2                 | yes         | winner                |
    And I am on Application lifestyle aviation private page

  @LQA381 @authenticated @shortform @saldi
  Scenario: Validate the Application questions data on Activities & pursuits - aviation private
    Then the lifestyle question asked is Do you fly outside Australia?
    And the lifestyle question asked is What form of aviation do you participate in or intend to participate in?
    And these are the lifestyle aviation-private options given to me to choose from Fixed wing,Rotary,Microlights/ultralights/powered hang gliding,Gyrocopter,Hot air balloon,Aerobatics/Stunt flying/Exhibitions

  @LQA381 @authenticated @shortform @saldi
  Scenario: Validate selected options should give only corresponding reflexives on Aviation Private
    When I do fly outside Australia for private aviation
    When I choose any private aviation form corresponding question is asked to me
| aviation form                                | reflexive                                                  | field heading                                        |
| Fixed wing                                   | How many hours did you fly in the past 12 months?          | Fixed wing (hours)                                   |
| Rotary                                       | How many hours did you fly in the past 12 months?          | Rotary (hours)                                       |
| Rotary                                       | How many hours do you expect to fly in the next 12 months? | Rotary (hours)                                       |
| Microlights/ultralights/powered hang gliding | How many hours did you fly in the past 12 months?          | Microlights/ultralights/powered hang gliding (hours) |
| Microlights/ultralights/powered hang gliding | How many hours do you expect to fly in the next 12 months? | Microlights/ultralights/powered hang gliding (hours) |
| Hot air balloon                              | How many hours did you fly in the past 12 months?          | Hot air Balloons (hours)                             |
| Hot air balloon                              | How many hours do you expect to fly in the next 12 months? | Hot air Balloons (hours)                              |

  @LQA381 @authenticated @shortform @saldi
  Scenario: Validate user is presented with reflexive on a new page is selected aviation form is Aerobatics
    When I do fly outside Australia for private aviation
    When I participate in Aerobatics/Stunt flying/Exhibitions aviation private
    And I try to go to next page on application
    Then the lifestyle text asked is Tell us more about Aerobatics/ Stunt flying/ Exhibitions
    And the lifestyle question asked is Provide details of the aerobatics/stunt flying/exhibitions participated in.

  @LQA381 @authenticated @shortform @saldi
  Scenario: Validate user should be able to go to next page from aviation private page
    When I enter the private aviation following data:
| aviation private form |fly outside australia|
| Gyrocopter            |yes                  |
    And I try to go to next page on application
    Then aviation private page is not displayed anymore