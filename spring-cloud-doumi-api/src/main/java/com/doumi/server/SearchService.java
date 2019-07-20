package com.doumi.server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface SearchService {

    @GetMapping("/createMapping/{index}/{type}")
    boolean createMapping(@PathVariable String index,@PathVariable String type);

    @GetMapping("/importPostjoblist/{index}/{type}")
    boolean importPostjoblist(@PathVariable String index,@PathVariable String type);

}
