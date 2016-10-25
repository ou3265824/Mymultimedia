package com.example.mina.codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

/**
 * Created by Administrator on 2016/10/24 0024.
 */

public class MinaTextCumulativeDecoder extends CumulativeProtocolDecoder{


    @Override
    protected boolean doDecode(IoSession ioSession, IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
        //开始位置
        int startposition=ioBuffer.position();
        while (ioBuffer.hasRemaining()){
            byte b=ioBuffer.get();
            if (b=='\n'){
                //结束位置
                int currentposition=ioBuffer.position();
                //总长度
                int limit=ioBuffer.limit();
                //截取字段
                ioBuffer.position(startposition);
                ioBuffer.limit(limit);
                IoBuffer buffer=ioBuffer.slice();
                //转换成string
                byte[] bytes=new byte[buffer.limit()];
                buffer.get(bytes);
                String s=new String(bytes);
                protocolDecoderOutput.write(s);
                //设置成原来的位置
                ioBuffer.position(currentposition);
                ioBuffer.limit(limit);
                return true;
            }
        }
        ioBuffer.position(startposition);
        return false;
    }
}
