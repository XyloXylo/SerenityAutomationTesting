Feature: Needs Summary Breakdown DEATH
  Verify needs summary breakdown details for Death

  Background:
    Given I am on Home Page

#
  @LQA-300 @LQA-298 @NeedsSum-Death-Modal-Verify-All-Block-Values-PUBLIC  @psy
    Scenario Outline: NeedsSum-Death-Modal-Verify-All-Block-Values-PUBLIC
    Given I am an public member
    And the following user data:
    | Age   | Gender   | Income   | Occupation   | Less than 15 hours work per week   | Contract employee   | Spouse   | Children   | Child age   | Cash   | Investment properties   | Shares   | Superannuation balances   | Other investment assets   | Mortgage   | Car loan   | Credit card   | Other loans   | Existing Death   | Existing TPD   | Existing IP   |Calculated Death | Calculated TPD | Calculated IP |
    | <Age> | <Gender> | <Income> | <Occupation> | <Less than 15 hours work per week> | <Contract employee> | <Spouse> | <Children> | <Child age> | <Cash> | <Investment properties> | <Shares> | <Superannuation balances> | <Other investment assets> | <Mortgage> | <Car loan> | <Credit card> | <Other loans> | <Existing Death> | <Existing TPD> | <Existing IP> |<Calculated Death> | <Calculated TPD> | <Calculated IP> |
    And I am on Needs Summary page
    And I click on DEATH See the Breakdown button
    Then I should see DEATH modal is open
    And I should be able to see DEATH cover details
    And Verify DEATH CoverForExpenses block
    And Verify DEATH CoverForDebts block
    And Verify DEATH CoverForDependants block
    And Verify DEATH CoverForChildren block
    Examples:
    | Age | Gender | Income          | Occupation                            | Less than 15 hours work per week | Contract employee | Spouse | Children | Child age | Cash  | Investment properties | Shares | Superannuation balances | Other investment assets | Mortgage | Car loan | Credit card | Other loans | Existing Death | Existing TPD | Existing IP | Calculated Death | Calculated TPD | Calculated IP |
    | 30  | Female | 35000:Per year  | Nurse [dental]                        | No                               | No                | No     | 0        | 0         | 2000  | 0                     | 0      | 18000                   | 0                       | 0        | 5000     | 2000        | 0           | 0              | 0            | 0           | 0                | 57000          | 2479.73          |
    | 40  | Male   | 80000:Per year  | Actor                                 | No                               | No                | Yes    | 1        | 5         | 20000 | 0                     | 0      | 50000                   | 10000                   | 400000   | 0        | 5000        | 20000       | 70000          | 0            | 850         | 1842865          | 1902865        | 5666.67          |
    | 50  | Male   | 150000:Per year | Business Analyst [tertiary qualified] | No                               | No                | Yes    | 3        | 10        | 50000 | 600000                | 50000  | 200000                  | 30000                   | 700000   | 30000    | 10000       | 0           | 200000         | 200000       | 850         | 2058925          | 2118925        | 10625.00         |

  @LQA-300 @LQA-298 @NeedsSum-Death-Modal-Verify-All-Block-Values-AUTHENTICATED  @psy @authenticated
  Scenario Outline: NeedsSum-Death-Modal-Verify-All-Block-Values-AUTHENTICATED
    Given I am an authenticated member
    And the following user data:
      | Age   | Gender   | Income   | Occupation   | Less than 15 hours work per week   | Contract employee   | Spouse   | Children   | Child age   | Cash   | Investment properties   | Shares   | Superannuation balances   | Other investment assets   | Mortgage   | Car loan   | Credit card   | Other loans   | Existing Death   | Existing TPD   | Existing IP   |Calculated Death | Calculated TPD | Calculated IP |
      | <Age> | <Gender> | <Income> | <Occupation> | <Less than 15 hours work per week> | <Contract employee> | <Spouse> | <Children> | <Child age> | <Cash> | <Investment properties> | <Shares> | <Superannuation balances> | <Other investment assets> | <Mortgage> | <Car loan> | <Credit card> | <Other loans> | <Existing Death> | <Existing TPD> | <Existing IP> |<Calculated Death> | <Calculated TPD> | <Calculated IP> |
    And I am on Needs Summary page
    And I click on DEATH See the Breakdown button
    Then I should see DEATH modal is open
    And I should be able to see DEATH cover details
    And Verify DEATH CoverForExpenses block
    And Verify DEATH CoverForDebts block
    And Verify DEATH CoverForDependants block
    And Verify DEATH CoverForChildren block
    Examples:
      | Age | Gender | Income          | Occupation                            | Less than 15 hours work per week | Contract employee | Spouse | Children | Child age | Cash  | Investment properties | Shares | Superannuation balances | Other investment assets | Mortgage | Car loan | Credit card | Other loans | Existing Death | Existing TPD | Existing IP | Calculated Death | Calculated TPD | Calculated IP |
      | 30  | Female | 35000:Per year  | Nurse [dental]                        | No                               | No                | No     | 0        | 0         | 2000  | 0                     | 0      | 18000                   | 0                       | 0        | 5000     | 2000        | 0           | 0              | 0            | 0           | 0                | 57000          | 2479.73          |
      | 40  | Male   | 80000:Per year  | Actor                                 | No                               | No                | Yes    | 1        | 5         | 20000 | 0                     | 0      | 50000                   | 10000                   | 400000   | 0        | 5000        | 20000       | 70000          | 0            | 850         | 1842865          | 1902865        | 5666.67          |
      | 50  | Male   | 150000:Per year | Business Analyst [tertiary qualified] | No                               | No                | Yes    | 3        | 10        | 50000 | 600000                | 50000  | 200000                  | 30000                   | 700000   | 30000    | 10000       | 0           | 200000         | 200000       | 850         | 2058925          | 2118925        | 10625.00         |


  @LQA-300 @LQA-298 @NeedsSum-Death-Modal-Edit-And-Verify-Slider-values  @psy
    Scenario Outline: NeedsSum-Death-Modal-Edit-And-Verify-Slider-values-PUBLIC
    Given I am an public member
    And the following user data:
      | Age   | Gender   | Income   | Occupation   | Less than 15 hours work per week   | Contract employee   | Spouse   | Children   | Child age   | Cash   | Investment properties   | Shares   | Superannuation balances   | Other investment assets   | Mortgage   | Car loan   | Credit card   | Other loans   | Existing Death   | Existing TPD   | Existing IP   |Calculated Death | Calculated TPD | Calculated IP |
      | <Age> | <Gender> | <Income> | <Occupation> | <Less than 15 hours work per week> | <Contract employee> | <Spouse> | <Children> | <Child age> | <Cash> | <Investment properties> | <Shares> | <Superannuation balances> | <Other investment assets> | <Mortgage> | <Car loan> | <Credit card> | <Other loans> | <Existing Death> | <Existing TPD> | <Existing IP> |<Calculated Death> | <Calculated TPD> | <Calculated IP> |
    And I am on Needs Summary page
    And I click on DEATH See the Breakdown button
    Then I should see DEATH modal is open
    And I should be able to see DEATH cover details
    And Verify DEATH CoverDetails values of all blocks
    And Edit CoverForExpenses values in DEATH modal
    And Edit CoverForDebts values in DEATH modal
    Then Dependant Edit CoverForDependants values in DEATH modal
    Then Dependant Edit CoverForChildren values in DEATH modal
    And Verify DEATH Coverdetails complete
    Examples:
      | Age | Gender | Income          | Occupation                            | Less than 15 hours work per week | Contract employee | Spouse | Children | Child age | Cash  | Investment properties | Shares | Superannuation balances | Other investment assets | Mortgage | Car loan | Credit card | Other loans | Existing Death | Existing TPD | Existing IP | Calculated Death | Calculated TPD | Calculated IP |
      | 30  | Female | 35000:Per year  | Nurse [dental]                        | No                               | No                | No     | 0        | 0         | 2000  | 0                     | 0      | 18000                   | 0                       | 0        | 5000     | 2000        | 0           | 0              | 0            | 0           | 0                | 57000          | 2479.73          |
      | 40  | Male   | 80000:Per year  | Actor                                 | No                               | No                | Yes    | 1        | 5         | 20000 | 0                     | 0      | 50000                   | 10000                   | 400000   | 0        | 5000        | 20000       | 70000          | 0            | 850         | 1842865          | 1902865        | 5666.67          |
      | 50  | Male   | 150000:Per year | Business Analyst [tertiary qualified] | No                               | No                | Yes    | 3        | 10        | 50000 | 600000                | 50000  | 200000                  | 30000                   | 700000   | 30000    | 10000       | 0           | 200000         | 200000       | 850         | 2058925          | 2118925        | 10625.00         |

  @LQA-300 @LQA-298 @NeedsSum-Death-Modal-Edit-And-Verify-Slider-values-AUTHENTICATED  @psy @pending
    Scenario Outline: Edit Slider values-NeedSummary Death modal complete authenticated
    Given I am an authenticated member
    And the following user data:
      | Age   | Gender   | Income   | Occupation   | Less than 15 hours work per week   | Contract employee   | Spouse   | Children   | Child age   | Cash   | Investment properties   | Shares   | Superannuation balances   | Other investment assets   | Mortgage   | Car loan   | Credit card   | Other loans   | Existing Death   | Existing TPD   | Existing IP   |Calculated Death | Calculated TPD | Calculated IP |
      | <Age> | <Gender> | <Income> | <Occupation> | <Less than 15 hours work per week> | <Contract employee> | <Spouse> | <Children> | <Child age> | <Cash> | <Investment properties> | <Shares> | <Superannuation balances> | <Other investment assets> | <Mortgage> | <Car loan> | <Credit card> | <Other loans> | <Existing Death> | <Existing TPD> | <Existing IP> |<Calculated Death> | <Calculated TPD> | <Calculated IP> |
    And I am on Needs Summary page
    And I click on DEATH See the Breakdown button
    Then I should see DEATH modal is open
    And I should be able to see DEATH cover details
    And Verify DEATH CoverDetails values of all blocks
    And Edit CoverForExpenses values in DEATH modal
    And Edit CoverForDebts values in DEATH modal
    Then Dependant Edit CoverForDependants values in DEATH modal
    Then Dependant Edit CoverForChildren values in DEATH modal
    And Verify DEATH Coverdetails complete
    Examples:
      | Age | Gender | Income          | Occupation                            | Less than 15 hours work per week | Contract employee | Spouse | Children | Child age | Cash  | Investment properties | Shares | Superannuation balances | Other investment assets | Mortgage | Car loan | Credit card | Other loans | Existing Death | Existing TPD | Existing IP | Calculated Death | Calculated TPD | Calculated IP |
