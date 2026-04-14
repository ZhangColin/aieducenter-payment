package example.com.icbc.api;
import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.GyjrB2bFinanceComdrftQryendorsementbilllistRequestV1;
import com.icbc.api.response.GyjrB2bFinanceComdrftQryendorsementbilllistResponseV1;

import java.util.Random;
import java.util.logging.Logger;

public class GyjrB2bFinanceComdrftQryendorsementbilllistTestV1 {

    protected static final String MY_PRIVATE_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    protected static final String APIGW_PUBLIC_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";

    protected static final String APP_ID = "11000000000000001234";

    static Logger log = Logger.getLogger(GyjrB2bFinanceComdrftQryendorsementbilllistTestV1.class.getName());

    public static void main(String[] args) {

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, "RSA2", MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        GyjrB2bFinanceComdrftQryendorsementbilllistRequestV1 req = new GyjrB2bFinanceComdrftQryendorsementbilllistRequestV1();

        req.setServiceUrl("https://ip:port/api/gyjr/b2b/finance/comdrft/qryendorsementbilllist/V1");
        GyjrB2bFinanceComdrftQryendorsementbilllistRequestV1.GyjrB2bFinanceComdrftQryendorsementbilllistRequestV1Biz bizContent = new GyjrB2bFinanceComdrftQryendorsementbilllistRequestV1.GyjrB2bFinanceComdrftQryendorsementbilllistRequestV1Biz();
        GyjrB2bFinanceComdrftQryendorsementbilllistRequestV1.TransinfoBiz transInfo = new GyjrB2bFinanceComdrftQryendorsementbilllistRequestV1.TransinfoBiz();

        transInfo.setProtocolCode("GYJR20260701105640610539404");
        transInfo.setPlatVendorId("29");
        transInfo.setUkeyId("29");
        transInfo.setPageIndex("1");
        transInfo.setPageSize("1");
        transInfo.setBillHoldAcct("0");
        transInfo.setBillType("0");
        transInfo.setBillDeadDateFrom("20230910");
        transInfo.setBillDeadDateTo("20230930");
        transInfo.setBillNos("20240930");
        transInfo.setIsEndRsmtmk("102");
        transInfo.setIsB2b("0");
        transInfo.setAcctprBankCode("102");
        transInfo.setEndorseTimes("0");
        transInfo.setBillAmtFrom("100");
        transInfo.setBillAmtTo("102");

        bizContent.setTransInfoBiz(transInfo);
        req.setBizContent(bizContent);
        GyjrB2bFinanceComdrftQryendorsementbilllistResponseV1 response;

        Random rand = new Random();
        String msgId = rand.nextInt(99999) + "msg";
        try {
            response = client.execute(req,msgId);
            log.fine("JSONObject.toJSONString(response):"+ JSONObject.toJSONString(response));
            if (response.isSuccess()) {
                // 业务成功处理
                log.fine("response.getReturnCode():"+response.getReturnCode());
            } else {
                //失败
                log.fine("response.getReturnCode():"+response.getReturnCode());
                log.fine("response.getReturnMsg():"+response.getReturnMsg());
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }
}
