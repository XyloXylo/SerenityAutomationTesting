Feature: Needs Summary Breakdown TPD
  Verify needs summary breakdown details for TPD

  Background:
    Given I am on Home Page


  @LQA178 @NeedsSum-TPD-Modal-complete-public @psy
  Scenario Outline: NeedSummary TPD modal complete public
    Given I am an public member
    And the following user data:
      | Age   | Gender   | Income   | Occupation   | Less than 15 hours work per week   | Contract employee   | Spouse   | Children   | Child age   | Cash   | Investment properties   | Shares   | Superannuation balances   | Other investment assets   | Mortgage   | Car loan   | Credit card   | Other loans   | Existing Death   | Existing TPD   | Existing IP   |Calculated Death | Calculated TPD | Calculated IP |
      | <Age> | <Gender> | <Income> | <Occupation> | <Less than 15 hours work per week> | <Contract employee> | <Spouse> | <Children> | <Child age> | <Cash> | <Investment properties> | <Shares> | <Superannuation balances> | <Other investment assets> | <Mortgage> | <Car loan> | <Credit card> | <Other loans> | <Existing Death> | <Existing TPD> | <Existing IP> |<Calculated Death> | <Calculated TPD> | <Calculated IP> |
    And I am on Needs Summary page
    And I click on TPD See the Breakdown button
    Then I should see TPD modal is open
    And I should be able to see TPD cover details
    And Verify TPD CoverForExpenses block
    And Verify TPD CoverForDebts block
    And Verify TPD CoverForDependants block
    And Verify TPD CoverForChildren block
    Examples:
      | Age | Gender | Income          | Occupation                            | Less than 15 hours work per week | Contract employee | Spouse | Children | Child age | Cash  | Investment properties | Shares | Superannuation balances | Other investment assets | Mortgage | Car loan | Credit card | Other loans | Existing Death | Existing TPD | Existing IP | Calculated Death | Calculated TPD | Calculated IP |
      | 30  | Female | 35000:Per year  | Nurse [dental]                        | No                               | No                | No     | 0        | 0         | 2000  | 0                     | 0      | 18000                   | 0                       | 0        | 5000     | 2000        | 0           | 0              | 0            | 0           | 0                | 57000          | 2479.73          |
      | 40  | Male   | 80000:Per year  | Actor                                 | No                               | No                | Yes    | 1        | 5         | 20000 | 0                     | 0      | 50000                   | 10000                   | 400000   | 0        | 5000        | 20000       | 70000          | 0            | 850         | 1842865          | 1902865        | 5666.67          |
      | 50  | Male   | 150000:Per year | Business Analyst [tertiary qualified] | No                               | No                | Yes    | 3        | 10        | 50000 | 600000                | 50000  | 200000                  | 30000                   | 700000   | 30000    | 10000       | 0           | 200000         | 200000       | 850         | 2058925          | 2118925        | 10625.00         |


  @LQA178 @NeedsSum-TPD-Modal-complete-AUTHENTICTED @psy
  Scenario Outline: NeedSummary TPD modal complete AUTHENTICTED
    Given I am an authenticated member
    And the following user data:
      | Age   | Gender   | Income   | Occupation   | Less than 15 hours work per week   | Contract employee   | Spouse   | Children   | Child age   | Cash   | Investment properties   | Shares   | Superannuation balances   | Other investment assets   | Mortgage   | Car loan   | Credit card   | Other loans   | Existing Death   | Existing TPD   | Existing IP   |Calculated Death | Calculated TPD | Calculated IP |
      | <Age> | <Gender> | <Income> | <Occupation> | <Less than 15 hours work per week> | <Contract employee> | <Spouse> | <Children> | <Child age> | <Cash> | <Investment properties> | <Shares> | <Superannuation balances> | <Other investment assets> | <Mortgage> | <Car loan> | <Credit card> | <Other loans> | <Existing Death> | <Existing TPD> | <Existing IP> |<Calculated Death> | <Calculated TPD> | <Calculated IP> |
    And I am on Needs Summary page
    And I click on TPD See the Breakdown button
    Then I should see TPD modal is open
    And I should be able to see TPD cover details
    And Verify TPD CoverForExpenses block
    And Verify TPD CoverForDebts block
    And Verify TPD CoverForDependants block
    And Verify TPD CoverForChildren block
    Examples:
      | Age | Gender | Income          | Occupation                            | Less than 15 hours work per week | Contract employee | Spouse | Children | Child age | Cash  | Investment properties | Shares | Superannuation balances | Other investment assets | Mortgage | Car loan | Credit card | Other loans | Existing Death | Existing TPD | Existing IP | Calculated Death | Calculated TPD | Calculated IP |
      | 30  | Female | 35000:Per year  | Nurse [dental]                        | No                               | No                | No     | 0        | 0         | 2000  | 0                     | 0      | 18000                   | 0                       | 0        | 5000     | 2000        | 0           | 0              | 0            | 0           | 0                | 57000          | 2479.73          |
      | 40  | Male   | 80000:Per year  | Actor                                 | No                               | No                | Yes    | 1        | 5         | 20000 | 0                     | 0      | 50000                   | 10000                   | 400000   | 0        | 5000        | 20000       | 70000          | 0            | 850         | 1842865          | 1902865        | 5666.67          |
      | 50  | Male   | 150000:Per year | Business Analyst [tertiary qualified] | No                               | No                | Yes    | 3        | 10        | 50000 | 600000                | 50000  | 200000                  | 30000                   | 700000   | 30000    | 10000       | 0           | 200000         | 200000       | 850         | 2058925          | 2118925        | 10625.00         |


  @LQA-283 @LQA-284 @NeedsSum-TPD-Modal-Edit-slider-values  @psy
  Scenario Outline: Edit Slider values-NeedSummary TPD modal complete
    Given I am an public member
    And the following user data:
      | Age   | Gender   | Income   | Occupation   | Less than 15 hours work per week   | Contract employee   | Spouse   | Children   | Child age   | Cash   | Investment properties   | Shares   | Superannuation balances   | Other investment assets   | Mortgage   | Car loan   | Credit card   | Other loans   | Existing Death   | Existing TPD   | Existing IP   |Calculated Death | Calculated TPD | Calculated IP |
      | <Age> | <Gender> | <Income> | <Occupation> | <Less than 15 hours work per week> | <Contract employee> | <Spouse> | <Children> | <Child age> | <Cash> | <Investment properties> | <Shares> | <Superannuation balances> | <Other investment assets> | <Mortgage> | <Car loan> | <Credit card> | <Other loans> | <Existing Death> | <Existing TPD> | <Existing IP> |<Calculated Death> | <Calculated TPD> | <Calculated IP> |
    And I am on Needs Summary page
    And I click on TPD See the Breakdown button
    Then I should see TPD modal is open
    And I should be able to see TPD cover details
    And Verify TPD CoverDetails values of all blocks
    And Edit CoverForExpenses values in TPD modal
    And Edit CoverForDebts values in TPD modal
    Then Dependant Edit CoverForDependants values in TPD modal
    Then Dependant Edit CoverForChildren values in TPD modal
    And Verify TPD Coverdetails complete
    Examples:
      | Age | Gender | Income          | Occupation                            | Less than 15 hours work per week | Contract employee | Spouse | Children | Child age | Cash  | Investment properties | Shares | Superannuation balances | Other investment assets | Mortgage | Car loan | Credit card | Other loans | Existing Death | Existing TPD | Existing IP | Calculated Death | Calculated TPD | Calculated IP |
      | 30  | Female | 35000:Per year  | Nurse [dental]                        | No                               | No                | No     | 0        | 0         | 2000  | 0                     | 0      | 18000                   | 0                       | 0        | 5000     | 2000        | 0           | 0              | 0            | 0           | 0                | 57000          | 2479.73          |
      | 40  | Male   | 80000:Per year  | Actor                                 | No                               | No                | Yes    | 1        | 5         | 20000 | 0                     | 0      | 50000                   | 10000                   | 400000   | 0        | 5000        | 20000       | 70000          | 0            | 850         | 1842865          | 1902865        | 5666.67          |
      | 50  | Male   | 150000:Per year | Business Analyst [tertiary qualified] | No                               | No                | Yes    | 3        | 10        | 50000 | 600000                | 50000  | 200000                  | 30000                   | 700000   | 30000    | 10000       | 0           | 200000         | 200000       | 850         | 2058925          | 2118925        | 10625.00         |


  @LQA-283 @LQA-284 @NeedsSum-TPD-Modal-Edit-slider-values-authenticated  @psy
  Scenario Outline: Edit Slider values-NeedSummary TPD modal complete authenticated
    Given I am an authenticated member
    And the following user data:
      | Age   | Gender   | Income   | Occupation   | Less than 15 hours work per week   | Contract employee   | Spouse   | Children   | Child age   | Cash   | Investment properties   | Shares   | Superannuation balances   | Other investment assets   | Mortgage   | Car loan   | Credit card   | Other loans   | Existing Death   | Existing TPD   | Existing IP   |Calculated Death | Calculated TPD | Calculated IP |
      | <Age> | <Gender> | <Income> | <Occupation> | <Less than 15 hours work per week> | <Contract employee> | <Spouse> | <Children> | <Child age> | <Cash> | <Investment properties> | <Shares> | <Superannuation balances> | <Other investment assets> | <Mortgage> | <Car loan> | <Credit card> | <Other loans> | <Existing Death> | <Existing TPD> | <Existing IP> |<Calculated Death> | <Calculated TPD> | <Calculated IP> |
    And I am on Needs Summary page
    And I click on TPD See the Breakdown button
    Then I should see TPD modal is open
    And I should be able to see TPD cover details
    And Verify TPD CoverDetails values of all blocks
    And Edit CoverForExpenses values in TPD modal
    And Edit CoverForDebts values in TPD modal
    Then Dependant Edit CoverForDependants values in TPD modal
    Then Dependant Edit CoverForChildren values in TPD modal
    And Verify TPD Coverdetails complete
    Examples:
      | Age | Gender | Income          | Occupation                            | Less than 15 hours work per week | Contract employee | Spouse | Children | Child age | Cash  | Investment properties | Shares | Superannuation balances | Other investment assets | Mortgage | Car loan | Credit card | Other loans | Existing Death | Existing TPD | Existing IP | Calculated Death | Calculated TPD | Calculated IP |
      | 30  | Female | 35000:Per year  | Nurse [dental]                        | No                               | No                | No     | 0        | 0         | 2000  | 0                     | 0      | 18000                   | 0                       | 0        | 5000     | 2000        | 0           | 0              | 0            | 0           | 0                | 57000          | 2479.73          |
      | 40  | Male   | 80000:Per year  | Actor                                 | No                               | No                | Yes    | 1        | 5         | 20000 | 0                     | 0      | 50000                   | 10000                   | 400000   | 0        | 5000        | 20000       | 70000          | 0            | 850         | 1842865          | 1902865        | 5666.67          |
      | 50  | Male   | 150000:Per year | Business Analyst [tertiary qualified] | No                               | No                | Yes    | 3        | 10        | 50000 | 600000                | 50000  | 200000                  | 30000                   | 700000   | 30000    | 10000       | 0           | 200000         | 200000       | 850         | 2058925          | 2118925        | 10625.00         |


  @LQA-300 @LQA-298 @NeedsSum-TPD-Modal-SaveAndClose @psy
  Scenario Outline: NeedsBreakdown- SaveAndClose TPD complete
    Given I am an public member
    And the following user data:
      | Age   | Gender   | Income   | Occupation   | Less than 15 hours work per week   | Contract employee   | Spouse   | Children   | Child age   | Cash   | Investment properties   | Shares   | Superannuation balances   | Other investment assets   | Mortgage   | Car loan   | Credit card   | Other loans   | Existing Death   | Existing TPD   | Existing IP   |Calculated Death | Calculated TPD | Calculated IP |
      | <Age> | <Gender> | <Income> | <Occupation> | <Less than 15 hours work per week> | <Contract employee> | <Spouse> | <Children> | <Child age> | <Cash> | <Investment properties> | <Shares> | <Superannuation balances> | <Other investment assets> | <Mortgage> | <Car loan> | <Credit card> | <Other loans> | <Existing Death> | <Existing TPD> | <Existing IP> |<Calculated Death> | <Calculated TPD> | <Calculated IP> |
    And I am on Needs Summary page
    And I click on TPD See the Breakdown button
    Then I should see TPD modal is open
    And I should be able to see TPD cover details
    And Verify TPD CoverDetails values of all blocks
    And Edit All Blocks values of TPD modal
    And Verify TPD Coverdetails complete
    And I click  SaveAndClose button on TPD modal
    Then Verify TPD CoverDetails after SaveAndClose
    Examples:
      | Age | Gender | Income          | Occupation                            | Less than 15 hours work per week | Contract employee | Spouse | Children | Child age | Cash  | Investment properties | Shares | Superannuation balances | Other investment assets | Mortgage | Car loan | Credit card | Other loans | Existing Death | Existing TPD | Existing IP | Calculated Death | Calculated TPD | Calculated IP |
      | 30  | Female | 35000:Per year  | Nurse [dental]                        | No                               | No                | No     | 0        | 0         | 2000  | 0                     | 0      | 18000                   | 0                       | 0        | 5000     | 2000        | 0           | 0              | 0            | 0           | 0                | 57000          | 2479.73          |
      | 40  | Male   | 80000:Per year  | Actor                                 | No                               | No                | Yes    | 1        | 5         | 20000 | 0                     | 0      | 50000                   | 10000                   | 400000   | 0        | 5000        | 20000       | 70000          | 0            | 850         | 1842865          | 1902865        | 5666.67          |
      | 50  | Male   | 150000:Per year | Business Analyst [tertiary qualified] | No                               | No                | Yes    | 3        | 10        | 50000 | 600000                | 50000  | 200000                  | 30000                   | 700000   | 30000    | 10000       | 0           | 200000         | 200000       | 850         | 2058925          | 2118925        | 10625.00         |

  @LQA-300 @LQA-298 @NeedsSum-TPD-Modal-SaveAndClose @psy
  Scenario Outline: NeedsBreakdown- SaveAndClose TPD complete-Authenticated
    Given I am an authenticated member
    And the following user data:
      | Age   | Gender   | Income   | Occupation   | Less than 15 hours work per week   | Contract employee   | Spouse   | Children   | Child age   | Cash   | Investment properties   | Shares   | Superannuation balances   | Other investment assets   | Mortgage   | Car loan   | Credit card   | Other loans   | Existing Death   | Existing TPD   | Existing IP   |Calculated Death | Calculated TPD | Calculated IP |
      | <Age> | <Gender> | <Income> | <Occupation> | <Less than 15 hours work per week> | <Contract employee> | <Spouse> | <Children> | <Child age> | <Cash> | <Investment properties> | <Shares> | <Superannuation balances> | <Other investment assets> | <Mortgage> | <Car loan> | <Credit card> | <Other loans> | <Existing Death> | <Existing TPD> | <Existing IP> |<Calculated Death> | <Calculated TPD> | <Calculated IP> |
    And I am on Needs Summary page
    And I click on TPD See the Breakdown button
    Then I should see TPD modal is open
    And I should be able to see TPD cover details
    And Verify TPD CoverDetails values of all blocks
    And Edit All Blocks values of TPD modal
    And Verify TPD Coverdetails complete
    And I click  SaveAndClose button on TPD modal
    Then Verify TPD CoverDetails after SaveAndClose
    Examples:
      | Age | Gender | Income          | Occupation                            | Less than 15 hours work per week | Contract employee | Spouse | Children | Child age | Cash  | Investment properties | Shares | Superannuation balances | Other investment assets | Mortgage | Car loan | Credit card | Other loans | Existing Death | Existing TPD | Existing IP | Calculated Death | Calculated TPD | Calculated IP |
      | 30  | Female | 35000:Per year  | Nurse [dental]                        | No                               | No                | No     | 0        | 0         | 2000  | 0                     | 0      | 18000                   | 0                       | 0        | 5000     | 2000        | 0           | 0              | 0            | 0           | 0                | 57000          | 2479.73          |
      | 40  | Male   | 80000:Per year  | Actor                                 | No                               | No                | Yes    | 1        | 5         | 20000 | 0                     | 0      | 50000                   | 10000                   | 400000   | 0        | 5000        | 20000       | 70000          | 0            | 850         | 1842865          | 1902865        | 5666.67          |
      | 50  | Male   | 150000:Per year | Business Analyst [tertiary qualified] | No                               | No                | Yes    | 3        | 10        | 50000 | 600000                | 50000  | 200000                  | 30000                   | 700000   | 30000    | 10000       | 0           | 200000         | 200000       | 850         | 2058925          | 2118925        | 10625.00         |
