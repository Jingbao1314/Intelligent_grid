package com.jingbao.base.http.httpEntity;

import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class HttpParmsEntity {


    public HttpParmsEntity(String[] parmsName) {
        this.parmsName = parmsName;
    }

    private String[] parmsName;

    public int getIndex(String name){
        if (StringUtils.isEmpty(name)){
            return 0;
        }
        for (int index=0;index<parmsName.length;index++){
            if (name.equals(parmsName[index])){
                return index;
            }
        }
        return 0;
    }
}
