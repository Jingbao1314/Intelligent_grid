package com.jingbao.base.MyException;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UrlRepeatException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private String msg;
    private int code = 7000;

    public UrlRepeatException( String msg) {
        super(msg);
        this.msg = msg;
    }

    public UrlRepeatException( ) {}

}
