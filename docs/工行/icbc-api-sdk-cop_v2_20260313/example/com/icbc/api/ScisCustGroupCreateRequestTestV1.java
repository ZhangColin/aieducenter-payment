package com.icbc.api;

import com.icbc.api.request.ScisCustGroupCreateRequestV1;
import com.icbc.api.response.ScisCustGroupCreateResponseV1;

import java.util.logging.Logger;

/**
 * @Description TODO
 * @author: kfzx-lij1
 * @date: 2023/1/29 14:15
 **/
public class ScisCustGroupCreateRequestTestV1 {

    protected static Logger log = Logger.getLogger(ScisCustGroupCreateRequestTestV1.class.getName());

    protected static String APP_ID = "90000000000000003382";

    protected static String APIGW_PUBLIC_KEY =
            "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY"
                    + "0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

    protected static String PRI_KEY =
            "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC3NdXULtLHXlmvn8nGuac8Us84tmdEd9UuXLAY7j4c8KAcNnPDRLDJkLD2p+gYVw+EF0WrVgdZwXmIo95rJnW4HQMTlkzCvMZD5I7u1d9EbyoGL8vTYSmcYbitDj5s3lsSAOSHfmRGBMV3JkSk2yhB6RTyW7CXqiQ5ty3BxLAcHvKLDpbStQrxLvU1rnmWGowm8uVeuZZGe18I+PlV+MRrE+5xHXl8N5C3fiNHxDLPjFlJLnPLS5gkIo/URoQOGrHzf4U58B6rQBRp2+EAkx/pP8tFn8XdWUj1YZ+Jqbahp5Xvz6dTyihHVBfAFWXZanVSh+9JWURdCf+GUYbeEhQ7AgMBAAECggEANJBymB0TBnZwfu32Y1jSvYD4yUm0GTXUaSfYsl5wXbdACLgqwISNwFcOHsaP1aPs5LYUnBLjqwHSj7yMTOvM7JUOU+lUfq7Qp5wE0kt0clr+kKXX8f63hJGY/bXPfUbSlDjDWIiP1nL6ALRvD2l9hGOOSfa6lNkQ6ao/q492A6r0kDViM3c7JV0/L1mDmG1o44iu0tIxbU4T5ZIMOuueAO1XzkJZypK4/TUjpHLAmGk94w555Ew3YYYKvs3LibY/WW1ClrDbNbgjlwWRKQkKsseVZ5yi1uwG4WsxLl+/0FihnLzEjG7YgX+pJmZt9Mqtup5HOqAyImlTD4IU9L5gwQKBgQDuK1xQpwJk8PppwBzicVW4noVpcZFnQflqLPvK9a5ZYnmijXqqGPPdBIURO4F9eovE4/NmlAfy7dpD4W1m9Hh2HWLvNxbzB4EaSvr/V4jtHROMNv4sKfdYTxEas1+5gFF+qVo4pGM0+tIK1IrxShyX9AbcYA9baTV87VDsXfV1WwKBgQDE7SfHPdOybE1DCKPxp+utxB7521y01MfdsQ/nf2cXjbXbRciFxfcR5rUl52nWOSmAeQyHx6J2qpoXTVaxQADXrSZf9fnqYDYrux7MoXsYrE9ssQPWyG7WA1EKrUuwJotytSDvSc2DCiyYR+VlzFutPJNtw7S4wuDkqu5ham6yoQKBgQDoPB+oBTGwe8mxlEKWCy0q+GfeiX7e0NYDUhmzrhxzzDIO7yXM9sfTm7SlOQDq+bOQKTYNsAqQNp290vJ6kadkhQMTfwRBte+NU8+rbA94JgIJlxYAumsVEQP8pSuSFi1g9IGk+aZC4tAs/1L8NOfsw2ZtPL28xLoDNuTRlkziQQKBgHhDvX9YqujUa3r80+LJgo1ksOY2qa9mI4mokOX4UKV5zg5T19qaxAESvJ6hbWl/D3F+e8CQkw+VsPV6Le+h/uG4xS7lsfMJPwwGAxFFrT+sO/VNReLuFYJuFBhJLWguogszwyFgGTpmBEYb4mUBIRvzWrY2RzBvE5mTKnIj5ecBAoGAVaNA2I3wwuvcazxr0M9sWECf9cpfuaUmqSn42NBSL3Y1QgX/zI3gOuFDjHbdreTf7QoQLvcXLHhLBTMrUro+3YabYRLo3gs0dR9GyXJcqr+IJ3LASm5horgzA+Tm5bDoo+nvoFxe5WaXX5AyFG1uokqe0dx5lr6vGlvZK9+TFwI=";

