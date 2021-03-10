package Game;

import java.awt.*;
import java.awt.event.*;
 
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
 
 
@SuppressWarnings("serial")
public class Mine extends JFrame {
 
	Integer rn;				//�û��ѱ�ǵ�����
	Integer sn;				//��ǶԵ�����
	public Mine(int rows,int cols,int n)		//����������,����,����
	{
		My_button buttons[]=new My_button[rows*cols];	//��¼ÿ����ť������
		Integer mine[]= new Integer[rows*cols];			//ÿ��λ�õ�����
		Boolean ismine[]=new Boolean[rows*cols];		//�Ƿ����׵ı�־
		new Calmine(mine,ismine,rows,cols,n);
		this.setSize(500, 450);
		this.setTitle("mine_sweep");
		this.setLayout(new BorderLayout());		
 
		rn=new Integer(0);				//�û���ǵ�����
		sn=new Integer(0);				//��ǶԵ�����
		JPanel showpanel=new JPanel();			//���ÿ������
		showpanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JTextArea showArea=new JTextArea("sum of mine: "+n);
		JTextArea showrmArea=new JTextArea("num of marked  mine: "+rn.intValue());
		showArea.setEditable(false);
		showrmArea.setEditable(false);
		showpanel.add(showrmArea );
		showpanel.add(showArea);
		this.add(showpanel,BorderLayout.NORTH);  	
		
		JPanel minepanel=new JPanel();			//��������
		minepanel.setLayout(new GridLayout(rows,cols));
		minepanel.setSize(500, 450);
		Mine t=this;
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<cols;j++)
			{
				My_button btn=new My_button(i,j);
				buttons[i*cols+j]=btn;
				minepanel.add(buttons[i*cols+j] );
				btn.setBackground(Color.blue);
				btn.addMouseListener(new MouseAdapter()
				{
					public void mouseClicked(MouseEvent e)
					{
						if(e.getButton()==MouseEvent.BUTTON1)
						{
							int ans=rn.intValue();
							if(btn.getLabel()!="#")
								 ans = new ClickLone(buttons,btn.x,btn.y,rows,cols,mine,ismine,t,rn).rep();		
							showrmArea.setText("num of marked  mine: "+ans);	//�����ѱ������;
							rn=new Integer(ans);
						}
						if(e.getButton()==MouseEvent.BUTTON3)
						{
							Num_Mine num_mine=new Num_Mine(rn,sn,n);
							num_mine=new ClickRone(buttons,btn.x,btn.y,rows,cols,num_mine,t,ismine).repnum();
							rn=new Integer(num_mine.mark_mine);
							showrmArea.setText("num of marked  mine: "+rn);	//�����ѱ������
							sn=new Integer(num_mine.mark_correct_mine);	//���±����ȷ����
							
						}
					}
					
				}); //���������¼�
			}
			
			
		}
		this.addWindowListener(new WindowAdapter(){		//��Ӵ��ڹرռ�����
			public void windowClosing(WindowEvent e)
			{
				t.dispose();
			}
		});
		this.add(minepanel,BorderLayout.SOUTH);
		minepanel.setVisible(true);
		this.setVisible(true);
	}
 
}

