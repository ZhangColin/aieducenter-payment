package com.icbc.api;

import com.icbc.api.IcbcApiException;
import com.icbc.api.IcbcConstants;
import com.icbc.api.UiIcbcClient;

public class OnlineCustContactManageRequestTestV1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            //UiIcbcClient中appid、prikey根据实际情况修改
            String APP_ID = "***************";
            String MY_PRIVATE_KEY = "-----请使用正确的APP私钥-----EvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCKgQTGORfOPv3HGSW5CdrJzRJw9KctjVkzTSgaxnqc/liqPGn1WgBdCXcZHqSM611l6JvC70HIhZjOLYjdGtQEgS+E5/tCEBNY54magVEMyxDzTMLtM2XkV0WOe/F0bb6juj0aPmjNYzWIMNeNb2kk/uOfwVYllHGqgznNM50vXkLnkK45qNSkTnX5zMPbzwrpDyG5BuG6kCK3M1zgqr3zWa4DPvFHSYLtc5+ufp9GCTWokIVTfgrUSIX4NWdjdOzII3FZAaTLyWgo4Ei+QieST3qI6D90pFt98LeaIyEhj14s76n5sfYn0On3Keomyr2PueaGe3UkLSeHutvGbA5RAgMBAAECggEBAIEeVf0VLIQxZRQ+9VPTRAvtCcwTx+ZHZ2jT/Rc5AQVqd+379Cuug7g4IrdgQba9lIx0lQDdW048fX94I6c8Y3rvlAUq3qlvoxm2gClxdMWgaWTnorDOwaZlVYrHMQmBZb/s1xmoJLuUSUt1fP11Z2f91uYPrufJw01amIAcJeUJc68FphZ2a/1QyP+AruY3ogt4i2yyjYFquzRQ6u/gxr4xaKZ1tqo8M7a74c7C+UgtYgb/Hlvv/7NTFYS7oDaDLrX5KCox8r2njwZVI7kB2chTYTBAqhTHSmnxH9WqJdovGR7eUCeLdEWQxXw+LSWIH27SdD3SRMqCUelieSkLGaECgYEAw5OmLdC020LmsQCjUiWu4zq9+Oz+OWrt0hE5dpl1f+95FX/5ut6ZGw+qPu29t+d8ovznyUgS1qXrf4QbtBC1+3JGOxAYiWjbb+vxJBlSeNtcGCIHJgDxomqLL8iCxuxeBizuq+MjVR/8w91nQ0Mb8hUXnlS5cYNSdiJJbLpdQXcCgYEAtUtt+67b5VG7JKyxgz9d5k1L3h+tXtmScLLMJX7WWZt1bxTTUfajeadZpm6bZMIctD2BFxvAGOoi7gR4eiky+Hh1VpXD8ewwQTBHfvUphy8rQfA1BrlzGnuQqiZCNet+KPqCQPAgfxpg0uFlbJSvaTMowWe7ol0lQjmc3ckqYHcCgYAx+gnuDTYaDYd71ajmdgCxyjH/aTgxnfR1yLcBaoePELfFWbWMHhPLyX38pK3lT78Wuz/TvJorxaxfIngd1GNchMATmbJbcrMYb5doXRwpDA0FkbR37qfQlB9LL5bTlFUWiObYf6totkrWFj6r/nsmqmMlr4rPyfoJfAt9QEZSUwKBgGK9eGNxsxZM026AGBZxtsSuKLoL6rMUMOvslb9+O2SGhQlBAG9Ns2+Ys/0vlLbl+hg4v0xEsvVe4DNef/eY5MfrY40K+LOUfhujHe2JvVqBWEALgfXtn0E3N21UdQ9E+Wt8NOEFZ82SIlX4qPLgcEENug7MXqdpr9dH5aXt4IS1AoGAHhC+v1mwNxteRidFDOzamC7On10uMlYp4JeCfcIyjcDStFXq7Uy4WUJMo7AL7PAiajmnZvhH6/ZnQdFLvf0IjcLO7OQPWCWnl0sCDT4if0S66Blx7mZTryGi4RXi7LqdpU9TPL0P1ET3QemEaLgnKoYullwcRsIvxNNRQ2d2jCs=";

            UiIcbcClient client=new UiIcbcClient(APP_ID, IcbcConstants.SIGN_TYPE_RSA2, MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8);
            OnlineCustContactManageRequestV1 requestV1=new OnlineCustContactManageRequestV1();
            //平台上注册的接口地址
            requestV1.setServiceUrl("https://ip:port/ui/online/cust/contact/manage/V1");
            OnlineCustContactManageRequestV1.OnlineCustContactManageRequestV1Biz bizContent = new OnlineCustContactManageRequestV1.OnlineCustContactManageRequestV1Biz();

            //根据实际情况修改以下参数
            bizContent.setQywxCorpId("ww413efbe0f94fdfff");
            bizContent.setInnerStruId("1234567890");
            bizContent.setUserId("001234567");
            bizContent.setReserved("备用字段");
            requestV1.setBizContent(bizContent);

            resp.setHeader("Content-Type", "text/html;charset="
                    + IcbcConstants.CHARSET_UTF8);
            PrintWriter out = resp.getWriter();

            out.write("<html>");
            out.write("<head>");
            out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset="
                    + IcbcConstants.CHARSET_UTF8 + "\">");
            out.write("</head>");
            out.write("<body>");
            out.write(client.buildPostForm(request));
            out.write("</body>");
            out.write("</html>");

        } catch (IcbcApiException e) {
            e.printStackTrace();
        }
    }

}
