package top.jiangyixin.ares.admin.entity;

import java.util.Date;

/**
 * key模板表
 * @version 1.0
 * @author jiangyixin
 * @date 2020/12/18 下午5:11
 */
public class CacheTemplate {
	private Long id;
	private String key;
	private String desc;
	private Date gmtCreate;
	private Date gmtModified;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public Date getGmtCreate() {
		return gmtCreate;
	}
	
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	
	public Date getGmtModified() {
		return gmtModified;
	}
	
	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
	
	@Override
	public String toString() {
		return "CacheTemplate{" +
				"id=" + id +
				", key='" + key + '\'' +
				", desc='" + desc + '\'' +
				", gmtCreate=" + gmtCreate +
				", gmtModified=" + gmtModified +
				'}';
	}
}
