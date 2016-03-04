package com.jjm.triphelper.repository;

import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Component
public class ProxyRepository {

    public <T> T proxy(Class<T> pClazz) {
        String interfaceName = pClazz.getClass().getName();
        Class clazz = null;
        try {
            clazz = Class.forName(interfaceName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new TrivialInvocationHandler());
    }

    static class TrivialInvocationHandler implements InvocationHandler {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) {
            System.out.println("Method called: " + method);
            if (method.getReturnType() == Integer.TYPE) {
                return 42;
            } else {
                return "I'm a string";
            }
        }
    }

}
