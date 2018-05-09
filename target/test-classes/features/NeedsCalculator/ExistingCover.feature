Feature: Existing Cover
  Enter Existing Cover

  Background:
    Given I am on Home Page

  @LQA-173 @ExistingCover
  Scenario Outline: Validate Existing Cover
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
    Examples:
      | Age | Gender | Income             | Occupation  | Less than 15 hours work per week | Contract employee | Spouse | Children | Child age | Cash  | Investment properties | Shares | Superannuation balances | Other investment assets | Mortgage | Car loan | Credit card | Other loans | Existing Death | Existing TPD | Existing IP |
      | 50  | Male   | 5000:Per fortnight | Accounts Clerk | No                               | No                | No     | 0        | 0         | 50000 | 60000                 | 10000  | 50000                   | 15000                   | 550000   | 10000    | 2000        | 3000        | 10000          | 300000       | 3000        |