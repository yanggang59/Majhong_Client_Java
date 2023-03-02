package com.thomas.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.thomas.view.MainFrame;

public class Login extends JFrame{
	private JLabel unameJlable;
	private JLabel passwordJLabel;
	private JTextField unameJTextFiled;
	private JPasswordField passwordJTextField;
	private JButton logInJButton;
	private JButton cancelJButton;
	
	private JPanel jp1,jp2,jp3;
	
	public Login()
	{
		this.unameJlable = new JLabel("�û���:");
		this.passwordJLabel = new JLabel("��    ��:");
		this.unameJTextFiled = new JTextField(10);
		this.passwordJTextField = new JPasswordField(10);
		this.logInJButton = new JButton("��¼");
		this.cancelJButton = new JButton("ȡ��");
		
		jp1 = new JPanel(); 
		jp2 = new JPanel(); 
		jp3 = new JPanel(); 
		
		this.setLayout(new GridLayout(3,1,5,5));
		
		jp1.add(unameJlable);
		jp1.add(unameJTextFiled);
		
		jp2.add(passwordJLabel);
		jp2.add(passwordJTextField);
		
		jp3.add(logInJButton);
		jp3.add(cancelJButton);
		
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		
		this.setSize(280,160);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//this.setLayout(new GridLayout(2,2));
		
		
		//�������������󣬰󶨵���¼��ť
		MyEventListener myEventListener = new MyEventListener();
		this.logInJButton.addActionListener(myEventListener);
	}
	
	//�����¼���������
	class MyEventListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			//1.����û���
			String uname = unameJTextFiled.getText();
		
			//2.����һ��socket
			try {
				Socket socket = new Socket("127.0.0.1",8838);
				//3.����������
				new MainFrame(uname,socket);
				
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}