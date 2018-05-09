Feature: Disclaimer
  Verify Needs calculator disclaimer

  Background:
    Given I am on Home Page

  @LQA-169 @Disclaimer @pending
  Scenario: Needs calculator disclaimer
    Given I am an public member
    Given I am on Insurance Needs Calculator Welcome Page
    When I click Calculate My Cover
    Then This text is displayed Calculator assumptions disclaimer
    Then This text is displayed This tool is intended to provide broad guidance on the level of insurance cover required to meet debt obligations and replace a portion of income in the event of death, TPD (total and permanent disablement) or temporary disablement. The calculated cover is not a recommendation and is not intended to be an exact figure. It does not take all of your needs into account and does not constitute financial advice.
    Then This text is displayed Death and TPD cover has been rounded to increments of $10,000.
    Then This text is displayed Separate advice should be sought regarding the need for any other insurance including Trauma and Private Health Insurance.
    Then This text is displayed All figures are gross of tax. No allowance is made for your particular tax circumstances or for the taxation of insurance benefits, either inside or outside superannuation. Generally, Death and TPD insurance benefits will be taxed at a lower rate than the income they are replacing. Please seek separate tax advice in relation to insurance benefits.
    Then This text is displayed No allowance has been made for any social security benefits to which you may be or become entitled.
    Then This text is displayed No assessment is carried out as to the availability of cover. Cover may not be available to some customers either for health reasons or due to their occupation.
    Then This text is displayed You should consider obtaining advice from a licensed financial adviser before making any decision.
    Then This text is displayed Your Needs
    Then This text is displayed You should reassess your insurance needs regularly as your circumstances may change. Also, while the assumptions underlying this calculator are considered reasonable at the present time, these assumptions may be reviewed in future.
    Then I close disclaimer