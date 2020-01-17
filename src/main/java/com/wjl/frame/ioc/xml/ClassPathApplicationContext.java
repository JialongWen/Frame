package com.wjl.frame.ioc.xml;

import com.wjl.frame.exception.ClassPathApplicationContextException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.util.List;

public class ClassPathApplicationContext {

    private String xmlPath;
    public ClassPathApplicationContext(String xmlPath){
        this.xmlPath = xmlPath;
    }

    //获取一个bean
    public Object getBean(String beanId)
            throws ClassPathApplicationContextException{
        if (StringUtils.isEmpty(beanId)){
            throw new  ClassPathApplicationContextException("beanId Cannot be empty !");
        }
        //解析xml获取到节点对象
        List<Element> elements = null;
        try {
            elements = readXML();
        } catch (DocumentException e) {
            throw new  ClassPathApplicationContextException(e);
        }
        //将节点对象中的id属性值与beanId比较
        String classPath = isBeanId(elements,beanId);
        Object obj = null;
        try {
            obj = newInstance(classPath);
        } catch (ClassNotFoundException e) {
            throw new  ClassPathApplicationContextException(e);
        } catch (IllegalAccessException e) {
            throw new  ClassPathApplicationContextException(e);
        } catch (InstantiationException e) {
            throw new  ClassPathApplicationContextException(e);
        }
        return obj;
    }

    //3.创建实例
    private Object newInstance(String classPath)
            throws ClassNotFoundException, IllegalAccessException,
            InstantiationException, ClassPathApplicationContextException {
        if (StringUtils.isEmpty(classPath)){
            throw new ClassPathApplicationContextException("class cannot be empty !");
        }
        Class<?> clazz = Class.forName(classPath);
        Object obj = clazz.newInstance();
        return obj;
    }

    //2.获取beanId比较获取到的xml中的信息
    private String isBeanId(List<Element> elements,String beanId) throws ClassPathApplicationContextException {
        for (Element element : elements) {
            String id = element.attributeValue("id");
            if (StringUtils.isEmpty(id)){
                //实际上此部分如果id为空的话，应将类名获取首字母小写作为id,但是本次只编写ioc容器部分
                throw new ClassPathApplicationContextException("No bean exists!");
            }
            if (id.equals(beanId)){
               return getClassPath(element);
            }
        }
        throw new ClassPathApplicationContextException("Not found bean "+beanId);
    }

    private String getClassPath(Element element) {
        String classPaht = element.attributeValue("class");
        return classPaht;
    }

    //1.读取xml配置信息
    private List<Element> readXML() throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(getResourceAssTream(xmlPath));
        Element rootElement = document.getRootElement();
        List<Element> elements = rootElement.elements();
        return elements;
    }

    private InputStream getResourceAssTream(String xmlPath) {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(xmlPath);
        return in;
    }
}
