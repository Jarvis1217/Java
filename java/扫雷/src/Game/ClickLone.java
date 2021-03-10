package Game;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import java.util.Queue;
 
import javax.swing.JButton;
import javax.swing.JDialog;
 
public class ClickLone {
	My_button buttons[]; 		//��ť������
	int i,j;
	int rows,cols;
	Integer  mine[];
	Boolean ismine[];
	boolean used[];
	Integer rn;				//�Ѿ���ǵ�����
	Mine sjm;
	int dx[]={1,-1,0};
	int dy[]={1,-1,0};
	ClickLone(My_button[] tem,int a,int b,int r,int l,Integer mines[],Boolean ismines[],Mine Jm,Integer n)
	{
		buttons=tem;
		rn=n;
		mine=mines;
		ismine=ismines;
		rows=r;
		cols=l;
		i=a;
		j=b;
		sjm=Jm;
		used=new boolean[rows*cols];
		for(int i=0;i<rows*cols;i++)
			used[i]=false;
		if(ismine[i*cols+j].booleanValue())			//���ѡ�е���
		{
			buttons[i*cols+j].setEnabled(false);
			buttons[i*cols+j].setLabel("*");
			buttons[i*cols+j].setBackground(Color.red);
			JButton btc=new JButton("GAME OVER!");
			JDialog dialog=new JDialog(sjm,"game_over");
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
			dialog.setModal(true);				//����game_over����
			dialog.setSize(220,150 );
			dialog.setLocation(350, 250);
			dialog.setVisible(true);
			
			return;
		}
		Bfs(i,j);
//		buttons[i*cols+j].setLabel(mine[i*cols+j].toString());
	//	if(ismine[i*cols+j].booleanValue())
	//	{
	//		buttons[i*cols+j].setLabel("*");
			
	//	}
		
	}
	int rep()
	{
		return rn.intValue();
	}
	void Bfs(int i,int j)			//�����������
	{
		Queue<Integer> que=new LinkedList<Integer>();
		que.offer(new Integer(i*cols+j));
		while(!que.isEmpty())
		{
			int t=que.poll().intValue();
			if(new String("#").equals(buttons[t].getLabel()))		//�����λ�ñ��Ϊ��,˵���û���Ǵ���
				rn=new Integer(rn.intValue()-1);			//�û���ǵ�����-1
			if(new String("0").equals(mine[t].toString()) )
				buttons[t].setLabel(" ");
			else
			{
				buttons[t].setLabel(mine[t].toString());
			}
 
			buttons[t].setEnabled(false);
			buttons[t].setBackground(Color.gray);
			if(mine[t].intValue()!=0)
				continue;
			i=t/cols;
			j=t%cols;
			for(int x=0;x<3;x++)
				for(int y=0;y<3;y++)
				{
					int nx=i+dx[x],ny=j+dy[y];
					if(nx>=0&&ny>=0&&nx<rows&&ny<cols&&			//û��Խ��
						mine[i*cols+j].intValue()==0					// �ǿհ�������û����
						&&(!ismine[nx*cols+ny].booleanValue())
						&&(!(nx==i&&ny==j))
						&&(!used[nx*cols+ny])
					)	
					{
						used[nx*cols+ny]=true;
						que.offer(new Integer(nx*cols+ny));
						
					}
				}
 
		}
		
	}
}
