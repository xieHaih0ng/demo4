package com.newer.domain;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 结合el-tree一起使用
 * @author pc
 *
 */
@Table(name="t_menu")
public class Menu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer pid;
	private String name;
	private String leaf;
	private String description;
	private String url;
	private Date intime;
	 
	//为叶子返回true，否则返回false用于与el-tree一起使用，实现懒加载
	public boolean isLeafBoolean() {
			return "1".equals(this.leaf)?true:false;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

	
	public String getLeaf() { return leaf; }
	 
	public void setLeaf(String leaf) {
		this.leaf = leaf;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getIntime() {
		return intime;
	}

	public void setIntime(Date intime) {
		this.intime = intime;
	}
	
	

}
