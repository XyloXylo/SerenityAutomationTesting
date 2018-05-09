Feature: Needs Summary
  Verify needs summary

  Background:
    Given I am on Home Page

  @LQA-174 @LQA-263 @LQA-202 @NeedsSummary
  Scenario Outline: Validate the needs summary
    Given I am an public member
    Given I am on Insurance Needs Calculator Welcome Page
    When I click Calculate My Cover
    Then I close disclaimer
    Given I am on Needs Calculator form
    Then I enter Date of Birth <Age> and Select gender <Gender>
    And I enter employment details
      | Income   | Occupation   | Less than 15 hours work per week   | Contract employee   |
      | <Income> | <Occupation> | <Less than 15 hours work per week> | <Contract employee> |
    And click Next
    And I have Spouse <Spouse> and <Children> children with youngest child being <Child age>
    And click Next
    And I have Assets worth
      | Cash   | Investment properties   | Shares   | Superannuation balances   | Other investment assets   |
      | <Cash> | <Investment properties> | <Shares> | <Superannuation balances> | <Other investment assets> |
    And I have Debts of value
      | Mortgage   | Car loan   | Credit card   | Other loans   |
      | <Mortgage> | <Car loan> | <Credit card> | <Other loans> |
    And click Next
    And I enter my Existing Cover
      | Existing Death   | Existing TPD   | Existing IP   |
      | <Existing Death> | <Existing TPD> | <Existing IP> |
    And I click Calculate Cover
