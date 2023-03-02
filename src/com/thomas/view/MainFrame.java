package com.thomas.view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.thomas.model.MajhongLabel;
import com.thomas.model.Player;
import com.thomas.view.MyPanel;
import com.thomas.thread.SendThread;
import com.thomas.thread.MusicThread;
import com.thomas.thread.ReceiveThread;
import com.thomas.model.Majhong;
import com.thomas.model.MajhongLabel;
import com.thomas.util.GameUtil;


public class MainFrame extends JFrame{
	public MyPanel myPanel;
	public String uname;
	public Socket socket;
	
	public SendThread sendThread;
	public ReceiveThread receiveThread;
	public MusicThread musicThread;   //������Ч�߳�
	
	public Player currentPlayer;   //��ŵ�ǰ��Ҷ���
	
	//�Լҵ��ֳ��齫�б�
	public List<MajhongLabel> myHoldMajhongLabels = new ArrayList<MajhongLabel>();
	
	//�ԼҵĴ�����齫�б�
	public List<MajhongLabel> myDiscardedMajhongLabels = new ArrayList<MajhongLabel>();
	
	//�Լҵ����ܺͻ����齫�б�
	public List<MajhongLabel> myOperatedMajhongLabels = new ArrayList<MajhongLabel>();
	
	//��һ���ֳֵ��齫�б�
	public List<MajhongLabel> leftHoldMajhongLabels = new ArrayList<MajhongLabel>();
	//��һ�ҵĴ�����齫�б�
	public List<MajhongLabel> leftDiscardedMajhongLabels = new ArrayList<MajhongLabel>();
	//��һ�ҵ����ܺͻ����齫�б�
	public List<MajhongLabel> leftOperatedMajhongLabels = new ArrayList<MajhongLabel>();
	
	//��һ���ֳֵ��齫�б�
	public List<MajhongLabel> rightHoldMajhongLabels = new ArrayList<MajhongLabel>();
	//��һ�ҵĴ�����齫�б�
	public List<MajhongLabel> rightDiscardedMajhongLabels = new ArrayList<MajhongLabel>();
	//��һ�ҵ����ܺͻ����齫�б�
	public List<MajhongLabel> rightOperatedMajhongLabels = new ArrayList<MajhongLabel>();

	//�Լҵ��ֳ��齫�б�
	public List<MajhongLabel> oppositeHoldMajhongLabels = new ArrayList<MajhongLabel>();
	//�ԼҵĴ�����齫�б�
	public List<MajhongLabel> opppositeDiscardedMajhongLabels = new ArrayList<MajhongLabel>();
	//�Լҵ����ܺͻ����齫�б�
	public List<MajhongLabel> oppositeOperatedMajhongLabels = new ArrayList<MajhongLabel>();

	public List<MajhongLabel> selectedMajhongLabels = new ArrayList<MajhongLabel>();
	//ׯ��ͼ��
	public JLabel dealerIconLabel;
	
	//�Ƿ������Ʊ�ѡ��
	public boolean isHoldMajhongSelected;
	public MajhongLabel selectedHoldMajhongLabel = null;
	
	//�����Ƿ����ֵ��ҳ���
	public boolean isMyturn;
	
