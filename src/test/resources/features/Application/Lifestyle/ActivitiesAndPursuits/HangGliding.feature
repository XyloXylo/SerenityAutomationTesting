Feature: Hang gliding
  In order to continue with my application
  As authenticated member
  I want to enter the details of any hang gliding activities I participate in

  Background:
    Given I am on Home Page
    And I am an authenticated member

  @LQA386 @authenticated @shortform @saldi
  Scenario Outline: Validate the Application questions data on Activities & pursuits - hang gliding
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
    And I am on Application lifestyle hang gliding page
    And the lifestyle question asked is Are you a member of a hang-gliding club or organisation?
    And the lifestyle question asked is Advise the number of hang-gliding flights per annum.
    Then the lifestyle text asked is Do you currently (or intend to) take part in
    Examples:
      | quoted death cover amount |
      | 27300                     |


  @LQA386 @authenticated @shortform @saldi
  Scenario Outline: Validate user should be able to go to next page from hand gliding page
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
| compete | make model | engine size | top speed | event type               | event participation | participation details            |
| yes     | honda      | 2000cc      | 300       | raceOmania, RacingFreaks | no                  | I am doing raceOmania every year |
    And I am on Application lifestyle hang gliding page
    And I enter the hand gliding following data:
| member   | number of flights | participate     | participation details |
| <member> | 3                 | <participation> | winner                |
    And I try to go to next page on application
    Then hand gliding page is not displayed anymore
    Examples:
| quoted death cover amount | participation | member |
| 27300                     | no            | yes    |
| 27300                     | yes           | no     |