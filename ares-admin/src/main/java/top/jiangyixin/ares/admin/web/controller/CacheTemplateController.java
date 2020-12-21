package top.jiangyixin.ares.admin.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.jiangyixin.ares.admin.common.R;
import top.jiangyixin.ares.admin.pojo.dto.CacheTemplateDTO;
import top.jiangyixin.ares.admin.service.CacheTemplateService;

import java.util.Map;

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
	public R<String> create(
			@Validated(CacheTemplateDTO.Create.class) CacheTemplateDTO cacheTemplateDTO) {
		return cacheTemplateService.create(cacheTemplateDTO);
	}
	
	@PostMapping("/update")
	@ApiOperation("更新缓存模板")
	public R<String> update(
			@Validated(CacheTemplateDTO.Update.class) CacheTemplateDTO cacheTemplateDTO
	) {
		return cacheTemplateService.update(cacheTemplateDTO);
		
	}
	
	@PostMapping("/delete/{id}")
	@ApiOperation("删除缓存模板")
	public R<String> delete(@PathVariable("id") Long id) {
		boolean result = cacheTemplateService.removeById(id);
		return result ? R.SUCCESS : R.FAIL;
	}
	
	@PostMapping("/list")
	@ApiOperation("查询缓存模板")
	public R<Map<String, ?>> list(String key, Integer page, Integer limit) {
		return cacheTemplateService.list(key, page, limit);
	}
	
}
