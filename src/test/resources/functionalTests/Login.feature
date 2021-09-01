Feature: Automate Yahoo site 

Scenario Outline: Login Yahoo page 

	Given Open the browser and launch the application 
	When Enter the Username and Password from excel file test-data <row_index> 
	Then User is able to login successfully 
	Examples: 
		|row_index|
		|0|
		
		
Scenario: Access Calendar 

	Given User is on Yahoo Home Page 
	When User selects Finance / Market Data/ Calendar 
	Then capture the values for the 27th August
	