package netty.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import static io.netty.util.CharsetUtil.UTF_8;

/**
 * Description:
 *
 * @author zhangr
 * 2020/7/3 9:57
 */
public class EchoClientHandler extends SimpleChannelInboundHandler {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        ByteBuf byteBuf = (ByteBuf) o;
        System.out.println("Client received:" + byteBuf.toString(UTF_8));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(this.getClass().getName() + "\t" + Thread.currentThread().getStackTrace()[1].getMethodName());
        //向服务端写数据
        ctx.writeAndFlush(Unpooled.copiedBuffer("测试发送数据到 服务端 ", UTF_8));
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
