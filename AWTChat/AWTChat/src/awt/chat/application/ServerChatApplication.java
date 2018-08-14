package awt.chat.application;

import java.awt.Button;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerChatApplication extends Frame implements WindowListener,ActionListener{
	TextArea txtArea;
	TextField txtFiled;
	Button btn;
	ServerSocket ss;
	Socket s;
	InputStream ips;
	OutputStream ots;
	String clientMsg,strAppend; 
	DataInputStream dis;
	DataOutputStream dout;
	public ServerChatApplication() throws IOException
	{
		setLayout(null);
		
		txtArea=new TextArea();
		
		txtFiled=new TextField();
		
		btn=new Button("Send");
		
		txtArea.setBounds(80, 50, 300, 200);
		
		txtFiled.setBounds(80, 280, 240, 30);;
		
		btn.setBounds(330, 280, 50, 30);
		
		this.add(txtArea);
		this.add(txtFiled);
		this.add(btn);
		
		btn.addActionListener(this);
		try{
		this.addWindowListener(this);
		
		ss=new ServerSocket(1234);
		
		s=ss.accept();
		ips=s.getInputStream();
		ots=s.getOutputStream();
		dis=new DataInputStream(ips);
		
		dout=new DataOutputStream(ots);
		dout.writeUTF("Server");
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
	}
	
	public static void main(String[] args)  {
		
		try {
			ServerChatApplication serverChatApp = new ServerChatApplication();
			serverChatApp.setTitle("Server");
			serverChatApp.setVisible(true);
			serverChatApp.setSize(500, 500);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==btn)
		{
			/*while(true)
			{*/
			strAppend=txtArea.getText();
				System.out.println("clicked re");
				try {
					 clientMsg=dis.readUTF();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				txtArea.setText(clientMsg);
				
				
				
				
				
				try {
					String strText=txtFiled.getText();
					dout.writeUTF(strText);
					strAppend=strAppend+"\n"+strText+"\n"+clientMsg;
					//strAppend="\n"+strAppend+" "+strText;
					
					txtArea.setText(strAppend);
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				
			//}
		}
	}
	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosed(WindowEvent arg0) {
		
	}
	@Override
	public void windowClosing(WindowEvent arg0) {
		dispose();
	}
	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
