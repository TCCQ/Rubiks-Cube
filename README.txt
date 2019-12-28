Ulmer’s Rubik’s Cube

- - - - - - - - - - 

This is a personal project of creating a virtual Rubik’s cube that tests and moves can be applied to, written in java(and some processing).

There is an underlying data structure (CubeObj.java), and an interactive visualization (Inteactive.pde) written in processing. I may add other files later. 

This project was written in java 1.8.0 
the visualization was written in Processing (https://processing.org)
The processing application is necessary for running the visualization.

Credit to Jonathan Feinberg and his library PeasyCam, which saved me the trouble of having to figure out how the processing camera works and how to do mouse controlled movement.

Processing allows you to export as a complete application (something equivalent to .jar I think), so if you don’t want to wade through my bad code (I don’t blame you), that is an option. I might do that myself at a later date, but I don’t think I am done yet.

- - - - - - - - - -

Explanations of how to use the methods on the data structure can be found in the comments of the CubeObj.java file.

The Interactive.pde file can be controlled in real time with the keyboard. In short r is right, R is right prime (r’). More details on that can be found in the comments of the processing file. 

Interactive.pde creates a solved cube that the user can mess around with. This is the simplest starting point.

CubeObj.java has what I imagine most people are interested in, which is the data structure and some methods to control it (and scramble and whatnot)

The visualize folder is for creating renders of a still cube. It is *not* interactive. Because processing is annoying, it has to be in a different folder than Interactive.pde, but they both need to be in the same folder as CubeObj.java, hence the duplicate. 

Using Visualize.pde is a pain, and I am working to streamline the process. The basics are, when you have done whatever it is you want to do to your cube,  call cube.exportState(filename) This writes out the cube state in a very basic way, but it’s the best way I could come up with to make processing and java talk the way I wanted. Next, call Visualize.pde *from the command line* and pass it the text file as its sole argument. *All paths should be absolute, as it prevents java from try to search in the “user.dir” directory, which since it is being called by processing, is in the middle of nowhere.

This is a huge pain, I know

so:

cube.whatever()
> make file “a.txt”
cube.exportState(“a.txt”)
close java
run processing-java --sketch=/Users/name/whereverItIs/Visualize --run “a.txt”

Note that the path should point to *the folder* (It should be an absolute path)
processing-java can be installed through the installation folder, or I think through the tools menu on mac

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
