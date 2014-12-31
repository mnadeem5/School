package cs2212.team4;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.text.*;

/**
 * A custom cell renderer used to display and format GRADES in a gradestable object.
 * 
 * @author Jenna Le 
 * @version 1.0 2014-03-31
 */

public class GradeCellRenderer extends DefaultTableCellRenderer{
	private static final long serialVersionUID = 1L;
	private Format formatter;
	private Color unselectedForeground;
	private Color unselectedBackground;

	public GradeCellRenderer(){
		setHorizontalAlignment(SwingConstants.RIGHT);
		NumberFormat numFor = NumberFormat.getNumberInstance();
		DecimalFormat df = (DecimalFormat)numFor;
		df.applyPattern("###.##");
		this.formatter=df;
	}

	/**
	 * Returns a component to help render a grade cell in a GUI.
	 * 
	 * @param table      jTable containing cell to be rendered
	 * @param value      value contained in cell
	 * @param isSelected boolean value determining cell's selection status
	 * @param hasFocus   boolean value determining cell's focus
	 * @param row        row location of cell
	 * @param column     column location of cell
	 * @return           component that displays cell
	 */
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {

		if (value == null){
			setValue("");
		}
		if (value instanceof String){
			if ((value).equals("")){
				setValue("");
			}
			else {
				double amount = Double.parseDouble((String)value);
				String formatted = formatter.format(amount);
				setValue(formatted+"%");
			}
		}

		if (value instanceof Double){
			if (Double.toString((Double)value).equals("")){
				setValue("");
			}
			else {
				double amount = ((double)value);
				String formatted = formatter.format(amount);
				setValue(formatted+"%");
			}
		}

		if (isSelected) {
			super.setForeground(table.getSelectionForeground());
			super.setBackground(table.getSelectionBackground());
		}
		else {
			Color background = unselectedBackground != null
					? unselectedBackground
							: Color.white;
			if (background == null || background.equals(Color.white)) {
				Color alternateColor = new Color(242, 242, 242);
				if (alternateColor != null && row % 2 != 0) {
					background = alternateColor;
				}
			}
			super.setForeground(unselectedForeground != null
					? unselectedForeground
							: table.getForeground());
			super.setBackground(background);

			//      Optional: change the shade of the Course Grade column
			//            if (column==0){
			//                Color c = new Color(0xc4c4c4);
			//                setBackground(c);
			//                if (background == null || c.equals(new Color(0xc4c4c4))) {
			//                    Color d = new Color(0xEEEEEE);
			//                    if (d != null && row % 2 != 0) {
			//                        background = c;
			//                    }
			//                }
			//            }

		}
		table.setBackground(Color.white);
		return this;
	}
}