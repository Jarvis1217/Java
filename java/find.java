class find 
{
	public static void main(String[] args) 
	{	
		int[] x=new int[] {20,10,50,40,30,70,95,80,90,100};
		int i;
		int max=x[0];
		boolean flag=false;
		int temp=0;
		//������ֵ
		for(i=1;i<10;i++)
		{
			if(x[i]>max)
			{
				max=x[i];
			}
		}
		System.out.println("�����е����ֵΪ"+max);
		//�ҳ�95
		for(i=0;i<10;i++)
		{
			if(x[i]==95)
			{
				flag=true;
				temp=i+1;
			}
		}
		if(flag)
			System.out.println("�����д���95����λ�ڵ�"+temp+"��λ��");
		else
			System.out.println("�����в�����95");
	}
}
