import java.awt.*;
import javax.swing.*;

abstract class GraphicalObject{
  protected Color color;
  protected int i0, j0;

  GraphicalObject(Color color, int i0, int j0){
    this.color = color;
    this.i0 = i0;
    this.j0 = j0;
  }

  abstract public void draw(Graphics g, int size);
}