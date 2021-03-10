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
	
	boolean gameOver = false;  //�ж���Ϸ����
	
	int nextNum; //��һ��ı��
	int fallCycle = 800; //ÿfallCycle��λʱ���½�һ��
	
	Elem ne; //��ǰ����
	Elem se[] = new Elem[7]; //���ڱ����������෽�������
	Cell np = new Cell(5, 0); //���������
	
	final static int W = 10; //���
	final static int H = 17; //�߶�
	
	private static Image background; //����ͼƬ
	public static Image sq[] = new Image[7];  //����ͼƬ������
	
	public static int cell[][] = new int[H][W]; //���浱ǰ����״̬������
	
	
	//���ؾ�̬��Դ
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
	
	int getx(int i){ //��õ�ǰ����x������
		int dx = ne.center2.x-ne.center1.x; //������ԭ����ת���ƫ����
		int x1 = (ne.c[i].x-dx)*15+np.turntoX(); //���ͼƬ�ϵ�x
		if((ne.c[i].x-dx)%2 != 0) x1 += 15;
		return x1;
	}
	
	int gety(int i){ //��õ�ǰ����y������
		int dy = ne.center2.y-ne.center1.y;
		int y1 = (ne.c[i].y-dy)*15+np.turntoY();
		if((ne.c[i].y-dy)%2 != 0) y1 += 15;
		return y1;
	}
	
	
	void drawNowfk(Graphics g){ //���Ƶ�ǰ����
		for(int i = 0; i < 4; i++){
			g.drawImage(sq[ne.imgNum], getx(i), gety(i), this);
		}
	}
	
	void drawNextfk(Graphics g){ //�����¸�����
		for(int i = 0; i < 4; i++){
			int x = 420+se[nextNum].c[i].x*15;
			int y = 35+se[nextNum].c[i].y*15;
			g.drawImage(sq[se[nextNum].imgNum], x, y, this);
		}
	}
	
	void drawCell(Graphics g){ //����֮ǰͣ�µķ���
		for(int i = 0; i < H; i++)
			for(int j = 0; j < W; j++)
				if(cell[i][j] != -1)
					g.drawImage(sq[cell[i][j]], Cell.turntoX(j), Cell.turntoY(i), this);
	}
	
	protected void paintComponent(Graphics g){
		g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
		g.drawLine(15, 70, 315, 70);  //��������
		drawNowfk(g); 
		drawNextfk(g);
		drawCell(g);
	}
	
	myPanel(){
		for(int i = 0; i < H; i++)//��ʼ����ͼ, -1�����
			Arrays.fill(cell[i], -1);
		//�����з�����
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
		check(1, 0); //�ж��Ƿ�����ƶ�
		np.x += 1;
	}
	
	void moveLeft(){
		check(-1, 0);//�ж��Ƿ�����ƶ�
		np.x -= 1;
	}
	
	void moveUp(){
		np.y -= 1;
	}
	
	void moveDown(){
		np.y += 1;
	}
	
	void xc(int y){ //������ǰ��,���������ȫ������һ��
		for(int i = y; i > 0; i--)
			for(int j = 0; j < W; j++)
				cell[i][j] = cell[i-1][j]; 
	}
	
	void isxc(int y){
		for(int i = 0; i < W; i++){
			if(cell[y][i] == -1) return;			//����пո񷵻�
		}
		xc(y); //�����ڿո�������y��
	}
	
	void stopNe(){ //�õ�ǰ����ֹͣ,��������λ�ü�¼��������
		TreeSet<Integer> set = new TreeSet(); //���浱ǰ����ͣ�ڵ���
		for(int i = 0; i < 4; i++){
			int x = getx(i);
			int y = gety(i);
			cell[(y-10)/30][(x-15)/30] = ne.imgNum; 
			if((y-10)/30 <= 3){
				gameOver = true;
			}
			set.add((y-10)/30);
		}
		for(int t:set) isxc(t); //����Ƿ��������
		while(ne.type != 0) ne.rotate(); //�ѵ�ǰ����ת���ʼ״̬,�Ա��´�ʹ��
		ne = se[nextNum];
		nextNum = (int)(Math.random()*7);
		np.x = 5; np.y = 0;
	}
	
	void check(int dx, int dy){ //���ɷ�����ƶ� dx��ʾ�ж�x���� dyͬ��
		for(int i = 0; i < 4; i++){
			int x = (getx(i)-15)/30+dx;
			int y = (gety(i)-10)/30+dy;
			if(x < 0 || x == W || y == H || cell[y][x] != -1){ //����޷��ƶ�
				if(dy != 0) stopNe(); //����Ǽ������Ͱѵ�ǰ����ͣ��
				else np.x -= dx; //ˮƽ����������ص�֮ǰλ��
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
				moveDown(); //fallCycle/100 ������һ��
				check(0, 1); //���ɷ�����
				time = 0;
			}
			this.repaint();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	boolean dge(){  //�жϵ�ǰ�����Ƿ�Ϸ��ĺ���
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
		if(e.getKeyCode() == KeyEvent.VK_UP){ //�ϼ���ת
			ne.rotate();
			if(ne.type == 2 && ne.T == 2){//���������Ϊ2�Ҵ���״̬2����ת, ��ֱ�������ص���һ״̬
				ne.rotate();
				ne.rotate();
			}
			if(!dge())  //�����ת���λ�ò��Ϸ���ת��ȥ
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
