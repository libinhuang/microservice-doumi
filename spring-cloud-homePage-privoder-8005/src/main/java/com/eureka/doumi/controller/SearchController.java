package com.eureka.doumi.controller;

import com.eureka.doumi.fegin.SearchFeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SearchController {

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
}
