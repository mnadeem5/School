package ca.uwo.csd.cs2212.zalbiraw;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;

import java.text.Format;
import java.text.NumberFormat;

import javax.swing.SwingConstants;

public class CurrencyCellRenderer extends DefaultTableCellRenderer {

	private final Format formatter;

	public CurrencyCellRenderer() {
		this.formatter = NumberFormat.getCurrencyInstance();

		setHorizontalAlignment(this.RIGHT);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {

		Double amount = (Double) value;
		if (amount > 0)
			setForeground(new Color(255, 0, 0));
		else
			setForeground(new Color(0, 0, 0));

		// Format the value with a currency symbol
		String formattedValue = formatter.format(amount);

		setValue(formattedValue);

		if (isSelected) {
			setForeground((Color)UIManager.get("Table.selectionForeground"));
			setBackground((Color)UIManager.get("Table.selectionBackground"));
		} else {
			setBackground((Color)UIManager.get("Table.background"));
		}

		return this;
	}
}