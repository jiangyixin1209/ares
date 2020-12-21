package top.jiangyixin.ares.admin.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * key模板表
 * @version 1.0
 * @author jiangyixin
 * @date 2020/12/18 下午5:11
 */
@TableName("ares_cache_template")
public class CacheTemplate {
	
	@TableId(type = IdType.AUTO)
	private Long id;
	private String keyTpl;
	private String description;
	private Date gmtCreate;
	private Date gmtModified;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getKeyTpl() {
		return keyTpl;
	}
	
	public void setKeyTpl(String keyTpl) {
		this.keyTpl = keyTpl;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
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
				", keyTpl='" + keyTpl + '\'' +
				", description='" + description + '\'' +
				", gmtCreate=" + gmtCreate +
				", gmtModified=" + gmtModified +
				'}';
	}
}
