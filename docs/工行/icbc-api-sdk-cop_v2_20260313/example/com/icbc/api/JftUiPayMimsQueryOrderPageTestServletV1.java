
public class JftUiPayMimsQueryOrderPageTestServletV1 extends HttpServlet {
	protected static final String MY_PRIVATE_KEY = "[需替换]应用方私钥，由应用方生成公私钥对，并将公钥上传至工行API开放平台";
	protected static final String APP_ID = "[需替换]APP的编号";
	protected static final String OUT_VENDOE_ID = "[需替换]OUT_VENDOE的编号";
	protected static final String AES_Key = "[需替换]应用方加密串，与工行API开放平台一致";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {

		try {
			
			UiIcbcClient client = new UiIcbcClient(APP_ID,"RSA2", MY_PRIVATE_KEY, IcbcConstants.CHARSET_UTF8,IcbcConstants.ENCRYPT_TYPE_AES,AES_Key);

			JftUiPayMimsQueryOrderPageRequestV1 request = new JftUiPayMimsQueryOrderPageRequestV1();
			
			request.setServiceUrl("[需替换]工行API网关地址/ui/jft/ui/pay/mims/queryOrderPage/V1");
			JftUiPayMimsQueryOrderPageRequestV1Biz bizContent = new JftUiPayMimsQueryOrderPageRequestV1Biz();
			bizContent.setAppId(APP_ID);
			bizContent.setOutVendorId(OUT_VENDOE_ID);
			request.setBizContent(bizContent);
			resp.setHeader("Content-Type", "text/html;charset="
					+ IcbcConstants.CHARSET_UTF8);
			PrintWriter out = resp.getWriter();
			System.out.println(client.buildPostForm(request));
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

