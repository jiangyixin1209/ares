package top.jiangyixin.ares.admin.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * CacheTemplate Param
 * @version 1.0
 * @author jiangyixin
 * @date 2020/12/21 上午10:05
 */
public class CacheTemplateDTO {
	
	@ApiModelProperty(value = "缓存模板id")
	@NotNull(message = "id不能为空", groups = {Update.class})
	private Long id;
	
	@ApiModelProperty(value = "缓存模板")
	@NotBlank(message = "缓存模板不能为空", groups = {Create.class, Update.class})
	private String keyTpl;
	
	@ApiModelProperty(value = "缓存模板描述")
	@NotBlank(message = "缓存模板描述不能为空", groups = {Create.class, Update.class})
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
		return "CacheTemplateDTO{" +
				"id=" + id +
				", keyTpl='" + keyTpl + '\'' +
				", description='" + description + '\'' +
				'}';
	}
	
	public interface Create{}
	public interface Update{}
}
