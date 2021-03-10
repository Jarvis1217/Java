

//cell类  x,y代表了数组上的点
public class Cell {
	
	int x, y;
	
	Cell(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int turntoX(){ 
		return x*30+15;  //返回数组上x对应屏幕的x坐标
	}
	
	public int turntoY(){
		return y*30+10;   //返回数组上y对应屏幕的y坐标
	}
	
	public static int turntoX(int x){
		return x*30+15;  //返回数组上x对应屏幕的x坐标,供其它类传参调用
	}
	
	public static int turntoY(int y){
		return y*30+10; //返回数组上y对应屏幕的y坐标,供其它类传参调用
	}
	
}
