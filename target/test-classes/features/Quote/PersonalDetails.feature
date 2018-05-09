Feature: Personal Details
  Verify Quote Personal Details

  Background:
    Given I am on Home Page
#    Given I am on Quote Page
#    Then I click Get A Quote

  @LQA-19 @QuotePersonalDetails @Poonam
  Scenario Outline: Quote Personal Details
    Given I am an public member
    And I am on Quote page
    Given I am on Quote form
    Then I enter Date of Birth <Age> and Select gender <Gender>
    And I enter employment details
      | Income   | Occupation   | Less than 15 hours work per week   | Contract employee   |
      | <Income> | <Occupation> | <Less than 15 hours work per week> | <Contract employee> |
    And click Next
    Examples:
      | Age | Gender          | Income             | Occupation     | Less than 15 hours work per week | Contract employee |
      | 25  | Female          | 2500:Per week      | Actor          | Yes                              | Yes               |
      | 52  | Male            | 5000:Per fortnight | Accounts Clerk | No                               | No                |
      | 25  | Intersex:Male   | 10000:Per month    | Actor          | Yes                              | No                |
      | 52  | Intersex:Female | 120000:Per year    | Accounts Clerk | No                               | Yes               |

  @LQA-195 @QuoteOccupationBehaviour
  Scenario Outline: Quote Occupation Behavior
    Given I am an public member
    And I am on Quote page
    Given I am on Quote form
    Then I enter Date of Birth <Age> and Select gender <Gender>
    And I enter employment details to verify occupation behavior
      | Income   | Occupation   | Less than 15 hours work per week   | Contract employee   | OccupationScale   |
      | <Income> | <Occupation> | <Less than 15 hours work per week> | <Contract employee> | <OccupationScale> |
    Examples:
      | Age | Gender | Income             | Occupation                                 | Less than 15 hours work per week | Contract employee | OccupationScale |
      | 25  | Female | 2500:Per week      | Bus::Business Analyst [tertiary qualified] | Yes                              | Yes               |                 |
      | 52  | Male   | 5000:Per fortnight | Acc::Accounts Clerk                        | No                               | No                |                 |
      | 25  | Male   | 10000:Per month    | Actor                                      | Yes                              | No                |                 |
      | 25  | Female | 2500:Per week      | Transformer::Not in list                 | Yes                              | Yes               | Standard        |
      | 25  | Female | 2500:Per week      | Transformer::Not in list                 | Yes                              | Yes               | Management      |