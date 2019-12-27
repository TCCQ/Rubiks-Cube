import java.io.FileWriter;

class CubeObj{
	/*
	this is the basic data structure
	its 3x3x3 matrix
	each cell is a 3 character string
	where each character is a color (white/yellow/red/orange/blue/green)
	w/y/r/o/b/g
	a 0 is no color
	each cell represents 1 of the 26 exterior blocks
	each of the characters represent one of the colors on the block
	they are in xyz order
	where the color is placed in the spot of the axis it is perpendicular to
	
	the axises are as follows
	x is left right (right is +)
	y is down up (up is +)
	z is far close (close is +)
	
	the centers of the cube never move in this simulation, so no turning of the cube
	white is facing down
	yellow is facing up
	red is facing right
	orange is facing left
	blue is facing towards
	green is facing away
	*/
	private String[][][] cube = new String[3][3][3];
	
	
	/**
	* This is the constructor for the cube object.
	* @param solved Boolean, if true, cube is set to solved, if false, sets all colors of 0 (empty).
	*/
	public CubeObj(boolean solved){
		for (int x = 0; x < 3; x++){
			for (int y = 0; y < 3; y++){
				for (int z = 0; z < 3; z++){
					this.setBlock(x, y, z, "000");
				}
			}
		}
		
		if (solved){
			this.setSolved();
		}
	}
	
	/**
	* Prints the cube state in a human readable manner.
	*/
	public void printIt(){
		for(String[][] outer: cube){
			for(String[] inner: outer){
				System.out.print(inner[0]);
				System.out.print(' ');
				System.out.print(inner[1]);
				System.out.print(' ');
				System.out.print(inner[2]);
				System.out.println();
			}
			System.out.println();
		}
	}
	
	/**
	* Writes the complete cube state out as string to file.
	* note: file must already exist for this to work
	* should be in the same directory
	* TOTEST: define filepath in filename?
	* for use with still visualization code (Visualize.pde)
	*/
	public void exportState(String filename){
		try {
			FileWriter out = new FileWriter(filename);
			for (int x = 0; x < 3; x++){
				for (int y = 0; y < 3; y++){
					for (int z = 0; z < 3; z++){
						out.write(this.getBlock(x,y,z));
					}
				}
			}
			out.close();
		} catch (Exception e){
			System.out.println("Something went wrong (CubeObj line 86)");
		}
	}
	
	/**
	* Getter method for the cube as a whole.
	* @return 3 color string (eg. ryb) of the targeted block.
	*/
	public String getBlock(int x, int y, int z){
		return cube[x][y][z];
	}
	
	/**
	* Sets the targeted block's color string to the passed argument. 
	* Should not be used carelessly outside of cube object, as the cube could be placed into an unsolvable state.
	* @param colors The 3 color String the block is set to.
	*/
	public void setBlock(int x, int y, int z, String colors){
		cube[x][y][z] = colors;
	}
	
	
	/**
	* Sets the cube object to a solved state.
	* Used in the constructor if solved = true
	*/
	public void setSolved(){
		for (int y = 0; y < 3; y++){
			for (int z = 0; z < 3; z++){
				String temp = this.getBlock(0, y, z);
				temp = 'o' + temp.substring(1);
				this.setBlock(0,y,z, temp);
			}
		}
		for (int y = 0; y < 3; y++){
			for (int z = 0; z < 3; z++){
				String temp = this.getBlock(2, y, z);
				temp = 'r' + temp.substring(1);
				this.setBlock(2,y,z, temp);
			}
		}
	
		for (int x = 0; x < 3; x++){
			for (int z = 0; z < 3; z++){
				String temp = this.getBlock(x, 0, z);
				temp = temp.substring(0,1) + 'w' + temp.substring(2);
				this.setBlock(x, 0, z, temp);
			}
		}
		for (int x = 0; x < 3; x++){
			for (int z = 0; z < 3; z++){
				String temp = this.getBlock(x, 2, z);
				temp = temp.substring(0,1) + 'y' + temp.substring(2);
				this.setBlock(x, 2, z, temp);
			}
		}
		
		for (int x = 0; x < 3; x++){
			for (int y = 0; y < 3; y++){
				String temp = this.getBlock(x, y, 0);
				temp = temp.substring(0,2) + 'g';
				this.setBlock(x, y, 0, temp);
			}
		}
		for (int x = 0; x < 3; x++){
			for (int y = 0; y < 3; y++){
				String temp = this.getBlock(x, y, 2);
				temp = temp.substring(0,2) + 'b';
				this.setBlock(x, y, 2, temp);
			}
		}	
	
	}
	
