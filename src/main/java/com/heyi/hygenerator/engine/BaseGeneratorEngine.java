package com.heyi.hygenerator.engine;

import com.heyi.hygenerator.annotation.Generator;
import com.heyi.hygenerator.common.SpringContextUtils;
import com.heyi.hygenerator.strategy.BaseStrategy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Component
public class BaseGeneratorEngine implements BaseEngine {

    @Override
    public Object parseData(Object obj)  {

        Class clazz = obj.getClass();

        Field[] fields = clazz.getFields();
        Map<String,Object> map = new HashMap<>();
        for (Field field :fields){
            Generator annotation  = field.getAnnotation(Generator.class);
            // todo 解析注解信息
            if (annotation != null){

                Class annotationClass = annotation.type();
                if (BaseStrategy.class.isAssignableFrom(annotationClass)){
                    fillData(obj, field, annotation.type(),annotation.value(),annotation.length());
                }else{
                    try {
                        throw new Exception(clazz.getName()+"."+field.getName()+"haven't be distinguish!");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return obj;
    }

    public Object fillData(Object obj, Field field, Class clazz, Object value, int length) {
        // todo 获取策略实例
        BaseStrategy strategy = (BaseStrategy) SpringContextUtils.getBeanFactory().getBean(clazz);
        field.setAccessible(false);

        try {
            field.set(obj,strategy.generateData(obj.getClass(),value,length));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return obj;
    }



    public void dfs(Map map, Annotation annotation) {

        Class clazz = annotation.annotationType();

        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annotation1 : annotations){
            if (annotation1 instanceof Target|| annotation1 instanceof Retention || annotation1 instanceof Documented || annotation1 instanceof Inherited)
                continue;
            dfs(map,annotation1);
        }

        Field[] fields = annotation.annotationType().getFields();
        for (Field field:fields){
            try{
                map.put(field.getName(),field.get(annotation));
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}
