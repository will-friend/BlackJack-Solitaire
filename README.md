# Blackjack Solitaire (with Java)
The code in this repository is the structure to play blackjack solitaire from the console, implemented through java. This project serves as practice implementing more complex systems through class-responsibility-collaborator modeling frmo the my introduction to software development class at UPenn. Any critiques on the program on ways it could be done more efficiently from an OOP perspective is always appreciated as I continue my progression in my masters!

# Layout of the Game
Blackjack solitaire is a play on traditional blackjack, combined with the idea of solitaire. A player has a grid laid out in the following manner:

```
 1    2    3    4    5   
 6    7    8    9   10   
     11   12   13        
     14   15   16        
```

where each number represents a "slot" a player can choose to place a card. Additionally, a separate grid is defined as follows:

```
17   18   
19   20   
```
where each number here is also a slot a player can place a card. This smaller 2x2 grid represents a discard pile, should I player draw a card and choose to not play it.

# Rules
Each turn the player "draws" the top card from the deck of cards. At this point, the player can choose to either place the card on the grid or discaard it. There are four discards allowed in a game. When the board is filled, we sum the value of the cards in each row and column as if they were a hand of blackjack, and assign a score based on how good the hand was. The goal of the game is to place cards such that when the board is full, we have optimally placed cards to get the best blackjack ahnds possible in each row and column, or achieve the best overall score. We score each row/column hand as follows:

| Hand Type     | Description of Hand                      | Point Value |
|---------------|------------------------------------------|:-----------:|
| BlackJack     | A hand of 2 cards that sum to 21         | 10          |
| 21            | 3,4, or 5 cards that sum to 21           | 7           |
| 20            | Hand of any size that sums to 20         | 5           |
| 19            | Hand of any size that sums to 19         | 4           |
| 18            | Hand of any size that sums to 18         | 3           |
| 17            | Hand of any size that sums to 17         | 2           |
| $\leq$ 16     | Hand of any size that sums to 16         | 1           |
| $>$ 21 (BUST) | Hand of any size that sums to 22 or more | 0           |

The game only ends when the board is completely filled with cards. If a player has discarded four cards already and still has spots to fill on the board, the player must play the drawn cards until the board is filled, so discard strategically! After the board is filled, the hand scores are calculated and you get your final score.