#      | 30  | Female | 35000:Per year  | Nurse [dental]                        | No                               | No                | No     | 0        | 0         | 2000  | 0                     | 0      | 18000                   | 0                       | 0        | 5000     | 2000        | 0           | 0              | 0            | 0           | 0                | 57000          | 2479.73          |
      | 40  | Male   | 80000:Per year  | Actor                                 | No                               | No                | Yes    | 1        | 5         | 20000 | 0                     | 0      | 50000                   | 10000                   | 400000   | 0        | 5000        | 20000       | 70000          | 0            | 850         | 1842865          | 1902865        | 5666.67          |
#      | 50  | Male   | 150000:Per year | Business Analyst [tertiary qualified] | No                               | No                | Yes    | 3        | 10        | 50000 | 600000                | 50000  | 200000                  | 30000                   | 700000   | 30000    | 10000       | 0           | 200000         | 200000       | 850         | 2058925          | 2118925        | 10625.00         |


  @LQA-300 @LQA-298 @NeedsSum-Death-Modal-SaveAndClose @psy
    Scenario Outline: NeedsBreakdown- SaveAndClose Death complete
    Given I am an public member
    And the following user data:
      | Age   | Gender   | Income   | Occupation   | Less than 15 hours work per week   | Contract employee   | Spouse   | Children   | Child age   | Cash   | Investment properties   | Shares   | Superannuation balances   | Other investment assets   | Mortgage   | Car loan   | Credit card   | Other loans   | Existing Death   | Existing TPD   | Existing IP   |Calculated Death | Calculated TPD | Calculated IP |
      | <Age> | <Gender> | <Income> | <Occupation> | <Less than 15 hours work per week> | <Contract employee> | <Spouse> | <Children> | <Child age> | <Cash> | <Investment properties> | <Shares> | <Superannuation balances> | <Other investment assets> | <Mortgage> | <Car loan> | <Credit card> | <Other loans> | <Existing Death> | <Existing TPD> | <Existing IP> |<Calculated Death> | <Calculated TPD> | <Calculated IP> |
    And I am on Needs Summary page
    And I click on DEATH See the Breakdown button
    Then I should see DEATH modal is open
    And I should be able to see DEATH cover details
    And Verify DEATH CoverDetails values of all blocks
    And Edit All Blocks values of DEATH modal
    And Verify DEATH Coverdetails complete
    And I click  SaveAndClose button on DEATH modal
    Then Verify DEATH CoverDetails after SaveAndClose
    Examples:
      | Age | Gender | Income          | Occupation                            | Less than 15 hours work per week | Contract employee | Spouse | Children | Child age | Cash  | Investment properties | Shares | Superannuation balances | Other investment assets | Mortgage | Car loan | Credit card | Other loans | Existing Death | Existing TPD | Existing IP | Calculated Death | Calculated TPD | Calculated IP |
      | 30  | Female | 35000:Per year  | Nurse [dental]                        | No                               | No                | No     | 0        | 0         | 2000  | 0                     | 0      | 18000                   | 0                       | 0        | 5000     | 2000        | 0           | 0              | 0            | 0           | 0                | 57000          | 2479.73          |
      | 40  | Male   | 80000:Per year  | Actor                                 | No                               | No                | Yes    | 1        | 5         | 20000 | 0                     | 0      | 50000                   | 10000                   | 400000   | 0        | 5000        | 20000       | 70000          | 0            | 850         | 1842865          | 1902865        | 5666.67          |
      | 50  | Male   | 150000:Per year | Business Analyst [tertiary qualified] | No                               | No                | Yes    | 3        | 10        | 50000 | 600000                | 50000  | 200000                  | 30000                   | 700000   | 30000    | 10000       | 0           | 200000         | 200000       | 850         | 2058925          | 2118925        | 10625.00         |
