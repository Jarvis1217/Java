package Ebitadd;

class Ebitadd 
{
	public static void main(String[] args) 
	{
		int[][] arr=new int[3][4];
		//第一行
		arr[0][0]=259;
		arr[0][1]=132;
		arr[0][2]=799;
		arr[0][3]=113;
		//第二行
		arr[1][0]=332;
		arr[1][1]=262;
		arr[1][2]=209;
		arr[1][3]=863;
		//第三行
		arr[2][0]=807;
		arr[2][1]=301;
		arr[2][2]=684;
		arr[2][3]=343;
		//输出矩阵
		System.out.println("原矩阵为:");
		int i,j;
		for(i=0;i<3;i++)
		{
			for(j=0;j<4;j++)
			{
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
		//找各位之和最大的数并输出
		int[] b=new int[12];
		int m=0;
		int max=0;//保存各位和的最大值
		int x=0,y=0;//保存各位和最大的数的地址
		for(i=0;i<3;i++)
		{
			for(j=0;j<4;j++)
			{
				b[m]=arr[i][j]/100%10+arr[i][j]/10%10+arr[i][j]%10;
				if(b[m]>max)
				{
					max=b[m];
					x=i;
					y=j;
				}
				m++;
			}
		}
		System.out.println();
		System.out.println("各位和最大的数是:"+arr[x][y]);
		System.out.println("这个数位于原矩阵第"+(x+1)+"行"+",第"+(y+1)+"列");
	}
}
