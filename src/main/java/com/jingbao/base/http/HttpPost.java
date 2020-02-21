package com.jingbao.base.http;

import com.google.gson.Gson;
import com.jingbao.core.load.ServiceEntity;
import lombok.Data;

@Data
public class HttpPost extends RequestParsing{
    private ServiceEntity serviceEntity;


    public HttpPost(String data, ServiceEntity serviceEntity) {
        super(data, serviceEntity);
        this.serviceEntity = serviceEntity;
    }
    @Override
    public Object[] parsing(String data) throws ClassNotFoundException {
        Class[] parms=serviceEntity.getParms();
        if (parms==null||parms.length==0){
            return null;
        }
        return new Object[]{new Gson().fromJson(data,parms[0])};
    }

}
