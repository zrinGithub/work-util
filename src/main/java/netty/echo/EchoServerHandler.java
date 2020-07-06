package netty.echo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;


import static io.netty.util.CharsetUtil.UTF_8;

/**
 * Description:
 *
 * @author zhangr
 * 2020/7/3 9:04
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf data = (ByteBuf) msg;
        System.out.println("EchoServerHandler channelRead：" + data.toString(UTF_8));
        ctx.writeAndFlush(data);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println(this.getClass().getName() + "\t" + Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
