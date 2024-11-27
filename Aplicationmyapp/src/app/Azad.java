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
    private static Transfer transfer_frame;
    private static BlockchainGUI Miningapp;
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

	    JMenuBar menuBar = new JMenuBar();
	    JMenu menu = new JMenu("Menu");
	    JMenu submenu = new JMenu("Submenu");
	    JMenuItem miningitem = new JMenuItem("start mining");
	    JMenuItem menuItem4 = new JMenuItem("Menu Item 2");
	    JMenuItem submenuItem1 = new JMenuItem("Submenu Item 1");
	    JMenuItem submenuItem2 = new JMenuItem("Submenu Item 2");
	    menu.add(miningitem);
	    menu.add(menuItem4);
	    submenu.add(submenuItem1);
	    submenu.add(submenuItem2);
	    menu.add(submenu);
	    menuBar.add(menu); 
	    
	    JPanel menuPanel = new JPanel();
	    menuPanel.setLayout(new BorderLayout());
	    menuPanel.add(menuBar, BorderLayout.PAGE_START);
	    leftPanel.add(menuPanel);
	    
	    JScrollPane scrollpanel = new JScrollPane(leftPanel);
	    scrollpanel.setMinimumSize(new Dimension(200, 0)); 
	    scrollpanel.getVerticalScrollBar().setUnitIncrement(20);

	    list.setBackground(new Color(128, 128, 128));
	    list.setMaximumSize(new Dimension(Integer.MAX_VALUE, list.getPreferredSize().height));
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
	            transfer_frame = new Transfer(image);
	            transfer_frame.setLocation(Azad.this.getLocation().x, Azad.this.getLocation().y);
	            transfer_frame.setVisible(true);
	            inputMessage.setText(list.getSelectedValue() + list.getSelectedIndex());
	        }
	    });

	    miningitem.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            // Add your mining action here
	        	Miningapp = new BlockchainGUI();
                int frameWidth = Miningapp.getWidth();
                int frameHeight = Miningapp.getHeight();
                int x = (Azad.this.getWidth() - frameWidth) / 2 + Azad.this.getX();
                int y = (Azad.this.getHeight() - frameHeight) / 2 + Azad.this.getY();
                Miningapp.setLocation(x, y);
                Miningapp.setVisible(true);
	        }
	    });

	    menuItem4.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            // Add your action for Menu Item 2 here
	            System.out.println("Menu Item 2 selected");
	        }
	    });

	    submenuItem1.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            // Add your action for Submenu Item 1 here
	            System.out.println("Submenu Item 1 selected");
	        }
	    });

	    submenuItem2.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            // Add your action for Submenu Item 2 here
	            System.out.println("Submenu Item 2 selected");
	        }
	    });

	    list.addComponentListener(new ComponentAdapter() {
	        @Override
	        public void componentResized(ComponentEvent e) {
	            int newWidth = list.getWidth() / 2;
	            Dimension menuSize = new Dimension(newWidth, menu.getPreferredSize().height);
	            menuBar.setPreferredSize(menuSize);
	            menuBar.revalidate();
	        }
	    });

	    leftPanel.addComponentListener(new ComponentAdapter() {
	        @Override
	        public void componentResized(ComponentEvent e) {
	            int newWidth = leftPanel.getWidth();
	            scrollpanel.setPreferredSize(new Dimension(newWidth, scrollpanel.getHeight()));
	            for (Component comp : menuBar.getComponents()) {
	                if (comp instanceof JMenu) {
	                    ((JMenu) comp).setPreferredSize(new Dimension(newWidth / 2, comp.getPreferredSize().height));
	                }
	                for (Component menuComp : ((JMenu) comp).getMenuComponents()) {
	                    if (menuComp instanceof JMenuItem) {
	                        ((JMenuItem) menuComp).setPreferredSize(new Dimension(newWidth / 2, menuComp.getPreferredSize().height));
	                    }
	                }
	            }
	            leftPanel.revalidate();
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
                transfer_frame = new Transfer(image);
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
 
        return rightPanel;
    
    }
}
