/**
 * Rectangle.java: a class that models a rectangle
 */

public class Rectangle {

  private int length;
  private int width;

  public Rectangle(int length, int width) {
    this.length = length;
    this.width = width;
  }
  public int getLength() {
    return length;
  }
  public int getWidth() {
    return width;
  }
  public int area() {
    return length*width;
  }
  public String toString() {
    return "Rectangle: " +
           "Length(" + length + ") " +
           "Width(" + width + ")";
  }
}
