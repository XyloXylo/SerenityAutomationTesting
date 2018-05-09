Feature: Rallying off road
  In order to continue with my application
  As authenticated member
  I want to enter the details of any off-road rallying activities I participate in

  Background:
    Given I am on Home Page
    And I am an authenticated member

  @LQA374 @authenticated @shortform @saldi
  Scenario Outline: Validate the Application questions data on Activities & pursuits - rallying off road
    Given the following user data:
      | Age | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee |
      | 54  | Female | 55000:Per year | Actor      | No                               | No                |
    And with the following death cover data:
      | death cover type | death current cover | death calculated cover | quoted death cover amount   |
      | units            | 170000              | 155000                 | <quoted death cover amount> |
    And with the following application data:
| contact method | best time to contact | resident | other cover | claimed benefits | has advisor |racing car type|
| phone          | 11am - 2pm           | yes      | no          | no               | no          |vintage        |
    And I am on Application lifestyle rallying page
    Then the lifestyle rallying text asked is Do you have a motor racing license (e.g CAMS, ANDRA)? If Yes, please state classification.
    Then the lifestyle rallying text asked is Specify type of vehicle driven, including engine size.
    Examples:
      | quoted death cover amount |
      | 27300                     |

  @LQA374 @authenticated @shortform @saldi
  Scenario Outline: Validate user should be able to add multiple license on rallying off road
    Given the following user data:
      | Age | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee |
      | 54  | Female | 55000:Per year | Actor      | No                               | No                |
    And with the following death cover data:
      | death cover type | death current cover | death calculated cover | quoted death cover amount   |
      | units            | 170000              | 155000                 | <quoted death cover amount> |
    And with the following application data:
      | contact method | best time to contact | resident | other cover | claimed benefits | has advisor |racing car type|
      | phone          | 11am - 2pm           | yes      | no          | no               | no          |vintage        |
    And I am on Application lifestyle rallying page
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
    Examples:
      | quoted death cover amount |
      | 27300                     |

  @LQA374 @authenticated @shortform @saldi
  Scenario Outline: Validate user should be able to add multiple vehicles on rallying
    Given the following user data:
      | Age | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee |
      | 54  | Female | 55000:Per year | Actor      | No                               | No                |
    And with the following death cover data:
      | death cover type | death current cover | death calculated cover | quoted death cover amount   |
      | units            | 170000              | 155000                 | <quoted death cover amount> |
    And with the following application data:
      | contact method | best time to contact | resident | other cover | claimed benefits | has advisor |racing car type|
      | phone          | 11am - 2pm           | yes      | no          | no               | no          |vintage        |
    And I am on Application lifestyle rallying page
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
    Examples:
      | quoted death cover amount |
      | 27300                     |


  @LQA374 @authenticated @shortform @saldi
  Scenario Outline: Validate user should be able to remove multiple licenses based on confirmation on rallying
    Given the following user data:
      | Age | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee |
      | 54  | Female | 55000:Per year | Actor      | No                               | No                |
    And with the following death cover data:
      | death cover type | death current cover | death calculated cover | quoted death cover amount   |
      | units            | 170000              | 155000                 | <quoted death cover amount> |
    And with the following application data:
      | contact method | best time to contact | resident | other cover | claimed benefits | has advisor |racing car type|
      | phone          | 11am - 2pm           | yes      | no          | no               | no          |vintage        |
    And I am on Application lifestyle rallying page
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

  @LQA374 @authenticated @shortform @saldi
  Scenario Outline: Validate user should be able to remove multiple vehicles based on confirmation on rallying
    Given the following user data:
      | Age | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee |
      | 54  | Female | 55000:Per year | Actor      | No                               | No                |
    And with the following death cover data:
      | death cover type | death current cover | death calculated cover | quoted death cover amount   |
      | units            | 170000              | 155000                 | <quoted death cover amount> |
    And with the following application data:
      | contact method | best time to contact | resident | other cover | claimed benefits | has advisor |racing car type|
      | phone          | 11am - 2pm           | yes      | no          | no               | no          |vintage        |
    And I am on Application lifestyle rallying page
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

  @LQA374 @authenticated @shortform @saldi
  Scenario Outline: Validate user should not be able to remove if there's only single license on rallying
    Given the following user data:
      | Age | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee |
      | 54  | Female | 55000:Per year | Actor      | No                               | No                |
    And with the following death cover data:
      | death cover type | death current cover | death calculated cover | quoted death cover amount   |
      | units            | 170000              | 155000                 | <quoted death cover amount> |
    And with the following application data:
      | contact method | best time to contact | resident | other cover | claimed benefits | has advisor |racing car type|
      | phone          | 11am - 2pm           | yes      | no          | no               | no          |vintage        |
    And I am on Application lifestyle rallying page
    When I enter the license 1 following data:
      | license type | classification |
      | car          | midsize        |
    Then I should not be able to remove this license
    Examples:
      | quoted death cover amount |
      | 27300                     |


  @LQA374 @authenticated @shortform @saldi
  Scenario Outline: Validate user should not be able to remove the if there's only single vehicle on rallying
    Given the following user data:
      | Age | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee |
      | 54  | Female | 55000:Per year | Actor      | No                               | No                |
    And with the following death cover data:
      | death cover type | death current cover | death calculated cover | quoted death cover amount   |
      | units            | 170000              | 155000                 | <quoted death cover amount> |
    And with the following application data:
      | contact method | best time to contact | resident | other cover | claimed benefits | has advisor |racing car type|
      | phone          | 11am - 2pm           | yes      | no          | no               | no          |vintage        |
    And I am on Application lifestyle rallying page
    When I enter the vehicle 1 following data:
      | make model | engine size | top speed |
      | audi       | 3.0 litre   | 300       |
    Then I should not be able to remove this vehicle
    Examples:
      | quoted death cover amount |
      | 27300                     |

  @manual @LQA374
  Scenario: Once I add the 2nd license or vehicle, the remove button should reappear on rallying

  @LQA374 @authenticated @shortform @saldi
  Scenario Outline: Validate user should be able to add maximum of 5 license on rallying
    Given the following user data:
      | Age | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee |
      | 54  | Female | 55000:Per year | Actor      | No                               | No                |
    And with the following death cover data:
      | death cover type | death current cover | death calculated cover | quoted death cover amount   |
      | units            | 170000              | 155000                 | <quoted death cover amount> |
    And with the following application data:
      | contact method | best time to contact | resident | other cover | claimed benefits | has advisor |racing car type|
      | phone          | 11am - 2pm           | yes      | no          | no               | no          |vintage        |
    And I am on Application lifestyle rallying page
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
    Examples:
      | quoted death cover amount |
      | 27300                     |


  @LQA374 @authenticated @shortform @saldi
  Scenario Outline: Validate user should be able to add maximum of 5 vehicles on rallying
    Given the following user data:
      | Age | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee |
      | 54  | Female | 55000:Per year | Actor      | No                               | No                |
    And with the following death cover data:
      | death cover type | death current cover | death calculated cover | quoted death cover amount   |
      | units            | 170000              | 155000                 | <quoted death cover amount> |
    And with the following application data:
      | contact method | best time to contact | resident | other cover | claimed benefits | has advisor |racing car type|
      | phone          | 11am - 2pm           | yes      | no          | no               | no          |vintage        |
    And I am on Application lifestyle rallying page
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
    Examples:
      | quoted death cover amount |
      | 27300                     |

  @LQA375 @authenticated @shortform @saldi
  Scenario Outline: Validate the Application questions data on Activities & pursuits - rallying events page 2
    Given the following user data:
      | Age | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee |
      | 54  | Female | 55000:Per year | Actor      | No                               | No                |
    And with the following death cover data:
      | death cover type | death current cover | death calculated cover | quoted death cover amount   |
      | units            | 170000              | 155000                 | <quoted death cover amount> |
    And with the following application data:
      | contact method | best time to contact | resident | other cover | claimed benefits | has advisor |racing car type|
      | phone          | 11am - 2pm           | yes      | no          | no               | no          |vintage        |
    And I am on Application lifestyle rallying page
    When I enter the car racing 1 following data:
      | license type | classification |make model | engine size | top speed |
      | car          | midsize        |audi       | 3.0 litre   | 300       |
    And I try to go to next page on application
    Then the lifestyle rallying text asked is Tell us more about the events you participate in.
    And the lifestyle rallying question asked is Specify type of events participated in.
    Then the lifestyle rallying text asked is How many events do you participate in per year?
    Then the lifestyle rallying text asked is Do you currently, or intend to take part in:
    And the lifestyle rallying question asked is Have you ever been involved in any accident/mishap whilst participating in this activity?
    Examples:
      | quoted death cover amount |
      | 27300                     |

  @LQA375 @authenticated @shortform @saldi
  Scenario Outline: Validate user should be able to go to next page from rallying events page 2
    Given the following user data:
      | Age | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee |
      | 54  | Female | 55000:Per year | Actor      | No                               | No                |
    And with the following death cover data:
      | death cover type | death current cover | death calculated cover | quoted death cover amount   |
      | units            | 170000              | 155000                 | <quoted death cover amount> |
    And with the following application data:
| contact method | best time to contact | resident | other cover | claimed benefits | has advisor | racing car type |
| phone          | 11am - 2pm           | yes      | no          | no               | no          | vintage         |
    And I am on Application lifestyle rallying page
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