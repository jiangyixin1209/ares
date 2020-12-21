package top.jiangyixin.ares.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.jiangyixin.ares.admin.common.R;
import top.jiangyixin.ares.admin.pojo.entity.CacheTemplate;
import top.jiangyixin.ares.admin.mapper.CacheTemplateMapper;
import top.jiangyixin.ares.admin.pojo.dto.CacheTemplateDTO;
import top.jiangyixin.ares.admin.service.CacheTemplateService;

import java.util.Date;

/**
 * key模板表 Service 接口实现类
 * @version 1.0
 * @author jiangyixin
 * @date 2020/12/18 下午5:16
 */
@Service
public class CacheTemplateServiceImpl extends ServiceImpl<CacheTemplateMapper, CacheTemplate>
		implements CacheTemplateService {
	
	@Override
	public R<String> create(CacheTemplateDTO cacheTemplateDTO) {
		CacheTemplate tpl = this.baseMapper.selectOne(
				new QueryWrapper<CacheTemplate>()
						.eq("key_tpl", cacheTemplateDTO.getKeyTpl()));
		if (tpl != null) {
			return new R<>(500, "此模板已存在");
		}
		CacheTemplate cacheTemplate = new CacheTemplate();
		BeanUtils.copyProperties(cacheTemplateDTO, cacheTemplate);
		Date now = new Date();
		cacheTemplate.setGmtCreate(now);
		cacheTemplate.setGmtModified(now);
		boolean result = this.save(cacheTemplate);
		return result ? R.SUCCESS : R.FAIL;
	}
}
