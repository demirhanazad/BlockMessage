package app;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingUtilities;

public class Firstpage extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private static Login loginpage;
    private static Register registerpage;
    private static ImageIcon image = new ImageIcon("logo.png");;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Firstpage frame = new Firstpage();
                    loginpage = new Login(image);
                    registerpage = new Register(image);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Firstpage() {
    	setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 335, 375);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        setLocationRelativeTo(null);
        contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		setIconImage(image.getImage());
        JPanel panel = new JPanel();
        contentPane.add(panel);
        panel.setLayout(null);

        JButton btnNewButton = new JButton("Login");
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        if (loginpage != null) {
                        	loginpage.setLocationRelativeTo(null);
                            loginpage.setVisible(true);
                            Firstpage.this.setVisible(false);
                        } else {
                            System.out.println("Login sayfası başlatılamadı.");
                        }
                    }
                });
            }
        });
        btnNewButton.setBounds(37, 159, 239, 38);
        panel.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Register");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        if (registerpage != null) {
                        	registerpage.setLocationRelativeTo(null);
                            registerpage.setVisible(true);
                            Firstpage.this.setVisible(false);
                        } else {
                            System.out.println("Register sayfası başlatılamadı.");
                        }
                    }
                });
            }
        });
        btnNewButton_1.setBounds(37, 235, 239, 38);
        panel.add(btnNewButton_1);

        JLabel lblWelcomeToBlockmessage = new JLabel("Welcome To BlockMessage");
        lblWelcomeToBlockmessage.setFont(new Font("Stencil", Font.PLAIN, 13));
        lblWelcomeToBlockmessage.setBounds(59, 57, 187, 57);
        panel.add(lblWelcomeToBlockmessage);
    }
}
