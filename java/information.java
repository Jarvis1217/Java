class student
{
	//��������
	private String name;
	private int age;
	private String interest;
	private int classnum;
	//��ӿ�
	student(String n,int a,String i,int c)
	{
		name=n;
		age=a;
		interest=i;
		classnum=c;
	}
	public void print()
	{
		System.out.print("name:" +name+"  ");
		System.out.print("age:" +age+"  ");
		System.out.print("interest:" +interest+"  ");
		System.out.println("class num:" +classnum+"  ");
	}
}

class teacher
{
	//��������
	private String name;
	private String subject;
	private String direction;
	private int tage;
	//��ӿ�
	teacher(String n,String s,String d,int t)
	{
		name=n;
		subject=s;
		direction=d;
		tage=t;
	}
	public void print()
	{
		System.out.print("name:"+name+"  ");
		System.out.print("subject:"+subject+"  ");
		System.out.print("direction:"+direction+"  ");
		System.out.println("teaching age:"+tage+"  ");
	}
}

//main����
public class  information
{
	public static void main(String[] args) 
	{
		int i=0;
		int j=0;
		student stu[]=null;
		stu=new student[3];
		stu[0]=new student("Tom",18,"listen music",1);
		stu[1]=new student("Timo",19,"watch movies",1);
		stu[2]=new student("Kimi",18,"read books",1);
		
		teacher tec=null;
		tec=new teacher("ted","English","translation",3);
		
		for (i=0;i<3;i++)
		{
			System.out.println("ѧ��"+(i+1)+"��Ϣ:");
			stu[i].print();
		}
		
		System.out.println("��ʦ��Ϣ:");
		tec.print();
	}
}
