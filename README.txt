
The goal of this program is to act as an economic simulation.

The player can generate profit, manage their budget, purchase ingredients,
gauge profitability off of weather and buyer probability, adjust price and 
ingredients, all to ultimately sell cups of boba.

The player starts off with zero supplies in their inventory to make boba, and 
$100 in their budget

They can choose different actions via the menu to manipulate the game to their 
economic advantage. The game will last for 2 weeks (14 days). Ideally, the 
player would want to maximize their profits.


method printEnd: print out amount of profit made in total over the entire 
period of two weeks. Profit would be the total amount you made during the game
minus the intial budget you had.

method weatherReport: This generates a probability of weather for the player to
gauge if they should increase prices, adjust ingredients in boba.
(ex: if the weather is hotter, they can expect more people to purchase boba, 
and increase prices or increase amount of ice in drinks)

method checkBudget: allows player to see how much money they have

method timeSink: utilizes a recursion to act as a time sink and create delay between each sellChance method, simulating the delays between Boba transactions

method startCycle: performs all the actions needed for player to complete 
sales - if the player has effectively stocked up on ingredients, the call to
method sellChance will take into account the price they set the boba to (money),how many ingredients they put into the boba (recipe), and the weather (day)
At the end, will perform all the necessary actions of subtracting ingredients
from inventory and adding profit to budget

method sellChance: takes into account the probability of selling boba due to 
weather - hotter weather has a greater weather multiplier - higher probability
that boba will get sold. Similarly, the probability of selling boba is increasedif there are more pearls and tea in a drink.

method checkInventory: allows player to see how much of each ingredient they 
have in inventory

method goToShop: player can purchase more of each ingredient if needed. They can gauge the profitability of their choices given the price of each ingredient and their budget. Ingredients are added to their inventory and costs are subtracted from their budget.

method adjust: player can adjust the price and amount of ingredients of each 
boba keeping in mind the weather probability, recipe probability, and their 
budget.   