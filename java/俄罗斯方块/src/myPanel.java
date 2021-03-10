import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.TreeSet;

import javax.imageio.ImageIO;
import javax.swing.*;

public class myPanel extends JPanel implements KeyListener, Runnable{
	
	boolean gameOver = false;  //判断游戏结束
	
	int nextNum; //下一块的编号
	int fallCycle = 800; //每fallCycle单位时间下降一格
	
	Elem ne; //当前方块
	Elem se[] = new Elem[7]; //用于保存所有种类方块的数组
	Cell np = new Cell(5, 0); //下落点坐标
	
	final static int W = 10; //宽度
	final static int H = 17; //高度
	
	private static Image background; //背景图片
	public static Image sq[] = new Image[7];  //保存图片的数组
	
	public static int cell[][] = new int[H][W]; //保存当前整个状态的数组
	
	
	//加载静态资源
	static{
		try{
			background = ImageIO.read(myPanel.class.getResource("tetris.png"));
			sq[0]=ImageIO.read(myPanel.class.getResource("T.png"));
			sq[1]=ImageIO.read(myPanel.class.getResource("I.png"));
			sq[2]=ImageIO.read(myPanel.class.getResource("Z.png"));
			sq[3]=ImageIO.read(myPanel.class.getResource("L.png"));
			sq[4]=ImageIO.read(myPanel.class.getResource("J.png"));
			sq[5]=ImageIO.read(myPanel.class.getResource("O.png"));
			sq[6]=ImageIO.read(myPanel.class.getResource("S.png"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	int getx(int i){ //获得当前方块x的坐标
		int dx = ne.center2.x-ne.center1.x; //计算绕原点旋转后的偏移量
		int x1 = (ne.c[i].x-dx)*15+np.turntoX(); //获得图片上的x
		if((ne.c[i].x-dx)%2 != 0) x1 += 15;
		return x1;
	}
	
	int gety(int i){ //获得当前方块y的坐标
		int dy = ne.center2.y-ne.center1.y;
		int y1 = (ne.c[i].y-dy)*15+np.turntoY();
		if((ne.c[i].y-dy)%2 != 0) y1 += 15;
		return y1;
	}
	
	
	void drawNowfk(Graphics g){ //绘制当前方块
		for(int i = 0; i < 4; i++){
			g.drawImage(sq[ne.imgNum], getx(i), gety(i), this);
		}
	}
	
	void drawNextfk(Graphics g){ //绘制下个方块
		for(int i = 0; i < 4; i++){
			int x = 420+se[nextNum].c[i].x*15;
			int y = 35+se[nextNum].c[i].y*15;
			g.drawImage(sq[se[nextNum].imgNum], x, y, this);
		}
	}
	
	void drawCell(Graphics g){ //绘制之前停下的方块
		for(int i = 0; i < H; i++)
			for(int j = 0; j < W; j++)
				if(cell[i][j] != -1)
					g.drawImage(sq[cell[i][j]], Cell.turntoX(j), Cell.turntoY(i), this);
	}
	
	protected void paintComponent(Graphics g){
		g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
		g.drawLine(15, 70, 315, 70);  //绘制死线
		drawNowfk(g); 
		drawNextfk(g);
		drawCell(g);
	}
	
	myPanel(){
		for(int i = 0; i < H; i++)//初始化地图, -1代表空
			Arrays.fill(cell[i], -1);
		//给所有方块编号
		se[0] = new I();  
		se[1] = new T();
		se[2] = new O();
		se[5] = new L();
		se[6] = new Z();
		se[4] = new UL();
		se[3] = new UZ();
		ne = se[(int)(Math.random()*7)];
		nextNum = (int)(Math.random()*7);
	}
	
	void moveRight(){
		check(1, 0); //判断是否可以移动
		np.x += 1;
	}
	
	void moveLeft(){
		check(-1, 0);//判断是否可以移动
		np.x -= 1;
	}
	
	void moveUp(){
		np.y -= 1;
	}
	
	void moveDown(){
		np.y += 1;
	}
	
	void xc(int y){ //消除当前行,把它上面的全部下移一格
		for(int i = y; i > 0; i--)
			for(int j = 0; j < W; j++)
				cell[i][j] = cell[i-1][j]; 
	}
	
	void isxc(int y){
		for(int i = 0; i < W; i++){
			if(cell[y][i] == -1) return;			//如果有空格返回
		}
		xc(y); //不存在空格消除第y行
	}
	
	void stopNe(){ //让当前方块停止,并把他的位置记录到数组里
		TreeSet<Integer> set = new TreeSet(); //保存当前方块停在的行
		for(int i = 0; i < 4; i++){
			int x = getx(i);
			int y = gety(i);
			cell[(y-10)/30][(x-15)/30] = ne.imgNum; 
			if((y-10)/30 <= 3){
				gameOver = true;
			}
			set.add((y-10)/30);
		}
		for(int t:set) isxc(t); //检测是否可以消除
		while(ne.type != 0) ne.rotate(); //把当前方块转会初始状态,以便下次使用
		ne = se[nextNum];
		nextNum = (int)(Math.random()*7);
		np.x = 5; np.y = 0;
	}
	
	void check(int dx, int dy){ //检查可否继续移动 dx表示判断x方向 dy同理
		for(int i = 0; i < 4; i++){
			int x = (getx(i)-15)/30+dx;
			int y = (gety(i)-10)/30+dy;
			if(x < 0 || x == W || y == H || cell[y][x] != -1){ //如果无法移动
				if(dy != 0) stopNe(); //如果是检查下落就把当前方块停下
				else np.x -= dx; //水平方向就让他回到之前位置
				return;
			}
		}
		return;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int time = 0;
		while(!gameOver){
			try {
				time += 100;  
				Thread.sleep(100);
			} catch (InterruptedException e) {}
			if(time == fallCycle){
				moveDown(); //fallCycle/100 次下落一格
				check(0, 1); //检查可否下落
				time = 0;
			}
			this.repaint();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	boolean dge(){  //判断当前方块是否合法的函数
		boolean flag = true;
		for(int i = 0; i < 4; i++){
			int x = (getx(i)-15)/30;
			int y = (gety(i)-10)/30;
			if(x < 0 || x >= W || y < 0 || y >= H || cell[y][x] != -1)
				flag = false;
		}
		return flag;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_UP){ //上键旋转
			ne.rotate();
			if(ne.type == 2 && ne.T == 2){//如果是周期为2且处于状态2在旋转, 就直接让他回到第一状态
				ne.rotate();
				ne.rotate();
			}
			if(!dge())  //如果旋转后的位置不合法就转回去
				for(int i = 0; i < 3; i++)
					ne.rotate();
		}  
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			moveLeft();
			check(0, 1);
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			moveRight();
			check(0, 1);
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN){
			moveDown();
			check(0, 1);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	
	}
}
