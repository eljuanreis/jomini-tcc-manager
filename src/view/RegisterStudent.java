package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.StudentController;
import service.ValidateField;
import threads.TimeThread;

import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import constants.Configs;

import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RegisterStudent extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private StudentController controller;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterStudent frame = new RegisterStudent();
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
	 * @throws InterruptedException 
	 */
	public RegisterStudent() throws InterruptedException {
		setTitle("Cadastro de estudantes - " + Configs.name);
		setResizable(false);
		this.controller = new StudentController();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cadastrar aluno");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 11, 157, 14);
		getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 61, 60, 71);
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(2, 2, 0, 10));
		
		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 12));
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("RA");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 12));
		panel.add(lblNewLabel_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(105, 61, 319, 71);
		getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(2, 2, 0, 20));
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (!ValidateField.validateInteger(e.getKeyChar())) {
					e.consume();
				}
			}
		});
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Por favor, preencha os dados abaixo:");
		lblNewLabel_3.setBounds(10, 36, 182, 14);
		getContentPane().add(lblNewLabel_3);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 195, 414, 29);
		getContentPane().add(panel_2);
		panel_2.setLayout(new GridLayout(1, 2, 50, 0));
		
		JButton btnNewButton = new JButton("Voltar");
		panel_2.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Salvar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		panel_2.add(btnNewButton_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 235, 414, 15);
		getContentPane().add(separator);
		
		JLabel labelDate = new JLabel("....");
		labelDate.setHorizontalTextPosition(SwingConstants.LEFT);
		labelDate.setHorizontalAlignment(SwingConstants.RIGHT);
		labelDate.setBounds(242, 236, 182, 14);
		getContentPane().add(labelDate);
	
		TimeThread timeThread = new TimeThread(labelDate);
		timeThread.start();

		JLabel softwareVersionLabel = new JLabel(Configs.version);
		softwareVersionLabel.setBounds(10, 236, 46, 14);
		getContentPane().add(softwareVersionLabel);
	}
	
	private void save() {
		String nome = this.textField.getText();
		String RA = this.textField_1.getText();
		
		String[] data = new String[2];
		data[0] = nome;
		data[1] = RA;
		
		this.controller.save(data);
	}
}
