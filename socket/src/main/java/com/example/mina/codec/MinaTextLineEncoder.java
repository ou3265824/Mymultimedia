package com.example.mina.codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

/**
 * Created by Administrator on 2016/10/24 0024.
 */

public class MinaTextLineEncoder implements ProtocolEncoder{
    @Override
    public void encode(IoSession ioSession, Object o, ProtocolEncoderOutput protocolEncoderOutput) throws Exception {
        String message=null;
        if(o instanceof String){
            message= (String) o;
        }
        if (message!=null){
            //复用编码
            CharsetEncoder charsetEncoder= (CharsetEncoder) ioSession.getAttribute("encoder");
            if (charsetEncoder==null){
                charsetEncoder= Charset.defaultCharset().newEncoder();
                ioSession.setAttribute("encoder",charsetEncoder);
            }
            //开辟内存空间
            IoBuffer ioBuffer=IoBuffer.allocate(message.length());
            ioBuffer.setAutoExpand(true);
            ioBuffer.putString(message,charsetEncoder);
            ioBuffer.flip();
            protocolEncoderOutput.write(ioBuffer);

        }

    }

    @Override
    public void dispose(IoSession ioSession) throws Exception {

    }
}
