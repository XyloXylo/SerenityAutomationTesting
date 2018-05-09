Feature: Premium Calculation Total
  Verify total premium calculation for Death, TPD and IP

  Background:
    Given I am on Home Page

  @LQA-254 @TotalPremium @CoverAmountAutoComplete(unitised)  @manual
  Scenario Outline: Verify Total Premium for unitised cover amount
    Given I am an public member
    And I am on Quote page
    Given I am on Quote form
    When I enter Date of Birth <Age> and Select gender <Gender>
    And I enter employment details
      | Income   | Occupation   | Manager Category   | Less than 15 hours work per week   | Contract employee   |
      | <Income> | <Occupation> | <Manager Category> | <Less than 15 hours work per week> | <Contract employee> |
    And click Next
    And Switch to default Page
    And Read Death TPD IP Units
      | Age   | Income   |
      | <Age> | <Income> |
    Then Verify Death Units Premium
      | Age   | Income   | Occupation   | Manager Category   |
      | <Age> | <Income> | <Occupation> | <Manager Category> |
    Then Verify TPD Units Premium
      | Age   | Income   | Occupation   | Manager Category   |
      | <Age> | <Income> | <Occupation> | <Manager Category> |
    Then Verify IP Premium
      | Age   | Income   | Occupation   | Manager Category   | Waiting Period   |
      | <Age> | <Income> | <Occupation> | <Manager Category> | <Waiting Period> |
    Then Verify Monthly Total Premium
    Then Verify Annual Total Premium
    Then Verify Weekly Total Premium
    Examples:
      | Age | Gender | Income           | Occupation | Manager Category | Less than 15 hours work per week | Contract employee | Waiting Period |
      | 15  | Female | 55000:Per year   | Business Analyst [not tertiary qualified]      | Yes              | No                               | No                | 30             |
      | 35  | Female | 1000000:Per year | Actor      | No               | No                               | No                | 30             |

  @LQA-254 @TotalPremium  @manual
  Scenario Outline: Verify Total Premium for unitised Death and fixed TPD cover amount
    Given I am an public member
    And I am on Quote page
    Given I am on Quote form
    When I enter Date of Birth <Age> and Select gender <Gender>
    And I enter employment details
      | Income   | Occupation   | Manager Category   | Less than 15 hours work per week   | Contract employee   |
      | <Income> | <Occupation> | <Manager Category> | <Less than 15 hours work per week> | <Contract employee> |
    And click Next
    And Switch to default Page
    And Read Death TPD IP Units
      | Age   | Income   |
      | <Age> | <Income> |
    Then Verify Death Units Premium
      | Age   | Income   | Occupation   | Manager Category   |
      | <Age> | <Income> | <Occupation> | <Manager Category> |
    And Read Death TPD Fixed Values
    Then Verify TPD Fixed Premium
      | Age   | Income   | Occupation   | Manager Category   |
      | <Age> | <Income> | <Occupation> | <Manager Category> |
    Then Verify Monthly Total Premium
    Then Verify Annual Total Premium
    Then Verify Weekly Total Premium
    Examples:
      | Age | Gender | Income           | Occupation | Manager Category | Less than 15 hours work per week | Contract employee |
      | 25  | Female | 1000000:Per year | Actor      | Yes              | No                               | No                |
      | 55  | Female | 1000000:Per year | Actor      | No               | No                               | No                |

  @LQA-254 @TotalPremium @manual
  Scenario Outline: Verify Total Premium for fixed Death, unitised TPD and IP cover amount
    Given I am an public member
    And I am on Quote page
    Given I am on Quote form
    When I enter Date of Birth <Age> and Select gender <Gender>
    And I enter employment details
      | Income   | Occupation   | Manager Category   | Less than 15 hours work per week   | Contract employee   |
      | <Income> | <Occupation> | <Manager Category> | <Less than 15 hours work per week> | <Contract employee> |
    And click Next
    And Switch to default Page
    And Read Death TPD Fixed Values
    Then Verify Death Fixed Premium
      | Age   | Income   | Occupation   | Manager Category   |
      | <Age> | <Income> | <Occupation> | <Manager Category> |
    And Read Death TPD IP Units
      | Age   | Income   |
      | <Age> | <Income> |
    Then Verify TPD Units Premium
      | Age   | Income   | Occupation   | Manager Category   |
      | <Age> | <Income> | <Occupation> | <Manager Category> |
    Then Verify IP Premium
      | Age   | Income   | Occupation   | Manager Category   | Waiting Period   |
      | <Age> | <Income> | <Occupation> | <Manager Category> | <Waiting Period> |
    Then Verify Monthly Total Premium
    Then Verify Annual Total Premium
    Then Verify Weekly Total Premium
    Examples:
      | Age | Gender | Income          | Occupation | Manager Category | Less than 15 hours work per week | Contract employee | Waiting Period |
      | 35  | Female | 200000:Per year | Actor      | Yes              | No                               | No                | 30             |
      | 56  | Female | 200000:Per year | Actor      | No               | No                               | No                | 30             |

  @LQA-254 @TotalPremium @manual
  Scenario Outline: Verify Total Premium from Needs
    Given I am an public member
    Given I am on Insurance Needs Calculator Welcome Page
    When I click Calculate My Cover
    Then I close disclaimer
    Given I am on Needs Calculator form
    Then I enter Date of Birth <Age> and Select gender <Gender>
    And I enter employment details
      | Income   | Occupation   | Manager Category   | Less than 15 hours work per week   | Contract employee   |
      | <Income> | <Occupation> | <Manager Category> | <Less than 15 hours work per week> | <Contract employee> |
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
    And Read Death TPD IP Units
      | Age   | Income   |
      | <Age> | <Income> |
    Then Verify Death Units Premium
      | Age   | Income   | Occupation   | Manager Category   |
      | <Age> | <Income> | <Occupation> | <Manager Category> |
    Then Verify TPD Units Premium
      | Age   | Income   | Occupation   | Manager Category   |
      | <Age> | <Income> | <Occupation> | <Manager Category> |
    Then Verify IP Premium
      | Age   | Income   | Occupation   | Manager Category   | Waiting Period   |
      | <Age> | <Income> | <Occupation> | <Manager Category> | <Waiting Period> |
    Then Verify Monthly Total Premium
    Then Verify Annual Total Premium
    Then Verify Weekly Total Premium
    Examples:
      | Age | Gender | Income          | Occupation     | Manager Category | Less than 15 hours work per week | Contract employee | Spouse | Children | Child age | Cash | Investment properties | Shares | Superannuation balances | Other investment assets | Mortgage | Car loan | Credit card | Other loans | Existing Death | Existing TPD | Existing IP | Waiting Period |
      | 25  | Male   | 120000:Per year | Nurse [dental] | Yes              | No                               | No                | No     | 0        | 0         | 2000 | 0                     | 0      | 18000                   | 0                       | 0        | 5000     | 2000        | 0           | 0              | 0            | 0           | 30             |
      | 52  | Male   | 55200:Per year  | Nurse [dental] | No               | No                               | No                | No     | 0        | 0         | 2000 | 0                     | 0      | 18000                   | 0                       | 0        | 5000     | 2000        | 0           | 0              | 0            | 0           | 30             |