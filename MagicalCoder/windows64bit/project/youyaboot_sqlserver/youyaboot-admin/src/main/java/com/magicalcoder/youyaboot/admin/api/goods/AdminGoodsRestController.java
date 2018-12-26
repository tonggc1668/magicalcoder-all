package com.magicalcoder.youyaboot.admin.api.goods;

import com.magicalcoder.youyaboot.core.service.CommonRestController;
import org.springframework.beans.factory.InitializingBean;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.math.*;
import java.util.List;
import java.util.Map;
import com.magicalcoder.youyaboot.core.common.constant.PageConstant;
import com.magicalcoder.youyaboot.core.common.exception.BusinessException;
import com.magicalcoder.youyaboot.core.serialize.ResponseMsg;
import com.magicalcoder.youyaboot.model.Goods;
import com.magicalcoder.youyaboot.service.goods.service.GoodsService;

import com.magicalcoder.youyaboot.core.utils.ListUtil;
import com.magicalcoder.youyaboot.core.utils.MapUtil;
import com.magicalcoder.youyaboot.core.utils.StringUtil;


/**
* 代码为自动生成 Created by www.magicalcoder.com
* 如果你改变了此类 read 请将此行删除
* 欢迎加入官方QQ群:323237052
*/

@RequestMapping("/admin/goods_rest/")
@RestController
public class AdminGoodsRestController extends CommonRestController<Goods,Long> implements InitializingBean
{

    @Resource
    private GoodsService goodsService;

    @RequestMapping(value = "search")
    public ResponseMsg search(
        @RequestParam(required = false) String uniqueField,
        @RequestParam(required = false) Long uniqueValue,
        @RequestParam(required = false,defaultValue = "20") Integer limit,
        @RequestParam(required = false) String keyword
    ){
        limit = Math.min(PageConstant.MAX_LIMIT,limit);
        List<Goods> list = null;
        Map<String,Object> query = new HashedMap();
        query.put("limit",limit);
        query.put("notSafeOrderBy","id desc");
        if(uniqueValue!=null){//说明是来初始化的
            query.put(uniqueField,uniqueValue);
            list = goodsService.getModelList(query);
        }else {//正常搜索
            if(ListUtil.isBlank(list)){
                query.put("goodsNameFirst",keyword);
                list = goodsService.getModelList(query);
                query.remove("goodsNameFirst");
            }
        }
        return new ResponseMsg(list);
    }
    //分页查询
    @RequestMapping(value={"page"}, method={RequestMethod.GET})
    public ResponseMsg page(
        @RequestParam(required = false,value ="idFirst")                            Long idFirst ,
        @RequestParam(required = false,value ="goodsNameFirst")                            String goodsNameFirst ,
        @RequestParam(required = false,value ="publishStatusFirst")                            String publishStatusFirst ,
        @RequestParam(required = false,value ="goodsStatusFirst")                            Byte goodsStatusFirst ,
        @RequestParam(required = false,value ="priceFirst")                            BigDecimal priceFirst ,
        @RequestParam(required = false,value ="storeCountFirst")                            Integer storeCountFirst ,
        @RequestParam(required = false,value ="shortBriefFirst")                            String shortBriefFirst ,
        @RequestParam(required = false,value ="goodsDescriptionFirst")                            String goodsDescriptionFirst ,
        @RequestParam(required = false,value ="createTimeFirst")                    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")Date createTimeFirst ,
        @RequestParam(required = false,value ="createTimeSecond")                    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")Date createTimeSecond ,
        @RequestParam(required = false,value ="updateTimeFirst")                    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")Date updateTimeFirst ,
        @RequestParam(required = false,value ="updateTimeSecond")                    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")Date updateTimeSecond ,
        @RequestParam(required = false,value ="imgSrcFirst")                            String imgSrcFirst ,
        @RequestParam(required = false,value ="goodsCategoryIdFirst")                            Long goodsCategoryIdFirst ,
        @RequestParam int page,@RequestParam int limit,@RequestParam(required = false) String safeOrderBy)
    {
        limit = Math.min(limit, PageConstant.MAX_LIMIT);
        int start = (page - 1) * limit;
        Map<String,Object> query = new HashedMap();
        query.put("idFirst",idFirst);
        query.put("goodsNameFirst",coverBlankToNull(goodsNameFirst));
        query.put("publishStatusFirst",coverBlankToNull(publishStatusFirst));
        query.put("goodsStatusFirst",goodsStatusFirst);
        query.put("priceFirst",priceFirst);
        query.put("storeCountFirst",storeCountFirst);
        query.put("shortBriefFirst",coverBlankToNull(shortBriefFirst));
        query.put("goodsDescriptionFirst",coverBlankToNull(goodsDescriptionFirst));
        query.put("createTimeFirst",createTimeFirst);
        query.put("createTimeSecond",createTimeSecond);
        query.put("updateTimeFirst",updateTimeFirst);
        query.put("updateTimeSecond",updateTimeSecond);
        query.put("imgSrcFirst",coverBlankToNull(imgSrcFirst));
        query.put("goodsCategoryIdFirst",goodsCategoryIdFirst);
        Integer count = goodsService.getModelListCount(query);
        query.put("start",start);
        query.put("limit",limit);
        if(StringUtil.isBlank(safeOrderBy)){
            query.put("notSafeOrderBy","id desc");
        }else{
            query.put("safeOrderBy",safeOrderBy);
        }
        return new ResponseMsg(count,goodsService.getModelList(query));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.commonService = goodsService;
        super.primaryKey = "id";//硬编码此实体的主键名称
    }
}
