package view;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ConsultGroupController;
import threads.TimeThread;

import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import constants.Configs;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class ConsultGroups extends JFrame {

	private ConsultGroupController controller;
	private JPanel contentPane;
	private DefaultComboBoxModel<String> modelAreas = new DefaultComboBoxModel<String>();
	private JTable table;
	private DefaultTableModel modelTable = new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultGroups frame = new ConsultGroups();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ConsultGroups() {
		setTitle("Consulta de grupo - " + Configs.name);
		setResizable(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 432);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblConsultarGrupos = new JLabel("Consultar Grupos");
		lblConsultarGrupos.setBounds(10, 11, 175, 31);
		lblConsultarGrupos.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblConsultarGrupos);
		
		JLabel lblNewLabel_2 = new JLabel("Área de Trabalho");
		lblNewLabel_2.setBounds(10, 59, 160, 14);
		contentPane.add(lblNewLabel_2);
		
		JComboBox<String> comboArea = new JComboBox<String>(modelAreas);
		comboArea.setBounds(10, 75, 260, 22);
		comboArea.setName("Areas");
		contentPane.add(comboArea);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 108, 464, 253);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setEnabled(false);
		table.setModel(modelTable);
		scrollPane.setViewportView(table);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 372, 464, 8);
		contentPane.add(separator);
		
		JLabel labelDate = new JLabel("....");
		labelDate.setHorizontalTextPosition(SwingConstants.LEFT);
		labelDate.setHorizontalAlignment(SwingConstants.RIGHT);
		labelDate.setBounds(292, 372, 182, 14);
		contentPane.add(labelDate);
		
		TimeThread timeThread = new TimeThread(labelDate);
		timeThread.start();

		JLabel softwareVersionLabel = new JLabel(Configs.version);
		softwareVersionLabel.setBounds(10, 374, 46, 14);
		contentPane.add(softwareVersionLabel);

		//controller
		this.controller = new ConsultGroupController(this.modelTable);

		//listeners
		comboArea.addActionListener(this.controller);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				// Carrega lista inicial de áreas
				String[] areas = Configs.areas;

				modelAreas.addElement(" ");
				
				int i = 0;
				for (String element : areas) {
					modelAreas.addElement(i + " - " + element);
					i++;
				}
				
				// Popula os grupos na tabela
				controller.initListingTable(controller.loadGroups("", true));

			}
		});
	
	}
}
