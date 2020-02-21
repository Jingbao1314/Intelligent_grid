package com.jingbao.base.MyException;

public class RequestParmException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private String msg;
    private int code = 7001;

    public RequestParmException( String msg) {
        super(msg);
        this.msg = msg;
    }
}
