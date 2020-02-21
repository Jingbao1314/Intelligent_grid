package com.jingbao.grid.entity;

import lombok.Data;

@Data
public class Return {
    private String msg;

    public Return(String msg) {
        this.msg = msg;
    }
}
