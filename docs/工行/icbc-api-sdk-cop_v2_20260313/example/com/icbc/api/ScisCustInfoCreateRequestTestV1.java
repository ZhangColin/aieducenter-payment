package com.icbc.api;

import com.icbc.api.request.ScisCustInfoCreateRequestV1;
import com.icbc.api.response.ScisCustInfoCreateResponseV1;

import java.util.logging.Logger;

/**
 * @Description TODO
 * @author: kfzx-lij1
 * @date: 2023/1/29 14:15
 **/
public class ScisCustInfoCreateRequestTestV1 {

    protected static Logger log = Logger.getLogger(ScisCustInfoCreateRequestTestV1.class.getName());

    protected static String APP_ID = "90000000000000003382";

    protected static String APIGW_PUBLIC_KEY =
            "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY"
                    + "0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

    protected static String PRI_KEY =
            "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC3NdXULtLHXlmvn8nGuac8Us84tmdEd9UuXLAY7j4c8KAcNnPDRLDJkLD2p+gYVw+EF0WrVgdZwXmIo95rJnW4HQMTlkzCvMZD5I7u1d9EbyoGL8vTYSmcYbitDj5s3lsSAOSHfmRGBMV3JkSk2yhB6RTyW7CXqiQ5ty3BxLAcHvKLDpbStQrxLvU1rnmWGowm8uVeuZZGe18I+PlV+MRrE+5xHXl8N5C3fiNHxDLPjFlJLnPLS5gkIo/URoQOGrHzf4U58B6rQBRp2+EAkx/pP8tFn8XdWUj1YZ+Jqbahp5Xvz6dTyihHVBfAFWXZanVSh+9JWURdCf+GUYbeEhQ7AgMBAAECggEANJBymB0TBnZwfu32Y1jSvYD4yUm0GTXUaSfYsl5wXbdACLgqwISNwFcOHsaP1aPs5LYUnBLjqwHSj7yMTOvM7JUOU+lUfq7Qp5wE0kt0clr+kKXX8f63hJGY/bXPfUbSlDjDWIiP1nL6ALRvD2l9hGOOSfa6lNkQ6ao/q492A6r0kDViM3c7JV0/L1mDmG1o44iu0tIxbU4T5ZIMOuueAO1XzkJZypK4/TUjpHLAmGk94w555Ew3YYYKvs3LibY/WW1ClrDbNbgjlwWRKQkKsseVZ5yi1uwG4WsxLl+/0FihnLzEjG7YgX+pJmZt9Mqtup5HOqAyImlTD4IU9L5gwQKBgQDuK1xQpwJk8PppwBzicVW4noVpcZFnQflqLPvK9a5ZYnmijXqqGPPdBIURO4F9eovE4/NmlAfy7dpD4W1m9Hh2HWLvNxbzB4EaSvr/V4jtHROMNv4sKfdYTxEas1+5gFF+qVo4pGM0+tIK1IrxShyX9AbcYA9baTV87VDsXfV1WwKBgQDE7SfHPdOybE1DCKPxp+utxB7521y01MfdsQ/nf2cXjbXbRciFxfcR5rUl52nWOSmAeQyHx6J2qpoXTVaxQADXrSZf9fnqYDYrux7MoXsYrE9ssQPWyG7WA1EKrUuwJotytSDvSc2DCiyYR+VlzFutPJNtw7S4wuDkqu5ham6yoQKBgQDoPB+oBTGwe8mxlEKWCy0q+GfeiX7e0NYDUhmzrhxzzDIO7yXM9sfTm7SlOQDq+bOQKTYNsAqQNp290vJ6kadkhQMTfwRBte+NU8+rbA94JgIJlxYAumsVEQP8pSuSFi1g9IGk+aZC4tAs/1L8NOfsw2ZtPL28xLoDNuTRlkziQQKBgHhDvX9YqujUa3r80+LJgo1ksOY2qa9mI4mokOX4UKV5zg5T19qaxAESvJ6hbWl/D3F+e8CQkw+VsPV6Le+h/uG4xS7lsfMJPwwGAxFFrT+sO/VNReLuFYJuFBhJLWguogszwyFgGTpmBEYb4mUBIRvzWrY2RzBvE5mTKnIj5ecBAoGAVaNA2I3wwuvcazxr0M9sWECf9cpfuaUmqSn42NBSL3Y1QgX/zI3gOuFDjHbdreTf7QoQLvcXLHhLBTMrUro+3YabYRLo3gs0dR9GyXJcqr+IJ3LASm5horgzA+Tm5bDoo+nvoFxe5WaXX5AyFG1uokqe0dx5lr6vGlvZK9+TFwI=";

