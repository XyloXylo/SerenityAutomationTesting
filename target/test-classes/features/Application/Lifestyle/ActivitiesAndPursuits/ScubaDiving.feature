Feature: Scuba diving
  In order to continue with my application
  As authenticated member
  I want to enter the details of any Scuba diving activities I participate in

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
      | yes     | honda      | 2000cc      | 300       | raceOmania, RacingFreaks | yes | I am doing raceOmania every year |
    And with the following hand gliding data:
      | member | number of flights | participate | participation details |
      | yes    | 2                 | yes         | winner                |
    And with the following motorcycle racing data:
| make model | engine size | top speed | event type               | event participation | participation details            | any accident | accident details                     |
| honda      | 2000cc      | 300       | raceOmania, RacingFreaks | yes                 | I am doing raceOmania every year | yes          | in 2002, over the steep hills in sfo |
    And with the following powerboat racing data:
      | boat class | boat max speed | boat locations |
      | heavy      | 85             | mississipi     |
    And I am on Application lifestyle scuba diving page

  @LQA407 @authenticated @shortform @saldi
  Scenario: Validate the Application questions data on Activities & pursuits - scuba diving
    Then the lifestyle question asked is Do you hold any diving qualifications (i.e. PADI/ NAUI/ SSI)?
    Then the lifestyle question asked is In what areas do you dive?
    Then the lifestyle question asked is Does your diving also include any of the following?
    Then the lifestyle question asked is What maximum depth do you dive to?
    And these are the lifestyle scubaDiving options given to me to choose from Coastal Waters/ Lakes/ Rivers/ Pits/ Quarries/ Sheltered Waters.,Caves and pot holes,Internal exploration of wrecks,Diving for treasure or special expeditions
    And these are the lifestyle scubaDiving options given to me to choose from Diving Bell,Free diving,Hookah,None of above
    And these are the lifestyle scubaDiving options given to me to choose from Less than or equal to 30 metres,31 to 40 metres,Over 40 metres
    When my maximum dive depth is 31 to 40 metres
    Then the lifestyle question asked is What is frequency of dives between 31 metres and 40 metres per annum?
    And these are the lifestyle scubaDiving options given to me to choose from Up to or equal to 4 dives per annum,More than 4 dives per annum
    When my maximum dive depth is Over 40 metres
    Then the lifestyle question asked is What is frequency of dives over 40 metres per annum?