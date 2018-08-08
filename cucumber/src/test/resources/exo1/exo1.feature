Feature: Floorball player’s recruitement 

	In order to recruit players
 As a internet user
 I want to access the floorball federation site
 
Scenario: I want to know more about the nearest floorball club 
	Given I have opened a browser 
	And I search for "France Floorball" 
	When I click on the French floorball federation website 
	Then the menu "où pratiquer" is clickable 
	
