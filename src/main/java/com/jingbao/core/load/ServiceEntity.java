package com.jingbao.core.load;

import com.jingbao.base.http.httpEntity.HttpParmsEntity;
import lombok.Data;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;

import java.lang.reflect.Method;

/**
 * @author jijngbao
 * @date 19-7-20
 */
@Data
public class ServiceEntity {
    private Method method;
    private Class clazz;
    private Class[] parms;
    private HttpParmsEntity parmsName;

    private static final ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();
    public ServiceEntity(Method method, Class clazz, Class[] parms) {
        this.method = method;
        this.clazz = clazz;
        this.parms = parms;

        parmsName = new HttpParmsEntity(parameterNameDiscoverer.getParameterNames(method));
    }

    public ServiceEntity(Method method, Class clazz) {
        this.method = method;
        this.clazz = clazz;
        parmsName = new HttpParmsEntity(parameterNameDiscoverer.getParameterNames(method));

    }
}
