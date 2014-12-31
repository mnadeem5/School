package ca.uwo.csd.cs2212.zalbiraw;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class MainWindow extends JFrame {

	private JButton btnGetSelection;
	private JTable tblCustomers;
	private JTextArea txtOutput;

	public MainWindow() {
		initComponents();
		initTable();
	}

	private void initComponents() {

		JPanel pnlOutput = new JPanel();
		JScrollPane scrOutput = new JScrollPane();
		JPanel pnlTable = new JPanel();
		JScrollPane scrTable = new JScrollPane();
		JToolBar toolBar = new JToolBar();

		btnGetSelection = new JButton();
		tblCustomers = new JTable();
		txtOutput = new JTextArea();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		pnlOutput.setBorder(BorderFactory.createTitledBorder("Output"));
		pnlOutput.setLayout(new BorderLayout());

		txtOutput.setColumns(20);
		txtOutput.setRows(5);
		scrOutput.setViewportView(txtOutput);

		pnlOutput.add(scrOutput, BorderLayout.CENTER);

		getContentPane().add(pnlOutput, BorderLayout.SOUTH);
		pnlOutput.getAccessibleContext().setAccessibleDescription("");

		pnlTable.setBorder(BorderFactory.createTitledBorder("Customer Data"));
		pnlTable.setLayout(new BorderLayout());

		scrTable.setViewportView(tblCustomers);

		pnlTable.add(scrTable, BorderLayout.CENTER);

		getContentPane().add(pnlTable, BorderLayout.CENTER);

		toolBar.setRollover(true);

		btnGetSelection.setText("Get Selection");
		btnGetSelection.setFocusable(false);
		btnGetSelection.setHorizontalTextPosition(SwingConstants.CENTER);
		btnGetSelection.setVerticalTextPosition(SwingConstants.BOTTOM);

		toolBar.add(btnGetSelection);

		getContentPane().add(toolBar, BorderLayout.NORTH);

		pack();
	}

	private void initTable()
	{
		initModel();
		setColumnWidths();
		tblCustomers.setAutoCreateRowSorter(true);
		
		this.btnGetSelection.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				StringBuilder sb = new StringBuilder();
				int [] selectedRows = tblCustomers.getSelectedRows();
				
				sb.append("Selected customers :\n");
				
				for (int row : selectedRows)
				{
					String firstName = tblCustomers.getModel().getValueAt(row, 0).toString();
					String lastName = tblCustomers.getModel().getValueAt(row, 1).toString();
					sb.append(firstName).append(" ").append(lastName).append("\n");
				}
				txtOutput.setText(txtOutput.getText()+"\n"+sb.toString());
			}
		});
		
		tblCustomers.setCellSelectionEnabled(true);
		tblCustomers.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "startEditing");
	}

	private void initModel() {
		/**tblCustomers.setModel(new DefaultTableModel(new Object[][] {
				{ "John ", "Doe", "jdoe@example.com", 32.50 },
				{ "Jane ", "Doe", "jane.doe@example.net", 0 },
				{ "Zaid ", "Albirawi", "zaid@example.net", -2 },
				{ "Allan ", "Yan", "realG@cityWOK.net", 100 },
				{ "Valmir ", "Vebrownie", " verbDerp@brownie.net", -40.50 }, },
				new String[] { "First Name", "Last Name", "Email",
						"Amount Owing" }));*/
		CustomerTableModel cTable = new CustomerTableModel();
		
		Customer c0 = new Customer ("John ", "Doe", "jdoe@example.com", 32.50 );
		Customer c1 = new Customer ("Jane ", "Doe", "jane.doe@example.net", 0);
		Customer c2 = new Customer ("Zaid ", "Albirawi", "zaid@example.net", -2);
		Customer c3 = new Customer ("Allan ", "Yan", "realG@cityWOK.net", 100);
		Customer c4 = new Customer ("Valmir ", "Vebrownie", " verbDerp@brownie.net", -40.50);
		
		java.util.List<Customer> customers=cTable.getCustomers();
		
		customers.add(c0);
		customers.add(c1);
		customers.add(c2);
		customers.add(c3);
		customers.add(c4);
		
		tblCustomers.setModel(cTable);
		
		tblCustomers.setDefaultRenderer(Double.class, new CurrencyCellRenderer());
	}

	private void setColumnWidths() {
		tblCustomers.getColumnModel().getColumn(0).setPreferredWidth(35);
		tblCustomers.getColumnModel().getColumn(1).setPreferredWidth(35);
		tblCustomers.getColumnModel().getColumn(2).setPreferredWidth(100);
		tblCustomers.getColumnModel().getColumn(3).setPreferredWidth(50);
	}
}