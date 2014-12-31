import java.awt.*;
import javax.swing.*;

public class CircleObject extends GraphicalObject{

  CircleObject(Color color, int i0, int j0){
    super(color, i0, j0);
  }

  public void draw(Graphics g, int size){
    g.setColor(color);
    g.fillOval(j0*size, i0*size, size, size);
  }
}