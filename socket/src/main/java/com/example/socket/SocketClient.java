package com.example.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by Administrator on 2016/10/24 0024.
 */

public class SocketClient {

    public static void main(String[] args) {
        SocketClient socketClient=new SocketClient();
        socketClient.start();
    }

    private void start(){
        Socket socket= null;
        BufferedWriter bufferedWriter= null;
        BufferedReader bufferedReader = null;
        BufferedReader reader=null;
        try {
            socket=new Socket("127.0.0.1",9898);
            //读取输入流数据
            reader=new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
            startServerReaderListener(reader);
            //写入输入流数据
            bufferedWriter=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"));
            //读取控制台数据
            bufferedReader=new BufferedReader(new InputStreamReader(System.in,"UTF-8"));
            String msg;
            while (!(msg=bufferedReader.readLine()).equals("bye")){
                bufferedWriter.write(socket.hashCode()+":"+msg+"\n");
                bufferedWriter.flush();
//                String request=reader.readLine();
//                System.out.println(request);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bufferedReader.close();
                bufferedWriter.close();
                reader.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void startServerReaderListener(BufferedReader reader){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String msg;
                    while ((msg=reader.readLine())!=null){
                        System.out.println(msg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
