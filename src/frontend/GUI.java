package frontend;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import common.Output;


public class GUI extends JFrame implements ActionListener{

	private Chat c;
	
	private JButton send, connect, options;
	private JTextField input;

	private JTextArea chatverlauf;
	private JScrollPane chatScrollPane;
	
	private JPanel oben, unten;
	
	public GUI(Chat chat){
		c = chat;
		
		this.setTitle("Chat | SSteinkellner, AKoelbl");
		this.setSize(new Dimension(600,400));
		this.setMinimumSize(new Dimension(250,150));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		oben = new JPanel();
		unten = new JPanel();
		
		chatverlauf = new JTextArea();
		chatverlauf.setText("Chatverlauf");
		chatverlauf.setMinimumSize(new Dimension(540, 250));
		chatverlauf.setEditable(false);
		chatScrollPane = new JScrollPane(chatverlauf);
		chatScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		input = new JTextField();
		{	//kapselung des Textes
			final String temp = "Insert your text here";
			input.setText(temp);
			input.addFocusListener(new FocusListener(){
				@Override
				public void focusGained(FocusEvent fe) {
					if(input.getText().equalsIgnoreCase(temp)){
						input.setText("");
					}else{
						input.removeFocusListener(this);
					}
				}
				public void focusLost(FocusEvent fe) {}
			});
			input.addKeyListener(new KeyListener(){
				@Override
				public void keyReleased(KeyEvent ke) {
//					Output.debug(ke.getKeyCode());
					if(ke.getKeyCode()==10){
						send();
					}
				}
				public void keyPressed(KeyEvent ke) {}
				public void keyTyped(KeyEvent ke) {}
			});
		}
		
		send = new JButton("Send");
		send.setBounds(30, 320, 150, 36);
		send.addActionListener(this);
		
		options = new JButton("Options");
		options.setBounds(420, 320, 150, 36);
		options.addActionListener(this);
		
		connect = new JButton("Connect");
		connect.setBounds(230, 320, 150, 36);
		connect.addActionListener(this);
		
		Container cp = this.getContentPane();
		
		cp.setLayout(new BorderLayout());
		oben.setLayout(new GridLayout(1,2));
		unten.setLayout(new BorderLayout());
		
		cp.add(oben, BorderLayout.NORTH);
		cp.add(chatScrollPane);
		cp.add(unten, BorderLayout.SOUTH);
		
		oben.add(options);
		oben.add(connect);
		
		unten.add(input);
		unten.add(send, BorderLayout.EAST);

		this.setVisible(true);
		
		while(true){
			c.recieve();
			this.update();
		}
	}

	public synchronized void update(){
		String text = c.getLastMessage();
		Output.debug(text);
		chatverlauf.append("\n"+text);
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		Object src = ae.getSource();
		
		if(src.equals(send)){
			send();
		}else if(src.equals(connect)){
			JTextField ip = new JTextField(), port = new JTextField();
			Object[] options = new Object[]{"IP",ip,"Port",port};
			JOptionPane.showMessageDialog(null, options, "Chat | open new Connection", JOptionPane.PLAIN_MESSAGE);
			if(ip.getText().isEmpty() || port.getText().isEmpty()){ return; }
			try{
				int convertedPort = Integer.parseInt(port.getText());
				c.getActiveConection().open(ip.getText(), convertedPort);
			}catch(Exception e){
				Output.error("Illegal Port: '" + port.getText() + "' !");
			}
		}else if(src.equals(options)){
			
		}
	}
	
	private void send(){
		c.send(input.getText());
		input.setText("");
		update();
	}
}