	/**
	* Check for if the cube is in a solved state.
	* @return true if cube is solved.
	*/
	public boolean checkSolved(){
		CubeObj test = new CubeObj(true);
		for (int x = 0; x < 3; x++){
			for (int y = 0; y < 3; y++){
				for (int z = 0; z < 3; z++){
					if (!this.getBlock(x,y,z).equals(test.getBlock(x,y,z))){
						return false;
					}
				}
			}
		}
		return true;
	}
	
	/*
	What follows are the 12 basic moves of the cube
	they are the most basic move that can be applied
	represent 1 90 degree turn of any 1 face 
	I wrote them out by hand and don't want to do it again, so please don't touch
	they work as far as I know
	this is the bread and butter 
	*/
	public void right(){
	
		String[] init = new String[4];
			
		init[0] = this.getBlock(2,2,2);
		init[1] = this.getBlock(2,2,0);
		init[2] = this.getBlock(2,0,0);
		init[3] = this.getBlock(2,0,2);
		
		this.setBlock(2,2,0, charSwap(init[0], "x"));
		this.setBlock(2,0,0, charSwap(init[1], "x"));
		this.setBlock(2,0,2, charSwap(init[2], "x"));
		this.setBlock(2,2,2, charSwap(init[3], "x"));
		
		init[0] = this.getBlock(2,2,1);
		init[1] = this.getBlock(2,1,0);
		init[2] = this.getBlock(2,0,1);
		init[3] = this.getBlock(2,1,2);
		
		this.setBlock(2,1,0, charSwap(init[0], "x"));
		this.setBlock(2,0,1, charSwap(init[1], "x"));
		this.setBlock(2,1,2, charSwap(init[2], "x"));
		this.setBlock(2,2,1, charSwap(init[3], "x"));
	}
	public void rightP(){
	
		String[] init = new String[4];
			
		init[0] = this.getBlock(2,2,2);
		init[1] = this.getBlock(2,2,0);
		init[2] = this.getBlock(2,0,0);
		init[3] = this.getBlock(2,0,2);
		
		this.setBlock(2,2,0, charSwap(init[2], "x"));
		this.setBlock(2,0,0, charSwap(init[3], "x"));
		this.setBlock(2,0,2, charSwap(init[0], "x"));
		this.setBlock(2,2,2, charSwap(init[1], "x"));
		
		init[0] = this.getBlock(2,2,1);
		init[1] = this.getBlock(2,1,0);
		init[2] = this.getBlock(2,0,1);
		init[3] = this.getBlock(2,1,2);
		
		this.setBlock(2,1,0, charSwap(init[2], "x"));
		this.setBlock(2,0,1, charSwap(init[3], "x"));
		this.setBlock(2,1,2, charSwap(init[0], "x"));
		this.setBlock(2,2,1, charSwap(init[1], "x"));
	}
	public void left(){
	
		String[] init = new String[4];
			
		init[0] = this.getBlock(0,2,0);
		init[1] = this.getBlock(0,2,2);
		init[2] = this.getBlock(0,0,2);
		init[3] = this.getBlock(0,0,0);
		
		this.setBlock(0,2,2, charSwap(init[0], "x"));
		this.setBlock(0,0,2, charSwap(init[1], "x"));
		this.setBlock(0,0,0, charSwap(init[2], "x"));
		this.setBlock(0,2,0, charSwap(init[3], "x"));
		
		init[0] = this.getBlock(0,2,1);
		init[1] = this.getBlock(0,1,2);
		init[2] = this.getBlock(0,0,1);
		init[3] = this.getBlock(0,1,0);
		
		this.setBlock(0,1,2, charSwap(init[0], "x"));
		this.setBlock(0,0,1, charSwap(init[1], "x"));
		this.setBlock(0,1,0, charSwap(init[2], "x"));
		this.setBlock(0,2,1, charSwap(init[3], "x"));
	}
	public void leftP(){
	
		String[] init = new String[4];
			
		init[0] = this.getBlock(0,2,0);
		init[1] = this.getBlock(0,2,2);
		init[2] = this.getBlock(0,0,2);
		init[3] = this.getBlock(0,0,0);
		
		this.setBlock(0,2,2, charSwap(init[2], "x"));
		this.setBlock(0,0,2, charSwap(init[3], "x"));
		this.setBlock(0,0,0, charSwap(init[0], "x"));
		this.setBlock(0,2,0, charSwap(init[1], "x"));
		
		init[0] = this.getBlock(0,2,1);
		init[1] = this.getBlock(0,1,2);
		init[2] = this.getBlock(0,0,1);
		init[3] = this.getBlock(0,1,0);
		
		this.setBlock(0,1,2, charSwap(init[2], "x"));
		this.setBlock(0,0,1, charSwap(init[3], "x"));
		this.setBlock(0,1,0, charSwap(init[0], "x"));
		this.setBlock(0,2,1, charSwap(init[1], "x"));
	}
	public void up(){
	
		String[] init = new String[4];
			
		init[0] = this.getBlock(0,2,0);
		init[1] = this.getBlock(2,2,0);
		init[2] = this.getBlock(2,2,2);
		init[3] = this.getBlock(0,2,2);
		
		this.setBlock(2,2,0, charSwap(init[0], "y"));
		this.setBlock(2,2,2, charSwap(init[1], "y"));
		this.setBlock(0,2,2, charSwap(init[2], "y"));
		this.setBlock(0,2,0, charSwap(init[3], "y"));
		
		init[0] = this.getBlock(1,2,0);
		init[1] = this.getBlock(2,2,1);
		init[2] = this.getBlock(1,2,2);
		init[3] = this.getBlock(0,2,1);
		
		this.setBlock(2,2,1, charSwap(init[0], "y"));
		this.setBlock(1,2,2, charSwap(init[1], "y"));
		this.setBlock(0,2,1, charSwap(init[2], "y"));
		this.setBlock(1,2,0, charSwap(init[3], "y"));
	
	}
	public void upP(){
		
		String[] init = new String[4];
			
		init[0] = this.getBlock(0,2,0);
		init[1] = this.getBlock(2,2,0);
		init[2] = this.getBlock(2,2,2);
		init[3] = this.getBlock(0,2,2);
		
		this.setBlock(2,2,0, charSwap(init[2], "y"));
		this.setBlock(2,2,2, charSwap(init[3], "y"));
		this.setBlock(0,2,2, charSwap(init[0], "y"));
		this.setBlock(0,2,0, charSwap(init[1], "y"));
		
		init[0] = this.getBlock(1,2,0);
		init[1] = this.getBlock(2,2,1);
		init[2] = this.getBlock(1,2,2);
		init[3] = this.getBlock(0,2,1);
		
		this.setBlock(2,2,1, charSwap(init[2], "y"));
		this.setBlock(1,2,2, charSwap(init[3], "y"));
		this.setBlock(0,2,1, charSwap(init[0], "y"));
		this.setBlock(1,2,0, charSwap(init[1], "y"));
	}
	public void down(){
	
		String[] init = new String[4];
			
		init[0] = this.getBlock(0,0,2);
		init[1] = this.getBlock(2,0,2);
		init[2] = this.getBlock(2,0,0);
		init[3] = this.getBlock(0,0,0);
		
		this.setBlock(2,0,2, charSwap(init[0], "y"));
		this.setBlock(2,0,0, charSwap(init[1], "y"));
		this.setBlock(0,0,0, charSwap(init[2], "y"));
		this.setBlock(0,0,2, charSwap(init[3], "y"));
		
		init[0] = this.getBlock(1,0,2);
		init[1] = this.getBlock(2,0,1);
		init[2] = this.getBlock(1,0,0);
		init[3] = this.getBlock(0,0,1);
		
		this.setBlock(2,0,1, charSwap(init[0], "y"));
		this.setBlock(1,0,0, charSwap(init[1], "y"));
		this.setBlock(0,0,1, charSwap(init[2], "y"));
		this.setBlock(1,0,2, charSwap(init[3], "y"));
	
	}
	public void downP(){
		
		String[] init = new String[4];
			
		init[0] = this.getBlock(0,0,2);
		init[1] = this.getBlock(2,0,2);
		init[2] = this.getBlock(2,0,0);
		init[3] = this.getBlock(0,0,0);
		
		this.setBlock(2,0,2, charSwap(init[2], "y"));
		this.setBlock(2,0,0, charSwap(init[3], "y"));
		this.setBlock(0,0,0, charSwap(init[0], "y"));
		this.setBlock(0,0,2, charSwap(init[1], "y"));
		
		init[0] = this.getBlock(1,0,2);
		init[1] = this.getBlock(2,0,1);
		init[2] = this.getBlock(1,0,0);
		init[3] = this.getBlock(0,0,1);
		
		this.setBlock(2,0,1, charSwap(init[2], "y"));
		this.setBlock(1,0,0, charSwap(init[3], "y"));
		this.setBlock(0,0,1, charSwap(init[0], "y"));
		this.setBlock(1,0,2, charSwap(init[1], "y"));
	
	}
	public void front(){
		
		String[] init = new String[4];
			
		init[0] = this.getBlock(0,2,2);
		init[1] = this.getBlock(2,2,2);
		init[2] = this.getBlock(2,0,2);
		init[3] = this.getBlock(0,0,2);
		
		this.setBlock(2,2,2, charSwap(init[0], "z"));
		this.setBlock(2,0,2, charSwap(init[1], "z"));
		this.setBlock(0,0,2, charSwap(init[2], "z"));
		this.setBlock(0,2,2, charSwap(init[3], "z"));
		
		init[0] = this.getBlock(1,2,2);
		init[1] = this.getBlock(2,1,2);
		init[2] = this.getBlock(1,0,2);
		init[3] = this.getBlock(0,1,2);
		
		this.setBlock(2,1,2, charSwap(init[0], "z"));
		this.setBlock(1,0,2, charSwap(init[1], "z"));
		this.setBlock(0,1,2, charSwap(init[2], "z"));
		this.setBlock(1,2,2, charSwap(init[3], "z"));
	}
	public void frontP(){
		
		String[] init = new String[4];
			
		init[0] = this.getBlock(0,2,2);
		init[1] = this.getBlock(2,2,2);
		init[2] = this.getBlock(2,0,2);
		init[3] = this.getBlock(0,0,2);
		
		this.setBlock(2,2,2, charSwap(init[2], "z"));
		this.setBlock(2,0,2, charSwap(init[3], "z"));
		this.setBlock(0,0,2, charSwap(init[0], "z"));
		this.setBlock(0,2,2, charSwap(init[1], "z"));
		
		init[0] = this.getBlock(1,2,2);
		init[1] = this.getBlock(2,1,2);
		init[2] = this.getBlock(1,0,2);
		init[3] = this.getBlock(0,1,2);
		
		this.setBlock(2,1,2, charSwap(init[2], "z"));
		this.setBlock(1,0,2, charSwap(init[3], "z"));
		this.setBlock(0,1,2, charSwap(init[0], "z"));
		this.setBlock(1,2,2, charSwap(init[1], "z"));
	}
	public void back(){
	
		String[] init = new String[4];
			
		init[0] = this.getBlock(2,2,0);
		init[1] = this.getBlock(0,2,0);
		init[2] = this.getBlock(0,0,0);
		init[3] = this.getBlock(2,0,0);
		
		this.setBlock(0,2,0, charSwap(init[0], "z"));
		this.setBlock(0,0,0, charSwap(init[1], "z"));
		this.setBlock(2,0,0, charSwap(init[2], "z"));
		this.setBlock(2,2,0, charSwap(init[3], "z"));
		
		init[0] = this.getBlock(1,2,0);
		init[1] = this.getBlock(0,1,0);
		init[2] = this.getBlock(1,0,0);
		init[3] = this.getBlock(2,1,0);
		
		this.setBlock(0,1,0, charSwap(init[0], "z"));
		this.setBlock(1,0,0, charSwap(init[1], "z"));
		this.setBlock(2,1,0, charSwap(init[2], "z"));
		this.setBlock(1,2,0, charSwap(init[3], "z"));
	}
	public void backP(){
	
		String[] init = new String[4];
			
		init[0] = this.getBlock(2,2,0);
		init[1] = this.getBlock(0,2,0);
		init[2] = this.getBlock(0,0,0);
		init[3] = this.getBlock(2,0,0);
		
		this.setBlock(0,2,0, charSwap(init[2], "z"));
		this.setBlock(0,0,0, charSwap(init[3], "z"));
		this.setBlock(2,0,0, charSwap(init[0], "z"));
		this.setBlock(2,2,0, charSwap(init[1], "z"));
		
		init[0] = this.getBlock(1,2,0);
		init[1] = this.getBlock(0,1,0);
		init[2] = this.getBlock(1,0,0);
		init[3] = this.getBlock(2,1,0);
		
		this.setBlock(0,1,0, charSwap(init[2], "z"));
		this.setBlock(1,0,0, charSwap(init[3], "z"));
		this.setBlock(2,1,0, charSwap(init[0], "z"));
		this.setBlock(1,2,0, charSwap(init[1], "z"));
	}
	
