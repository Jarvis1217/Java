package Game;

import java.util.Random;
 
public class Calmine {
	Integer mine[];
	Boolean ismine[];
	int rows,cols;
	int dx[]={-1,1,0};
	int dy[]={-1,1,0};
	int num;
	Random rd;
	Calmine(Integer m[],Boolean ism[],int r,int l,int n)
	{
		rows=r;
		cols=l;
		num=n;
		mine=m;
		ismine=ism;
		rd=new Random();
		for(int i=0;i<rows*cols;i++)			//初始化
		{
			ismine[i]=new Boolean(false);
			mine[i]=new Integer(0);
		}
		for(int i=0;i<num;i++)		//随机产生n雷
		{
			int rom=rd.nextInt(rows*cols);
			while(ismine[rom]==true)			//防止生成重复的随机数
				rom=rd.nextInt(rows*cols);
			ismine[rom]=new Boolean(true);
		}
		for(int i=0;i<rows;i++)			//计算每个按钮按下后应该显示的数字
			for(int j=0;j<cols;j++)
			{
				int t=0;
				for(int x=0;x<3;x++)		//周围8个位置
					for(int y=0;y<3;y++)
					{
						int nx=dx[x]+i,ny=dy[y]+j;
						if(nx>=0&&ny>=0&&nx<rows&&ny<cols&&(!ismine[i*cols+j].booleanValue()))
						{
							t+=ismine[nx*cols+ny].booleanValue()?1:0;
						}
					}
				mine[i*cols+j]=new Integer(t);
			}
	}
 
}

