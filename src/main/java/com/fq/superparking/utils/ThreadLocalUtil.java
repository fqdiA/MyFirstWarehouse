package com.fq.superparking.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 线程本地变量工具类
 *
 * @author fang
 * @date 2023/10/18
 */
public class ThreadLocalUtil {
    private static ThreadLocal<Map<String, Object>> TL = ThreadLocal.withInitial(HashMap::new);

    public static void set(String key, Object value) {
        Map<String, Object> map = TL.get();
        map.put(key, value);
    }

    public static Object get(String key) {
        Map<String, Object> map = TL.get();
        return map.get(key);
    }

    public static void remove(String key) {
        Map<String, Object> map = TL.get();
        map.remove(key);
    }

    public static void clear() {
        TL.remove();
    }
}
