package com.example.mina;

import com.example.mina.codec.MinaTextLineFactory;

import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by Administrator on 2016/10/24 0024.
 */

public class MinaServer {

    public static void main(String[] args) {
        MinaServer minaServer=new MinaServer();
        minaServer.start();
    }

    private void start(){
        NioSocketAcceptor nioSocketAcceptor=new NioSocketAcceptor();
        try {
            nioSocketAcceptor.setHandler(new MinaServerHandle());
            //拦截器
            nioSocketAcceptor.getFilterChain()
                    //二进制跟对象数据转换
                    .addLast("codec",new ProtocolCodecFilter(new MinaTextLineFactory()));
            nioSocketAcceptor.bind(new InetSocketAddress(9898));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
