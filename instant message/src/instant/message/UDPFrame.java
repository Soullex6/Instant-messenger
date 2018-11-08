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
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

public class UDPFrame extends Frame implements ActionListener, Runnable {

    Label label1 = new Label("IP");
    Label label2 = new Label("   Port N");
    Label label3 = new Label("My Name Is");
    Label label4 = new Label("I wanna to send message to", 1);
    Label label5 = new Label();
    Button b = new Button("SEND");
    private JRadioButton red = new JRadioButton(" red");
    private JRadioButton yellow = new JRadioButton(" yellow");
    private JRadioButton blue = new JRadioButton(" blue");
    private JRadioButton green = new JRadioButton(" green");
    private JRadioButton magenta = new JRadioButton(" magenta");
    List list = new List();
    TextField tPort = new TextField("1111", 10);
    TextField tMes = new TextField(60);
    TextField tName = new TextField(10);
    TextField tIP = new TextField("localhost", 16);
    public DatagramSocket dS;
    Panel p = new Panel();
    Panel p0 = new Panel();
    Panel p1 = new Panel();
    Panel p2 = new Panel();
    Panel p3 = new Panel();//added to modify the background to a greater extent -Shaun

    public UDPFrame() {
        setTitle("Chat Window");
        setLayout(new BorderLayout());
        setBackground(new Color(180, 240, 200));
        ButtonGroup group = new ButtonGroup();//*
        group.add(red);
        group.add(yellow);
        group.add(blue);
        group.add(green);
        group.add(magenta);
        red.addActionListener(this);
        yellow.addActionListener(this);
        blue.addActionListener(this);
        green.addActionListener(this);
        magenta.addActionListener(this);//^
        setSize(605, 536);
        setLocation(50, 50);
        setFont(new Font("Dialog", Font.BOLD, 14));

        p.setLayout(new GridLayout(4, 1));
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
        p3.add(red);
        p3.add(yellow);
        p3.add(blue);
        p3.add(green);
        p3.add(magenta);
        p.add(p3);
        add(p, BorderLayout.NORTH);
        add(list, BorderLayout.CENTER);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                System.exit(0);
            }
        });
        b.addActionListener(this);
        tMes.addActionListener(this);
        try {
            label5.setText("My IP is:" + InetAddress.getLocalHost());
            dS = new DatagramSocket(1111);
        } catch (Exception e) {
            list.add(e.toString());
        }
        setVisible(true);
        new Thread(this).start();
    }

    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == red) {
            p.setBackground(Color.red);
            p0.setBackground(Color.red);
            p1.setBackground(Color.red);
            p2.setBackground(Color.red);
            p3.setBackground(Color.red);
            label1.setBackground(Color.red);
            label2.setBackground(Color.red);
            label3.setBackground(Color.red);
            label4.setBackground(Color.red);
            label5.setBackground(Color.red);
        }
        if (event.getSource() == yellow) {
            p.setBackground(Color.yellow);
            p0.setBackground(Color.yellow);
            p1.setBackground(Color.yellow);
            p2.setBackground(Color.yellow);
            p3.setBackground(Color.yellow);
            label1.setBackground(Color.yellow);
            label2.setBackground(Color.yellow);
            label3.setBackground(Color.yellow);
            label4.setBackground(Color.yellow);
            label5.setBackground(Color.yellow);
        }
        if (event.getSource() == blue) {
            p.setBackground(Color.blue);
            p0.setBackground(Color.blue);
            p1.setBackground(Color.blue);
            p2.setBackground(Color.blue);
            p3.setBackground(Color.blue);
            label1.setBackground(Color.blue);
            label2.setBackground(Color.blue);
            label3.setBackground(Color.blue);
            label4.setBackground(Color.blue);
            label5.setBackground(Color.blue);
        }
        if (event.getSource() == green) {
            p.setBackground(Color.green);
            p0.setBackground(Color.green);
            p1.setBackground(Color.green);
            p2.setBackground(Color.green);
            p3.setBackground(Color.green);
            label1.setBackground(Color.green);
            label2.setBackground(Color.green);
            label3.setBackground(Color.green);
            label4.setBackground(Color.green);
            label5.setBackground(Color.green);
        }
        if (event.getSource() == magenta) {
            p.setBackground(Color.magenta);
            p0.setBackground(Color.magenta);
            p1.setBackground(Color.magenta);
            p2.setBackground(Color.magenta);
            p3.setBackground(Color.magenta);
            label1.setBackground(Color.magenta);
            label2.setBackground(Color.magenta);
            label3.setBackground(Color.magenta);
            label4.setBackground(Color.magenta);
            label5.setBackground(Color.magenta);
        }

        try {
            String mes = tName.getText() + ">> " + tMes.getText();
            InetAddress ia = InetAddress.getByName(tIP.getText());
            int port = Integer.parseInt(tPort.getText());
            byte data[] = mes.getBytes();
            list.add(tMes.getText());
            dS.send(new DatagramPacket(data, data.length, ia, port));
            tMes.setText("");
        } catch (Exception e) {
            list.add(e.toString());
        }

    }

    public void run() {
        while (true) {
            try {
                DatagramPacket p = new DatagramPacket(new byte[1024], 1024);
                dS.receive(p);
                list.add(new String(p.getData(), 0, p.getLength()));
            } catch (Exception e) {
            }
        }
    }

    static public void main(String args[]) {
        new UDPFrame();
    }
}
