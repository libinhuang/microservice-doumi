package com.doumi.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
//@TableName(value = "postjoblist")
public class PostJobList {
//    `PostJobListId` int(32) NOT NULL AUTO_INCREMENT COMMENT '发布职位id',
//    @TableId(value = "PostJobListId",type = IdType.AUTO)
    private String PostJobListId;
//            `ShowCity` varchar(50) NOT NULL COMMENT '展示城市',
    private String ShowCity;
//            `tybeOfJob` varchar(50) NOT NULL COMMENT '职位类型',
    private String tybeOfJob;
//            `NumberRecruits` int(32) NOT NULL COMMENT '招聘人数',
    private Integer NumberRecruits;
//            `positionName` varchar(50) NOT NULL COMMENT '职位名称',
    private String positionName;
//            `Salary` int(32) NOT NULL COMMENT '薪资',
    private Integer Salary;
//            `SettlementTime` date NOT NULL COMMENT '结算时间',
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date SettlementTime;
//            `BillingType` varchar(50) NOT NULL COMMENT '结算类型',
    private String BillingType;
//            `SalaryRemarks` varchar(50) NOT NULL COMMENT '薪资备注',
    private String SalaryRemarks;
//            `StartDate` date NOT NULL COMMENT '开始日期',
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date StartDate;
//            `EndDate` date NOT NULL COMMENT '结束日期',
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date EndDate;
//            `RegistrationDeadline` date NOT NULL COMMENT '报名截止日期',
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date RegistrationDeadline;
//            `DescriptionOfJob` varchar(50) NOT NULL COMMENT '职位描述',
    private String DescriptionOfJob;
//            `WorkingHours` date NOT NULL COMMENT '上班时间',
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date WorkingHours;
//            `workPlace` varchar(50) NOT NULL COMMENT '上班地点',
    private String workPlace;
//            `personnelClaim` varchar(50) NOT NULL COMMENT '人员要求',
    private String personnelClaim;
//            `WhetherInterview` varchar(20) NOT NULL COMMENT '是否面试',
    private String WhetherInterview;
//            `Contact` varchar(20) NOT NULL COMMENT '联系人',
    private String Contact;
//            `ContactNumber` int(32) NOT NULL COMMENT '联系电话',
    private String ContactNumber;
//            `AdvisoryManner` varchar(20) NOT NULL COMMENT '咨询方式',
    private String AdvisoryManner;
//            `RecruitmentStatus` varchar(50) NOT NULL COMMENT '招聘状态',
    private String RecruitmentStatus;
//            `ReleaseTime` date NOT NULL COMMENT '发布时间',
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date ReleaseTime;
//            `EnterpriseId` varchar(50) NOT NULL COMMENT '企业id',
    private String EnterpriseId;
}
