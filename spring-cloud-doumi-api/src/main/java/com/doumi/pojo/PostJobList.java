package com.doumi.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Document(indexName = "test2022",type = "itemss")
@ApiModel(value = "兼职信息实体")
//@TableName(value = "postjoblist")
public class PostJobList {
//    `PostJobListId` int(32) NOT NULL AUTO_INCREMENT COMMENT '发布职位id',
//    @TableId(value = "PostJobListId",type = IdType.AUTO)
    @Id
    @ApiModelProperty(value = "发布职位id")
    private String PostJobListId;
//            `ShowCity` varchar(50) NOT NULL COMMENT '展示城市',
    @ApiModelProperty(value = "展示城市")
    private String ShowCity;
//            `tybeOfJob` varchar(50) NOT NULL COMMENT '职位类型',
    @ApiModelProperty(value = "职位类型")
    private String tybeOfJob;
//            `NumberRecruits` int(32) NOT NULL COMMENT '招聘人数',
    @ApiModelProperty(value = "招聘人数")
    private Integer NumberRecruits;
//            `positionName` varchar(50) NOT NULL COMMENT '职位名称',
    @ApiModelProperty(value = "职位名称")
    private String positionName;
//            `Salary` int(32) NOT NULL COMMENT '薪资',
    @ApiModelProperty(value = "薪资")
    private Integer Salary;
//            `SettlementTime` date NOT NULL COMMENT '结算时间',
    @ApiModelProperty(value = "结算时间")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date SettlementTime;
//            `BillingType` varchar(50) NOT NULL COMMENT '结算类型',
    @ApiModelProperty(value = "结算类型")
    private String BillingType;
//            `SalaryRemarks` varchar(50) NOT NULL COMMENT '薪资备注',
    @ApiModelProperty(value = "薪资备注")
    private String SalaryRemarks;
//            `StartDate` date NOT NULL COMMENT '开始日期',
    @ApiModelProperty(value = "开始日期")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date StartDate;
//            `EndDate` date NOT NULL COMMENT '结束日期',
    @ApiModelProperty(value = "结束日期")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date EndDate;
//            `RegistrationDeadline` date NOT NULL COMMENT '报名截止日期',
    @ApiModelProperty(value = "报名截止日期")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date RegistrationDeadline;
//            `DescriptionOfJob` varchar(50) NOT NULL COMMENT '职位描述',
    @ApiModelProperty(value = "职位描述")
    private String DescriptionOfJob;
//            `WorkingHours` date NOT NULL COMMENT '上班时间',
    @ApiModelProperty(value = "上班时间")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date WorkingHours;
//            `workPlace` varchar(50) NOT NULL COMMENT '上班地点',
    @ApiModelProperty(value = "上班地点")
    private String workPlace;
//            `personnelClaim` varchar(50) NOT NULL COMMENT '人员要求',
    @ApiModelProperty(value = "人员要求")
    private String personnelClaim;
//            `WhetherInterview` varchar(20) NOT NULL COMMENT '是否面试',
    @ApiModelProperty(value = "是否面试")
    private String WhetherInterview;
//            `Contact` varchar(20) NOT NULL COMMENT '联系人',
    @ApiModelProperty(value = "联系人")
    private String Contact;
//            `ContactNumber` int(32) NOT NULL COMMENT '联系电话',
    @ApiModelProperty(value = "联系电话")
    private String ContactNumber;
//            `AdvisoryManner` varchar(20) NOT NULL COMMENT '咨询方式',
    @ApiModelProperty(value = "咨询方式")
    private String AdvisoryManner;
//            `RecruitmentStatus` varchar(50) NOT NULL COMMENT '招聘状态',
    @ApiModelProperty(value = "招聘状态")
    private String RecruitmentStatus;
//            `ReleaseTime` date NOT NULL COMMENT '发布时间',
    @ApiModelProperty(value = "发布时间")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date ReleaseTime;
//            `EnterpriseId` varchar(50) NOT NULL COMMENT '企业id',
    @ApiModelProperty(value = "企业id")
    private String EnterpriseId;
}
