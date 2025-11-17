Feature: SIM Card Activation

  Scenario: Successful SIM card activation
    Given I submit an activation request with ICCID "1255789453849037777" and customer email "customer1@example.com"
    When I query the SIM record with ID 1
    Then the response should have active true

  Scenario: Failed SIM card activation
    Given I submit an activation request with ICCID "8944500102198304826" and customer email "customer2@example.com"
    When I query the SIM record with ID 2
    Then the response should have active false