import java.awt.*;
import javax.swing.*;

public class TriangleLeftObject extends GraphicalObject{

  TriangleLeftObject(Color color, int i0, int j0){
    super(color, i0, j0);
  }

  public void draw(Graphics g, int size){
    g.setColor(color);
    int[] x = new int[3];
    int[] y = new int[3];
    x[0] = (j0+1)*size;
    y[0] = i0*size;

    x[1] = j0*size;
    y[1] = i0*size + (size/2);

    x[2] = (j0+1)*size;
    y[2] = (i0+1)*size;

    g.fillPolygon(x, y, 3);
  }
}