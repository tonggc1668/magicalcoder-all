package com.magicalcoder.youyaboot.core.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @param <E> 实体类类型
 * @param <P> 主键类型
 */
public interface ICommonService<E,P> {
    /**
     * 根据主键获取实体
     * @param id 主键
     * @return
     */
    E getModel(P id);

    /**
     * 不返回被忽略的字段
     * @param id
     * @param ignoreFields 建议放常量中统一维护 如果不传则全部返回
     * @return
     */
    E getModelIgnore(P id, String... ignoreFields);

    /**
     * 只返回不被忽略的字段
     * @param id
     * @param notIgnoreFields 建议放常量中统一维护 如果不传则全部返回
     * @return
     */
    E getModelNotIgnore(P id, String... notIgnoreFields);

    /**
     * 调用mybatis selectOne 如果查询返回超过1条 就会发生异常 请自行处理
     * @param query 入参 调用MapUtil构造
     * @return
     */
    E selectOneModelWillThrowException(Map<String, Object> query);
    E selectOneModelWillThrowExceptionIgnore(Map<String, Object> query, String... ignoreFields);
    E selectOneModelWillThrowExceptionNotIgnore(Map<String, Object> query, String... notIgnoreFields);
    /**
     * 获取满足条件的第一条数据
     * @param query 入参 调用MapUtil构造
     * @return
     */
    E selectFirstModel(Map<String, Object> query);
    E selectFirstModelIgnore(Map<String, Object> query, String... ignoreFields);
    E selectFirstModelNotIgnore(Map<String, Object> query, String... notIgnoreFields);
    /**
     * 查询实体集合
     * @param query
     * @return
     */
    List<E> getModelList(Map<String, Object> query);
    List<E> getModelListIgnore(Map<String, Object> query, String... ignoreFields);
    List<E> getModelListNotIgnore(Map<String, Object> query, String... notIgnoreFields);

    List<E> getModelInList(Set<P> idList);
    List<E> getModelInListIgnore(Set<P> idList, String ... ignoreFields);
    List<E> getModelInListNotIgnore(Set<P> idList, String ... notIgnoreFields);

    /**
     * 查询实体集合的个数
     * @param query
     * @return
     */
    int getModelListCount(Map<String, Object> query);

    /**
     * 保存实体 保证Model 主键为空
     * @param model
     * @return null
     */
    int insertModel(E model);
    /**
     * 保存实体 保证Model 主键为空
     * @param model
     * @return null
     */
    int insertModelWithoutNull(E model);
    /**
     *  批量插入实体，保证list中的实体 主键为空
     * @param list
     * return 批量自动把实体主键设置
     */
    int batchInsertModel(List<E> list);
    /**
     *  批量插入实体，保证list中的实体 主键为空
     * @param list
     * return 批量自动把实体主键设置
     */
    int batchInsertModelWithoutNull(List<E> list);
    /**
     * 智能保存实体
     * @param model
     * @return null
     */
    int replaceModel(E model);
    /**
     * 智能保存实体
     * @param model
     * @return null
     */
    int replaceModelWithoutNull(E model);
    /**
     *  智能批量保存实体，
     * @param list
     * @retrun 批量自动把实体主键设置
     */
    int batchReplaceModel(List<E> list);
    /**
     *  智能批量保存实体
     * @param list
     * @retrun 批量自动把实体主键设置
     */
    int batchReplaceModelWithoutNull(List<E> list);
    /**
     * 更新实体 null值会字段设置数据库默认字段
     * 保证实体中的主键不为空
     * @param model
     */
    int updateModel(E model);
    /**
     * 更新实体 null字段忽略更新
     * 保证实体中的主键不为空
     * @param model
     */
    int updateModelWithoutNull(E model);
    /**
     * 批量更新实体 保证list中的实体 主键不为空
     * @param list
     */
    int batchUpdateModel(List<E> list);
    /**
     * 批量更新实体 保证list中的实体 主键不为空
     * @param list
     */
    int batchUpdateModelWithoutNull(List<E> list);
    /**
     * 根据主键删除实体
     * @param id
     */
    int deleteModel(P id);
    /**
     * 删除实体集合 使用不当 就会造成整个表清空，务必保证query的字段对应的值存在
     * @param query 如果字段值不为空 将作为查询条件
     */
    int deleteModel(Map<String, Object> query);
    /**
     * 根据主键list 批量删除实体
     * @param idList
     */
    int deleteModel(Set<P> idList);
    /**
     * 清空实体表
     */
    int truncateModel();


    //已重构名称请不要再使用下面的方法
    @Deprecated
    int batchDeleteModel(Set<P> idList);
    @Deprecated
    int deleteModelList(Map<String, Object> query);

}
