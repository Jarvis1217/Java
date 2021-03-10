package com.myself.test.test1;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static  final int SERVER_PORT = 9001;

    public Client() {
        try {
            InetAddress address = InetAddress.getByName("localhost");
            Socket s = new Socket(address,SERVER_PORT);
            System.out.println("客户端已启动...");
            InputStream in = s.getInputStream();
            OutputStream out = s.getOutputStream();

            System.out.print("输入消息:");
            Scanner sc = new Scanner(System.in);
            String strToServer = sc.nextLine();
            out.write(strToServer.getBytes());

            byte[] buf = new byte[1024];
            int len = in.read(buf);
            String strFromServer = new String(buf,0,len);
            System.out.print("服务器应答>>>");
            System.out.println(strFromServer);
            in.close();out.close();
            s.close();
            System.out.println("客户端已关闭");
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Client();
    }
}
