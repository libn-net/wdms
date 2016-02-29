/**
 * 
 */
package com.thinkgem.jeesite.modules.gtg.web;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.gtg.entity.GtgGoods;
import com.thinkgem.jeesite.modules.spider.JdSpider;
import com.thinkgem.jeesite.modules.spider.SnSpider;

/**
 * 货比货-搜索Controller
 * 
 * @author libn
 * @version 2015-08-17
 */
@Controller
@RequestMapping(value = "${adminPath}/gtg/search")
public class GtgSearchController extends BaseController {
	
	private static final String INIT_PAGE = "modules/gtg/gtgSearchForm";
	
	@Autowired
	private JdSpider jdSpider;
	
	@Autowired
	private SnSpider snSpider;

	@RequestMapping(value = { "", "index" })
	public String index(String keyword, Model model) throws IOException {
		
		// 无关键字不进行查询
		if (keyword == null || keyword.isEmpty()) {
			return INIT_PAGE;
		}
		
		List<GtgGoods> jdGoodsList = jdSpider.fetch(keyword);
		List<GtgGoods> snGoodsList = snSpider.fetch(keyword);
		
		model.addAttribute("jdGoods", jdGoodsList);
		model.addAttribute("snGoods", snGoodsList);
		
		model.addAttribute("keyword", keyword);

		return INIT_PAGE;
	}

}