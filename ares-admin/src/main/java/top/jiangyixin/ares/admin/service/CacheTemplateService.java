package top.jiangyixin.ares.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.jiangyixin.ares.admin.common.R;
import top.jiangyixin.ares.admin.pojo.entity.CacheTemplate;
import top.jiangyixin.ares.admin.pojo.dto.CacheTemplateDTO;

/**
 * CacheTemplate Service 接口
 * @version 1.0
 * @author jiangyixin
 * @date 2020/12/18 下午5:15
 */
public interface CacheTemplateService extends IService<CacheTemplate> {
	
	/**
	 * 创建 CacheTemplate
	 * @param cacheTemplateDTO    请求参数
	 * @return                      通用返回
	 */
	R<String> create(CacheTemplateDTO cacheTemplateDTO);
}
