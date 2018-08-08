Feature: Casino entrance control

Scenario: A very old woman wants to come inside a casino
Given the following person
| firstName | surname | age |
|     Marie     |  Curie     | 150  |
When I ask if I could go in a casino
Then the bouncer should say « you look radiant Ma’am, come in »
