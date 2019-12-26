Ulmer’s Rubik’s Cube

- - - - - - - - - - 

This is a personal project of creating a virtual Rubik’s cube that tests and moves can be applied to, written in java(and some processing).

There is an underlying data structure (CubeObj.java), and an interactive visualization (Cube.pde) written in processing. I may add other filer later. 

This project was written in java 1.8.0 
the visualization is interactive and was written in Processing (https://processing.org)
The processing application is necessary for running the visualization.

Credit to Jonathan Feinberg and his library PeasyCam, which saved me the trouble of having to figure out how the processing camera works and how to do mouse controlled movement.

Processing allows you to export as a complete application (something equivalent to .jar I think), so if you don’t want to wade through my bad code (I don’t blame you), that is an option. I might do that myself at a later date, but I don’t think I am done yet.

- - - - - - - - - -

Explanations of how to use the methods on the data structure can be found in the comments of the CubeObj.java file.

The visualization can be controlled in real time with the keyboard. in short r is right, R is right prime (r’). More details on that can be found in the comments of the processing file. 

- - - - - - - - - -

Limitations and things to note:
	The data structure does not support rotating the whole cube as a unit, so all move are applied with the assumption that the blue side is facing you and the yellow side is facing up. 

	This means that the centers of each side never move.

	There is currently no animation for turning in the visualization, we will see if I get to that point.

	I highly recommend checking out some of the methods in the CubeObj class, as some of them are helpful (eg. scrambling, checking for a solved state, and applying more than one move in a row)

- - - - - - - - - -

The future of this project is uncertain, so feel free to ask me about it/make suggestions.

Please feel free to use some or all of my code, as I would hate to force someone else to reprogram the whole thing. (Do credit me though)

The next steps for me are probably making a turning animation, and exploring solving algorithms (both human ones and maybe trying to find the fastest/most direct path to the solved state) 