#    Then This text is displayed "*Disclaimer information relating to the figures above. Dolor sit amet, consectetur adipiscing elit. Aenean euismod bibendum laoreet. Proin gravida dolor sit amet lacus accumsan et viverra justo commodo. Proin sodales pulvinar tempor. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus."
    Then Verify Death Cover and TPD and IP
      | Existing Death   | Existing TPD   | Existing IP   | Calculated Death   | Calculated TPD   | Calculated IP   |
      | <Existing Death> | <Existing TPD> | <Existing IP> | <Calculated Death> | <Calculated TPD> | <Calculated IP> |
    Then Verify Needs Summary for buttons and links
    And Switch to default Page
    Then I click Get A Quote
    Examples:
      | Age | Gender | Income          | Occupation                            | Less than 15 hours work per week | Contract employee | Spouse | Children | Child age | Cash  | Investment properties | Shares | Superannuation balances | Other investment assets | Mortgage | Car loan | Credit card | Other loans | Existing Death | Existing TPD | Existing IP | Calculated Death | Calculated TPD | Calculated IP |
      | 30  | Female | 35000:Per year  | Nurse [dental]                        | No                               | No                | No     | 0        | 0         | 2000  | 0                     | 0      | 18000                   | 0                       | 0        | 5000     | 2000        | 0           | 0              | 0            | 0           | 0                | 57000          | 2480          |
      | 40  | Male   | 80000:Per year  | Actor                                 | No                               | No                | Yes    | 1        | 5         | 20000 | 0                     | 0      | 50000                   | 10000                   | 400000   | 0        | 5000        | 20000       | 70000          | 0            | 850         | 1842865          | 1902865        | 5667          |
      | 50  | Male   | 150000:Per year | Business Analyst [tertiary qualified] | No                               | No                | Yes    | 3        | 10        | 50000 | 600000                | 50000  | 200000                  | 30000                   | 700000   | 30000    | 10000       | 0           | 200000         | 200000       | 850         | 2058925          | 2118925        | 10625         |


  @LQA183 @NeedsSummary @saldi
  Scenario Outline: Validate the Assumptions and default values on needs summary
    Given I am an public member
    And the following user data:
      | Age   | Gender   | Income   | Occupation   | Less than 15 hours work per week   | Contract employee   | Spouse   | Children   | Child age   | Cash   | Investment properties   | Shares   | Superannuation balances   | Other investment assets   | Mortgage   | Car loan   | Credit card   | Other loans   | Existing Death   | Existing TPD   | Existing IP   |
      | <Age> | <Gender> | <Income> | <Occupation> | <Less than 15 hours work per week> | <Contract employee> | <Spouse> | <Children> | <Child age> | <Cash> | <Investment properties> | <Shares> | <Superannuation balances> | <Other investment assets> | <Mortgage> | <Car loan> | <Credit card> | <Other loans> | <Existing Death> | <Existing TPD> | <Existing IP> |
    And I am on Needs Summary page
    When I look at the Assumptions
    Then this text is displayed on Calculator Assumptions modal Below are the assumptions we use to calculate your needs in combination with your inputs. You can change the values of these important assumptions below:
    And the following assumptions should be listed:
      | Assumption                          | Assumption Value |
      | Death expenses                      | 10000            |
      | TPD expenses                        | 70000            |
      | Spouse/TPD future income proportion | 0.75             |
      | Age spouse/TPD income support ends  | 67               |
      | Child income proportion             | 0.2              |
      | Age child income support ends       | 20               |
      | Age gap between children            | 3                |
      | Salary Inflation                    | 0.035            |
      | Interest Rate                       | 0.055            |
      | Super default                       | 0.1              |
    Examples:
      | Age | Gender | Income         | Occupation     | Less than 15 hours work per week | Contract employee | Spouse | Children | Child age | Cash | Investment properties | Shares | Superannuation balances | Other investment assets | Mortgage | Car loan | Credit card | Other loans | Existing Death | Existing TPD | Existing IP |
      | 30  | Female | 35000:Per year | Nurse [dental] | No                               | No                | No     | 0        | 0         | 2000 | 0                     | 0      | 18000                   | 0                       | 0        | 5000     | 2000        | 0           | 0              | 0            | 0           |

  @LQA183 @NeedsSummary @saldi
  Scenario Outline: Validate the user should be able to save values on Assumptions
    Given I am an public member
    And the following user data:
      | Age   | Gender   | Income   | Occupation   | Less than 15 hours work per week   | Contract employee   | Spouse   | Children   | Child age   | Cash   | Investment properties   | Shares   | Superannuation balances   | Other investment assets   | Mortgage   | Car loan   | Credit card   | Other loans   | Existing Death   | Existing TPD   | Existing IP   |
      | <Age> | <Gender> | <Income> | <Occupation> | <Less than 15 hours work per week> | <Contract employee> | <Spouse> | <Children> | <Child age> | <Cash> | <Investment properties> | <Shares> | <Superannuation balances> | <Other investment assets> | <Mortgage> | <Car loan> | <Credit card> | <Other loans> | <Existing Death> | <Existing TPD> | <Existing IP> |
    And I am on Needs Summary page
    When I look at the Assumptions
    And enter the following assumption values replacing the default
      | Assumption                          | Assumption Value |
      | Death expenses                      | 20000            |
      | TPD expenses                        | 3000             |
      | Spouse/TPD future income proportion | 0.15             |
      | Age spouse/TPD income support ends  | 52               |
      | Child income proportion             | 0.1              |
      | Age child income support ends       | 10               |
      | Age gap between children            | 5                |
      | Salary Inflation                    | 0.012            |
      | Interest Rate                       | 0.024            |
      | Super default                       | 0.2              |
    When I save the assumption values
    Then the user should be presented with Needs Summary page from Assumptions
    Examples:
      | Age | Gender | Income         | Occupation     | Less than 15 hours work per week | Contract employee | Spouse | Children | Child age | Cash | Investment properties | Shares | Superannuation balances | Other investment assets | Mortgage | Car loan | Credit card | Other loans | Existing Death | Existing TPD | Existing IP |
      | 30  | Female | 35000:Per year | Nurse [dental] | No                               | No                | No     | 0        | 0         | 2000 | 0                     | 0      | 18000                   | 0                       | 0        | 5000     | 2000        | 0           | 0              | 0            | 0           |


  @LQA183 @NeedsSummary @saldi
  Scenario Outline: Validate the user should be able to reset values on Assumptions
    Given I am an public member
    And the following user data:
      | Age   | Gender   | Income   | Occupation   | Less than 15 hours work per week   | Contract employee   | Spouse   | Children   | Child age   | Cash   | Investment properties   | Shares   | Superannuation balances   | Other investment assets   | Mortgage   | Car loan   | Credit card   | Other loans   | Existing Death   | Existing TPD   | Existing IP   |
      | <Age> | <Gender> | <Income> | <Occupation> | <Less than 15 hours work per week> | <Contract employee> | <Spouse> | <Children> | <Child age> | <Cash> | <Investment properties> | <Shares> | <Superannuation balances> | <Other investment assets> | <Mortgage> | <Car loan> | <Credit card> | <Other loans> | <Existing Death> | <Existing TPD> | <Existing IP> |
    And I am on Needs Summary page
    And I look at the Assumptions
    And enter the following assumption values replacing the default
      | Assumption                          | Assumption Value |
      | Death expenses                      | 20000            |
      | TPD expenses                        | 3000             |
      | Spouse/TPD future income proportion | 0.15             |
      | Age spouse/TPD income support ends  | 52               |
      | Child income proportion             | 0.1              |
      | Age child income support ends       | 10               |
      | Age gap between children            | 5                |
      | Salary Inflation                    | 0.012            |
      | Interest Rate                       | 0.024            |
      | Super default                       | 0.2              |
    When I reset the assumption values
    Then the values should get reset to their default values
      | Assumption                          | Assumption Value |
      | Death expenses                      | 10000            |
      | TPD expenses                        | 70000            |
      | Spouse/TPD future income proportion | 0.75             |
      | Age spouse/TPD income support ends  | 67               |
      | Child income proportion             | 0.2              |
      | Age child income support ends       | 20               |
      | Age gap between children            | 3                |
      | Salary Inflation                    | 0.035            |
      | Interest Rate                       | 0.055            |
      | Super default                       | 0.1              |
    Examples:
      | Age | Gender | Income         | Occupation     | Less than 15 hours work per week | Contract employee | Spouse | Children | Child age | Cash | Investment properties | Shares | Superannuation balances | Other investment assets | Mortgage | Car loan | Credit card | Other loans | Existing Death | Existing TPD | Existing IP |
      | 30  | Female | 35000:Per year | Nurse [dental] | No                               | No                | No     | 0        | 0         | 2000 | 0                     | 0      | 18000                   | 0                       | 0        | 5000     | 2000        | 0           | 0              | 0            | 0           |

  @LQA183 @NeedsSummary @saldi
  Scenario Outline: Validate the user gets to see the new assumptions when saved and reopened again
    Given I am an public member
    And the following user data:
      | Age   | Gender   | Income   | Occupation   | Less than 15 hours work per week   | Contract employee   | Spouse   | Children   | Child age   | Cash   | Investment properties   | Shares   | Superannuation balances   | Other investment assets   | Mortgage   | Car loan   | Credit card   | Other loans   | Existing Death   | Existing TPD   | Existing IP   |
      | <Age> | <Gender> | <Income> | <Occupation> | <Less than 15 hours work per week> | <Contract employee> | <Spouse> | <Children> | <Child age> | <Cash> | <Investment properties> | <Shares> | <Superannuation balances> | <Other investment assets> | <Mortgage> | <Car loan> | <Credit card> | <Other loans> | <Existing Death> | <Existing TPD> | <Existing IP> |
    And I am on Needs Summary page
    When I look at the Assumptions
    And enter the following assumption values replacing the default
      | Assumption                          | Assumption Value |
      | Death expenses                      | 20000            |
      | TPD expenses                        | 3000             |
      | Spouse/TPD future income proportion | 0.15             |
      | Age spouse/TPD income support ends  | 52               |
      | Child income proportion             | 0.1              |
      | Age child income support ends       | 10               |
      | Age gap between children            | 5                |
      | Salary Inflation                    | 0.012            |
      | Interest Rate                       | 0.024            |
      | Super default                       | 0.2              |
    When I save the assumption values
    Then the user should be presented with Needs Summary page from Assumptions
    When I look at the Assumptions
    Then the following assumptions should be listed:
      | Assumption                          | Assumption Value |
      | Death expenses                      | 20000            |
      | TPD expenses                        | 3000             |
      | Spouse/TPD future income proportion | 0.15             |
      | Age spouse/TPD income support ends  | 52               |
      | Child income proportion             | 0.1              |
      | Age child income support ends       | 10               |
      | Age gap between children            | 5                |
      | Salary Inflation                    | 0.012            |
      | Interest Rate                       | 0.024            |
      | Super default                       | 0.2              |
    Examples:
      | Age | Gender | Income         | Occupation     | Less than 15 hours work per week | Contract employee | Spouse | Children | Child age | Cash | Investment properties | Shares | Superannuation balances | Other investment assets | Mortgage | Car loan | Credit card | Other loans | Existing Death | Existing TPD | Existing IP |
      | 30  | Female | 35000:Per year | Nurse [dental] | No                               | No                | No     | 0        | 0         | 2000 | 0                     | 0      | 18000                   | 0                       | 0        | 5000     | 2000        | 0           | 0              | 0            | 0           |



  @LQA-187 @NeedsSummaryPersonalDetails_mandatory_fields_error_validation
  Scenario: NeedsSummary_PersonalDetails-required field validation
    Given I am an public member
    Given I am on Insurance Needs Calculator Welcome Page
    When I click Calculate My Cover
    Then I close disclaimer
    Given I am on Needs Calculator form
    And click Next
    Then Verify required fields are highlighted for PersonalDetails page


  @LQA-187 @NeedsSummaryPersonalDetails_Dateof_Birth  @psy
  Scenario Outline: NeedsSummary_PersonalDetails-date of birth
    Given I am an public member
    Given I am on Insurance Needs Calculator Welcome Page
    When I click Calculate My Cover
    Then I close disclaimer
    Given I am on Needs Calculator form
    Then I enter Date of Birth <Age> and Select gender <Gender>
    And click Next
    Then Verify DOB required fields are highlighted
    Examples:
    | Age | Gender |
    | 80   |Male  |

  @LQA-187 @NeedsSummaryPersonalDetails_INCOME  @psy
  Scenario Outline: NeedsSummary_PersonalDetails-Income
    Given I am an public member
    Given I am on Insurance Needs Calculator Welcome Page
    When I click Calculate My Cover
    Then I close disclaimer
    Given I am on Needs Calculator form
    Then I enter Date of Birth <Age> and Select gender <Gender>
    And click Next
    Then Verify INCOME required fields are highlighted
    Examples:
      | Age | Gender |
      | 25   | Male  |

  @LQA-196 @NeedsSummaryVaidation_Dependants @psy
  Scenario Outline: NeedsSummary_Dependants-Spouse and Children mandatory check
    Given I am an public member
    Given I am on Insurance Needs Calculator Welcome Page
    When I click Calculate My Cover
    Then I close disclaimer
    Given I am on Needs Calculator form
    Then I enter Date of Birth <Age> and Select gender <Gender>
    And click Next
    And I enter employment details
      | Income   | Occupation   | Less than 15 hours work per week   | Contract employee   |
      | <Income> | <Occupation> | <Less than 15 hours work per week> | <Contract employee> |
    And click Next
    Then Verify required fields are highlighted for Dependants page
    Examples:
      | Age | Gender | Income         | Occupation     | Less than 15 hours work per week   | Contract employee  |
      | 25   | Male  | 35000:Per year | Nurse [dental] | No                                 | No                |

  @LQA-196 @NeedsSummaryVaidation_Dependants_OnlyChildren @psy
  Scenario Outline: NeedsSummary_Dependants-Children mandatory check
    Given I am an public member
    Given I am on Insurance Needs Calculator Welcome Page
    When I click Calculate My Cover
    Then I close disclaimer
    Given I am on Needs Calculator form
    Then I enter Date of Birth <Age> and Select gender <Gender>
    And click Next
    And I enter employment details
      | Income   | Occupation   | Less than 15 hours work per week   | Contract employee   |
      | <Income> | <Occupation> | <Less than 15 hours work per week> | <Contract employee> |
    And click Next
    Then Verify required fields are highlighted for Dependants page
    Then I select <SpouseValue> for Spouse and click next
    Then Verify Children required fields are highlighted
    Then I select <ChildrenValue> for Children and click next
    Then Verify ChildrenNumber required fields are highlighted
    Then Verify ChildYoungAge required fields are highlighted
    Examples:
      | Age | Gender | Income         | Occupation     | Less than 15 hours work per week   | Contract employee  | SpouseValue | ChildrenValue | Child age |
      | 25   | Male  | 35000:Per year | Nurse [dental] | No                                 | No                 | No           | Yes          | 2         |
