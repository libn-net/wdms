package com.thinkgem.jeesite.modules.spider;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

public class SpiderConstants {
	
	public static final Header[] headers = new Header[]{
			new BasicHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
			, new BasicHeader("Accept-Encoding", "gzip, deflate, sdch")
			, new BasicHeader("Accept-Language", "zh-CN,zh;q=0.8")
			, new BasicHeader("Cache-Control", "max-age=0")
			, new BasicHeader("Connection", "keep-alive")
			, new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.155 Safari/537.36")
	};
	
}
