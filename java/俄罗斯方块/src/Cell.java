

//cell��  x,y�����������ϵĵ�
public class Cell {
	
	int x, y;
	
	Cell(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int turntoX(){ 
		return x*30+15;  //����������x��Ӧ��Ļ��x����
	}
	
	public int turntoY(){
		return y*30+10;   //����������y��Ӧ��Ļ��y����
	}
	
	public static int turntoX(int x){
		return x*30+15;  //����������x��Ӧ��Ļ��x����,�������ഫ�ε���
	}
	
	public static int turntoY(int y){
		return y*30+10; //����������y��Ӧ��Ļ��y����,�������ഫ�ε���
	}
	
}
