import java.util.Scanner;
class NumberOff 
{
	public static void main(String[] args) 
	{
		Scanner sc=new Scanner(System.in);
		int n;
		System.out.print("����������:");
		n=sc.nextInt();
		int[] a=new int[n];
		//�������в����
		int i;
		for (i=0;i<n;i++)
		{
			a[i]=i+1;
		}
		//��һ�����
		System.out.println("��ǰ�������������:");
		for (i=0;i<n;i++)
		{
			System.out.print(a[i]+" ");
		}
		
		System.out.println();
		
		//���º����
		System.out.println("���º�������������:");
		for (i=0;i<n;i++)
		{
			if (a[i]%3==0)
				continue;
			else
			System.out.print(a[i]+" ");
		}
	}
}
