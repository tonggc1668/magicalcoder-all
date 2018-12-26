package com.magicalcoder.youyaboot.core.service;

import com.magicalcoder.youyaboot.core.utils.MapUtil;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CommonServiceImpl<E,P> {

    private static final String ignoreSuffix = "Ignore_";
    private static final String notIgnoreSuffix = "NotIgnore_";

    private static final String magicalCoderPrimaryKeyName = "magicalCoderPrimaryKeyName";//注意这个是保留字段，不知道用户传的主键名称,所以单独一个名称

    public ICommonMapper<E,P> commonMapper;

    public E getModel(P id) {
        Map<String,Object> query = new HashMap<String,Object>();
        query.put(magicalCoderPrimaryKeyName, id);
        return commonMapper.getModelByPk(query);
    }
    public E getModelIgnore(P id,String ... ignoreFields) {
        Map<String,Object> query = new HashMap<String,Object>();
        query.put(magicalCoderPrimaryKeyName, id);
        addIgnoreFields(query, ignoreFields);
        return commonMapper.getModelByPk(query);
    }

    public E getModelNotIgnore(P id,String ... notIgnoreFields) {
        Map<String,Object> query = new HashMap<String,Object>();
        query.put(magicalCoderPrimaryKeyName, id);
        addNotIgnoreFields(query, notIgnoreFields);
        return commonMapper.getModelByPk(query);
    }

    public E selectOneModelWillThrowException(Map<String, Object> query){
        return commonMapper.getModel(query);
    }
    public E selectOneModelWillThrowExceptionIgnore(Map<String, Object> query,String ... ignoreFields){
        if(query == null){query = new HashMap<>();}
        addIgnoreFields(query,ignoreFields);
        return commonMapper.getModel(query);
    }
    public E selectOneModelWillThrowExceptionNotIgnore(Map<String, Object> query,String ... notIgnoreFields){
        if(query == null){query = new HashMap<>();}
        addNotIgnoreFields(query,notIgnoreFields);
        return commonMapper.getModel(query);
    }

    public E selectFirstModel(Map<String, Object> query){
        if(query == null){query = new HashMap<>();}
        query.put("limit",1);
        List<E> list = commonMapper.getModelList(query);
        if(list!=null && list.size()>0){
            return list.get(0);
        }
        return null;
    }

    public E selectFirstModelIgnore(Map<String, Object> query,String ... ignoreFields){
        if(query == null){query = new HashMap<>();}
        query.put("limit",1);
        addIgnoreFields(query,ignoreFields);
        List<E> list = commonMapper.getModelList(query);
        if(list!=null && list.size()>0){
            return list.get(0);
        }
        return null;
    }
    public E selectFirstModelNotIgnore(Map<String, Object> query,String ... notIgnoreFields){
        if(query == null){query = new HashMap<>();}
        query.put("limit",1);
        addNotIgnoreFields(query,notIgnoreFields);
        List<E> list = commonMapper.getModelList(query);
        if(list!=null && list.size()>0){
            return list.get(0);
        }
        return null;
    }
    public List<E> getModelList(Map<String, Object> query) {
        return commonMapper.getModelList(query);
    }

    public List<E> getModelListIgnore(Map<String, Object> query,String ... ignoreFields) {
        if(query == null){query = new HashMap<>();}
        addIgnoreFields(query,ignoreFields);
        return commonMapper.getModelList(query);
    }
    public List<E> getModelListNotIgnore(Map<String, Object> query,String ... notIgnoreFields) {
        if(query == null){query = new HashMap<>();}
        addNotIgnoreFields(query,notIgnoreFields);
        return commonMapper.getModelList(query);
    }
    public List<E> getModelInList(Set<P> idList) {
        if(idList==null || idList.isEmpty()){
            return null;
        }
        return commonMapper.getModelInList(MapUtil.buildMap("idList",idList));
    }
    public List<E> getModelInListIgnore(Set<P> idList,String ... ignoreFields) {
        if(idList==null || idList.isEmpty()){
            return null;
        }
        Map<String,Object> query = new HashMap<>();
        addIgnoreFields(query,ignoreFields);
        query.put("idList",idList);
        return commonMapper.getModelInList(query);
    }
    public List<E> getModelInListNotIgnore(Set<P> idList,String ... notIgnoreFields) {
        if(idList==null || idList.isEmpty()){
            return null;
        }
        Map<String,Object> query = new HashMap<>();
        addNotIgnoreFields(query,notIgnoreFields);
        query.put("idList",idList);
        return commonMapper.getModelInList(query);
    }

    public int getModelListCount(Map<String, Object> query) {
        Integer count = commonMapper.getModelListCount(query);
        if(count == null){
            count = 0;
        }
        return count;
    }

    public int insertModel(E model) {
        return commonMapper.insertModel(model);
    }
    public int insertModelWithoutNull(E model) {
        return commonMapper.insertModelWithoutNull(model);
    }

    public int batchInsertModel(List<E> list) {
        return commonMapper.batchInsertModel(list);
    }
    public int batchInsertModelWithoutNull(List<E> list) {
        return commonMapper.batchInsertModelWithoutNull(list);
    }

    public int replaceModel(E model) {
        return commonMapper.replaceModel(model);
    }
    public int replaceModelWithoutNull(E model) {
        return commonMapper.replaceModelWithoutNull(model);
    }
    public int batchReplaceModel(List<E> list) {
        return commonMapper.batchReplaceModel(list);
    }
    public int batchReplaceModelWithoutNull(List<E> list) {
        return commonMapper.batchReplaceModelWithoutNull(list);
    }

    public int updateModel(E model) {
        return commonMapper.updateModel(model);
    }
    public int updateModelWithoutNull(E model) {
        return commonMapper.updateModelWithoutNull(model);
    }
    public int batchUpdateModel(List<E> list) {
        return commonMapper.batchUpdateModel(list);
    }
    public int batchUpdateModelWithoutNull(List<E> list) {
        return commonMapper.batchUpdateModelWithoutNull(list);
    }

    public int truncateModel() {
        return commonMapper.truncateModel();
    }

    public int deleteModel(P id) {
        Map<String,Object> query = new HashMap<String,Object>();
        query.put(magicalCoderPrimaryKeyName, id);
        return commonMapper.deleteModelByPk(query);
    }
    public int deleteModel(Map<String,Object> query){
        return commonMapper.deleteModelList(query);
    }
    public int deleteModel(Set<P> idList) {
        return commonMapper.deleteModelByIds(idList);
    }

    private void addIgnoreFields(Map<String, Object> query, String[] ignoreFields) {
        if(ignoreFields!=null && ignoreFields.length>0){
            query.put(ignoreSuffix,true);
            for(String field:ignoreFields){
                query.put(new StringBuilder(field).append(ignoreSuffix).toString(),true);
            }
        }
    }
    private void addNotIgnoreFields(Map<String, Object> query, String[] notIgnoreFields) {
        if(notIgnoreFields!=null && notIgnoreFields.length>0){
            query.put(notIgnoreSuffix,true);
            for(String field:notIgnoreFields){
                query.put(new StringBuilder(field).append(notIgnoreSuffix).toString(),true);
            }
        }
    }

    @Deprecated
    public int batchDeleteModel(Set<P> idList){
        return deleteModel(idList);
    }
    @Deprecated
    public int deleteModelList(Map<String, Object> query){
        return deleteModel(query);
    }

}
