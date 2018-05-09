Feature: Long or short form
  In order to complete the application and provided all required detail
  As authenticated member
  I want to be presented with the required questions and sections

  Background:
    Given I am on Home Page

  #  If the member is requesting more than $1.25 million in Death per product (not combined)
  @LQA38 @authenticated @saldi
  Scenario Outline: Validate long and short form for death
    Given I am an authenticated member
    And the following user data:
      | Age   | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee |
      | <Age> | Female | 55000:Per year | Actor      | No                               | No                |
    And with the following death cover data:
| death cover type | death current cover | death calculated cover | quoted death cover amount   |
| units            | 170000              | 155000                 | <quoted death cover amount> |
    And I am on Application personal details about you page
    Then the application type should be <app type>
    Examples:
| Age | quoted death cover amount | app type |
| 54  | 4122300                   | long     |
| 54  | 27300                     | short    |
| 56  | 23250                     | long     |

    #  If the member is requesting more than $1.25 million in Death or TPD cover per product (not combined)
  @LQA38 @authenticated @saldi
  Scenario Outline: Validate long and short form for tpd
    Given I am an authenticated member
    And the following user data:
      | Age   | Gender | Income         | Occupation | Less than 15 hours work per week | Contract employee |
      | <Age> | Female | 55000:Per year | Actor      | No                               | No                |
    And with the following death cover data:
| death cover type | death current cover | death calculated cover | quoted death cover amount   |
| units            | 170000              | 155000                 | <quoted death cover amount> |
    And with the following tpd cover data:
| tpd cover type | tpd current cover | tpd calculated cover | quoted tpd cover amount   |
| units          | 170000            | 155000               | <quoted tpd cover amount> |
    And I am on Application personal details about you page
    Then the application type should be <app type>
    Examples:
| Age | quoted death cover amount | quoted tpd cover amount | app type |
| 54  | 4122300                   | 327600                  | long     |
| 54  | 27300                     | 2839200                 | long     |
| 56  | 69750                     | 1209000                 | long     |
| 33  | 67450                     | 1190000                 | short    |

    #  If the member is requesting more than $10,000 per month for IP
  @LQA38 @authenticated @saldi
  Scenario Outline: Validate long and short form for IP
    Given I am an authenticated member
    And the following user data:
| Age   | Gender | Income   | Occupation | Less than 15 hours work per week | Contract employee |
| <Age> | Female | <income> | Actor      | No                               | No                |
    And with the following ip cover data:
| ip current cover | ip calculated cover | quoted ip cover amount   |
| 170000           | 155000              | <quoted ip cover amount> |
    And I am on Application personal details about you page
    Then the application type should be <app type>
    Examples:
| Age | income          | quoted ip cover amount | app type |
| 54  | 55000:Per year  | 3800                   | short    |
| 56  | 55000:Per year  | 3800                   | long     |
| 61  | 200000:Per year | 10450                  | long     |
