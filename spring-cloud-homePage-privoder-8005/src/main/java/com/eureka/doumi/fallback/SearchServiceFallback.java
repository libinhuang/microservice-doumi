package com.eureka.doumi.fallback;

import com.eureka.doumi.fegin.SearchFeignClient;
import com.eureka.doumi.utils.ResponseApi;
import org.springframework.stereotype.Component;

@Component
public class SearchServiceFallback<T> extends ResponseApi<T> implements SearchFeignClient {

    @Override
    public boolean createMapping(String index, String type) {
        return false;
    }

    @Override
    public boolean importPostjoblist(String index, String type) {
        return false;
    }
}
