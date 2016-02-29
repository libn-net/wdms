package com.thinkgem.jeesite.modules.spider;

import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public abstract class AEbSpider implements ISpider {

	@Override
	public CloseableHttpClient init() {
		// 创建一个HttpClient
		RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).build();
		CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
		return httpClient;
		
	}

	@Override
	public void determineTarget() {
		
	}

	@Override
	public void preFetch() {
		
	}

	@Override
	public void fetch() {
		
		CloseableHttpClient httpClient = init();
		determineTarget();
		preFetch();
		
		fetchTarget();
		
		afterFetch();
		preParseData();
		parseData();
		afterFetch();
		destory();
	}
	
	public abstract void fetchTarget();

	@Override
	public void afterFetch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preParseData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void parseData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterParseData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destory() {
		// TODO Auto-generated method stub
		
	}
	
//	public Map fetchTool(String url, String expression, );
	

}
