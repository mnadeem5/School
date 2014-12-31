package ca.uwo.csd.cs2212.zalbiraw;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class CustomerTableModel extends AbstractTableModel {

	private final static int COLUMN_COUNT = 4;
	private final static int IDX_FIRST_NAME = 0;
	private final static int IDX_LAST_NAME = 1;
	private final static int IDX_EMAIL = 2;
	private final static int IDX_BALANCE = 3;

	private final List<Customer> customers;

	public CustomerTableModel() {
		customers = new ArrayList<>();
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	@Override
	public int getRowCount() {
		return customers.size();
	}

	@Override
	public int getColumnCount() {
		return COLUMN_COUNT;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return (columnIndex == IDX_BALANCE ? Double.class : String.class);
	}

	@Override
	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case IDX_FIRST_NAME:
			return "First Name";
		case IDX_LAST_NAME:
			return "Last Name";
		case IDX_EMAIL:
			return "Email";
		case IDX_BALANCE:
			return "Balance Owing";
		default:
			return null;
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if ((rowIndex < 0) || (rowIndex >= customers.size()))
			return null;

		Customer customer = customers.get(rowIndex);

		switch (columnIndex) {
		case IDX_FIRST_NAME:
			return customer.getFirstName();
		case IDX_LAST_NAME:
			return customer.getLastName();
		case IDX_EMAIL:
			return customer.getEmail();
		case IDX_BALANCE:
			return customer.getBalanceOwing();
		default:
			return null;
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if ((rowIndex < 0) || (rowIndex >= customers.size())
				|| columnIndex != IDX_BALANCE)
			return;

		Customer customer = customers.get(rowIndex);

		if (columnIndex == IDX_BALANCE) {
			customer.setBalanceOwing((Double)aValue);
			fireTableCellUpdated(rowIndex, columnIndex);
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {

		if (columnIndex == IDX_BALANCE) return true;
		return false;
	}
}