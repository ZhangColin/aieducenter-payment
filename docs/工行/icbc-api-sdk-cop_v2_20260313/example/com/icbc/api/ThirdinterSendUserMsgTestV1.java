package com.icbc.api.response;

import com.icbc.api.IcbcResponse;
import com.icbc.api.internal.util.fastjson.annotation.JSONField;

import java.util.Map;

public class ThirdinterSendUserMsgResponseV1 extends IcbcResponse {
    @JSONField(name = "trans_map")
    private Map trans_map;

    public Map getTrans_map() {
        return trans_map;
    }

    public void setTrans_map(Map trans_map) {
        this.trans_map = trans_map;
    }

    @JSONField(name = "response_biz_content")
    private ThirdinterSendUserMsgResponseV1BizContent responseBizContent;

    public ThirdinterSendUserMsgResponseV1BizContent getResponseBizContent() {
        return responseBizContent;
    }

    public void setResponseBizContent(ThirdinterSendUserMsgResponseV1BizContent responseBizContent) {
        this.responseBizContent = responseBizContent;
    }

    public static class ThirdinterSendUserMsgResponseV1BizContent{
        @JSONField(name = "return_code")
        private String return_code;

        @JSONField(name = "return_msg")
        private String return_msg;

        @JSONField(name = "trans_map")
        private String trans_map;

        @JSONField(name = "is_employee")
        private String is_employee;

        @JSONField(name = "is_credit_card_user")
        private String is_credit_card_user;

        @JSONField(name = "is_debit_card_user")
        private String is_debit_card_user;

        public String getReturn_code() {
            return return_code;
        }

        public void setReturn_code(String return_code) {
            this.return_code = return_code;
        }

        public String getReturn_msg() {
            return return_msg;
        }

        public void setReturn_msg(String return_msg) {
            this.return_msg = return_msg;
        }

        public String getTrans_map() {
            return trans_map;
        }

        public void setTrans_map(String trans_map) {
            this.trans_map = trans_map;
        }

        public String getIs_employee() {
            return is_employee;
        }

        public void setIs_employee(String is_employee) {
            this.is_employee = is_employee;
        }

        public String getIs_credit_card_user() {
            return is_credit_card_user;
        }

        public void setIs_credit_card_user(String is_credit_card_user) {
            this.is_credit_card_user = is_credit_card_user;
        }

        public String getIs_debit_card_user() {
            return is_debit_card_user;
        }

        public void setIs_debit_card_user(String is_debit_card_user) {
            this.is_debit_card_user = is_debit_card_user;
        }
    }
}
