Feature: Employees test
    In order to value
    As a role
    I want feature

Scenario: Create a character 

* def user =
"""
{
  "id": 0,
  "name": "string"
}
"""


Given url 'http://localhost:8080/api/characters'
And request user
When method post
Then status 200
And match response $ == {id:'#uuid', name:'str'}
And def user = response

Scenario: Get all characters

Given url 'http://localhost:8080/api/characters'
When method get
Then status 200
And match $ = []

Scenario: Delete a character
* def val = '5'
Given url 'http://localhost:8080/api/characters/'
And request val
When method delete
Then status 204
And match $ == {id:5, name:'#(user.name)'}

Scenario: update the character

* def u23 =
"""
{
  "id": 7,
  "name": "kg"
}
"""
Given url 'http://localhost:8080/api/characters/7'
And request u23 
When method put
Then status 500


