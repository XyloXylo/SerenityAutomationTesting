Feature: Premium calculation death
  Verify premium calculation for Death

  Background:
    Given I am on Home Page

  @LQA-251 @LQA-269 @UnitisedDeathPremium
  Scenario Outline: Verify Death Premium for unitised cover amount
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
    Examples:
#    Rows should be uncommented to test all age range when moved to SIT/nightly builds
      | Age | Gender | Income             | Occupation                      | Manager Category | Less than 15 hours work per week | Contract employee |
      | 15  | Female | 55000:Per year     | Actor                           | Yes              | No                               | No                |
#      | 17  | Male   | 100000:Per year    | Aerospace Engineer              | No               | No                               | No                |
      | 25  | Female | 5000:Per week      | Art Restorer                    | Yes              | No                               | No                |
#      | 49  | Female | 35000:Per year     | Building Site Manager           | No               | No                               | No                |
#      | 55  | Female | 2000000:Per year   | Cinema Ticket Seller            | Yes              | No                               | No                |
#      | 65  | Female | 2000:Per fortnight | Computer-based Graphic Designer | No               | No                               | No                |
      | 74  | Female | 55000:Per year     | Draftsman                       | Yes              | No                               | No                |

  @LQA-251 @LQA-269 @FixedDeathPremium
  Scenario Outline: Verify Death Premium for fixed cover amount
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
    Examples:
#    Rows should be uncommented to test all age range when moved to SIT/nightly builds
      | Age | Gender | Income             | Occupation                      | Manager Category | Less than 15 hours work per week | Contract employee |
      | 15  | Female | 55000:Per year     | Actor                           | Yes              | No                               | No                |
#      | 16  | Female | 55000:Per year     | Draftsman                       | No               | No                               | No                |
#      | 20  | Female | 55000:Per year     | Draftsman                       | Yes              | No                               | No                |
      | 25  | Female | 55000:Per year     | Actor                           | No               | No                               | No                |
#      | 30  | Female | 55000:Per year     | Draftsman                       | Yes              | No                               | No                |
#      | 36  | Female | 55000:Per year     | Actor                           | No               | No                               | No                |
#      | 44  | Female | 55000:Per year     | Computer-based Graphic Designer | Yes              | No                               | No                |
#      | 47  | Female | 55000:Per year     | Computer-based Graphic Designer | No               | No                               | No                |
#      | 50  | Female | 55000:Per year     | Actor                           | Yes              | No                               | No                |
      | 65  | Female | 2000:Per fortnight | Computer-based Graphic Designer | No               | No                               | No                |
      | 74  | Female | 55000:Per year     | Draftsman                       | Yes              | No                               | No                |