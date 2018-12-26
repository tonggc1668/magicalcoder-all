package com.magicalcoder.youyaboot.core.utils;

import java.util.*;

public class CollectionUtil {

	private CollectionUtil() {
		super();
	}

	// 判断一个集合是否为空
	public static <T> boolean isEmpty(Collection<T> col) {
		if (col == null || col.isEmpty()) {
			return true;
		}

		return false;
	}

	// 判断一个集合是否不为空
	public static <T> boolean isNotEmpty(Collection<T> col) {
		return !isEmpty(col);
	}

	// 判断Map是否为空
	public static <K, V> boolean isEmpty(Map<K, V> map) {
		if (map == null || map.isEmpty()) {
			return true;
		}

		return false;
	}

	// 判断Map是否不为空为空
	public static <K, V> boolean isNotEmpty(Map<K, V> map) {
		return !isEmpty(map);
	}

	// 去除list中的重复数据
	public static <T> List<T> removeRepeat(List<T> list) {
		if (isEmpty(list)) {
			return list;
		}

		List<T> result = new ArrayList<T>();
		for (T e : list) {
			if (!result.contains(e)) {
				result.add(e);
			}
		}

		return result;
	}

	// 将集合转换为String数组
	public static <T> String[] toArray(List<T> list) {
		if (isEmpty(list)) {
			return null;
		}

		String[] result = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			result[i] = String.valueOf(list.get(i));
		}

		return result;
	}

    /**
     * 使用 Map按key进行排序
     *
     * @param map
     * @return
     */
    public static Map<String, String> sortMapByKey(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<String, String> sortMap = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String str1, String str2) {
                return str1.compareTo(str2);
            }
        });
        sortMap.putAll(map);
        return sortMap;
    }
}
