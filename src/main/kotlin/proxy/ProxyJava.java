package proxy;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ProxyJava {
    Properties props = new Properties();
    public ProxyJava(InputStream ips) throws IOException {
        props.load(ips);
    }

    public Object getBean(String name) {
        String className = props.getProperty(name);
        Object bean = null;
        Class clazz = null;
        try {
            clazz = Class.forName(className);
            bean = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(bean instanceof ProxyFactoryBean) {
            Object proxy = ((ProxyFactoryBean)bean).getProxy();
            return proxy;
        }
        return bean;
    }
}