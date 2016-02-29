/**
 * 
 */
package com.thinkgem.jeesite.modules.gtg.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.gtg.entity.GtgInterface;
import com.thinkgem.jeesite.modules.gtg.dao.GtgInterfaceDao;

/**
 * 货比货-接口信息Service
 * @author libn
 * @version 2015-08-04
 */
@Service
@Transactional(readOnly = true)
public class GtgInterfaceService extends CrudService<GtgInterfaceDao, GtgInterface> {

	public GtgInterface get(String id) {
		return super.get(id);
	}
	
	public List<GtgInterface> findList(GtgInterface gtgInterface) {
		return super.findList(gtgInterface);
	}
	
	public Page<GtgInterface> findPage(Page<GtgInterface> page, GtgInterface gtgInterface) {
		return super.findPage(page, gtgInterface);
	}
	
	@Transactional(readOnly = false)
	public void save(GtgInterface gtgInterface) {
		super.save(gtgInterface);
	}
	
	@Transactional(readOnly = false)
	public void delete(GtgInterface gtgInterface) {
		super.delete(gtgInterface);
	}
	
}