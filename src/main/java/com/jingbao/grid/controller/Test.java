package com.jingbao.grid.controller;

import com.jingbao.core.serverAnnotation.RequestMapping;
import com.jingbao.core.serverAnnotation.Service;
import com.jingbao.grid.entity.Goods;
import com.jingbao.grid.entity.Return;

/**
 * @author jijngbao
 * @date 19-7-20
 */
@Service
public class Test {
    @RequestMapping(value = "/test")
    public void test(){
        System.out.println("test");
    }

    @RequestMapping(value = "/dataTest")
    public Object test(Goods goods){
        System.out.println("data test");
        return goods;
    }


    @RequestMapping(value = "/say")
    public Object say(Goods goods){
        System.out.println("data test");
        return new Return("HELLO WORLD");
    }
}
