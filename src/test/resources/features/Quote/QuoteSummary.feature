Feature: Quote Summary
  Verify Quote Summary Details

  Background:
    Given I am on Home Page
#    Given I am on Quote Page
#    Then I click Get A Quote

  @LQA-216 @LQA-233 @LQA-250 @QuoteSummary
  Scenario Outline: Quote Summary Cover amount for Death TPD and IP
    Given I am an public member
    And I am on Quote page
    Given I am on Quote form
    When I enter Date of Birth <Age> and Select gender <Gender>
    And I enter employment details
      | Income   | Occupation   | Less than 15 hours work per week   | Contract employee   |
      | <Income> | <Occupation> | <Less than 15 hours work per week> | <Contract employee> |
    And click Next
    And Switch to default Page
    Then This text is displayed Choose your cover
    Then This text is displayed Select the cover that suits your situation from death, TPD and income protection you'd like.
    Then This text is displayed These amounts are paid for through your super fund.
    Then This text is displayed If you're not sure, use our
    # Below step is commented. Unitised and Fixed Cover listbox is replaced by autocomplete textfield
#    Then Verify Cover Amount
#      | Age   | Income   |
#      | <Age> | <Income> |
    Examples:
      | Age | Gender | Income             | Occupation | Less than 15 hours work per week | Contract employee |
      | 25  | Female | 55000:Per year     | Actor      | No                               | No                |

    # Below step is commented. Unitised and Fixed Cover listbox is replaced by autocomplete textfield
