package com.example.mina;

import com.example.socket.SocketClient;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;

/**
 * Created by Administrator on 2016/10/25 0025.
 */

public class MinaClient {

    public static void main(String[] args) {
        MinaClient minaClient=new MinaClient();
        minaClient.start();
    }

    private void start(){
        NioSocketConnector connector=new NioSocketConnector();
        connector.setHandler(new MinaClientHandle());
        connector.getFilterChain().addLast("codec",new ProtocolCodecFilter(new TextLineCodecFactory()));
        ConnectFuture future=connector.connect(new InetSocketAddress("127.0.0.1",9898));
        future.awaitUninterruptibly();
        IoSession ioSession=future.getSession();
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
        try {
            String msg;
            while (!(msg=bufferedReader.readLine()).equals("byb")){
                ioSession.write(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
