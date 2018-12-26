package com.magicalcoder.youyaboot.admin;

import com.magicalcoder.youyaboot.core.utils.MapUtil;
import com.magicalcoder.youyaboot.model.Goods;
import com.magicalcoder.youyaboot.service.goods.service.GoodsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.*;

/**
 * author: www.magicalcoder.com
 * date:2018/3/28
 * function:
 * 商品模块测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsTest {

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Resource
    private GoodsService goodsService;

    private Byte step = 0;

    private Goods goods(Long id){
        Goods goods = new Goods();
        goods.setId(id);
        goods.setGoodsName("测试商品"+step++);
        goods.setCreateTime(new Timestamp(new Date().getTime()));
        return goods;
    }

    private List<Goods> goodsList(int size,Long ...ids){
        List<Goods> list = new ArrayList<>();
        for(int i=0;i<size;i++){
            list.add(goods(ids[i]));
        }
        return list;
    }

    @Test
    public void testInsertWithPk(){
        goodsService.insertModel(goods(1L));
        goodsService.insertModelWithoutNull(goods(2L));
        goodsService.replaceModel(goods(3L));
        goodsService.replaceModelWithoutNull(goods(4L));
        goodsService.batchInsertModel(goodsList(2,5L,6L));
        goodsService.batchInsertModelWithoutNull(goodsList(2,7L,8L));
        goodsService.batchReplaceModel(goodsList(2,9L,10L));
        goodsService.batchReplaceModelWithoutNull(goodsList(2,11L,12L));
    }
    //初始化无主键的数据
    @Test
    public void testInsert(){
        Goods goods = goods(null);//看看自增
        goodsService.insertModel(goods);
        goodsService.insertModel(goods(null));
        goodsService.insertModelWithoutNull(goods(null));
        goodsService.replaceModel(goods(null));
        goodsService.replaceModelWithoutNull(goods(null));
        goodsService.batchInsertModel(goodsList(2,null,null));
        goodsService.batchInsertModelWithoutNull(goodsList(2,null,null));
        goodsService.batchReplaceModel(goodsList(2,null,null));
        goodsService.batchReplaceModelWithoutNull(goodsList(2,null,null));
    }


    @Test
    public void testUpdate(){
        goodsService.updateModel(goods(1L));
        goodsService.updateModelWithoutNull(goods(1L));
        goodsService.batchUpdateModel(goodsList(2,5L,6L));
        goodsService.batchUpdateModelWithoutNull(goodsList(2,7L,8L));
    }

    @Test
    public void testDelete(){
        goodsService.deleteModel(1L);
        goodsService.deleteModel(MapUtil.buildMap("id",2L,"goodsName","商品"));
        Set<Long> ids = new HashSet<>();
        ids.add(2L);
        ids.add(3L);
        goodsService.deleteModel(ids);
    }
    @Test
    public void testSelect(){
        Set<Long> ids = new HashSet<>();
        ids.add(2L);
        ids.add(3L);
        String field = "id";
        Map<String,Object> query = MapUtil.buildMap(field,1L);
        goodsService.getModel(1L);
        goodsService.getModelList(MapUtil.buildMap(field,1L));
        goodsService.getModelListCount(MapUtil.buildMap(field,1L));
        goodsService.getModelListIgnore(MapUtil.buildMap(field,1L),field);
        goodsService.getModelListNotIgnore(MapUtil.buildMap(field,1L),field);
        goodsService.getModelInListIgnore(ids,field);
        goodsService.getModelInListNotIgnore(ids,field);
        goodsService.getModelInList(ids);
        goodsService.selectFirstModel(MapUtil.buildMap(field,1L));
        goodsService.selectFirstModelIgnore(MapUtil.buildMap(field,1L),field);
        goodsService.selectFirstModelNotIgnore(MapUtil.buildMap(field,1L),field);
        goodsService.selectOneModelWillThrowException(MapUtil.buildMap(field,1L));
        goodsService.selectOneModelWillThrowExceptionIgnore(MapUtil.buildMap(field,1L),field);
        goodsService.selectOneModelWillThrowExceptionNotIgnore(MapUtil.buildMap(field,1L),field);

    }

}