#  @LQA-208 @LQA-238 @DeathAndTPDFixedCover
#  Scenario Outline: Death Cover amount for fixed values
#    Given I am an public member
#    And I am on Quote page
#    Given I am on Quote form
#    When I enter Date of Birth <Age> and Select gender <Gender>
#    And I enter employment details
#      | Income   | Occupation   | Less than 15 hours work per week   | Contract employee   |
#      | <Income> | <Occupation> | <Less than 15 hours work per week> | <Contract employee> |
#    And click Next
#    And Switch to default Page
#    Then Verify Cover Amount
#      | Age   | Income   |
#      | <Age> | <Income> |
#    And I Verify Death Cover amount values for fixed cover type
#    And I Verify TPD Cover amount values for fixed cover type
#    Then Verify Cover Amount
#      | Age   | Income   |
#      | <Age> | <Income> |
#    Examples:
#      | Age | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee |
#      | 25  | Female | 55000:Per year | Actor      | No                               | No                |

  @LQA-254 @manual
  Scenario: Quote Summary Cover amount for Death TPD and IP
    Given I am an public member
    And I am on Quote page
    Given I am on Quote form
    When I enter Date of Birth <Age> and Select gender <Gender>
    And I enter employment details
      | Income   | Occupation   | Less than 15 hours work per week   | Contract employee   |
      | <Income> | <Occupation> | <Less than 15 hours work per week> | <Contract employee> |
    And click Next
    And Verify Death Unitised and Fixed Cover amount behavior as per story LQA-254
    And Verify TPD Unitised and Fixed Cover amount behavior as per story LQA-254
    And Verify IP Unitised Cover amount behavior as per story

  @LQA262 @QuoteSummary @public @saldi @LQA402
  Scenario Outline: Validate public user should not be able to apply for application from quote summary page
    Given I am an public member
    And the following user data:
      | Age   | Gender   | Income   | Occupation   | Less than 15 hours work per week   | Contract employee   |
      | <Age> | <Gender> | <Income> | <Occupation> | <Less than 15 hours work per week> | <Contract employee> |
    And I am on Quote Summary page
    Then I cannot apply for the application from quote summary
    Examples:
      | Age | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee |
      | 35  | Female | 55000:Per year | Actor      | No                               | No                |

  @LQA262 @QuoteSummary @authenticated @saldi
  Scenario Outline: Validate the default content on Eligibility modal when user tries to apply from quote summary
    Given I am an authenticated member
    And the following user data:
      | Age   | Gender   | Income   | Occupation   | Less than 15 hours work per week   | Contract employee   |
      | <Age> | <Gender> | <Income> | <Occupation> | <Less than 15 hours work per week> | <Contract employee> |
    And I am on Quote Summary page
    When I apply for the application from quote summary
    Then Before we begin your application modal should be displayed
    And this text is displayed on Before you apply modal Please answer the following question
    And this text is displayed on Before you apply modal Have you previously received a lump-sum TPD or terminal illness benefit?
    Examples:
      | Age | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee |
      | 35  | Female | 55000:Per year | Actor      | No                               | No                |

  @LQA262 @QuoteSummary @authenticated @saldi
  Scenario Outline: Validate user should not be able to apply if user previously received a lump-sum TPD or terminal illness benefit
    Given I am an authenticated member
    And the following user data:
      | Age   | Gender   | Income   | Occupation   | Less than 15 hours work per week   | Contract employee   |
      | <Age> | <Gender> | <Income> | <Occupation> | <Less than 15 hours work per week> | <Contract employee> |
    And I am on Quote Summary page
    And I apply for the application from quote summary
    And Before we begin your application modal should be displayed
    When I have received a lump-sum TPD or terminal illness benefit
    And this text is displayed on Ineligible to apply modal Unfortunately you are not able to proceed with an online application at this point in time.
    And this text is displayed on Ineligible to apply modal Please contact HESTA on 1800 813 327 between 8:00am and 8:00pm Monday to Friday (AEST/AEST) to discuss your insurance options.
    Examples:
      | Age | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee |
      | 35  | Female | 55000:Per year | Actor      | No                               | No                |

  @LQA262 @QuoteSummary @authenticated @amit @saldi
  Scenario Outline: Validate user should be able to apply if user has not previously received a lump-sum TPD or terminal illness benefit
    Given I am an authenticated member
    And the following user data:
      | Age   | Gender   | Income   | Occupation   | Less than 15 hours work per week   | Contract employee   |
      | <Age> | <Gender> | <Income> | <Occupation> | <Less than 15 hours work per week> | <Contract employee> |
    And I am on Quote Summary page
    And I apply for the application from quote summary
    And Before we begin your application modal should be displayed
    When I have not received a lump-sum TPD or terminal illness benefit
    Then I should be able to go to application welcome page
    Examples:
      | Age | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee |
      | 35  | Female | 55000:Per year | Actor      | No                               | No                |


  @LQA-23 @QS_DeathRules
  Scenario Outline: Death Cover Rules for public user
    Given I am an public member
    And I am on Quote page
    Given I am on Quote form
    When I enter Date of Birth <Age> and Select gender <Gender>
    And I enter employment details
      | Income   | Occupation   | Less than 15 hours work per week   | Contract employee   |
      | <Income> | <Occupation> | <Less than 15 hours work per week> | <Contract employee> |
    And click Next
    And Switch to default Page
    Then Verify Death Block is disabled
    Then Verify TPD Block is disabled
    Then Verify IP Block is disabled
    Examples:
      | Age | Gender | Income             | Occupation | Less than 15 hours work per week | Contract employee |
      | 75  | Female | 55000:Per year     | Actor      | No                               | No                |
      | 74  | Male   | 10000:Per month    | Actor      | No                               | No                |

  @LQA-23 @QS_DeathRules @authenticated
  Scenario Outline: Death Cover Rules for authenticated user
    Given I am an authenticated member
    And I am on Quote page
    Given I am on Quote form
    When I enter Date of Birth <Age> and Select gender <Gender>
    And I enter employment details
      | Income   | Occupation   | Less than 15 hours work per week   | Contract employee   |
      | <Income> | <Occupation> | <Less than 15 hours work per week> | <Contract employee> |
    And click Next
    And Switch to default Page
    Then Verify Death Block is disabled
    Then Verify TPD Block is disabled
    Then Verify IP Block is disabled
    Examples:
      | Age | Gender | Income             | Occupation | Less than 15 hours work per week | Contract employee |
      | 75  | Female | 55000:Per year     | Actor      | No                               | No                |
      | 74  | Male   | 10000:Per month    | Actor      | No                               | No                |


  @LQA-24 @QS_TPDRules
  Scenario Outline: TPD Cover Rules for public user
    Given I am an public member
    And I am on Quote page
    Given I am on Quote form
    When I enter Date of Birth <Age> and Select gender <Gender>
    And I enter employment details
      | Income   | Occupation   | Less than 15 hours work per week   | Contract employee   |
      | <Income> | <Occupation> | <Less than 15 hours work per week> | <Contract employee> |
    And click Next
    And Switch to default Page
    And Read Death TPD IP Units
      | Age   | Income   |
      | <Age> | <Income> |
    And Select random unit for Death Cover amount
    Then Verify TPD Block is disabled
    Examples:
      | Age | Gender | Income             | Occupation | Less than 15 hours work per week | Contract employee |
      | 70  | Female | 55000:Per year     | Actor      | No                               | No                |
      | 71  | Male   | 10000:Per month    | Actor      | No                               | No                |

  @LQA-24 @QS_TPDRules @authenticated
  Scenario Outline: TPD Cover Rules for authenticated user
    Given I am an authenticated member
    And I am on Quote page
    Given I am on Quote form
    When I enter Date of Birth <Age> and Select gender <Gender>
    And I enter employment details
      | Income   | Occupation   | Less than 15 hours work per week   | Contract employee   |
      | <Income> | <Occupation> | <Less than 15 hours work per week> | <Contract employee> |
    And click Next
    And Switch to default Page
    And Read Death TPD IP Units
      | Age   | Income   |
      | <Age> | <Income> |
    And Select random unit for Death Cover amount
    Then Verify TPD Block is disabled
    Examples:
      | Age | Gender | Income             | Occupation | Less than 15 hours work per week | Contract employee |
      | 70  | Female | 55000:Per year     | Actor      | No                               | No                |
      | 71  | Male   | 10000:Per month    | Actor      | No                               | No                |

  @LQA-26 @QS_IPRules
  Scenario Outline: Income Protection Cover Rules for public user
    Given I am an public member
    And I am on Quote page
    Given I am on Quote form
    When I enter Date of Birth <Age> and Select gender <Gender>
    And I enter employment details
      | Income   | Occupation   | Less than 15 hours work per week   | Contract employee   |
      | <Income> | <Occupation> | <Less than 15 hours work per week> | <Contract employee> |
    And click Next
    And Switch to default Page
    Then Verify IP Block is disabled
    Examples:
      | Age | Gender | Income             | Occupation | Less than 15 hours work per week | Contract employee |
      | 68  | Female | 55000:Per year     | Actor      | No                               | No                |
      | 67  | Male   | 10000:Per month    | Actor      | No                               | No                |

  @LQA-26 @QS_IPRules  @authenticated
  Scenario Outline: Income Protection Cover Rules for authenticated user
    Given I am an authenticated member
    And I am on Quote page
    Given I am on Quote form
    When I enter Date of Birth <Age> and Select gender <Gender>
    And I enter employment details
      | Income   | Occupation   | Less than 15 hours work per week   | Contract employee   |
      | <Income> | <Occupation> | <Less than 15 hours work per week> | <Contract employee> |
    And click Next
    And Switch to default Page
    Then Verify IP Block is disabled
    Examples:
      | Age | Gender | Income             | Occupation | Less than 15 hours work per week | Contract employee |
      | 68  | Female | 55000:Per year     | Actor      | No                               | No                |
      | 67  | Male   | 10000:Per month    | Actor      | No                               | No                |


  @LQA-37 @QuoteSummary @authenticated
  Scenario Outline: validate user can confirm that previously not received lum-sum TPD or terminal benefit and apply for cover
    Given I am an authenticated member
    And the following user data:
      | Age   | Gender   | Income   | Occupation   | Less than 15 hours work per week   | Contract employee   |
      | <Age> | <Gender> | <Income> | <Occupation> | <Less than 15 hours work per week> | <Contract employee> |
    And I am on Quote Summary page
    And I apply for the application from quote summary
    And Before we begin your application modal should be displayed
    When I have not received a lump-sum TPD or terminal illness benefit
    Then I should be able to go to application welcome page
    And This text is displayed Your height and weight
    And This text is displayed Details of your medical history, including illnesses, injuries and medications
    And This text is displayed Details of your doctor (name, address, contact details)
    And This text is displayed Details of any hazardous pursuits you may engage in
    And This text is displayed Details about your occupation and salary
    And This text is displayed Your duty of disclosure
    And This text is displayed Before you enter into a contract of life insurance with an insurer, you have a duty under the Insurance Contracts Act 1984 to disclose to the insurer every matter
    And I should be able to begin application
    Examples:
      | Age | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee |
      | 35  | Female | 55000:Per year | Actor      | No                               | No                |


    @LQA-231 @QS_IPRules  @authenticated @hi
    Scenario Outline: Adjust Income Protection periods from quote
      Given I am an authenticated member
      And I am on Quote page
      Given I am on Quote form
      When I enter Date of Birth <Age> and Select gender <Gender>
      And I enter employment details
        | Income   | Occupation   | Less than 15 hours work per week   | Contract employee   |
        | <Income> | <Occupation> | <Less than 15 hours work per week> | <Contract employee> |
      And click Next
      And Switch to default Page
      Then Verify IP Block is enabled
      Then I click on Adjust benefit period button
      Then This text is displayed Adjust income protection periods
      Then This text is displayed Description of the two periods to consider when exploring Income protection cover
