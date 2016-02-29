package com.thinkgem.jeesite.modules.spider;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.modules.gtg.entity.GtgGoods;

@Component
public class SnSpider {
	
	// 苏宁地理位置接口
	static String ipservice = "http://ipservice.suning.com/ipQuery.do";

	// 搜索接口
	String searchUrl = "http://search.suning.com/#keyword#/cityId=#cityId#";
	
	// 商品详情
	String deatailUrl = "http://product.suning.com/#goodsId#.html";

	// 评价接口
	String reviewsUrl = "http://review.suning.com/ajax/review_satisfy/general-000000000#goodsId#------satisfy.htm";

	public List<GtgGoods> fetch(String keyword) {

		List<GtgGoods> gtgGoodsList = new ArrayList<GtgGoods>();
		
		List<Header> defaultHeaders = new ArrayList<Header>();
		defaultHeaders.add(new BasicHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8"));
		defaultHeaders.add(new BasicHeader("Accept-Encoding", "gzip, deflate, sdch"));
		defaultHeaders.add(new BasicHeader("Accept-Language", "zh-CN,zh;q=0.8"));
		defaultHeaders.add(new BasicHeader("Cache-Control", "max-age=0"));
		defaultHeaders.add(new BasicHeader("Connection", "keep-alive"));
		defaultHeaders.add(new BasicHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.155 Safari/537.36"));
		

		// 创建一个HttpClient
		RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).build();
		CookieStore cookieStore = new BasicCookieStore();
		CloseableHttpClient httpClient = HttpClients.custom()
				.setDefaultRequestConfig(requestConfig)
				.setDefaultCookieStore(cookieStore)
//				.setDefaultHeaders(defaultHeaders)
				.build();

		JSONObject obj = null;
		String jsonp = null;
		try {
			
			// 判断地理位置，取得城市id
			String cityId = null;
			HttpGet ipGet = new HttpGet(ipservice);
			CloseableHttpResponse ipResponse = httpClient.execute(ipGet);
			String ipJsonp = EntityUtils.toString(ipResponse.getEntity(), "UTF-8");
			cityId = JSON.parseObject(ipJsonp).getString("cityCommerceId");
			
			HttpGet get = new HttpGet();
			
			get.setHeader("Content-Type", "text/xml;charset=UTF-8");
			get.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			get.setHeader("Accept-Encoding", "gzip, deflate, sdch");
			get.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
			get.setHeader("Cache-Control", "max-age=0");
			get.setHeader("Connection", "keep-alive");
			get.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.155 Safari/537.36");

			get.setURI(URI.create(searchUrl.replace("#keyword#", keyword).replace("#cityId#", cityId)));

			CloseableHttpResponse response = httpClient.execute(get);

			Document doc = Jsoup.parse(response.getEntity().getContent(), "UTF-8", "http://www.suning.com/");
			Elements goodsElements = doc.select(".grid li");

			if (goodsElements != null && goodsElements.size() > 0) {

				Iterator<Element> it = goodsElements.iterator();

				while (it.hasNext()) {
					Element e = it.next();

					String link = e.select(".i-name a").attr("href");

					GtgGoods g = new GtgGoods();
					g.setImgUrl("http://image2.suning.cn/b2c/catentries/000000000"
							+link.replace("http://product.suning.com/", "").replace(".html", "")+"_1_200x200.jpg");
					g.setName(e.select(".i-name a").text());
					g.setPrice(e.select(".i-price p").text());
					g.setDetailLink(link);

					// 详细页面内容抓取
//					HttpGet getDetail = new HttpGet(
//							reviewsUrl.replace("#goodsId#", link.replace("http://product.suning.com/", "").replace(".html", "")));
//					CloseableHttpResponse responseDetail = httpClient.execute(getDetail);
//					 jsonp = EntityUtils.toString(responseDetail.getEntity(), "UTF-8");
//
//					// 解析接口返回数据
//					 obj = JSON.parseObject(
//							jsonp.substring("satisfy(".length(), jsonp.length() - 1)).getJSONArray("reviewCounts").getJSONObject(0);	
//					if (obj != null) {
//						g.setCommentCount(obj.getString("totalCount"));// 评论总数
//						g.setGoodCount(String.valueOf(obj.getInteger("totalCount") - (
//								obj.getInteger("oneStarCount") + obj.getInteger("twoStarCount") + obj.getInteger("threeStarCount"))));// 好评
////						g.setGoodRateShow(obj.getString("goodRateShow"));
//						g.setGeneralCount(String.valueOf(obj.getInteger("twoStarCount") + obj.getInteger("threeStarCount")));
////						g.setGeneralRateShow(obj.getString("generalRateShow"));
//						g.setPoorCount(obj.getString("oneStarCount"));
////						g.setPoorRateShow(obj.getString("poorRateShow"));
//					}
//					Thread.sleep(1000);
					gtgGoodsList.add(g);
				}
			}
			
			httpClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return gtgGoodsList;
	}
	
	public static void main(String[] args) {
		SnSpider s = new SnSpider();	
		System.out.println(s.fetch("运动耳机").size());
	}
}
