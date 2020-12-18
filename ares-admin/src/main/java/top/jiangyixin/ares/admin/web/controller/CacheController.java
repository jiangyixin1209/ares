package top.jiangyixin.ares.admin.web.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Cache Controller
 * @version 1.0
 * @author jiangyixin
 * @date 2020/12/18 下午5:29
 */
@RestController
@RequestMapping("/cache")
@Api(tags = "缓存操作")
public class CacheController {
	
	@GetMapping("/get")
	public String getCache(String key, String param) {
		return "";
	}
	
	@PostMapping("/remove")
	public String removeCache(String key, String param) {
		return "";
	}
}
