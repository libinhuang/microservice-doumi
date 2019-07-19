package com.eureka.doumi.fegin;

import com.doumi.server.SearchService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "search-provider")
public interface SearchFeignClient extends SearchService {

}
