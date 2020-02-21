package com.jingbao;

import com.jingbao.core.runType.MoreThreadType;
import com.jingbao.core.runType.NettyRun;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException, InterruptedException {
        NettyRun server=new MoreThreadType();
        server.run(7721);
    }
}
