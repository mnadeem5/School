/**
 * TurtleArt.java
 *
 *
 * Zaid Albirawi.
 * zalbiraw
 * CompuerSci1026
 * 2012/10/2
 */
import java.awt.Color;
import java.lang.Math.*;
public class TurtleArt
{ 
  public static void main (String[] args)
  {
    int x=200, y=100;
    World worldObj = new World(800,600); 
    Turtle turtle = new Turtle(300,250, worldObj);
    
    //sets pen width to 3 and sets color to black
    turtle.setPenWidth(3);turtle.setPenColor(Color.BLACK);
    
    //draws a triangle using the x and y values
    turtle.drawRectangle(x,y);
    
    //moves the turtle then draws a square using the x value
    turtle.moveTo(500,250);
    turtle.drawSquare(y);
    
    //draws and equilateral using the y value
    turtle.drawEquilateral(y);
    
    //moves the turtle then draws and equilateral using the x value
    turtle.moveTo(300,250);
    turtle.drawEquilateral(x);
    
    //sets pen width to 2 and changes the color to 
    turtle.setPenWidth(2);turtle.setPenColor(Color.BLUE);
    
    //moves the turtle to a specific point without drawing lines and draws a square with a length vlue of 70 and turns the turtle to the left
    turtle.penUp();turtle.moveTo(515,350);turtle.turnLeft();turtle.penDown();
    turtle.drawSquare(70);
    
    //moves the turtle to a specific point without drawing lines and draws a rectangle with a length and width vlues of 70 and 20
    turtle.penUp();turtle.moveTo(515,320);turtle.penDown();
    turtle.drawRectangle(20,70);
    
    //draws a rectangle with a length and width values of 70 and 20, -20 is used to change the direction
    turtle.drawRectangle(-20,70);
    
    //moves the turtle to a specific point without drawing lines and draws a rectangle with a length and width vlues of 75 and 50 and turns the turtle to the right
    turtle.penUp();turtle.moveTo(375,275);turtle.turnRight();turtle.penDown();
    turtle.drawRectangle(50,75);
    
    //moves the turtle to a specific point without drawing lines and draws a square with a length  vlues of 25 and sets the pen color to blue
    turtle.penUp();turtle.moveTo(325,285);turtle.penDown();
    turtle.setPenColor(Color.BLUE);
    turtle.drawSquare(25);
    
    //moves the turtle to a specific point without drawing lines and draws a square with a length  vlues of 25
    turtle.penUp();turtle.moveTo(450,285);turtle.penDown();
    turtle.drawSquare(25);
    
    //hides turtle
    turtle.hide();
    
    //extras
    
    turtle.setPenColor(Color.RED);
    turtle.penUp();turtle.moveTo(337,227);turtle.penDown();
    turtle.drawEquilateral(x-75);
    
    turtle.setPenColor(Color.BLACK);
    turtle.penUp();turtle.moveTo(375,207);turtle.penDown();
    turtle.drawEquilateral(x-150);
    
    turtle.penUp();turtle.moveTo(399,207);turtle.penDown();turtle.forward((int)((Math.sqrt(3))*25));
    turtle.penUp();turtle.moveTo(385,190);turtle.penDown();turtle.turnRight();turtle.forward(27);
    
    turtle.setPenWidth(5);
    turtle.penUp();turtle.moveTo(296,325);turtle.penDown();
    turtle.setPenColor(Color.GREEN);turtle.turn(-180);turtle.forward(396);
    
    turtle.penUp();turtle.moveTo(604,325);turtle.penDown();
    turtle.turn(-180);turtle.forward(196);
  }
}