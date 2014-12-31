/**
 * Square.java: a class that models a square
 *  - an example of Inheritance
 */

public class Square extends Rectangle {

  // no new attributes need be introduced

  public Square(int s) {
    // call the 2 variable superclass constructor
    super(s, s);
  }

  public int getSide() {
    return getWidth();
  }

  public String toString() {
    return "Square: Side(" + getSide() + ")";
  }   
}

