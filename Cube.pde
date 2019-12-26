import peasy.*;
//makes camera controls with mouse possible

//runs once, all the setup and one time only stuff
CubeObj cube;
PeasyCam camera;
void setup(){
  size(500,500,P3D); 
  camera = new PeasyCam(this, -250, -250, -250, 1000);
  camera.setWheelScale(0.2);
  camera.setMaximumDistance(1000);
  camera.setMinimumDistance(500);
  camera.setRightDragHandler(null);
  camera.setSuppressRollRotationMode();
  camera.rotateY(PI);
  cube = new CubeObj(true);
}

/*
runs over and over again
it all gets called from here
background removes previous render, then renders the new cube on top
*/
void draw(){ 
  background(#000000);
  update();
}

//reads input from keyboard to pass moves to cube
String input = new String();
void keyPressed(){
  input = str(key); 
}

//the meat of the visualization
void update(){
  if (input.equals("r")){
    cube.right();
    input = "None";
  } else if (input.equals("l")){
    cube.left();
    input = "None";
  } else if (input.equals("f")){
    cube.front();
    input = "None";
  } else if (input.equals("b")){
    cube.back();
    input = "None";
  } else if (input.equals("u")){
    cube.up();
    input = "None";
  } else if (input.equals("d")){
    cube.down();
    input = "None";
  }
  //the six regular turns (clockwise)
  
  if (input.equals("R")){
    cube.rightP();
    input = "None";
  } else if (input.equals("L")){
    cube.leftP();
    input = "None";
  } else if (input.equals("F")){
    cube.frontP();
    input = "None";
  } else if (input.equals("B")){
    cube.backP();
    input = "None";
  } else if (input.equals("U")){
    cube.upP();
    input = "None";
  } else if (input.equals("D")){
    cube.downP();
    input = "None";
  }
  //the six inverse turns (counter-clockwise)
  
  //render a black cube so you can't see through the cracks
  pushMatrix();
  translate(-250,-250,-250);
  fill(0);
  box(300);
  popMatrix();

  //rendering each 1 color square
  for (int x = 0; x < 3; x++){
    for (int y = 0; y < 3; y++){
      for (int z = 0; z < 3; z++){ //touch each block
        String c = cube.getBlock(x,y,z);
        String cx = new String();
        String cy = new String();
        String cz = new String(); 
        
        cx = c.substring(0,1);
        cy = c.substring(1,2);
        cz = c.substring(2); //color of each of each axis 
        color colx = colorSet(cx);
        color coly = colorSet(cy);
        color colz = colorSet(cz); 
        //convert to hex color values
        
        facet(x,y,z, colx, "x"); //create each 1 color square
        facet(x,y,z, coly, "y"); //I call each one a "facet"
        facet(x,y,z, colz, "z");
      }
    }
  }         
}

//changes the r/o/w/y/b/g/0 to hex
color colorSet(String c){
  color out = color(#000000);
  switch(c){
    case("0"):
      out = color(#000000);
      break;
    case("r"):
      out = color(#ff2d00);
      break;
    case("o"):
      out = color(#ffad00);
      break;
    case("w"):
      out = color(#ffffff);
      break;
    case("y"):
      out = color(#ffff00);
      break;
    case("b"):
      out = color(#1300ff);
      break;
    case("g"):
      out = color(#01df0b);
      break;
  }
  return out;
}

//renders each small square of color
//1 sticker on a real cube
void facet (int x, int y, int z, color col, String axis){
  pushMatrix();
  rectMode(CENTER);
  x = -x * 100 - 100;
  y = -y * 100 - 100;
  z = -z * 100 - 100; //points to a block

  /*
  the following 6 if/else statements
  are the 3 axis, and which direction
  which is equivalent to which side of the relivant block
  then renders a 2d square 5 units off the surface
  
  note that the method for rendering 2d squares in 3d space 
  relies on creating a square in the xy plane
  and then translating and rotating 
  leaving it at the correct place facing the corrent direction
  */
  if (axis.equals("x") && x < -150){
    translate(x-105, y-50, z - 50);
    rotateY(PI/2);
  } else if (axis.equals("x") && x > -150){
    translate(x+5, y-50, z - 50);
    rotateY(PI/2);
    
  } else if (axis.equals("y") && y < -150){
    translate(x-50, y-105, z-50);
    rotateX(PI/2);
  } else if (axis.equals("y") && y > -150){
    translate(x-50, y+5, z-50);
    rotateX(PI/2);
    
  } else if (axis.equals("z") && z < -150){
    translate(x-50,y-50,z-105);
    
  } else if (axis.equals("z") && z > -150){
    translate(x-50,y-50,z+5);
    
  }
  
  //the rectangle is rendered in the appropriate color
  //if black, nothing is rendered
  if (col != #000000){ 
    fill(col);
    rect(0,0,100,100);
  }
  popMatrix();
}
