package com.thinkgem.jeesite.modules.spider;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.thinkgem.jeesite.modules.gtg.entity.GtgGoods;

@Component
public class JdSpider {

	// 搜索接口
	String searchUrl = "http://search.jd.com/Search?keyword=#keyword#&enc=utf-8";

	// 商品详情
	String deatailUrl = "http://s.club.jd.com/productpage/p-#goodsId#-s-1-t-0-p-0.html?callback=fetchJSON_comment";

	public List<GtgGoods> fetch(String keyword) {

		List<GtgGoods> gtgGoodsList = new ArrayList<GtgGoods>();

		// 创建一个HttpClient
		RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).build();
		CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();

		try {
			HttpGet get = new HttpGet();

			get.setURI(URI.create(searchUrl.replace("#keyword#", keyword)));

			get.setHeader("Content-Type", "text/xml;charset=UTF-8");
			get.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			get.setHeader("Accept-Encoding", "gzip, deflate, sdch");
			get.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
			get.setHeader("Cache-Control", "max-age=0");
			get.setHeader("Connection", "keep-alive");
			get.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.155 Safari/537.36");

			HttpResponse response = httpClient.execute(get);

//			System.out.println(EntityUtils.toString(response.getEntity()));
			Document doc = Jsoup.parse(EntityUtils.toString(response.getEntity()));
//			Document doc = Jsoup.parse(EntityUtils.toString(response.getEntity()));
			Elements goodsElements = doc.select("#plist li[sku!='']");
			
			if (goodsElements != null && goodsElements.size() > 0) {

				Iterator<Element> it = goodsElements.iterator();

				while (it.hasNext()) {
					Element e = it.next();

					String link = e.select(".p-name a").attr("href");

					GtgGoods g = new GtgGoods();
					g.setImgUrl(e.select(".p-img img").attr("data-lazyload"));
					g.setName(e.select(".p-name a").text());
					g.setPrice(e.select(".p-price").text());
					g.setDetailLink(link);

					// 详细页面内容抓取
//					HttpGet getDetail = new HttpGet(
//							deatailUrl.replace("#goodsId#", link.replace("//item.jd.com/", "").replace(".html", "")));
//					CloseableHttpResponse responseDetail = httpClient.execute(getDetail);
//					String body = EntityUtils.toString(responseDetail.getEntity(), "UTF-8");
//					String jsonp = body.substring("fetchJSON_comment(".length(), body.length() - 1 - 1);
//
//					// 解析接口返回数据
//					JSONObject obj = JSON.parseObject(jsonp).getJSONObject("productCommentSummary");
//					if (obj != null) {
//						g.setCommentCount(obj.getString("commentCount"));// 评论总数
//						g.setGoodCount(obj.getString("goodCount"));// 好评
//						g.setGoodRateShow(obj.getString("goodRateShow"));
//						g.setGeneralCount(obj.getString("generalCount"));
//						g.setGeneralRateShow(obj.getString("generalRateShow"));
//						g.setPoorCount(obj.getString("poorCount"));
//						g.setPoorRateShow(obj.getString("poorRateShow"));
//					}

					gtgGoodsList.add(g);
				}
			}
			
			httpClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return gtgGoodsList;
	}
	
}
