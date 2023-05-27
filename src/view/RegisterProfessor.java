package view;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import components.SaveButton;
import constants.Configs;
import controller.RegisterProfessorController;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;
import javax.swing.JSeparator;
import components.ActualDate;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
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
	 */
	public RegisterProfessor() {
		setTitle("Cadastro de professor - " + Configs.name);
		setResizable(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 348, 411);
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
		textField.setBounds(22, 79, 284, 20);
		contentPane.add(textField);

		JPanel panelAreas = new JPanel();
		panelAreas.setBounds(22, 137, 284, 146);
		contentPane.add(panelAreas);
		panelAreas.setLayout(null);
		
		this.controller = new RegisterProfessorController(textField);

		addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                String[] options = Configs.areas;
                
                int initialPosition = 0;
                int length = options.length;
              
                for (int i = 0; i < length; i++) {
              		JCheckBox chckbxOpt = new JCheckBox(options[i]);
              		chckbxOpt.setName("checkbox_" + i);
            		chckbxOpt.setBounds(0, initialPosition, 200, 23);
            		chckbxOpt.addActionListener(controller);

            		panelAreas.add(chckbxOpt);
            		
            		initialPosition += 25;
                }

            }
        });

		JButton btnNewButton = new SaveButton("Professor", "salvo");
		btnNewButton.addActionListener(this.controller);
		btnNewButton.setBounds(22, 294, 284, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Áreas");
		lblNewLabel_2.setBounds(22, 112, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(22, 346, 296, 15);
		contentPane.add(separator);
		
		ActualDate labelDate = new ActualDate("....");
		labelDate.setHorizontalTextPosition(SwingConstants.LEFT);
		labelDate.setHorizontalAlignment(SwingConstants.RIGHT);
		labelDate.setBounds(135, 347, 182, 14);
		contentPane.add(labelDate);
		
		JLabel softwareVersionLabel = new JLabel(Configs.version);
		softwareVersionLabel.setBounds(22, 347, 46, 14);
		contentPane.add(softwareVersionLabel);
	}
}
