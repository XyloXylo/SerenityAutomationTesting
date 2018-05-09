Feature: Needs Summary Breakdown
  Verify needs summary breakdown details for Death, TPD and IP

  Background:
    Given I am on Home Page





  //----------------------------------------------------------------------//
  //-----TPD MODAL------------------------------------------------------//
  //----------------------------------------------------------------------//







  @LQA-292 @RESET-TPD-NeedsSum-Breakdown @pending
  Scenario Outline: Reset TPD NeedSummary BreakDown
    Given I am an public member
    And the following user data:
      | Age   | Gender   | Income   | Occupation   | Less than 15 hours work per week   | Contract employee   | Spouse   | Children   | Child age   | Cash   | Investment properties   | Shares   | Superannuation balances   | Other investment assets   | Mortgage   | Car loan   | Credit card   | Other loans   | Existing Death   | Existing TPD   | Existing IP   |Calculated Death | Calculated TPD | Calculated IP |
      | <Age> | <Gender> | <Income> | <Occupation> | <Less than 15 hours work per week> | <Contract employee> | <Spouse> | <Children> | <Child age> | <Cash> | <Investment properties> | <Shares> | <Superannuation balances> | <Other investment assets> | <Mortgage> | <Car loan> | <Credit card> | <Other loans> | <Existing Death> | <Existing TPD> | <Existing IP> |<Calculated Death> | <Calculated TPD> | <Calculated IP> |
    And I am on Needs Summary page
    And I click on TPD See the Breakdown button
    Then I should see TPD modal is open
    And I should store TPD the slider values
    And I click on TPD Breakdown modal reset button
    And Verify RESET button brings up prevoius values.

#  And Verify TPD CoverForExpenses block
#  And Verify TPD CoverForDebts block
#  And Move the slider for different value
#  Then Verify the value changes on the top
    Examples:
      | Age | Gender | Income          | Occupation                            | Less than 15 hours work per week | Contract employee | Spouse | Children | Child age | Cash  | Investment properties | Shares | Superannuation balances | Other investment assets | Mortgage | Car loan | Credit card | Other loans | Existing Death | Existing TPD | Existing IP | Calculated Death | Calculated TPD | Calculated IP |
      | 30  | Female | 35000:Per year  | Nurse [dental]                        | No                               | No                | No     | 0        | 0         | 2000  | 0                     | 0      | 18000                   | 0                       | 0        | 5000     | 2000        | 0           | 0              | 0            | 0           | 0                | 57000          | 2479.73          |
#    | 40  | Male   | 80000:Per year  | Actor                                 | No                               | No                | Yes    | 1        | 5         | 20000 | 0                     | 0      | 50000                   | 10000                   | 400000   | 0        | 5000        | 20000       | 70000          | 0            | 850         | 1842865          | 1902865        | 5666.67          |
#    | 50  | Male   | 150000:Per year | Business Analyst [tertiary qualified] | No                               | No                | Yes    | 3        | 10        | 50000 | 600000                | 50000  | 200000                  | 30000                   | 700000   | 30000    | 10000       | 0           | 200000         | 200000       | 850         | 2058925          | 2118925        | 10625.00         |



  @LQA-203 @NeedSummaryalerts @psy @pending
  Scenario Outline: NeedSummary Alerts for Death , TPD and IP
    Given I am an public member
    And the following user data:
      | Age   | Gender   | Income   | Occupation   | Less than 15 hours work per week   | Contract employee   | Spouse   | Children   | Child age   | Cash   | Investment properties   | Shares   | Superannuation balances   | Other investment assets   | Mortgage   | Car loan   | Credit card   | Other loans   | Existing Death   | Existing TPD   | Existing IP   |Calculated Death | Calculated TPD | Calculated IP |
      | <Age> | <Gender> | <Income> | <Occupation> | <Less than 15 hours work per week> | <Contract employee> | <Spouse> | <Children> | <Child age> | <Cash> | <Investment properties> | <Shares> | <Superannuation balances> | <Other investment assets> | <Mortgage> | <Car loan> | <Credit card> | <Other loans> | <Existing Death> | <Existing TPD> | <Existing IP> |<Calculated Death> | <Calculated TPD> | <Calculated IP> |
    And I am on Needs Summary page
    #    Then Verify Needs Summary alerts for DEATH
    #    Then Verify Needs Summary alerts for TPD
    #    Then Verify Needs Summary alerts for IP
    Then Verify Needs Summary alerts for ALL

    Examples:
    | Age | Gender | Income          | Occupation                            | Less than 15 hours work per week | Contract employee | Spouse | Children | Child age | Cash  | Investment properties | Shares | Superannuation balances | Other investment assets | Mortgage | Car loan | Credit card | Other loans | Existing Death | Existing TPD | Existing IP | Calculated Death | Calculated TPD | Calculated IP |
    | 30  | Female | 35000:Per year  | Nurse [dental]                        | No                               | No                | No     | 0        | 0         | 2000  | 0                     | 0      | 18000                   | 0                       | 0        | 5000     | 2000        | 0           | 0              | 0            | 0           | 0                | 57000          | 2479.73          |
    | 40  | Male   | 350000:Per year  | Actor                                 | No                               | No                | Yes    | 1        | 5         | 20000 | 0                     | 0      | 50000                   | 10000                   | 400000   | 0        | 5000        | 20000       | 70000          | 0            | 850         | 1842865          | 1902865        | 5666.67          |
    | 50  | Male   | 150000:Per year | Business Analyst [tertiary qualified] | No                               | No                | Yes    | 3        | 10        | 50000 | 600000                | 50000  | 200000                  | 30000                   | 700000   | 30000    | 10000       | 0           | 200000         | 200000       | 850         | 2058925          | 2118925        | 10625.00         |



