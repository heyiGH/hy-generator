package com.heyi.hygenerator.strategy;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class STString implements BaseStrategy {
    @Override
    public Object generateData(Class clazz, Object value, int length) {

        Random random = new Random();

        StringBuffer stringBuffer = new StringBuffer();

        for (int i = 0; i < length; i++){
            stringBuffer.append(random.nextInt(10));
        }

        String tmp = value.toString();

        tmp = String.format(tmp,stringBuffer.toString());

        return tmp;
    }
}
