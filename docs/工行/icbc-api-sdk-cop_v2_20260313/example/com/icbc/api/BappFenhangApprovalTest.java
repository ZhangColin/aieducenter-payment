package com.icbc.api;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.BappFenhangApprovalRequestV1;
import com.icbc.api.response.BappFenhangApprovalResponseV1;

/**
 * @author KFZX-CHENWP5
 * @date 2022-7-28 11:28:23
 */
public class BappFenhangApprovalTest {

    //MY_PRIVATE_KEY 用于加密请求报文
    protected static final String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCpL9BnrwwXIdBi9Ls64EeTxW/g0hI9F/NK+5U/EHdnY+vWV0fcftxM2eE+UCetJ4jSIMlhtai959l+/eXXX7H1ckcWoej72to49RYm6U1f/FnzFgMja2voFGlK2PuWBuvKMYTeWG/IOoelCrkOEl7qFoFNxQSKzfceU2yTJR36/JYRXk9jSqYoj5o75qHGLGnQjXxF7nNeUr4sYVSMn2BrLs2r4xhW2LpS2phwfLlNYs4xlnT/TYSgeRR+7SZ4qoKZ4h/fYkfocnlW2EOYglsSgjFq9a9udkP2DsRasz8GXTB0dwOgJ55GSAezW+KJGbZQNfbPWrG+0fp7YKUN1T5dAgMBAAECggEAStIpcWEZkLVUih6/GneLUA+VvbBJfpkz8Co4w64NkDzBVIY+8oVr+dS1ZzBdi5cbTApR5RNIb8u0BRRV7ByW//ws8hZiidcsgNnqlQTCQjvAr99BPg3Ip9c3BXHWOXAQINgrcYVc0gkIafXHBzEapp2TS4hgzN3vdorgTmawzojhdWwqwUqS3ALylHUzXKdxxar32hWVFkBWW5Dbe4qf4r1JdAavpjj3eYJZ1aqOPBBGc6zsLlMC3cPngWUHr3PKuDyP5oxHRS+QMMv/40PevD2ZI0bgoCr9Nv1CoXI2yr9pc6Rnd0qtM0srl47m12niCNoDIkiltyWvYnjySqPb5QKBgQD024tAh1OErsNweq29KY80oy1toA+B8q/37fsAZmw/jWiqwqLg/IR3brdCd4xGIMeV0jqmObNim850QkO82JLJBiRm0bplLoieBVkYsceuEGnajPhMueI0DbyCuCW33eWFYTfwOyZFVP0h8BK69nfw4VimzsVCuLbrTivY4e7BQwKBgQCw4r8shkIVkqeU1vGmR73k3mddvG0IwhHL/qbaA9tjhiI+I8jKkb/SDqbTfqMqAcoXVF4LLU5NSrrieKGqYgeKsUsE9g2Wc4JOkKh9czCg6ASPNmNRB9bjL/oydDw2IGtD6rbPzxXjWitmoF2QRglgJm01CsUT3x1FiDLNrTy33wKBgQCjOsws+0nQ9NQy4oV5yf++fl1lbEOKRGfMZuhiDuVwxGsXEuOUCqKgH72pYJqxkV6b4ObaMzMDtIw/8oDHnrTTr/WKWZvhodDPTw9jJ/jOcACxAjLwqyZBvzqBBhOW7CT68qFg032i0JJjEnDaFD5G5WK86LCV1x+R463VSmBokwKBgQCGmlWd/SCmp+3sXlCPF0LSucdw1iUmp//A+yxe9NZq+rRuEu8F7zlIEKw0fsaM/cWaUTIrUTOnr3ldQyzKqkRQ7y9GPDMEzStYu83rgM99cnGdxWZ4T/3e7jB3WmpDShI2IZMxucVPN9uVAe2RDyqtRzCNT3++6mas9cKrLuvogwKBgQDkLK7WrEiEgzVtcgn9YY5foD1XDmHBdd3o2ikwGuB4LVn9GKb+yjkFmXWaqSCX0qJoDvur02EWDSC8LHxG04QfPiGO9Pbm9g9J8ErcC3kuchw6EXXT1Sgwh7gcXj708Ss9hauW2b7Vo3nXN0vp+THyq+ydQQxclkdk/kfdnL/TgQ==";

    //APIGW_PUBLIC_KEY 工行公钥，用于解密返回报文
    protected static final String APIGW_PUBLIC_KEY = "-----请使用正确的网关公钥-----D4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

    //APP_ID 工行API接口的ID
    protected static final String APP_ID = "110000000131576";

    public static void main(String[] args) throws IcbcApiException {
        BappFenhangApprovalRequestV1 requestV1 = new BappFenhangApprovalRequestV1();
        requestV1.setServiceUrl("https://ip:port/api/bapp/fenhang/approval/V1");
        BappFenhangApprovalRequestV1.BappFenHangApprovalRequestV1Biz biz = new BappFenhangApprovalRequestV1.BappFenHangApprovalRequestV1Biz();

        BappFenhangApprovalRequestV1.BappFenHangApprovalRequestV1Biz.Cosp cosp = new BappFenhangApprovalRequestV1.BappFenHangApprovalRequestV1Biz.Cosp();
        BappFenhangApprovalRequestV1.BappFenHangApprovalRequestV1Biz.Fhtopt fhtopt = new BappFenhangApprovalRequestV1.BappFenHangApprovalRequestV1Biz.Fhtopt();
        BappFenhangApprovalRequestV1.BappFenHangApprovalRequestV1Biz.Out out = new BappFenhangApprovalRequestV1.BappFenHangApprovalRequestV1Biz.Out();
        out.setAccname("accname");
        out.setBusseqno("busseqno");
        out.setCommlist("1");
        out.setMessagetyp("1");
        out.setPayobnkcode("89704932874012978");
        out.setPfdate("2022-7-28");
        out.setPfnote("this is a message");
        out.setPfsource("1");
        out.setPftell("555088846");
        out.setPftime("2022-7-28 14:42:32");
        out.setRetcode("0");
        out.setUniseqno("897fwi9832");
        fhtopt.setOut(out);
        cosp.setFhtopt(fhtopt);

        BappFenhangApprovalRequestV1.BappFenHangApprovalRequestV1Biz.Head head = new BappFenhangApprovalRequestV1.BappFenHangApprovalRequestV1Biz.Head();
        head.setCENTNO("200");
        head.setTRADECODE("6002");
        head.setTRANSID("WMA3009");
        cosp.setHead(head);
        BappFenhangApprovalRequestV1.BappFenHangApprovalRequestV1Biz.Tail tail = new BappFenhangApprovalRequestV1.BappFenHangApprovalRequestV1Biz.Tail();
        tail.setRetCode("retcode");
        tail.setRetReason("retreason");
        cosp.setTail(tail);

        biz.setCosp(cosp);
        requestV1.setBizContent(biz);
        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
        BappFenhangApprovalResponseV1 response = client.execute(requestV1);

        System.out.println(JSONObject.toJSONString(response));


    }
}
