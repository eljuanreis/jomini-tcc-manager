package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import components.ActualDate;
import components.SaveButton;
import constants.Configs;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;

import controller.GroupController;
import controller.IndexController;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class CreateGroup extends JFrame {

	private GroupController controller;
	private JPanel contentPane;
	private JTextField tema;
	private DefaultComboBoxModel<String> modelCombox = new DefaultComboBoxModel<String>();
	private DefaultComboBoxModel<String> modelAreas = new DefaultComboBoxModel<String>();
	private DefaultComboBoxModel<String> modelProfessors = new DefaultComboBoxModel<String>();
	private DefaultListModel<String> modelList = new DefaultListModel<String>();
	private JTextField searchStudent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateGroup frame = new CreateGroup();
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
	public CreateGroup() {
		setTitle("Cadastro de grupo - " + Configs.name);
		setResizable(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 477, 449);
		this.setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Criar Grupo");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 11, 122, 20);
		contentPane.add(lblNewLabel);

		tema = new JTextField();
		tema.setColumns(10);
		tema.setBounds(10, 325, 441, 20);
		contentPane.add(tema);

		JComboBox<String> areas = new JComboBox<String>(modelAreas);
		areas.setBounds(10, 277, 200, 22);
		contentPane.add(areas);

		JLabel lblNewLabel_2 = new JLabel("Área de Trabalho");
		lblNewLabel_2.setBounds(10, 263, 160, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Professor");
		lblNewLabel_2_1.setBounds(251, 263, 160, 14);
		contentPane.add(lblNewLabel_2_1);

		JComboBox<String> comboBox_1 = new JComboBox<String>(modelProfessors);
		comboBox_1.setBounds(251, 277, 200, 22);
		contentPane.add(comboBox_1);

		JButton criarBtn = new SaveButton("Grupo", "salvo");
		criarBtn.setBounds(10, 356, 200, 23);
		contentPane.add(criarBtn);

		JLabel lblNewLabel_4 = new JLabel("Alunos Adicionados - clique duas vezes para remover");
		lblNewLabel_4.setBounds(10, 143, 431, 14);
		contentPane.add(lblNewLabel_4);

		JPanel panel = new JPanel();
		panel.setBounds(0, 42, 451, 83);
		contentPane.add(panel);
		panel.setLayout(null);

		JComboBox<String> listOfStudents = new JComboBox<String>(modelCombox);
		listOfStudents.setToolTipText("");
		listOfStudents.setBounds(175, 20, 266, 25);
		panel.add(listOfStudents);

		JLabel lblNewLabel_1 = new JLabel("Aluno");
		lblNewLabel_1.setBounds(10, 0, 46, 14);
		panel.add(lblNewLabel_1);

		searchStudent = new JTextField();
		searchStudent.setBounds(10, 21, 155, 24);
		panel.add(searchStudent);
		searchStudent.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 158, 454, 94);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 424, 61);
		panel_1.add(scrollPane);

		// Remove ao clicar
		JList<String> list = new JList<String>();
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 && !e.isConsumed()) {
					int index = list.getSelectedIndex();

					controller.removeStudent(index);
				}
			}
		});
		list.setModel(this.modelList);

		scrollPane.setViewportView(list);

		this.controller = new GroupController(modelCombox, searchStudent, modelList, modelAreas, modelProfessors, tema);

		areas.addActionListener(this.controller);
		areas.setName("Areas");

		criarBtn.addActionListener(this.controller);

		JButton btnAddStudent = new JButton("Adicionar");
		btnAddStudent.setBounds(175, 48, 266, 24);
		panel.add(btnAddStudent);
		btnAddStudent.addActionListener(this.controller);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(this.controller);

		btnPesquisar.setBounds(10, 49, 155, 24);
		panel.add(btnPesquisar);

		JLabel lblNewLabel_1_1 = new JLabel("Nome - RA");
		lblNewLabel_1_1.setBounds(175, 0, 92, 14);
		panel.add(lblNewLabel_1_1);

		JLabel lblNewLabel_5 = new JLabel("Tema");
		lblNewLabel_5.setBounds(10, 307, 46, 14);
		contentPane.add(lblNewLabel_5);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 383, 441, 8);
		contentPane.add(separator);

		JLabel labelDate = new ActualDate("....");
		labelDate.setHorizontalTextPosition(SwingConstants.LEFT);
		labelDate.setHorizontalAlignment(SwingConstants.RIGHT);
		labelDate.setBounds(269, 385, 182, 14);
		contentPane.add(labelDate);

		JLabel softwareVersionLabel = new JLabel(Configs.version);
		softwareVersionLabel.setBounds(10, 385, 46, 14);
		contentPane.add(softwareVersionLabel);
		
		JButton back = new JButton("Voltar para a tela inicial");
		JFrame tela = this;
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IndexController.backToIndex(tela);
			}
		});
		back.setBounds(251, 356, 200, 23);
		contentPane.add(back);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {

				// Carrega lista inicial de estudantes
				String[] students = controller.listStudents("");

				for (String element : students) {
					modelCombox.addElement(element);
				}

				// Carrega lista inicial de áreas
				String[] areas = Configs.areas;

				modelAreas.addElement(" ");

				int cont = 0;
				for (String element : areas) {
					modelAreas.addElement(cont + " - " + element);
					cont++;
				}

			}
		});
	}
}
