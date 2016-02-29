/**
 * 
 */
package com.thinkgem.jeesite.modules.gtg.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 货比货-电商信息Entity
 * @author libn
 * @version 2015-08-04
 */
public class GtgEb extends DataEntity<GtgEb> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 电商名称
	private String www;		// 网址
	
	public GtgEb() {
		super();
	}

	public GtgEb(String id){
		super(id);
	}

	@Length(min=1, max=255, message="电商名称长度必须介于 1 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=255, message="网址长度必须介于 1 和 255 之间")
	public String getWww() {
		return www;
	}

	public void setWww(String www) {
		this.www = www;
	}
	
}