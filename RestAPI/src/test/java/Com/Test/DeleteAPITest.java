package Com.Test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Com.client.restClient;
import Com.restApi.testBase;
import Com.util.TestUtil;

public class DeleteAPITest extends testBase {

	testBase testbase;
	String serviceUrl;
	String url;
	String apiUrl;
	restClient restclient;
	CloseableHttpResponse closeableHttpResponse;

	@BeforeMethod
	public void SetUp() throws ClientProtocolException, IOException, JSONException {
		testbase = new testBase();

		serviceUrl = prop.getProperty("URL");
		apiUrl = prop.getProperty("serviceURL");
		// https://reqres.in/api/users
		url = serviceUrl + apiUrl;

	}

	@Test
	public void deleteAPITest() throws ClientProtocolException, IOException, JSONException {

		restclient = new restClient();
		closeableHttpResponse = restclient.delete(url);

		// a. Status Code
		int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("StatusCode: " + statuscode);

		Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_204, "Status code is not 204");

		/*// b. JSON String
		String ResponseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");

		JSONObject responseJson = new JSONObject(ResponseString);
		System.out.println("Response JSON from API" + responseJson);*/

	}

}
