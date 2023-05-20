package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.RegisterProfessorController;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class RegisterProfessor extends JFrame {

	private RegisterProfessorController controller;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterProfessor frame = new RegisterProfessor();
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
	public RegisterProfessor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 393);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Registrar Professor");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(22, 26, 201, 26);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setBounds(22, 65, 46, 14);
		contentPane.add(lblNewLabel_1);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(22, 79, 160, 20);
		contentPane.add(textField);

		this.controller = new RegisterProfessorController(textField);

		addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                String[] options = RegisterProfessorController.areas;
                
                int initialPosition = 140;
                int length = options.length;
              
                for (int i = 0; i < length; i++) {
              		JCheckBox chckbxOpt = new JCheckBox(options[i]);
              		chckbxOpt.setName("checkbox_" + i);
            		chckbxOpt.setBounds(22, initialPosition, 200, 23);
            		chckbxOpt.addActionListener(controller);

            		contentPane.add(chckbxOpt);
            		
            		initialPosition += 25;
                }

            }
        });

		JButton btnNewButton = new JButton("Salvar");
		btnNewButton.addActionListener(this.controller);
		btnNewButton.setBounds(334, 319, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Áreas");
		lblNewLabel_2.setBounds(22, 112, 46, 14);
		contentPane.add(lblNewLabel_2);
	}
}
