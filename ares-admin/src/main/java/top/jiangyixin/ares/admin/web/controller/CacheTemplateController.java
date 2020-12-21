package top.jiangyixin.ares.admin.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.jiangyixin.ares.admin.common.R;
import top.jiangyixin.ares.admin.pojo.entity.CacheTemplate;
import top.jiangyixin.ares.admin.pojo.dto.CacheTemplateDTO;
import top.jiangyixin.ares.admin.service.CacheTemplateService;

import java.util.Date;

/**
 * CacheTemplate Controller
 * @version 1.0
 * @author jiangyixin
 * @date 2020/12/18 下午5:22
 */
@RestController()
@RequestMapping("/cache_template")
@Api(tags = "缓存Key模板操作")
public class CacheTemplateController {
	
	private final CacheTemplateService cacheTemplateService;
	
	@Autowired
	public CacheTemplateController(CacheTemplateService cacheTemplateService) {
		this.cacheTemplateService = cacheTemplateService;
	}
	
	@PostMapping("/create")
	@ApiOperation("创建缓存模板")
	public R<String> create(@Validated CacheTemplateDTO cacheTemplateDTO) {
		return cacheTemplateService.create(cacheTemplateDTO);
	}
	
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		boolean result = cacheTemplateService.removeById(id);
		return "";
	}
}
