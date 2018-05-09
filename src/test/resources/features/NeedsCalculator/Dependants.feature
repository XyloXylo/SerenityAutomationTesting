Feature: Dependants
  Enter Dependants

  Background:
    Given I am on Home Page

  @LQA-171 @Dependants
  Scenario Outline: Select Dependants
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
    Examples:
      | Age | Gender | Income             | Occupation  | Less than 15 hours work per week | Contract employee | Spouse | Children | Child age |
      | 25  | Male   | 2500:Per week      | Accounts Clerk | No                               | No                | Yes    | 2        | 2         |
      | 30  | Male   | 5000:Per fortnight | Accounts Clerk | No                               | Yes               | Yes    | 3        | 2         |
      | 35  | Male   | 10000:Per month    | Accounts Clerk | Yes                              | No                | Yes    | 4        | 2         |
      | 40  | Male   | 120000:Per year    | Accounts Clerk | Yes                              | No                | Yes    | 5        | 2         |
      | 45  | Male   | 2500:Per week      | Accounts Clerk | No                               | Yes               | Yes    | 0        | 0         |
      | 50  | Male   | 5000:Per fortnight | Accounts Clerk | No                               | No                | No     | 0        | 0         |
