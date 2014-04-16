package demo.vicshady.coreshopping.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;

import demo.vicshady.coreshopping.BuildConfig;
import android.util.Log;

public class RestClient {


	  private static byte[] sBuffer = new byte[512];

	  public static String connect(String urlString)
	  {
	    String str = null;
	    BasicHttpParams basicHttpParams = new BasicHttpParams();
	    HttpConnectionParams.setConnectionTimeout(basicHttpParams, 7000);
	    HttpConnectionParams.setSoTimeout(basicHttpParams, 15000);
	    DefaultHttpClient defaultHttpClient = new DefaultHttpClient(basicHttpParams);
	    HttpGet httpGet = new HttpGet(urlString);
	    httpGet.setHeader("Authorization", "Basic " + Base64.encodeBytes("kada_user:JasSk171".getBytes()));
		try {
			HttpEntity httpEntity = defaultHttpClient.execute(httpGet).getEntity();
			if (httpEntity != null) {
				InputStream inputStream = httpEntity.getContent();
				str = convertStreamToString(inputStream);
				inputStream.close();
			}
		} catch (Exception localException) {
		}
		if(BuildConfig.DEBUG)
		{
			Log.i("REQUEST", urlString);
			Log.i("RESPONSE", str);	
		}
		return str;
	  }

	  private static String convertStreamToString(InputStream inputStream)
	  {
		  BufferedReader br = null;
			StringBuilder sb = new StringBuilder();
	 
			String line;
			try {
	 
				br = new BufferedReader(new InputStreamReader(inputStream));
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
	 
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
	 
			return sb.toString();
	  }
}
