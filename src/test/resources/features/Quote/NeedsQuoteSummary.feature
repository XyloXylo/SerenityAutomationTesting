Feature: Needs quote summary
  Verify Quote Summary Details when user comes from Needs calculator

  Background:
    Given I am on Home Page


  @LQA-29 @LQA-233 @LQA-250 @QuoteSummary_Needsfirst @VerifyCoverAmount
  Scenario Outline: Quote Summary from Needs, Verify Death units cover amount for Death TPD and IP
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
    And Switch to default Page
    Then I click Get A Quote
    Then This text is displayed Choose your cover
    Then This text is displayed Select the cover that suits your situation from death, TPD and income protection you'd like.
    Then This text is displayed These amounts are paid for through your super fund.
    Then This text is displayed If you're not sure, use our
    # Below step is commented. Unitised and Fixed Cover listbox is replaced by autocomplete textfield
#    Then Verify Cover Amount
#      | Age   | Income   |
#      | <Age> | <Income> |
    Examples:
      | Age | Gender | Income             | Occupation     | Less than 15 hours work per week | Contract employee | Spouse | Children | Child age | Cash | Investment properties | Shares | Superannuation balances | Other investment assets | Mortgage | Car loan | Credit card | Other loans | Existing Death | Existing TPD | Existing IP | Calculated Death | Calculated TPD | Calculated IP |
      | 30  | Female | 35000:Per year     | Nurse [dental] | No                               | No                | No     | 0        | 0         | 2000 | 0                     | 0      | 18000                   | 0                       | 0        | 5000     | 2000        | 0           | 0              | 0            | 0           | 0                | 57000          | 2480          |
      | 60  | Male   | 10000:Per month    | Actor          | No                               | No                | No     | 0        | 0         | 2000 | 0                     | 0      | 18000                   | 0                       | 0        | 5000     | 2000        | 0           | 0              | 0            | 0           | 0                | 57000          | 2480          |
      | 15  | Male   | 2000:Per fortnight | Nurse [dental] | No                               | No                | No     | 0        | 0         | 2000 | 0                     | 0      | 18000                   | 0                       | 0        | 5000     | 2000        | 0           | 0              | 0            | 0           | 0                | 57000          | 2480          |
      | 45  | Male   | 1000:Per week      | Nurse [dental] | No                               | No                | No     | 0        | 0         | 2000 | 0                     | 0      | 18000                   | 0                       | 0        | 5000     | 2000        | 0           | 0              | 0            | 0           | 0                | 57000          | 2480          |

  @LQA-208 @LQA-238
  Scenario Outline: Quote Summary from Needs, Death Cover amount for fixed values
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
    And Switch to default Page
    Then I click Get A Quote
    Then This text is displayed Choose your cover
    Then This text is displayed Select the cover that suits your situation from death, TPD and income protection you'd like.
    Then This text is displayed These amounts are paid for through your super fund.
    Then This text is displayed If you're not sure, use our
    # Below step is commented. Unitised and Fixed Cover listbox is replaced by autocomplete textfield
