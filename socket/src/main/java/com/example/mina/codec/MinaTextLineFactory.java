package com.example.mina.codec;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;

/**
 * Created by Administrator on 2016/10/24 0024.
 */

public class MinaTextLineFactory implements ProtocolCodecFactory{

    private MinaTextLineDecoder decoder;

    private MinaTextCumulativeDecoder cumulativeDecoder;

    private MinaTextLineEncoder encoder;

    public MinaTextLineFactory() {
        decoder=new MinaTextLineDecoder();
        cumulativeDecoder=new MinaTextCumulativeDecoder();
        encoder=new MinaTextLineEncoder();
    }

    @Override
    public ProtocolEncoder getEncoder(IoSession ioSession) throws Exception {
        return encoder;
    }

    @Override
    public ProtocolDecoder getDecoder(IoSession ioSession) throws Exception {
        return cumulativeDecoder;
//        return decoder;
    }
}
