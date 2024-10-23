package app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private static Azad blockmessage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					blockmessage=new Azad();
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
	public Login() {
		setTitle("BlockMessage");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 335, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblLoginToBlockmessage = new JLabel("Login To BlockMessage");
		lblLoginToBlockmessage.setFont(new Font("Stencil", Font.PLAIN, 13));
		lblLoginToBlockmessage.setBounds(62, 41, 168, 68);
		panel.add(lblLoginToBlockmessage);
		
		textField = new JTextField();
		textField.setBounds(28, 151, 242, 19);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(28, 128, 45, 13);
		panel.add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(28, 210, 242, 19);
		panel.add(passwordField);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(28, 193, 45, 13);
		panel.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    if (textField.getText().equals("Azad")) {
			        char[] password = passwordField.getPassword();
			        String passwordStr = new String(password);
			        if (passwordStr.equals("123")) {
			            blockmessage.setVisible(true);
			            Login.this.setVisible(false);
			        } else {
			        	lblLoginToBlockmessage.setText("wrong password \n please try again");
			        }
			    } else {
			    	lblLoginToBlockmessage.setText("user is not found \n  please try again");
			    }
			}
		});
		btnNewButton.setBounds(107, 268, 85, 21);
		panel.add(btnNewButton);
	}

}