    protected static String BASE_URL = "http://ip:port/api";

    public static void main(String[] args) {

        String URI = "scis/cust/group/create/V1";
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, PRI_KEY, APIGW_PUBLIC_KEY);
        try {
            ScisCustGroupCreateRequestV1.ScisCustomerInfoRequestV1Biz bizContent = new ScisCustGroupCreateRequestV1.ScisCustomerInfoRequestV1Biz();
            ScisCustGroupCreateRequestV1 request = new ScisCustGroupCreateRequestV1();

            String reqData = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                    "<APPROOT from=\"F-CORP\" to=\"F-SCIS\" mode=\"syn\" >\n" +
                    "\t<PUBLIC>\n" +
                    "\t\t<!-- 交易代码 -->\n" +
                    "\t\t<TxCode>SCI05050</TxCode>\n" +
                    "\t\t<!-- 公司代码 参照工行统一机构代码的分配-->\n" +
                    "\t\t<CorpCode>0018100000</CorpCode>\t\t\t\t\n" +
                    "\t\t<!-- 应用号 -->\n" +
                    "\t\t<AppNo>004</AppNo>\t\t\n" +
                    "\t\t<!-- 交易机构号 子机构代码 -->\n" +
                    "\t\t<SubCorpCode>0018100000</SubCorpCode>\t\t\n" +
                    "\t\t<!-- 隔离单元 -->\n" +
                    "\t\t<IsolationCell>0</IsolationCell>\n" +
                    "\t\t<!-- 业务经营所在国家或地区 -->\n" +
                    "\t\t<AreaCode>999</AreaCode>\n" +
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
                    "\t\t<Ver>1.0.2</Ver>\t\t\n" +
                    "\t</PUBLIC>\n" +
                    "\t<PRIVATE>\n" +
                    "\t\t<Category name=\"SCIS_HOUSEHOLD\"> <!-- 客户群体信息 -->\n" +
                    "\t\t\t<field-list>\n" +
                    "\t\t\t\t<!-- 修改模式 1-新增 -->\n" +
                    "\t\t\t\t<ModifyType>1</ModifyType>\t\t\t\t\n" +
                    "\t\t\t\t<!--客户群编号-->\n" +
                    "\t\t\t\t<field name='PARTYGROUP_NO'>87933523412</field>\n" +
                    "\t\t\t\t<!--群体类型-->\n" +
                    "\t\t\t\t<field name='HH_UNIT_TYPE'>1</field>\n" +
                    "\t\t\t\t<!--客户群客户信息组合 示例为上送5个客户信息从小到大排列-->\n" +
                    "\t\t\t\t<field name='CUSTNO_UNITE'>123466666611111123466666611112123466666611113123466666611114123466666611115</field>\n" +
                    "\t\t\t</field-list>\n" +
                    "\t\t</Category>\n" +
                    "\t</PRIVATE>\n" +
                    "</APPROOT>";

            bizContent.setReqData(reqData);
            request.setBizContent(bizContent);
            request.setServiceUrl(BASE_URL + "/" + URI);
            request.setBizContent(bizContent);
            ScisCustGroupCreateResponseV1 response = (ScisCustGroupCreateResponseV1) client.execute(request);
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
