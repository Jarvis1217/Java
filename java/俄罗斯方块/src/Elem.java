import java.awt.Image;


public class Elem {  //����˹����7�ַ���ĸ���
	
	int imgNum;  //ͼƬ�ı��
	Cell center1, center2; //center1����ת������, 2����ת������� ����ͨ��2-1�Ϳ��Եó����ƫ����
	Cell c[] = new Cell[4];  //�洢4��������������
	int T = 2, type = 0;  //T����ת����, type�ǵ�ǰ״̬
	final int sx = -1; 
	final int sy = 1;
	 
	void swap(){
		int tmp = center2.x;
		center2.x = center2.y;
		center2.y = tmp;
		return;
	}
	
	void swap(int p){
		int tmp = c[p].x;
		c[p].x = c[p].y;
		c[p].y = tmp;
		return;
	}
	
	void rotate(){   //ͼ����ԭ����ת90�Ⱦ����Ƚ���x,y ��Ȼx*sx, y*sy
		if(T == 0) return;
		swap();  //��������ת
		center2.x *= sx;
		center2.y *= sy;
		for(int i = 0; i < 4; i++){
			swap(i);
			c[i].x *= sx;
			c[i].y *= sy;
		}
		type = (++type)%4; //���㵱ǰ״̬
	}
	
}
//���涼�ǳ�ʼ������ͼ�ε�����
class I extends Elem{
	
	I(){
		imgNum = 1;
		c[0] = new Cell(1, 0);
		c[1] = new Cell(3, 0);
		c[2] = new Cell(5, 0);
		c[3] = new Cell(7, 0);
		center1 = new Cell(3, 0);
		center2 = new Cell(3, 0);
	}
	
}

class UZ extends Elem{
	
	UZ(){
		T = 2;
		imgNum = 2;
		c[0] = new Cell(2, 1);
		c[1] = new Cell(2, 3);
		c[2] = new Cell(0, 3);
		c[3] = new Cell(4, 1);
		center1 = new Cell(2, 3);
		center2 = new Cell(2, 3);
	}
	
}

class L extends Elem{
	
	L(){
		T = 4;
		imgNum = 0;
		c[0] = new Cell(1, 5);
		c[1] = new Cell(-1, 1);
		c[2] = new Cell(-1, 3);
		c[3] = new Cell(-1, 5);
		center1 = new Cell(-1, 3);
		center2 = new Cell(-1, 3);
	}
	
}

class T extends Elem{
	
	T(){
		T = 4;
		imgNum = 3;
		c[0] = new Cell(0, 1);
		c[1] = new Cell(0, 3);
		c[2] = new Cell(2, 1);
		c[3] = new Cell(-2, 1);
		center1 = new Cell(0, 1);
		center2 = new Cell(0, 1);
	}
	
}

class O extends Elem{
	
	O(){
		T = 0;
		imgNum = 4;
		c[0] = new Cell(-1, 1);
		c[1] = new Cell(1, 1);
		c[2] = new Cell(-1, 3);
		c[3] = new Cell(1, 3);
		center1 = new Cell(0, 0);
		center2 = new Cell(0, 0);
	}
	
}

class UL extends Elem{
	
	UL(){
		T = 4;
		imgNum = 5;
		c[0] = new Cell(-3, 5);
		c[1] = new Cell(-1, 1);
		c[2] = new Cell(-1, 3);
		c[3] = new Cell(-1, 5);
		center1 = new Cell(-1, 3);
		center2 = new Cell(-1, 3);
	}
	
}

class Z extends Elem{
	
	Z(){
		T = 2;
		imgNum = 6;
		c[0] = new Cell(2, 1);
		c[1] = new Cell(2, 3);
		c[2] = new Cell(4, 3);
		c[3] = new Cell(0, 1);
		center1 = new Cell(2, 3);
		center2 = new Cell(2, 3);
	}
	
}