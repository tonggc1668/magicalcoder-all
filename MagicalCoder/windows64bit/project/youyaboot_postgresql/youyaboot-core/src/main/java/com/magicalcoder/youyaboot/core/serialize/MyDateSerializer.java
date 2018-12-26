package com.magicalcoder.youyaboot.core.serialize;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.magicalcoder.youyaboot.core.utils.DateFormatUtil;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;

public class MyDateSerializer implements ObjectSerializer {
    public void write(JSONSerializer serializer, Object object,
                      Object fieldName, Type fieldType) throws IOException {
        SerializeWriter out = serializer.getWriter();
        if (object == null) {
            serializer.getWriter().writeNull();
            return;
        }
        String utc = "Date.UTC(%d,%d,%d)";
        if(object instanceof Date){
            Date value = (Date)object;
            int year = DateFormatUtil.getIntYear(value);
            int month = DateFormatUtil.getIntMonth(value);
            int day = DateFormatUtil.getIntDay(value);
            utc = String.format(utc,year,month-1,day);
        }
        out.write(utc);
    }

    @Override
    public void write(JSONSerializer jsonSerializer, Object o, Object o1, Type type, int i) throws IOException {

    }
}
