import java.awt.*;
import javax.swing.*;

public class SquareObject extends GraphicalObject{

  SquareObject(Color color, int i0, int j0){
    super(color, i0, j0);
  }

  public void draw(Graphics g, int size){
    g.setColor(color);
    g.fillRect(j0*size, i0*size, size, size);
  }
}