	public MainFrame(String uname,Socket socket)
	{
		this.uname=uname;
		this.socket = socket;
		
		//���ô��ڵ�����
		this.setSize(1280,720);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//���mypanel
		myPanel = new MyPanel();
		myPanel.setBounds(0,0,1280,720);
		this.add(myPanel);
		
		//����������Ϣ���߳�
		sendThread = new SendThread(socket,uname);
		sendThread.start();
		
		//����������Ϣ���߳�
		receiveThread = new ReceiveThread(socket, this);
		receiveThread.start();
		
//		String picPath1 = "images/majhong/front_inhand_image_29.png";
//		String valuePath = "images/majhong/w_1.png";
//		//List<MajhongLabel> majhongLabels1 = new ArrayList<MajhongLabel>();
//		for(int i = 0;i<13;i++)
//		{
//			MajhongLabel majhongLabel = new MajhongLabel(i,"wan",picPath1,88,128, false, true);
//			//majhongLabels1.add(majhongLabel);
//			this.myPanel.add(majhongLabel);  //��ӵ����
//			//ÿ�ε���ȥ������������
//			this.myPanel.setComponentZOrder(majhongLabel, 0);
//			
//			MajhongLabel valueLabel = new MajhongLabel(i,"wan",valuePath,60,90, false, true);
//			//majhongLabels1.add(majhongLabel);
//			valueLabel.setVisible(false);
//			this.myPanel.add(valueLabel);  //��ӵ����
//			//ÿ�ε���ȥ������������
//			this.myPanel.setComponentZOrder(valueLabel, 0);
//			
//			//һ��һ�ŵ���ʾ����
//			GameUtil.move(majhongLabel, 40+82*i, 550);
//			GameUtil.move(valueLabel, 55+82*i, 550);
//			valueLabel.setVisible(true);
//		}
//		
//		String picPath2 = "images/majhong/opposite_inhand_29.png";
//		List<MajhongLabel> majhongLabels2 = new ArrayList<MajhongLabel>();
//		for(int i = 0;i<13;i++)
//		{
//			MajhongLabel majhongLabel = new MajhongLabel(i, "wan",picPath2,44,72, false, true);
//			majhongLabels2.add(majhongLabel);
//			this.myPanel.add(majhongLabel);  //��ӵ����
//			//ÿ�ε���ȥ������������
//			this.myPanel.setComponentZOrder(majhongLabel, 0);
//			//һ��һ�ŵ���ʾ����
//			GameUtil.move(majhongLabel, 300+43*i, 10);
//		}
//		
//		String picPath3 = "images/majhong/left_inhand_29.png";
//		List<MajhongLabel> majhongLabels3 = new ArrayList<MajhongLabel>();
//		for(int i = 0;i<13;i++)
//		{
//			MajhongLabel majhongLabel = new MajhongLabel(i, "wan",picPath3, 26,56,false, true);
//			majhongLabels3.add(majhongLabel);
//			this.myPanel.add(majhongLabel);  //��ӵ����
//			//ÿ�ε���ȥ������������
//			this.myPanel.setComponentZOrder(majhongLabel, 0);
//			//һ��һ�ŵ���ʾ����
//			GameUtil.move(majhongLabel, 180, 140+20*i);
//		}
//		
//		String picPath4 = "images/majhong/right_inhand_29.png";
//		List<MajhongLabel> majhongLabels4 = new ArrayList<MajhongLabel>();
//		for(int i = 0;i<13;i++)
//		{
//			MajhongLabel majhongLabel = new MajhongLabel(i, "wan", picPath4, 26,56,false, true);
//			majhongLabels3.add(majhongLabel);
//			this.myPanel.add(majhongLabel);  //��ӵ����
//			//ÿ�ε���ȥ������������
//			this.myPanel.setComponentZOrder(majhongLabel, 0);
//			//һ��һ�ŵ���ʾ����
//			GameUtil.move(majhongLabel, 1000, 140+20*i);
//		}
		
	}

	public void showAllPlayersInfo(List<Player> players) {
		//1.��ʾ4����ҵ�����,uname
		
		//2.��ʾׯ����Ϣ
		for(int i = 0;i<players.size();i++)
		{
			if(players.get(i).getName().equals(uname))
			{
				currentPlayer = players.get(i);
			}
		}
		showDealerInfo(players);
		
		//3.��ʾ��ǰ������ҵ��齫�б�
	
		List<Majhong> majhongs =  currentPlayer.getMajhongs();
		for(int i = 0;i<majhongs.size();i++)
		{
			Majhong majhong = majhongs.get(i);
			String majhongName  = majhong.getName();
			MajhongLabel majhongLabel = new MajhongLabel(majhongs.get(i).getId(),majhongName,0,"images/majhong/"+majhong.getId()+".png",60,90);
			myHoldMajhongLabels.add(majhongLabel);
		}
		System.out.println("Before Sorting:");
		for(int i=0;i<myHoldMajhongLabels.size();i++)
		{
			System.out.println(myHoldMajhongLabels.get(i).getId());
		}
		//���齫�ƽ�������
		Collections.sort(myHoldMajhongLabels);
		System.out.println("After Sorting:");
		for(int i=0;i<myHoldMajhongLabels.size();i++)
		{
			System.out.println(myHoldMajhongLabels.get(i).getId());
		}
		showHoldMajhongs(myHoldMajhongLabels,currentPlayer.getId());
		this.repaint();		
		
	}
	