    protected static String BASE_URL = "http://ip:port/api";

    public static void main(String[] args) {

        String URI = "scis/cust/info/create/V1";
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, PRI_KEY, APIGW_PUBLIC_KEY);
        try {
            ScisCustInfoCreateRequestV1.ScisCustomerInfoRequestV1Biz bizContent = new ScisCustInfoCreateRequestV1.ScisCustomerInfoRequestV1Biz();
            ScisCustInfoCreateRequestV1 request = new ScisCustInfoCreateRequestV1();

            String reqData = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                    "<APPROOT from=\"F-CORP\" to=\"F-SCIS\" mode=\"syn\" >\n" +
                    "\t<PUBLIC>\n" +
                    "\t\t<!-- 交易代码 -->\n" +
                    "\t\t<TxCode>SCI01000</TxCode>\n" +
                    "\t\t<!-- 公司代码 参照工行统一机构代码的分配-->\n" +
                    "\t\t<CorpCode>0601500000</CorpCode>\t\t\t\t\n" +
                    "\t\t<!-- 应用号 -->\n" +
                    "\t\t<AppNo>001</AppNo>\t\t\n" +
                    "\t\t<!-- 交易机构号 子机构代码 -->\n" +
                    "\t\t<SubCorpCode>0601500000</SubCorpCode>\t\t\n" +
                    "\t\t<!-- 交易发起终端号  客户端请求的端机IP地址或者其它能够唯一标识发起方的信息 -->\n" +
                    "\t\t<SendFrom></SendFrom>\n" +
                    "\t\t<!-- 操作员编号  统一认证号-->\n" +
                    "\t\t<OperNo></OperNo>\n" +
                    "\t\t<!-- 操作员密级 -->\n" +
                    "\t\t<OperSecLevel></OperSecLevel>\t\t\t\n" +
                    "\t\t<!-- 验证码 暂时送空-->\n" +
                    "\t\t<Verify></Verify>\t\t\n" +
                    "\t\t<!-- 发送方日期 -->\n" +
                    "\t\t<SendDate>20120816</SendDate>\n" +
                    "\t\t<!-- 发送方时间 -->\n" +
                    "\t\t<SendTime>193636</SendTime>\t\t\n" +
                    "\t\t<!-- 发送方流水号 -->\t\t\t\n" +
                    "\t\t<SerialNo>20120816193636000002</SerialNo>\n" +
                    "\t\t<!-- 语言集 -->\n" +
                    "\t\t<Language>zh_CN</Language>\n" +
                    "\t\t<!-- 开始记录数 -->\n" +
                    "\t\t<BeginRec></BeginRec>\n" +
                    "\t\t<!-- 一次查询最大记录数 -->\n" +
                    "\t\t<MaxRec></MaxRec>\n" +
                    "\t\t<!-- 版本号 -->\t\t\t\t\t\t\n" +
                    "\t\t<Ver>1.0.1</Ver>\t\t\n" +
                    "\t</PUBLIC>\n" +
                    "\t<PRIVATE>\n" +
                    "\t\t<Common>\n" +
                    "\t\t\t<!-- 客户信息号-->\n" +
                    "\t\t\t<CUST_ID></CUST_ID>\n" +
                    "\t\t\t<!-- 客户类型-必输  1：个人客户  2：法人客户-->\n" +
                    "\t\t\t<CUST_TYPE>1</CUST_TYPE>\t\t\n" +
                    "\t\t\t<!-- 客户主证件所属国家/地区-->\n" +
                    "\t\t\t<PRIM_NAT>156</PRIM_NAT>\n" +
                    "\t\t</Common>\n" +
                    "\t\t<Category name=\"SCIS_INDIVIDUAL_BASE\">   <!-- 客户基本信息-->\t\n" +
                    "\t\t\t<field-list>\n" +
                    "\t\t\t<!-- 修改模式 1-新增 -->\n" +
                    "\t\t\t<ModifyType>1</ModifyType>\n" +
                    "\t\t\t<!-- 姓名 -->\n" +
                    "\t\t\t<field name='NAME'>李四</field>\n" +
                    "\t\t\t<!-- 开户证件类型(主证件) -->\n" +
                    "\t\t\t<field name='REG_TYPE'>0</field>\n" +
                    "\t\t\t<!-- 开户证件号码 -->\n" +
                    "\t\t\t<field name='REG_NO'>320413123234451122</field>\n" +
                    "\t\t\t<!-- 曾用名 -->\t\t\t\n" +
                    "\t\t\t<field name='ORI_NAME'>李四</field>\n" +
                    "\t\t\t<!-- 英文名-->\n" +
                    "\t\t\t<field name='EN_NAME'>Jack</field>\n" +
                    "\t\t\t<!-- 出生日期 -->\n" +
                    "\t\t\t<field name='BIRTH_DATE'>20120101</field>\t\t\t\n" +
                    "\t\t\t<!-- 性别 1-男 2-女 0-未知 -->\n" +
                    "\t\t\t<field name='GENDER'>1</field>\n" +
                    "\t\t\t<!-- 死亡日期 -->\n" +
                    "\t\t\t<field name='DEATH_DATE'>20700101</field>\t\t\t\t\t\n" +
                    "\t\t\t<!-- 生存状态 -->\t\t\t\n" +
                    "\t\t\t<field name='LIVE_STATUS'>1</field>\n" +
                    "\t\t\t<!-- 婚姻状况-->\n" +
                    "\t\t\t<field name='MARITAL_STAT'>1</field>\n" +
                    "\t\t\t<!-- 国籍 -->\n" +
                    "\t\t\t<field name='PRIM_NAT'>156</field>\t\t\t\t\n" +
                    "\t\t\t<!-- 户口所在地 -->\t\t\t\n" +
                    "\t\t\t<field name='RESI_OFFICE'>李四</field>\n" +
                    "\t\t\t<!-- 常住地址 -->\t\t\t                   \t\t\t\n" +
                    "\t\t\t<field name='ADDR'></field>   \t\t\t                                             \n" +
                    "\t\t\t<!-- 高度（厘米）-->\n" +
                    "\t\t\t<field name='HEIGHT_VAL'>179</field>\n" +
                    "\t\t\t<!-- 重量（公斤） -->\n" +
                    "\t\t\t<field name='WEIGHT_VALUE'>86</field>\t\t\t\n" +
                    "\t\t\t<!-- 社保类型 INSUTYPE  0-否 1-是 -->\n" +
                    "\t\t\t<field name='INSUTYPE'>1</field>\n" +
                    "\t\t\t<!-- 收入币种 -->\n" +
                    "\t\t\t<field name='CURRENCY'>388999</field>\t\t\n" +
                    "\t\t\t<!-- 年收入(单位为分) -->\n" +
                    "\t\t\t<field name='INCOME'>388999</field>\t\t\t\t\t\n" +
                    "\t\t\t<!-- 主要经济来源 -->\t\t\t\n" +
                    "\t\t\t<field name='PRIMARYECONOMICSOU'>001</field>\n" +
                    "\t\t\t<!-- 工作单位单位类别-->\n" +
                    "\t\t\t<field name='EMPLOYER_TYPE'>1</field>\n" +
                    "\t\t\t<!-- 工作单位 -->\n" +
                    "\t\t\t<field name='EMPLOYER'>0302</field>\t\t\n" +
                    "\t\t\t<!-- 职业 -->\t\t\t\t\n" +
                    "\t\t\t<field name='PROF'>1</field>\n" +
                    "\t\t\t<!-- 职业状态 -->\n" +
                    "\t\t\t<field name='PROF_STAT'>1</field>\t\t\t\t\t\n" +
                    "\t\t\t<!-- 企志业高管人员标志 -->\t\t\t\n" +
                    "\t\t\t<field name='CO_MANAGER_FLAG'>001</field>\n" +
                    "\t\t\t<!-- 技术职称-->\n" +
                    "\t\t\t<field name='TECHNIQUELEVEL'>1</field>\n" +
                    "\t\t\t<!-- 行政级别 -->\n" +
                    "\t\t\t<field name='EXECUTIVELEVEL'>0302</field>\t\t\n" +
                    "\t\t\t<!--主管客户经理  统一认证号-->\n" +
                    "\t\t\t<field name='MANAGER'>0</field>\t\t\t  \n" +
                    "\t\t\t<!--尊称-->\n" +
                    "\t\t\t<field name='RESPECT_NAME'>0</field>\t\t           \n" +
                    "\t\t\t</field-list>\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n" +
                    "\t\t</Category>\n" +
                    "\t\t<Category name=\"SCIS_CORP_BASE\">   <!-- 法人客户基本信息-->\n" +
                    "\t\t\t<field-list>\n" +
                    "\t\t\t<!-- 修改模式 1-新增 2-修改 3-删除-->\n" +
                    "\t\t\t<ModifyType>1</ModifyType>\n" +
                    "\t\t\t<!-- 开户证件类型(主证件) -->\n" +
                    "\t\t\t<field name='REG_TYPE'>001</field>\n" +
                    "\t\t\t<!-- 开户证件号码 -->\n" +
                    "\t\t\t<field name='REG_NO'>320413123234451122</field> \n" +
                    "\t\t\t<!-- 名称 -->\n" +
                    "\t\t\t<field name='NAME'>机构名称</field>\n" +
                    "\t\t\t<!-- 英文名-->\n" +
                    "\t\t\t<field name='EN_NAME'>Jack</field>\n" +
                    "\t\t\t<!-- 单位类别 -->\n" +
                    "\t\t\t<field name='EMPLOYER_TYPE'>YYYYMMDD</field>\t\t\t\t\t\n" +
                    "\t\t\t<!-- 经济性质 -->\t\t\t\n" +
                    "\t\t\t<field name='ORGOWNERECONOMICTY'>1</field>\n" +
                    "\t\t\t<!-- 单位级别-->\n" +
                    "\t\t\t<field name='LEVELOFADMINISTRAT'>1</field>\n" +
                    "\t\t\t<!-- 公司员工人数 -->\n" +
                    "\t\t\t<field name='NO_OF_EMP'>020021</field>\t\t\t\t\n" +
                    "\t\t\t<!-- 财政年度开始日期 >MM_DD -->\t\t\t\n" +
                    "\t\t\t<field name='FISCAL_YR_START'>MM_DD</field>\n" +
                    "\t\t\t<!-- 总收入币种-->\n" +
                    "\t\t\t<field name='GROSS_REVN_CRNCY'>Jack</field>\n" +
                    "\t\t\t<!-- 总收入 -->\n" +
                    "\t\t\t<field name='GROSS_REVN'>YYYYMMDD</field>\t\t\t\n" +
                    "\t\t\t<!-- 客户营销状态 -->\n" +
                    "\t\t\t<field name='SALES_STATUS'>1</field>\n" +
                    "\t\t\t<!-- 客户来源 -->\n" +
                    "\t\t\t<field name='HOW_REFER'>2</field>\t\t\t\t\t\n" +
                    "\t\t\t<!-- 公司所有者数目 -->\t\t\t\n" +
                    "\t\t\t<field name='NO_OF_OWNERS'>001</field>\n" +
                    "\t\t\t<!-- 法人资格标志-->\n" +
                    "\t\t\t<field name='CORPORATIONFLAG'>1</field>\n" +
                    "\t\t\t<!-- 法人开始日期 -->\n" +
                    "\t\t\t<field name='ORG_START_DATE'>0302</field>\t\t\n" +
                    "\t\t\t<!-- 法人结束日期 -->\t\t\t\t\n" +
                    "\t\t\t<field name='ORG_END_DATE'>1</field>\n" +
                    "\t\t\t<!-- 兼营业务范围 -->\n" +
                    "\t\t\t<field name='INDUSTRYFORSECONDA'>222</field>\t\t\t\t\t\n" +
                    "\t\t\t<!-- 经营范围 -->\t\t\t\n" +
                    "\t\t\t<field name='INDUSTRYFORPRIMARY'>001</field>\n" +
                    "\t\t\t<!-- 经营方式-->\n" +
                    "\t\t\t<field name='BUSINESSAREA'>1</field>\n" +
                    "\t\t\t<!-- 企业规模 -->\n" +
                    "\t\t\t<field name='SCALEOFORG'>0302</field>\t\t\n" +
                    "\t\t\t<!-- 上市公司标志 -->\n" +
                    "\t\t\t<field name='ISSUESHAREFLAG'>2</field>\t\t\t\t\t\n" +
                    "\t\t\t<!-- 上市地点 -->\t\t\t\n" +
                    "\t\t\t<field name='ISSUESHAREPOSITION'>001</field>\n" +
                    "\t\t\t<!-- 是否有进出口权-->\n" +
                    "\t\t\t<field name='AUTHORITYFORIMPORT'>1</field>\n" +
                    "\t\t\t<!-- 是否有外汇业务 -->\n" +
                    "\t\t\t<field name='AUTHORITYFORFOREIG'>0302</field>\t\t\n" +
                    "\t\t\t<!-- 所在地（注册地） -->\t\t\t\t\n" +
                    "\t\t\t<field name='CITYOFREGISTRATION'>1</field>\n" +
                    "\t\t\t<!-- 行业代码 -->\n" +
                    "\t\t\t<field name='INDUSTRYCODE'>222</field>\t\t\t\t\t\n" +
                    "\t\t\t<!-- VIP客户标志 -->\t\t\t\n" +
                    "\t\t\t<field name='VIPORGFLAG'>001</field>\n" +
                    "\t\t\t<!-- 资产规模-->\n" +
                    "\t\t\t<field name='ASSETAMOUNT'>1</field>\n" +
                    "\t\t\t<!-- 注册资金币种 -->\n" +
                    "\t\t\t<field name='REG_CAPITAL_CRNCY'></field>\t\t\t\t\t\t\t\t\n" +
                    "\t\t\t<!-- 注册资金 -->\n" +
                    "\t\t\t<field name='REG_CAPITAL'>0302</field>\t\t\n" +
                    "\t\t\t<!-- 发薪日 -->\n" +
                    "\t\t\t<field name='PAYDAY'>222</field>\t\t\t\t\t\n" +
                    "\t\t\t<!-- 匡算工资量币种 -->\t\t\t\n" +
                    "\t\t\t<field name='ESTISALARYAMO_CRNCY'>001</field>\t\t\t\n" +
                    "\t\t\t<!-- 匡算工资量 -->\t\t\t\n" +
                    "\t\t\t<field name='ESTIMATEDSALARYAMO'>001</field>\n" +
                    "\t\t\t<!-- 离退休人员数-->\n" +
                    "\t\t\t<field name='NUMBEROFRETIREE'>1</field>\n" +
                    "\t\t\t<!-- 是否在保税区 -->\n" +
                    "\t\t\t<field name='STAYINTAXFREEAREAF'>0302</field>\t\t\n" +
                    "\t\t\t<!-- 建筑物产权 -->\n" +
                    "\t\t\t<field name='OWNEROFBUILDING'>2</field>\t\t\t\t\t\n" +
                    "\t\t\t<!-- 信贷风险评估 -->\t\t\t\n" +
                    "\t\t\t<field name='CR_RSK_RATING'>001</field>\n" +
                    "\t\t\t<!-- 办公面积-->\n" +
                    "\t\t\t<field name='OFFICE_AERA'>1</field>\n" +
                    "\t\t\t<!-- 营业面积 -->\n" +
                    "\t\t\t<field name='MART_AERA'>0302</field>\t\t\n" +
                    "\t\t\t<!-- 开始办公时间（每日） -->\t\t\t\t\n" +
                    "\t\t\t<field name='BEGIN_WORK_TIME'>1</field>\n" +
                    "\t\t\t<!-- 本国企业标志 -->\n" +
                    "\t\t\t<field name='DOMESTIC_COM_FLAG'>222</field>\t\t\t\t\t\n" +
                    "\t\t\t<!-- 券商标志 -->\t\t\t\n" +
                    "\t\t\t<field name='STOCK_BROKER'>001</field>\n" +
                    "\t\t\t<!-- 代理行标志-->\n" +
                    "\t\t\t<field name='AGENCY_FLAG'>1</field>\n" +
                    "\t\t\t<!-- 机构/公司客户标志 -->\n" +
                    "\t\t\t<field name='ORG_COM_FLAG'>0302</field>\t\t\t\t\t\t\t\n" +
                    "\t\t\t<!-- 企业知名度 -->\t\t\t\n" +
                    "\t\t\t<field name='POPULARITY'>001</field>\n" +
                    "\t\t\t<!-- 关注客户标志 -->\n" +
                    "\t\t\t<field name='INTEREST_FLAG'>0302</field>\t\t\n" +
                    "\t\t\t<!-- 银行同业客户标志 -->\t\t\t\n" +
                    "\t\t\t<field name='OTH_BANK_CUST'>001</field>\n" +
                    "\t\t\t<!-- 隶属关系(主管单位名称)-->\n" +
                    "\t\t\t<field name='NAMEOFPARENTORG'>1</field>\n" +
                    "\t\t\t<!-- 中小企业管理标识 -->\n" +
                    "\t\t\t<field name='MEDCORP_FLAG'>0302</field>\t\t\n" +
                    "\t\t\t<!-- 注册国家 -->\t\t\t\t\n" +
                    "\t\t\t<field name='PRIM_NAT'>1</field>\n" +
                    "\t\t\t<!-- 第二注册国家 -->\n" +
                    "\t\t\t<field name='SEC_NAT'>222</field>\t\t\t\n" +
                    "\t\t\t<!-- 法人姓名 -->\n" +
                    "\t\t\t<field name='ARTIFNAME'>张三</field>\t\t\n" +
                    "\t\t\t<!--主管客户经理  统一认证号-->\n" +
                    "\t\t\t<field name='MANAGER'>0</field>\t\t\t\t\n" +
                    "\t\t\t</field-list>\t\t\t\t\t\t\t\t\t\t\t\n" +
                    "\t\t</Category>\t\t\n" +
                    "\t\t<Category name=\"SCIS_REG_CERT\"> <!-- 注册证件（多记录） -->\n" +
                    "\t\t\t<field-list>  <!-- 辅助证件1 -->\n" +
                    "\t\t\t<!-- 修改模式 1-新增 2-修改 3-删除-->\n" +
                    "\t\t\t<ModifyType>1</ModifyType>\n" +
                    "\t\t\t<!-- 序号 -->\n" +
                    "\t\t\t<field name='SEQ_NO'>1</field>\t\t\t\n" +
                    "\t\t\t<!-- 证件类型 -->\n" +
                    "\t\t\t<field name='REG_TYPE'>0</field>\n" +
                    "\t\t\t<!-- 证件号码 --> \n" +
                    "\t\t\t<field name='REG_NO'>320413123234451122</field>\n" +
                    "\t\t\t<!-- 客户名称 -->\n" +
                    "\t\t\t<field name='NAME'>李四</field>\t\t\t\t\t\t\t\t \n" +
                    "\t\t\t<!-- 客户所属国家地区(证件注册国家) -->\n" +
                    "\t\t\t<field name='PRIM_NAT'>156</field>\t\t\t\n" +
                    "\t\t\t<!-- 证件签发日期 --> \t\t\t\t \n" +
                    "\t\t\t<field name='STAT_DATE'>YYYYMMDD</field>\n" +
                    "\t        <!-- 证件截止日期 -->\t\n" +
                    "\t\t\t<field name='END_DATE'>YYYYMMDD</field>\t\n" +
                    "\t\t\t<!-- 证件长期有效标志 -->\n" +
                    "\t\t\t<field name='VALID_STAT'></field>\t\t\t\t\t\t\t\t\t\n" +
                    "\t\t\t</field-list>\n" +
                    "\t\t</Category>\n" +
                    "\t\t<Category name=\"SCIS_PHYS_CONNECTION\"> \t<!-- 物理联系（多记录） -->\n" +
                    "\t\t\t<field-list>\n" +
                    "\t\t\t<!-- 修改模式 1-新增 2-修改 3-删除-->\n" +
                    "\t\t\t  <ModifyType>1</ModifyType>\t\n" +
                    "\t\t\t<!-- 序号 -->\n" +
                    "\t\t\t<field name='SEQ_NO'>1</field>\t\t\t\t\t\n" +
                    "\t\t\t<!-- 客户与联系信息关系类型 1、注册；2、办公；3、住宅；4、其他 -->\n" +
                    "\t\t\t<field name='PRIN_CP_ROLE'>50</field> \n" +
                    "\t\t\t<!-- 邮政编码 -->\n" +
                    "\t\t\t<field name='POST_CDE'>50</field>\n" +
                    "\t\t\t<!-- 国家 -->\n" +
                    "\t\t\t<field name='COUNTRY'>50</field>\n" +
                    "\t\t\t<!-- 省 -->\n" +
                    "\t\t\t<field name='PROVINCE'>50</field>\n" +
                    "\t\t\t<!-- 城市 -->\n" +
                    "\t\t\t<field name='CITY'>50</field>\n" +
                    "\t\t\t<!-- 县、区 -->\n" +
                    "\t\t\t<field name='COUNTY'>50</field>\n" +
                    "\t\t\t<!-- 地址 -->\t\t\t\t\n" +
                    "\t\t\t<field name='ADDR'>50</field>\t\n" +
                    "\t\t\t<!-- 国际电话国家代码 -->\t\t\t\t\n" +
                    "\t\t\t<field name='CNTRY_CDE'>86</field>\n" +
                    "\t\t\t<!-- 电话区号 -->\t\t\n" +
                    "\t\t\t<field name='AREA_CDE'>50</field>\t\n" +
                    "\t\t\t<!--号码主体-->\n" +
                    "\t\t\t<field name='NO_BODY'>0</field>\t\t\t\n" +
                    "\t\t\t<!-- 分机号码 -->\t\t\t\t\n" +
                    "\t\t\t<field name='EXTN'>86</field>\n" +
                    "\t\t\t<!-- 传真 -->\t\t\n" +
                    "\t\t\t<field name='FAX'>50</field>\t\n" +
                    "\t\t\t<!--联系人-->\n" +
                    "\t\t\t<field name='CONTACT'>0</field>\t\t\t\n" +
                    "\t\t\t<!-- 联系人称谓 -->\t\t\t\t\n" +
                    "\t\t\t<field name='PREFIX'>86</field>\n" +
                    "\t\t\t<!-- 是否客户喜好的联系信息标志 -->\t\t\n" +
                    "\t\t\t<field name='DFLT_FLAG'>1</field>\t\n" +
                    "\t\t\t<!--联系信息可用的开始时间-->\n" +
                    "\t\t\t<field name='AVAIL_START_DATE'>YYYYMMDD HH24MISS</field>\t\t\t\n" +
                    "\t\t\t<!-- 联系信息可用的结束时间 -->\t\t\t\t\n" +
                    "\t\t\t<field name='AVAIL_END_DAY'>YYYYMMDD HH24MISS</field>\n" +
                    "\t\t\t<!--可联系时间-->\n" +
                    "\t\t\t<field name='AVAIL_TIME'>HH24MISS</field>\t\t\n" +
                    "\t\t\t<!--联系时用的语言-->\n" +
                    "\t\t\t<field name='PREF_LANG'>HH24MISS</field>\t\t\n" +
                    "\t\t\t<!-- 备注 -->\t\t\n" +
                    "\t\t\t<field name='NOTES'>YYYYMMDD</field>\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n" +
                    "\t\t\t</field-list>\t\t\t\n" +
                    "\t\t</Category>\n" +
                    "\t\t<Category name=\"SCIS_NET_CONNECTION\"> \t<!-- 移动(手机，网络等)联系() -->\n" +
                    "\t\t\t<field-list>\n" +
                    "\t\t\t<!-- 修改模式 1-新增 2-修改 3-删除-->\n" +
                    "\t\t\t<ModifyType>1</ModifyType>\t\n" +
                    "\t\t\t<!-- 序号 -->\n" +
                    "\t\t\t<field name='SEQ_NO'>1</field>\t\t\t\t\t\t\n" +
                    "\t\t\t<!--联系方式类型-->\t\t\n" +
                    "\t\t\t<field name='CONTRACT_TYPE'>50</field>\n" +
                    "\t\t\t<!--联系方式-->\n" +
                    "\t\t\t<field name='CONTRACT_ADDRESS'>1234666666</field> \n" +
                    "\t\t\t<!--备注-->\n" +
                    "\t\t\t<field name='NOTES'>1234666666</field>\t\n" +
                    "\t\t\t</field-list>\n" +
                    "    \t</Category>\n" +
                    "\t\t<Category name=\"SCIS_CUST_REL\"> <!-- 客户关系（多记录） -->\n" +
                    "\t\t\t<field-list>\n" +
                    "\t\t\t<!-- 修改模式 1-新增 2-修改 3-删除-->\n" +
                    "\t\t\t<ModifyType>1</ModifyType>\n" +
                    "\t\t\t<!-- 序号 -->\n" +
                    "\t\t\t<field name='SEQ_NO'>1</field>\t\t \t\t\n" +
                    "\t\t\t<!-- 关联客户ID -->\t\t\t\t\n" +
                    "\t\t\t<field name='ROL_CUST_ID'>50</field>\n" +
                    "\t\t\t<!-- 关联客户ID类型 -->\t\t\t\t\t\t\n" +
                    "\t\t\t<field name='ROLEPLAYER_TYPE'>50</field>\n" +
                    "\t\t\t<!-- 客户与客户关系类型 -->\n" +
                    "\t\t\t<field name='PAR_TO_PAR_REL_ROL'>50</field>\t\n" +
                    "\t\t\t<!-- 客户关系描述 -->\n" +
                    "\t\t\t<field name='ROL_DESCRIPT'>032210211112031</field>\n" +
                    "\t\t\t<!-- 角色在关系中的百分比  (对公,所占股份比例) -->\n" +
                    "\t\t\t<field name='PERC_OF_REL'>YYYYMMDD</field>\t\t\t\t\n" +
                    "\t\t\t<!-- 出资额 -->\n" +
                    "\t\t\t<field name='INVEST_AMOUNT'>032210211112031</field>\n" +
                    "\t\t\t<!-- 出资币种 -->\n" +
                    "\t\t\t<field name='INVEST_CURRENCY'>YYYYMMDD</field>\t\t\t\t\t\t\t\t\t\t\t\t\n" +
                    "\t\t\t</field-list>\n" +
                    "\t\t</Category>\t\n" +
                    "\t\t<Category name=\"SCIS_PROD_PROTOCOL\"> <!-- 客户持有产品（多记录） -->\n" +
                    "\t\t\t<field-list>\n" +
                    "\t\t\t<!-- 修改模式 1-新增 2-修改 3-删除-->\n" +
                    "\t\t\t<ModifyType>1</ModifyType>\t\n" +
                    "\t\t\t<!-- 序号 -->\n" +
                    "\t\t\t<field name='SEQ_NO'>1</field>\t\t\t\t\t\t\t    \n" +
                    "\t\t\t<!-- 客户与协议的关系类型 -->\n" +
                    "\t\t\t<field name='PRIN_ARR_TYPE'>1</field>\t\t\t\t\t    \n" +
                    "\t\t\t<!--业务领域   1保险 2租赁 3投行 4基金 5银行-->\n" +
                    "\t\t\t<field name='ACCOUNT_AREA'>1</field>\n" +
                    "\t\t\t<!--产品类型-->\n" +
                    "\t\t\t<field name='ACCOUNT_TYPE'>1234666666</field>\t\t\t\t\t\n" +
                    "\t\t\t<!--业务系统中帐号(产品编号，例如保单号,基金账号)-->\n" +
                    "\t\t\t<field name='EXT_ARR_REF'>CONTRA1234666666</field>\t\n" +
                    "\t\t\t<!--客户在产品中的角色-->\n" +
                    "\t\t\t<field name='EXT_ARR_ROL'>101</field>\t\t\t\t\t\t\t\t\t\t\n" +
                    "\t\t\t</field-list>\n" +
                    "\t</Category>\t\n" +
                    "\t</PRIVATE>\n" +
                    "</APPROOT>\n";

            bizContent.setReqData(reqData);
            request.setBizContent(bizContent);
            request.setServiceUrl(BASE_URL + "/" + URI);
            request.setBizContent(bizContent);
            ScisCustInfoCreateResponseV1 response = (ScisCustInfoCreateResponseV1) client.execute(request);
            log.info(response.getResData());
            if (response.isSuccess()) {
                // 业务成功处理
                log.info("处理成功");
                log.info(response.getResData());
            } else {
                // 失败
                log.info("处理失败");
                log.info(response.getReturnCode()+","+response.getReturnMsg());
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
        }
    }
}
