import java.util.Scanner;
class Average
{
	public static void main(String[] args) 
	{
		Scanner reader=new Scanner(System.in);
		System.out.print("���������鳤��:");
		int n=reader.nextInt();
		//��������
		int i=0;
		int[] a=new int[n];
		System.out.print("����������:");
		for (i=0;i<n;i++)
		{
			a[i]=reader.nextInt();
		}

		//��ƽ����
		int sum=0;
		for (i=0;i<n;i++)
		{
			sum=sum+a[i];
		}
		int Average=sum/n;
		System.out.print("Average="+Average);
		System.out.println();

		//�����ֵ
		int max=a[0];
		for (i=1;i<n;i++)
		{
			if(a[i]>max)
				max=a[i];
		}
		System.out.print("max="+max);
		System.out.println();

		//����Сֵ
		int min=a[0];
		for (i=1;i<n;i++)
		{
			if(a[i]<min)
				min=a[i];
		}
		System.out.print("min="+min);
		System.out.println();
	}
}