#    Then Verify Cover Amount
#      | Age   | Income   |
#      | <Age> | <Income> |
#    And I Verify Death Cover amount values for fixed cover type
#    And I Verify TPD Cover amount values for fixed cover type
#    Then Verify Cover Amount
#      | Age   | Income   |
#      | <Age> | <Income> |
    Examples:
      | Age | Gender | Income         | Occupation     | Less than 15 hours work per week | Contract employee | Spouse | Children | Child age | Cash | Investment properties | Shares | Superannuation balances | Other investment assets | Mortgage | Car loan | Credit card | Other loans | Existing Death | Existing TPD | Existing IP | Calculated Death | Calculated TPD | Calculated IP |
      | 25  | Female | 35000:Per year | Nurse [dental] | No                               | No                | No     | 0        | 0         | 2000 | 0                     | 0      | 18000                   | 0                       | 0        | 5000     | 2000        | 0           | 0              | 0            | 0           | 0                | 57000          | 2480          |

  @LQA-218 @LQA-311 @LQA-420 @LQA-333 @PopulateQuoteSummaryFromNeeds
  Scenario Outline: NeedSummary Populate cover amount from needs
    Given I am an public member
    And the following user data:
      | Age   | Gender   | Income   | Occupation   | Less than 15 hours work per week   | Contract employee   | Spouse   | Children   | Child age   | Cash   | Investment properties   | Shares   | Superannuation balances   | Other investment assets   | Mortgage   | Car loan   | Credit card   | Other loans   | Existing Death   | Existing TPD   | Existing IP   |
      | <Age> | <Gender> | <Income> | <Occupation> | <Less than 15 hours work per week> | <Contract employee> | <Spouse> | <Children> | <Child age> | <Cash> | <Investment properties> | <Shares> | <Superannuation balances> | <Other investment assets> | <Mortgage> | <Car loan> | <Credit card> | <Other loans> | <Existing Death> | <Existing TPD> | <Existing IP> |
    And I am on Needs Summary page
    Then I click Get A Quote
    And Read Death TPD IP Units
      | Age   | Income   |
      | <Age> | <Income> |
    Then Verify Cover details populated from needs summary
    Examples:
      | Age | Gender | Income          | Occupation                            | Less than 15 hours work per week | Contract employee | Spouse | Children | Child age | Cash  | Investment properties | Shares | Superannuation balances | Other investment assets | Mortgage | Car loan | Credit card | Other loans | Existing Death | Existing TPD | Existing IP |
      | 30  | Female | 35000:Per year  | Nurse [dental]                        | No                               | No                | No     | 0        | 0         | 2000  | 0                     | 0      | 18000                   | 0                       | 0        | 5000     | 2000        | 0           | 0              | 0            | 0           |
      | 40  | Male   | 80000:Per year  | Actor                                 | No                               | No                | Yes    | 1        | 5         | 20000 | 0                     | 0      | 50000                   | 10000                   | 400000   | 0        | 5000        | 20000       | 70000          | 0            | 850         |
      | 50  | Male   | 150000:Per year | Business Analyst [tertiary qualified] | No                               | No                | Yes    | 3        | 10        | 50000 | 600000                | 50000  | 200000                  | 30000                   | 700000   | 30000    | 10000       | 0           | 200000         | 200000       | 850         |

  @LQA-311 @LQA-420 @LQA-333 @PopulateQuoteSummaryFromNeeds
  Scenario Outline: Needs first Quote Summary diplay conditions for auth member
    Given I am an authenticated member
    And the following user data:
      | Age   | Gender   | Income   | Occupation   | Less than 15 hours work per week   | Contract employee   | Spouse   | Children   | Child age   | Cash   | Investment properties   | Shares   | Superannuation balances   | Other investment assets   | Mortgage   | Car loan   | Credit card   | Other loans   | Existing Death   | Existing TPD   | Existing IP   |
      | <Age> | <Gender> | <Income> | <Occupation> | <Less than 15 hours work per week> | <Contract employee> | <Spouse> | <Children> | <Child age> | <Cash> | <Investment properties> | <Shares> | <Superannuation balances> | <Other investment assets> | <Mortgage> | <Car loan> | <Credit card> | <Other loans> | <Existing Death> | <Existing TPD> | <Existing IP> |
    And I am on Needs Summary page
    Then I click Get A Quote
    And Read Death TPD IP Units
      | Age   | Income   |
      | <Age> | <Income> |
    Then Verify Cover details populated from needs summary
    Examples:
      | Age | Gender | Income          | Occupation                            | Less than 15 hours work per week | Contract employee | Spouse | Children | Child age | Cash  | Investment properties | Shares | Superannuation balances | Other investment assets | Mortgage | Car loan | Credit card | Other loans | Existing Death | Existing TPD | Existing IP |
      | 35 | Male   | 100000:Per year | Business Analyst [tertiary qualified] | No                               | No                | Yes    | 3        | 10        | 50000 | 600000                | 50000  | 200000                  | 30000                   | 700000   | 30000    | 10000       | 0           | 150000         | 100000       | 650         |

  @LQA-311 @LQA-446
  Scenario Outline:  Quote Summary diplay conditions for auth member
    Given I am an authenticated member
    And the following user data:
      | Age   | Gender   | Income   | Occupation   | Less than 15 hours work per week   | Contract employee   |
      | <Age> | <Gender> | <Income> | <Occupation> | <Less than 15 hours work per week> | <Contract employee> |
    And I am on Quote Summary page
    And Read Death TPD IP Units
      | Age   | Income   |
      | <Age> | <Income> |
    And Select random unit for Death Cover amount
    And Select random unit for TPD Cover amount
    And Select random unit for IP Cover amount
    And Verify Quote Summary cover amount fields
    And Verify Remove buttons
    Examples:
      | Age | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee |
      | 35  | Female | 55000:Per year | Actor      | No                               | No                |

  @LQA-311
  Scenario Outline:  Quote Summary diplay conditions for public member
    Given I am an public member
    And the following user data:
      | Age   | Gender   | Income   | Occupation   | Less than 15 hours work per week   | Contract employee   |
      | <Age> | <Gender> | <Income> | <Occupation> | <Less than 15 hours work per week> | <Contract employee> |
    And I am on Quote Summary page
    And Read Death TPD IP Units
      | Age   | Income   |
      | <Age> | <Income> |
    And Select random unit for Death Cover amount
    And Verify Quote Summary cover amount fields
    Examples:
      | Age | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee |
      | 35  | Female | 55000:Per year | Actor      | No                               | No                |

  @LQA-28 @LQA-221 @EditPersonalDetailsFromNeedsSummary @noor
  Scenario Outline: Edit personal details
    Given I am an authenticated member
    And the following user data:
      | Age   | Gender   | Income   | Occupation   | Less than 15 hours work per week   | Contract employee   | Spouse   | Children   | Child age   | Cash   | Investment properties   | Shares   | Superannuation balances   | Other investment assets   | Mortgage   | Car loan   | Credit card   | Other loans   | Existing Death   | Existing TPD   | Existing IP   |
      | <Age> | <Gender> | <Income> | <Occupation> | <Less than 15 hours work per week> | <Contract employee> | <Spouse> | <Children> | <Child age> | <Cash> | <Investment properties> | <Shares> | <Superannuation balances> | <Other investment assets> | <Mortgage> | <Car loan> | <Credit card> | <Other loans> | <Existing Death> | <Existing TPD> | <Existing IP> |
    And I am on Needs Summary page
    When I click Previous
    Then Verify previous existing cover details populated
    And I click Back on Existing Cover page
    And Verify previous assets and debts details populated
    And I click Back on Assets page
    And Verify previous <Spouse> and <Children> details populated with youngest child being <Child age>
    And I click Back on Dependants page
    And Verify previous personal details populated

    Given the following user data:
      | New Age   | New Gender   | New Income   | New Occupation   | New Less than 15 hours work per week   | New Contract employee   | New Spouse   | New Children   | New Child age   | New Cash   | New Investment properties   | New Shares   | New Superannuation balances   | New Other investment assets   | New Mortgage   | New Car loan   | New Credit card   | New Other loans   | New Existing Death   | New Existing TPD   | New Existing IP   |
      | <New Age> | <New Gender> | <New Income> | <New Occupation> | <New Less than 15 hours work per week> | <New Contract employee> | <New Spouse> | <New Children> | <New Child age> | <New Cash> | <New Investment properties> | <New Shares> | <New Superannuation balances> | <New Other investment assets> | <New Mortgage> | <New Car loan> | <New Credit card> | <New Other loans> | <New Existing Death> | <New Existing TPD> | <New Existing IP> |
    And I update data from About You page
    When I click Previous
    Then Verify updated existing cover details populated
    And I click Back on Existing Cover page
    And Verify updated assets and debts details populated
    And I click Back on Assets page
    And Verify updated <New Spouse> and <New Children> details populated with youngest child being <New Child age>
    And I click Back on Dependants page
    And Verify updated personal details populated

    Examples:
      | Age | Gender | Income          | Occupation                            | Less than 15 hours work per week | Contract employee | Spouse | Children | Child age | Cash  | Investment properties | Shares | Superannuation balances | Other investment assets | Mortgage | Car loan | Credit card | Other loans | Existing Death | Existing TPD | Existing IP | New Age   | New Gender   | New Income   | New Occupation   | New Less than 15 hours work per week   | New Contract employee   | New Spouse   | New Children   | New Child age   | New Cash   | New Investment properties   | New Shares   | New Superannuation balances   | New Other investment assets   | New Mortgage   | New Car loan   | New Credit card   | New Other loans   | New Existing Death   | New Existing TPD   | New Existing IP   |
      | 35 | Male   | 100000:Per year | Business Analyst [tertiary qualified] | No                               | No                | Yes    | 3        | 10        | 50000 | 600000                | 50000  | 200000                  | 30000                   | 700000   | 30000    | 10000       | 0           | 150000         | 100000       | 650         | 37 | Male   | 120000:Per year | Business Analyst [tertiary qualified] | Yes                               | Yes                | Yes    | 3        | 10        | 55000 | 650000                | 60000  | 230000                  | 37000                   | 720000   | 33000    | 11000       | 0           | 140000         | 125000       | 675         |

  @LQA-430 @DisplayQuotes @noor
  Scenario Outline: Display quotes
    Given I am an authenticated member
    And the following user data:
      | Age   | Gender   | Income   | Occupation   | Less than 15 hours work per week   | Contract employee   | Spouse   | Children   | Child age   | Cash   | Investment properties   | Shares   | Superannuation balances   | Other investment assets   | Mortgage   | Car loan   | Credit card   | Other loans   | Existing Death   | Existing TPD   | Existing IP   |
      | <Age> | <Gender> | <Income> | <Occupation> | <Less than 15 hours work per week> | <Contract employee> | <Spouse> | <Children> | <Child age> | <Cash> | <Investment properties> | <Shares> | <Superannuation balances> | <Other investment assets> | <Mortgage> | <Car loan> | <Credit card> | <Other loans> | <Existing Death> | <Existing TPD> | <Existing IP> |
    And I am on Quote Summary page
    And Read Death TPD IP Units
      | Age   | Income   |
      | <Age> | <Income> |
    And Select random unit for Death Cover amount
    And Select random unit for TPD Cover amount
    And Select random unit for IP Cover amount
