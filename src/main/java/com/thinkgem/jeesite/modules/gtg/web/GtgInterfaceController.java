/**
 * 
 */
package com.thinkgem.jeesite.modules.gtg.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.gtg.entity.GtgInterface;
import com.thinkgem.jeesite.modules.gtg.service.GtgInterfaceService;

/**
 * 货比货-接口信息Controller
 * @author libn
 * @version 2015-08-04
 */
@Controller
@RequestMapping(value = "${adminPath}/gtg/gtgInterface")
public class GtgInterfaceController extends BaseController {

	@Autowired
	private GtgInterfaceService gtgInterfaceService;
	
	@ModelAttribute
	public GtgInterface get(@RequestParam(required=false) String id) {
		GtgInterface entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = gtgInterfaceService.get(id);
		}
		if (entity == null){
			entity = new GtgInterface();
		}
		return entity;
	}
	
	@RequiresPermissions("gtg:gtgInterface:view")
	@RequestMapping(value = {"index"})
	public String index(Model model) {
		return "modules/gtg/gtgInterfaceIndex";
	}
	
	@RequiresPermissions("gtg:gtgInterface:view")
	@RequestMapping(value = {"list", ""})
	public String list(GtgInterface gtgInterface, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GtgInterface> page = gtgInterfaceService.findPage(new Page<GtgInterface>(request, response), gtgInterface); 
		model.addAttribute("page", page);
		return "modules/gtg/gtgInterfaceList";
	}

	@RequiresPermissions("gtg:gtgInterface:view")
	@RequestMapping(value = "form")
	public String form(GtgInterface gtgInterface, Model model) {
		model.addAttribute("gtgInterface", gtgInterface);
		return "modules/gtg/gtgInterfaceForm";
	}

	@RequiresPermissions("gtg:gtgInterface:edit")
	@RequestMapping(value = "save")
	public String save(GtgInterface gtgInterface, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gtgInterface)){
			return form(gtgInterface, model);
		}
		gtgInterfaceService.save(gtgInterface);
		addMessage(redirectAttributes, "保存接口成功");
		return "redirect:"+Global.getAdminPath()+"/gtg/gtgInterface/?repage";
	}
	
	@RequiresPermissions("gtg:gtgInterface:edit")
	@RequestMapping(value = "delete")
	public String delete(GtgInterface gtgInterface, RedirectAttributes redirectAttributes) {
		gtgInterfaceService.delete(gtgInterface);
		addMessage(redirectAttributes, "删除接口成功");
		return "redirect:"+Global.getAdminPath()+"/gtg/gtgInterface/?repage";
	}

}