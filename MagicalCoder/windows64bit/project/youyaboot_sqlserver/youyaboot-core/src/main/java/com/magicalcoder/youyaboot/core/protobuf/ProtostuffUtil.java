package com.magicalcoder.youyaboot.core.protobuf;

import com.alibaba.fastjson.JSON;
import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by magicalcoder.com.com on 2017/5/22.
 * 谷歌protobuf序列化反序列号工具类
 * 好处是 不用再单独创建.proto文件了
 */
public class ProtostuffUtil {
    private static Map<Class<?>, Schema<?>> cachedSchema = new ConcurrentHashMap<Class<?>, Schema<?>>();
    // objenesis是一个小型Java类库用来实例化一个特定class的对象 无需再提供默认构造器
    private static Objenesis objenesis = new ObjenesisStd(true);

    private ProtostuffUtil() {
    }

    @SuppressWarnings("unchecked")
    private static <T> Schema<T> getSchema(Class<T> cls) {
        Schema<T> schema = (Schema<T>) cachedSchema.get(cls);
        if (schema == null) {
            schema = RuntimeSchema.createFrom(cls);
            if (schema != null) {
                cachedSchema.put(cls, schema);
            }
        }
        return schema;
    }

    /**
     * 序列化（对象 -> 字节数组）
     */
    @SuppressWarnings("unchecked")
    public static <T> byte[] serialize(T obj) {
        if(obj == null){
            return null;
        }
//        Class<T> cls = (Class<T>) obj.getClass();
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            Schema<ProtoDto> schema = getSchema(ProtoDto.class);
            ProtoDto protoDto = new ProtoDto(obj);
            return ProtostuffIOUtil.toByteArray(protoDto, schema, buffer);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        } finally {
            buffer.clear();
        }
    }

    /**
     * 反序列化（字节数组 -> 对象）
     */
    public static <T> T deserialize(byte[] data) {
        try {
            if(data==null){
                return null;
            }
            ProtoDto message = objenesis.newInstance(ProtoDto.class);
            Schema<ProtoDto> schema = getSchema(ProtoDto.class);
            ProtostuffIOUtil.mergeFrom(data, message, schema);
            return (T)message.getObj();
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("12发");
        List<String> obj = deserialize(serialize(list));
        System.out.println(JSON.toJSONString(obj));
    }
}
