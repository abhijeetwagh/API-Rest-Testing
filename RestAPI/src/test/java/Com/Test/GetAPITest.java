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

public class GetAPITest extends testBase {

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

	@Test(priority = 1)
	public void getAPITestWithoutHeaders() throws ClientProtocolException, IOException, JSONException {

		restclient = new restClient();
		closeableHttpResponse = restclient.get(url);

		// a. Status Code
		int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("StatusCode: " + statuscode);

		Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200, "Status code is not 200");

		// b. JSON String
		String ResponseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");

		JSONObject responseJson = new JSONObject(ResponseString);
		System.out.println("Response JSON from API" + responseJson);

		// Single value assertion
		// Per_page
		String perPageValue = TestUtil.getValueByPath(responseJson, "/per_page");
		System.out.println("Value of per page: " + perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 3);

		// Total:
		String TotalValue = TestUtil.getValueByPath(responseJson, "/total");
		System.out.println("Value of total is: " + TotalValue);
		Assert.assertEquals(Integer.parseInt(TotalValue), 12);

		// Get the value from JSON ARRAY
		String last_name = TestUtil.getValueByPath(responseJson, "/data[0]/last_name");
		String id = TestUtil.getValueByPath(responseJson, "/data[0]/id");
		String avatar = TestUtil.getValueByPath(responseJson, "/data[0]/avatar");
		String first_name = TestUtil.getValueByPath(responseJson, "/data[0]/first_name");

		System.out.println(id);
		System.out.println(first_name);
		System.out.println(last_name);
		System.out.println(avatar);

		// c. all headers
		Header[] headerArray = closeableHttpResponse.getAllHeaders();

		HashMap<String, String> allheaders = new HashMap<String, String>();

		for (Header header : headerArray) {
			allheaders.put(header.getName(), header.getValue());
		}

		System.out.println("Headers Array-->" + allheaders);
	}

	@Test(priority = 2)
	public void getAPITestwitHeaders() throws ClientProtocolException, IOException, JSONException {

		restclient = new restClient();

		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("content-type", "application/json");
		/*
		 * headerMap.put("username", "abhi"); headerMap.put("password", "123");
		 * headerMap.put("token", "123456");
		 */

		closeableHttpResponse = restclient.get(url);

		// a. Status Code
		int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("StatusCode: " + statuscode);

		Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200, "Status code is not 200");

		// b. JSON String
		String ResponseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");

		JSONObject responseJson = new JSONObject(ResponseString);
		System.out.println("Response JSON from API" + responseJson);

		// Single value assertion
		// Per_page
		String perPageValue = TestUtil.getValueByPath(responseJson, "/per_page");
		System.out.println("Value of per page: " + perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 3);

		// Total:
		String TotalValue = TestUtil.getValueByPath(responseJson, "/total");
		System.out.println("Value of total is: " + TotalValue);
		Assert.assertEquals(Integer.parseInt(TotalValue), 12);

		// Get the value from JSON ARRAY
		String last_name = TestUtil.getValueByPath(responseJson, "/data[0]/last_name");
		String id = TestUtil.getValueByPath(responseJson, "/data[0]/id");
		String avatar = TestUtil.getValueByPath(responseJson, "/data[0]/avatar");
		String first_name = TestUtil.getValueByPath(responseJson, "/data[0]/first_name");

		System.out.println(id);
		System.out.println(first_name);
		System.out.println(last_name);
		System.out.println(avatar);

		// c. all headers
		Header[] headerArray = closeableHttpResponse.getAllHeaders();

		HashMap<String, String> allheaders = new HashMap<String, String>();

		for (Header header : headerArray) {
			allheaders.put(header.getName(), header.getValue());
		}

		System.out.println("Headers Array-->" + allheaders);
	}
}
