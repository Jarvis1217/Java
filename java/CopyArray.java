package CopyArray;

import java.util.Arrays;
public class CopyArray {
   public static void main (String args[ ]) {
      int [] a = {1,2,3,4,500,600,700,800};
      int [] b,c,d;
      System.out.println(Arrays.toString(a));
      
      b = Arrays.copyOf(a,a.length);
      
      System.out.println(Arrays.toString(b)); 
      
      c = Arrays.copyOf(a,4);//Arrays����copyOf������������a��ǰ4��Ԫ��   
      
      System.out.println(Arrays.toString(c)); //Arrays����toString������������c��Ԫ��ֵ�ı�ʾ��ʽ
      
      d = Arrays.copyOfRange(a,4,7);//Arrays����copyOfRange������������a�ĺ�4��Ԫ��
      
      System.out.println(Arrays.toString(d));
      
    c[c.length-1] = -100; //��-100��������c�����һ��Ԫ��
    
      d[d.length-1] = -200;
      
      System.out.println(Arrays.toString(c));
      
      System.out.println(Arrays.toString(d));
   }
}
