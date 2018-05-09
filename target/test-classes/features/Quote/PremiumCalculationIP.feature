Feature: Premium calculation IP
  Verify premium calculation for IP

  Background:
    Given I am on Home Page

  @LQA-253 @LQA-269 @IPPremium
  Scenario Outline: Verify IP Premium for unitised cover amount
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
    Then Verify IP Premium
      | Age   | Income   | Occupation   | Manager Category   | Waiting Period   |
      | <Age> | <Income> | <Occupation> | <Manager Category> | <Waiting Period> |
    Examples:
#    Rows should be uncommented to test all age range when moved to SIT/nightly builds
      | Age | Gender | Income          | Occupation | Manager Category | Less than 15 hours work per week | Contract employee | Waiting Period |
      | 15  | Female | 55000:Per year  | Actor      | Yes              | No                               | No                | 30             |
      | 25  | Female | 55000:Per month | Draftsman  | No               | No                               | No                | 30             |
#      | 34  | Male   | 10000:Per month    | Computer-based Graphic Designer | Yes              | No                               | No                | 30             |
#      | 53  | Male   | 2000:Per fortnight | Building Site Manager           | No               | No                               | No                | 30             |
#      | 60  | Male   | 1000:Per week      | Art Restorer                    | Yes              | No                               | No                | 30             |
      | 66  | Male   | 100000:Per week | Actor      | No               | No                               | No                | 30             |

  @LQA-308 @LQA-269 @IPPremiumWaitingPeriods
  Scenario Outline: Verify IP Premium for different waiting periods
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
    Then Verify IP Premium
      | Age   | Income   | Occupation   | Manager Category   | Waiting Period |
      | <Age> | <Income> | <Occupation> | <Manager Category> | 30             |
    And I update the ip waiting period to <Waiting Period> days
    Then Verify IP Premium for benefit period update
      | Age   | Income   | Occupation   | Manager Category   | Waiting Period   |
      | <Age> | <Income> | <Occupation> | <Manager Category> | <Waiting Period> |
    Examples:
      | Age | Gender | Income           | Occupation | Manager Category | Less than 15 hours work per week | Contract employee | Waiting Period |
      | 15  | Female | 55000:Per year   | Actor      | Yes              | No                               | No                | 30             |
      | 25  | Female | 4000:Per week    | Actor      | No               | No                               | No                | 60             |
      | 35  | Female | 1000000:Per year | Actor      | Yes              | No                               | No                | 90             |
      | 44  | Female | 82000:Per year   | Actor      | No               | No                               | No                | 30             |
      | 51  | Female | 2000:Per week    | Actor      | Yes              | No                               | No                | 60             |
      | 66  | Female | 1000000:Per year | Actor      | No               | No                               | No                | 90             |

  @LQA-308 @LQA-269 @IPPremiumWaitingPeriods
  Scenario Outline: Verify IP Premium for waiting periods
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
    And I update the ip waiting period to <Waiting Period> days
    Then Verify IP Premium
      | Age   | Income   | Occupation   | Manager Category   | Waiting Period   |
      | <Age> | <Income> | <Occupation> | <Manager Category> | <Waiting Period> |
    Examples:
      | Age | Gender | Income           | Occupation | Manager Category | Less than 15 hours work per week | Contract employee | Waiting Period |
      | 15  | Female | 55000:Per year   | Actor      | No               | No                               | No                | 30             |
      | 25  | Female | 4000:Per week    | Actor      | Yes              | No                               | No                | 60             |
      | 35  | Female | 1000000:Per year | Actor      | No               | No                               | No                | 90             |