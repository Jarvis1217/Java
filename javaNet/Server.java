package com.myself.test.test1;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private static  final int SERVER_PORT = 9001;

    public Server() {
        try {
            ServerSocket ss = new ServerSocket(SERVER_PORT);
            System.out.println("服务器启动，等待客户端...");
            Socket s = ss.accept();
            InputStream in = s.getInputStream();
            OutputStream out = s.getOutputStream();

            byte[] buf = new byte[1024];
            int len = in.read(buf);
            String strFromClient = new String(buf,0,len);
            System.out.print("客户端的消息>>>");
            System.out.println(strFromClient);

            System.out.print("输入消息:");
            Scanner sc = new Scanner(System.in);
            String strToClient = sc.nextLine();
            out.write(strToClient.getBytes());
            in.close();out.close();
            s.close();ss.close();
            System.out.println("服务端已关闭...");
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server();
    }
}
