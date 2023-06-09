package view;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.IndexController;
import controller.StudentController;
import service.ValidateField;

import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import components.ActualDate;
import components.SaveButton;
import constants.Configs;

import javax.swing.JSeparator;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class RegisterStudent extends JFrame {
	private JTextField nomeAluno;
	private JTextField raAluno;
	private StudentController controller;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterStudent frame = new RegisterStudent();
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
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);

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
		
		nomeAluno = new JTextField();
		panel_1.add(nomeAluno);
		nomeAluno.setColumns(10);
		
		raAluno = new JTextField();
		raAluno.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (!ValidateField.validateInteger(e.getKeyChar())) {
					e.consume();
				}
			}
		});
		panel_1.add(raAluno);
		raAluno.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Por favor, preencha os dados abaixo:");
		lblNewLabel_3.setBounds(10, 36, 182, 14);
		getContentPane().add(lblNewLabel_3);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 195, 414, 29);
		getContentPane().add(panel_2);
		panel_2.setLayout(new GridLayout(1, 2, 50, 0));
		
		this.controller = new StudentController(this.nomeAluno, this.raAluno);
		
		JButton btnNewButton = new JButton("Voltar para tela principal");
		JFrame telaAtual = (JFrame) this;

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IndexController.backToIndex(telaAtual);
			}
		});
		
		JButton btnNewButton_1 = new SaveButton("Aluno", "salvo");
		btnNewButton_1.addActionListener(this.controller);
		panel_2.add(btnNewButton_1);
		panel_2.add(btnNewButton);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 235, 414, 15);
		getContentPane().add(separator);
		
		JLabel labelDate = new ActualDate("....");
		labelDate.setHorizontalTextPosition(SwingConstants.LEFT);
		labelDate.setHorizontalAlignment(SwingConstants.RIGHT);
		labelDate.setBounds(242, 236, 182, 14);
		getContentPane().add(labelDate);

		JLabel softwareVersionLabel = new JLabel(Configs.version);
		softwareVersionLabel.setBounds(10, 236, 46, 14);
		getContentPane().add(softwareVersionLabel);
	}
}
