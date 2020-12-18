package top.jiangyixin.ares.admin.web.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.jiangyixin.ares.admin.service.CacheTemplateService;

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
	
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		boolean result = cacheTemplateService.removeById(id);
		return "";
	}
}
