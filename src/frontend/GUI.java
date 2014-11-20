package frontend;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


public class GUI extends JFrame implements ActionListener{

	private Chat c;
	
	private JButton send, connect, options;
	private JTextField text;

	private JLabel chatverlauf;
	private JScrollPane chatScrollPane;
	
	private JComboBox<String> comboBox;
	
	private JPanel oben, unten;
	
	public GUI(Chat chat){
		c = chat;
		
		this.setTitle("Chat | SSteinkellner, AKoelbl");
		this.setSize(600,400);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		oben = new JPanel();
		unten = new JPanel();
		
		chatverlauf = new JLabel();
		chatverlauf.setText("Chatverlauf");
		chatverlauf.setMinimumSize(new Dimension(540, 250));
		chatScrollPane = new JScrollPane(chatverlauf);
		chatScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		text = new JTextField();
		text.setText("Insert your text here");
		
		send = new JButton("Send");
		send.setBounds(30, 320, 150, 36);
		
		options = new JButton("Options");
		options.setBounds(420, 320, 150, 36);
		
		connect = new JButton("Connect");
		connect.setBounds(230, 320, 150, 36);

		Container cp = this.getContentPane();
		
		cp.setLayout(new BorderLayout());
		oben.setLayout(new GridLayout(1,2));
		unten.setLayout(new BorderLayout());
		
		cp.add(oben, BorderLayout.NORTH);
		cp.add(chatScrollPane);
		cp.add(unten, BorderLayout.SOUTH);
		
		oben.add(options);
		oben.add(connect);
		
		unten.add(text);
		unten.add(send, BorderLayout.EAST);

		this.setVisible(true);
		
		while(true){
			c.receive();
			this.update();
		}
	}

	public void update(){
		chatverlauf.setText(c.messagesToString());
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		Object src = ae.getSource();
		
		if(src.equals(send)){
			
		}else if(src.equals(connect)){
			
		}else if(src.equals(options)){
			
		}
	}
}
