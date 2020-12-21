package top.jiangyixin.ares.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.jiangyixin.ares.admin.common.R;
import top.jiangyixin.ares.admin.pojo.entity.CacheTemplate;
import top.jiangyixin.ares.admin.pojo.dto.CacheTemplateDTO;

import java.util.Map;

/**
 * CacheTemplate Service 接口
 * @version 1.0
 * @author jiangyixin
 * @date 2020/12/18 下午5:15
 */
public interface CacheTemplateService extends IService<CacheTemplate> {
	
	/**
	 * 创建 CacheTemplate
	 * @param cacheTemplateDTO      请求参数
	 * @return                      通用返回
	 */
	R<String> create(CacheTemplateDTO cacheTemplateDTO);
	
	/**
	 * 更新 CacheTemplate
	 * @param cacheTemplateDTO      请求参数
	 * @return                      通用返回
	 */
	R<String> update(CacheTemplateDTO cacheTemplateDTO);
	
	/**
	 * 查询 CacheTemplate 列表
	 * @param key                   关键字
	 * @param page                  页数
	 * @param limit                 每页条数
	 * @return                      通用返回
	 */
	R<Map<String, ?>> list(String key, Integer page, Integer limit);
}
