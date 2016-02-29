/**
 * 
 */
package com.thinkgem.jeesite.modules.gtg.entity;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 货比货-接口信息Entity
 * @author libn
 * @version 2015-08-04
 */
public class GtgInterface extends DataEntity<GtgInterface> {
	
	private static final long serialVersionUID = 1L;
	private GtgEb eb;		// 电商id
	private String name;		// 接口名称
	private String uri;		// 接口地址
	private String rstDp;		// 返回数据处理的表达式
	
	public GtgInterface() {
		super();
	}

	public GtgInterface(String id){
		super(id);
	}

	@Length(min=1, max=255, message="接口名称长度必须介于 1 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=2000, message="接口地址长度必须介于 1 和 2000 之间")
	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
	
	@Length(min=0, max=255, message="返回数据处理的表达式长度必须介于 0 和 255 之间")
	public String getRstDp() {
		return rstDp;
	}

	public void setRstDp(String rstDp) {
		this.rstDp = rstDp;
	}

	@JsonIgnore
	public GtgEb getEb() {
		return eb;
	}

	public void setEb(GtgEb eb) {
		this.eb = eb;
	}
	
}