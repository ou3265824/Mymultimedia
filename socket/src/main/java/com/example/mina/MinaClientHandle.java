package com.example.mina;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * Created by Administrator on 2016/10/24 0024.
 */

public class MinaClientHandle extends IoHandlerAdapter{


    @Override
    public void sessionCreated(IoSession session) throws Exception {
//        super.sessionCreated(session);
        System.out.println("sessionCreated");
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
//        super.sessionOpened(session);
        System.out.println("sessionOpened");
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
//        super.sessionClosed(session);
        System.out.println("sessionClosed");
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
//        super.sessionIdle(session, status);
        System.out.println("sessionIdle");
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
//        super.exceptionCaught(session, cause);
        System.out.println("exceptionCaught");
    }

    //接收消息
    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
//        super.messageReceived(session, message);
        System.out.println("messageReceived");
        System.out.println("消息："+message.toString());
//        session.write("server:"+message.toString());
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
//        super.messageSent(session, message);
        System.out.println("messageSent");
    }

    @Override
    public void inputClosed(IoSession session) throws Exception {
//        super.inputClosed(session);
        System.out.println("inputClosed");
    }
}
