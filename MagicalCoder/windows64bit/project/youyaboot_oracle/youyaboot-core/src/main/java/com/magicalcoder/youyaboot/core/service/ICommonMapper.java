package com.magicalcoder.youyaboot.core.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ICommonMapper<E,P> {
    E getModel(Map<String, Object> query);
    E getModelByPk(Map<String, Object> query);
    List<E> getModelList(Map<String, Object> query);
    List<E> getModelInList(Map<String, Object> query);
    Integer getModelListCount(Map<String, Object> query);

    int insertModel(E model);
    int insertModelWithoutNull(E model);
    int batchInsertModel(List<E> list);
    int batchInsertModelWithoutNull(List<E> list);

    int replaceModel(E model);
    int replaceModelWithoutNull(E model);
    int batchReplaceModel(List<E> list);
    int batchReplaceModelWithoutNull(List<E> list);

    int updateModel(E model);
    int updateModelWithoutNull(E model);
    int batchUpdateModel(List<E> list);
    int batchUpdateModelWithoutNull(List<E> list);

    int truncateModel();

    int deleteModelByPk(Map<String, Object> query);
    int deleteModelList(Map<String, Object> query);
    int deleteModelByIds(Set<P> idList);
}
