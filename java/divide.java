import java.util.Scanner;
class divide 
{
	public static void main(String[] args) 
	{
		Scanner reader=new Scanner(System.in);
		int n;
		do
		{
			System.out.println("����������(0<n<1000):");
			n=reader.nextInt();
			//�ݴ���
			if(n<=0||n>=1000)
			{
				System.out.println("��������,����������:");
				System.out.println();
				continue;
			}
			//�ж�
			if(n%9==0&&n!=1)
			{
				System.out.println(n+"�ܱ�9����");
				System.out.println();
			}
			else if(n%9!=0&&n!=1)
			{
				System.out.println(n+"���ܱ�9����");
				System.out.println();
			}
			//����
			if(n==1)
			{
				System.out.println("��лʹ��!");
				break;
			}
		}while(n!=1);
	}
}
