import java.util.Scanner;
import java.util.Random;
public class GuessNumber {
	public static void main (String args[]) {
	      Scanner reader = new Scanner(System.in);
	      Random random = new Random();
	      System.out.println("����һ��1��100֮�������,��²������");
	      //random.nextInt(100)��[0,100)�е��������
	      int realNumber = random.nextInt(100)+1;
	      int yourGuess = 0;
	      System.out.print("�������Ĳ²�:");  
	      yourGuess = reader.nextInt();
	      while(yourGuess>=0&&yourGuess<100&&yourGuess!=realNumber) //ѭ������
	      {
	         if(yourGuess>realNumber)   //�´��˵���������
	         {
	             System.out.print("�´���,��������Ĳ²�:");
	             yourGuess = reader.nextInt();
	         }
	         else if(yourGuess<realNumber) //��С�˵���������
	         {
	             System.out.print("��С��,��������Ĳ²�:");
	             yourGuess = reader.nextInt();
	         }
	      }
	      System.out.println("�¶���!");
	   }
}