package pay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayOpenPublicTemplateMessageIndustryModifyRequest;
import com.alipay.api.response.AlipayOpenPublicTemplateMessageIndustryModifyResponse;

public class AlipayTest {
private static final String APP_ID = "2016072900121033";
private static final String APP_PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMSnVuG0G9mkEsHK"
		+ "SMNFW9eWG5B2ON4ewwLaKYDbS0NREELfI72nDrNtEIohzUezj2CTkUy39uf2bsoi"
		+ "FuVM68EDKGprRbWsnnj5fnwrdlNLpSNZHJCRGLUkpPPiwBH9G+3zt3FP9zG4fp+S"
		+ "AVdCDS8yLYODIOtxgRIsFqyksLkrAgMBAAECgYAuBiP1StvBwf6J0DfbS55FBGKL"
		+ "GsAQbA6j2j8gYzJoHM67d9G7jx+HqpzWVzbd8w45QhAdbmigvTIMqJHHNItwBZnt"
		+ "63QsWBoI83/rNJ+sL63KV6ud1Uivg5FxhFNzsARKSN39xm3+wSPb+d9LPDBD2YCW"
		+ "x0H3Mggs67MmT8Rd0QJBAOnalpLfBKDT3Qbgqz7UxMtPD/6egInsEpJWYs5tJ8zU"
		+ "+OjzYZ7eszR2pZzhSjWYIwVEvovCUu6+oEZ97gCIEzcCQQDXRuRppBU34bD8UNxd"
		+ "cddqo42JmAqa8auuKgeWF/amC9WLWG4FFiNEISCM9G/MeVHYg6/FlfeuMuxoYf/a"
		+ "SqutAkAMt6JwokgyJtNiSF2HsTJyi/4mrp/24CuyYZhF+ZGshfhzUqkAZsQAGmOv"
		+ "QGE0wO1GoBJsr0irSxqUOszyonPJAkEAxb7xAbkfZH2EPcFpKszvu50Q3lszIrDr"
		+ "qsggv9azUkN8nMtWBIYZTvO/GrtOFt06OVB9nVnsca+FSTz78GC24QJAVxi3dzhu"
		+ "qL8jDsjSDaoSb7vYfE+6pFKlwzCzgklw57wOaV2TOaIzYLdH7zVJsl16YbXCOHDN"
		+ "lr9aCDbYrDlRCw==";

//"MIICXAIBAAKBgQDEp1bhtBvZpBLBykjDRVvXlhuQdjjeHsMC2imA20tDURBC3yO9"
//		+ "pw6zbRCKIc1Hs49gk5FMt/bn9m7KIhblTOvBAyhqa0W1rJ54+X58K3ZTS6UjWRyQ"
//		+ "kRi1JKTz4sAR/Rvt87dxT/cxuH6fkgFXQg0vMi2DgyDrcYESLBaspLC5KwIDAQAB"
//		+ "AoGALgYj9UrbwcH+idA320ueRQRiixrAEGwOo9o/IGMyaBzOu3fRu48fh6qc1lc2"
//		+ "3fMOOUIQHW5ooL0yDKiRxzSLcAWZ7et0LFgaCPN/6zSfrC+tylerndVIr4ORcYRT"
//		+ "c7AESkjd/cZt/sEj2/nfSzwwQ9mAlsdB9zIILOuzJk/EXdECQQDp2paS3wSg090G"
//		+ "4Ks+1MTLTw/+noCJ7BKSVmLObSfM1Pjo82Ge3rM0dqWc4Uo1mCMFRL6LwlLuvqBG"
//		+ "fe4AiBM3AkEA10bkaaQVN+Gw/FDcXXHXaqONiZgKmvGrrioHlhf2pgvVi1huBRYj"
//		+ "RCEgjPRvzHlR2IOvxZX3rjLsaGH/2kqrrQJADLeicKJIMibTYkhdh7Eycov+Jq6f"
//		+ "9uArsmGYRfmRrIX4c1KpAGbEABpjr0BhNMDtRqASbK9Iq0salDrM8qJzyQJBAMW+"
//		+ "8QG5H2R9hD3BaSrM77udEN5bMyKw66rIIL/Ws1JDfJzLVgSGGU7zvxq7ThbdOjlQ"
//		+ "fZ1Z7HGvhUk8+/BgtuECQFcYt3c4bqi/Iw7I0g2qEm+72HxPuqRSpcMws4JJcOe8"
//		+ "DmldkzmiM2C3R+81SbJdemG1wjhwzZa/Wgg22Kw5UQs=";
private static final String ALIPAY_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDIgHnOn7LLILlKETd6BFRJ0GqgS2Y3mn1wMQmyh9zEyWlz5p1zrahRahbXAfCfSqshSNfqOmAQzSHRVjCqjsAw1jyqrXaPdKBmr90DIpIxmIyKXv4GGAkPyJ/6FTFY99uhpiq0qadD/uSzQsefWo0aTvP/65zi3eof7TcZ32oWpwIDAQAB";

public static void main(String[] args) throws AlipayApiException {
	
	//
	//实例化客户端
	AlipayClient client = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do",APP_ID,APP_PRIVATE_KEY,"json","GBK",ALIPAY_PUBLIC_KEY);
	//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.open.public.template.message.industry.modify 
	AlipayOpenPublicTemplateMessageIndustryModifyRequest request = new AlipayOpenPublicTemplateMessageIndustryModifyRequest();
	//SDK已经封装掉了公共参数，这里只需要传入业务参数
	//此次只是参数展示，未进行字符串转义，实际情况下请转义
	request.setBizContent("{\"primary_industry_name\":\"IT科技/IT软件与服务\"}");
	AlipayOpenPublicTemplateMessageIndustryModifyResponse response = client.execute(request); 
	//调用成功，则处理业务逻辑
	if(response.isSuccess()){
	    //.....
	}
}
}
