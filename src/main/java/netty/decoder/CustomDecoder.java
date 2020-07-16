package netty.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Description:
 *
 * @author zhangr
 * 2020/7/9 10:45
 */
public class CustomDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        //int是四个字节，需要检查下是否满足
        if (in.readableBytes() >= 4)
            out.add(in.readInt());  //添加到解码信息里面去
    }
}
