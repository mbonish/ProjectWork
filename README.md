# ProjectWork
Projects completed during my time at the software guild, and works in progress

Flooring project: A Console flooring application which allows users to create, retrieve, update and delete flooring orders. The system dynamically updates to add or remove support for states and flooring materials based on text files. Saves orders to separate text files based on the date the order is created, and is unit tested both in the service layer and DAO

Mastermind: Built a web API which uses a MySQL database to store data. The user can make calls into the API to create a new game, or add a guess to an existing game based on the game ID. If a game is complete, no further guesses can be added. Creating a game generates a 4 digit number, with each digit being unique. Making a guess will return the number of exact position match and correct guess but incorrect position. If all four positions are correct, the game ends

Vending machine: Built a front end for a vending machine which interacts with a Heroku web API to populate items. An AJAX call retrieves JSON data from the web API, and JQuery is used to populate the page when data is returned. Money can be added, and items can be purchased. Upon purchase, change will be returned, and error messages displayed based on the return from the Heroku API (out of stock, insufficient money, invalid item ID).
