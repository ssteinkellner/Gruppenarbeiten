import javax.swing.JFrame;

import java.awt.Label;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class GUI extends JFrame {
	public JButton send;
	
	public JButton connect;
	
	public JButton options;
	
	public JTextField text;
	
	public Label chatverlauf;
	
	JFrame f;
	
	public JComboBox<String> comboBox;
	public GUI(){

		this.f = new JFrame("Chat");
		this.f.setSize(600,400);
		f.getContentPane().setLayout(null);
		f.setLocationRelativeTo(null);
		
		text = new JTextField();
		text.setText("Insert your text here");
		text.setBounds(30, 280, 540, 20);
		f.getContentPane().add(text);
		
		chatverlauf = new Label();
		chatverlauf.setText("Chatverlauf");
		chatverlauf.setBounds(30,10, 540, 250);
		JScrollPane jScrollPane = new JScrollPane(chatverlauf);
		jScrollPane.setBounds(30, 50, 222, 100);
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		f.getContentPane().add(chatverlauf);

		
		send = new JButton("Send");
		send.setBounds(30, 320, 150, 36);
		f.getContentPane().add(send);
		
		options = new JButton("Options");
		options.setBounds(420, 320, 150, 36);
		f.getContentPane().add(options);
		
		JButton connect = new JButton("Connect");
		connect.setBounds(230, 320, 150, 36);
		f.getContentPane().add(connect);
		

		

		
		
		this.f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.setVisible(true);
	}
}
