import java.util.Scanner;
class halfArray 
{
	public static void main(String[] args) 
	{
		Scanner reader=new Scanner(System.in);
        //��������
		System.out.println("�������Զ������鳤��:");
		int n=reader.nextInt();
		int i=0;
		int[] a=new int[n];
		System.out.println("����������Ԫ��:");
		for(i=0;i<n;i++)
		{
			a[i]=reader.nextInt();
		}
		//�������
		System.out.println("�Զ�������Ϊ:");
		for(i=0;i<n;i++)
		{
			System.out.print(a[i]+" ");
		}
		System.out.println();
		//����halfArray����
		int[] c=halfArray(a,n);
		//����µ�����
		System.out.println("�µ�����Ϊ:");
		for(i=0;i<n;i++)
		{
			System.out.print(c[i]+" ");
		}
		
	}
	//����halfArray����
	public static int[] halfArray(int[] a,int n)
	{
		int j=0;
		int[] b=new int[n];
		for(j=0;j<n;j++)
		{
			b[j]=a[j]/2;
		}
		return b;
	}
}
