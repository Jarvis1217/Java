package Game;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
 
import javax.swing.JButton;
import javax.swing.JDialog;
 
public class ClickRone {
	My_button buttons[];
	int x,y;
	int rows,cols;
	Mine sjm;
	Boolean ismine[];
	Num_Mine num_mine;
	ClickRone(My_button button[],int a,int b,int r,int l,Num_Mine num,Mine jm,Boolean ism[])
	{
		ismine=ism;
		buttons=button;
		num_mine=num;
		sjm=jm;
		x=a;
		y=b;
		rows=r;
		cols=l;
		if(buttons[x*cols+y].getLabel()=="#")			//��λ���Ա����Ϊ��,ȡ�����
		{
			num_mine.mark_mine=new Integer(num_mine.mark_mine.intValue()-1);
			buttons[x*cols+y].setBackground(Color.blue);
			buttons[x*cols+y].setLabel(" ");
			if(ismine[x*cols+y].booleanValue())			//�����λ������
				num_mine.mark_correct_mine=new Integer(num_mine.mark_correct_mine.intValue()-1);
		}
		else						//�����׵ı��	
		{
			num_mine.mark_mine=new Integer(num_mine.mark_mine.intValue()+1);
			buttons[x*cols+y].setBackground(Color.yellow);
			buttons[x*cols+y].setLabel("#");
			if(ismine[x*cols+y].booleanValue())		//��������ȷ
				num_mine.mark_correct_mine=new Integer(num_mine.mark_correct_mine.intValue()+1);
		}
		if(num_mine.mark_correct_mine.intValue()==num_mine.mark_mine.intValue()
				&&num_mine.mark_mine.intValue()==num_mine.sum_mine.intValue())
		{
			JButton btc=new JButton("YOU WIN");
			JDialog dialog=new JDialog(sjm,"you_win");
			dialog.setLayout(new FlowLayout());
			dialog.addWindowListener(new WindowAdapter()		//�Ի��������
			{
 
				@Override
				public void windowClosing(
						WindowEvent e) {
					// TODO Auto-generated method stub
					super.windowClosing(e);
					dialog.dispose();
					sjm.dispose();
				}
				
				
			});
			btc.addActionListener(new ActionListener(){			
 
				@Override
				public void actionPerformed(
						ActionEvent e) {
					// TODO Auto-generated method stub
					dialog.dispose();
					sjm.dispose();
				}
				
			});
			dialog.add(btc);
			dialog.setModal(true);				//����you_win����
			dialog.setSize(220,150 );
			dialog.setLocation(350, 250);
			dialog.setVisible(true);
		}
	}
	Num_Mine repnum()
	{
		return num_mine;
	}
 
}
