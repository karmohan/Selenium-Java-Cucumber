Feature: To verify the items present on my videos page

Scenario: I check the videos present on my videos page 
Given Creating a chrome driver instance and Navigating to discovery home page
When To look into the videos recommended for me
And To create a favourite list
And To open my videos page to ensure my videos section is not empty
Then To validate the title and description displayed in the page matches the favorites list
