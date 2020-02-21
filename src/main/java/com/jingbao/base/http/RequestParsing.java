package com.jingbao.base.http;

import com.google.gson.Gson;
import com.jingbao.core.load.ServiceEntity;
import lombok.Data;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;


@Data
public abstract class RequestParsing {
    private String data;
    private ServiceEntity serviceEntity;
    private HashMap parmsMap;
    public RequestParsing(String data, ServiceEntity serviceEntity) {
        this.data = data;
        this.serviceEntity = serviceEntity;
    }

    public RequestParsing(String data, ServiceEntity serviceEntity, HashMap parms) {
        this.data = data;
        this.serviceEntity = serviceEntity;
        this.parmsMap = parms;
    }

    public abstract Object[] parsing(String data) throws ClassNotFoundException, IllegalAccessException, InstantiationException;



    public String invoke() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException{
        String result="{}";
        Method method=serviceEntity.getMethod();
        Class clazz=serviceEntity.getClazz();
        Object obj=clazz.getConstructor().newInstance();

        Type type=method.getGenericReturnType();
        Gson gson=new Gson();
        if (method.getParameterTypes().length == 0){
            if (!type.toString().equals("void")){
                result=  gson.toJson(method.invoke(obj));
            }else {
                method.invoke(obj);
            }
        }else {
            if (!type.toString().equals("void")){
                result=  gson.toJson(method.invoke(obj,this.parsing(data)));
            }else {
                method.invoke(obj,this.parsing(data));
            }

        }
        return result;
    }


    boolean isPrimitive(Object obj) {
        try {
            if (obj instanceof String){
                return true;
            }
            return ((Class<?>)obj.getClass().getField("TYPE").get(null)).isPrimitive();
        } catch (Exception e) {
            return false;
        }
    }
}
