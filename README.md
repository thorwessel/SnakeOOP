Simple snake game written in an OOP manner.
The program uses Korge to render the graphics and to listen for keystrokes. Unfortunately, I did not manage to preserve a fully object-oriented programming style, while using Korge. Therefor, all the graphics related code and key listeners are present in the main.kt file and not in separate classes.

 The overall structure is rather simple, 5 classes are used:
### Game
The Game class is the top level class which interacts with all other classes, it provides methods to progress the game and return the score.

### Graphics
To render the graphics with Korge, a View object is created for each time-step/frame in the game. The Graphics class takes the positions of the Snake(s) and Food and return a View to be displayed.

### Snake
Snake is where most of the heavy lifting is. The Snake class contains the states of the snake which is used to render the snake in the game.
The class also contains methods to add input from the user, check whether the snake has hit part of its body, add check if the snake has eaten the food of the game.


### Movement
This class checks if the input from the user is valid and if input should be added as a direction.


### Food
The Food class contains the current location of the “food” within the game and has a method to generate a new random location.