#      Then This text is displayed This cover provides a maximum of 75% of your income with a waiting period. A portion of this goes to your super
#      Then This text is displayed Waiting period
      And I should be able select different Waiting period values
      Examples:
        | Age | Gender | Income             | Occupation | Less than 15 hours work per week | Contract employee |
        | 25  | Female | 55000:Per year     | Actor      | No                               | No                |
        | 55  | Male   | 10000:Per month    | Actor      | No                               | No                |

    @LQA-231 @QS_IPRules  @public
    Scenario Outline: Adjust Income Protection periods from quote
    Given I am an public member
    And I am on Quote page
    Given I am on Quote form
    When I enter Date of Birth <Age> and Select gender <Gender>
    And I enter employment details
      | Income   | Occupation   | Less than 15 hours work per week   | Contract employee   |
      | <Income> | <Occupation> | <Less than 15 hours work per week> | <Contract employee> |
    And click Next
    And Switch to default Page
    Then Verify IP Block is enabled
    Then I click on Adjust benefit period button
    Then This text is displayed Adjust income protection periods
    Then This text is displayed Description of the two periods to consider when exploring Income protection cover
    #      Then This text is displayed This cover provides a maximum of 75% of your income with a waiting period. A portion of this goes to your super
    #      Then This text is displayed Waiting period
    And I should be able select different Waiting period values
    Examples:
      | Age | Gender | Income             | Occupation | Less than 15 hours work per week | Contract employee |
      | 25  | Female | 55000:Per year     | Actor      | No                               | No                |
      | 55  | Male   | 10000:Per month    | Actor      | No                               | No                |

  @LQA-28 @EditPersonalDetailsFromNeedsSummary @noor
  Scenario Outline: Edit personal details from Quote Summary
    Given I am an public member
    And the following user data:
      | Age   | Gender   | Income   | Occupation   | Less than 15 hours work per week   | Contract employee   |
      | <Age> | <Gender> | <Income> | <Occupation> | <Less than 15 hours work per week> | <Contract employee> |
    And I am on Quote Summary page
    When I click Previous button
    Then Verify previous member personal details populated

    Given the following user data:
      | New Age   | New Gender   | New Income   | New Occupation   | New Less than 15 hours work per week   | New Contract employee   |
      | <New Age> | <New Gender> | <New Income> | <New Occupation> | <New Less than 15 hours work per week> | <New Contract employee> |
    And I update member data from About You page
    When I click Previous button
    Then Verify updated member personal details populated
    Examples:
      | Age | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee | New Age   | New Gender   | New Income   | New Occupation   | New Less than 15 hours work per week   | New Contract employee   |
      | 35  | Female | 55000:Per year | Actor      | No                               | No                | 37 | Male   | 120000:Per year | Business Analyst [tertiary qualified] | Yes                               | Yes                |
