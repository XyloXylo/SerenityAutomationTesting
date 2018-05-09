Feature: Premium calculation TPD
  Verify premium calculation for TPD

  Background:
    Given I am on Home Page

  @LQA-252 @LQA-269 @UnitisedTPDPremium @manual
  Scenario Outline: Verify TPD Premium for unitised cover amount
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
    And Select random unit for Death Cover amount
    Then Verify TPD Units Premium
      | Age   | Income   | Occupation   | Manager Category   |
      | <Age> | <Income> | <Occupation> | <Manager Category> |
    Examples:
#    Rows should be uncommented to test all age range when moved to SIT/nightly builds
      | Age | Gender | Income             | Occupation                      | Manager Category | Less than 15 hours work per week | Contract employee |
      | 15  | Female | 55000:Per year     | Actor                           | Yes              | No                               | No                |
#      | 18  | Male   | 100000:Per year    | Aerospace Engineer              | No               | No                               | No                |
      | 25  | Female | 5000:Per week      | Art Restorer                    | Yes              | No                               | No                |
#      | 35  | Female | 35000:Per year     | Building Site Manager           | No               | No                               | No                |
#      | 50  | Female | 2000000:Per year   | Cinema Ticket Seller            | Yes              | No                               | No                |
#      | 61  | Female | 2000:Per fortnight | Computer-based Graphic Designer | No               | No                               | No                |
      | 69  | Female | 55000:Per year     | Draftsman                       | Yes              | No                               | No                |

  @LQA-252 @LQA-269 @FixedTPDPremium @manual
  Scenario Outline: Verify TPD Premium for fixed cover amount
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
    And Select random unit for Death Cover amount
    And Read Death TPD Fixed Values
    Then Verify TPD Fixed Premium
      | Age   | Income   | Occupation   | Manager Category   |
      | <Age> | <Income> | <Occupation> | <Manager Category> |
    Examples:
#    Rows should be uncommented to test all age range when moved to SIT/nightly builds
      | Age | Gender | Income             | Occupation                      | Manager Category | Less than 15 hours work per week | Contract employee |
      | 15  | Female | 55000:Per year     | Computer-based Graphic Designer | Yes              | No                               | No                |
#      | 16  | Female | 55000:Per year     | Draftsman                       | No               | No                               | No                |
#      | 20  | Female | 55000:Per year     | Draftsman                       | Yes              | No                               | No                |
      | 25  | Female | 55000:Per year     | Actor                           | No               | No                               | No                |
#      | 30  | Female | 55000:Per year     | Draftsman                       | Yes              | No                               | No                |
#      | 36  | Female | 55000:Per year     | Actor                           | No               | No                               | No                |
#      | 44  | Female | 55000:Per year     | Computer-based Graphic Designer | Yes              | No                               | No                |
#      | 47  | Female | 55000:Per year     | Computer-based Graphic Designer | No               | No                               | No                |
#      | 50  | Female | 55000:Per year     | Actor                           | Yes              | No                               | No                |
      | 65  | Female | 2000:Per fortnight | Computer-based Graphic Designer | No               | No                               | No                |
      | 69  | Female | 55000:Per year     | Draftsman                       | Yes              | No                               | No                |