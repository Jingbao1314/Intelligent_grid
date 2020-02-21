package com.jingbao.core.handler;

import com.jingbao.base.http.HttpGet;
import com.jingbao.base.http.HttpPost;
import com.jingbao.core.load.ServiceEntity;
import com.jingbao.core.load.ServiceLoad;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;

import java.util.HashMap;

import static io.netty.handler.codec.http.HttpHeaderNames.ACCESS_CONTROL_ALLOW_ORIGIN;
import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
import static io.netty.handler.codec.stomp.StompHeaders.CONTENT_LENGTH;

/**
 * Created by jingbao on 18-11-1.
 */

public class MyHandler extends ChannelInboundHandlerAdapter {
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ChannelInboundHandlerAdapter.class);
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {

        FullHttpRequest fhr = (FullHttpRequest) msg;
        HttpMethod httpMethod = fhr.method();

        String result="{}";
        ByteBuf buf = fhr.content();
        byte[] res = new byte[buf.readableBytes()];
        buf.readBytes(res);

        // 5.根据method，确定不同的逻辑
        if(httpMethod.equals(HttpMethod.GET)){
            log.info("REQUEST DATA-------"+fhr.uri().split("[?]"));
            System.out.println("REQUEST DATA-------"+fhr.uri().split("[?]"));
            String data=fhr.uri();
            HashMap parms=new HashMap();
            QueryStringDecoder decoder = new QueryStringDecoder(data);
            decoder.parameters().entrySet().forEach( entry -> {
                // entry.getValue()是一个List, 只取第一个元素
                parms.put(entry.getKey(), entry.getValue().get(0));
            });
            ServiceEntity serviceEntity= ServiceLoad.urlMapping.get(fhr.uri().split("[?]")[0]);
            HttpGet httpGet=new HttpGet(data,serviceEntity,parms);
            result=httpGet.invoke();
            // TODO
        }

        if(httpMethod.equals(HttpMethod.POST)){
            String data=new String(res,"utf8");
            System.out.println("REQUEST DATA-------"+data);
            log.info("REQUEST DATA-------"+data);
            ServiceEntity serviceEntity= ServiceLoad.urlMapping.get(fhr.uri());
            HttpPost httpPost=new HttpPost(data,serviceEntity);
            result=httpPost.invoke();
        }

        if(httpMethod.equals(HttpMethod.PUT)){
            // TODO
        }

        if(httpMethod.equals(HttpMethod.DELETE)){
            // TODO
        }


        FullHttpResponse response = null;
        response = new DefaultFullHttpResponse(HTTP_1_1,
                OK, Unpooled.wrappedBuffer(result.getBytes("UTF-8")));
        response.headers().set(CONTENT_TYPE, "application/json");
        response.headers().setInt(CONTENT_LENGTH,
                response.content().readableBytes());
        response.headers().set(ACCESS_CONTROL_ALLOW_ORIGIN,"*");
        ctx.write(response);
        ctx.flush();
    }

}
