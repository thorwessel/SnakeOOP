Simple snake game written in an OOP manner.
The program uses Korge to render the graphics and to listen for keystrokes. Unfortunately, I did not manage to preserve a fully object-oriented programming style, while using Korge. Therefor, all the graphics related code and key listeners are present in the main.kt file and not in separate classes.

 The overall structure is rather simple, 3 classes are used:
### Snake
Snake is where most of the heavy lifting is. The Snake class both contains the states of the snake which is used to render the snake in the game. The class also contains methods to add input from the user, check whether the snake has hit part of its body, add check if the snake has eaten the food of the game.


### Movement
This class checks if the input from the user is valid and if input should be added as a direction.


### Food
The Food class contains the current location of the “food” within the game and has a method to generate a new random location.