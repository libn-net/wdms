/**
 * 
 */
package com.thinkgem.jeesite.modules.gtg.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.gtg.entity.GtgEb;
import com.thinkgem.jeesite.modules.gtg.service.GtgEbService;

/**
 * 货比货-电商信息Controller
 * @author libn
 * @version 2015-08-04
 */
@Controller
@RequestMapping(value = "${adminPath}/gtg/gtgEb")
public class GtgEbController extends BaseController {

	@Autowired
	private GtgEbService gtgEbService;
	
	@ModelAttribute
	public GtgEb get(@RequestParam(required=false) String id) {
		GtgEb entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = gtgEbService.get(id);
		}
		if (entity == null){
			entity = new GtgEb();
		}
		return entity;
	}
	
	@RequiresPermissions("gtg:gtgEb:view")
	@RequestMapping(value = {"list", ""})
	public String list(GtgEb gtgEb, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GtgEb> page = gtgEbService.findPage(new Page<GtgEb>(request, response), gtgEb); 
		model.addAttribute("page", page);
		return "modules/gtg/gtgEbList";
	}

	@RequiresPermissions("gtg:gtgEb:view")
	@RequestMapping(value = "form")
	public String form(GtgEb gtgEb, Model model) {
		model.addAttribute("gtgEb", gtgEb);
		return "modules/gtg/gtgEbForm";
	}

	@RequiresPermissions("gtg:gtgEb:edit")
	@RequestMapping(value = "save")
	public String save(GtgEb gtgEb, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gtgEb)){
			return form(gtgEb, model);
		}
		gtgEbService.save(gtgEb);
		addMessage(redirectAttributes, "保存电商成功");
		return "redirect:"+Global.getAdminPath()+"/gtg/gtgEb/?repage";
	}
	
	@RequiresPermissions("gtg:gtgEb:edit")
	@RequestMapping(value = "delete")
	public String delete(GtgEb gtgEb, RedirectAttributes redirectAttributes) {
		gtgEbService.delete(gtgEb);
		addMessage(redirectAttributes, "删除电商成功");
		return "redirect:"+Global.getAdminPath()+"/gtg/gtgEb/?repage";
	}
	
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData() {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		
		GtgEb gtgEb = new GtgEb();
		List<GtgEb> list = gtgEbService.findList(gtgEb);
		for (int i=0; i<list.size(); i++){
			GtgEb e = list.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", e.getId());
//			map.put("pId", e.getParentId());
//			map.put("pIds", e.getParentIds());
			map.put("name", e.getName());
			mapList.add(map);
		}
		return mapList;
	}

}