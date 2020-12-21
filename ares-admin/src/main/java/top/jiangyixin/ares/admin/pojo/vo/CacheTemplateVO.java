package top.jiangyixin.ares.admin.pojo.vo;

/**
 * CacheTemplate VO
 * @version 1.0
 * @author jiangyixin
 * @date 2020/12/21 下午2:47
 */
public class CacheTemplateVO {
	
	private Long id;
	private String keyTpl;
	private String description;
	
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
	
	@Override
	public String toString() {
		return "CacheTemplateVO{" +
				"id=" + id +
				", keyTpl='" + keyTpl + '\'' +
				", description='" + description + '\'' +
				'}';
	}
}
