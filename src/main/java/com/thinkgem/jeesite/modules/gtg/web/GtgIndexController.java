/**
 * 
 */
package com.thinkgem.jeesite.modules.gtg.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.web.BaseController;

/**
 * 货比货-主页信息Controller
 * 
 * @author libn
 * @version 2015-08-04
 */
@Controller
@RequestMapping(value = "${adminPath}/gtg/index")
public class GtgIndexController extends BaseController {

	@RequestMapping(value = { "index" })
	public String index(Model model) {
		return "modules/gtg/gtgIndexForm";
	}

	public static void main(String[] args) {
		String appKey = "250cf9570b0c2e804dc31e346c0d017d";
		String appSecret = "ecd8920882de530afb7feb80464e588f";
		
		
	}

}