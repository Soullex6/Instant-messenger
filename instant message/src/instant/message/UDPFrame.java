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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.*;
import java.net.*;


public class ProjectGUI extends JFrame {

   public static final String title = "Instant Messenger";
   private static ProjectGUI instance;

   private JTextPane textChat; //chat log
   private JTextField fieldInput; //user input
   private JButton sendButton;
   
    //Constructor
    public ProjectGUI() {
        createView();
        
        setTitle(title);
        setSize(600, 500);
        setResizable(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
    }
    
    private void createView(){
        JPanel panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(new BorderLayout());
        
        //Chat Log Panel
        JPanel panelChat = new JPanel(new BorderLayout());
        panel.add(panelChat, BorderLayout.CENTER);
        textChat = new JTextPane();
        textChat.setEditable(false);
        JScrollPane textChatSP = new JScrollPane(textChat);
        panelChat.add(textChatSP, BorderLayout.CENTER);
        
        //User Input and Send Button Panel
        JPanel panelInput = new JPanel(new BorderLayout());
        panel.add(panelInput, BorderLayout.SOUTH);
        fieldInput = new JTextField();
        panelInput.add(fieldInput, BorderLayout.CENTER);
        sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            //TODO send chat message
        }
    });
        panelInput.add(sendButton, BorderLayout.EAST);
    }
}

public class UDPFrame extends Frame implements ActionListener, Runnable
{
	public DatagramSocket dS;

	public UDPFrame()
	{
		
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
      
       SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                instance = new ProjectGUI();
                instance.setVisible(true);
            }
        });
	}
}
