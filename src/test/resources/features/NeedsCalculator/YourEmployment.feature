Feature: Your Employment
  Enter personal question(About you)

  Background:
    Given I am on Home Page
    And I am an public member
    And I am on Insurance Needs Calculator Welcome Page
    When I click Calculate My Cover
    Then I close disclaimer

  @LQA-188 @YourEmployment
  Scenario Outline: Verify Your Employment
    Given I am on Needs Calculator form
    Then I enter Date of Birth <Age> and Select gender <Gender>
    And I enter employment details
      | Income   | Occupation   | Less than 15 hours work per week   | Contract employee   |
      | <Income> | <Occupation> | <Less than 15 hours work per week> | <Contract employee> |
    Examples:
      | Age | Gender | Income             | Occupation                            | Less than 15 hours work per week | Contract employee |
      | 25  | Female | 2500:Per week      | Business Analyst [tertiary qualified] | Yes                              | Yes               |
      | 52  | Male   | 5000:Per fortnight | Accounts Clerk                        | No                               | No                |
      | 25  | Male   | 10000:Per month    | Actor                                 | Yes                              | No                |
      | 52  | Male   | 120000:Per year    | Accounts Clerk                        | No                               | Yes               |

  @LQA-195 @NeedsOccupationBehaviour
  Scenario Outline: Verify Occupation Behaviour
    Given I am on Needs Calculator form
    Then I enter Date of Birth <Age> and Select gender <Gender>
    And I enter employment details to verify occupation behavior
      | Income   | Occupation   | Less than 15 hours work per week   | Contract employee   | OccupationScale   |
      | <Income> | <Occupation> | <Less than 15 hours work per week> | <Contract employee> | <OccupationScale> |
    Examples:
      | Age | Gender | Income             | Occupation                                 | Less than 15 hours work per week | Contract employee | OccupationScale |
      | 25  | Female | 2500:Per week      | Bus::Business Analyst [tertiary qualified] | Yes                              | Yes               |                 |
      | 52  | Male   | 5000:Per fortnight | Acc::Accounts Clerk                        | No                               | No                |                 |
      | 25  | Male   | 10000:Per month    | Actor                                      | Yes                              | No                |                 |
      | 25  | Female | 2500:Per week      | Transformer::Not in listed                 | Yes                              | Yes               | Standard        |
      | 25  | Female | 2500:Per week      | Transformer::Not in listed                 | Yes                              | Yes               | Management      |

  @LQA-361
  Scenario: Verify Management category wording update
    Given I am on Needs Calculator form
    And This text is displayed If your occupation is considered to be management or executive, you may be eligible to reduce the premiums you pay.
    And This text is displayed I confirm I meet all of the following criteria:
    And This text is displayed I work at least at least 15 hours per week on an ongoing basis,
    And This text is displayed I earn a gross income of $100,000 per annum (or more) (pro rata for part time),
    And This text is displayed The duties of my occupation are limited to professional or managerial duties which do not involve manual work, and I spend a minimum of 80% of my working hours within an office environment, and
    And This text is displayed I belong to at least one of the following categories:
    And This text is displayed I hold a tertiary qualification which is necessary for my role, or
    And This text is displayed I am a member of a professional institute or body which is necessary for my role, or
    And This text is displayed I have 10 years of service in a senior management or executive role.
    And This text is displayed Am I eligible?