	private void showHoldMajhongs(List<MajhongLabel> myHoldMajhongLabels,int playerID)
	{
		if(currentPlayer.getId() == playerID)  //��ʾ�Լ��ҵ��ֳ���
		{
			String shellPath = "images/majhong/front_inhand_image_29.png";
			for(int i = 0;i<myHoldMajhongLabels.size();i++)
			{
				//һ��һ�ŵ���ʾ����
				GameUtil.move(myHoldMajhongLabels.get(i).getMajhongShellLabel(), 40+82*i, 550);
				GameUtil.move(myHoldMajhongLabels.get(i), 55+82*i, 580);
				
				this.myPanel.add(myHoldMajhongLabels.get(i));  //��ӵ����
				this.myPanel.add(myHoldMajhongLabels.get(i).getMajhongShellLabel());  //��ӵ����
				
				//ÿ�ε���ȥ������������
				this.myPanel.setComponentZOrder(myHoldMajhongLabels.get(i).getMajhongShellLabel(), 0);
				this.myPanel.setComponentZOrder(myHoldMajhongLabels.get(i), 0);
			}
		}
		this.repaint();
	}
	
	public void playSoundEffect(String majhongName)
	{
		musicThread = new MusicThread("audio/"+majhongName+".wav");
		musicThread.start();
	}
	
	public void discardMajhong(MajhongLabel majhongLabel) {
		myHoldMajhongLabels.remove(majhongLabel);
		for(int i = 0;i<myHoldMajhongLabels.size();i++)
		{
			this.myPanel.remove(myHoldMajhongLabels.get(i));
			this.myPanel.remove(myHoldMajhongLabels.get(i).getMajhongShellLabel());
		}
		Collections.sort(myHoldMajhongLabels);
		myDiscardedMajhongLabels.add(majhongLabel);
		playSoundEffect(majhongLabel.getName());
	}
	
	private void showDiscardMajhongLabels(List<MajhongLabel> myDiscardedMajhongLabels, int playerID) {
		// TODO Auto-generated method stub
		if(currentPlayer.getId() == playerID)
		{
			String shellPath = "images/majhong/vertical_discard_image_29.png";
			for(int i = 0;i<myDiscardedMajhongLabels.size();i++)
			{
				MajhongLabel majhongLabel = myDiscardedMajhongLabels.get(i);
				majhongLabel.setValueLabelSize(35,42);
				//������Ǳ�ǩ
				majhongLabel.getMajhongShellLabel().setUpIcon(shellPath,38,58);
				//�����˿˱�ǩ
				this.myPanel.add(myDiscardedMajhongLabels.get(i));  //��ӵ����
				this.myPanel.add(myDiscardedMajhongLabels.get(i).getMajhongShellLabel());  //��ӵ����
				//ÿ�ε���ȥ������������
				this.myPanel.setComponentZOrder(myDiscardedMajhongLabels.get(i).getMajhongShellLabel(), 0);
				this.myPanel.setComponentZOrder(myDiscardedMajhongLabels.get(i), 0);
				//һ��һ�ŵ���ʾ����
				GameUtil.move(myDiscardedMajhongLabels.get(i).getMajhongShellLabel(), 254+38*i, 395);
				GameUtil.move(myDiscardedMajhongLabels.get(i), 255+38*i, 370);
			}
		}
		this.repaint();
	}
	
	public void refreshMyPanel()
	{
		this.repaint();
	}
	
