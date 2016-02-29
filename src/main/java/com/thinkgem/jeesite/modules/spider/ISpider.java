package com.thinkgem.jeesite.modules.spider;

import org.apache.http.impl.client.CloseableHttpClient;

public interface ISpider {
	
	public CloseableHttpClient init();
	
	public void determineTarget();
	
	public void preFetch();
	
	public void fetch();
	
	public void afterFetch();
	
	public void preParseData();
	
	public void parseData();
	
	public void afterParseData();
	
	public void destory();

	void preFetch(String keyword);

	void parseData(String body);

	void fetchTarget(String keyword);
}
