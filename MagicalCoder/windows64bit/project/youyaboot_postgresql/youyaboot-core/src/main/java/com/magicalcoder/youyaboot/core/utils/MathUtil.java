package com.magicalcoder.youyaboot.core.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * author: www.magicalcoder.com
 * date:2018/3/26
 * function:
 */
public class MathUtil {
    /**
     *
     * 笛卡尔积
     * @param cutList
     * @param result
     * @param layer 首次传 0
     * @param curList
     * @param <T>
     */
    public static <T> void dicar(List<List<T>> cutList, List<List<T>> result, int layer, List<T> curList) {
        if (layer < cutList.size() - 1) {
            if (cutList.get(layer).size() == 0) {
                dicar(cutList, result, layer + 1, curList);
            } else {
                for (int i = 0; i < cutList.get(layer).size(); i++) {
                    List<T> list = new ArrayList<T>(curList);
                    list.add(cutList.get(layer).get(i));
                    dicar(cutList, result, layer + 1, list);
                }
            }
        } else if (layer == cutList.size() - 1) {
            if (cutList.get(layer).size() == 0) {
                result.add(curList);
            } else {
                for (int i = 0; i < cutList.get(layer).size(); i++) {
                    List<T> list = new ArrayList<T>(curList);
                    list.add(cutList.get(layer).get(i));
                    result.add(list);
                }
            }
        }
    }
}
