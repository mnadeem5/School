package cs2212.team4;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class GradebookGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private Gradebook gradebook = new Gradebook();

	private Border errorHighlightBorder = BorderFactory
			.createLineBorder(Color.RED);
	private Border defaultHighlightBorder;

	private DefaultListModel<String> listCourses = new DefaultListModel<String>();
	private DefaultListModel<String> listDelivers = new DefaultListModel<String>();

	UsersTable tableStudents = new UsersTable();
	GradesTable tableGrades = tableStudents.getGradesTable();

	private Font helvetica = new java.awt.Font("Arial", 1, 10);

	Course currCourse;

	JFrame addFrame = new JFrame("");
	JFrame emailFrame = new JFrame("");
	JFrame calcFrame = new JFrame("");
	JFrame propsFrame = new JFrame("");

	public GradebookGUI() {
		int size;

		initComponents();
		getContentPane().setBackground(new Color(20, 150, 250));
		tabGrades.setVisible(true);
		tabSetup.setVisible(false);
		tabDropbox.setVisible(false);

		pnlCourseMenu.setVisible(false);
		defaultHighlightBorder = txtCourseName.getBorder();
		try {
			InputStream is = new FileInputStream(new File(
					"/src/main/resources/cs2212/team4/helvetica.ttf"));
			helvetica = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FileNotFoundException e) {
		} catch (FontFormatException e) {
		} catch (IOException e) {
		}

		if (helvetica != null) {
			helvetica = helvetica.deriveFont(0, 16);
			lblActiveCourseTitleInfo.setFont(helvetica);
			helvetica = helvetica.deriveFont(0, 16);
			lblGrades.setFont(helvetica);
			lblSetup.setFont(helvetica);
			lblDropbox.setFont(helvetica);
		}
		pnlAddCourse.setVisible(false);
		pnlAddDeliver.setVisible(false);
		pnlAddStudent.setVisible(false);
		addFrame.setResizable(false);
		addFrame.setSize(350, 270);
		addFrame.setLocationRelativeTo(this);
		addFrame.setAlwaysOnTop(true);
		addFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				enable();
			}
		});

		pnlEmail.setVisible(false);
		emailFrame.setResizable(false);
		emailFrame.setSize(600, 420);
		emailFrame.setLocationRelativeTo(this);
		emailFrame.setAlwaysOnTop(true);
		emailFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				enable();
			}
		});

		calcFrame.setResizable(false);
		calcFrame.setSize(500, 220);
		calcFrame.setLocationRelativeTo(this);
		calcFrame.setAlwaysOnTop(true);
		calcFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				enable();
			}
		});

		propsFrame.setResizable(false);
		propsFrame.setSize(520, 290);
		propsFrame.setLocationRelativeTo(this);
		propsFrame.setAlwaysOnTop(true);
		propsFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				enable();
			}
		});

		Course crs;
		if ((size = gradebook.getCourseListSize()) > 0)
			for (int i = 0; i < size; i++) {
				crs = gradebook.getCourse(i);
				listCourses.addElement(crs.getTitle() + ", " + crs.getCode()
						+ crs.getTerm());
			}
		else
			listCourses.addElement("No Courses");

		studentTable.setModel(tableStudents);
		gradesTable.setModel(tableStudents.getGradesTable());

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height
				/ 2 - this.getSize().height / 2);

		txtCourseDesc.setText("Enter a course description...");

		if (gradebook.getPrevCourse() != null) {
			currCourse = gradebook.getPrevCourse();
			courseMenuList.setSelectedIndex(gradebook.findCourse(currCourse));
		}

		updateInfo();

		lblDbxDownload.setVisible(false);
		lblDbxUpload.setVisible(false);

		msgText.setText("Please enter a massage content...");
		msgText.setForeground(Color.LIGHT_GRAY);

		boolean boolEmailProps;
		if (gradebook.getProperties() == null)
			boolEmailProps = false;
		else
			boolEmailProps = true;

		if (boolEmailProps) {
			lblEmailVarify.setIcon(new javax.swing.ImageIcon(getClass()
					.getResource("/cs2212/team4/emailTrue.png")));
			lblEMLErrorLog.setForeground(Color.green);
			lblEMLErrorLog.setText("Connected.");
		} else {
			lblEmailVarify.setIcon(new javax.swing.ImageIcon(getClass()
					.getResource("/cs2212/team4/emailFalse.png")));
			lblEMLErrorLog.setForeground(Color.lightGray);
			lblEMLErrorLog.setText("Disconnected.");
		}
	}

	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		pnlAddStudent = new javax.swing.JPanel();
		pnlAddStudentContainer = new javax.swing.JPanel();
		lblAddStudentTitle = new javax.swing.JLabel();
		lblStudentNameFirst = new javax.swing.JLabel();
		txtStudentNameFirst = new javax.swing.JTextField();
		lblStudentNameLast = new javax.swing.JLabel();
		txtStudentNameLast = new javax.swing.JTextField();
		lblStudentNumber = new javax.swing.JLabel();
		txtStudentNumber = new javax.swing.JTextField();
		lblStudentEmail = new javax.swing.JLabel();
		txtStudentEmail = new javax.swing.JTextField();
		lblStudentAddErrorLog = new javax.swing.JLabel();
		lblAddStudent = new javax.swing.JLabel();
		lblCancelStudentAddition = new javax.swing.JLabel();
		pnlEmail = new javax.swing.JPanel();
		lblEmailErrorLog = new javax.swing.JLabel();
		lblSendEmail = new javax.swing.JLabel();
		subject = new javax.swing.JLabel();
		txtSubject = new javax.swing.JTextField();
		recipients = new javax.swing.JLabel();
		lblRecipients = new javax.swing.JLabel();
		toggle = new javax.swing.JLabel();
		lblToggle = new javax.swing.JLabel();
		msgScroll = new javax.swing.JScrollPane();
		msgText = new javax.swing.JTextArea();
		pnlAddDeliver = new javax.swing.JPanel();
		lblDeliverName = new javax.swing.JLabel();
		txtDeliverName = new javax.swing.JTextField();
		txtDeliverWeight = new javax.swing.JTextField();
		lblDeliverType = new javax.swing.JLabel();
		lblDeliverWeight = new javax.swing.JLabel();
		lblAddDeliverTitle = new javax.swing.JLabel();
		lblAddDeliver = new javax.swing.JLabel();
		comboDeliverType = new javax.swing.JComboBox();
		lblCancelDeliverAddition = new javax.swing.JLabel();
		addDeliverErrorLog = new javax.swing.JLabel();
		pnlAddCourse = new javax.swing.JPanel();
		pnlAddCourseContainer = new javax.swing.JPanel();
		lblAddCourseTitle = new javax.swing.JLabel();
		lblCourseName = new javax.swing.JLabel();
		txtCourseName = new javax.swing.JTextField();
		lblCourseCode = new javax.swing.JLabel();
		txtCourseCode = new javax.swing.JTextField();
		lblCourseTerm = new javax.swing.JLabel();
		comboCourseTerm = new javax.swing.JComboBox();
		lblCourseAddErrorLog = new javax.swing.JLabel();
		lblAddCourse = new javax.swing.JLabel();
		lblCancelCourseAddition = new javax.swing.JLabel();
		pnlCalc = new javax.swing.JPanel();
		lblCalcTitle = new javax.swing.JLabel();
		lblEnterAvg = new javax.swing.JLabel();
		txtAvg = new javax.swing.JTextField();
		lblCalculate = new javax.swing.JLabel();
		resultScroll = new javax.swing.JScrollPane();
		lblResult = new javax.swing.JTextPane();
		pnlCustom = new javax.swing.JPanel();
		lblHostName1 = new javax.swing.JLabel();
		lblHostName3 = new javax.swing.JLabel();
		txtHostName = new javax.swing.JTextField();
		txtCustomPassword = new javax.swing.JPasswordField();
		lblPassword1 = new javax.swing.JLabel();
		lblEmailAddress1 = new javax.swing.JLabel();
		txtCustomEmail = new javax.swing.JTextField();
		txtPort = new javax.swing.JTextField();
		lblCutomTitle = new javax.swing.JLabel();
		lblCustomSubmit = new javax.swing.JLabel();
		lblSMTPError = new javax.swing.JLabel();
		lblGrades = new javax.swing.JLabel();
		lblTabGrades = new javax.swing.JLabel();
		lblSetup = new javax.swing.JLabel();
		lblTabSetup = new javax.swing.JLabel();
		lblDropbox = new javax.swing.JLabel();
		lblTabDropbox = new javax.swing.JLabel();
		lblMini = new javax.swing.JLabel();
		lblExit = new javax.swing.JLabel();
		myCourses = new javax.swing.JLabel();
		pnlCourseMenu = new javax.swing.JPanel();
		courses = new javax.swing.JScrollPane();
		courseMenuList = new javax.swing.JList(listCourses);
		addCourse = new javax.swing.JLabel();
		container = new javax.swing.JLayeredPane();
		tabGrades = new javax.swing.JPanel();
		deliversScroll = new javax.swing.JScrollPane();
		deliverList = new javax.swing.JList(listDelivers);
		courseName = new javax.swing.JLabel();
		editCourseIcon = new javax.swing.JLabel();
		addDeliver = new javax.swing.JLabel();
		addStudent = new javax.swing.JLabel();
		pnlTables = new javax.swing.JPanel();
		studentScroll = new javax.swing.JScrollPane();
		studentTable = new javax.swing.JTable();
		gradesScroll = new javax.swing.JScrollPane();
		gradesTable = new javax.swing.JTable();
		deleteDeliver = new javax.swing.JLabel();
		deleteStudent = new javax.swing.JLabel();
		lblExportStudents = new javax.swing.JLabel();
		lblImportStudents = new javax.swing.JLabel();
		lblFirstName = new javax.swing.JLabel();
		lblLastName = new javax.swing.JLabel();
		lblNumber = new javax.swing.JLabel();
		lblEmail = new javax.swing.JLabel();
		lblExmAvg = new javax.swing.JLabel();
		lblAsnAvg = new javax.swing.JLabel();
		lblImportGrades = new javax.swing.JLabel();
		lblExportGrades = new javax.swing.JLabel();
		lblCourseAvg = new javax.swing.JLabel();
		courseAsnAvg = new javax.swing.JLabel();
		courseExamAvg = new javax.swing.JLabel();
		lblCourseAsnAvg = new javax.swing.JLabel();
		lblCourseExamAvg = new javax.swing.JLabel();
		courseAvg = new javax.swing.JLabel();
		lblEmailStudents = new javax.swing.JLabel();
		lblCalculator = new javax.swing.JLabel();
		lblGradesErrorLog = new javax.swing.JLabel();
		tabSetup = new javax.swing.JPanel();
		lblCourseSetup = new javax.swing.JLabel();
		lblEditCourseTtile = new javax.swing.JLabel();
		txtEditCourseTitle = new javax.swing.JTextField();
		lblEditCourseCode = new javax.swing.JLabel();
		txtEditCourseCode = new javax.swing.JTextField();
		lblEditCourseTerm = new javax.swing.JLabel();
		comboEditCourseTerm = new javax.swing.JComboBox();
		lblEditCourse = new javax.swing.JLabel();
		lblDeleteCourse = new javax.swing.JLabel();
		editDeliverListScroll = new javax.swing.JScrollPane();
		editDeliverList = new javax.swing.JList(listDelivers);
		lblEditDeliverName = new javax.swing.JLabel();
		lblEditDeliverWeight = new javax.swing.JLabel();
		lblEditDeliverType = new javax.swing.JLabel();
		comboEditDeliverType = new javax.swing.JComboBox();
		txtEditDeliverName = new javax.swing.JTextField();
		txtEditDeliverWeight = new javax.swing.JTextField();
		lblEditDeliver = new javax.swing.JLabel();
		lblExportDelivers = new javax.swing.JLabel();
		lblImportDelivers = new javax.swing.JLabel();
		lblCourseDeliverables = new javax.swing.JLabel();
		lblSetupErrorLog = new javax.swing.JLabel();
		courseDescScroll = new javax.swing.JScrollPane();
		txtCourseDesc = new javax.swing.JTextPane();
		lblEditDeleteDeliver = new javax.swing.JLabel();
		lblDeleteSure = new javax.swing.JLabel();
		lblBlue = new javax.swing.JLabel();
		lblWestern = new javax.swing.JLabel();
		lblCobalt = new javax.swing.JLabel();
		lblTeal = new javax.swing.JLabel();
		lblViolet = new javax.swing.JLabel();
		lblMagenta = new javax.swing.JLabel();
		lblOlive = new javax.swing.JLabel();
		lblMauve = new javax.swing.JLabel();
		lblSteel = new javax.swing.JLabel();
		tabDropbox = new javax.swing.JPanel();
		txtDbxCode = new javax.swing.JTextField();
		lblDbxActivate = new javax.swing.JLabel();
		lblDbxUpload = new javax.swing.JLabel();
		lblDbxDownload = new javax.swing.JLabel();
		lblDbxSubmit = new javax.swing.JLabel();
		lblDbxErrorLog = new javax.swing.JLabel();
		lblDbxVerify = new javax.swing.JLabel();
		lblEmailVarify = new javax.swing.JLabel();
		txtEmail = new javax.swing.JTextField();
		txtPassword = new javax.swing.JPasswordField();
		lblPassword = new javax.swing.JLabel();
		comboEmail = new javax.swing.JComboBox();
		lblEmailAddress = new javax.swing.JLabel();
		lblEMLErrorLog = new javax.swing.JLabel();
		customizeSMTP = new javax.swing.JLabel();
		lblSignin = new javax.swing.JLabel();
		lyrActiveCourse = new javax.swing.JLayeredPane();
		lblActiveCourseTitleInfo = new javax.swing.JLabel();
		lblActiveCourseInfo = new javax.swing.JLabel();
		lblActiveCourse = new javax.swing.JLabel();

		pnlAddStudent.setPreferredSize(new java.awt.Dimension(350, 250));

		pnlAddStudentContainer.setBackground(new java.awt.Color(255, 255, 255));
		pnlAddStudentContainer
				.setPreferredSize(new java.awt.Dimension(340, 225));

		lblAddStudentTitle.setFont(new java.awt.Font("Helvetica", 0, 16)); // NOI18N
		lblAddStudentTitle
				.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblAddStudentTitle.setText("Add a New Student");

		lblStudentNameFirst.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
		lblStudentNameFirst
				.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		lblStudentNameFirst.setText("Student First Name:");
		lblStudentNameFirst.setToolTipText("");
		lblStudentNameFirst.setPreferredSize(new java.awt.Dimension(150, 30));

		txtStudentNameFirst.setForeground(new java.awt.Color(204, 204, 204));
		txtStudentNameFirst.setText("ex. John");
		txtStudentNameFirst.setPreferredSize(new java.awt.Dimension(150, 30));
		txtStudentNameFirst.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				txtStudentNameFirstFocusGained(evt);
			}

			public void focusLost(java.awt.event.FocusEvent evt) {
				txtStudentNameFirstFocusLost(evt);
			}
		});

		lblStudentNameLast.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
		lblStudentNameLast
				.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		lblStudentNameLast.setText("Student Last Name:");
		lblStudentNameLast.setToolTipText("");
		lblStudentNameLast.setPreferredSize(new java.awt.Dimension(150, 30));

		txtStudentNameLast.setForeground(new java.awt.Color(204, 204, 204));
		txtStudentNameLast.setText("ex. Doe");
		txtStudentNameLast.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				txtStudentNameLastFocusGained(evt);
			}

			public void focusLost(java.awt.event.FocusEvent evt) {
				txtStudentNameLastFocusLost(evt);
			}
		});

		lblStudentNumber.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
		lblStudentNumber
				.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		lblStudentNumber.setText("Student Number:");
		lblStudentNumber.setToolTipText("");

		txtStudentNumber.setForeground(new java.awt.Color(204, 204, 204));
		txtStudentNumber.setText("ex. 250626000");
		txtStudentNumber.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				txtStudentNumberFocusGained(evt);
			}

			public void focusLost(java.awt.event.FocusEvent evt) {
				txtStudentNumberFocusLost(evt);
			}
		});

		lblStudentEmail.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
		lblStudentEmail
				.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		lblStudentEmail.setText("Student Email:");
		lblStudentEmail.setToolTipText("");

		txtStudentEmail.setForeground(new java.awt.Color(204, 204, 204));
		txtStudentEmail.setText("ex. john-doe@example.com");
		txtStudentEmail.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				txtStudentEmailFocusGained(evt);
			}

			public void focusLost(java.awt.event.FocusEvent evt) {
				txtStudentEmailFocusLost(evt);
			}
		});

		lblStudentAddErrorLog.setForeground(new java.awt.Color(255, 0, 0));

		lblAddStudent.setBackground(new java.awt.Color(255, 255, 255));
		lblAddStudent.setFont(new java.awt.Font("Helvetica", 0, 16)); // NOI18N
		lblAddStudent.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblAddStudent.setText("Add");
		lblAddStudent
				.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		lblAddStudent.setOpaque(true);
		lblAddStudent.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblAddStudentMouseClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				lblAddStudentMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				lblAddStudentMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				lblAddStudentMousePressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				lblAddStudentMouseReleased(evt);
			}
		});

		lblCancelStudentAddition
				.setBackground(new java.awt.Color(255, 255, 255));
		lblCancelStudentAddition.setFont(new java.awt.Font("Helvetica", 0, 16)); // NOI18N
		lblCancelStudentAddition
				.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblCancelStudentAddition.setText("Cancel");
		lblCancelStudentAddition.setCursor(new java.awt.Cursor(
				java.awt.Cursor.HAND_CURSOR));
		lblCancelStudentAddition.setOpaque(true);
		lblCancelStudentAddition
				.addMouseListener(new java.awt.event.MouseAdapter() {
					public void mouseClicked(java.awt.event.MouseEvent evt) {
						lblCancelStudentAdditionMouseClicked(evt);
					}

					public void mouseEntered(java.awt.event.MouseEvent evt) {
						lblCancelStudentAdditionMouseEntered(evt);
					}

					public void mouseExited(java.awt.event.MouseEvent evt) {
						lblCancelStudentAdditionMouseExited(evt);
					}

					public void mousePressed(java.awt.event.MouseEvent evt) {
						lblCancelStudentAdditionMousePressed(evt);
					}

					public void mouseReleased(java.awt.event.MouseEvent evt) {
						lblCancelStudentAdditionMouseReleased(evt);
					}
				});

		javax.swing.GroupLayout pnlAddStudentContainerLayout = new javax.swing.GroupLayout(
				pnlAddStudentContainer);
		pnlAddStudentContainer.setLayout(pnlAddStudentContainerLayout);
		pnlAddStudentContainerLayout
				.setHorizontalGroup(pnlAddStudentContainerLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								pnlAddStudentContainerLayout
										.createSequentialGroup()
										.addComponent(
												lblAddStudent,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												165,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(0, 0, 0)
										.addComponent(
												lblCancelStudentAddition,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE))
						.addGroup(
								pnlAddStudentContainerLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												pnlAddStudentContainerLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																lblAddStudentTitle,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addGroup(
																pnlAddStudentContainerLayout
																		.createSequentialGroup()
																		.addGroup(
																				pnlAddStudentContainerLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING,
																								false)
																						.addComponent(
																								lblStudentEmail,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								lblStudentNumber,
																								javax.swing.GroupLayout.Alignment.LEADING,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								lblStudentNameFirst,
																								javax.swing.GroupLayout.Alignment.LEADING,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								151,
																								Short.MAX_VALUE)
																						.addComponent(
																								lblStudentNameLast,
																								javax.swing.GroupLayout.Alignment.LEADING,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE))
																		.addGap(10,
																				10,
																				10)
																		.addGroup(
																				pnlAddStudentContainerLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								txtStudentEmail,
																								javax.swing.GroupLayout.Alignment.TRAILING,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								169,
																								Short.MAX_VALUE)
																						.addComponent(
																								txtStudentNameLast)
																						.addComponent(
																								txtStudentNameFirst,
																								javax.swing.GroupLayout.Alignment.TRAILING,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								169,
																								Short.MAX_VALUE)
																						.addComponent(
																								txtStudentNumber)))
														.addComponent(
																lblStudentAddErrorLog,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addContainerGap()));
		pnlAddStudentContainerLayout
				.setVerticalGroup(pnlAddStudentContainerLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								pnlAddStudentContainerLayout
										.createSequentialGroup()
										.addGap(0, 0, 0)
										.addComponent(
												lblAddStudentTitle,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												35,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(5, 5, 5)
										.addGroup(
												pnlAddStudentContainerLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																txtStudentNameFirst,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblStudentNameFirst,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(5, 5, 5)
										.addGroup(
												pnlAddStudentContainerLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																txtStudentNameLast,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																28,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblStudentNameLast,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addGap(5, 5, 5)
										.addGroup(
												pnlAddStudentContainerLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																lblStudentNumber,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																30,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																txtStudentNumber,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																28,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(5, 5, 5)
										.addGroup(
												pnlAddStudentContainerLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																lblStudentEmail,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																30,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																txtStudentEmail,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																28,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(5, 5, 5)
										.addComponent(
												lblStudentAddErrorLog,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												20,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(0, 0, 0)
										.addGroup(
												pnlAddStudentContainerLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																lblAddStudent,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																30,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblCancelStudentAddition,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																30,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(0, 0, 0)));

		javax.swing.GroupLayout pnlAddStudentLayout = new javax.swing.GroupLayout(
				pnlAddStudent);
		pnlAddStudent.setLayout(pnlAddStudentLayout);
		pnlAddStudentLayout.setHorizontalGroup(pnlAddStudentLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						pnlAddStudentLayout
								.createSequentialGroup()
								.addGap(0, 0, 0)
								.addComponent(pnlAddStudentContainer,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										350, Short.MAX_VALUE).addGap(0, 0, 0)));
		pnlAddStudentLayout.setVerticalGroup(pnlAddStudentLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						pnlAddStudentLayout
								.createSequentialGroup()
								.addGap(0, 0, 0)
								.addComponent(pnlAddStudentContainer,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										250, Short.MAX_VALUE).addGap(0, 0, 0)));

		pnlEmail.setBackground(new java.awt.Color(255, 255, 255));
		pnlEmail.setPreferredSize(new java.awt.Dimension(600, 400));

		lblEmailErrorLog.setPreferredSize(new java.awt.Dimension(580, 15));

		lblSendEmail.setBackground(new java.awt.Color(255, 255, 255));
		lblSendEmail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblSendEmail.setText("Send Email");
		lblSendEmail.setBorder(javax.swing.BorderFactory
				.createLineBorder(java.awt.Color.lightGray));
		lblSendEmail
				.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		lblSendEmail.setPreferredSize(new java.awt.Dimension(580, 30));
		lblSendEmail.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblSendEmailMouseClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				lblSendEmailMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				lblSendEmailMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				lblSendEmailMousePressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				lblSendEmailMouseReleased(evt);
			}
		});

		subject.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
		subject.setText("Email Subject:");
		subject.setPreferredSize(new java.awt.Dimension(120, 30));

		txtSubject.setText("Please enter an email subject...");
		txtSubject.setPreferredSize(new java.awt.Dimension(350, 30));
		txtSubject.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				txtSubjectFocusGained(evt);
			}

			public void focusLost(java.awt.event.FocusEvent evt) {
				txtSubjectFocusLost(evt);
			}
		});

		recipients.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
		recipients.setText("Email Recipients:");
		recipients.setPreferredSize(new java.awt.Dimension(120, 30));

		lblRecipients.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		lblRecipients.setPreferredSize(new java.awt.Dimension(120, 30));

		toggle.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
		toggle.setForeground(java.awt.Color.lightGray);
		toggle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		toggle.setText("Toggle to attach:");
		toggle.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
		toggle.setPreferredSize(new java.awt.Dimension(120, 30));

		lblToggle.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
		lblToggle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblToggle.setText("Student Report");
		lblToggle.setToolTipText("");
		lblToggle.setBorder(javax.swing.BorderFactory
				.createLineBorder(java.awt.Color.lightGray));
		lblToggle.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		lblToggle.setPreferredSize(new java.awt.Dimension(120, 30));
		lblToggle.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblToggleMouseClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				lblToggleMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				lblToggleMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				lblToggleMousePressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				lblToggleMouseReleased(evt);
			}
		});

		msgText.setColumns(20);
		msgText.setRows(5);
		msgText.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				msgTextFocusGained(evt);
			}

			public void focusLost(java.awt.event.FocusEvent evt) {
				msgTextFocusLost(evt);
			}
		});
		msgScroll.setViewportView(msgText);

		javax.swing.GroupLayout pnlEmailLayout = new javax.swing.GroupLayout(
				pnlEmail);
		pnlEmail.setLayout(pnlEmailLayout);
		pnlEmailLayout
				.setHorizontalGroup(pnlEmailLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								pnlEmailLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												pnlEmailLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																pnlEmailLayout
																		.createSequentialGroup()
																		.addComponent(
																				msgScroll)
																		.addContainerGap())
														.addGroup(
																pnlEmailLayout
																		.createSequentialGroup()
																		.addGroup(
																				pnlEmailLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								lblSendEmail,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addGroup(
																								pnlEmailLayout
																										.createSequentialGroup()
																										.addGroup(
																												pnlEmailLayout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING)
																														.addGroup(
																																pnlEmailLayout
																																		.createSequentialGroup()
																																		.addComponent(
																																				subject,
																																				javax.swing.GroupLayout.PREFERRED_SIZE,
																																				javax.swing.GroupLayout.DEFAULT_SIZE,
																																				javax.swing.GroupLayout.PREFERRED_SIZE)
																																		.addPreferredGap(
																																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																		.addComponent(
																																				txtSubject,
																																				javax.swing.GroupLayout.PREFERRED_SIZE,
																																				javax.swing.GroupLayout.DEFAULT_SIZE,
																																				javax.swing.GroupLayout.PREFERRED_SIZE))
																														.addGroup(
																																pnlEmailLayout
																																		.createSequentialGroup()
																																		.addComponent(
																																				recipients,
																																				javax.swing.GroupLayout.PREFERRED_SIZE,
																																				javax.swing.GroupLayout.DEFAULT_SIZE,
																																				javax.swing.GroupLayout.PREFERRED_SIZE)
																																		.addGap(5,
																																				5,
																																				5)
																																		.addComponent(
																																				lblRecipients,
																																				javax.swing.GroupLayout.PREFERRED_SIZE,
																																				350,
																																				javax.swing.GroupLayout.PREFERRED_SIZE)))
																										.addGroup(
																												pnlEmailLayout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING)
																														.addGroup(
																																pnlEmailLayout
																																		.createSequentialGroup()
																																		.addGap(5,
																																				5,
																																				5)
																																		.addComponent(
																																				toggle,
																																				javax.swing.GroupLayout.PREFERRED_SIZE,
																																				0,
																																				Short.MAX_VALUE))
																														.addGroup(
																																pnlEmailLayout
																																		.createSequentialGroup()
																																		.addPreferredGap(
																																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																		.addComponent(
																																				lblToggle,
																																				javax.swing.GroupLayout.PREFERRED_SIZE,
																																				1,
																																				Short.MAX_VALUE))))
																						.addComponent(
																								lblEmailErrorLog,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE))
																		.addGap(10,
																				10,
																				10)))));
		pnlEmailLayout
				.setVerticalGroup(pnlEmailLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								pnlEmailLayout
										.createSequentialGroup()
										.addGap(10, 10, 10)
										.addGroup(
												pnlEmailLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addGroup(
																pnlEmailLayout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																		.addComponent(
																				recipients,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addComponent(
																				lblRecipients,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addComponent(
																toggle,
																javax.swing.GroupLayout.Alignment.LEADING,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(5, 5, 5)
										.addGroup(
												pnlEmailLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																subject,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																txtSubject,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblToggle,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												msgScroll,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												248,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												lblSendEmail,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(5, 5, 5)
										.addComponent(
												lblEmailErrorLog,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(10, 10, 10)));

		pnlAddDeliver.setBackground(new java.awt.Color(255, 255, 255));
		pnlAddDeliver.setDoubleBuffered(false);
		pnlAddDeliver.setPreferredSize(new java.awt.Dimension(350, 250));
		pnlAddDeliver.setVerifyInputWhenFocusTarget(false);

		lblDeliverName.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
		lblDeliverName.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		lblDeliverName.setText("Deliverable Name:");
		lblDeliverName.setToolTipText("");
		lblDeliverName.setPreferredSize(new java.awt.Dimension(140, 30));

		txtDeliverName.setFont(new java.awt.Font("Helvetica", 0, 13)); // NOI18N
		txtDeliverName.setForeground(new java.awt.Color(204, 204, 204));
		txtDeliverName.setText("ex. Group Project");
		txtDeliverName.setPreferredSize(new java.awt.Dimension(185, 30));
		txtDeliverName.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				txtDeliverNameFocusGained(evt);
			}

			public void focusLost(java.awt.event.FocusEvent evt) {
				txtDeliverNameFocusLost(evt);
			}
		});

		txtDeliverWeight.setFont(new java.awt.Font("Helvetica", 0, 13)); // NOI18N
		txtDeliverWeight.setForeground(new java.awt.Color(204, 204, 204));
		txtDeliverWeight.setText("ex. 54");
		txtDeliverWeight.setPreferredSize(new java.awt.Dimension(185, 30));
		txtDeliverWeight.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				txtDeliverWeightFocusGained(evt);
			}

			public void focusLost(java.awt.event.FocusEvent evt) {
				txtDeliverWeightFocusLost(evt);
			}
		});

		lblDeliverType.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
		lblDeliverType.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		lblDeliverType.setText("Deliverable Type:");
		lblDeliverType.setToolTipText("");
		lblDeliverType.setPreferredSize(new java.awt.Dimension(140, 30));

		lblDeliverWeight.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
		lblDeliverWeight
				.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		lblDeliverWeight.setText("Deliverable Weight:");
		lblDeliverWeight.setToolTipText("");
		lblDeliverWeight.setPreferredSize(new java.awt.Dimension(140, 30));

		lblAddDeliverTitle.setFont(new java.awt.Font("Helvetica", 0, 16)); // NOI18N
		lblAddDeliverTitle
				.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblAddDeliverTitle.setText("Add a New Deliverable");
		lblAddDeliverTitle.setPreferredSize(new java.awt.Dimension(330, 30));

		lblAddDeliver.setBackground(new java.awt.Color(255, 255, 255));
		lblAddDeliver.setFont(new java.awt.Font("Helvetica", 0, 16)); // NOI18N
		lblAddDeliver.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblAddDeliver.setText("Add");
		lblAddDeliver
				.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		lblAddDeliver.setOpaque(true);
		lblAddDeliver.setPreferredSize(new java.awt.Dimension(175, 30));
		lblAddDeliver.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblAddDeliverMouseClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				lblAddDeliverMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				lblAddDeliverMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				lblAddDeliverMousePressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				lblAddDeliverMouseReleased(evt);
			}
		});

		comboDeliverType.setFont(new java.awt.Font("Helvetica", 0, 13)); // NOI18N
		comboDeliverType.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "Assignment", "Exam", "Other" }));
		comboDeliverType.setPreferredSize(new java.awt.Dimension(185, 30));

		lblCancelDeliverAddition
				.setBackground(new java.awt.Color(255, 255, 255));
		lblCancelDeliverAddition.setFont(new java.awt.Font("Helvetica", 0, 16)); // NOI18N
		lblCancelDeliverAddition
				.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblCancelDeliverAddition.setText("Cancel");
		lblCancelDeliverAddition.setCursor(new java.awt.Cursor(
				java.awt.Cursor.HAND_CURSOR));
		lblCancelDeliverAddition.setOpaque(true);
		lblCancelDeliverAddition.setPreferredSize(new java.awt.Dimension(175,
				30));
		lblCancelDeliverAddition
				.addMouseListener(new java.awt.event.MouseAdapter() {
					public void mouseClicked(java.awt.event.MouseEvent evt) {
						lblCancelDeliverAdditionMouseClicked(evt);
					}

					public void mouseEntered(java.awt.event.MouseEvent evt) {
						lblCancelDeliverAdditionMouseEntered(evt);
					}

					public void mouseExited(java.awt.event.MouseEvent evt) {
						lblCancelDeliverAdditionMouseExited(evt);
					}

					public void mousePressed(java.awt.event.MouseEvent evt) {
						lblCancelDeliverAdditionMousePressed(evt);
					}

					public void mouseReleased(java.awt.event.MouseEvent evt) {
						lblCancelDeliverAdditionMouseReleased(evt);
					}
				});

		addDeliverErrorLog.setPreferredSize(new java.awt.Dimension(330, 30));

		javax.swing.GroupLayout pnlAddDeliverLayout = new javax.swing.GroupLayout(
				pnlAddDeliver);
		pnlAddDeliver.setLayout(pnlAddDeliverLayout);
		pnlAddDeliverLayout
				.setHorizontalGroup(pnlAddDeliverLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								pnlAddDeliverLayout
										.createSequentialGroup()
										.addGroup(
												pnlAddDeliverLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																false)
														.addComponent(
																lblAddDeliverTitle,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGroup(
																pnlAddDeliverLayout
																		.createSequentialGroup()
																		.addComponent(
																				lblDeliverType,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addGap(5,
																				5,
																				5)
																		.addComponent(
																				comboDeliverType,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(
																pnlAddDeliverLayout
																		.createSequentialGroup()
																		.addComponent(
																				lblDeliverName,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addGap(5,
																				5,
																				5)
																		.addComponent(
																				txtDeliverName,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(
																pnlAddDeliverLayout
																		.createSequentialGroup()
																		.addComponent(
																				lblDeliverWeight,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addGap(5,
																				5,
																				5)
																		.addComponent(
																				txtDeliverWeight,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addGap(10, 10, 10))
						.addGroup(
								pnlAddDeliverLayout
										.createSequentialGroup()
										.addComponent(
												lblAddDeliver,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(0, 0, 0)
										.addComponent(
												lblCancelDeliverAddition,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGroup(
								pnlAddDeliverLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												addDeliverErrorLog,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addContainerGap()));
		pnlAddDeliverLayout
				.setVerticalGroup(pnlAddDeliverLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								pnlAddDeliverLayout
										.createSequentialGroup()
										.addGap(10, 10, 10)
										.addComponent(
												lblAddDeliverTitle,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(15, 15, 15)
										.addGroup(
												pnlAddDeliverLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																txtDeliverName,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblDeliverName,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(10, 10, 10)
										.addGroup(
												pnlAddDeliverLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																txtDeliverWeight,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblDeliverWeight,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(10, 10, 10)
										.addGroup(
												pnlAddDeliverLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																comboDeliverType,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblDeliverType,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addGap(5, 5, 5)
										.addComponent(
												addDeliverErrorLog,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(0, 0, 0)
										.addGroup(
												pnlAddDeliverLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																lblAddDeliver,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblCancelDeliverAddition,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))));

		pnlAddCourse.setBackground(new java.awt.Color(255, 255, 255));
		pnlAddCourse.setPreferredSize(new java.awt.Dimension(350, 250));

		pnlAddCourseContainer.setBackground(new java.awt.Color(255, 255, 255));
		pnlAddCourseContainer
				.setPreferredSize(new java.awt.Dimension(340, 225));

		lblAddCourseTitle.setFont(new java.awt.Font("Helvetica", 0, 16)); // NOI18N
		lblAddCourseTitle
				.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblAddCourseTitle.setText("Add a New Course");
		lblAddCourseTitle.setPreferredSize(new java.awt.Dimension(150, 22));

		lblCourseName.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
		lblCourseName.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		lblCourseName.setText("Course Title:");
		lblCourseName.setToolTipText("");
		lblCourseName.setPreferredSize(new java.awt.Dimension(150, 30));

		txtCourseName.setForeground(new java.awt.Color(204, 204, 204));
		txtCourseName.setText("ex. Computer Engineering");
		txtCourseName.setPreferredSize(new java.awt.Dimension(150, 30));
		txtCourseName.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				txtCourseNameFocusGained(evt);
			}

			public void focusLost(java.awt.event.FocusEvent evt) {
				txtCourseNameFocusLost(evt);
			}
		});

		lblCourseCode.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
		lblCourseCode.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		lblCourseCode.setText("Course Code:");
		lblCourseCode.setToolTipText("");
		lblCourseCode.setPreferredSize(new java.awt.Dimension(150, 30));

		txtCourseCode.setForeground(new java.awt.Color(204, 204, 204));
		txtCourseCode.setText("ex. CS2212");
		txtCourseCode.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				txtCourseCodeFocusGained(evt);
			}

			public void focusLost(java.awt.event.FocusEvent evt) {
				txtCourseCodeFocusLost(evt);
			}
		});

		lblCourseTerm.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
		lblCourseTerm.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		lblCourseTerm.setText("Course Term:");
		lblCourseTerm.setToolTipText("");

		comboCourseTerm.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "", "A", "B", "E", "F", "G", "R", "S", "T", "W",
						"X", "Y", "Z" }));
		comboCourseTerm.setPreferredSize(new java.awt.Dimension(80, 30));

		lblCourseAddErrorLog.setForeground(new java.awt.Color(255, 0, 0));

		lblAddCourse.setBackground(new java.awt.Color(255, 255, 255));
		lblAddCourse.setFont(new java.awt.Font("Helvetica", 0, 16)); // NOI18N
		lblAddCourse.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblAddCourse.setText("Add");
		lblAddCourse
				.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		lblAddCourse.setOpaque(true);
		lblAddCourse.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblAddCourseMouseClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				lblAddCourseMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				lblAddCourseMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				lblAddCourseMousePressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				lblAddCourseMouseReleased(evt);
			}
		});

		lblCancelCourseAddition
				.setBackground(new java.awt.Color(255, 255, 255));
		lblCancelCourseAddition.setFont(new java.awt.Font("Helvetica", 0, 16)); // NOI18N
		lblCancelCourseAddition
				.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblCancelCourseAddition.setText("Cancel");
		lblCancelCourseAddition.setCursor(new java.awt.Cursor(
				java.awt.Cursor.HAND_CURSOR));
		lblCancelCourseAddition.setOpaque(true);
		lblCancelCourseAddition
				.addMouseListener(new java.awt.event.MouseAdapter() {
					public void mouseClicked(java.awt.event.MouseEvent evt) {
						lblCancelCourseAdditionMouseClicked(evt);
					}

					public void mouseEntered(java.awt.event.MouseEvent evt) {
						lblCancelCourseAdditionMouseEntered(evt);
					}

					public void mouseExited(java.awt.event.MouseEvent evt) {
						lblCancelCourseAdditionMouseExited(evt);
					}

					public void mousePressed(java.awt.event.MouseEvent evt) {
						lblCancelCourseAdditionMousePressed(evt);
					}

					public void mouseReleased(java.awt.event.MouseEvent evt) {
						lblCancelCourseAdditionMouseReleased(evt);
					}
				});

		javax.swing.GroupLayout pnlAddCourseContainerLayout = new javax.swing.GroupLayout(
				pnlAddCourseContainer);
		pnlAddCourseContainer.setLayout(pnlAddCourseContainerLayout);
		pnlAddCourseContainerLayout
				.setHorizontalGroup(pnlAddCourseContainerLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								pnlAddCourseContainerLayout
										.createSequentialGroup()
										.addComponent(
												lblAddCourse,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												165,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(0, 0, 0)
										.addComponent(
												lblCancelCourseAddition,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												185, Short.MAX_VALUE))
						.addGroup(
								pnlAddCourseContainerLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												pnlAddCourseContainerLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																lblAddCourseTitle,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																lblCourseAddErrorLog,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addGroup(
																pnlAddCourseContainerLayout
																		.createSequentialGroup()
																		.addGroup(
																				pnlAddCourseContainerLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING,
																								false)
																						.addComponent(
																								lblCourseName,
																								javax.swing.GroupLayout.Alignment.LEADING,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								1,
																								Short.MAX_VALUE)
																						.addComponent(
																								lblCourseCode,
																								javax.swing.GroupLayout.Alignment.LEADING,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								1,
																								Short.MAX_VALUE)
																						.addComponent(
																								lblCourseTerm,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								120,
																								Short.MAX_VALUE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addGroup(
																				pnlAddCourseContainerLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								txtCourseCode)
																						.addComponent(
																								txtCourseName,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addGroup(
																								pnlAddCourseContainerLayout
																										.createSequentialGroup()
																										.addComponent(
																												comboCourseTerm,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												50,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addGap(0,
																												0,
																												Short.MAX_VALUE)))))
										.addContainerGap()));
		pnlAddCourseContainerLayout
				.setVerticalGroup(pnlAddCourseContainerLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								pnlAddCourseContainerLayout
										.createSequentialGroup()
										.addGap(5, 5, 5)
										.addComponent(
												lblAddCourseTitle,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												35,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(20, 20, 20)
										.addGroup(
												pnlAddCourseContainerLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																txtCourseName,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblCourseName,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(5, 5, 5)
										.addGroup(
												pnlAddCourseContainerLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																txtCourseCode,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																28,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblCourseCode,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addGap(5, 5, 5)
										.addGroup(
												pnlAddCourseContainerLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																lblCourseTerm,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																30,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGroup(
																pnlAddCourseContainerLayout
																		.createSequentialGroup()
																		.addGap(1,
																				1,
																				1)
																		.addComponent(
																				comboCourseTerm,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addGap(10, 10, 10)
										.addComponent(
												lblCourseAddErrorLog,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												27,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(5, 5, 5)
										.addGroup(
												pnlAddCourseContainerLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																lblAddCourse,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																30,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblCancelCourseAddition,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																30,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(0, 0, 0)));

		javax.swing.GroupLayout pnlAddCourseLayout = new javax.swing.GroupLayout(
				pnlAddCourse);
		pnlAddCourse.setLayout(pnlAddCourseLayout);
		pnlAddCourseLayout.setHorizontalGroup(pnlAddCourseLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						pnlAddCourseLayout
								.createSequentialGroup()
								.addGap(0, 0, 0)
								.addComponent(pnlAddCourseContainer,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										350, Short.MAX_VALUE).addGap(0, 0, 0)));
		pnlAddCourseLayout.setVerticalGroup(pnlAddCourseLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						pnlAddCourseLayout
								.createSequentialGroup()
								.addGap(0, 0, 0)
								.addComponent(pnlAddCourseContainer,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										250, Short.MAX_VALUE).addGap(0, 0, 0)));

		pnlCalc.setBackground(new java.awt.Color(255, 255, 255));
		pnlCalc.setPreferredSize(new java.awt.Dimension(500, 180));

		lblCalcTitle.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		lblCalcTitle.setText("Student Average Calculator");
		lblCalcTitle.setPreferredSize(new java.awt.Dimension(280, 20));

		lblEnterAvg.setText("Please enter the desired average(0-100):");
		lblEnterAvg.setMaximumSize(new java.awt.Dimension(280, 30));
		lblEnterAvg.setPreferredSize(new java.awt.Dimension(280, 20));

		txtAvg.setForeground(new java.awt.Color(204, 204, 204));
		txtAvg.setText("Please enter a number between 0-100");
		txtAvg.setPreferredSize(new java.awt.Dimension(280, 30));
		txtAvg.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				txtAvgFocusGained(evt);
			}

			public void focusLost(java.awt.event.FocusEvent evt) {
				txtAvgFocusLost(evt);
			}
		});

		lblCalculate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblCalculate.setText("Calculate");
		lblCalculate.setBorder(javax.swing.BorderFactory
				.createLineBorder(java.awt.Color.lightGray));
		lblCalculate.setFocusTraversalPolicyProvider(true);
		lblCalculate.setPreferredSize(new java.awt.Dimension(280, 30));
		lblCalculate.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblCalculateMouseClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				lblCalculateMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				lblCalculateMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				lblCalculateMousePressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				lblCalculateMouseReleased(evt);
			}
		});

		lblResult.setBorder(null);
		lblResult.setPreferredSize(new java.awt.Dimension(480, 50));
		resultScroll.setViewportView(lblResult);
		lblResult.disable();

		javax.swing.GroupLayout pnlCalcLayout = new javax.swing.GroupLayout(
				pnlCalc);
		pnlCalc.setLayout(pnlCalcLayout);
		pnlCalcLayout
				.setHorizontalGroup(pnlCalcLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								pnlCalcLayout
										.createSequentialGroup()
										.addGap(10, 10, 10)
										.addGroup(
												pnlCalcLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																pnlCalcLayout
																		.createSequentialGroup()
																		.addComponent(
																				txtAvg,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				480,
																				Short.MAX_VALUE)
																		.addContainerGap())
														.addComponent(
																lblCalcTitle,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																lblEnterAvg,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)))
						.addGroup(
								pnlCalcLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												pnlCalcLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																resultScroll)
														.addComponent(
																lblCalculate,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addContainerGap()));
		pnlCalcLayout.setVerticalGroup(pnlCalcLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				pnlCalcLayout
						.createSequentialGroup()
						.addGap(10, 10, 10)
						.addComponent(lblCalcTitle,
								javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(10, 10, 10)
						.addComponent(lblEnterAvg,
								javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, 0)
						.addComponent(txtAvg,
								javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(10, 10, 10)
						.addComponent(lblCalculate,
								javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(10, 10, 10)
						.addComponent(resultScroll,
								javax.swing.GroupLayout.DEFAULT_SIZE, 49,
								Short.MAX_VALUE).addContainerGap()));

		pnlCustom.setBackground(new java.awt.Color(255, 255, 255));
		pnlCustom.setPreferredSize(new java.awt.Dimension(520, 260));

		lblHostName1.setText("Host Name:");
		lblHostName1.setMinimumSize(new java.awt.Dimension(100, 30));
		lblHostName1.setPreferredSize(new java.awt.Dimension(100, 30));

		lblHostName3.setText("Port:");
		lblHostName3.setMinimumSize(new java.awt.Dimension(100, 30));
		lblHostName3.setPreferredSize(new java.awt.Dimension(100, 30));

		txtHostName.setForeground(new java.awt.Color(204, 204, 204));
		txtHostName.setHorizontalAlignment(javax.swing.JTextField.LEFT);
		txtHostName.setText("Please enter the host name...");
		txtHostName.setPreferredSize(new java.awt.Dimension(400, 30));
		txtHostName.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				txtHostNameFocusGained(evt);
			}

			public void focusLost(java.awt.event.FocusEvent evt) {
				txtHostNameFocusLost(evt);
			}
		});

		txtCustomPassword.setText("sickcoderoverhere");
		txtCustomPassword.setPreferredSize(new java.awt.Dimension(400, 30));
		txtCustomPassword.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				txtCustomPasswordFocusGained(evt);
			}
		});

		lblPassword1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
		lblPassword1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		lblPassword1.setText("Password:");
		lblPassword1.setPreferredSize(new java.awt.Dimension(100, 30));

		lblEmailAddress1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
		lblEmailAddress1
				.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		lblEmailAddress1.setText("Email Address:");
		lblEmailAddress1.setPreferredSize(new java.awt.Dimension(100, 30));

		txtCustomEmail.setForeground(new java.awt.Color(204, 204, 204));
		txtCustomEmail.setHorizontalAlignment(javax.swing.JTextField.LEFT);
		txtCustomEmail.setText("Please enter your email address...");
		txtCustomEmail.setPreferredSize(new java.awt.Dimension(400, 30));
		txtCustomEmail.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				txtCustomEmailFocusGained(evt);
			}

			public void focusLost(java.awt.event.FocusEvent evt) {
				txtCustomEmailFocusLost(evt);
			}
		});

		txtPort.setForeground(new java.awt.Color(204, 204, 204));
		txtPort.setHorizontalAlignment(javax.swing.JTextField.LEFT);
		txtPort.setText("Please enter a port...");
		txtPort.setPreferredSize(new java.awt.Dimension(400, 30));
		txtPort.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				txtPortFocusGained(evt);
			}

			public void focusLost(java.awt.event.FocusEvent evt) {
				txtPortFocusLost(evt);
			}
		});

		lblCutomTitle.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		lblCutomTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblCutomTitle.setText("Custom SMTP");
		lblCutomTitle.setPreferredSize(new java.awt.Dimension(500, 30));

		lblCustomSubmit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		lblCustomSubmit
				.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblCustomSubmit.setText("Submit");
		lblCustomSubmit.setBorder(javax.swing.BorderFactory
				.createLineBorder(new java.awt.Color(204, 204, 204)));
		lblCustomSubmit.setCursor(new java.awt.Cursor(
				java.awt.Cursor.HAND_CURSOR));
		lblCustomSubmit.setPreferredSize(new java.awt.Dimension(500, 30));
		lblCustomSubmit.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblCustomSubmitMouseClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				lblCustomSubmitMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				lblCustomSubmitMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				lblCustomSubmitMousePressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				lblCustomSubmitMouseReleased(evt);
			}
		});

		lblSMTPError.setPreferredSize(new java.awt.Dimension(500, 20));

		javax.swing.GroupLayout pnlCustomLayout = new javax.swing.GroupLayout(
				pnlCustom);
		pnlCustom.setLayout(pnlCustomLayout);
		pnlCustomLayout
				.setHorizontalGroup(pnlCustomLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								pnlCustomLayout
										.createSequentialGroup()
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(
												lblCustomSubmit,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(10, 10, 10))
						.addGroup(
								pnlCustomLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												pnlCustomLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																pnlCustomLayout
																		.createSequentialGroup()
																		.addGroup(
																				pnlCustomLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING)
																						.addGroup(
																								pnlCustomLayout
																										.createSequentialGroup()
																										.addGroup(
																												pnlCustomLayout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING,
																																false)
																														.addGroup(
																																javax.swing.GroupLayout.Alignment.TRAILING,
																																pnlCustomLayout
																																		.createSequentialGroup()
																																		.addComponent(
																																				lblPassword1,
																																				javax.swing.GroupLayout.PREFERRED_SIZE,
																																				javax.swing.GroupLayout.DEFAULT_SIZE,
																																				javax.swing.GroupLayout.PREFERRED_SIZE)
																																		.addGap(0,
																																				0,
																																				0)
																																		.addComponent(
																																				txtCustomPassword,
																																				javax.swing.GroupLayout.PREFERRED_SIZE,
																																				javax.swing.GroupLayout.DEFAULT_SIZE,
																																				javax.swing.GroupLayout.PREFERRED_SIZE))
																														.addGroup(
																																pnlCustomLayout
																																		.createSequentialGroup()
																																		.addComponent(
																																				lblHostName3,
																																				javax.swing.GroupLayout.PREFERRED_SIZE,
																																				javax.swing.GroupLayout.DEFAULT_SIZE,
																																				javax.swing.GroupLayout.PREFERRED_SIZE)
																																		.addGap(0,
																																				0,
																																				0)
																																		.addComponent(
																																				txtPort,
																																				javax.swing.GroupLayout.PREFERRED_SIZE,
																																				javax.swing.GroupLayout.DEFAULT_SIZE,
																																				javax.swing.GroupLayout.PREFERRED_SIZE))
																														.addGroup(
																																pnlCustomLayout
																																		.createSequentialGroup()
																																		.addComponent(
																																				lblHostName1,
																																				javax.swing.GroupLayout.PREFERRED_SIZE,
																																				javax.swing.GroupLayout.DEFAULT_SIZE,
																																				javax.swing.GroupLayout.PREFERRED_SIZE)
																																		.addGap(0,
																																				0,
																																				0)
																																		.addComponent(
																																				txtHostName,
																																				javax.swing.GroupLayout.PREFERRED_SIZE,
																																				javax.swing.GroupLayout.DEFAULT_SIZE,
																																				javax.swing.GroupLayout.PREFERRED_SIZE)))
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED))
																						.addGroup(
																								pnlCustomLayout
																										.createSequentialGroup()
																										.addComponent(
																												lblEmailAddress1,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addGap(0,
																												0,
																												0)
																										.addComponent(
																												txtCustomEmail,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
																		.addContainerGap(
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE))
														.addGroup(
																pnlCustomLayout
																		.createSequentialGroup()
																		.addGroup(
																				pnlCustomLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								lblCutomTitle,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								lblSMTPError,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE))
																		.addContainerGap()))));
		pnlCustomLayout
				.setVerticalGroup(pnlCustomLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								pnlCustomLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												lblCutomTitle,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(10, 10, 10)
										.addGroup(
												pnlCustomLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																lblHostName1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																txtHostName,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(5, 5, 5)
										.addGroup(
												pnlCustomLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																lblHostName3,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																txtPort,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(5, 5, 5)
										.addGroup(
												pnlCustomLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																lblEmailAddress1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																txtCustomEmail,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(5, 5, 5)
										.addGroup(
												pnlCustomLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																txtCustomPassword,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblPassword1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(10, 10, 10)
										.addComponent(
												lblCustomSubmit,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(5, 5, 5)
										.addComponent(
												lblSMTPError,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(15, 15, 15)));

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Gradebook Application");
		setBackground(new java.awt.Color(51, 105, 232));
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setLocationByPlatform(true);
		setName("Gradebook"); // NOI18N
		setUndecorated(true);
		setResizable(false);
		addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
			public void mouseDragged(java.awt.event.MouseEvent evt) {
				formMouseDragged(evt);
			}
		});
		addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				formMouseClicked(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				formMousePressed(evt);
			}
		});
		getContentPane().setLayout(
				new org.netbeans.lib.awtextra.AbsoluteLayout());

		lblGrades.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblGrades.setText("Grades");
		lblGrades.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		lblGrades.setPreferredSize(new java.awt.Dimension(125, 40));
		getContentPane().add(
				lblGrades,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1,
						-1));

		lblTabGrades.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/cs2212/team4/tabOn.png"))); // NOI18N
		lblTabGrades
				.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		lblTabGrades.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblTabGradesMouseClicked(evt);
			}
		});
		getContentPane().add(
				lblTabGrades,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1,
						-1));

		lblSetup.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblSetup.setText("Course Setup");
		lblSetup.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		lblSetup.setPreferredSize(new java.awt.Dimension(125, 40));
		getContentPane().add(
				lblSetup,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 60, 125,
						40));

		lblTabSetup.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/cs2212/team4/tabOff.png"))); // NOI18N
		lblTabSetup.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		lblTabSetup.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblTabSetupMouseClicked(evt);
			}
		});
		getContentPane().add(
				lblTabSetup,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 60, -1,
						-1));

		lblDropbox.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblDropbox.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/cs2212/team4/dropbox.png"))); // NOI18N
		lblDropbox.setText("Settings");
		lblDropbox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		lblDropbox.setPreferredSize(new java.awt.Dimension(125, 40));
		lblDropbox.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblDropboxMouseClicked(evt);
			}
		});
		getContentPane().add(
				lblDropbox,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 60, 125,
						40));

		lblTabDropbox.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/cs2212/team4/tabOff.png"))); // NOI18N
		lblTabDropbox
				.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		lblTabDropbox.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblTabDropboxMouseClicked(evt);
			}
		});
		getContentPane().add(
				lblTabDropbox,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 60, -1,
						-1));

		lblMini.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/cs2212/team4/miniOff.png"))); // NOI18N
		lblMini.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		lblMini.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblMiniMouseClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				lblMiniMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				lblMiniMouseExited(evt);
			}
		});
		getContentPane().add(
				lblMini,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 0, -1,
						-1));

		lblExit.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/cs2212/team4/exitOff.png"))); // NOI18N
		lblExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		lblExit.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblExitMouseClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				lblExitMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				lblExitMouseExited(evt);
			}
		});
		getContentPane().add(
				lblExit,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(1074, 0, -1,
						-1));

		myCourses.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/cs2212/team4/myCourses.png"))); // NOI18N
		myCourses.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		myCourses.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				myCoursesMouseClicked(evt);
			}
		});
		getContentPane().add(
				myCourses,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 50, -1,
						-1));

		pnlCourseMenu.setBackground(new java.awt.Color(255, 255, 255));
		pnlCourseMenu.setBorder(javax.swing.BorderFactory
				.createLineBorder(new java.awt.Color(204, 204, 204)));

		courses.setBorder(null);

		courseMenuList.setBorder(javax.swing.BorderFactory
				.createLineBorder(new java.awt.Color(255, 255, 255)));
		courseMenuList
				.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		courseMenuList
				.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
					public void valueChanged(
							javax.swing.event.ListSelectionEvent evt) {
						courseMenuListValueChanged(evt);
					}
				});
		courses.setViewportView(courseMenuList);

		addCourse.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		addCourse.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/cs2212/team4/addCourse.png"))); // NOI18N
		addCourse.setText("Add Course");
		addCourse.setBorder(javax.swing.BorderFactory
				.createLineBorder(java.awt.Color.lightGray));
		addCourse.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		addCourse.setPreferredSize(new java.awt.Dimension(110, 30));
		addCourse.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				addCourseMouseClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				addCourseMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				addCourseMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				addCourseMousePressed(evt);
			}
		});

		javax.swing.GroupLayout pnlCourseMenuLayout = new javax.swing.GroupLayout(
				pnlCourseMenu);
		pnlCourseMenu.setLayout(pnlCourseMenuLayout);
		pnlCourseMenuLayout
				.setHorizontalGroup(pnlCourseMenuLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								pnlCourseMenuLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												addCourse,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												228, Short.MAX_VALUE)
										.addContainerGap())
						.addGroup(
								pnlCourseMenuLayout
										.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(
												courses,
												javax.swing.GroupLayout.Alignment.TRAILING,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												248, Short.MAX_VALUE)));
		pnlCourseMenuLayout
				.setVerticalGroup(pnlCourseMenuLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								pnlCourseMenuLayout
										.createSequentialGroup()
										.addContainerGap(268, Short.MAX_VALUE)
										.addComponent(
												addCourse,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(10, 10, 10))
						.addGroup(
								pnlCourseMenuLayout
										.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(
												pnlCourseMenuLayout
														.createSequentialGroup()
														.addComponent(
																courses,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																251,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGap(0, 57,
																Short.MAX_VALUE))));

		getContentPane().add(
				pnlCourseMenu,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 100,
						250, 310));

		container.setBackground(new java.awt.Color(255, 255, 255));
		container.setPreferredSize(new java.awt.Dimension(1080, 490));

		tabGrades.setBackground(new java.awt.Color(255, 255, 255));
		tabGrades.setPreferredSize(new java.awt.Dimension(1080, 480));

		deliverList
				.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		deliverList.setPreferredSize(new java.awt.Dimension(35, 80));
		deliverList.setVisibleRowCount(10);
		deliverList
				.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
					public void valueChanged(
							javax.swing.event.ListSelectionEvent evt) {
						deliverListValueChanged(evt);
					}
				});
		deliversScroll.setViewportView(deliverList);

		courseName.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
		courseName.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/cs2212/team4/course.png"))); // NOI18N
		courseName.setText("Select a course");
		courseName.setPreferredSize(new java.awt.Dimension(200, 24));

		editCourseIcon
				.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		editCourseIcon.setIcon(new javax.swing.ImageIcon(getClass()
				.getResource("/cs2212/team4/settings.png"))); // NOI18N
		editCourseIcon.setCursor(new java.awt.Cursor(
				java.awt.Cursor.HAND_CURSOR));
		editCourseIcon.setPreferredSize(new java.awt.Dimension(40, 40));
		editCourseIcon.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				editCourseIconMouseClicked(evt);
			}
		});

		addDeliver.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		addDeliver.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/cs2212/team4/addDeliver.png"))); // NOI18N
		addDeliver.setText("Add Deliverable");
		addDeliver.setBorder(javax.swing.BorderFactory
				.createLineBorder(java.awt.Color.lightGray));
		addDeliver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		addDeliver.setMaximumSize(new java.awt.Dimension(95, 40));
		addDeliver.setMinimumSize(new java.awt.Dimension(95, 40));
		addDeliver.setPreferredSize(new java.awt.Dimension(110, 30));
		addDeliver.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				addDeliverMouseClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				addDeliverMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				addDeliverMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				addDeliverMousePressed(evt);
			}
		});

		addStudent.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		addStudent.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/cs2212/team4/addStudent.png"))); // NOI18N
		addStudent.setText("Add Student");
		addStudent.setBorder(javax.swing.BorderFactory
				.createLineBorder(java.awt.Color.lightGray));
		addStudent.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		addStudent.setPreferredSize(new java.awt.Dimension(130, 30));
		addStudent.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				addStudentMouseClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				addStudentMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				addStudentMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				addStudentMousePressed(evt);
			}
		});

		pnlTables.setBorder(javax.swing.BorderFactory
				.createLineBorder(new java.awt.Color(204, 204, 204)));
		pnlTables.setPreferredSize(new java.awt.Dimension(820, 350));

		studentScroll.setBorder(null);
		studentScroll
				.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		studentTable.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] { { null, null, null, null, null },
						{ null, null, null, null, null },
						{ null, null, null, null, null },
						{ null, null, null, null, null } }, new String[] { "",
						"First Name", "Last Name", "Number", "Email" }) {
			Class[] types = new Class[] { java.lang.Boolean.class,
					java.lang.String.class, java.lang.String.class,
					java.lang.String.class, java.lang.String.class };

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}
		});
		studentTable
				.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
		studentTable.setOpaque(false);
		studentTable
				.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		studentTable.getTableHeader().setReorderingAllowed(false);
		studentTable.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				studentTableMouseClicked(evt);
			}
		});
		studentScroll.setViewportView(studentTable);

		gradesScroll.setBorder(null);
		gradesScroll
				.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		gradesTable.setToolTipText("");
		gradesTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
		gradesTable.setOpaque(false);
		gradesTable
				.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		gradesTable.getTableHeader().setReorderingAllowed(false);
		gradesTable.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				gradesTableMouseClicked(evt);
			}
		});
		gradesScroll.setViewportView(gradesTable);

		javax.swing.GroupLayout pnlTablesLayout = new javax.swing.GroupLayout(
				pnlTables);
		pnlTables.setLayout(pnlTablesLayout);
		pnlTablesLayout.setHorizontalGroup(pnlTablesLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				pnlTablesLayout
						.createSequentialGroup()
						.addGap(0, 0, 0)
						.addComponent(studentScroll,
								javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, 0)
						.addComponent(gradesScroll,
								javax.swing.GroupLayout.DEFAULT_SIZE, 366,
								Short.MAX_VALUE)));
		pnlTablesLayout.setVerticalGroup(pnlTablesLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(studentScroll,
						javax.swing.GroupLayout.PREFERRED_SIZE, 0,
						Short.MAX_VALUE)
				.addComponent(gradesScroll,
						javax.swing.GroupLayout.DEFAULT_SIZE, 348,
						Short.MAX_VALUE));

		deleteDeliver.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		deleteDeliver.setText("Delete Deliverable");
		deleteDeliver.setBorder(javax.swing.BorderFactory
				.createLineBorder(java.awt.Color.lightGray));
		deleteDeliver
				.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		deleteDeliver.setPreferredSize(new java.awt.Dimension(110, 30));
		deleteDeliver.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				deleteDeliverMouseClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				deleteDeliverMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				deleteDeliverMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				deleteDeliverMousePressed(evt);
			}
		});

		deleteStudent.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		deleteStudent.setText("Delete Student");
		deleteStudent.setBorder(javax.swing.BorderFactory
				.createLineBorder(java.awt.Color.lightGray));
		deleteStudent
				.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		deleteStudent.setPreferredSize(new java.awt.Dimension(130, 30));
		deleteStudent.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				deleteStudentMouseClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				deleteStudentMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				deleteStudentMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				deleteStudentMousePressed(evt);
			}
		});

		lblExportStudents
				.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblExportStudents.setText("Export Students");
		lblExportStudents.setBorder(javax.swing.BorderFactory
				.createLineBorder(java.awt.Color.lightGray));
		lblExportStudents.setCursor(new java.awt.Cursor(
				java.awt.Cursor.HAND_CURSOR));
		lblExportStudents.setPreferredSize(new java.awt.Dimension(130, 30));
		lblExportStudents.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblExportStudentsMouseClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				lblExportStudentsMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				lblExportStudentsMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				lblExportStudentsMousePressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				lblExportStudentsMouseReleased(evt);
			}
		});

		lblImportStudents
				.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblImportStudents.setText("Import Students");
		lblImportStudents.setBorder(javax.swing.BorderFactory
				.createLineBorder(java.awt.Color.lightGray));
		lblImportStudents.setCursor(new java.awt.Cursor(
				java.awt.Cursor.HAND_CURSOR));
		lblImportStudents.setPreferredSize(new java.awt.Dimension(130, 30));
		lblImportStudents.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblImportStudentsMouseClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				lblImportStudentsMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				lblImportStudentsMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				lblImportStudentsMousePressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				lblImportStudentsMouseReleased(evt);
			}
		});

		lblFirstName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblFirstName.setText("First Name");
		lblFirstName.setBorder(javax.swing.BorderFactory
				.createLineBorder(java.awt.Color.lightGray));
		lblFirstName
				.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		lblFirstName.setPreferredSize(new java.awt.Dimension(80, 30));
		lblFirstName.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				lblFirstNameMousePressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				lblFirstNameMouseReleased(evt);
			}

			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblFirstNameMouseClicked(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				lblFirstNameMouseExited(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				lblFirstNameMouseEntered(evt);
			}
		});

		lblLastName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblLastName.setText("Last Name");
		lblLastName.setBorder(javax.swing.BorderFactory
				.createLineBorder(java.awt.Color.lightGray));
		lblLastName.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		lblLastName.setPreferredSize(new java.awt.Dimension(80, 30));
		lblLastName.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				lblLastNameMousePressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				lblLastNameMouseReleased(evt);
			}

			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblLastNameMouseClicked(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				lblLastNameMouseExited(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				lblLastNameMouseEntered(evt);
			}
		});

		lblNumber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblNumber.setText("Number");
		lblNumber.setBorder(javax.swing.BorderFactory
				.createLineBorder(java.awt.Color.lightGray));
		lblNumber.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		lblNumber.setPreferredSize(new java.awt.Dimension(80, 30));
		lblNumber.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblNumberMouseClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				lblNumberMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				lblNumberMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				lblNumberMousePressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				lblNumberMouseReleased(evt);
			}
		});

		lblEmail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblEmail.setText("Email");
		lblEmail.setBorder(javax.swing.BorderFactory
				.createLineBorder(java.awt.Color.lightGray));
		lblEmail.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		lblEmail.setPreferredSize(new java.awt.Dimension(80, 30));
		lblEmail.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				lblEmailMousePressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				lblEmailMouseReleased(evt);
			}

			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblEmailMouseClicked(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				lblEmailMouseExited(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				lblEmailMouseEntered(evt);
			}
		});

		lblExmAvg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblExmAvg.setText("Exam Avg");
		lblExmAvg.setBorder(javax.swing.BorderFactory
				.createLineBorder(java.awt.Color.lightGray));
		lblExmAvg.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		lblExmAvg.setPreferredSize(new java.awt.Dimension(80, 30));
		lblExmAvg.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				lblExmAvgMousePressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				lblExmAvgMouseReleased(evt);
			}

			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblExmAvgMouseClicked(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				lblExmAvgMouseExited(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				lblExmAvgMouseEntered(evt);
			}
		});

		lblAsnAvg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblAsnAvg.setText("Asn Avg");
		lblAsnAvg.setBorder(javax.swing.BorderFactory
				.createLineBorder(java.awt.Color.lightGray));
		lblAsnAvg.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		lblAsnAvg.setPreferredSize(new java.awt.Dimension(80, 30));
		lblAsnAvg.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				lblAsnAvgMousePressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				lblAsnAvgMouseReleased(evt);
			}

			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblAsnAvgMouseClicked(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				lblAsnAvgMouseExited(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				lblAsnAvgMouseEntered(evt);
			}
		});

		lblImportGrades
				.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblImportGrades.setText("Import Grades");
		lblImportGrades.setBorder(javax.swing.BorderFactory
				.createLineBorder(java.awt.Color.lightGray));
		lblImportGrades.setCursor(new java.awt.Cursor(
				java.awt.Cursor.HAND_CURSOR));
		lblImportGrades.setPreferredSize(new java.awt.Dimension(135, 30));
		lblImportGrades.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblImportGradesMouseClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				lblImportGradesMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				lblImportGradesMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				lblImportGradesMousePressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				lblImportGradesMouseReleased(evt);
			}
		});

		lblExportGrades
				.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblExportGrades.setText("Export Grades");
		lblExportGrades.setBorder(javax.swing.BorderFactory
				.createLineBorder(java.awt.Color.lightGray));
		lblExportGrades.setCursor(new java.awt.Cursor(
				java.awt.Cursor.HAND_CURSOR));
		lblExportGrades.setPreferredSize(new java.awt.Dimension(135, 30));
		lblExportGrades.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblExportGradesMouseClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				lblExportGradesMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				lblExportGradesMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				lblExportGradesMousePressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				lblExportGradesMouseReleased(evt);
			}
		});

		lblCourseAvg.setFont(new java.awt.Font("Helvetica", 0, 30)); // NOI18N
		lblCourseAvg.setForeground(new java.awt.Color(70, 70, 70));
		lblCourseAvg.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		lblCourseAvg.setText("--%");
		lblCourseAvg.setPreferredSize(new java.awt.Dimension(160, 65));

		courseAsnAvg.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
		courseAsnAvg.setForeground(new java.awt.Color(70, 70, 70));
		courseAsnAvg.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		courseAsnAvg.setText("Class Asn Avg:");
		courseAsnAvg.setPreferredSize(new java.awt.Dimension(120, 20));

		courseExamAvg.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
		courseExamAvg.setForeground(new java.awt.Color(70, 70, 70));
		courseExamAvg.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		courseExamAvg.setText("Class Exam Avg:");
		courseExamAvg.setPreferredSize(new java.awt.Dimension(120, 20));

		lblCourseAsnAvg.setFont(new java.awt.Font("Helvetica", 0, 12)); // NOI18N
		lblCourseAsnAvg.setForeground(new java.awt.Color(70, 70, 70));
		lblCourseAsnAvg
				.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		lblCourseAsnAvg.setText("--%");
		lblCourseAsnAvg.setPreferredSize(new java.awt.Dimension(60, 20));

		lblCourseExamAvg.setFont(new java.awt.Font("Helvetica", 0, 12)); // NOI18N
		lblCourseExamAvg.setForeground(new java.awt.Color(70, 70, 70));
		lblCourseExamAvg
				.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		lblCourseExamAvg.setText("--%");
		lblCourseExamAvg.setPreferredSize(new java.awt.Dimension(60, 20));

		courseAvg.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
		courseAvg.setForeground(new java.awt.Color(70, 70, 70));
		courseAvg.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		courseAvg.setText("Class Average:");
		courseAvg.setPreferredSize(new java.awt.Dimension(120, 20));

		lblEmailStudents
				.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblEmailStudents.setText("Email Selected Students");
		lblEmailStudents.setBorder(javax.swing.BorderFactory
				.createLineBorder(java.awt.Color.lightGray));
		lblEmailStudents.setCursor(new java.awt.Cursor(
				java.awt.Cursor.HAND_CURSOR));
		lblEmailStudents.setPreferredSize(new java.awt.Dimension(80, 30));
		lblEmailStudents.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblEmailStudentsMouseClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				lblEmailStudentsMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				lblEmailStudentsMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				lblEmailStudentsMousePressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				lblEmailStudentsMouseReleased(evt);
			}
		});

		lblCalculator.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblCalculator.setText("Average Calculator");
		lblCalculator.setBorder(javax.swing.BorderFactory
				.createLineBorder(java.awt.Color.lightGray));
		lblCalculator
				.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		lblCalculator.setPreferredSize(new java.awt.Dimension(80, 30));
		lblCalculator.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblCalculatorMouseClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				lblCalculatorMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				lblCalculatorMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				lblCalculatorMousePressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				lblCalculatorMouseReleased(evt);
			}
		});

		lblGradesErrorLog.setPreferredSize(new java.awt.Dimension(1060, 14));

		javax.swing.GroupLayout tabGradesLayout = new javax.swing.GroupLayout(
				tabGrades);
		tabGrades.setLayout(tabGradesLayout);
		tabGradesLayout
				.setHorizontalGroup(tabGradesLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								tabGradesLayout
										.createSequentialGroup()
										.addGap(10, 10, 10)
										.addGroup(
												tabGradesLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																tabGradesLayout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.TRAILING,
																				false)
																		.addComponent(
																				addDeliver,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				deliversScroll,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				230,
																				Short.MAX_VALUE))
														.addGroup(
																tabGradesLayout
																		.createSequentialGroup()
																		.addComponent(
																				courseName,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				180,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				editCourseIcon,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addComponent(
																deleteDeliver,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																230,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(10, 10, 10)
										.addGroup(
												tabGradesLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																tabGradesLayout
																		.createSequentialGroup()
																		.addComponent(
																				addStudent,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addGap(6,
																				6,
																				6)
																		.addComponent(
																				deleteStudent,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				lblExportStudents,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addGap(6,
																				6,
																				6)
																		.addComponent(
																				lblImportStudents,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addGap(6,
																				6,
																				6)
																		.addComponent(
																				lblExportGrades,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addGap(6,
																				6,
																				6)
																		.addComponent(
																				lblImportGrades,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(
																tabGradesLayout
																		.createSequentialGroup()
																		.addGroup(
																				tabGradesLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								false)
																						.addGroup(
																								tabGradesLayout
																										.createSequentialGroup()
																										.addGroup(
																												tabGradesLayout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING,
																																false)
																														.addGroup(
																																tabGradesLayout
																																		.createSequentialGroup()
																																		.addComponent(
																																				lblFirstName,
																																				javax.swing.GroupLayout.PREFERRED_SIZE,
																																				javax.swing.GroupLayout.DEFAULT_SIZE,
																																				javax.swing.GroupLayout.PREFERRED_SIZE)
																																		.addGap(5,
																																				5,
																																				5)
																																		.addComponent(
																																				lblLastName,
																																				javax.swing.GroupLayout.PREFERRED_SIZE,
																																				javax.swing.GroupLayout.DEFAULT_SIZE,
																																				javax.swing.GroupLayout.PREFERRED_SIZE))
																														.addGroup(
																																tabGradesLayout
																																		.createSequentialGroup()
																																		.addComponent(
																																				lblNumber,
																																				javax.swing.GroupLayout.PREFERRED_SIZE,
																																				javax.swing.GroupLayout.DEFAULT_SIZE,
																																				javax.swing.GroupLayout.PREFERRED_SIZE)
																																		.addGap(5,
																																				5,
																																				5)
																																		.addComponent(
																																				lblAsnAvg,
																																				javax.swing.GroupLayout.DEFAULT_SIZE,
																																				javax.swing.GroupLayout.DEFAULT_SIZE,
																																				Short.MAX_VALUE)))
																										.addGap(5,
																												5,
																												5)
																										.addGroup(
																												tabGradesLayout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING)
																														.addGroup(
																																tabGradesLayout
																																		.createSequentialGroup()
																																		.addComponent(
																																				lblEmail,
																																				javax.swing.GroupLayout.PREFERRED_SIZE,
																																				javax.swing.GroupLayout.DEFAULT_SIZE,
																																				javax.swing.GroupLayout.PREFERRED_SIZE)
																																		.addPreferredGap(
																																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																		.addComponent(
																																				lblEmailStudents,
																																				javax.swing.GroupLayout.DEFAULT_SIZE,
																																				javax.swing.GroupLayout.DEFAULT_SIZE,
																																				Short.MAX_VALUE)
																																		.addGap(18,
																																				18,
																																				18)
																																		.addComponent(
																																				courseAsnAvg,
																																				javax.swing.GroupLayout.PREFERRED_SIZE,
																																				javax.swing.GroupLayout.DEFAULT_SIZE,
																																				javax.swing.GroupLayout.PREFERRED_SIZE)
																																		.addGap(0,
																																				0,
																																				0)
																																		.addComponent(
																																				lblCourseAsnAvg,
																																				javax.swing.GroupLayout.PREFERRED_SIZE,
																																				javax.swing.GroupLayout.DEFAULT_SIZE,
																																				javax.swing.GroupLayout.PREFERRED_SIZE))
																														.addGroup(
																																tabGradesLayout
																																		.createSequentialGroup()
																																		.addComponent(
																																				lblExmAvg,
																																				javax.swing.GroupLayout.PREFERRED_SIZE,
																																				javax.swing.GroupLayout.DEFAULT_SIZE,
																																				javax.swing.GroupLayout.PREFERRED_SIZE)
																																		.addPreferredGap(
																																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																		.addComponent(
																																				lblCalculator,
																																				javax.swing.GroupLayout.DEFAULT_SIZE,
																																				javax.swing.GroupLayout.DEFAULT_SIZE,
																																				Short.MAX_VALUE)
																																		.addGap(18,
																																				18,
																																				18)
																																		.addGroup(
																																				tabGradesLayout
																																						.createParallelGroup(
																																								javax.swing.GroupLayout.Alignment.TRAILING)
																																						.addGroup(
																																								tabGradesLayout
																																										.createSequentialGroup()
																																										.addComponent(
																																												courseExamAvg,
																																												javax.swing.GroupLayout.PREFERRED_SIZE,
																																												javax.swing.GroupLayout.DEFAULT_SIZE,
																																												javax.swing.GroupLayout.PREFERRED_SIZE)
																																										.addGap(0,
																																												0,
																																												0)
																																										.addComponent(
																																												lblCourseExamAvg,
																																												javax.swing.GroupLayout.PREFERRED_SIZE,
																																												javax.swing.GroupLayout.DEFAULT_SIZE,
																																												javax.swing.GroupLayout.PREFERRED_SIZE))
																																						.addComponent(
																																								courseAvg,
																																								javax.swing.GroupLayout.PREFERRED_SIZE,
																																								javax.swing.GroupLayout.DEFAULT_SIZE,
																																								javax.swing.GroupLayout.PREFERRED_SIZE))))
																										.addGap(10,
																												10,
																												10)
																										.addComponent(
																												lblCourseAvg,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												javax.swing.GroupLayout.PREFERRED_SIZE))
																						.addComponent(
																								pnlTables,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addGap(0,
																				0,
																				Short.MAX_VALUE)))
										.addGap(10, 10, 10))
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								tabGradesLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												lblGradesErrorLog,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap()));
		tabGradesLayout
				.setVerticalGroup(tabGradesLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								tabGradesLayout
										.createSequentialGroup()
										.addGap(5, 5, 5)
										.addGroup(
												tabGradesLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																editCourseIcon,
																javax.swing.GroupLayout.Alignment.TRAILING,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																0,
																Short.MAX_VALUE)
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																tabGradesLayout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																		.addGroup(
																				tabGradesLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								lblImportGrades,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								lblExportGrades,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addGroup(
																				tabGradesLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								addStudent,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								deleteStudent,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								lblImportStudents,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								lblExportStudents,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)))
														.addComponent(
																courseName,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addGap(5, 5, 5)
										.addGroup(
												tabGradesLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																deliversScroll,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																350,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																pnlTables,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(
												tabGradesLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																tabGradesLayout
																		.createSequentialGroup()
																		.addGap(5,
																				5,
																				5)
																		.addGroup(
																				tabGradesLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addGroup(
																								tabGradesLayout
																										.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.TRAILING)
																										.addComponent(
																												lblCourseAvg,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												70,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addGroup(
																												tabGradesLayout
																														.createSequentialGroup()
																														.addComponent(
																																lblCourseAsnAvg,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																javax.swing.GroupLayout.PREFERRED_SIZE)
																														.addGap(0,
																																0,
																																0)
																														.addGroup(
																																tabGradesLayout
																																		.createParallelGroup(
																																				javax.swing.GroupLayout.Alignment.BASELINE)
																																		.addComponent(
																																				lblCourseExamAvg,
																																				javax.swing.GroupLayout.PREFERRED_SIZE,
																																				javax.swing.GroupLayout.DEFAULT_SIZE,
																																				javax.swing.GroupLayout.PREFERRED_SIZE)
																																		.addComponent(
																																				courseExamAvg,
																																				javax.swing.GroupLayout.DEFAULT_SIZE,
																																				javax.swing.GroupLayout.DEFAULT_SIZE,
																																				javax.swing.GroupLayout.PREFERRED_SIZE))
																														.addGap(0,
																																0,
																																0)
																														.addComponent(
																																courseAvg,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																20,
																																javax.swing.GroupLayout.PREFERRED_SIZE)))
																						.addGroup(
																								tabGradesLayout
																										.createSequentialGroup()
																										.addGroup(
																												tabGradesLayout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.BASELINE)
																														.addComponent(
																																addDeliver,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																javax.swing.GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																lblFirstName,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																javax.swing.GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																lblLastName,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																javax.swing.GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																lblEmail,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																javax.swing.GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																lblEmailStudents,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																javax.swing.GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																courseAsnAvg,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																javax.swing.GroupLayout.PREFERRED_SIZE))
																										.addGap(5,
																												5,
																												5)
																										.addGroup(
																												tabGradesLayout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.BASELINE)
																														.addComponent(
																																deleteDeliver,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																javax.swing.GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																lblNumber,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																javax.swing.GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																lblAsnAvg,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																javax.swing.GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																lblExmAvg,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																javax.swing.GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																lblCalculator,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																javax.swing.GroupLayout.PREFERRED_SIZE))))
																		.addContainerGap(
																				17,
																				Short.MAX_VALUE))
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																tabGradesLayout
																		.createSequentialGroup()
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				lblGradesErrorLog,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addContainerGap()))));

		tabSetup.setBackground(new java.awt.Color(255, 255, 255));
		tabSetup.setPreferredSize(new java.awt.Dimension(1080, 480));

		lblCourseSetup.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
		lblCourseSetup.setText("Course Setup");
		lblCourseSetup.setPreferredSize(new java.awt.Dimension(330, 30));

		lblEditCourseTtile.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
		lblEditCourseTtile
				.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		lblEditCourseTtile.setText("Course Title:");
		lblEditCourseTtile.setPreferredSize(new java.awt.Dimension(120, 30));

		txtEditCourseTitle.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
		txtEditCourseTitle.setForeground(new java.awt.Color(204, 204, 204));
		txtEditCourseTitle.setText("Please select a course");
		txtEditCourseTitle.setPreferredSize(new java.awt.Dimension(120, 30));
		txtEditCourseTitle.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				txtEditCourseTitleFocusGained(evt);
			}

			public void focusLost(java.awt.event.FocusEvent evt) {
				txtEditCourseTitleFocusLost(evt);
			}
		});

		lblEditCourseCode.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
		lblEditCourseCode
				.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		lblEditCourseCode.setText("Course Code:");
		lblEditCourseCode.setPreferredSize(new java.awt.Dimension(120, 30));

		txtEditCourseCode.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
		txtEditCourseCode.setForeground(new java.awt.Color(204, 204, 204));
		txtEditCourseCode.setText("Please select a course");
		txtEditCourseCode.setPreferredSize(new java.awt.Dimension(120, 30));
		txtEditCourseCode.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				txtEditCourseCodeFocusGained(evt);
			}

			public void focusLost(java.awt.event.FocusEvent evt) {
				txtEditCourseCodeFocusLost(evt);
			}
		});

		lblEditCourseTerm.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
		lblEditCourseTerm
				.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		lblEditCourseTerm.setText("Course Term:");
		lblEditCourseTerm.setPreferredSize(new java.awt.Dimension(120, 30));

		comboEditCourseTerm.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
		comboEditCourseTerm.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "", "A", "B", "E", "F", "G", "R", "S", "T", "W",
						"X", "Y", "Z" }));
		comboEditCourseTerm.setPreferredSize(new java.awt.Dimension(80, 30));

		lblEditCourse.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
		lblEditCourse.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblEditCourse.setText("Submit Changes");
		lblEditCourse.setBorder(javax.swing.BorderFactory
				.createLineBorder(java.awt.Color.lightGray));
		lblEditCourse
				.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		lblEditCourse.setPreferredSize(new java.awt.Dimension(200, 30));
		lblEditCourse.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				lblEditCourseMousePressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				lblEditCourseMouseReleased(evt);
			}

			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblEditCourseMouseClicked(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				lblEditCourseMouseExited(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				lblEditCourseMouseEntered(evt);
			}
		});

		lblDeleteCourse.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
		lblDeleteCourse
				.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblDeleteCourse.setText("Delete Course");
		lblDeleteCourse.setBorder(javax.swing.BorderFactory
				.createLineBorder(new java.awt.Color(204, 204, 204)));
		lblDeleteCourse.setPreferredSize(new java.awt.Dimension(165, 30));
		lblDeleteCourse.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				lblDeleteCourseMousePressed(evt);
			}

			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblDeleteCourseMouseClicked(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				lblDeleteCourseMouseExited(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				lblDeleteCourseMouseEntered(evt);
			}
		});

		editDeliverListScroll.setBorder(javax.swing.BorderFactory
				.createLineBorder(java.awt.Color.lightGray));

		editDeliverList
				.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		editDeliverList
				.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
					public void valueChanged(
							javax.swing.event.ListSelectionEvent evt) {
						editDeliverListValueChanged(evt);
					}
				});
		editDeliverListScroll.setViewportView(editDeliverList);

		lblEditDeliverName.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
		lblEditDeliverName
				.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		lblEditDeliverName.setText("Deliverable Name:");
		lblEditDeliverName.setPreferredSize(new java.awt.Dimension(120, 30));

		lblEditDeliverWeight.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
		lblEditDeliverWeight
				.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		lblEditDeliverWeight.setText("Deliverable Weight:");
		lblEditDeliverWeight.setPreferredSize(new java.awt.Dimension(120, 30));

		lblEditDeliverType.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
		lblEditDeliverType
				.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		lblEditDeliverType.setText("Deliverable Type:");
		lblEditDeliverType.setPreferredSize(new java.awt.Dimension(120, 30));

		comboEditDeliverType.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
		comboEditDeliverType.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "Assignment", "Exam", "Other" }));
		comboEditDeliverType.setPreferredSize(new java.awt.Dimension(80, 30));

		txtEditDeliverName.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
		txtEditDeliverName.setForeground(new java.awt.Color(204, 204, 204));
		txtEditDeliverName.setText("Please select a deliverable");
		txtEditDeliverName.setPreferredSize(new java.awt.Dimension(120, 30));
		txtEditDeliverName.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				txtEditDeliverNameFocusGained(evt);
			}

			public void focusLost(java.awt.event.FocusEvent evt) {
				txtEditDeliverNameFocusLost(evt);
			}
		});

		txtEditDeliverWeight.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
		txtEditDeliverWeight.setForeground(new java.awt.Color(204, 204, 204));
		txtEditDeliverWeight.setText("Please select a deliverable");
		txtEditDeliverWeight.setPreferredSize(new java.awt.Dimension(120, 30));
		txtEditDeliverWeight
				.addFocusListener(new java.awt.event.FocusAdapter() {
					public void focusGained(java.awt.event.FocusEvent evt) {
						txtEditDeliverWeightFocusGained(evt);
					}

					public void focusLost(java.awt.event.FocusEvent evt) {
						txtEditDeliverWeightFocusLost(evt);
					}
				});

		lblEditDeliver.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
		lblEditDeliver
				.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblEditDeliver.setText("Submit Changes");
		lblEditDeliver.setBorder(javax.swing.BorderFactory
				.createLineBorder(java.awt.Color.lightGray));
		lblEditDeliver.setCursor(new java.awt.Cursor(
				java.awt.Cursor.HAND_CURSOR));
		lblEditDeliver.setPreferredSize(new java.awt.Dimension(330, 30));
		lblEditDeliver.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				lblEditDeliverMousePressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				lblEditDeliverMouseReleased(evt);
			}

			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblEditDeliverMouseClicked(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				lblEditDeliverMouseExited(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				lblEditDeliverMouseEntered(evt);
			}
		});

		lblExportDelivers.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
		lblExportDelivers
				.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblExportDelivers.setText("Export Deliverables");
		lblExportDelivers.setBorder(javax.swing.BorderFactory
				.createLineBorder(java.awt.Color.lightGray));
		lblExportDelivers.setCursor(new java.awt.Cursor(
				java.awt.Cursor.HAND_CURSOR));
		lblExportDelivers.setPreferredSize(new java.awt.Dimension(150, 30));
		lblExportDelivers.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				lblExportDeliversMousePressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				lblExportDeliversMouseReleased(evt);
			}

			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblExportDeliversMouseClicked(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				lblExportDeliversMouseExited(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				lblExportDeliversMouseEntered(evt);
			}
		});

		lblImportDelivers.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
		lblImportDelivers
				.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblImportDelivers.setText("Import Deliverables");
		lblImportDelivers.setBorder(javax.swing.BorderFactory
				.createLineBorder(java.awt.Color.lightGray));
		lblImportDelivers.setCursor(new java.awt.Cursor(
				java.awt.Cursor.HAND_CURSOR));
		lblImportDelivers.setPreferredSize(new java.awt.Dimension(150, 30));
		lblImportDelivers.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				lblImportDeliversMousePressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				lblImportDeliversMouseReleased(evt);
			}

			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblImportDeliversMouseClicked(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				lblImportDeliversMouseExited(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				lblImportDeliversMouseEntered(evt);
			}
		});

		lblCourseDeliverables.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
		lblCourseDeliverables.setText("Course Deliverables:");
		lblCourseDeliverables.setPreferredSize(new java.awt.Dimension(200, 30));

		lblSetupErrorLog.setForeground(new java.awt.Color(255, 0, 0));
		lblSetupErrorLog.setPreferredSize(new java.awt.Dimension(34, 20));

		txtCourseDesc.setBorder(javax.swing.BorderFactory
				.createLineBorder(java.awt.Color.lightGray));
		txtCourseDesc.setForeground(java.awt.Color.lightGray);
		txtCourseDesc.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				txtCourtxtCourseDesceDescFocusGained(evt);
			}

			public void focusLost(java.awt.event.FocusEvent evt) {
				txtCourtxtCourseDesceDescFocusLost(evt);
			}
		});
		courseDescScroll.setViewportView(txtCourseDesc);

		lblEditDeleteDeliver.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
		lblEditDeleteDeliver
				.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblEditDeleteDeliver.setText("Delete Deliverable");
		lblEditDeleteDeliver.setBorder(javax.swing.BorderFactory
				.createLineBorder(java.awt.Color.lightGray));
		lblEditDeleteDeliver.setCursor(new java.awt.Cursor(
				java.awt.Cursor.HAND_CURSOR));
		lblEditDeleteDeliver.setPreferredSize(new java.awt.Dimension(160, 30));
		lblEditDeleteDeliver
				.addMouseListener(new java.awt.event.MouseAdapter() {
					public void mousePressed(java.awt.event.MouseEvent evt) {
						lblEditDeleteDeliverMousePressed(evt);
					}

					public void mouseReleased(java.awt.event.MouseEvent evt) {
						lblEditDeleteDeliverMouseReleased(evt);
					}

					public void mouseClicked(java.awt.event.MouseEvent evt) {
						lblEditDeleteDeliverMouseClicked(evt);
					}

					public void mouseExited(java.awt.event.MouseEvent evt) {
						lblEditDeleteDeliverMouseExited(evt);
					}

					public void mouseEntered(java.awt.event.MouseEvent evt) {
						lblEditDeleteDeliverMouseEntered(evt);
					}
				});

		lblDeleteSure.setFont(new java.awt.Font("Helvetica", 0, 13)); // NOI18N
		lblDeleteSure.setForeground(new java.awt.Color(255, 102, 102));
		lblDeleteSure.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblDeleteSure.setText(" ");

		lblBlue.setBackground(new java.awt.Color(20, 150, 250));
		lblBlue.setToolTipText("");
		lblBlue.setBorder(javax.swing.BorderFactory
				.createLineBorder(java.awt.Color.lightGray));
		lblBlue.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		lblBlue.setOpaque(true);
		lblBlue.setPreferredSize(new java.awt.Dimension(20, 20));
		lblBlue.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblBlueMouseClicked(evt);
			}
		});

		lblWestern.setBackground(new java.awt.Color(79, 38, 131));
		lblWestern.setToolTipText("");
		lblWestern.setBorder(javax.swing.BorderFactory
				.createLineBorder(java.awt.Color.lightGray));
		lblWestern.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		lblWestern.setOpaque(true);
		lblWestern.setPreferredSize(new java.awt.Dimension(20, 20));
		lblWestern.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblWesternMouseClicked(evt);
			}
		});

		lblCobalt.setBackground(new java.awt.Color(0, 80, 239));
		lblCobalt.setToolTipText("");
		lblCobalt.setBorder(javax.swing.BorderFactory
				.createLineBorder(java.awt.Color.lightGray));
		lblCobalt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		lblCobalt.setOpaque(true);
		lblCobalt.setPreferredSize(new java.awt.Dimension(20, 20));
		lblCobalt.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblCobaltMouseClicked(evt);
			}
		});

		lblTeal.setBackground(new java.awt.Color(0, 171, 169));
		lblTeal.setToolTipText("");
		lblTeal.setBorder(javax.swing.BorderFactory
				.createLineBorder(java.awt.Color.lightGray));
		lblTeal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		lblTeal.setOpaque(true);
		lblTeal.setPreferredSize(new java.awt.Dimension(20, 20));
		lblTeal.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblTealMouseClicked(evt);
			}
		});

		lblViolet.setBackground(new java.awt.Color(170, 0, 255));
		lblViolet.setToolTipText("");
		lblViolet.setBorder(javax.swing.BorderFactory
				.createLineBorder(java.awt.Color.lightGray));
		lblViolet.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		lblViolet.setOpaque(true);
		lblViolet.setPreferredSize(new java.awt.Dimension(20, 20));
		lblViolet.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblVioletMouseClicked(evt);
			}
		});

		lblMagenta.setBackground(new java.awt.Color(216, 0, 115));
		lblMagenta.setToolTipText("");
		lblMagenta.setBorder(javax.swing.BorderFactory
				.createLineBorder(java.awt.Color.lightGray));
		lblMagenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		lblMagenta.setOpaque(true);
		lblMagenta.setPreferredSize(new java.awt.Dimension(20, 20));
		lblMagenta.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblMagentaMouseClicked(evt);
			}
		});

		lblOlive.setBackground(new java.awt.Color(109, 135, 100));
		lblOlive.setToolTipText("");
		lblOlive.setBorder(javax.swing.BorderFactory
				.createLineBorder(java.awt.Color.lightGray));
		lblOlive.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		lblOlive.setOpaque(true);
		lblOlive.setPreferredSize(new java.awt.Dimension(20, 20));
		lblOlive.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblOliveMouseClicked(evt);
			}
		});

		lblMauve.setBackground(new java.awt.Color(118, 96, 138));
		lblMauve.setToolTipText("");
		lblMauve.setBorder(javax.swing.BorderFactory
				.createLineBorder(java.awt.Color.lightGray));
		lblMauve.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		lblMauve.setOpaque(true);
		lblMauve.setPreferredSize(new java.awt.Dimension(20, 20));
		lblMauve.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblMauveMouseClicked(evt);
			}
		});

		lblSteel.setBackground(new java.awt.Color(100, 118, 135));
		lblSteel.setToolTipText("");
		lblSteel.setBorder(javax.swing.BorderFactory
				.createLineBorder(java.awt.Color.lightGray));
		lblSteel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		lblSteel.setOpaque(true);
		lblSteel.setPreferredSize(new java.awt.Dimension(20, 20));
		lblSteel.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblSteelMouseClicked(evt);
			}
		});

		javax.swing.GroupLayout tabSetupLayout = new javax.swing.GroupLayout(
				tabSetup);
		tabSetup.setLayout(tabSetupLayout);
		tabSetupLayout
				.setHorizontalGroup(tabSetupLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								tabSetupLayout
										.createSequentialGroup()
										.addGap(20, 20, 20)
										.addGroup(
												tabSetupLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																lblSetupErrorLog,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addGroup(
																tabSetupLayout
																		.createSequentialGroup()
																		.addGroup(
																				tabSetupLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								lblCourseSetup,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								courseDescScroll,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								488,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addGroup(
																								tabSetupLayout
																										.createSequentialGroup()
																										.addComponent(
																												lblWestern,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																										.addComponent(
																												lblViolet,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																										.addComponent(
																												lblMauve,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addGap(10,
																												10,
																												10)
																										.addComponent(
																												lblSteel,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																										.addComponent(
																												lblOlive,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																										.addComponent(
																												lblTeal,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addGap(10,
																												10,
																												10)
																										.addComponent(
																												lblBlue,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addGap(10,
																												10,
																												10)
																										.addComponent(
																												lblCobalt,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addGap(10,
																												10,
																												10)
																										.addComponent(
																												lblMagenta,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												javax.swing.GroupLayout.PREFERRED_SIZE)))
																		.addGroup(
																				tabSetupLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addGroup(
																								tabSetupLayout
																										.createSequentialGroup()
																										.addGap(20,
																												20,
																												20)
																										.addGroup(
																												tabSetupLayout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING)
																														.addGroup(
																																tabSetupLayout
																																		.createParallelGroup(
																																				javax.swing.GroupLayout.Alignment.TRAILING,
																																				false)
																																		.addGroup(
																																				javax.swing.GroupLayout.Alignment.LEADING,
																																				tabSetupLayout
																																						.createSequentialGroup()
																																						.addComponent(
																																								lblEditDeliverName,
																																								javax.swing.GroupLayout.PREFERRED_SIZE,
																																								javax.swing.GroupLayout.DEFAULT_SIZE,
																																								javax.swing.GroupLayout.PREFERRED_SIZE)
																																						.addPreferredGap(
																																								javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																																						.addComponent(
																																								txtEditDeliverName,
																																								javax.swing.GroupLayout.PREFERRED_SIZE,
																																								200,
																																								javax.swing.GroupLayout.PREFERRED_SIZE))
																																		.addGroup(
																																				javax.swing.GroupLayout.Alignment.LEADING,
																																				tabSetupLayout
																																						.createSequentialGroup()
																																						.addComponent(
																																								lblEditDeliverWeight,
																																								javax.swing.GroupLayout.PREFERRED_SIZE,
																																								126,
																																								javax.swing.GroupLayout.PREFERRED_SIZE)
																																						.addPreferredGap(
																																								javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																						.addComponent(
																																								txtEditDeliverWeight,
																																								javax.swing.GroupLayout.PREFERRED_SIZE,
																																								200,
																																								javax.swing.GroupLayout.PREFERRED_SIZE))
																																		.addComponent(
																																				lblDeleteCourse,
																																				javax.swing.GroupLayout.Alignment.LEADING,
																																				javax.swing.GroupLayout.DEFAULT_SIZE,
																																				javax.swing.GroupLayout.DEFAULT_SIZE,
																																				Short.MAX_VALUE)
																																		.addComponent(
																																				lblEditCourse,
																																				javax.swing.GroupLayout.Alignment.LEADING,
																																				javax.swing.GroupLayout.DEFAULT_SIZE,
																																				javax.swing.GroupLayout.DEFAULT_SIZE,
																																				Short.MAX_VALUE)
																																		.addGroup(
																																				javax.swing.GroupLayout.Alignment.LEADING,
																																				tabSetupLayout
																																						.createSequentialGroup()
																																						.addComponent(
																																								lblEditCourseTerm,
																																								javax.swing.GroupLayout.PREFERRED_SIZE,
																																								javax.swing.GroupLayout.DEFAULT_SIZE,
																																								javax.swing.GroupLayout.PREFERRED_SIZE)
																																						.addGap(10,
																																								10,
																																								10)
																																						.addComponent(
																																								comboEditCourseTerm,
																																								javax.swing.GroupLayout.PREFERRED_SIZE,
																																								50,
																																								javax.swing.GroupLayout.PREFERRED_SIZE))
																																		.addGroup(
																																				javax.swing.GroupLayout.Alignment.LEADING,
																																				tabSetupLayout
																																						.createSequentialGroup()
																																						.addComponent(
																																								lblEditDeliverType,
																																								javax.swing.GroupLayout.PREFERRED_SIZE,
																																								javax.swing.GroupLayout.DEFAULT_SIZE,
																																								javax.swing.GroupLayout.PREFERRED_SIZE)
																																						.addPreferredGap(
																																								javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																																						.addComponent(
																																								comboEditDeliverType,
																																								javax.swing.GroupLayout.PREFERRED_SIZE,
																																								200,
																																								javax.swing.GroupLayout.PREFERRED_SIZE)))
																														.addGroup(
																																tabSetupLayout
																																		.createSequentialGroup()
																																		.addGroup(
																																				tabSetupLayout
																																						.createParallelGroup(
																																								javax.swing.GroupLayout.Alignment.LEADING)
																																						.addComponent(
																																								lblEditCourseCode,
																																								javax.swing.GroupLayout.PREFERRED_SIZE,
																																								120,
																																								javax.swing.GroupLayout.PREFERRED_SIZE)
																																						.addComponent(
																																								lblEditCourseTtile,
																																								javax.swing.GroupLayout.PREFERRED_SIZE,
																																								120,
																																								javax.swing.GroupLayout.PREFERRED_SIZE))
																																		.addGap(10,
																																				10,
																																				10)
																																		.addGroup(
																																				tabSetupLayout
																																						.createParallelGroup(
																																								javax.swing.GroupLayout.Alignment.LEADING)
																																						.addComponent(
																																								txtEditCourseCode,
																																								javax.swing.GroupLayout.PREFERRED_SIZE,
																																								200,
																																								javax.swing.GroupLayout.PREFERRED_SIZE)
																																						.addComponent(
																																								txtEditCourseTitle,
																																								javax.swing.GroupLayout.PREFERRED_SIZE,
																																								200,
																																								javax.swing.GroupLayout.PREFERRED_SIZE)))
																														.addGroup(
																																javax.swing.GroupLayout.Alignment.TRAILING,
																																tabSetupLayout
																																		.createSequentialGroup()
																																		.addGap(0,
																																				0,
																																				Short.MAX_VALUE)
																																		.addGroup(
																																				tabSetupLayout
																																						.createParallelGroup(
																																								javax.swing.GroupLayout.Alignment.LEADING)
																																						.addComponent(
																																								lblEditDeleteDeliver,
																																								javax.swing.GroupLayout.PREFERRED_SIZE,
																																								330,
																																								javax.swing.GroupLayout.PREFERRED_SIZE)
																																						.addComponent(
																																								lblEditDeliver,
																																								javax.swing.GroupLayout.PREFERRED_SIZE,
																																								javax.swing.GroupLayout.DEFAULT_SIZE,
																																								javax.swing.GroupLayout.PREFERRED_SIZE)))))
																						.addGroup(
																								tabSetupLayout
																										.createSequentialGroup()
																										.addGap(18,
																												18,
																												18)
																										.addComponent(
																												lblDeleteSure,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												Short.MAX_VALUE)))
																		.addGap(10,
																				10,
																				10)
																		.addGroup(
																				tabSetupLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								false)
																						.addComponent(
																								lblCourseDeliverables,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								lblImportDelivers,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								lblExportDelivers,
																								javax.swing.GroupLayout.Alignment.TRAILING,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								editDeliverListScroll,
																								javax.swing.GroupLayout.Alignment.TRAILING,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								0,
																								Short.MAX_VALUE))))
										.addGap(10, 10, 10)));
		tabSetupLayout
				.setVerticalGroup(tabSetupLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								tabSetupLayout
										.createSequentialGroup()
										.addGap(18, 18, 18)
										.addGroup(
												tabSetupLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																lblCourseDeliverables,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																28,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblCourseSetup,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(5, 5, 5)
										.addGroup(
												tabSetupLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addGroup(
																tabSetupLayout
																		.createSequentialGroup()
																		.addGroup(
																				tabSetupLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								false)
																						.addComponent(
																								editDeliverListScroll)
																						.addGroup(
																								tabSetupLayout
																										.createSequentialGroup()
																										.addGroup(
																												tabSetupLayout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.BASELINE)
																														.addComponent(
																																lblEditCourseTtile,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																javax.swing.GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																txtEditCourseTitle,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																javax.swing.GroupLayout.PREFERRED_SIZE))
																										.addGap(5,
																												5,
																												5)
																										.addGroup(
																												tabSetupLayout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.BASELINE)
																														.addComponent(
																																lblEditCourseCode,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																javax.swing.GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																txtEditCourseCode,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																javax.swing.GroupLayout.PREFERRED_SIZE))
																										.addGap(5,
																												5,
																												5)
																										.addGroup(
																												tabSetupLayout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.BASELINE)
																														.addComponent(
																																lblEditCourseTerm,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																javax.swing.GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																comboEditCourseTerm,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																javax.swing.GroupLayout.PREFERRED_SIZE))
																										.addGap(5,
																												5,
																												5)
																										.addComponent(
																												lblEditCourse,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addGap(5,
																												5,
																												5)
																										.addComponent(
																												lblDeleteCourse,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																										.addComponent(
																												lblDeleteSure)
																										.addGap(12,
																												12,
																												12)
																										.addGroup(
																												tabSetupLayout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.BASELINE)
																														.addComponent(
																																lblEditDeliverName,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																javax.swing.GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																txtEditDeliverName,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																javax.swing.GroupLayout.PREFERRED_SIZE))
																										.addGap(5,
																												5,
																												5)
																										.addGroup(
																												tabSetupLayout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.BASELINE)
																														.addComponent(
																																lblEditDeliverWeight,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																javax.swing.GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																txtEditDeliverWeight,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																javax.swing.GroupLayout.PREFERRED_SIZE))
																										.addGap(5,
																												5,
																												5)
																										.addGroup(
																												tabSetupLayout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.BASELINE)
																														.addComponent(
																																lblEditDeliverType,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																javax.swing.GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																comboEditDeliverType,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																javax.swing.GroupLayout.PREFERRED_SIZE))))
																		.addGap(5,
																				5,
																				5)
																		.addGroup(
																				tabSetupLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								lblImportDelivers,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								lblEditDeliver,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addGap(5,
																				5,
																				5)
																		.addGroup(
																				tabSetupLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								lblExportDelivers,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								lblEditDeleteDeliver,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addGap(17,
																				17,
																				17))
														.addGroup(
																tabSetupLayout
																		.createSequentialGroup()
																		.addGroup(
																				tabSetupLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								lblBlue,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								lblWestern,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								lblCobalt,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								lblTeal,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								lblViolet,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								lblMagenta,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								lblOlive,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								lblSteel,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								lblMauve,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addGap(10,
																				10,
																				10)
																		.addComponent(
																				courseDescScroll)
																		.addGap(10,
																				10,
																				10)))
										.addComponent(
												lblSetupErrorLog,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		tabDropbox.setBackground(new java.awt.Color(255, 255, 255));
		tabDropbox.setPreferredSize(new java.awt.Dimension(1080, 480));

		txtDbxCode.setForeground(java.awt.Color.lightGray);
		txtDbxCode.setHorizontalAlignment(javax.swing.JTextField.LEFT);
		txtDbxCode.setText("Please enter the code...");
		txtDbxCode.setPreferredSize(new java.awt.Dimension(500, 30));
		txtDbxCode.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				txtDbxCodeFocusGained(evt);
			}

			public void focusLost(java.awt.event.FocusEvent evt) {
				txtDbxCodeFocusLost(evt);
			}
		});
		txtDbxCode.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				txtDbxCodeActionPerformed(evt);
			}
		});

		lblDbxActivate
				.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblDbxActivate.setText("Activate");
		lblDbxActivate.setBorder(javax.swing.BorderFactory
				.createLineBorder(java.awt.Color.lightGray));
		lblDbxActivate.setCursor(new java.awt.Cursor(
				java.awt.Cursor.HAND_CURSOR));
		lblDbxActivate.setPreferredSize(new java.awt.Dimension(245, 30));
		lblDbxActivate.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblDbxActivateMouseClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				lblDbxActivateMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				lblDbxActivateMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				lblDbxActivateMousePressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				lblDbxActivateMouseReleased(evt);
			}
		});

		lblDbxUpload.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblDbxUpload.setText("Upload");
		lblDbxUpload.setBorder(javax.swing.BorderFactory
				.createLineBorder(java.awt.Color.lightGray));
		lblDbxUpload
				.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		lblDbxUpload.setPreferredSize(new java.awt.Dimension(245, 30));
		lblDbxUpload.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblDbxUploadMouseClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				lblDbxUploadMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				lblDbxUploadMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				lblDbxUploadMousePressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				lblDbxUploadMouseReleased(evt);
			}
		});

		lblDbxDownload
				.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblDbxDownload.setText("Download");
		lblDbxDownload.setBorder(javax.swing.BorderFactory
				.createLineBorder(java.awt.Color.lightGray));
		lblDbxDownload.setCursor(new java.awt.Cursor(
				java.awt.Cursor.HAND_CURSOR));
		lblDbxDownload.setPreferredSize(new java.awt.Dimension(245, 30));
		lblDbxDownload.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblDbxDownloadMouseClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				lblDbxDownloadMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				lblDbxDownloadMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				lblDbxDownloadMousePressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				lblDbxDownloadMouseReleased(evt);
			}
		});

		lblDbxSubmit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblDbxSubmit.setText("Submit");
		lblDbxSubmit.setBorder(javax.swing.BorderFactory
				.createLineBorder(java.awt.Color.lightGray));
		lblDbxSubmit
				.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		lblDbxSubmit.setPreferredSize(new java.awt.Dimension(245, 30));
		lblDbxSubmit.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblDbxSubmitMouseClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				lblDbxSubmitMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				lblDbxSubmitMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				lblDbxSubmitMousePressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				lblDbxSubmitMouseReleased(evt);
			}
		});

		lblDbxErrorLog.setForeground(java.awt.Color.lightGray);
		lblDbxErrorLog.setText("Disconnected");
		lblDbxErrorLog.setPreferredSize(new java.awt.Dimension(500, 20));

		lblDbxVerify.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblDbxVerify.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/cs2212/team4/dropboxFalse.png"))); // NOI18N
		lblDbxVerify.setPreferredSize(new java.awt.Dimension(500, 160));

		lblEmailVarify
				.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblEmailVarify.setIcon(new javax.swing.ImageIcon(getClass()
				.getResource("/cs2212/team4/emailFalse.png"))); // NOI18N
		lblEmailVarify.setPreferredSize(new java.awt.Dimension(500, 160));

		txtEmail.setForeground(new java.awt.Color(204, 204, 204));
		txtEmail.setHorizontalAlignment(javax.swing.JTextField.LEFT);
		txtEmail.setText("Please enter your email address...");
		txtEmail.setPreferredSize(new java.awt.Dimension(400, 30));
		txtEmail.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				txtEmailFocusGained(evt);
			}

			public void focusLost(java.awt.event.FocusEvent evt) {
				txtEmailFocusLost(evt);
			}
		});

		txtPassword.setText("sickcoderoverhere");
		txtPassword.setPreferredSize(new java.awt.Dimension(400, 30));
		txtPassword.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				txtPasswordFocusGained(evt);
			}
		});

		lblPassword.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
		lblPassword.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		lblPassword.setText("Password:");
		lblPassword.setPreferredSize(new java.awt.Dimension(100, 30));

		comboEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		comboEmail.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"", "Gmail", "Hotmail", "Yahoo" }));
		comboEmail.setPreferredSize(new java.awt.Dimension(500, 30));

		lblEmailAddress.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
		lblEmailAddress.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		lblEmailAddress.setText("Email Address:");
		lblEmailAddress.setPreferredSize(new java.awt.Dimension(100, 30));

		lblEMLErrorLog.setForeground(java.awt.Color.lightGray);
		lblEMLErrorLog.setText("Disconnected");
		lblEMLErrorLog.setPreferredSize(new java.awt.Dimension(500, 20));

		customizeSMTP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		customizeSMTP.setText("Customize your SMTP server");
		customizeSMTP.setBorder(javax.swing.BorderFactory
				.createLineBorder(java.awt.Color.lightGray));
		customizeSMTP
				.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		customizeSMTP.setPreferredSize(new java.awt.Dimension(245, 30));
		customizeSMTP.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				customizeSMTPMouseClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				customizeSMTPMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				customizeSMTPMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				customizeSMTPMousePressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				customizeSMTPMouseReleased(evt);
			}
		});

		lblSignin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblSignin.setText("Sign In");
		lblSignin.setBorder(javax.swing.BorderFactory
				.createLineBorder(java.awt.Color.lightGray));
		lblSignin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		lblSignin.setPreferredSize(new java.awt.Dimension(245, 30));
		lblSignin.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblSigninMouseClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				lblSigninMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				lblSigninMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				lblSigninMousePressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				lblSigninMouseReleased(evt);
			}
		});

		javax.swing.GroupLayout tabDropboxLayout = new javax.swing.GroupLayout(
				tabDropbox);
		tabDropbox.setLayout(tabDropboxLayout);
		tabDropboxLayout
				.setHorizontalGroup(tabDropboxLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								tabDropboxLayout
										.createSequentialGroup()
										.addGap(20, 20, 20)
										.addGroup(
												tabDropboxLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																tabDropboxLayout
																		.createSequentialGroup()
																		.addGroup(
																				tabDropboxLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								lblEmailVarify,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								comboEmail,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addGap(40,
																				40,
																				40)
																		.addGroup(
																				tabDropboxLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								txtDbxCode,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								lblDbxVerify,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)))
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																tabDropboxLayout
																		.createSequentialGroup()
																		.addComponent(
																				lblEMLErrorLog,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addGap(40,
																				40,
																				40)
																		.addComponent(
																				lblDbxErrorLog,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																tabDropboxLayout
																		.createSequentialGroup()
																		.addGroup(
																				tabDropboxLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING)
																						.addGroup(
																								tabDropboxLayout
																										.createSequentialGroup()
																										.addComponent(
																												lblEmailAddress,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addGap(0,
																												0,
																												0)
																										.addComponent(
																												txtEmail,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												javax.swing.GroupLayout.PREFERRED_SIZE))
																						.addGroup(
																								tabDropboxLayout
																										.createSequentialGroup()
																										.addComponent(
																												customizeSMTP,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addGap(10,
																												10,
																												10)
																										.addComponent(
																												lblSignin,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												javax.swing.GroupLayout.PREFERRED_SIZE))
																						.addGroup(
																								tabDropboxLayout
																										.createSequentialGroup()
																										.addComponent(
																												lblPassword,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addGap(0,
																												0,
																												0)
																										.addComponent(
																												txtPassword,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												javax.swing.GroupLayout.PREFERRED_SIZE)))
																		.addGap(40,
																				40,
																				40)
																		.addGroup(
																				tabDropboxLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								lblDbxActivate,
																								javax.swing.GroupLayout.Alignment.TRAILING,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								lblDbxUpload,
																								javax.swing.GroupLayout.Alignment.TRAILING,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addGap(10,
																				10,
																				10)
																		.addGroup(
																				tabDropboxLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								lblDbxSubmit,
																								javax.swing.GroupLayout.Alignment.TRAILING,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								lblDbxDownload,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE))))
										.addGap(20, 20, 20)));
		tabDropboxLayout
				.setVerticalGroup(tabDropboxLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								tabDropboxLayout
										.createSequentialGroup()
										.addGap(60, 60, 60)
										.addGroup(
												tabDropboxLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																lblDbxVerify,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGroup(
																tabDropboxLayout
																		.createSequentialGroup()
																		.addComponent(
																				lblEmailVarify,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addGap(38,
																				38,
																				38)
																		.addComponent(
																				comboEmail,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addGap(20, 20, 20)
										.addGroup(
												tabDropboxLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																txtDbxCode,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																txtEmail,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblEmailAddress,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(10, 10, 10)
										.addGroup(
												tabDropboxLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																lblDbxSubmit,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblDbxActivate,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																txtPassword,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblPassword,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(10, 10, 10)
										.addGroup(
												tabDropboxLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																tabDropboxLayout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																		.addComponent(
																				lblDbxUpload,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addComponent(
																				lblDbxDownload,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(
																tabDropboxLayout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																		.addComponent(
																				lblSignin,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addComponent(
																				customizeSMTP,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addGap(30, 30, 30)
										.addGroup(
												tabDropboxLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																lblDbxErrorLog,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblEMLErrorLog,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(10, 10, 10)));

		javax.swing.GroupLayout containerLayout = new javax.swing.GroupLayout(
				container);
		container.setLayout(containerLayout);
		containerLayout
				.setHorizontalGroup(containerLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGap(0, 2446, Short.MAX_VALUE)
						.addGroup(
								containerLayout
										.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(
												containerLayout
														.createSequentialGroup()
														.addComponent(
																tabSetup,
																1078,
																1078,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGap(0, 1366,
																Short.MAX_VALUE)))
						.addGroup(
								containerLayout
										.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(
												containerLayout
														.createSequentialGroup()
														.addComponent(
																tabGrades,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addContainerGap()))
						.addGroup(
								containerLayout
										.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(
												containerLayout
														.createSequentialGroup()
														.addComponent(
																tabDropbox,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addContainerGap())));
		containerLayout
				.setVerticalGroup(containerLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGap(0, 491, Short.MAX_VALUE)
						.addGroup(
								containerLayout
										.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(
												containerLayout
														.createSequentialGroup()
														.addComponent(
																tabSetup,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGap(0, 11,
																Short.MAX_VALUE)))
						.addGroup(
								containerLayout
										.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(
												containerLayout
														.createSequentialGroup()
														.addComponent(
																tabGrades,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																482,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGap(0, 9,
																Short.MAX_VALUE)))
						.addGroup(
								containerLayout
										.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(
												containerLayout
														.createSequentialGroup()
														.addComponent(
																tabDropbox,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addContainerGap())));
		container.setLayer(tabGrades, javax.swing.JLayeredPane.DEFAULT_LAYER);
		container.setLayer(tabSetup, javax.swing.JLayeredPane.DEFAULT_LAYER);
		container.setLayer(tabDropbox, javax.swing.JLayeredPane.DEFAULT_LAYER);

		getContentPane().add(
				container,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1,
						-1));

		lyrActiveCourse
				.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		lblActiveCourseTitleInfo.setFont(helvetica);
		lblActiveCourseTitleInfo
				.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		lblActiveCourseTitleInfo.setText("Select a course");
		lyrActiveCourse.add(lblActiveCourseTitleInfo,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 40, 160,
						40));

		lblActiveCourseInfo.setForeground(new java.awt.Color(153, 153, 153));
		lblActiveCourseInfo
				.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		lyrActiveCourse.add(lblActiveCourseInfo,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 70, 150,
						20));

		lblActiveCourse.setIcon(new javax.swing.ImageIcon(getClass()
				.getResource("/cs2212/team4/activeCourseTab.png"))); // NOI18N
		lblActiveCourse.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblActiveCourseMouseClicked(evt);
			}
		});
		lyrActiveCourse.add(lblActiveCourse,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, -1,
						-1));

		getContentPane().add(
				lyrActiveCourse,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 0, 650,
						-1));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void txtHostNameFocusGained(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txtHostNameFocusGained
		txtHostName.selectAll();
		txtHostName.setForeground(Color.black);
	}// GEN-LAST:event_txtHostNameFocusGained

	private void txtHostNameFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txtHostNameFocusLost
		if (txtHostName.getText().equals(""))
			txtHostName.setText("Please enter the host name...");
		if (txtHostName.getText().equals("Please enter the host name..."))
			txtHostName.setForeground(Color.lightGray);
	}// GEN-LAST:event_txtHostNameFocusLost

	private void txtCustomPasswordFocusGained(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txtCustomPasswordFocusGained
		txtCustomPassword.setText("");
	}// GEN-LAST:event_txtCustomPasswordFocusGained

	private void txtCustomEmailFocusGained(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txtCustomEmailFocusGained
		txtCustomEmail.selectAll();
		txtCustomEmail.setForeground(Color.black);
	}// GEN-LAST:event_txtCustomEmailFocusGained

	private void txtCustomEmailFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txtCustomEmailFocusLost
		if (txtCustomEmail.getText().equals(""))
			txtCustomEmail.setText("Please enter your email address...");
		if (txtCustomEmail.getText().equals(
				"Please enter your email address..."))
			txtCustomEmail.setForeground(Color.lightGray);
	}// GEN-LAST:event_txtCustomEmailFocusLost

	private void txtPortFocusGained(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txtPortFocusGained
		txtPort.selectAll();
		txtPort.setForeground(Color.black);
	}// GEN-LAST:event_txtPortFocusGained

	private void txtPortFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txtPortFocusLost
		if (txtPort.getText().equals(""))
			txtPort.setText("Please enter a port...");
		if (txtPort.getText().equals("Please enter a port..."))
			txtPort.setForeground(Color.lightGray);
	}// GEN-LAST:event_txtPortFocusLost

	private void lblCustomSubmitMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblCustomSubmitMouseClicked
		char[] pass = txtCustomPassword.getPassword();
		if (!txtHostName.getText().equals("Please enter the host name...")) {
			if (!txtPort.getText().equals("Please enter a port...")) {
				if (!txtCustomEmail.getText().equals(
						"Please enter your email address...")) {
					if (txtCustomPassword.getPassword().length != 0) {
						String password = "";
						for (int i = 0; i < txtCustomPassword.getPassword().length; i++) {
							password += pass[i];
						}
						MyProperties props = new MyProperties(
								txtHostName.getText(), txtPort.getText(),
								txtCustomEmail.getText(), password);
						Email email = new Email(props.getProperties());
						if (email.authenUser().equals("")) {
							gradebook.setProperties(props.getProperties());
							lblEMLErrorLog.setForeground(Color.green);
							lblEMLErrorLog.setText("Connected.");
							lblSMTPError.setText("");
							closeFrame(propsFrame);
						} else {
							lblSMTPError.setForeground(Color.red);
							lblSMTPError.setText("Invalid information");
						}

					} else {
						lblSMTPError.setForeground(Color.red);
						lblSMTPError.setText("Please enter a password");
					}
				} else {
					lblSMTPError.setForeground(Color.red);
					lblSMTPError.setText("Please enter an email");
				}
			} else {
				lblSMTPError.setForeground(Color.red);
				lblSMTPError.setText("Please enter a port");
			}
		} else {
			lblSMTPError.setForeground(Color.red);
			lblSMTPError.setText("Please enter a hostname");
		}
	}// GEN-LAST:event_lblCustomSubmitMouseClicked

	private void lblCustomSubmitMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblCustomSubmitMouseEntered
		lblCustomSubmit.setBorder(BorderFactory.createLineBorder(new Color(20,
				150, 250)));
	}// GEN-LAST:event_lblCustomSubmitMouseEntered

	private void lblCustomSubmitMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblCustomSubmitMouseExited
		lblCustomSubmit.setBorder(BorderFactory.createLineBorder(new Color(204,
				204, 204)));
	}// GEN-LAST:event_lblCustomSubmitMouseExited

	private void lblCustomSubmitMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblCustomSubmitMousePressed
		lblCustomSubmit.setBorder(BorderFactory.createBevelBorder(1, new Color(
				20, 150, 250), new Color(20, 150, 250),
				new Color(20, 150, 250), new Color(20, 150, 250)));
	}// GEN-LAST:event_lblCustomSubmitMousePressed

	private void lblCustomSubmitMouseReleased(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblCustomSubmitMouseReleased
		lblCustomSubmit.setBorder(BorderFactory.createLineBorder(new Color(204,
				204, 204)));
	}// GEN-LAST:event_lblCustomSubmitMouseReleased

	private void txtDbxCodeActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtDbxCodeActionPerformed
	}// GEN-LAST:event_txtDbxCodeActionPerformed

	private void txtEmailFocusGained(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txtEmailFocusGained
		if (txtEmail.getText().equals("Please enter your email address...")) {
			txtEmail.setForeground(Color.BLACK);
			txtEmail.selectAll();
		}
	}// GEN-LAST:event_txtEmailFocusGained

	private void txtEmailFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txtEmailFocusLost
		if (txtEmail.getText().equals("Please enter your email address..."))
			txtEmail.setForeground(Color.lightGray);
		if (txtEmail.getText().equals("")) {
			txtEmail.setForeground(Color.lightGray);
			txtEmail.setText("Please enter your email address...");
		}
	}// GEN-LAST:event_txtEmailFocusLost

	private void txtPasswordFocusGained(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txtPasswordFocusGained
		txtPassword.setText("");
	}// GEN-LAST:event_txtPasswordFocusGained

	private void txtDbxCodeFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txtDbxCodeFocusLost
		if (txtDbxCode.getText().equals("Please enter the code..."))
			txtDbxCode.setForeground(Color.lightGray);
		if (txtDbxCode.getText().equals("")) {
			txtDbxCode.setForeground(Color.lightGray);
			txtDbxCode.setText("Please enter the code...");
		}
	}// GEN-LAST:event_txtDbxCodeFocusLost

	private void customizeSMTPMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_customizeSMTPMouseClicked
		propsFrame.add(pnlCustom);
		pnlCustom.setVisible(true);
		propsFrame.setVisible(true);
		disable();
	}// GEN-LAST:event_customizeSMTPMouseClicked

	private void customizeSMTPMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_customizeSMTPMouseEntered
		customizeSMTP.setBorder(BorderFactory.createLineBorder(new Color(20,
				150, 250)));
	}// GEN-LAST:event_customizeSMTPMouseEntered

	private void customizeSMTPMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_customizeSMTPMouseExited
		customizeSMTP.setBorder(BorderFactory.createLineBorder(new Color(204,
				204, 204)));
	}// GEN-LAST:event_customizeSMTPMouseExited

	private void customizeSMTPMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_customizeSMTPMousePressed
		customizeSMTP.setBorder(BorderFactory.createBevelBorder(1, new Color(
				20, 150, 250), new Color(20, 150, 250),
				new Color(20, 150, 250), new Color(20, 150, 250)));
	}// GEN-LAST:event_customizeSMTPMousePressed

	private void customizeSMTPMouseReleased(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_customizeSMTPMouseReleased
		customizeSMTP.setBorder(BorderFactory.createLineBorder(new Color(204,
				204, 204)));
	}// GEN-LAST:event_customizeSMTPMouseReleased

	private void lblSigninMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblSigninMouseClicked
		txtEmail.setBorder(defaultHighlightBorder);
		txtPassword.setBorder(defaultHighlightBorder);

		char[] temp = txtPassword.getPassword();
		String password = "", returnMsg;
		for (int i = 0; i < temp.length; i++)
			password += temp[i];

		if (comboEmail.getSelectedIndex() != 0) {
			if (!txtEmail.getText()
					.equals("Please enter your email address...")
					&& !txtEmail.getText().equals("")) {
				if (!password.equals("sickcoderoverhere")
						&& !password.equals("")) {
					MyProperties props = new MyProperties(txtEmail.getText(),
							password,
							((String) comboEmail.getSelectedItem())
									.toLowerCase());
					Email test = new Email(props.getProperties());
					if ((returnMsg = test.authenUser()).equals("")) {
						gradebook.setProperties(props.getProperties());
						lblEmailVarify.setIcon(new javax.swing.ImageIcon(
								getClass().getResource(
										"/cs2212/team4/emailTrue.png")));
						lblEMLErrorLog.setForeground(Color.green);
						lblEMLErrorLog.setText("Connected");
					} else {
						lblEMLErrorLog.setForeground(Color.red);
						lblEMLErrorLog
								.setText("Incorrect information, please try again!");
					}
				} else {
					lblEMLErrorLog.setForeground(Color.red);
					lblEMLErrorLog.setText("Please enter your password");
					txtPassword.setBorder(errorHighlightBorder);
				}
			} else {
				lblEMLErrorLog.setForeground(Color.red);
				lblEMLErrorLog.setText("Please enter an email address");
				txtEmail.setBorder(errorHighlightBorder);
			}
		} else {
			lblEMLErrorLog.setForeground(Color.red);
			lblEMLErrorLog.setText("Please select your email providor");
		}
	}// GEN-LAST:event_lblSigninMouseClicked

	private void lblSigninMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblSigninMouseEntered
		lblSignin.setBorder(BorderFactory.createLineBorder(new Color(20, 150,
				250)));
	}// GEN-LAST:event_lblSigninMouseEntered

	private void lblSigninMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblSigninMouseExited
		lblSignin.setBorder(BorderFactory.createLineBorder(new Color(204, 204,
				204)));
	}// GEN-LAST:event_lblSigninMouseExited

	private void lblSigninMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblSigninMousePressed
		lblSignin.setBorder(BorderFactory.createBevelBorder(1, new Color(20,
				150, 250), new Color(20, 150, 250), new Color(20, 150, 250),
				new Color(20, 150, 250)));
	}// GEN-LAST:event_lblSigninMousePressed

	private void lblSigninMouseReleased(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblSigninMouseReleased
		lblSignin.setBorder(BorderFactory.createLineBorder(new Color(204, 204,
				204)));
	}// GEN-LAST:event_lblSigninMouseReleased

	private void lblWesternMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblWesternMouseClicked
		if (currCourse != null) {
			getContentPane().setBackground(lblWestern.getBackground());
			currCourse.setColor(lblWestern.getBackground());
		} else
			lblSetupErrorLog.setText("Please select a course");
	}// GEN-LAST:event_lblWesternMouseClicked

	private void lblBlueMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblBlueMouseClicked
		if (currCourse != null) {
			getContentPane().setBackground(lblBlue.getBackground());
			currCourse.setColor(lblBlue.getBackground());
		} else
			lblSetupErrorLog.setText("Please select a course");
	}// GEN-LAST:event_lblBlueMouseClicked

	private void lblTealMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblTealMouseClicked
		if (currCourse != null) {
			getContentPane().setBackground(lblTeal.getBackground());
			currCourse.setColor(lblTeal.getBackground());
		} else
			lblSetupErrorLog.setText("Please select a course");
	}// GEN-LAST:event_lblTealMouseClicked

	private void lblCobaltMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblCobaltMouseClicked
		if (currCourse != null) {
			getContentPane().setBackground(lblCobalt.getBackground());
			currCourse.setColor(lblCobalt.getBackground());
		} else
			lblSetupErrorLog.setText("Please select a course");
	}// GEN-LAST:event_lblCobaltMouseClicked

	private void lblVioletMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblVioletMouseClicked
		if (currCourse != null) {
			getContentPane().setBackground(lblViolet.getBackground());
			currCourse.setColor(lblViolet.getBackground());
		} else
			lblSetupErrorLog.setText("Please select a course");
	}// GEN-LAST:event_lblVioletMouseClicked

	private void lblMagentaMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblMagentaMouseClicked
		if (currCourse != null) {
			getContentPane().setBackground(lblMagenta.getBackground());
			currCourse.setColor(lblMagenta.getBackground());
		} else
			lblSetupErrorLog.setText("Please select a course");
	}// GEN-LAST:event_lblMagentaMouseClicked

	private void lblOliveMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblOliveMouseClicked
		if (currCourse != null) {
			getContentPane().setBackground(lblOlive.getBackground());
			currCourse.setColor(lblOlive.getBackground());
		} else
			lblSetupErrorLog.setText("Please select a course");
	}// GEN-LAST:event_lblOliveMouseClicked

	private void lblMauveMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblMauveMouseClicked
		if (currCourse != null) {
			getContentPane().setBackground(lblMauve.getBackground());
			currCourse.setColor(lblMauve.getBackground());
		} else
			lblSetupErrorLog.setText("Please select a course");
	}// GEN-LAST:event_lblMauveMouseClicked

	private void lblSteelMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblSteelMouseClicked
		if (currCourse != null) {
			getContentPane().setBackground(lblSteel.getBackground());
			currCourse.setColor(lblSteel.getBackground());
		} else
			lblSetupErrorLog.setText("Please select a course");
	}// GEN-LAST:event_lblSteelMouseClicked

	private void lblCalculateMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblCalculateMouseClicked
		try {
			double avg;
			avg = Double.parseDouble(txtAvg.getText());
			if (avg > -1 && avg < 101) {
				MyAvg myAvg = new MyAvg(currCourse,
						currCourse.getStudent(studentTable.getSelectedRow()));
				lblResult.setForeground(Color.black);
				if (myAvg.calcAvg(avg) > 100 || myAvg.calcAvg(avg) < 0)
					lblResult
							.setText(String
									.format("This average cannot be obtained, required average, %.2f",
											myAvg.calcAvg(avg))
									+ "%.");
				else
					lblResult.setText(String.format(
							"The student needs to obtaine an average of %.2f",
							myAvg.calcAvg(avg))
							+ "% for the rest of the course to recieve a "
							+ txtAvg.getText() + "%");
			} else {
				lblResult.setForeground(Color.red);
				lblResult.setText("Please enter a number between 0-100");
			}
		} catch (NumberFormatException e) {
			lblResult.setForeground(Color.red);
			lblResult.setText("Please enter a valid number.");
		}
	}// GEN-LAST:event_lblCalculateMouseClicked

	private void lblCalculateMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblCalculateMouseEntered
		lblCalculate.setBorder(BorderFactory.createLineBorder(new Color(20,
				150, 250)));
	}// GEN-LAST:event_lblCalculateMouseEntered

	private void lblCalculateMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblCalculateMouseExited
		lblCalculate.setBorder(BorderFactory.createLineBorder(new Color(204,
				204, 204)));
	}// GEN-LAST:event_lblCalculateMouseExited

	private void lblCalculateMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblCalculateMousePressed
		lblCalculate.setBorder(BorderFactory.createBevelBorder(1, new Color(20,
				150, 250), new Color(20, 150, 250), new Color(20, 150, 250),
				new Color(20, 150, 250)));
	}// GEN-LAST:event_lblCalculateMousePressed

	private void lblCalculateMouseReleased(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblCalculateMouseReleased
		lblCalculate.setBorder(BorderFactory.createLineBorder(new Color(204,
				204, 204)));
	}// GEN-LAST:event_lblCalculateMouseReleased

	private void txtAvgFocusGained(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txtAvgFocusGained
		if (txtAvg.getText().equals("Please enter a number between 0-100"))
			txtAvg.selectAll();
		txtAvg.setForeground(Color.black);
	}// GEN-LAST:event_txtAvgFocusGained

	private void txtAvgFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txtAvgFocusLost
		if (txtAvg.getText().equals("Please enter a number between 0-100"))
			txtAvg.setForeground(Color.lightGray);
	}// GEN-LAST:event_txtAvgFocusLost

	boolean butToggle = false;

	private void lblToggleMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblToggleMouseClicked
		if (butToggle) {
			butToggle = false;
		} else {
			butToggle = true;
		}

	}// GEN-LAST:event_lblToggleMouseClicked

	private void lblToggleMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblToggleMouseEntered
		if (!butToggle)
			lblToggle.setBorder(BorderFactory.createLineBorder(new Color(20,
					150, 250)));
	}// GEN-LAST:event_lblToggleMouseEntered

	private void lblToggleMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblToggleMouseExited
		if (!butToggle)
			lblToggle.setBorder(BorderFactory.createLineBorder(new Color(204,
					204, 204)));
	}// GEN-LAST:event_lblToggleMouseExited

	private void lblToggleMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblToggleMousePressed
		if (!butToggle)
			lblToggle.setBorder(BorderFactory.createBevelBorder(1, new Color(
					20, 150, 250), new Color(20, 150, 250), new Color(20, 150,
					250), new Color(20, 150, 250)));
		else
			lblToggle.setBorder(BorderFactory.createBevelBorder(1, new Color(
					204, 204, 204), new Color(204, 204, 204), new Color(204,
					204, 204), new Color(204, 204, 204)));
	}// GEN-LAST:event_lblToggleMousePressed

	private void lblToggleMouseReleased(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblToggleMouseReleased
		if (!butToggle)
			lblToggle.setBorder(BorderFactory.createBevelBorder(1, new Color(
					20, 150, 250), new Color(20, 150, 250), new Color(20, 150,
					250), new Color(20, 150, 250)));
		else
			lblToggle.setBorder(BorderFactory.createBevelBorder(1, new Color(
					204, 204, 204), new Color(204, 204, 204), new Color(204,
					204, 204), new Color(204, 204, 204)));
	}// GEN-LAST:event_lblToggleMouseReleased

	private void txtSubjectFocusGained(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txtSubjectFocusGained
		if (txtSubject.getText().equals("Please enter an email subject...")) {
			txtSubject.selectAll();
			txtSubject.setForeground(Color.black);
		}
	}// GEN-LAST:event_txtSubjectFocusGained

	private void txtSubjectFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txtSubjectFocusLost
		if (txtSubject.getText().equals("Please enter an email subject...")
				|| txtSubject.getText().equals("")) {
			txtSubject.setText("Please enter an email subject...");
			txtSubject.setForeground(Color.LIGHT_GRAY);
		}
	}// GEN-LAST:event_txtSubjectFocusLost

	private void msgTextFocusGained(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_msgTextFocusGained
		if (msgText.getText().equals("Please enter a massage content...")) {
			msgText.selectAll();
			msgText.setForeground(Color.black);
		}
	}// GEN-LAST:event_msgTextFocusGained

	private void msgTextFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_msgTextFocusLost
		if (msgText.getText().equals("Please enter a massage content...")
				|| msgText.getText().equals("")) {
			msgText.setText("Please enter a massage content...");
			msgText.setForeground(Color.LIGHT_GRAY);
		}
	}// GEN-LAST:event_msgTextFocusLost

	private void lblSendEmailMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblSendEmailMouseClicked
		Student student;
		String returnMsg;
		int[] indecies = studentTable.getSelectedRows();
		if (!txtSubject.getText().equals("Please enter an email subject...")) {
			if (!msgText.getText().equals("Please enter a massage content...")) {
				for (int i = 0; i < indecies.length; i++) {
					if (indecies[i] > -1
							&& (student = currCourse.getStudent(indecies[i])) != null) {

						Email email = new Email(currCourse, student,
								txtSubject.getText(), msgText.getText(),
								butToggle, gradebook.getProperties());
						returnMsg = email.sendEmail();
						if (!returnMsg.equals("")) {
							lblEmailErrorLog.setForeground(Color.red);
							lblEmailErrorLog.setText(returnMsg);
						} else {
							lblEmailErrorLog.setForeground(Color.green);
							lblGradesErrorLog
									.setText("The emails have been successfully delivered!");
						}
					}
				}
				closeFrame(emailFrame);
				restPnlEmail();
			} else {
				lblEmailErrorLog.setForeground(Color.red);
				lblEmailErrorLog.setText("Please enter a massage content...");
			}
		} else {
			lblEmailErrorLog.setForeground(Color.red);
			lblEmailErrorLog.setText("Please enter an email subject...");
		}
	}// GEN-LAST:event_lblSendEmailMouseClicked

	private void lblSendEmailMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblSendEmailMouseEntered
		if (!txtSubject.getText().equals("Please enter an email subject...")
				&& !msgText.getText().equals(
						"Please enter a massage content..."))
			lblSendEmail.setBorder(BorderFactory.createLineBorder(new Color(51,
					255, 51)));
		else
			lblSendEmail.setBorder(BorderFactory.createLineBorder(new Color(
					255, 51, 51)));
	}// GEN-LAST:event_lblSendEmailMouseEntered

	private void lblSendEmailMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblSendEmailMouseExited
		lblSendEmail.setBorder(BorderFactory.createLineBorder(new Color(204,
				204, 204)));
	}// GEN-LAST:event_lblSendEmailMouseExited

	private void lblSendEmailMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblSendEmailMousePressed
		if (!txtSubject.getText().equals("Please enter an email subject...")
				&& !msgText.getText().equals(
						"Please enter a massage content..."))
			lblSendEmail.setBorder(BorderFactory.createBevelBorder(1,
					new Color(51, 255, 51), new Color(51, 255, 51), new Color(
							51, 255, 51), new Color(51, 255, 51)));
		else
			lblSendEmail.setBorder(BorderFactory.createBevelBorder(1,
					new Color(255, 51, 51), new Color(255, 51, 51), new Color(
							255, 51, 51), new Color(255, 51, 51)));
	}// GEN-LAST:event_lblSendEmailMousePressed

	private void lblSendEmailMouseReleased(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblSendEmailMouseReleased
		lblSendEmail.setBorder(BorderFactory.createBevelBorder(1, new Color(
				204, 204, 204), new Color(204, 204, 204), new Color(204, 204,
				204), new Color(204, 204, 204)));
	}// GEN-LAST:event_lblSendEmailMouseReleased

	private void lblEmailStudentsMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblEmailStudentsMouseClicked
		if (currCourse != null)
			if (gradebook.getProperties() != null) {
				if (studentTable.getSelectedRow() != -1
						&& currCourse.getStudent(studentTable.getSelectedRow()) != null) {
					String str = "";
					int[] indecies = studentTable.getSelectedRows();
					Student student;
					for (int i = 0; i < indecies.length; i++) {
						if (indecies[i] > -1
								&& (student = currCourse
										.getStudent(indecies[i])) != null) {
							str += student.getNameFirst() + " "
									+ student.getNameLast() + ", ";
						}
					}
					str = str.substring(0, str.length() - 2);

					emailFrame.add(pnlEmail);
					pnlEmail.setVisible(true);
					lblRecipients.setText(str);
					emailFrame.setVisible(true);
					disable();
					lblGradesErrorLog.setText("");
				} else {
					lblGradesErrorLog.setForeground(Color.red);
					lblGradesErrorLog.setText("Please select a student");
				}
			} else {
				lblGradesErrorLog.setForeground(Color.red);
				lblGradesErrorLog
						.setText("Please set the email properties in the Settings tab");
			}
		else {
			lblGradesErrorLog.setForeground(Color.red);
			lblGradesErrorLog.setText("Please select a course");
		}
	}// GEN-LAST:event_lblEmailStudentsMouseClicked

	private void lblEmailStudentsMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblEmailStudentsMouseEntered
		if (currCourse != null && studentTable.getSelectedRow() != -1
				&& currCourse.getStudent(studentTable.getSelectedRow()) != null)
			lblEmailStudents.setBorder(BorderFactory
					.createLineBorder(new Color(20, 150, 250)));
		else
			lblEmailStudents.setBorder(BorderFactory
					.createLineBorder(new Color(204, 204, 204)));
	}// GEN-LAST:event_lblEmailStudentsMouseEntered

	private void lblEmailStudentsMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblEmailStudentsMouseExited
		lblEmailStudents.setBorder(BorderFactory.createLineBorder(new Color(
				204, 204, 204)));
	}// GEN-LAST:event_lblEmailStudentsMouseExited

	private void lblEmailStudentsMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblEmailStudentsMousePressed
		if (currCourse != null && studentTable.getSelectedRow() != -1
				&& currCourse.getStudent(studentTable.getSelectedRow()) != null)
			lblEmailStudents.setBorder(BorderFactory.createBevelBorder(1,
					new Color(20, 150, 250), new Color(20, 150, 250),
					new Color(20, 150, 250), new Color(20, 150, 250)));
		else
			lblEmailStudents.setBorder(BorderFactory.createBevelBorder(1,
					new Color(204, 204, 204), new Color(204, 204, 204),
					new Color(204, 204, 204), new Color(204, 204, 204)));
	}// GEN-LAST:event_lblEmailStudentsMousePressed

	private void lblEmailStudentsMouseReleased(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblEmailStudentsMouseReleased
		if (currCourse != null && studentTable.getSelectedRow() != -1
				&& currCourse.getStudent(studentTable.getSelectedRow()) != null)
			lblEmailStudents.setBorder(BorderFactory
					.createLineBorder(new Color(20, 150, 250)));
		else
			lblEmailStudents.setBorder(BorderFactory
					.createLineBorder(new Color(204, 204, 204)));
	}// GEN-LAST:event_lblEmailStudentsMouseReleased

	private void lblCalculatorMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblCalculatorMouseClicked
		if (currCourse != null)
			if (studentTable.getSelectedRows().length <= 1) {
				if (studentTable.getSelectedRow() != -1
						&& currCourse.getStudent(studentTable.getSelectedRow()) != null) {
					calcFrame.add(pnlCalc);
					pnlCalc.setVisible(true);
					calcFrame.setVisible(true);
					disable();
					lblGradesErrorLog.setText("");
				} else {
					lblGradesErrorLog.setForeground(Color.red);
					lblGradesErrorLog.setText("Please select a student");
				}
			} else {
				lblGradesErrorLog.setForeground(Color.red);
				lblGradesErrorLog.setText("Please select one student only");
			}
		else {
			lblGradesErrorLog.setForeground(Color.red);
			lblGradesErrorLog.setText("Please select a course");
		}
	}// GEN-LAST:event_lblCalculatorMouseClicked

	private void lblCalculatorMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblCalculatorMouseEntered
		if (currCourse != null && studentTable.getSelectedRow() != -1
				&& currCourse.getStudent(studentTable.getSelectedRow()) != null)
			lblCalculator.setBorder(BorderFactory.createLineBorder(new Color(
					20, 150, 250)));
		else
			lblCalculator.setBorder(BorderFactory.createLineBorder(new Color(
					204, 204, 204)));
	}// GEN-LAST:event_lblCalculatorMouseEntered

	private void lblCalculatorMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblCalculatorMouseExited
		lblCalculator.setBorder(BorderFactory.createLineBorder(new Color(204,
				204, 204)));
	}// GEN-LAST:event_lblCalculatorMouseExited

	private void lblCalculatorMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblCalculatorMousePressed
		if (currCourse != null && studentTable.getSelectedRow() != -1
				&& currCourse.getStudent(studentTable.getSelectedRow()) != null)
			lblCalculator.setBorder(BorderFactory.createBevelBorder(1,
					new Color(20, 150, 250), new Color(20, 150, 250),
					new Color(20, 150, 250), new Color(20, 150, 250)));
		else
			lblCalculator.setBorder(BorderFactory.createBevelBorder(1,
					new Color(204, 204, 204), new Color(204, 204, 204),
					new Color(204, 204, 204), new Color(204, 204, 204)));
	}// GEN-LAST:event_lblCalculatorMousePressed

	private void lblCalculatorMouseReleased(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblCalculatorMouseReleased
		if (currCourse != null && studentTable.getSelectedRow() != -1
				&& currCourse.getStudent(studentTable.getSelectedRow()) != null)
			lblCalculator.setBorder(BorderFactory.createLineBorder(new Color(
					20, 150, 250)));
		else
			lblCalculator.setBorder(BorderFactory.createLineBorder(new Color(
					204, 204, 204)));
	}// GEN-LAST:event_lblCalculatorMouseReleased

	private void lblActiveCourseMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblActiveCourseMouseClicked
		if (!pnlCourseMenu.isVisible())
			pnlCourseMenu.setVisible(true);
		else
			pnlCourseMenu.setVisible(false);
	}// GEN-LAST:event_lblActiveCourseMouseClicked

	/****************************************************************************************************
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 * Start: CUSTOM CODE
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 */

	/****************************************************************************************************
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 * Start: Grades Tab
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 */
	/****************************************************************************************************
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 * 
	 * Start: Deliverable Side bar
	 * 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 */
	private void editCourseIconMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_editCourseIconMouseClicked
		tabSwitch(1);
	}// GEN-LAST:event_editCourseIconMouseClicked

	private void addDeliverMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_addDeliverMouseClicked
		hideMenu();
		if (currCourse != null) {
			restPnlCourse();
			restPnlDeliver();
			restPnlStudent();

			addFrame.add(pnlAddDeliver);
			pnlAddDeliver.setVisible(true);
			addFrame.setVisible(true);
			disable();
		} else
			lblGradesErrorLog.setText("Please select a course");
	}// GEN-LAST:event_addDeliverMouseClicked

	private void addDeliverMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_addDeliverMouseEntered
		if (currCourse != null)
			addDeliver.setBorder(BorderFactory.createLineBorder(new Color(51,
					255, 51)));
		else
			addDeliver.setBorder(BorderFactory.createLineBorder(new Color(204,
					204, 204)));
	}// GEN-LAST:event_addDeliverMouseEntered

	private void addDeliverMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_addDeliverMouseExited
		addDeliver.setBorder(BorderFactory.createLineBorder(new Color(204, 204,
				204)));
	}// GEN-LAST:event_addDeliverMouseExited

	private void addDeliverMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_addDeliverMousePressed
		if (currCourse != null)
			addDeliver.setBorder(BorderFactory.createBevelBorder(1, new Color(
					51, 255, 51), new Color(51, 255, 51),
					new Color(51, 255, 51), new Color(51, 255, 51)));
		else
			addDeliver.setBorder(BorderFactory.createBevelBorder(1, new Color(
					204, 204, 204), new Color(204, 204, 204), new Color(204,
					204, 204), new Color(204, 204, 204)));
	}// GEN-LAST:event_addDeliverMousePressed

	private void deleteDeliverMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_deleteDeliverMouseClicked
		String del;
		if (currCourse != null) {
			if (deliverList.getSelectedIndex() > -1) {
				int[] selectedRows = deliverList.getSelectedIndices();
				String SelectedDelivers = "";
				for (int i = 0; i < selectedRows.length; i++) {
					del = listDelivers.getElementAt(selectedRows[i]);
					SelectedDelivers += currCourse.getDeliverable(
							findDeliver(del)).getName()
							+ "\n";
				}
				int j = JOptionPane.showConfirmDialog(this,
						"Are you sure you want to delete\n" + SelectedDelivers,
						"Deliverable Deletion Confirmation",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (j == 0) {
					for (int i = 0; i < selectedRows.length; i++) {
						del = listDelivers.getElementAt(selectedRows[i]);
						currCourse.removeDeliverable(findDeliver(del));
					}
					updateInfo();
				}
			} else
				lblGradesErrorLog.setText("Please select a deliverable");
		} else
			lblGradesErrorLog.setText("Please select a course");
	}// GEN-LAST:event_deleteDeliverMouseClicked

	private void deleteDeliverMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_deleteDeliverMouseEntered
		if (currCourse != null)
			deleteDeliver.setBorder(BorderFactory.createLineBorder(new Color(
					255, 51, 51)));
		else
			deleteDeliver.setBorder(BorderFactory.createLineBorder(new Color(
					204, 204, 204)));
	}// GEN-LAST:event_deleteDeliverMouseEntered

	private void deleteDeliverMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_deleteDeliverMouseExited
		deleteDeliver.setBorder(BorderFactory.createLineBorder(new Color(204,
				204, 204)));
	}// GEN-LAST:event_deleteDeliverMouseExited

	private void deleteDeliverMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_deleteDeliverMousePressed
		if (currCourse != null)
			deleteDeliver.setBorder(BorderFactory.createBevelBorder(1,
					new Color(255, 51, 51), new Color(255, 51, 51), new Color(
							255, 51, 51), new Color(255, 51, 51)));
		else
			deleteDeliver.setBorder(BorderFactory.createBevelBorder(1,
					new Color(204, 204, 204), new Color(204, 204, 204),
					new Color(204, 204, 204), new Color(204, 204, 204)));
	}// GEN-LAST:event_deleteDeliverMousePressed

	/****************************************************************************************************
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 * 
	 * End: Deliverable Side bar
	 * 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 */

	/****************************************************************************************************
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 * 
	 * Start: Tables
	 * 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 */
	int firstName = 1, lastName = 1, number = 1, email = 1, asnAvg = 1,
			exmAvg = 1;

	private void deliverListValueChanged(
			javax.swing.event.ListSelectionEvent evt) {// GEN-FIRST:event_deliverListValueChanged

	}// GEN-LAST:event_deliverListValueChanged

	private void studentTableMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_studentTableMouseClicked
		hideMenu();
	}// GEN-LAST:event_studentTableMouseClicked

	private void gradesTableMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_gradesTableMouseClicked
		hideMenu();
	}// GEN-LAST:event_gradesTableMouseClicked

	private void lblFirstNameMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblFirstNameMouseClicked
		if (currCourse != null) {
			if (firstName == 1)
				firstName = 0;
			else
				firstName = 1;
			updateTables();
		}
	}// GEN-LAST:event_lblFirstNameMouseClicked

	Color grey = new Color(204, 204, 204);

	private void lblFirstNameMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblFirstNameMouseEntered
		if (currCourse != null)
			if (firstName == 1) {
				lblFirstName.setBorder(BorderFactory
						.createLineBorder(new Color(20, 150, 250)));
				lblFirstName.setText("Hide");
			} else {
				lblFirstName.setText("Show");
				lblFirstName.setBackground(grey);
			}
		else {
			lblFirstName.setBorder(BorderFactory.createLineBorder(new Color(
					204, 204, 204)));
		}
	}// GEN-LAST:event_lblFirstNameMouseEntered

	private void lblFirstNameMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblFirstNameMouseExited
		if (firstName != 1)
			lblFirstName.setBorder(BorderFactory.createLineBorder(new Color(
					204, 204, 204)));
		lblFirstName.setText("First Name");
	}// GEN-LAST:event_lblFirstNameMouseExited

	private void lblFirstNameMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblFirstNameMousePressed
		if (currCourse != null)
			lblFirstName.setBorder(BorderFactory.createBevelBorder(0,
					new Color(20, 150, 250), new Color(20, 150, 250),
					new Color(20, 150, 250), new Color(20, 150, 250)));
		else
			lblFirstName.setBorder(BorderFactory.createBevelBorder(0,
					new Color(204, 204, 204), new Color(204, 204, 204),
					new Color(204, 204, 204), new Color(204, 204, 204)));

	}// GEN-LAST:event_lblFirstNameMousePressed

	private void lblFirstNameMouseReleased(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblFirstNameMouseReleased
		if (currCourse != null)
			lblFirstName.setBorder(BorderFactory.createLineBorder(new Color(20,
					150, 250)));
		else
			lblFirstName.setBorder(BorderFactory.createLineBorder(new Color(
					204, 204, 204)));
	}// GEN-LAST:event_lblFirstNameMouseReleased

	private void lblLastNameMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblLastNameMouseClicked
		if (currCourse != null) {
			if (lastName == 1)
				lastName = 0;
			else
				lastName = 1;
			updateTables();
		}
	}// GEN-LAST:event_lblLastNameMouseClicked

	private void lblLastNameMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblLastNameMouseEntered
		if (currCourse != null)
			if (lastName == 1) {
				lblLastName.setBorder(BorderFactory.createLineBorder(new Color(
						20, 150, 250)));
				lblLastName.setText("Hide");
			} else
				lblLastName.setText("Show");
		else
			lblLastName.setBorder(BorderFactory.createLineBorder(new Color(204,
					204, 204)));
	}// GEN-LAST:event_lblLastNameMouseEntered

	private void lblLastNameMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblLastNameMouseExited
		if (lastName != 1)
			lblLastName.setBorder(BorderFactory.createLineBorder(new Color(204,
					204, 204)));
		lblLastName.setText("Last Name");
	}// GEN-LAST:event_lblLastNameMouseExited

	private void lblLastNameMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblLastNameMousePressed
		if (currCourse != null)
			lblLastName.setBorder(BorderFactory.createBevelBorder(0, new Color(
					20, 150, 250), new Color(20, 150, 250), new Color(20, 150,
					250), new Color(20, 150, 250)));
		else
			lblLastName.setBorder(BorderFactory.createBevelBorder(0, new Color(
					204, 204, 204), new Color(204, 204, 204), new Color(204,
					204, 204), new Color(204, 204, 204)));
	}// GEN-LAST:event_lblLastNameMousePressed

	private void lblLastNameMouseReleased(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblLastNameMouseReleased
		if (currCourse != null)
			lblLastName.setBorder(BorderFactory.createLineBorder(new Color(20,
					150, 250)));
		else
			lblLastName.setBorder(BorderFactory.createLineBorder(new Color(204,
					204, 204)));
	}// GEN-LAST:event_lblLastNameMouseReleased

	private void lblNumberMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblNumberMouseClicked
		if (currCourse != null) {
			if (number == 1)
				number = 0;
			else
				number = 1;
			updateTables();
		}
	}// GEN-LAST:event_lblNumberMouseClicked

	private void lblNumberMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblNumberMouseEntered
		if (currCourse != null)
			if (number == 1) {
				lblNumber.setBorder(BorderFactory.createLineBorder(new Color(
						20, 150, 250)));
				lblNumber.setText("Hide");
			} else
				lblNumber.setText("Show");
		else
			lblNumber.setBorder(BorderFactory.createLineBorder(new Color(204,
					204, 204)));
	}// GEN-LAST:event_lblNumberMouseEntered

	private void lblNumberMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblNumberMouseExited
		if (number != 1)
			lblNumber.setBorder(BorderFactory.createLineBorder(new Color(204,
					204, 204)));
		lblNumber.setText("Number");
	}// GEN-LAST:event_lblNumberMouseExited

	private void lblNumberMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblNumberMousePressed
		if (currCourse != null)
			lblNumber.setBorder(BorderFactory.createBevelBorder(0, new Color(
					20, 150, 250), new Color(20, 150, 250), new Color(20, 150,
					250), new Color(20, 150, 250)));
		else
			lblNumber.setBorder(BorderFactory.createBevelBorder(0, new Color(
					204, 204, 204), new Color(204, 204, 204), new Color(204,
					204, 204), new Color(204, 204, 204)));
	}// GEN-LAST:event_lblNumberMousePressed

	private void lblNumberMouseReleased(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblNumberMouseReleased
		if (currCourse != null)
			lblNumber.setBorder(BorderFactory.createLineBorder(new Color(20,
					150, 250)));
		else
			lblNumber.setBorder(BorderFactory.createLineBorder(new Color(204,
					204, 204)));
	}// GEN-LAST:event_lblNumberMouseReleased

	private void lblEmailMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblEmailMouseClicked
		if (currCourse != null) {
			if (email == 1)
				email = 0;
			else
				email = 1;
			updateTables();
		}
	}// GEN-LAST:event_lblEmailMouseClicked

	private void lblEmailMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblEmailMouseEntered
		if (currCourse != null)
			if (email == 1) {
				lblEmail.setBorder(BorderFactory.createLineBorder(new Color(20,
						150, 250)));
				lblEmail.setText("Hide");
			} else
				lblEmail.setText("Show");
		else
			lblEmail.setBorder(BorderFactory.createLineBorder(new Color(204,
					204, 204)));
	}// GEN-LAST:event_lblEmailMouseEntered

	private void lblEmailMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblEmailMouseExited
		if (email != 1)
			lblEmail.setBorder(BorderFactory.createLineBorder(new Color(204,
					204, 204)));
		lblEmail.setText("Email");
	}// GEN-LAST:event_lblEmailMouseExited

	private void lblEmailMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblEmailMousePressed
		if (currCourse != null)
			lblEmail.setBorder(BorderFactory.createBevelBorder(0, new Color(20,
					150, 250), new Color(20, 150, 250),
					new Color(20, 150, 250), new Color(20, 150, 250)));
		else
			lblEmail.setBorder(BorderFactory.createBevelBorder(0, new Color(
					204, 204, 204), new Color(204, 204, 204), new Color(204,
					204, 204), new Color(204, 204, 204)));
	}// GEN-LAST:event_lblEmailMousePressed

	private void lblEmailMouseReleased(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblEmailMouseReleased
		if (currCourse != null)
			lblEmail.setBorder(BorderFactory.createLineBorder(new Color(20,
					150, 250)));
		else
			lblEmail.setBorder(BorderFactory.createLineBorder(new Color(204,
					204, 204)));
	}// GEN-LAST:event_lblEmailMouseReleased

	private void lblExmAvgMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblExmAvgMouseClicked
		if (currCourse != null) {
			if (exmAvg == 1)
				exmAvg = 0;
			else
				exmAvg = 1;
			updateTables();
		}
	}// GEN-LAST:event_lblExmAvgMouseClicked

	private void lblExmAvgMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblExmAvgMouseEntered
		if (currCourse != null)
			if (exmAvg == 1) {
				lblExmAvg.setBorder(BorderFactory.createLineBorder(new Color(
						20, 150, 250)));
				lblExmAvg.setText("Hide");
			} else
				lblExmAvg.setText("Show");
		else
			lblExmAvg.setBorder(BorderFactory.createLineBorder(new Color(204,
					204, 204)));

	}// GEN-LAST:event_lblExmAvgMouseEntered

	private void lblExmAvgMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblExmAvgMouseExited
		if (exmAvg != 1)
			lblExmAvg.setBorder(BorderFactory.createLineBorder(new Color(204,
					204, 204)));
		lblExmAvg.setText("Exam Avg");
	}// GEN-LAST:event_lblExmAvgMouseExited

	private void lblExmAvgMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblExmAvgMousePressed
		if (currCourse != null)
			lblExmAvg.setBorder(BorderFactory.createBevelBorder(0, new Color(
					20, 150, 250), new Color(20, 150, 250), new Color(20, 150,
					250), new Color(20, 150, 250)));
		else
			lblExmAvg.setBorder(BorderFactory.createBevelBorder(0, new Color(
					204, 204, 204), new Color(204, 204, 204), new Color(204,
					204, 204), new Color(204, 204, 204)));
	}// GEN-LAST:event_lblExmAvgMousePressed

	private void lblExmAvgMouseReleased(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblExmAvgMouseReleased
		if (currCourse != null)
			lblExmAvg.setBorder(BorderFactory.createLineBorder(new Color(20,
					150, 250)));
		else
			lblExmAvg.setBorder(BorderFactory.createLineBorder(new Color(204,
					204, 204)));
	}// GEN-LAST:event_lblExmAvgMouseReleased

	private void lblAsnAvgMouseClicked(java.awt.event.MouseEvent evt) {
		if (currCourse != null) {
			if (asnAvg == 1)
				asnAvg = 0;
			else
				asnAvg = 1;
			updateTables();
		}
	}

	private void lblAsnAvgMouseEntered(java.awt.event.MouseEvent evt) {
		if (currCourse != null)
			if (asnAvg == 1) {
				lblAsnAvg.setBorder(BorderFactory.createLineBorder(new Color(
						20, 150, 250)));
				lblAsnAvg.setText("Hide");
			} else
				lblAsnAvg.setText("Show");
		else
			lblAsnAvg.setBorder(BorderFactory.createLineBorder(new Color(204,
					204, 204)));

	}

	private void lblAsnAvgMouseExited(java.awt.event.MouseEvent evt) {
		if (asnAvg != 1)
			lblAsnAvg.setBorder(BorderFactory.createLineBorder(new Color(204,
					204, 204)));
		lblAsnAvg.setText("Asn Avg");
	}

	private void lblAsnAvgMousePressed(java.awt.event.MouseEvent evt) {
		if (currCourse != null)
			lblAsnAvg.setBorder(BorderFactory.createBevelBorder(0, new Color(
					20, 150, 250), new Color(20, 150, 250), new Color(20, 150,
					250), new Color(20, 150, 250)));
		else
			lblAsnAvg.setBorder(BorderFactory.createBevelBorder(0, new Color(
					204, 204, 204), new Color(204, 204, 204), new Color(204,
					204, 204), new Color(204, 204, 204)));
	}

	private void lblAsnAvgMouseReleased(java.awt.event.MouseEvent evt) {
		if (currCourse != null)
			lblAsnAvg.setBorder(BorderFactory.createLineBorder(new Color(20,
					150, 250)));
		else
			lblAsnAvg.setBorder(BorderFactory.createLineBorder(new Color(204,
					204, 204)));
	}

	private void addStudentMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_addStudentMouseClicked
		hideMenu();
		if (currCourse != null) {
			restPnlCourse();
			restPnlDeliver();
			restPnlStudent();
			addFrame.add(pnlAddStudent);
			pnlAddStudent.setVisible(true);
			addFrame.setVisible(true);
			disable();
		} else
			lblGradesErrorLog.setText("Please select a course");
	}// GEN-LAST:event_addStudentMouseClicked

	private void addStudentMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_addStudentMouseEntered
		if (currCourse != null)
			addStudent.setBorder(BorderFactory.createLineBorder(new Color(51,
					255, 51)));
		else
			addStudent.setBorder(BorderFactory.createLineBorder(new Color(204,
					204, 204)));
	}// GEN-LAST:event_addStudentMouseEntered

	private void addStudentMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_addStudentMouseExited
		addStudent.setBorder(BorderFactory.createLineBorder(new Color(204, 204,
				204)));
	}// GEN-LAST:event_addStudentMouseExited

	private void addStudentMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_addStudentMousePressed
		if (currCourse != null)
			addStudent.setBorder(BorderFactory.createBevelBorder(1, new Color(
					51, 255, 51), new Color(51, 255, 51),
					new Color(51, 255, 51), new Color(51, 255, 51)));
		else
			addStudent.setBorder(BorderFactory.createBevelBorder(1, new Color(
					204, 204, 204), new Color(204, 204, 204), new Color(204,
					204, 204), new Color(204, 204, 204)));
	}// GEN-LAST:event_addStudentMousePressed

	private void deleteStudentMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_deleteStudentMouseClicked
		if (currCourse != null) {
			int[] selectedRows = gradesTable.getSelectedRows();
			String SelectedStudents = "";
			for (int i = 0; i < selectedRows.length; i++) {
				Student stud = tableStudents.getStudentNames().get(
						selectedRows[i]);
				if (stud != null)
					SelectedStudents += stud.getNameFirst() + ", "
							+ stud.getNameLast() + ", " + stud.getNumber()
							+ "\n";
			}
			int j = JOptionPane.showConfirmDialog(this,
					"Are you sure you want to delete\n" + SelectedStudents,
					"Student Deletion Confirmation", JOptionPane.YES_NO_OPTION,
					JOptionPane.WARNING_MESSAGE);
			if (j == 0) {
				for (int i = 0; i < selectedRows.length; i++) {
					Student stud = tableStudents.getStudentNames().get(
							selectedRows[i]);
					if (stud != null)
						currCourse.removeStudent(currCourse.findStudent(stud
								.getNumber()));
				}
				updateInfo();
			}
		} else
			lblGradesErrorLog.setText("Please select a course");
	}// GEN-LAST:event_deleteStudentMouseClicked

	private void deleteStudentMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_deleteStudentMouseEntered
		if (currCourse != null)
			if (studentTable.getSelectedRow() != -1
					&& currCourse.getStudent(studentTable.getSelectedRow()) != null)
				deleteStudent.setBorder(BorderFactory
						.createLineBorder(new Color(255, 51, 51)));
			else
				deleteStudent.setBorder(BorderFactory
						.createLineBorder(new Color(204, 204, 204)));
	}// GEN-LAST:event_deleteStudentMouseEntered

	private void deleteStudentMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_deleteStudentMouseExited
		deleteStudent.setBorder(BorderFactory.createLineBorder(new Color(204,
				204, 204)));
	}// GEN-LAST:event_deleteStudentMouseExited

	private void deleteStudentMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_deleteStudentMousePressed
		if (currCourse != null)
			if (studentTable.getSelectedRow() != -1
					&& currCourse.getStudent(studentTable.getSelectedRow()) != null)
				deleteStudent.setBorder(BorderFactory.createBevelBorder(1,
						new Color(255, 51, 51), new Color(255, 51, 51),
						new Color(255, 51, 51), new Color(255, 51, 51)));
			else
				deleteStudent.setBorder(BorderFactory.createBevelBorder(1,
						new Color(204, 204, 204), new Color(204, 204, 204),
						new Color(204, 204, 204), new Color(204, 204, 204)));
	}// GEN-LAST:event_deleteStudentMousePressed

	/****************************************************************************************************
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 * 
	 * End: Tables
	 * 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 */

	/****************************************************************************************************
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 * 
	 * Start: Imports/Exports
	 * 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 */

	private void lblImportGradesMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblImportGradesMouseClicked
		String returnMsg;
		if (currCourse != null) {
			final JFileChooser importGrades = new JFileChooser();
			int returnVal = importGrades.showOpenDialog(this);
			if (returnVal == 0) {
				if (!(returnMsg = currCourse.importGrades(importGrades
						.getSelectedFile())).equals("")) {
					if (returnMsg.startsWith("0")) {
						JOptionPane.showConfirmDialog(this,
								returnMsg.substring(1, returnMsg.length()),
								"Non-existing students",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.OK_OPTION);
					} else
						lblGradesErrorLog.setText(returnMsg);
				} else
					updateInfo();
			} else
				lblGradesErrorLog.setText("No file selected.");
		} else
			lblGradesErrorLog.setText("Please select a course");
	}// GEN-LAST:event_lblImportGradesMouseClicked

	private void lblImportGradesMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblImportGradesMouseEntered
		if (currCourse != null)
			lblImportGrades.setBorder(BorderFactory.createLineBorder(new Color(
					20, 150, 250)));
		else
			lblImportGrades.setBorder(BorderFactory.createLineBorder(new Color(
					204, 204, 204)));
	}// GEN-LAST:event_lblImportGradesMouseEntered

	private void lblImportGradesMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblImportGradesMouseExited
		lblImportGrades.setBorder(BorderFactory.createLineBorder(new Color(204,
				204, 204)));
	}// GEN-LAST:event_lblImportGradesMouseExited

	private void lblImportGradesMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblImportGradesMousePressed
		if (currCourse != null)
			lblImportGrades.setBorder(BorderFactory.createBevelBorder(1,
					new Color(20, 150, 250), new Color(20, 150, 250),
					new Color(20, 150, 250), new Color(20, 150, 250)));
		else {
			lblImportGrades.setBorder(BorderFactory.createBevelBorder(1,
					new Color(204, 204, 204), new Color(204, 204, 204),
					new Color(204, 204, 204), new Color(204, 204, 204)));
		}
	}// GEN-LAST:event_lblImportGradesMousePressed

	private void lblImportGradesMouseReleased(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblImportGradesMouseReleased
		if (currCourse != null)
			lblImportGrades.setBorder(BorderFactory.createLineBorder(new Color(
					20, 150, 250)));
		else
			lblImportGrades.setBorder(BorderFactory.createLineBorder(new Color(
					204, 204, 204)));
	}// GEN-LAST:event_lblImportGradesMouseReleased

	private void lblExportGradesMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblExportGradesMouseClicked
		if (currCourse != null) {
			final JFileChooser exportGrades = new JFileChooser();
			exportGrades.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int returnVal = exportGrades.showSaveDialog(this);
			String returnMsg;
			if (returnVal == 0) {
				File file = new File(exportGrades.getSelectedFile(),
						currCourse.getTitle() + currCourse.getCode()
								+ currCourse.getTerm() + "Grades.csv");
				if (!(returnMsg = currCourse.exportGrades(file)).equals(""))
					lblGradesErrorLog.setText(returnMsg);
				else
					updateInfo();
			} else
				lblGradesErrorLog.setText("Please select a valid directory.");
		} else
			lblGradesErrorLog.setText("Please select a course");

	}// GEN-LAST:event_lblExportGradesMouseClicked

	private void lblExportGradesMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblExportGradesMouseEntered
		if (currCourse != null)
			lblExportGrades.setBorder(BorderFactory.createLineBorder(new Color(
					20, 150, 250)));
		else
			lblExportGrades.setBorder(BorderFactory.createLineBorder(new Color(
					204, 204, 204)));
	}// GEN-LAST:event_lblExportGradesMouseEntered

	private void lblExportGradesMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblExportGradesMouseExited
		lblExportGrades.setBorder(BorderFactory.createLineBorder(new Color(204,
				204, 204)));
	}// GEN-LAST:event_lblExportGradesMouseExited

	private void lblExportGradesMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblExportGradesMousePressed
		if (currCourse != null)
			lblExportGrades.setBorder(BorderFactory.createBevelBorder(1,
					new Color(20, 150, 250), new Color(20, 150, 250),
					new Color(20, 150, 250), new Color(20, 150, 250)));
		else
			lblExportGrades.setBorder(BorderFactory.createBevelBorder(1,
					new Color(204, 204, 204), new Color(204, 204, 204),
					new Color(204, 204, 204), new Color(204, 204, 204)));

	}// GEN-LAST:event_lblExportGradesMousePressed

	private void lblExportGradesMouseReleased(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblExportGradesMouseReleased
		if (currCourse != null)
			lblExportGrades.setBorder(BorderFactory.createLineBorder(new Color(
					20, 150, 250)));
		else
			lblExportGrades.setBorder(BorderFactory.createLineBorder(new Color(
					204, 204, 204)));
	}// GEN-LAST:event_lblExportGradesMouseReleased

	private void lblImportStudentsMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblImportStudentsMouseClicked
		if (currCourse != null) {
			final JFileChooser importStudents = new JFileChooser();
			int returnVal = importStudents.showOpenDialog(this);
			String returnMsg;
			if (returnVal == 0) {
				if (!(returnMsg = currCourse.importStudents((importStudents
						.getSelectedFile()))).equals(""))
					lblGradesErrorLog.setText(returnMsg);
				else
					updateInfo();
			} else
				lblGradesErrorLog.setText("Please select a valid directory.");

		} else
			lblGradesErrorLog.setText("Please select a course");
	}// GEN-LAST:event_lblImportStudentsMouseClicked

	private void lblImportStudentsMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblImportStudentsMouseEntered
		if (currCourse != null)
			lblImportStudents.setBorder(BorderFactory
					.createLineBorder(new Color(20, 150, 250)));
		else
			lblImportStudents.setBorder(BorderFactory
					.createLineBorder(new Color(204, 204, 204)));
	}// GEN-LAST:event_lblImportStudentsMouseEntered

	private void lblImportStudentsMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblImportStudentsMouseExited
		lblImportStudents.setBorder(BorderFactory.createLineBorder(new Color(
				204, 204, 204)));
	}// GEN-LAST:event_lblImportStudentsMouseExited

	private void lblImportStudentsMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblImportStudentsMousePressed
		if (currCourse != null)
			lblImportStudents.setBorder(BorderFactory.createBevelBorder(1,
					new Color(20, 150, 250), new Color(20, 150, 250),
					new Color(20, 150, 250), new Color(20, 150, 250)));
		else {
			lblImportStudents.setBorder(BorderFactory.createBevelBorder(1,
					new Color(204, 204, 204), new Color(204, 204, 204),
					new Color(204, 204, 204), new Color(204, 204, 204)));
			// lblEditCourseErrorLog.setText("Please select a course");
		}
	}// GEN-LAST:event_lblImportStudentsMousePressed

	private void lblImportStudentsMouseReleased(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblImportStudentsMouseReleased
		if (currCourse != null)
			lblImportStudents.setBorder(BorderFactory
					.createLineBorder(new Color(20, 150, 250)));
		else
			lblImportStudents.setBorder(BorderFactory
					.createLineBorder(new Color(204, 204, 204)));
	}// GEN-LAST:event_lblImportStudentsMouseReleased

	private void lblExportStudentsMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblExportStudentsMouseClicked
		if (currCourse != null) {
			final JFileChooser exportStudents = new JFileChooser();
			exportStudents.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int returnVal = exportStudents.showSaveDialog(this);
			String returnMsg;
			if (returnVal == 0) {
				File file = new File(exportStudents.getSelectedFile(),
						currCourse.getTitle() + currCourse.getCode()
								+ currCourse.getTerm() + "Students.csv");
				if (!(returnMsg = currCourse.exportStudents(file)).equals(""))
					lblGradesErrorLog.setText(returnMsg);
				else
					updateInfo();
			}
		} else
			lblGradesErrorLog.setText("Please select a course");

	}// GEN-LAST:event_lblExportStudentsMouseClicked

	private void lblExportStudentsMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblExportStudentsMouseEntered
		if (currCourse != null)
			lblExportStudents.setBorder(BorderFactory
					.createLineBorder(new Color(20, 150, 250)));
		else
			lblExportStudents.setBorder(BorderFactory
					.createLineBorder(new Color(204, 204, 204)));
	}// GEN-LAST:event_lblExportStudentsMouseEntered

	private void lblExportStudentsMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblExportStudentsMouseExited
		lblExportStudents.setBorder(BorderFactory.createLineBorder(new Color(
				204, 204, 204)));
	}// GEN-LAST:event_lblExportStudentsMouseExited

	private void lblExportStudentsMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblExportStudentsMousePressed
		if (currCourse != null)
			lblExportStudents.setBorder(BorderFactory.createBevelBorder(1,
					new Color(20, 150, 250), new Color(20, 150, 250),
					new Color(20, 150, 250), new Color(20, 150, 250)));
		else {
			lblExportStudents.setBorder(BorderFactory.createBevelBorder(1,
					new Color(204, 204, 204), new Color(204, 204, 204),
					new Color(204, 204, 204), new Color(204, 204, 204)));
		}
	}// GEN-LAST:event_lblExportStudentsMousePressed

	private void lblExportStudentsMouseReleased(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblExportStudentsMouseReleased
		if (currCourse != null)
			lblExportStudents.setBorder(BorderFactory
					.createLineBorder(new Color(20, 150, 250)));
		else
			lblExportStudents.setBorder(BorderFactory
					.createLineBorder(new Color(204, 204, 204)));
	}// GEN-LAST:event_lblExportStudentsMouseReleased

	/****************************************************************************************************
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 * 
	 * End: Imports/Exports
	 * 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 */
	/****************************************************************************************************
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 * End: Grades Tab
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 */
	/****************************************************************************************************
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 * Start: Setup Tab
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 */
	/****************************************************************************************************
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 * 
	 * Start: Course Config
	 * 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 */
	int boolCourse = 2;
	Color colorCourse = Color.lightGray;

	private void txtCourtxtCourseDesceDescFocusLost(
			java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txtCourtxtCourseDesceDescFocusLost
		if (!txtCourseDesc.getText().equals("Enter a course description..."))
			currCourse.setDescription(txtCourseDesc.getText());
		else
			txtCourseDesc.setForeground(Color.lightGray);
	}// GEN-LAST:event_txtCourtxtCourseDesceDescFocusLost

	private void txtCourtxtCourseDesceDescFocusGained(
			java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txtCourtxtCourseDesceDescFocusGained
		if (txtCourseDesc.getText().equals("Enter a course description...")) {
			txtCourseDesc.select(0, txtCourseDesc.getText().length());
			txtCourseDesc.setForeground(Color.black);
		}
	}// GEN-LAST:event_txtCourtxtCourseDesceDescFocusGained

	private void txtEditCourseTitleFocusGained(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txtEditCourseTitleFocusGained
		txtEditCourseTitle.setForeground(Color.black);
		txtEditCourseTitle.select(0, txtEditCourseTitle.getText().length());
	}// GEN-LAST:event_txtEditCourseTitleFocusGained

	private void txtEditCourseTitleFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txtEditCourseTitleFocusLost
		if (currCourse != null
				&& txtEditCourseTitle.getText().equals(currCourse.getTitle())
				|| txtEditCourseTitle.getText()
						.equals("Please select a course")) {
			txtEditCourseTitle.setForeground(Color.lightGray);
		}
	}// GEN-LAST:event_txtEditCourseTitleFocusLost

	private void txtEditCourseCodeFocusGained(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txtEditCourseCodeFocusGained
		txtEditCourseCode.setForeground(Color.black);
		txtEditCourseCode.select(0, txtEditCourseCode.getText().length());
	}// GEN-LAST:event_txtEditCourseCodeFocusGained

	private void txtEditCourseCodeFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txtEditCourseCodeFocusLost
		if (currCourse != null
				&& txtEditCourseCode.getText().equals(currCourse.getCode())
				|| txtEditCourseCode.getText().equals("Please select a course")) {
			txtEditCourseCode.setForeground(Color.lightGray);
		}
	}// GEN-LAST:event_txtEditCourseCodeFocusLost

	private void lblEditCourseMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblEditCourseMouseClicked
		if (currCourse != null) {
			if (boolCourse == 1) {
				currCourse.setTitle(txtEditCourseTitle.getText());
				currCourse.setCode(txtEditCourseCode.getText());
				currCourse.setTerm((String) comboEditCourseTerm
						.getSelectedItem());
				updateInfo();
			} else if (boolCourse == 0)
				lblSetupErrorLog.setText("The course"
						+ txtEditCourseTitle.getText() + ", "
						+ txtEditCourseCode.getText()
						+ (String) comboEditCourseTerm.getSelectedItem()
						+ " already exists!");
		} else {
			lblSetupErrorLog.setText("Please select a course");
		}
	}// GEN-LAST:event_lblEditCourseMouseClicked

	private void lblEditCourseMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblEditCourseMouseEntered
		String title=currCourse.getTitle();
                String code =currCourse.getCode();
                
            if (currCourse != null &&!code.equals("")&&!title.equals("")
				&& ((!title.equals(txtEditCourseTitle.getText()))
						|| (!code.equals(
								txtEditCourseCode.getText()))|| !currCourse
						.getTerm()
						.equals(comboEditCourseTerm.getSelectedItem()))) {
               			int i = gradebook.findCourse(new Course(txtEditCourseTitle
					.getText(), (String) comboEditCourseTerm.getSelectedItem(),
					txtEditCourseCode.getText()));
			if (i == -1) {
				lblEditCourse.setBorder(BorderFactory
						.createLineBorder(new Color(51, 255, 51)));
				boolCourse = 1;
			} else {
				lblEditCourse.setBorder(BorderFactory
						.createLineBorder(new Color(255, 51, 51)));
				boolCourse = 0;
			}
		} else {
			lblEditCourse.setBorder(BorderFactory.createLineBorder(new Color(
					204, 204, 204)));
			boolCourse = 2;
		}
	}// GEN-LAST:event_lblEditCourseMouseEntered

	private void lblEditCourseMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblEditCourseMouseExited
		lblEditCourse.setBorder(BorderFactory.createLineBorder(new Color(204,
				204, 204)));
	}// GEN-LAST:event_lblEditCourseMouseExited

	private void lblEditCourseMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblEditCourseMousePressed
		if (boolCourse == 0)
			colorCourse = new Color(255, 51, 51);
		else if (boolCourse == 1)
			colorCourse = new Color(51, 255, 51);
		else
			colorCourse = new Color(204, 204, 204);
		lblEditCourse.setBorder(BorderFactory.createBevelBorder(1, colorCourse,
				colorCourse, colorCourse, colorCourse));
	}// GEN-LAST:event_lblEditCourseMousePressed

	private void lblEditCourseMouseReleased(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblEditCourseMouseReleased
		if (boolCourse == 0)
			colorCourse = new Color(255, 51, 51);
		else if (boolCourse == 1)
			colorCourse = new Color(51, 255, 51);
		else
			colorCourse = new Color(204, 204, 204);
		lblEditCourse.setBorder(BorderFactory.createLineBorder(colorCourse));
	}// GEN-LAST:event_lblEditCourseMouseReleased

	boolean delete = false;

	private void lblDeleteCourseMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblDeleteCourseMouseClicked
		if (currCourse != null) {
			int i = JOptionPane
					.showConfirmDialog(
							this,
							"Are you sure you want to delete\n"
									+ currCourse.getTitle() + " "
									+ currCourse.getCode()
									+ currCourse.getTerm(),
							"Course Deletion Confirmation",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.WARNING_MESSAGE);
			if (i == 0) {
				gradebook.removeCourse(gradebook.getCourse(courseMenuList
						.getSelectedIndex()));
				listCourses.remove(courseMenuList.getSelectedIndex());
				currCourse = gradebook.getPrevCourse();
				if (currCourse != null
						&& gradebook.findCourse(currCourse) != -1)
					courseMenuList.setSelectedIndex(gradebook
							.findCourse(currCourse));
				else
					currCourse = null;
				tabSwitch(0);
				updateInfo();
			}
		} else
			lblSetupErrorLog.setText("Please select a course");

	}// GEN-LAST:event_lblDeleteCourseMouseClicked

	private void lblDeleteCourseMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblDeleteCourseMouseEntered
		if (currCourse != null) {
			lblDeleteCourse.setBorder(BorderFactory.createLineBorder(new Color(
					255, 51, 51)));
		} else {
			lblDeleteCourse.setBorder(BorderFactory.createLineBorder(new Color(
					204, 204, 204)));
		}
	}// GEN-LAST:event_lblDeleteCourseMouseEntered

	private void lblDeleteCourseMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblDeleteCourseMouseExited
		lblDeleteCourse.setBorder(BorderFactory.createLineBorder(new Color(204,
				204, 204)));
	}// GEN-LAST:event_lblDeleteCourseMouseExited

	private void lblDeleteCourseMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblDeleteCourseMousePressed
		if (currCourse != null) {
			lblDeleteCourse.setBorder(BorderFactory.createBevelBorder(1,
					new Color(255, 51, 51), new Color(255, 51, 51), new Color(
							255, 51, 51), new Color(255, 51, 51)));
		} else {
			lblDeleteCourse.setBorder(BorderFactory.createBevelBorder(1,
					new Color(204, 204, 204), new Color(204, 204, 204),
					new Color(204, 204, 204), new Color(204, 204, 204)));
		}
	}// GEN-LAST:event_lblDeleteCourseMousePressed

	/****************************************************************************************************
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 * 
	 * End: Course Config
	 * 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 */
	/****************************************************************************************************
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 * 
	 * End: Deliverable Config
	 * 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 */
	int boolDeliver = 2;
	Color colorDeliver = Color.lightGray;
	Deliverable currDeliver = null;

	private void editDeliverListValueChanged(
			javax.swing.event.ListSelectionEvent evt) {// GEN-FIRST:event_editDeliverListValueChanged
		hideMenu();
		if (currCourse != null) {
			if (editDeliverList.getSelectedIndex() != -1) {

				String temp = listDelivers.getElementAt(editDeliverList
						.getSelectedIndex());

				currDeliver = currCourse.getDeliverable(findDeliver(temp));

				if (currDeliver != null) {
					txtEditDeliverName.setText(currDeliver.getName());
					txtEditDeliverWeight.setText(currDeliver.getWeight() + "");
					comboEditDeliverType.setSelectedItem(currDeliver.getType());
				}
			} else {
				txtEditDeliverName.setText("Please select a deliverable");
				txtEditDeliverWeight.setText("Please select a deliverable");
				comboEditDeliverType.setSelectedIndex(0);
			}
		}
	}// GEN-LAST:event_editDeliverListValueChanged

	private void txtEditDeliverNameFocusGained(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txtEditDeliverNameFocusGained
		txtEditDeliverName.setForeground(Color.black);
		txtEditDeliverName.select(0, txtEditDeliverName.getText().length());
	}// GEN-LAST:event_txtEditDeliverNameFocusGained

	private void txtEditDeliverNameFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txtEditDeliverNameFocusLost
		if (currDeliver != null
				&& txtEditDeliverName.getText().equals(currDeliver.getName())
				|| txtEditDeliverName.getText().equals(
						"Please select a deliverable")) {
			txtEditDeliverName.setForeground(Color.lightGray);
		}
	}// GEN-LAST:event_txtEditDeliverNameFocusLost

	private void txtEditDeliverWeightFocusGained(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txtEditDeliverWeightFocusGained
		txtEditDeliverWeight.setForeground(Color.black);
		txtEditDeliverWeight.select(0, txtEditDeliverWeight.getText().length());
	}// GEN-LAST:event_txtEditDeliverWeightFocusGained

	private void txtEditDeliverWeightFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txtEditDeliverWeightFocusLost
		if (currDeliver != null
				&& txtEditDeliverWeight.getText().equals(
						currDeliver.getWeight())
				|| txtEditDeliverWeight.getText().equals(
						"Please select a deliverable")) {
			txtEditDeliverWeight.setForeground(Color.lightGray);
		}
	}// GEN-LAST:event_txtEditDeliverWeightFocusLost

	private void lblEditDeliverMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblEditDeliverMouseClicked
		if (currDeliver != null) {
			if (!weightException) {
				if (currCourse.getTotalWeight() + weight <= 100) {
					if (boolDeliver == 1) {
						currCourse.setTotalWeight(currCourse.getTotalWeight()
								+ weight - currDeliver.getWeight());
						currDeliver.setName(txtEditDeliverName.getText());
						currDeliver.setWeight(weight);
						currDeliver.setType((String) comboEditDeliverType
								.getSelectedItem());
						updateInfo();
						currDeliver = null;
					} else if (boolCourse == 0)
						lblSetupErrorLog
								.setText("The deliverable"
										+ txtEditDeliverName.getText()
										+ ", "
										+ weight
										+ (String) comboEditDeliverType
												.getSelectedItem()
										+ " already exists!");
				} else {
					lblSetupErrorLog.setText("Weight invalid: "
							+ currCourse.getTotalWeight()
							+ "/100 marks already accounted for");
				}
			} else {
				lblSetupErrorLog.setText("Please enter a valid weight.");
				txtEditDeliverWeight.setBorder(errorHighlightBorder);
			}
		} else
			lblSetupErrorLog.setText("Please select a deliverable");

	}// GEN-LAST:event_lblEditDeliverMouseClicked

	double weight = -1;
	boolean weightException = false;

	private void lblEditDeliverMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblEditDeliverMouseEntered
		try {
			weight = Double.parseDouble(txtEditDeliverWeight.getText());

			if (lblSetupErrorLog.getText().equals(
					"Please enter a valid weight."))
				lblSetupErrorLog.setText("");
			weightException = false;
			txtDeliverWeight.setBorder(defaultHighlightBorder);
			if (currDeliver != null
					&& (!currDeliver.getName().equals(
							txtEditDeliverName.getText())
							|| currDeliver.getWeight() != weight || !currDeliver
							.getType().equals(
									comboEditDeliverType.getSelectedItem()))) {
				int i = currCourse.findDeliverable(new Deliverable(
						txtEditDeliverName.getText(),
						(String) comboEditDeliverType.getSelectedItem(),
						weight, 0));
				if (i == -1) {
					lblEditDeliver.setBorder(BorderFactory
							.createLineBorder(new Color(51, 255, 51)));
					boolDeliver = 1;
				} else {
					lblEditDeliver.setBorder(BorderFactory
							.createLineBorder(new Color(255, 51, 51)));
					boolDeliver = 0;
				}
			} else {
				lblEditDeliver.setBorder(BorderFactory
						.createLineBorder(new Color(204, 204, 204)));
				boolDeliver = 2;
			}
		} catch (NumberFormatException e) {
			weightException = true;
		}
	}// GEN-LAST:event_lblEditDeliverMouseEntered

	private void lblEditDeliverMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblEditDeliverMouseExited
		lblEditDeliver.setBorder(BorderFactory.createLineBorder(new Color(204,
				204, 204)));
	}// GEN-LAST:event_lblEditDeliverMouseExited

	private void lblEditDeliverMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblEditDeliverMousePressed
		if (boolDeliver == 0)
			colorDeliver = new Color(255, 51, 51);
		else if (boolDeliver == 1)
			colorDeliver = new Color(51, 255, 51);
		else
			colorCourse = new Color(204, 204, 204);
		lblEditDeliver.setBorder(BorderFactory.createBevelBorder(1,
				colorDeliver, colorDeliver, colorDeliver, colorDeliver));
	}// GEN-LAST:event_lblEditDeliverMousePressed

	private void lblEditDeliverMouseReleased(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblEditDeliverMouseReleased
		if (boolDeliver == 0)
			colorDeliver = new Color(255, 51, 51);
		else if (boolDeliver == 1)
			colorDeliver = new Color(51, 255, 51);
		else
			colorDeliver = new Color(204, 204, 204);
		lblEditDeliver.setBorder(BorderFactory.createLineBorder(colorDeliver));
	}// GEN-LAST:event_lblEditDeliverMouseReleased

	private void lblImportDeliversMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblImportDeliversMouseClicked
		if (currCourse != null) {
			final JFileChooser importDeliver = new JFileChooser();
			int returnVal = importDeliver.showOpenDialog(this);
			String returnMsg;
			if (returnVal == 0) {
				if (!(returnMsg = currCourse.importDeliverables(importDeliver
						.getSelectedFile())).equals("")) {
					lblSetupErrorLog.setText(returnMsg);
				} else
					updateInfo();
			}
		} else
			lblSetupErrorLog.setText("Please select a course.");
	}// GEN-LAST:event_lblImportDeliversMouseClicked

	private void lblImportDeliversMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblImportDeliversMouseEntered
		if (currCourse != null)
			lblImportDelivers.setBorder(BorderFactory
					.createLineBorder(new Color(20, 150, 250)));
		else
			lblImportDelivers.setBorder(BorderFactory
					.createLineBorder(new Color(204, 204, 204)));
	}// GEN-LAST:event_lblImportDeliversMouseEntered

	private void lblImportDeliversMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblImportDeliversMouseExited
		lblImportDelivers.setBorder(BorderFactory.createLineBorder(new Color(
				204, 204, 204)));
	}// GEN-LAST:event_lblImportDeliversMouseExited

	private void lblImportDeliversMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblImportDeliversMousePressed
		if (currCourse != null)
			lblImportDelivers.setBorder(BorderFactory.createBevelBorder(1,
					new Color(20, 150, 250), new Color(20, 150, 250),
					new Color(20, 150, 250), new Color(20, 150, 250)));
		else
			lblImportDelivers.setBorder(BorderFactory.createBevelBorder(1,
					new Color(204, 204, 204), new Color(204, 204, 204),
					new Color(204, 204, 204), new Color(204, 204, 204)));

	}// GEN-LAST:event_lblImportDeliversMousePressed

	private void lblImportDeliversMouseReleased(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblImportDeliversMouseReleased
		if (currCourse != null)
			lblImportDelivers.setBorder(BorderFactory
					.createLineBorder(new Color(20, 150, 250)));
		else
			lblImportDelivers.setBorder(BorderFactory
					.createLineBorder(new Color(204, 204, 204)));
	}// GEN-LAST:event_lblImportDeliversMouseReleased

	private void lblExportDeliversMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblExportDeliversMouseClicked
		if (currCourse != null) {
			final JFileChooser exportDeliver = new JFileChooser();
			exportDeliver.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int returnVal = exportDeliver.showSaveDialog(this);
			String returnMsg;
			if (returnVal == 0) {
				File file = new File(exportDeliver.getSelectedFile(),
						currCourse.getTitle() + currCourse.getCode()
								+ currCourse.getTerm() + "Deliverables.csv");
				if (!(returnMsg = currCourse.exportDeliverables(file))
						.equals(""))
					lblSetupErrorLog.setText(returnMsg);
				updateInfo();
			}
		} else
			lblSetupErrorLog.setText("Please selcet a course.");
	}// GEN-LAST:event_lblExportDeliversMouseClicked

	private void lblExportDeliversMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblExportDeliversMouseEntered
		if (currCourse != null)
			lblExportDelivers.setBorder(BorderFactory
					.createLineBorder(new Color(20, 150, 250)));
		else
			lblExportDelivers.setBorder(BorderFactory
					.createLineBorder(new Color(204, 204, 204)));
	}// GEN-LAST:event_lblExportDeliversMouseEntered

	private void lblExportDeliversMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblExportDeliversMouseExited
		lblExportDelivers.setBorder(BorderFactory.createLineBorder(new Color(
				204, 204, 204)));
	}// GEN-LAST:event_lblExportDeliversMouseExited

	private void lblExportDeliversMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblExportDeliversMousePressed
		if (currCourse != null)
			lblExportDelivers.setBorder(BorderFactory.createBevelBorder(1,
					new Color(20, 150, 250), new Color(20, 150, 250),
					new Color(20, 150, 250), new Color(20, 150, 250)));
		else
			lblExportDelivers.setBorder(BorderFactory.createBevelBorder(1,
					new Color(204, 204, 204), new Color(204, 204, 204),
					new Color(204, 204, 204), new Color(204, 204, 204)));

	}// GEN-LAST:event_lblExportDeliversMousePressed

	private void lblExportDeliversMouseReleased(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblExportDeliversMouseReleased
		if (currCourse != null)
			lblExportDelivers.setBorder(BorderFactory
					.createLineBorder(new Color(20, 150, 250)));
		else
			lblExportDelivers.setBorder(BorderFactory
					.createLineBorder(new Color(204, 204, 204)));
	}// GEN-LAST:event_lblExportDeliversMouseReleased

	private void lblEditDeleteDeliverMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblEditDeleteDeliverMouseClicked
		if (currCourse != null) {
			if (editDeliverList.getSelectedIndex() != -1) {
				int i = JOptionPane.showConfirmDialog(
						this,
						"Are you sure you want to delete\n"
								+ currDeliver.getName(),
						"Deliverable Deletion Confirmation",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (i == 0) {
					String temp = listDelivers.getElementAt(editDeliverList
							.getSelectedIndex());
					currCourse.removeDeliverable(findDeliver(temp));
					currDeliver = null;
					updateInfo();
				}
			} else
				lblSetupErrorLog.setText("Please select a deliverable");
		} else
			lblSetupErrorLog.setText("Please select a course");
	}// GEN-LAST:event_lblEditDeleteDeliverMouseClicked

	private void lblEditDeleteDeliverMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblEditDeleteDeliverMouseEntered
		if (currCourse != null && editDeliverList.getSelectedIndex() != -1)
			lblEditDeleteDeliver.setBorder(BorderFactory
					.createLineBorder(new Color(255, 51, 51)));
		else
			lblEditDeleteDeliver.setBorder(BorderFactory
					.createLineBorder(new Color(204, 204, 204)));
	}// GEN-LAST:event_lblEditDeleteDeliverMouseEntered

	private void lblEditDeleteDeliverMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblEditDeleteDeliverMouseExited
		lblEditDeleteDeliver.setBorder(BorderFactory
				.createLineBorder(new Color(204, 204, 204)));
	}// GEN-LAST:event_lblEditDeleteDeliverMouseExited

	private void lblEditDeleteDeliverMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblEditDeleteDeliverMousePressed
		if (currCourse != null && editDeliverList.getSelectedIndex() != -1)
			lblEditDeleteDeliver.setBorder(BorderFactory.createBevelBorder(1,
					new Color(255, 51, 51), new Color(255, 51, 51), new Color(
							255, 51, 51), new Color(255, 51, 51)));
		else
			lblEditDeleteDeliver.setBorder(BorderFactory.createBevelBorder(1,
					new Color(204, 204, 204), new Color(204, 204, 204),
					new Color(204, 204, 204), new Color(204, 204, 204)));
	}// GEN-LAST:event_lblEditDeleteDeliverMousePressed

	private void lblEditDeleteDeliverMouseReleased(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblEditDeleteDeliverMouseReleased
		if (currCourse != null && editDeliverList.getSelectedIndex() != -1)
			lblEditDeleteDeliver.setBorder(BorderFactory
					.createLineBorder(new Color(255, 51, 51)));
		else
			lblEditDeleteDeliver.setBorder(BorderFactory
					.createLineBorder(new Color(204, 204, 204)));
	}// GEN-LAST:event_lblEditDeleteDeliverMouseReleased

	/****************************************************************************************************
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 * 
	 * End: Deliverable Config
	 * 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 */

	/****************************************************************************************************
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 * End: Setup Tab
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 */

	/****************************************************************************************************
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 * Start: Dropbox Tab
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 */
	Dropbox dbx;
	boolean generated = false;

	private void lblTabDropboxMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblTabDropboxMouseClicked

	}// GEN-LAST:event_lblTabDropboxMouseClicked

	private void lblDropboxMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblDropboxMouseClicked
		tabSwitch(2);
	}// GEN-LAST:event_lblDropboxMouseClicked

	private void lblDbxUploadMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblDbxUploadMouseClicked
		if (!dbx.upload()) {
			lblDbxErrorLog.setForeground(Color.red);
			lblDbxErrorLog.setText("Error, upload failed.");
		} else {
			lblDbxErrorLog.setForeground(Color.green);
			lblDbxErrorLog.setText("Success, upload was successful.");
		}
	}// GEN-LAST:event_lblDbxUploadMouseClicked

	private void lblDbxDownloadMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblDbxDownloadMouseClicked
		if (!dbx.download()) {
			lblDbxErrorLog.setForeground(Color.red);
			lblDbxErrorLog.setText("Error, download failed.");
		} else {
			lblDbxErrorLog.setForeground(Color.green);
			lblDbxErrorLog.setText("Success, download was successful.");
		}
	}// GEN-LAST:event_lblDbxDownloadMouseClicked

	private void lblDbxActivateMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblDbxAuthLinkMouseClicked
		dbx = new Dropbox();
		generated = true;
		if (generated) {
			try {
				java.awt.Desktop.getDesktop().browse(
						java.net.URI.create(dbx.getAuthorizeUrl()));
			} catch (java.io.IOException e) {
			}
		}
	}// GEN-LAST:event_lblDbxAuthLinkMouseClicked

	private void lblDbxSubmitMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblDbxSubmitMouseClicked
		if (generated) {
			if (!dbx.authenticate(txtDbxCode.getText())) {
				lblDbxErrorLog.setForeground(Color.red);
				lblDbxErrorLog.setText("Invalid Code.");
			} else {
				lblDbxErrorLog.setForeground(Color.green);
				lblDbxErrorLog.setText("Connected.");

				txtDbxCode.setVisible(false);
				lblDbxActivate.setVisible(false);
				lblDbxSubmit.setVisible(false);

				lblDbxVerify.setIcon(new javax.swing.ImageIcon(getClass()
						.getResource("/cs2212/team4/dropboxTrue.png")));
				lblDbxDownload.setVisible(true);
				lblDbxUpload.setVisible(true);
			}
		} else {
			lblDbxErrorLog.setForeground(Color.red);
			lblDbxErrorLog
					.setText("Error, please generate a link, and insert the code provided by the Dropbox webpage.");
		}
	}// GEN-LAST:event_lblDbxSubmitMouseClicked

	private void txtDbxCodeFocusGained(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txtDbxCodeFocusGained
		txtDbxCode.selectAll();
		txtDbxCode.setForeground(Color.black);
	}// GEN-LAST:event_txtDbxCodeFocusGained

	private void lblDbxActivateMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblDbxGenerateMouseEntered
		lblDbxActivate.setBorder(BorderFactory.createLineBorder(new Color(20,
				150, 250)));
	}// GEN-LAST:event_lblDbxGenerateMouseEntered

	private void lblDbxActivateMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblDbxGenerateMouseExited
		lblDbxActivate.setBorder(BorderFactory.createLineBorder(new Color(204,
				204, 204)));
	}// GEN-LAST:event_lblDbxGenerateMouseExited

	private void lblDbxActivateMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblDbxGenerateMousePressed
		lblDbxActivate.setBorder(BorderFactory.createBevelBorder(0, new Color(
				20, 150, 250), new Color(20, 150, 250),
				new Color(20, 150, 250), new Color(20, 150, 250)));
	}// GEN-LAST:event_lblDbxGenerateMousePressed

	private void lblDbxActivateMouseReleased(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblDbxGenerateMouseReleased
		lblDbxActivate.setBorder(BorderFactory.createLineBorder(new Color(20,
				150, 250)));
	}// GEN-LAST:event_lblDbxGenerateMouseReleased

	private void lblDbxSubmitMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblDbxSubmitMouseEntered
		lblDbxSubmit.setBorder(BorderFactory.createLineBorder(new Color(20,
				150, 250)));
	}// GEN-LAST:event_lblDbxSubmitMouseEntered

	private void lblDbxSubmitMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblDbxSubmitMouseExited
		lblDbxSubmit.setBorder(BorderFactory.createLineBorder(new Color(204,
				204, 204)));
	}// GEN-LAST:event_lblDbxSubmitMouseExited

	private void lblDbxSubmitMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblDbxSubmitMousePressed
		lblDbxSubmit.setBorder(BorderFactory.createBevelBorder(0, new Color(20,
				150, 250), new Color(20, 150, 250), new Color(20, 150, 250),
				new Color(20, 150, 250)));
	}// GEN-LAST:event_lblDbxSubmitMousePressed

	private void lblDbxSubmitMouseReleased(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblDbxSubmitMouseReleased
		lblDbxSubmit.setBorder(BorderFactory.createLineBorder(new Color(204,
				204, 204)));
	}// GEN-LAST:event_lblDbxSubmitMouseReleased

	private void lblDbxUploadMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblDbxUploadMouseEntered
		lblDbxUpload.setBorder(BorderFactory.createLineBorder(new Color(20,
				150, 250)));
	}// GEN-LAST:event_lblDbxUploadMouseEntered

	private void lblDbxUploadMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblDbxUploadMouseExited
		lblDbxUpload.setBorder(BorderFactory.createLineBorder(new Color(204,
				204, 204)));
	}// GEN-LAST:event_lblDbxUploadMouseExited

	private void lblDbxUploadMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblDbxUploadMousePressed
		lblDbxUpload.setBorder(BorderFactory.createBevelBorder(0, new Color(20,
				150, 250), new Color(20, 150, 250), new Color(20, 150, 250),
				new Color(20, 150, 250)));
	}// GEN-LAST:event_lblDbxUploadMousePressed

	private void lblDbxUploadMouseReleased(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblDbxUploadMouseReleased
		lblDbxUpload.setBorder(BorderFactory.createLineBorder(new Color(204,
				204, 204)));
	}// GEN-LAST:event_lblDbxUploadMouseReleased

	private void lblDbxDownloadMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblDbxDownloadMouseEntered
		lblDbxDownload.setBorder(BorderFactory.createLineBorder(new Color(20,
				150, 250)));
	}// GEN-LAST:event_lblDbxDownloadMouseEntered

	private void lblDbxDownloadMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblDbxDownloadMouseExited
		lblDbxDownload.setBorder(BorderFactory.createLineBorder(new Color(204,
				204, 204)));
	}// GEN-LAST:event_lblDbxDownloadMouseExited

	private void lblDbxDownloadMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblDbxDownloadMousePressed
		lblDbxDownload.setBorder(BorderFactory.createBevelBorder(0, new Color(
				20, 150, 250), new Color(20, 150, 250),
				new Color(20, 150, 250), new Color(20, 150, 250)));
	}// GEN-LAST:event_lblDbxDownloadMousePressed

	private void lblDbxDownloadMouseReleased(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblDbxDownloadMouseReleased
		lblDbxDownload.setBorder(BorderFactory.createLineBorder(new Color(204,
				204, 204)));
	}// GEN-LAST:event_lblDbxDownloadMouseReleased

	/****************************************************************************************************
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 * End: Dropbox Tab
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 */

	/****************************************************************************************************
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 * Start: Extras
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 */

	/****************************************************************************************************
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 * 
	 * Start: Tabs
	 * 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 */

	private void lblTabGradesMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblTabGradesMouseClicked
		tabSwitch(0);
	}// GEN-LAST:event_lblTabGradesMouseClicked

	private void lblTabSetupMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblTabSetupMouseClicked
		tabSwitch(1);
	}// GEN-LAST:event_lblTabSetupMouseClicked

	/****************************************************************************************************
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 * 
	 * End: Tabs
	 * 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 */

	/****************************************************************************************************
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 * 
	 * Start: Drag
	 * 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 */
	Point inWinCoords;

	private void formMouseDragged(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_formMouseDragged
		hideMenu();
		Point currCoords = evt.getLocationOnScreen();
		setLocation(currCoords.x - inWinCoords.x, currCoords.y - inWinCoords.y);
	}// GEN-LAST:event_formMouseDragged

	private void formMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_formMousePressed
		inWinCoords = evt.getPoint();
	}// GEN-LAST:event_formMousePressed

	/****************************************************************************************************
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 * 
	 * End: Drag
	 * 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 */

	/****************************************************************************************************
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 * 
	 * Start: Minimize
	 * 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 */

	private void lblMiniMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblMiniMouseEntered
		lblMini.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/cs2212/team4/miniOn.png")));
	}// GEN-LAST:event_lblMiniMouseEntered

	private void lblMiniMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblMiniMouseExited
		lblMini.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/cs2212/team4/miniOff.png")));
	}// GEN-LAST:event_lblMiniMouseExited

	private void lblMiniMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblMiniMouseClicked
		hideMenu();
		setState(Frame.ICONIFIED);
	}// GEN-LAST:event_lblMiniMouseClicked

	/****************************************************************************************************
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 * 
	 * End: Minimize
	 * 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 */

	/****************************************************************************************************
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 * 
	 * Start: Exit
	 * 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 */

	private void lblExitMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblExitMouseEntered
		lblExit.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/cs2212/team4/exitOn.png")));
	}// GEN-LAST:event_lblExitMouseEntered

	private void lblExitMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblExitMouseExited
		lblExit.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/cs2212/team4/exitOff.png")));
	}// GEN-LAST:event_lblExitMouseExited

	private void lblExitMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblExitMouseClicked
		gradebook.setPrevCourse(currCourse);
		gradebook.store();
		System.exit(0);
	}// GEN-LAST:event_lblExitMouseClicked

	/****************************************************************************************************
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 * 
	 * End: Exit
	 * 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 */

	/****************************************************************************************************
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 * 
	 * Start: My Courses Menu
	 * 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 */
	int check = 0;

	private void myCoursesMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_myCoursesMouseClicked
		if (!pnlCourseMenu.isVisible())
			pnlCourseMenu.setVisible(true);
		else
			pnlCourseMenu.setVisible(false);
	}// GEN-LAST:event_myCoursesMouseClicked

	private void formMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_formMouseClicked
		hideMenu();
	}// GEN-LAST:event_formMouseClicked

	private void addCourseMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_addCourseMouseClicked
		hideMenu();
		restPnlCourse();
		restPnlDeliver();
		restPnlStudent();

		addFrame.add(pnlAddCourse);
		pnlAddCourse.setVisible(true);
		addFrame.setVisible(true);
		disable();
	}// GEN-LAST:event_addCourseMouseClicked

	private void addCourseMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_addCourseMouseEntered
		addCourse.setBorder(BorderFactory.createLineBorder(new Color(51, 255,
				51)));
	}// GEN-LAST:event_addCourseMouseEntered

	private void addCourseMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_addCourseMouseExited
		addCourse.setBorder(BorderFactory.createLineBorder(new Color(204, 204,
				204)));
	}// GEN-LAST:event_addCourseMouseExited

	private void addCourseMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_addCourseMousePressed
		addCourse.setBorder(BorderFactory.createBevelBorder(1, new Color(51,
				255, 51), new Color(51, 255, 51), new Color(51, 255, 51),
				new Color(51, 255, 51)));
	}// GEN-LAST:event_addCourseMousePressed

	private void courseMenuListValueChanged(
			javax.swing.event.ListSelectionEvent evt) {// GEN-FIRST:event_courseMenuListValueChanged
		hideMenu();
		if (courseMenuList.getSelectedIndex() != -1) {
			if (!courseMenuList.getSelectedValue().equals("No Courses")
					&& check == 0) {
				check++;
				gradebook.setPrevCourse(currCourse);
				currCourse = gradebook.getCourse(courseMenuList
						.getSelectedIndex());
				updateInfo();
			} else
				check = 0;
		}
	}// GEN-LAST:event_courseMenuListValueChanged

	/****************************************************************************************************
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 * 
	 * End: My Courses Menu
	 * 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 */

	/****************************************************************************************************
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 * 
	 * Start: Add New Course Panel
	 * 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 */

	private void txtCourseNameFocusGained(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txtCourseNameFocusGained
		if (txtCourseName.getText().equals("")
				|| txtCourseName.getText().equals("ex. Computer Engineering"))
			txtCourseName.setText("");
		txtCourseName.setForeground(Color.black);
	}// GEN-LAST:event_txtCourseNameFocusGained

	private void txtCourseNameFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txtCourseNameFocusLost
		if (txtCourseName.getText().equals("")) {
			txtCourseName.setText("ex. Computer Engineering");
			txtCourseName.setForeground(new Color(204, 204, 204));
		}
	}// GEN-LAST:event_txtCourseNameFocusLost

	private void txtCourseCodeFocusGained(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txtCourseCodeFocusGained
		if (txtCourseCode.getText().equals("")
				|| txtCourseCode.getText().equals("ex. CS2212"))
			txtCourseCode.setText("");
		txtCourseCode.setForeground(Color.black);
	}// GEN-LAST:event_txtCourseCodeFocusGained

	private void txtCourseCodeFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txtCourseCodeFocusLost
		if (txtCourseCode.getText().equals("")) {
			txtCourseCode.setText("ex. CS2212");
			txtCourseCode.setForeground(new Color(204, 204, 204));
		}
	}// GEN-LAST:event_txtCourseCodeFocusLost

	private void lblCancelCourseAdditionMouseEntered(
			java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblCancelCourseAdditionMouseEntered
		lblCancelCourseAddition.setBackground(new Color(255, 51, 51));
	}// GEN-LAST:event_lblCancelCourseAdditionMouseEntered

	private void lblCancelCourseAdditionMouseExited(
			java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblCancelCourseAdditionMouseExited
		lblCancelCourseAddition.setBackground(Color.white);
	}// GEN-LAST:event_lblCancelCourseAdditionMouseExited

	private void lblCancelCourseAdditionMousePressed(
			java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblCancelCourseAdditionMousePressed
		lblCancelCourseAddition.setBackground(new Color(255, 51, 51));
	}// GEN-LAST:event_lblCancelCourseAdditionMousePressed

	private void lblCancelCourseAdditionMouseReleased(
			java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblCancelCourseAdditionMouseReleased
		lblCancelCourseAddition.setBackground(Color.white);
	}// GEN-LAST:event_lblCancelCourseAdditionMouseReleased

	private void lblCancelCourseAdditionMouseClicked(
			java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblCancelCourseAdditionMouseClicked
		closeFrame(addFrame);
		restPnlCourse();
	}// GEN-LAST:event_lblCancelCourseAdditionMouseClicked

	private void lblAddCourseMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblAddCourseMouseEntered
		lblAddCourse.setBackground(new Color(51, 255, 51));
	}// GEN-LAST:event_lblAddCourseMouseEntered

	private void lblAddCourseMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblAddCourseMouseExited
		lblAddCourse.setBackground(Color.white);
	}// GEN-LAST:event_lblAddCourseMouseExited

	private void lblAddCourseMouseReleased(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblAddCourseMouseReleased
		lblAddCourse.setBackground(Color.white);
	}// GEN-LAST:event_lblAddCourseMouseReleased

	private void lblAddCourseMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblAddCourseMousePressed
		lblAddCourse.setBackground(new Color(51, 255, 51));
	}// GEN-LAST:event_lblAddCourseMousePressed

	private void lblAddCourseMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblAddCourseMouseClicked
		String name, code, getName, getCode, info, term;

		txtCourseName.setBorder(defaultHighlightBorder);
		txtCourseCode.setBorder(defaultHighlightBorder);
		lblCourseAddErrorLog.setText("");

		getName = txtCourseName.getText();
		if (getName.equals("ex. Computer Engineering"))
			getName = "";

		getCode = txtCourseCode.getText();
		if (getCode.equals("ex. CS2212"))
			getCode = "";

		term = (String) comboCourseTerm.getSelectedItem();

		if (getName.equals("") || (name = elimSpaces(getName)) == null) {
			lblCourseAddErrorLog.setText("Please enter a course name.");
			txtCourseName.setBorder(errorHighlightBorder);
		} else if (getCode.equals("") || (code = elimSpaces(getCode)) == null) {
			lblCourseAddErrorLog.setText("Please enter a course code.");
			txtCourseCode.setBorder(errorHighlightBorder);
		} else {
			if (!gradebook.addCourse(name, term, code))
				lblCourseAddErrorLog.setText("Course Already exists!");
			else {
				closeFrame(addFrame);
				restPnlCourse();
				info = name + ", " + code + term;
				if (listCourses.size() == 1
						&& listCourses.get(0).equals("No Courses"))
					listCourses.set(0, info);
				else
					listCourses.addElement(info);

				currCourse = gradebook.getCourse(listCourses.getSize() - 1);
				courseMenuList.setSelectedIndex(listCourses.getSize() - 1);
				updateInfo();
			}
		}
	}// GEN-LAST:event_lblAddCourseMouseClicked

	/****************************************************************************************************
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 * 
	 * End: Add New Course Panel
	 * 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 */

	/****************************************************************************************************
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 * 
	 * Start: Add New Deliverable Panel
	 * 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 */

	private void txtDeliverNameFocusGained(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txtDeliverNameFocusGained
		if (txtDeliverName.getText().equals("")
				|| txtDeliverName.getText().equals("ex. Group Project"))
			txtDeliverName.setText("");
		txtDeliverName.setForeground(Color.black);
	}// GEN-LAST:event_txtDeliverNameFocusGained

	private void txtDeliverNameFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txtDeliverNameFocusLost
		if (txtDeliverName.getText().equals("")) {
			txtDeliverName.setText("ex. Group Project");
			txtDeliverName.setForeground(new Color(204, 204, 204));
		}
	}// GEN-LAST:event_txtDeliverNameFocusLost

	private void txtDeliverWeightFocusGained(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txtDeliverWeightFocusGained
		if (txtDeliverWeight.getText().equals("")
				|| txtDeliverWeight.getText().equals("ex. 54"))
			txtDeliverWeight.setText("");
		txtDeliverWeight.setForeground(Color.black);
	}// GEN-LAST:event_txtDeliverWeightFocusGained

	private void txtDeliverWeightFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txtDeliverWeightFocusLost
		if (txtDeliverWeight.getText().equals("")) {
			txtDeliverWeight.setText("ex. 54");
			txtDeliverWeight.setForeground(new Color(204, 204, 204));
		}
	}// GEN-LAST:event_txtDeliverWeightFocusLost

	private void lblCancelDeliverAdditionMouseEntered(
			java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblCancelDeliverAdditionMouseEntered
		lblCancelDeliverAddition.setBackground(new Color(255, 51, 51));
	}// GEN-LAST:event_lblCancelDeliverAdditionMouseEntered

	private void lblCancelDeliverAdditionMouseExited(
			java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblCancelDeliverAdditionMouseExited
		lblCancelDeliverAddition.setBackground(Color.white);
	}// GEN-LAST:event_lblCancelDeliverAdditionMouseExited

	private void lblCancelDeliverAdditionMousePressed(
			java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblCancelDeliverAdditionMousePressed
		lblCancelDeliverAddition.setBackground(new Color(255, 51, 51));
	}// GEN-LAST:event_lblCancelDeliverAdditionMousePressed

	private void lblCancelDeliverAdditionMouseReleased(
			java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblCancelDeliverAdditionMouseReleased
		lblCancelDeliverAddition.setBackground(Color.white);
	}// GEN-LAST:event_lblCancelDeliverAdditionMouseReleased

	private void lblCancelDeliverAdditionMouseClicked(
			java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblCancelDeliverAdditionMouseClicked
		closeFrame(addFrame);
		restPnlDeliver();
	}// GEN-LAST:event_lblCancelDeliverAdditionMouseClicked

	private void lblAddDeliverMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblAddDeliverMouseEntered
		lblAddDeliver.setBackground(new Color(51, 255, 51));
	}// GEN-LAST:event_lblAddDeliverMouseEntered

	private void lblAddDeliverMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblAddDeliverMouseExited
		lblAddDeliver.setBackground(Color.white);
	}// GEN-LAST:event_lblAddDeliverMouseExited

	private void lblAddDeliverMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblAddDeliverMousePressed
		lblAddDeliver.setBackground(new Color(51, 255, 51));
	}// GEN-LAST:event_lblAddDeliverMousePressed

	private void lblAddDeliverMouseReleased(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblAddDeliverMouseReleased
		lblAddDeliver.setBackground(Color.white);
	}// GEN-LAST:event_lblAddDeliverMouseReleased

	private void lblAddDeliverMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblAddDeliverMouseClicked
		if (currCourse == null)
			addDeliverErrorLog.setText("Please select a course!");
		else {
			String name, weight, getName, getWeight, info, type, returnMsg = "";
			boolean boolFormat = true;

			txtDeliverName.setBorder(defaultHighlightBorder);
			txtDeliverWeight.setBorder(defaultHighlightBorder);
			addDeliverErrorLog.setText("");

			getName = txtDeliverName.getText();
			if (getName.equals("ex. Group Project"))
				getName = "";

			getWeight = txtDeliverWeight.getText();
			if (getWeight.equals("ex. 54"))
				getWeight = "";

			type = (String) comboDeliverType.getSelectedItem();

			if (getName.equals("") || (name = elimSpaces(getName)) == null) {
				addDeliverErrorLog.setText("Please enter a deliverable name.");
				txtDeliverName.setBorder(errorHighlightBorder);
			} else if (getWeight.equals("")
					|| (weight = elimSpaces(getWeight)) == null) {
				addDeliverErrorLog
						.setText("Please enter a deliverable weight.");
				txtDeliverWeight.setBorder(errorHighlightBorder);
			} else {
				try {
					returnMsg = currCourse.addDeliverable(name, type,
							Double.parseDouble(weight));
				} catch (NumberFormatException e) {
					txtDeliverWeight.setBorder(errorHighlightBorder);
					boolFormat = false;
				}

				if (!boolFormat) {
					addDeliverErrorLog
							.setText("Please enter a valid deliverable weight.");
					addDeliverErrorLog.setForeground(Color.red);
				} else if (!returnMsg.equals("")) {
					addDeliverErrorLog.setText(returnMsg);
					addDeliverErrorLog.setForeground(Color.red);
				} else {
					closeFrame(addFrame);
					restPnlDeliver();
					info = name + ", " + type + ", "
							+ Double.parseDouble(weight);
					listDelivers.addElement(info);
					updateTables();
				}
			}
		}
	}// GEN-LAST:event_lblAddDeliverMouseClicked

	/****************************************************************************************************
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 * 
	 * End: Add New Deliverable Panel
	 * 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 */

	/****************************************************************************************************
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 * 
	 * Start: Add New Student Panel
	 * 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 */
	private void txtStudentNameFirstFocusGained(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txtStudentNameFirstFocusGained
		if (txtStudentNameFirst.getText().equals("")
				|| txtStudentNameFirst.getText().equals("ex. John"))
			txtStudentNameFirst.setText("");
		txtStudentNameFirst.setForeground(Color.black);
	}// GEN-LAST:event_txtStudentNameFirstFocusGained

	private void txtStudentNameFirstFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txtStudentNameFirstFocusLost
		if (txtStudentNameFirst.getText().equals("")) {
			txtStudentNameFirst.setText("ex. John");
			txtStudentNameFirst.setForeground(new Color(204, 204, 204));
		}
	}// GEN-LAST:event_txtStudentNameFirstFocusLost

	private void txtStudentNameLastFocusGained(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txtStudentNameLastFocusGained
		if (txtStudentNameLast.getText().equals("")
				|| txtStudentNameLast.getText().equals("ex. Doe"))
			txtStudentNameLast.setText("");
		txtStudentNameLast.setForeground(Color.black);
	}// GEN-LAST:event_txtStudentNameLastFocusGained

	private void txtStudentNameLastFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txtStudentNameLastFocusLost
		if (txtStudentNameLast.getText().equals("")) {
			txtStudentNameLast.setText("ex. Doe");
			txtStudentNameLast.setForeground(new Color(204, 204, 204));
		}
	}// GEN-LAST:event_txtStudentNameLastFocusLost

	private void txtStudentNumberFocusGained(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txtStudentNumberFocusGained
		if (txtStudentNumber.getText().equals("")
				|| txtStudentNumber.getText().equals("ex. 250626000"))
			txtStudentNumber.setText("");
		txtStudentNumber.setForeground(Color.black);
	}// GEN-LAST:event_txtStudentNumberFocusGained

	private void txtStudentNumberFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txtStudentNumberFocusLost
		if (txtStudentNumber.getText().equals("")) {
			txtStudentNumber.setText("ex. 250626000");
			txtStudentNumber.setForeground(new Color(204, 204, 204));
		}
	}// GEN-LAST:event_txtStudentNumberFocusLost

	private void txtStudentEmailFocusGained(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txtStudentEmailFocusGained
		if (txtStudentEmail.getText().equals("")
				|| txtStudentEmail.getText().equals("ex. john-doe@example.com"))
			txtStudentEmail.setText("");
		txtStudentEmail.setForeground(Color.black);
	}// GEN-LAST:event_txtStudentEmailFocusGained

	private void txtStudentEmailFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txtStudentEmailFocusLost
		if (txtStudentEmail.getText().equals("")) {
			txtStudentEmail.setText("ex. john-doe@example.com");
			txtStudentEmail.setForeground(new Color(204, 204, 204));
		}
	}// GEN-LAST:event_txtStudentEmailFocusLost

	private void lblCancelStudentAdditionMouseEntered(
			java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblCancelStudentAdditionMouseEntered
		lblCancelStudentAddition.setBackground(new Color(255, 51, 51));
	}// GEN-LAST:event_lblCancelStudentAdditionMouseEntered

	private void lblCancelStudentAdditionMouseExited(
			java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblCancelStudentAdditionMouseExited
		lblCancelStudentAddition.setBackground(Color.white);
	}// GEN-LAST:event_lblCancelStudentAdditionMouseExited

	private void lblCancelStudentAdditionMousePressed(
			java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblCancelStudentAdditionMousePressed
		lblCancelStudentAddition.setBackground(new Color(255, 51, 51));
	}// GEN-LAST:event_lblCancelStudentAdditionMousePressed

	private void lblCancelStudentAdditionMouseReleased(
			java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblCancelStudentAdditionMouseReleased
		lblCancelStudentAddition.setBackground(Color.white);
	}// GEN-LAST:event_lblCancelStudentAdditionMouseReleased

	private void lblCancelStudentAdditionMouseClicked(
			java.awt.event.MouseEvent evt) {
		closeFrame(addFrame);
		restPnlStudent();
	}

	private void lblAddStudentMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblAddStudentMouseEntered
		lblAddStudent.setBackground(new Color(51, 255, 51));
	}// GEN-LAST:event_lblAddStudentMouseEntered

	private void lblAddStudentMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblAddStudentMouseExited
		lblAddStudent.setBackground(Color.white);
	}// GEN-LAST:event_lblAddStudentMouseExited

	private void lblAddStudentMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblAddStudentMousePressed
		lblAddStudent.setBackground(new Color(51, 255, 51));
	}// GEN-LAST:event_lblAddStudentMousePressed

	private void lblAddStudentMouseReleased(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblAddStudentMouseReleased
		lblAddStudent.setBackground(Color.white);
	}// GEN-LAST:event_lblAddStudentMouseReleased

	private void lblAddStudentMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblAddStudentMouseClicked
		if (currCourse == null)
			lblStudentAddErrorLog.setText("Please select a course!");
		else {
			String nameFirst, nameLast, number, email, getNameFirst, getNameLast, getNumber, getEmail;
			boolean boolFormat = false;
			txtStudentNameFirst.setBorder(defaultHighlightBorder);
			txtStudentNameLast.setBorder(defaultHighlightBorder);
			txtStudentNumber.setBorder(defaultHighlightBorder);
			txtStudentEmail.setBorder(defaultHighlightBorder);
			lblStudentAddErrorLog.setText("");

			getNameFirst = txtStudentNameFirst.getText();
			if (getNameFirst.equals("ex. John"))
				getNameFirst = "";

			getNameLast = txtStudentNameLast.getText();
			if (getNameLast.equals("ex. Doe"))
				getNameLast = "";

			getNumber = txtStudentNumber.getText();
			if (getNumber.equals("ex. 250626000"))
				getNumber = "";

			getEmail = txtStudentEmail.getText();
			if (getEmail.equals("ex. john-doe@example.com"))
				getEmail = "";

			try {
				Integer.parseInt(getNumber);
				boolFormat = true;
			} catch (NumberFormatException e) {
				boolFormat = false;
			}

			if (getNameFirst.equals("")
					|| (nameFirst = elimSpaces(getNameFirst)) == null) {
				lblStudentAddErrorLog
						.setText("Please enter a student first name.");
				txtStudentNameFirst.setBorder(errorHighlightBorder);
			} else if (getNameLast.equals("")
					|| (nameLast = elimSpaces(getNameLast)) == null) {
				lblStudentAddErrorLog
						.setText("Please enter a student last name.");
				txtStudentNameLast.setBorder(errorHighlightBorder);
			} else if (getNumber.equals("")
					|| (number = elimSpaces(getNumber)) == null) {
				lblStudentAddErrorLog.setText("Please enter a student number.");
				txtStudentNumber.setBorder(errorHighlightBorder);
			} else if (!boolFormat) {
				lblStudentAddErrorLog
						.setText("Please enter a valid student number.");
				txtStudentNumber.setBorder(errorHighlightBorder);
			} else if (getEmail.equals("")
					|| (email = elimSpaces(getEmail)) == null) {
				lblStudentAddErrorLog.setText("Please enter a student email.");
				txtStudentEmail.setBorder(errorHighlightBorder);
			} else {
				if (!currCourse.addStudent(nameFirst, nameLast, number, email))
					lblStudentAddErrorLog.setText("Student Already exists!");
				else {
					closeFrame(addFrame);
					restPnlStudent();
					updateInfo();
				}
			}
		}
	}// GEN-LAST:event_lblAddStudentMouseClicked

	/****************************************************************************************************
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 * 
	 * End: Add New Student Panel
	 * 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 */

	/****************************************************************************************************
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 * 
	 * Start: Helper Methods
	 * 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 */

	private void tabSwitch(int i) {
		hideMenu();
		if (i == 0) {
			lblTabGrades.setIcon(new javax.swing.ImageIcon(getClass()
					.getResource("/cs2212/team4/tabOn.png")));
			lblTabSetup.setIcon(new javax.swing.ImageIcon(getClass()
					.getResource("/cs2212/team4/tabOff.png")));
			lblTabDropbox.setIcon(new javax.swing.ImageIcon(getClass()
					.getResource("/cs2212/team4/tabOff.png")));
			tabGrades.setVisible(true);
			tabSetup.setVisible(false);
			tabDropbox.setVisible(false);
		} else if (i == 1) {
			lblTabGrades.setIcon(new javax.swing.ImageIcon(getClass()
					.getResource("/cs2212/team4/tabOff.png")));
			lblTabSetup.setIcon(new javax.swing.ImageIcon(getClass()
					.getResource("/cs2212/team4/tabOn.png")));
			lblTabDropbox.setIcon(new javax.swing.ImageIcon(getClass()
					.getResource("/cs2212/team4/tabOff.png")));
			tabGrades.setVisible(false);
			tabSetup.setVisible(true);
			tabDropbox.setVisible(false);
		} else {
			lblTabGrades.setIcon(new javax.swing.ImageIcon(getClass()
					.getResource("/cs2212/team4/tabOff.png")));
			lblTabSetup.setIcon(new javax.swing.ImageIcon(getClass()
					.getResource("/cs2212/team4/tabOff.png")));
			lblTabDropbox.setIcon(new javax.swing.ImageIcon(getClass()
					.getResource("/cs2212/team4/tabOn.png")));
			tabGrades.setVisible(false);
			tabSetup.setVisible(false);
			tabDropbox.setVisible(true);
		}
	}

	private String elimSpaces(String str) {
		int i = 0;
		for (; i < str.length(); i++)
			if (str.charAt(i) != ' ')
				break;
		if (i == str.length())
			return null;
		return str.substring(i);
	}

	private void hideMenu() {
		pnlCourseMenu.setVisible(false);
	}

	private void updateInfo() {
		Deliverable deliver;

		if (currCourse != null) {

			/** GRADES TAB */
			lblActiveCourseTitleInfo.setText(currCourse.getTitle());
			lblActiveCourseInfo.setText(currCourse.getCode()
					+ currCourse.getTerm());
			courseName.setText(currCourse.getTitle());

			updateTables();
			lblDeleteSure.setText(" ");

			listDelivers.clear();
			for (int i = 0; i < currCourse.getDeliverableListSize(); i++) {
				deliver = currCourse.getDeliverable(i);
				if (deliver != null)
					listDelivers.addElement(deliver.getName() + ", "
							+ deliver.getType() + ", " + deliver.getWeight());
			}

			if (firstName == 1)
				lblFirstName.setBorder(BorderFactory
						.createLineBorder(new Color(20, 150, 250)));
			if (lastName == 1)
				lblLastName.setBorder(BorderFactory.createLineBorder(new Color(
						20, 150, 250)));
			if (number == 1)
				lblNumber.setBorder(BorderFactory.createLineBorder(new Color(
						20, 150, 250)));
			if (email == 1)
				lblEmail.setBorder(BorderFactory.createLineBorder(new Color(20,
						150, 250)));
			if (asnAvg == 1)
				lblAsnAvg.setBorder(BorderFactory.createLineBorder(new Color(
						20, 150, 250)));
			if (exmAvg == 1)
				lblExmAvg.setBorder(BorderFactory.createLineBorder(new Color(
						20, 150, 250)));

			lblGradesErrorLog.setText("");

			lblCourseSetup.setText("Course Setup: " + currCourse.getTitle()
					+ ", " + currCourse.getCode() + currCourse.getTerm());

			if (currCourse.getDescription().equals(""))
				txtCourseDesc.setText("Enter a course description...");
			else
				txtCourseDesc.setText(currCourse.getDescription());

			txtEditCourseTitle.setText(currCourse.getTitle());
			txtEditCourseCode.setText(currCourse.getCode());
			comboEditCourseTerm.setSelectedItem(currCourse.getTerm());

			lblSetupErrorLog.setText("");

			getContentPane().setBackground(currCourse.getColor());
		} else {
			lblActiveCourseTitleInfo.setText("Select a course");
			lblActiveCourseInfo.setText("");
			courseName.setText("select course");

			listDelivers.clear();
			updateTables();

			lblGradesErrorLog.setText("");

			lblCourseSetup.setText("Course Setup: ");

			txtCourseDesc.setText("Enter a course description...");
			txtEditCourseTitle.setText("Please select a course");
			txtEditCourseCode.setText("Please select a course");
			comboEditCourseTerm.setSelectedItem(0);

			lblSetupErrorLog.setText("");

			getContentPane().setBackground(new Color(20, 150, 250));
		}
	}

	private void restPnlCourse() {
		txtCourseName.setText("ex. Computer Engineering");
		txtCourseName.setForeground(new Color(204, 204, 204));

		txtCourseCode.setText("ex. CS2212");
		txtCourseCode.setForeground(new Color(204, 204, 204));

		comboCourseTerm.setSelectedIndex(0);

		txtCourseName.setBorder(defaultHighlightBorder);
		txtCourseCode.setBorder(defaultHighlightBorder);

		lblCourseAddErrorLog.setText("");

		pnlAddCourse.setVisible(false);
	}

	private void restPnlDeliver() {
		txtDeliverName.setText("ex. Group Project");
		txtDeliverName.setForeground(new Color(204, 204, 204));

		txtDeliverWeight.setText("ex. 54");
		txtDeliverWeight.setForeground(new Color(204, 204, 204));

		comboDeliverType.setSelectedIndex(0);

		txtDeliverName.setBorder(defaultHighlightBorder);
		txtDeliverWeight.setBorder(defaultHighlightBorder);

		addDeliverErrorLog.setText("");

		pnlAddDeliver.setVisible(false);
	}

	private void restPnlStudent() {
		txtStudentNameFirst.setText("ex. John");
		txtStudentNameFirst.setForeground(new Color(204, 204, 204));

		txtStudentNameLast.setText("ex. Doe");
		txtStudentNameLast.setForeground(new Color(204, 204, 204));

		txtStudentNumber.setText("ex. 250626000");
		txtStudentNumber.setForeground(new Color(204, 204, 204));

		txtStudentEmail.setText("ex. john-doe@example.com");
		txtStudentEmail.setForeground(new Color(204, 204, 204));

		txtStudentNameFirst.setBorder(defaultHighlightBorder);
		txtStudentNameLast.setBorder(defaultHighlightBorder);
		txtStudentNumber.setBorder(defaultHighlightBorder);
		txtStudentEmail.setBorder(defaultHighlightBorder);

		lblStudentAddErrorLog.setText("");
		pnlAddStudent.setVisible(false);
	}

	private void restPnlEmail() {
		txtSubject.setForeground(Color.lightGray);
		txtSubject.setText("Please enter an email subject...");

		msgText.setForeground(Color.lightGray);
		msgText.setText("Please enter a message content...");

		butToggle = false;
		lblToggle.setBorder(BorderFactory.createLineBorder(new java.awt.Color(
				204, 204, 204)));
		lblSendEmail.setBorder(BorderFactory
				.createLineBorder(new java.awt.Color(204, 204, 204)));
		lblEmailErrorLog.setText("");

		pnlEmail.setVisible(false);
	}

	private void closeFrame(JFrame frame) {
		WindowEvent closingFrame = new WindowEvent(frame,
				WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue()
				.postEvent(closingFrame);
	}

	private void updateTables() {
		if (currCourse != null) {
			makeTables();
			studentTable.setModel(tableStudents);
			gradesTable.setModel(tableGrades);
			initTables();
		} else {
			currCourse = new Course("", "", "");
			makeTables();
			studentTable.setModel(tableStudents);
			gradesTable.setModel(tableGrades);
			initTables();
			currCourse = null;
		}
		updateAvglbl();
	}

	private void makeTables() {
		tableStudents = new UsersTable(currCourse, firstName, lastName, email,
				number, asnAvg, exmAvg);
		tableStudents.addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				updateTables();
			}
		});
		tableStudents.getGradesTable().addTableModelListener(
				new TableModelListener() {
					@Override
					public void tableChanged(TableModelEvent e) {
						updateTables();
					}
				});
		tableGrades = tableStudents.getGradesTable();
	}

	private void initTables() {
		// Create a fixed-size table (adjusting to view settings/number of
		// columns)
		studentTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		if (gradesTable.getColumnCount() + studentTable.getColumnCount() < 12)
			gradesTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		else
			gradesTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		sizeTables();

		studentTable.setPreferredScrollableViewportSize(studentTable
				.getPreferredSize());
		gradesTable.setPreferredScrollableViewportSize(gradesTable
				.getPreferredSize());
		studentScroll.getVerticalScrollBar().setPreferredSize(
				new Dimension(0, 0));
		studentScroll.getVerticalScrollBar().setModel(
				gradesScroll.getVerticalScrollBar().getModel());
		gradesScroll.getVerticalScrollBar().setPreferredSize(
				new Dimension(15, 5));
		gradesScroll.setWheelScrollingEnabled(true);

		gradesScroll.getViewport().setBackground(Color.white);
		studentScroll.getViewport().setBackground(Color.white);

		MatteBorder border = new MatteBorder(0, 0, 0, 1, new Color(196, 196,
				196));
		studentTable.setBorder(border);

		gradesTable
				.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		gradesTable.setDefaultRenderer(Object.class, new GradeCellRenderer());
		studentTable
				.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		deliverList
				.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		gradesTable.setSelectionModel(studentTable.getSelectionModel());
		gradesTable.getTableHeader().setReorderingAllowed(false);
	}

	private void sizeTables() {
		if (firstName == 1)
			studentTable.getColumn("First Name").setPreferredWidth(75);
		if (lastName == 1)
			studentTable.getColumn("Last Name").setPreferredWidth(75);
		if (number == 1)
			studentTable.getColumn("Student #").setPreferredWidth(75);
		if (email == 1)
			studentTable.getColumn("Email").setPreferredWidth(100);

		for (int i = 0; i < gradesTable.getColumnCount(); i++) {
			if (i < 1 + asnAvg + exmAvg)
				gradesTable.getColumnModel().getColumn(i).setMinWidth(60);
			else
				gradesTable.getColumnModel().getColumn(i)
						.setPreferredWidth(120);
		}
	}

	private Integer findDeliver(String deliver) {
		String name = "", type = "", w = "";
		Double weight;
		int i = 0;
		while (deliver.charAt(i) != ',') {
			name += deliver.charAt(i);
			i++;
		}
		i += 2;
		while (deliver.charAt(i) != ',') {
			type += deliver.charAt(i);
			i++;
		}
		i += 2;
		while (i < deliver.length()) {
			w += deliver.charAt(i);
			i++;
		}
		weight = Double.parseDouble(w);
		return currCourse
				.findDeliverable(new Deliverable(name, type, weight, 0));
	}

	public void updateAvglbl() {
		if (currCourse != null && currCourse.getClassAvg() >= 0) {
			lblCourseAvg
					.setText(String.format("%.2f", currCourse.getClassAvg())
							+ "%");

			if (currCourse.getClassAsnAvg() >= 0)
				lblCourseAsnAvg.setText(String.format("%.2f",
						currCourse.getClassAsnAvg())
						+ "%");
			else
				lblCourseAsnAvg.setText("-- %");
			if (currCourse.getClassExamAvg() >= 0)
				lblCourseExamAvg.setText(String.format("%.2f",
						currCourse.getClassExamAvg())
						+ "%");
			else
				lblCourseExamAvg.setText("-- %");
		} else {
			lblCourseAvg.setText("-- %");
			lblCourseAsnAvg.setText("-- %");
			lblCourseExamAvg.setText("-- %");
		}
	}

	/****************************************************************************************************
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 * 
	 * End: Helper Methods
	 * 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 */

	/****************************************************************************************************
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 * End: Extras
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 **************************************************************************************************** 
	 */

	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed"
		// desc=" Look and feel setting code (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the
		 * default look and feel. For details see
		 * http://download.oracle.com/javase
		 * /tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(GradebookGUI.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(GradebookGUI.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(GradebookGUI.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(GradebookGUI.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new GradebookGUI().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JLabel addCourse;
	private javax.swing.JLabel addDeliver;
	private javax.swing.JLabel addDeliverErrorLog;
	private javax.swing.JLabel addStudent;
	private javax.swing.JComboBox comboCourseTerm;
	private javax.swing.JComboBox comboDeliverType;
	private javax.swing.JComboBox comboEditCourseTerm;
	private javax.swing.JComboBox comboEditDeliverType;
	private javax.swing.JComboBox comboEmail;
	private javax.swing.JLayeredPane container;
	private javax.swing.JLabel courseAsnAvg;
	private javax.swing.JLabel courseAvg;
	private javax.swing.JScrollPane courseDescScroll;
	private javax.swing.JLabel courseExamAvg;
	private javax.swing.JList courseMenuList;
	private javax.swing.JLabel courseName;
	private javax.swing.JScrollPane courses;
	private javax.swing.JLabel customizeSMTP;
	private javax.swing.JLabel deleteDeliver;
	private javax.swing.JLabel deleteStudent;
	private javax.swing.JList deliverList;
	private javax.swing.JScrollPane deliversScroll;
	private javax.swing.JLabel editCourseIcon;
	private javax.swing.JList editDeliverList;
	private javax.swing.JScrollPane editDeliverListScroll;
	private javax.swing.JScrollPane gradesScroll;
	private javax.swing.JTable gradesTable;
	private javax.swing.JLabel lblActiveCourse;
	private javax.swing.JLabel lblActiveCourseInfo;
	private javax.swing.JLabel lblActiveCourseTitleInfo;
	private javax.swing.JLabel lblAddCourse;
	private javax.swing.JLabel lblAddCourseTitle;
	private javax.swing.JLabel lblAddDeliver;
	private javax.swing.JLabel lblAddDeliverTitle;
	private javax.swing.JLabel lblAddStudent;
	private javax.swing.JLabel lblAddStudentTitle;
	private javax.swing.JLabel lblAsnAvg;
	private javax.swing.JLabel lblBlue;
	private javax.swing.JLabel lblCalcTitle;
	private javax.swing.JLabel lblCalculate;
	private javax.swing.JLabel lblCalculator;
	private javax.swing.JLabel lblCancelCourseAddition;
	private javax.swing.JLabel lblCancelDeliverAddition;
	private javax.swing.JLabel lblCancelStudentAddition;
	private javax.swing.JLabel lblCobalt;
	private javax.swing.JLabel lblCourseAddErrorLog;
	private javax.swing.JLabel lblCourseAsnAvg;
	private javax.swing.JLabel lblCourseAvg;
	private javax.swing.JLabel lblCourseCode;
	private javax.swing.JLabel lblCourseDeliverables;
	private javax.swing.JLabel lblCourseExamAvg;
	private javax.swing.JLabel lblCourseName;
	private javax.swing.JLabel lblCourseSetup;
	private javax.swing.JLabel lblCourseTerm;
	private javax.swing.JLabel lblCustomSubmit;
	private javax.swing.JLabel lblCutomTitle;
	private javax.swing.JLabel lblDbxActivate;
	private javax.swing.JLabel lblDbxDownload;
	private javax.swing.JLabel lblDbxErrorLog;
	private javax.swing.JLabel lblDbxSubmit;
	private javax.swing.JLabel lblDbxUpload;
	private javax.swing.JLabel lblDbxVerify;
	private javax.swing.JLabel lblDeleteCourse;
	private javax.swing.JLabel lblDeleteSure;
	private javax.swing.JLabel lblDeliverName;
	private javax.swing.JLabel lblDeliverType;
	private javax.swing.JLabel lblDeliverWeight;
	private javax.swing.JLabel lblDropbox;
	private javax.swing.JLabel lblEMLErrorLog;
	private javax.swing.JLabel lblEditCourse;
	private javax.swing.JLabel lblEditCourseCode;
	private javax.swing.JLabel lblEditCourseTerm;
	private javax.swing.JLabel lblEditCourseTtile;
	private javax.swing.JLabel lblEditDeleteDeliver;
	private javax.swing.JLabel lblEditDeliver;
	private javax.swing.JLabel lblEditDeliverName;
	private javax.swing.JLabel lblEditDeliverType;
	private javax.swing.JLabel lblEditDeliverWeight;
	private javax.swing.JLabel lblEmail;
	private javax.swing.JLabel lblEmailAddress;
	private javax.swing.JLabel lblEmailAddress1;
	private javax.swing.JLabel lblEmailErrorLog;
	private javax.swing.JLabel lblEmailStudents;
	private javax.swing.JLabel lblEmailVarify;
	private javax.swing.JLabel lblEnterAvg;
	private javax.swing.JLabel lblExit;
	private javax.swing.JLabel lblExmAvg;
	private javax.swing.JLabel lblExportDelivers;
	private javax.swing.JLabel lblExportGrades;
	private javax.swing.JLabel lblExportStudents;
	private javax.swing.JLabel lblFirstName;
	private javax.swing.JLabel lblGrades;
	private javax.swing.JLabel lblGradesErrorLog;
	private javax.swing.JLabel lblHostName1;
	private javax.swing.JLabel lblHostName3;
	private javax.swing.JLabel lblImportDelivers;
	private javax.swing.JLabel lblImportGrades;
	private javax.swing.JLabel lblImportStudents;
	private javax.swing.JLabel lblLastName;
	private javax.swing.JLabel lblMagenta;
	private javax.swing.JLabel lblMauve;
	private javax.swing.JLabel lblMini;
	private javax.swing.JLabel lblNumber;
	private javax.swing.JLabel lblOlive;
	private javax.swing.JLabel lblPassword;
	private javax.swing.JLabel lblPassword1;
	private javax.swing.JLabel lblRecipients;
	private javax.swing.JTextPane lblResult;
	private javax.swing.JLabel lblSMTPError;
	private javax.swing.JLabel lblSendEmail;
	private javax.swing.JLabel lblSetup;
	private javax.swing.JLabel lblSetupErrorLog;
	private javax.swing.JLabel lblSignin;
	private javax.swing.JLabel lblSteel;
	private javax.swing.JLabel lblStudentAddErrorLog;
	private javax.swing.JLabel lblStudentEmail;
	private javax.swing.JLabel lblStudentNameFirst;
	private javax.swing.JLabel lblStudentNameLast;
	private javax.swing.JLabel lblStudentNumber;
	private javax.swing.JLabel lblTabDropbox;
	private javax.swing.JLabel lblTabGrades;
	private javax.swing.JLabel lblTabSetup;
	private javax.swing.JLabel lblTeal;
	private javax.swing.JLabel lblToggle;
	private javax.swing.JLabel lblViolet;
	private javax.swing.JLabel lblWestern;
	private javax.swing.JLayeredPane lyrActiveCourse;
	private javax.swing.JScrollPane msgScroll;
	private javax.swing.JTextArea msgText;
	private javax.swing.JLabel myCourses;
	private javax.swing.JPanel pnlAddCourse;
	private javax.swing.JPanel pnlAddCourseContainer;
	private javax.swing.JPanel pnlAddDeliver;
	private javax.swing.JPanel pnlAddStudent;
	private javax.swing.JPanel pnlAddStudentContainer;
	private javax.swing.JPanel pnlCalc;
	private javax.swing.JPanel pnlCourseMenu;
	private javax.swing.JPanel pnlCustom;
	private javax.swing.JPanel pnlEmail;
	private javax.swing.JPanel pnlTables;
	private javax.swing.JLabel recipients;
	private javax.swing.JScrollPane resultScroll;
	private javax.swing.JScrollPane studentScroll;
	private javax.swing.JTable studentTable;
	private javax.swing.JLabel subject;
	private javax.swing.JPanel tabDropbox;
	private javax.swing.JPanel tabGrades;
	private javax.swing.JPanel tabSetup;
	private javax.swing.JLabel toggle;
	private javax.swing.JTextField txtAvg;
	private javax.swing.JTextField txtCourseCode;
	private javax.swing.JTextPane txtCourseDesc;
	private javax.swing.JTextField txtCourseName;
	private javax.swing.JTextField txtCustomEmail;
	private javax.swing.JPasswordField txtCustomPassword;
	private javax.swing.JTextField txtDbxCode;
	private javax.swing.JTextField txtDeliverName;
	private javax.swing.JTextField txtDeliverWeight;
	private javax.swing.JTextField txtEditCourseCode;
	private javax.swing.JTextField txtEditCourseTitle;
	private javax.swing.JTextField txtEditDeliverName;
	private javax.swing.JTextField txtEditDeliverWeight;
	private javax.swing.JTextField txtEmail;
	private javax.swing.JTextField txtHostName;
	private javax.swing.JPasswordField txtPassword;
	private javax.swing.JTextField txtPort;
	private javax.swing.JTextField txtStudentEmail;
	private javax.swing.JTextField txtStudentNameFirst;
	private javax.swing.JTextField txtStudentNameLast;
	private javax.swing.JTextField txtStudentNumber;
	private javax.swing.JTextField txtSubject;
	// End of variables declaration//GEN-END:variables
}
