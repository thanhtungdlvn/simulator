Feature: Get card details

  Background:
    * url karate.properties['application.URL']


  Scenario: 1 - Card details get 200 OK response
    * def query = {srcClientId: '1'}
    Given path '/api/users/1'
    When method GET
    * print response
    Then status 200