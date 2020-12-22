package top.jiangyixin.ares.admin.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.jiangyixin.ares.admin.common.R;
import top.jiangyixin.ares.admin.util.JsonUtil;
import top.jiangyixin.ares.admin.pojo.vo.CacheInfoVO;
import top.jiangyixin.ares.core.AresCacheKey;
import top.jiangyixin.ares.core.AresCacheService;

import java.util.List;

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

	private final AresCacheService aresCacheService;

	@Autowired
	public CacheController(AresCacheService aresCacheService) {
		this.aresCacheService = aresCacheService;
	}

	@GetMapping("/get")
	@ApiOperation("获取缓存信息")
	public R<?> getCache(String key, String param) {
		String[] params = param != null ? param.split(",") : null;
		AresCacheKey cacheKey = new AresCacheKey(key, params);
		Object cacheValue = aresCacheService.get(cacheKey);
		if (cacheValue == null) {
			return new R<String>(500, "缓存数据为空");
		}
		int length = (cacheValue instanceof List) ? ((List<?>)cacheValue).size() : 1;
		CacheInfoVO cacheInfoVO = new CacheInfoVO();
		cacheInfoVO.setRealKey(cacheKey.getRealKey());
		cacheInfoVO.setJavaType(cacheValue.getClass().getName());
		cacheInfoVO.setLength(length);
		cacheInfoVO.setToString(cacheValue.toString());
		cacheInfoVO.setJson(JsonUtil.writeValueAsString(cacheValue));
		return new R<>(cacheInfoVO);
	}
	
	@PostMapping("/remove")
	@ApiOperation("移除缓存")
	public R<String> removeCache(String key, String param) {
		String[] params = param != null ? param.split(",") : null;
		AresCacheKey cacheKey = new AresCacheKey(key, params);
		boolean success = aresCacheService.delete(cacheKey);
		return success ? R.SUCCESS : R.FAIL;
	}


}
