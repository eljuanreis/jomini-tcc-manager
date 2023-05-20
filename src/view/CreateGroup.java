package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;

public class CreateGroup extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 475, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Criar Grupo");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 11, 122, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Aluno");
		lblNewLabel_1.setBounds(10, 48, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(10, 62, 160, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Tema");
		lblNewLabel_1_1.setBounds(10, 174, 46, 14);
		contentPane.add(lblNewLabel_1_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(10, 188, 160, 20);
		contentPane.add(textField_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 149, 160, 22);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel_2 = new JLabel("Área de Trabalho");
		lblNewLabel_2.setBounds(10, 135, 160, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Código");
		lblNewLabel_3.setBounds(354, 47, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setText("00000");
		textPane.setBounds(354, 61, 83, 20);
		contentPane.add(textPane);
		
		JLabel lblNewLabel_2_1 = new JLabel("Professor");
		lblNewLabel_2_1.setBounds(180, 135, 160, 14);
		contentPane.add(lblNewLabel_2_1);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(180, 149, 160, 22);
		contentPane.add(comboBox_1);
		
		JButton btnNewButton = new JButton("Criar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(353, 227, 89, 23);
		contentPane.add(btnNewButton);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setEditable(false);
		textPane_1.setBounds(9, 102, 160, 20);
		contentPane.add(textPane_1);
		
		JLabel lblNewLabel_4 = new JLabel("Alunos Adicionados");
		lblNewLabel_4.setBounds(10, 86, 135, 14);
		contentPane.add(lblNewLabel_4);
		
		JButton btnRemoveStudent = new JButton("Remover");
		btnRemoveStudent.setSelectedIcon(null);
		btnRemoveStudent.setBounds(175, 99, 91, 24);
		contentPane.add(btnRemoveStudent);
		
		JButton btnAddStudent = new JButton("Adicionar");
		btnAddStudent.setBounds(176, 60, 90, 24);
		contentPane.add(btnAddStudent);
	}
}