	/**
	* Performs one random move from the above twelve.
	*/
	public void randMove(){
		int r = (int)(Math.random()*12);
		switch(r){
  		case(0):
  			this.right();
  			break;
  		case(1):
  			this.rightP();
  			break;
  		case(2):
  			this.left();
  			break;
  		case(3):
  			this.leftP();
  			break;
  		case(4):
  			this.up();
  			break;
  		case(5):
  			this.upP();
  			break;
  		case(6):
  			this.down();
  			break;
  		case(7):
  			this.downP();
  			break;
  		case(8):
  			this.front();
  			break;
  		case(9):
  			this.frontP();
  			break;
  		case(10):
  			this.back();
  			break;
  		case(11):
  			this.backP();
  			break;
    	}
	}
	
  /**
  * Scrambles the cube by applying n random moves.
  */
	public void scramble (int n){
		for (int i = 0; i <=n; i++){
			this.randMove();
		}
	}
	
  /**
  * Performs a set of moves in order.
  * @param instructions Array of strings, each of which is 1 move.
  * notation is standard cube notation
  * r is right (clockwise), l' is left prime (counterclockwise)
  * note that moves are case neutral
  * r is the same as R
  * f' is the same as F'
  */
	public void multiMove(String[] instructions){
		for (String i : instructions){
			i = i.toLowerCase();
			switch(i){
			case("r"):
			  this.right();
			  break;
			case("r'"):
			  this.rightP();
			  break;
	  
			case("l"):
			  this.left();
			  break;
			case("l'"):
			  this.leftP();
			  break;
	  
			case("f"):
			  this.front();
			  break;
			case("f'"):
			  this.frontP();
			  break;
	  
			case("b"):
			  this.back();
			  break;
			case("b'"):
			  this.backP();
			  break;
	  
			case("u"):
			  this.up();
			  break;
			case("u'"):
			  this.upP();
			  break;
	  
			case("d"):
			  this.down();
			  break;
			case("d'"):
			  this.downP();
			  break;
		    }
		}
	}
  /**
  * The same as the other multiMove, but takes 1 long string as input instead.
  * moves must be seperated by spaces
  * eg. "r u r' u'"
  */
	public void multiMove(String instructions){
		this.multiMove(instructions.split(" ",0));
	}
	
	/*
	for internal use only
	used inside the basic move methods to swap colors in the string 
	eg. ryb => byr
	*/
	private static String charSwap(String in, String axis){
		String out = new String();
    switch(axis){
      case("z"):
        out = in.substring(1,2) + in.substring(0,1) + in.substring(2);
        break;
      case("x"):
        out = in.substring(0,1) + in.substring(2) + in.substring(1,2);
        break;
      case("y"):
        out = in.substring(2) + in.substring (1,2) + in.substring(0,1);
        break;
    }
		return out;
	}

}
