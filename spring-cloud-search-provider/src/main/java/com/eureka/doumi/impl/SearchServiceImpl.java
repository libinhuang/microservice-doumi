package com.eureka.doumi.impl;

import com.doumi.pojo.PostJobList;
import com.doumi.server.SearchService;
import com.eureka.doumi.mapper.PostJobMapper;
import com.eureka.doumi.utils.ResponseApi;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
public class SearchServiceImpl<T> extends ResponseApi<T> implements SearchService {

    @Resource
    private PostJobMapper postJobMapper;

    @Resource
    private TransportClient transportClient;

    /**
     * 导入数据
     * @param index
     * @param type
     * @return
     */
    @Override
    public boolean importPostjoblist(String index, String type) {
        List<PostJobList> postJobLists = postJobMapper.getAllJobList();
        BulkRequestBuilder bulkRequestBuilder = transportClient.prepareBulk();
        try {
            for (PostJobList jobList : postJobLists) {
                bulkRequestBuilder.add(transportClient.prepareIndex(index, type, jobList.getPostJobListId())
                        .setSource(XContentFactory.jsonBuilder().startObject()
                                .field("PostJobListId", jobList.getPostJobListId())
                                .field("ShowCity", jobList.getShowCity())
                                .field("tybeOfJob", jobList.getTybeOfJob())
                                .field("NumberRecruits", jobList.getNumberRecruits())
                                .field("positionName", jobList.getPositionName())
                                .field("Salary", jobList.getSalary())
                                .field("SettlementTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(jobList.getSettlementTime()))
                                .field("BillingType", jobList.getBillingType())
                                .field("SalaryRemarks", jobList.getSalaryRemarks())
                                .field("StartDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(jobList.getStartDate()))
                                .field("EndDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(jobList.getEndDate()))
                                .field("RegistrationDeadline", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(jobList.getRegistrationDeadline()))
                                .field("DescriptionOfJob", jobList.getDescriptionOfJob())
                                .field("WorkingHours", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(jobList.getWorkingHours()))
                                .field("workPlace", jobList.getWorkPlace())
                                .field("personnelClaim", jobList.getPersonnelClaim())
                                .field("WhetherInterview", jobList.getWhetherInterview())
                                .field("Contact", jobList.getContact())
                                .field("ContactNumber", jobList.getContactNumber())
                                .field("AdvisoryManner", jobList.getAdvisoryManner())
                                .field("RecruitmentStatus", jobList.getRecruitmentStatus())
                                .field("ReleaseTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(jobList.getReleaseTime()))
                                .field("EnterpriseId", jobList.getEnterpriseId())
                                .endObject()
                        ));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        BulkResponse bulkResponse= bulkRequestBuilder.get();
        if (bulkResponse.hasFailures()) {
            return false;
        }else{
            return true;
        }
    }

    /**
     * 创建映射
     * @param index
     * @param type
     * @return
     */
    @Override
    public boolean createMapping(String index, String type) {
        System.out.println("provider================>createMapping");
        PutMappingResponse putMappingResponse=null;
        transportClient.admin().indices().prepareCreate(index).execute().actionGet();
        XContentBuilder xContentBuilder=null;
        try {
            xContentBuilder= XContentFactory.jsonBuilder()
                    .startObject()
                    .startObject(type)
                    .startObject("properties")
                    .startObject("PostJobListId").field("type","text").endObject()
                    .startObject("ShowCity").field("type","text").endObject()
                    .startObject("tybeOfJob").field("type", "text").endObject()
                    .startObject("NumberRecruits").field("type", "text").endObject()
                    //分词搜索字段
                    .startObject("positionName").field("type", "text").field("store",true).field("analyzer","ik_smart").field("index",true).endObject()
                    .startObject("Salary").field("type", "text").endObject()
                    .startObject("SettlementTime").field("type","text").endObject()
                    .startObject("BillingType").field("type","text").endObject()
                    .startObject("SalaryRemarks").field("type", "text").endObject()
                    .startObject("StartDate").field("type", "text").endObject()
                    .startObject("EndDate").field("type", "text").endObject()
                    .startObject("RegistrationDeadline").field("type", "text").endObject()
                    .startObject("DescriptionOfJob").field("type","text").endObject()
                    .startObject("WorkingHours").field("type","text").endObject()
                    .startObject("workPlace").field("type", "text").endObject()
                    .startObject("personnelClaim").field("type", "text").endObject()
                    .startObject("WhetherInterview").field("type", "text").endObject()
                    .startObject("Contact").field("type", "text").endObject()
                    .startObject("ContactNumber").field("type","text").endObject()
                    .startObject("AdvisoryManner").field("type", "text").endObject()
                    .startObject("RecruitmentStatus").field("type", "text").endObject()
                    .startObject("ReleaseTime").field("type", "text").endObject()
                    .startObject("EnterpriseId").field("type", "text").endObject()
                    .endObject()
                    .endObject()
                    .endObject();
            PutMappingRequest putMappingRequest= Requests.putMappingRequest(index).type(type).source(xContentBuilder);
            putMappingResponse= transportClient.admin().indices().putMapping(putMappingRequest).actionGet();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return putMappingResponse.isAcknowledged();
    }

    //@Override
    /*public Response<PostJobList> getHighLightPostJobList(String index, String type, String keywords) {
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("title", keywords);
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("title")
                .preTags("<b style='color:red'>")
                .postTags("</b>")
                .fragmentSize(50);
        SearchResponse searchResponse = transportClient.prepareSearch(index)
                .setTypes(type)
                .setQuery(queryBuilder)
                .highlighter(highlightBuilder)
                .execute().actionGet();
        SearchHits searchHits = searchResponse.getHits();
        System.out.println("查询到总记录数1：===========>" + searchResponse.getHits().totalHits);
        List<PostJobList> items = null;
        try {
            if (searchHits.getTotalHits() > 0) {
                items = new ArrayList<PostJobList>();
                for (SearchHit searchHit : searchHits) {
                    PostJobList item = new PostJobList();
                    Map<String, HighlightField> map = searchHit.getHighlightFields();
                    item.setPostJobListId(Integer.parseInt(searchHit.getSource().get("PostJobListId").toString()));
                    item.setPositionName(searchHit.getSource().get("ShowCity").toString());
                    item.setAdvisoryManner(Integer.parseInt(searchHit.getSource().get("price").toString()));
                    item.setBillingType(Integer.parseInt(searchHit.getSource().get("num").toString()));
                    item.setContact(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(searchHit.getSource().get("created").toString()));
                    String highlight = "";
                    for (String key : map.keySet()) {
                        HighlightField highlightField = map.get(key);
                        Text[] texts = highlightField.getFragments();
                        for (Text text : texts) {
                            highlight += text.toString();
                        }
                    }
                    item.setPositionName(highlight);
                    items.add(item);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }*/
}
