# Quarto
Quarto is originaly a boardgame where two players are trying to win by being the first to rank 4 pieces having at least one charcterisic in common. They play with 16
pieces that are all unique, on a grid of 4 by 4. The pieces have 4 characteritics : tall/small, round/squared, dark/light, full/empty. The tricky thing is that a player
can't choose the piece that he will place on the grid : it is his opponnent that will choose for him. So they are two types of action : choosing a piece for the 
adversary and placing the piece that the adversary choose for yourself. This is un uncommon way of thinking and very intersting for Artificial Intelligence.

# Language
All the classes are coded in java.

#Libraries
AWT
SWING
IO
UTIL

# Launching
You have to compile the classes and execute the Main.java.

# Features
## Versions
This programm proposes two versions of the game : one where two players can face each other, and one where a player can face an AI.
It begins with the opening of the menu window from which you can access to the rules (it opens a new window), to the version1 or the version2 (it also opens a new window).
## Start
To start the game you may press on the button "confirmer". In order to not loose the progression of the game, the tour, the player and the instructions are displayed on
the upper banner of the window.
## Playing
To choose a piece you just have to click on the piece you want in the right part of the window and a green square will cerle it to make sure it is the one you want.
Then you press "confirmer" and can't change after that. The piece is displayed in the upper banner for the other player to remember which one it was.
To place a piece you just have to click on the position you want and a green square will appear on the emplacement. You press "confirmer". The piece disappears from the
pieces choosing zone to help seeing what is left and is put on position you wanted.
## Ending
At the end of the game a new window opens, telling which player won or telling if it is an equality.
In the AI version you have to press on "confirmer" in the place of the bot.
Pressing on CLOSE in the playing window or the end game window will make a pop-up window to open, asking if you really want to close. In this case you will return in 
the menu window. Else, you will return in the previous window at the state you left it.
## Rules
You can access to the rules from the playing window.

# Ways of improvement
## Interface
In order to improve the experience of the user, a night/day mode could be added.
The rules window could be displayed just next to the playing window in order to keep on eye on the rules and the game at the same time.
A JTextfield could let the users choosing there pseudos instead of "Joueur1" and "Joueur2".
An adjustable background sound could be fun, with subtle sounds when pressing on a piece or a 'bravo' at the ending.
When playing on the AI version it would be better if we didn't have to press all the time on "confirmer".

## AI
Several levels could be created to let a chance to a beginner to win, or a good player not to be bored.
