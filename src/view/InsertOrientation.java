package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.InsertOrientationController;
import threads.TimeThread;

import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import constants.Configs;

import javax.swing.JSeparator;

import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import java.awt.Cursor;

@SuppressWarnings("serial")
public class InsertOrientation extends JFrame {
	private InsertOrientationController controller;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertOrientation frame = new InsertOrientation();
					frame.setLocationRelativeTo(null);

					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws InterruptedException
	 */
	public InsertOrientation() throws InterruptedException {
		this.controller = new InsertOrientationController();
		setTitle("Orientações - Trabalho de ED");
		setResizable(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 513, 396);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Orientações");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 11, 157, 14);
		getContentPane().add(lblNewLabel);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 331, 461, 15);
		getContentPane().add(separator);

		JLabel labelDate = new JLabel("....");
		labelDate.setHorizontalTextPosition(SwingConstants.LEFT);
		labelDate.setHorizontalAlignment(SwingConstants.RIGHT);
		labelDate.setBounds(289, 332, 182, 14);
		getContentPane().add(labelDate);

		TimeThread timeThread = new TimeThread(labelDate);
		timeThread.start();

		JLabel softwareVersionLabel = new JLabel(Configs.version);
		softwareVersionLabel.setBounds(10, 332, 46, 14);
		getContentPane().add(softwareVersionLabel);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 61, 471, 231);
		getContentPane().add(tabbedPane);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Consultar", null, panel_1, null);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 446, 162);
		panel_1.add(scrollPane);

		table = this.controller.initListingTable();
		table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Cadastrar", null, panel, null);
		panel.setLayout(null);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBounds(105, 11, 319, 71);
		panel.add(panel_1_1);
		panel_1_1.setLayout(new GridLayout(2, 2, 0, 20));

		textField = new JTextField();
		textField.setColumns(10);
		panel_1_1.add(textField);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		panel_1_1.add(textField_1);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 11, 60, 71);
		panel.add(panel_3);
		panel_3.setLayout(new GridLayout(2, 2, 0, 10));

		JLabel lblNewLabel_1_1 = new JLabel("Título");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 12));
		panel_3.add(lblNewLabel_1_1);

		JLabel lblNewLabel_2_2 = new JLabel("Descrição");
		lblNewLabel_2_2.setFont(new Font("Arial", Font.PLAIN, 12));
		panel_3.add(lblNewLabel_2_2);

		JButton btnNewButton_1 = new JButton("Salvar");
		btnNewButton_1.setBounds(242, 174, 182, 29);
		panel.add(btnNewButton_1);

		JLabel lblNewLabel_1 = new JLabel("Grupo:");
		lblNewLabel_1.setBounds(10, 36, 46, 14);
		getContentPane().add(lblNewLabel_1);
	}
}
