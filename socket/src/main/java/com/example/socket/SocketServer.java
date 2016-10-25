package com.example.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class SocketServer {


    public static void main(String[] args){
        SocketServer socketServer=new SocketServer();
        socketServer.start();
    }

    private void start(){
        ServerSocket serverSocket=null;
        Socket socket=null;
        try {
            serverSocket = new ServerSocket(9898);
            while (true){
                //阻塞
                System.out.println("server start");
                socket=serverSocket.accept();
                //接收消息
                System.out.println("用户："+socket.hashCode());
                manageSocket(socket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void manageSocket(Socket socket) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                BufferedReader bufferedReader=null;
                BufferedWriter bufferedWriter=null;
                //写入数据流数据
                try {
                    bufferedWriter=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"));
//            startHeartbeat(bufferedWriter);
                    //读取输入流数据
                    bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
                    String msg;
                    while ((msg=bufferedReader.readLine())!=null){
                        System.out.println("服务器："+msg);
                        bufferedWriter.write("server:"+msg+"\n");
                        bufferedWriter.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        bufferedWriter.close();
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();


    }

    private void startHeartbeat(BufferedWriter writer){
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    String msg="keep connect";
                    System.out.println(msg);
                    writer.write(msg+"\n");
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        },3000,3000);
    }



}
