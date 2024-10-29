package app;
import Designpattern.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
/*
 * @author ihmus
 * 
 * 
 * P2P and coin transfer main interface
 */
public class Azad extends JFrame {
    private static final long serialVersionUID = 1L;
    private static ImageIcon image;
    private static String username = "Azad";
    private final JSplitPane sp = new JSplitPane();
    private final JPanel leftPanel = new JPanel(); // Panel to display all members
    private final JPanel rightPanel = new JPanel(); // Panel with chat, message input and send button.
    private JScrollPane chatScrollPane = null;
    private static transferframe transfer_frame;
    private final JTextArea inputMessage = new JTextArea();
    private final String PLACEHOLDER_TEXT = "Type your message here...";
    private static JList<String> list = new JList<>(new String[]{"Han", "Demirhan", "Demir","Han", "Demirhan", "Demirhan", "Demir","Han", "Demirhan", "Demirhan", "Demir","Han", "Demirhan", "Demirhan", "Demir","Han", "Demirhan", "Demirhan", "Demir","Han", "Demirhan", "Demirhan", "Demir","Han", "Demirhan", "Demir","Han", "Demirhan", "Demir","Han", "Demirhan", "Demir","Han", "Demirhan", "Demir","Han", "Demirhan", "Demir","Han", "Demirhan", "Demir","Han", "Demirhan", "Demir"});
    private final JPanel chatPanel = new JPanel(); // Chat messages are displayed here
    private final JButton btnsendmessage = new JButton("Gönder");
    private final JButton btnCoinGnder = new JButton("Coin Gönder");
    
    
     public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    image = new ImageIcon("logo.png");
                    Azad frame = new Azad(image, username);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public Azad(ImageIcon image, String username) {
        // Placeholder text handling
        inputMessage.setForeground(Color.gray);
        inputMessage.setText(PLACEHOLDER_TEXT);
        inputMessage.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (inputMessage.getText().equals(PLACEHOLDER_TEXT)) {
                    inputMessage.setText("");
                    inputMessage.setForeground(Color.black);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (inputMessage.getText().isEmpty()) {
                    inputMessage.setForeground(Color.gray);
                    inputMessage.setText(PLACEHOLDER_TEXT);
                }
            }
        });
        Azad.this.setFocusable(true);
        Azad.this.requestFocusInWindow(true);
        init(image, username);
     
    }
    /*
      private void addKeyListenerRecursively(Container container, KeyListener keyListener) { 
    	container.addKeyListener(keyListener);
    	for (Component component : container.getComponents()) {
    		if (component instanceof Container) { 
    			addKeyListenerRecursively((Container) component, keyListener); 
    			} 
    		else { component.addKeyListener(keyListener);
    		}
    	}
    }
    */
	private void init(ImageIcon image, String username) {
        setTitle(String.format("P2P And Blockchain, %s!", username));
        setIconImage(image.getImage());
        setBackground(new Color(128, 128, 128));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 937, 604);
        setMinimumSize(new Dimension(800, 500));
        setLocationRelativeTo(null);

        // Configure JSplitPane
        sp.setOneTouchExpandable(true);
        sp.setDividerLocation(235);
        sp.setLeftComponent(initializeLeftPanel(image));
        sp.setRightComponent(initializeRightPanel(image));

        add(sp);
        
    }

    private JScrollPane initializeLeftPanel(ImageIcon image) {
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollpanel = new JScrollPane(leftPanel);
        scrollpanel.setMinimumSize(new Dimension(200, 0)); // Same as initial divider location of the JSplitPane
        scrollpanel.getVerticalScrollBar().setUnitIncrement(20);
        list.setBackground(new Color(128, 128, 128));
        list.setMaximumSize(new Dimension(Integer.MAX_VALUE, list.getPreferredSize().height)); // Maksimum genişliği ayarlayın
        leftPanel.add(list);
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem menuItem1 = new JMenuItem("Coin Gönder");
        JMenuItem menuItem2 = new JMenuItem("Ara");
        popupMenu.add(menuItem1);
        popupMenu.add(menuItem2);
        list.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    int index = list.locationToIndex(e.getPoint());
                    list.setSelectedIndex(index);
                    list.ensureIndexIsVisible(index);
                    popupMenu.show(list, e.getX(), e.getY());
                }
            }

            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    mousePressed(e);
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
        
        menuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                transfer_frame = new transferframe(image);
                transfer_frame.setLocation(Azad.this.getLocation().x, Azad.this.getLocation().y);
                transfer_frame.setVisible(true);
                inputMessage.setText(list.getSelectedValue()+list.getSelectedIndex());
            }
        });
        return scrollpanel;
    }

    private JPanel initializeRightPanel(ImageIcon image) {
    	komut buttonClickCommand = new ButtonClickCommand(btnCoinGnder);
    	
    	RelativeLayout rightPanelLayout = new RelativeLayout(RelativeLayout.Y_AXIS);
        rightPanelLayout.setFill(true); // Fill components both horizontally AND vertically
        rightPanel.setLayout(rightPanelLayout);
        
        JPanel topRight = new JPanel(new BorderLayout()); // Use BorderLayout so that messages can cover entire width (by adding to North)
        chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));
        topRight.add(chatPanel, BorderLayout.NORTH);
        chatScrollPane = new JScrollPane(topRight);
        chatScrollPane.getVerticalScrollBar().setUnitIncrement(20); // Increase scroll speed
        
        rightPanel.add(chatScrollPane, 0.8f);
        
        RelativeLayout inputPanelLayout = new RelativeLayout();
        inputPanelLayout.setFill(true);
        JPanel inputPanel = new JPanel(inputPanelLayout);
        
        inputMessage.setLineWrap(true);
        inputMessage.setMargin(new Insets(5, 5, 5, 5)); // Add a margin around the text area
        inputMessage.setBackground(new Color(192, 192, 192));
        inputPanel.add(btnCoinGnder);
        inputPanel.add(new JScrollPane(inputMessage), 0.6f);
        inputPanel.add(btnsendmessage, 0.2f);
        
        rightPanel.add(inputPanel, 0.2f);
        btnCoinGnder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputMessage.setText("merhaba");
                transfer_frame = new transferframe(image);
                int frameWidth = transfer_frame.getWidth();
                int frameHeight = transfer_frame.getHeight();
                int x = (Azad.this.getWidth() - frameWidth) / 2 + Azad.this.getX();
                int y = (Azad.this.getHeight() - frameHeight) / 2 + Azad.this.getY();
                transfer_frame.setLocation(x, y);
                transfer_frame.setVisible(true);
            }
        });
        btnsendmessage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // action code
            	inputMessage.setText("gönderildi");
            }
        });
        // enter ctrl+enter fonksiyon ayrımı command pattern ile
        ButtonClickCommand enterKeyCommand = new ButtonClickCommand(inputMessage, btnsendmessage);
        KeyHandler keyHandler = new KeyHandler(enterKeyCommand);
        inputMessage.addKeyListener(new KeyAdapter() {
        	@Override 
        	public void keyPressed(KeyEvent e) { 
        		if (e.getKeyCode() == KeyEvent.VK_G && e.isControlDown()) { 
        			buttonClickCommand.execute(); 
        			}
        		else {
        			keyHandler.handleKey(e); 
        		}
        		}
        });
     // Frame'e KeyListener ekle 
        Azad.this.addKeyListener(new KeyAdapter() { 
        	@Override 
        	public void keyPressed(KeyEvent e) { 
        		if (e.getKeyCode() == KeyEvent.VK_G && e.isControlDown()) { 
        			buttonClickCommand.execute(); 
        			} 
        		}
        });
        /*
        // Frame içindeki bileşenlere KeyListener ekle 
           addKeyListenerRecursively(this, new KeyAdapter() { 
           	@Override 
           	public void keyPressed(KeyEvent e) { 
           		if (e.getKeyCode() == KeyEvent.VK_G && e.isControlDown()) { 
           			buttonClickCommand.execute();
           			}
           		} 
           	});
         */
        
     // Frame içindeki bileşenlere KeyListener ekle addKeyListenerRecursively(this, new KeyAdapter() { @Override public void keyPressed(KeyEvent e) { if (e.getKeyCode() == KeyEvent.VK_G && e.isControlDown()) { buttonClickCommand.execute(); } }
        // enter ctrl+enter fonksiyon ayrımı
        /*inputMessage.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent e) {
        		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
        			if (e.isControlDown()) {
        				inputMessage.setText("ctrl+enter");
        				//btnCoinGnder butonunun aksiyonu çalıştırılır
        				for (ActionListener al:btnCoinGnder.getActionListeners()) {
        					al.actionPerformed(new ActionEvent (this,ActionEvent.ACTION_PERFORMED,null));
        				}
        			}
        			else {
        				inputMessage.append("\n");
        				e.consume(); //varsayılan enterı engeller
        			}
        		}
        	}
        });*/
        return rightPanel;
    
    }
}
