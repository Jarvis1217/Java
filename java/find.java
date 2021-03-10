class find 
{
	public static void main(String[] args) 
	{	
		int[] x=new int[] {20,10,50,40,30,70,95,80,90,100};
		int i;
		int max=x[0];
		boolean flag=false;
		int temp=0;
		//输出最大值
		for(i=1;i<10;i++)
		{
			if(x[i]>max)
			{
				max=x[i];
			}
		}
		System.out.println("数组中的最大值为"+max);
		//找出95
		for(i=0;i<10;i++)
		{
			if(x[i]==95)
			{
				flag=true;
				temp=i+1;
			}
		}
		if(flag)
			System.out.println("数组中存在95，且位于第"+temp+"个位置");
		else
			System.out.println("数组中不存在95");
	}
}
