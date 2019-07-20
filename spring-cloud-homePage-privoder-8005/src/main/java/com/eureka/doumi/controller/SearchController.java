package com.eureka.doumi.controller;

import com.alibaba.fastjson.JSONObject;
import com.eureka.doumi.fegin.SearchFeignClient;
import com.eureka.doumi.utils.ElasticsearchUtil;
import com.eureka.doumi.utils.EsPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "页面数据获取前端控制器")
public class SearchController {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


    @Resource
    private SearchFeignClient searchFeignClient;

    @GetMapping("/importPostjoblist/{index}/{type}")
    Map<String,Boolean> importPostjoblist(@PathVariable String index,@PathVariable String type){
        Map<String,Boolean> map=new HashMap<String, Boolean>();
        boolean result1= searchFeignClient.createMapping(index,type);
        if (result1){
            boolean result= searchFeignClient.importPostjoblist(index,type);
            if (result){
                map.put("result",result);
                return map;
            }
        }
        map.put("result",false);
        return map;
    }

    /**
     * 查询所有
     * @throws Exception
     */
    @GetMapping("/all")
    @ApiOperation(value = "获取es中的数据")
    public List<Map<String, Object>> searchAll() throws Exception {
        //这一步是最关键的
        Client client = elasticsearchTemplate.getClient();
        // @Document(indexName = "product", type = "book")
        SearchRequestBuilder srb = client.prepareSearch("test2022").setTypes("itemss");
        SearchResponse sr = srb.setQuery(QueryBuilders.matchAllQuery()).execute().actionGet(); // 查询所有
        SearchHits hits = sr.getHits();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (SearchHit hit : hits) {
            Map<String, Object> source = hit.getSourceAsMap();
            list.add(source);
            System.out.println(hit.getSourceAsString());
        }
        return list;
    }
    /**
     * 查询分页
     *
     * @param startPage 第几条记录开始
     *                  从0开始
     *                  第1页 ：http://127.0.0.1:8080/es/queryPage?startPage=0&pageSize=2
     *                  第2页 ：http://127.0.0.1:8080/es/queryPage?startPage=2&pageSize=2
     * @param pageSize  每页大小
     * @return
     */
    @GetMapping("/queryPage/{startPage}/{pageSize}/{keywords}")
    public String queryPage(@PathVariable String startPage,@PathVariable String pageSize,@PathVariable String keywords) {
        if (StringUtils.isNotBlank(startPage) && StringUtils.isNotBlank(pageSize)) {
//            QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();//查询全部
            QueryBuilder queryBuilder = QueryBuilders.fuzzyQuery("positionName",keywords);
            EsPage list = ElasticsearchUtil.searchDataPage("test2022", "itemss", Integer.parseInt(startPage), Integer.parseInt(pageSize), queryBuilder, null, null, null);
            System.out.println(list);
            return JSONObject.toJSONString(list);
        } else {
            return "startPage或者pageSize缺失";
        }
    }
}