#    And I am on Needs Summary page
    And I save the quote from quote summary
    And Close the save quote modal
    Then Saved quote should be displayed on dashboard

    Examples:
      | Age | Gender | Income          | Occupation                            | Less than 15 hours work per week | Contract employee | Spouse | Children | Child age | Cash  | Investment properties | Shares | Superannuation balances | Other investment assets | Mortgage | Car loan | Credit card | Other loans | Existing Death | Existing TPD | Existing IP | New Age   | New Gender   | New Income   | New Occupation   | New Less than 15 hours work per week   | New Contract employee   | New Spouse   | New Children   | New Child age   | New Cash   | New Investment properties   | New Shares   | New Superannuation balances   | New Other investment assets   | New Mortgage   | New Car loan   | New Credit card   | New Other loans   | New Existing Death   | New Existing TPD   | New Existing IP   |
      | 35 | Male   | 100000:Per year | Business Analyst [tertiary qualified] | No                               | No                | Yes    | 3        | 10        | 50000 | 600000                | 50000  | 200000                  | 30000                   | 700000   | 30000    | 10000       | 0           | 150000         | 100000       | 650         | 37 | Male   | 120000:Per year | Business Analyst [tertiary qualified] | Yes                               | Yes                | Yes    | 3        | 10        | 55000 | 650000                | 60000  | 230000                  | 37000                   | 720000   | 33000    | 11000       | 0           | 140000         | 125000       | 675         |