	private void showDealerInfo(List<Player> players) 
	{
		String dealerIconPath = "images/majhong/dealer.png";
		dealerIconLabel = new JLabel();
		dealerIconLabel.setIcon(new ImageIcon(dealerIconPath));
		dealerIconLabel.setSize(44,46);
		//�������ID��ʾ�ڶ�Ӧ��λ��
		int myID = currentPlayer.getId();
		int dealerID = 0;
		for(int i=0;i<players.size();i++)
		{
			if(players.get(i).isDealer())
			{
				dealerID = i;
				break;
			}
		}
		//�Լ���ׯ��
		if(dealerID == myID)  
		{
			dealerIconLabel.setLocation(130,500);
		}
		//��һ����ׯ��     0 1 , 1 2 , 2 3 , 3 0
		else if(myID+1 == dealerID || myID-3 == currentPlayer.getId())
		{
			dealerIconLabel.setLocation(1200,360);
		}
		//�Լ���ׯ�� 0 2, 1 3, 2 0, 3 1
		else if(myID+2==dealerID || dealerID+2==myID){
			dealerIconLabel.setLocation(1000,50);
		}//�ϼ���ׯ��
		else {
			dealerIconLabel.setLocation(50,360);
		}
		this.myPanel.add(dealerIconLabel);
		this.repaint(); //�ػ�
		
	}
	
	public void addClickEventToMajhongLabel(MajhongLabel majhongLabel)
	{
		majhongLabel.addMouseListener(new MajhongLabelEvent());
	}

	public void addClickEventToMajhongLabels()
	{
		for(int i=0;i<myHoldMajhongLabels.size();i++)
		{
			myHoldMajhongLabels.get(i).addMouseListener(new MajhongLabelEvent());
		}
	}
	
	class MajhongLabelEvent implements MouseListener
	{
		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			//���֮ǰѡ�����������,�����ֵ��ҳ��ƣ�����
			MajhongLabel majhongLabel = (MajhongLabel)arg0.getSource();
			if(majhongLabel.isSelected())
			{
				selectedHoldMajhongLabel=null;
				discardMajhong(majhongLabel);
				showHoldMajhongs(myHoldMajhongLabels,currentPlayer.getId());
				showDiscardMajhongLabels(myDiscardedMajhongLabels,currentPlayer.getId());
			}
			//���֮ǰû��ѡ��,���ȿ���û������ѡ�е���
			else 
			{
				//���֮ǰû���������Ʊ�ѡ��
				if(selectedHoldMajhongLabel == null)
				{
					//��ѡ�е��������ƶ�һ�ξ���
					majhongLabel.setLocation(majhongLabel.getX(),majhongLabel.getY()-30);
					majhongLabel.getMajhongShellLabel().setLocation(majhongLabel.getMajhongShellLabel().getX(),majhongLabel.getMajhongShellLabel().getY()-30);
					
					selectedHoldMajhongLabel = majhongLabel;
					majhongLabel.setSelected(true);	
				}
				else //֮ǰ��������ѡ��
				{
					//֮ǰ��ѡ�е��Ʒ���ԭλ��
					selectedHoldMajhongLabel.setLocation(selectedHoldMajhongLabel.getX(),selectedHoldMajhongLabel.getY()+30);
					selectedHoldMajhongLabel.getMajhongShellLabel().setLocation(selectedHoldMajhongLabel.getMajhongShellLabel().getX(),selectedHoldMajhongLabel.getMajhongShellLabel().getY()+30);
//					if(isChiPengMode)
//					{
//						selectedMajhongLabels.add(majhongLabel);
//					}
					//ȡ��֮ǰ��ѡ�е���
					selectedHoldMajhongLabel.setSelected(false);
					
					//��ѡ�е��������ƶ�һ�ξ���
					majhongLabel.setLocation(majhongLabel.getX(),majhongLabel.getY()-30);
					majhongLabel.getMajhongShellLabel().setLocation(majhongLabel.getMajhongShellLabel().getX(),majhongLabel.getMajhongShellLabel().getY()-30);
					selectedHoldMajhongLabel = majhongLabel;
					selectedHoldMajhongLabel.setSelected(true);
					refreshMyPanel();
				}
			} 
		}

		

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
