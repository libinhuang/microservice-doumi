package com.xxl.job.jobhandler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.stereotype.Component;

@JobHandler(value = "myJobHandler")
@Component
public class MyJobHandler extends IJobHandler {

    @Override
    public ReturnT<String> execute(String s) throws Exception {
        XxlJobLogger.log("start a job......");
        System.out.print("定时任务开始......");
        return SUCCESS;
    }
}
