package Game;

import java.awt.*;
import java.awt.event.*;
 
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
 
 
@SuppressWarnings("serial")
public class Mine extends JFrame {
 
	Integer rn;				//用户已标记的雷数
	Integer sn;				//标记对的雷数
	public Mine(int rows,int cols,int n)		//雷区的行数,列数,雷数
	{
		My_button buttons[]=new My_button[rows*cols];	//记录每个按钮的引用
		Integer mine[]= new Integer[rows*cols];			//每个位置的数字
		Boolean ismine[]=new Boolean[rows*cols];		//是否有雷的标志
		new Calmine(mine,ismine,rows,cols,n);
		this.setSize(500, 450);
		this.setTitle("mine_sweep");
		this.setLayout(new BorderLayout());		
 
		rn=new Integer(0);				//用户标记的雷数
		sn=new Integer(0);				//标记对的雷数
		JPanel showpanel=new JPanel();			//设置控制面板
		showpanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JTextArea showArea=new JTextArea("sum of mine: "+n);
		JTextArea showrmArea=new JTextArea("num of marked  mine: "+rn.intValue());
		showArea.setEditable(false);
		showrmArea.setEditable(false);
		showpanel.add(showrmArea );
		showpanel.add(showArea);
		this.add(showpanel,BorderLayout.NORTH);  	
		
		JPanel minepanel=new JPanel();			//设置雷区
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
							showrmArea.setText("num of marked  mine: "+ans);	//更新已标记雷数;
							rn=new Integer(ans);
						}
						if(e.getButton()==MouseEvent.BUTTON3)
						{
							Num_Mine num_mine=new Num_Mine(rn,sn,n);
							num_mine=new ClickRone(buttons,btn.x,btn.y,rows,cols,num_mine,t,ismine).repnum();
							rn=new Integer(num_mine.mark_mine);
							showrmArea.setText("num of marked  mine: "+rn);	//更新已标记雷数
							sn=new Integer(num_mine.mark_correct_mine);	//更新标记正确雷数
							
						}
					}
					
				}); //添加鼠标点击事件
			}
			
			
		}
		this.addWindowListener(new WindowAdapter(){		//添加窗口关闭监听器
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

