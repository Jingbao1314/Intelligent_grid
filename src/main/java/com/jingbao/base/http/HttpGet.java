package com.jingbao.base.http;

import com.google.gson.Gson;
import com.jingbao.base.http.httpEntity.HttpParmsEntity;
import com.jingbao.core.load.ServiceEntity;
import lombok.Data;

import java.util.HashMap;


@Data
public class HttpGet extends RequestParsing{

    public HttpGet(String data, ServiceEntity serviceEntity,HashMap parms) {
        super(data, serviceEntity,parms);
        this.serviceEntity = serviceEntity;
    }

    private ServiceEntity serviceEntity;

    @Override
    public Object[] parsing(String data) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class[] parms=serviceEntity.getParms();
        if (parms==null||parms.length==0){
            return null;
        }
        Object[] invokeParms=new Object[parms.length];
        HttpParmsEntity parmsName=serviceEntity.getParmsName();
        if (parmsName.getParmsName().length!=this.getParmsMap().size()){
            return null;
        }
        int index=0;
        for (String fieldName:parmsName.getParmsName()){
            Class parmClass=parms[index];

            Object fieldValue=this.getParmsMap().get(fieldName);
            //参数的类型是否是基本数据类型
            if (isPrimitive(parmClass.newInstance())){
                invokeParms[index]=fieldValue;
            }else {
                invokeParms[index]=new Gson().fromJson(fieldValue.toString(),parmClass);
            }
            index++;
        }
        return invokeParms;
    }
}
