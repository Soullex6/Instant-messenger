package instant.message;
/**
 * UDPFrame.java
 *
 * Simple AWT GUI java local network messenger
 *
 * requires JDK 1.4 or later
 *
 * @author Vjacheslav K.
 *
 */
import java.awt.*;
import java.awt.event.*;
import java.net.*;

public class UDPFrame extends Frame implements ActionListener, Runnable
{
	Label label1 = new Label("IP");
	Label label2 = new Label("   Port N");
	Label label3 = new Label("My Name Is");
	Label label4 = new Label("I wanna to send message to", 1);
	Label label5 = new Label();
	Button b = new Button ("SEND");
	List list = new List();
	TextField tPort = new TextField("1111", 10);
	TextField tMes = new TextField(60);
	TextField tName = new TextField(10);
	TextField tIP = new TextField("localhost", 16);
	public DatagramSocket dS;

	public UDPFrame()
	{
		setTitle("Chat Window");
		setLayout(new BorderLayout());
		setBackground(new Color(180,240,200));
		setSize(605,536);
		setLocation(50,50);
		setFont(new Font("Dialog", Font.BOLD, 14));
		Panel p = new Panel();
		Panel p0 = new Panel();
		Panel p1 = new Panel();
		Panel p2 = new Panel();
		p.setLayout(new GridLayout(4,1));
		p0.add(label3);
		p0.add(tName);
		p0.add(label5);
		p.add(p0);
		p.add(label4);
		p1.add(label1);
		p1.add(tIP);
		p1.add(label2);
		p1.add(tPort);
		p.add(p1);
		p2.add(tMes);
		p2.add(b);
		p.add(p2);
		add(p,BorderLayout.NORTH);
		add(list,BorderLayout.CENTER);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent event){
				System.exit(0);}});
		b.addActionListener(this);
		tMes.addActionListener(this);
		try {label5.setText("My IP is:"+InetAddress.getLocalHost());
			dS = new DatagramSocket(1111);
		} catch(Exception e) {list.add(e.toString());}
		setVisible(true);
		new Thread(this).start();
	}
	
	public void actionPerformed(ActionEvent event)
	{
		try {String mes=tName.getText()+">> "+tMes.getText();
			InetAddress ia=InetAddress.getByName(tIP.getText());
			int port=Integer.parseInt(tPort.getText());
			byte data[] = mes.getBytes();
			list.add(tMes.getText());
			dS.send(new DatagramPacket(data,data.length,ia,port));
			tMes.setText("");
		} catch(Exception e) {list.add(e.toString());}
	}

	public void run()
	{
		while(true) {
			try {DatagramPacket p = new DatagramPacket (new byte[1024], 1024);
				dS.receive(p);
				list.add(new String(p.getData(), 0, p.getLength()));
			} catch(Exception e) {}
		}
	}

	static public void main (String args[])
	{
		new UDPFrame();
	}
}
