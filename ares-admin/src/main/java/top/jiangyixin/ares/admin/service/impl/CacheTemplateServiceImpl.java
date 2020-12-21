package top.jiangyixin.ares.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.jiangyixin.ares.admin.common.R;
import top.jiangyixin.ares.admin.pojo.entity.CacheTemplate;
import top.jiangyixin.ares.admin.mapper.CacheTemplateMapper;
import top.jiangyixin.ares.admin.pojo.dto.CacheTemplateDTO;
import top.jiangyixin.ares.admin.pojo.vo.CacheTemplateVO;
import top.jiangyixin.ares.admin.service.CacheTemplateService;

import java.util.*;

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
	
	@Override
	public R<String> update(CacheTemplateDTO cacheTemplateDTO) {
		CacheTemplate tpl = this.baseMapper.selectOne(
				new QueryWrapper<CacheTemplate>()
						.eq("key_tpl", cacheTemplateDTO.getKeyTpl())
						.ne("id", cacheTemplateDTO.getId())
		);
		if (tpl != null) {
			return new R<>(500, "此模板已存在");
		}
		CacheTemplate cacheTemplate = new CacheTemplate();
		BeanUtils.copyProperties(cacheTemplateDTO, cacheTemplate);
		Date now = new Date();
		cacheTemplate.setGmtModified(now);
		boolean result = this.saveOrUpdate(cacheTemplate);
		return result ? R.SUCCESS : R.FAIL;
	}
	
	@Override
	public R<Map<String, ?>> list(String key, Integer page, Integer limit) {
		IPage<CacheTemplate> result = this.baseMapper.listByKey(new Page<>(page, limit), key);
		List<CacheTemplate> records = result.getRecords();
		List<CacheTemplateVO> voList = new ArrayList<>(records.size());
		for (CacheTemplate cacheTemplate : records) {
			CacheTemplateVO cacheTemplateVO = new CacheTemplateVO();
			BeanUtils.copyProperties(cacheTemplate, cacheTemplateVO);
		    voList.add(cacheTemplateVO);
		}
		Map<String, Object> data = new HashMap<>(5);
		data.put("total", result.getTotal());
		data.put("current", result.getCurrent());
		data.put("pages", result.getPages());
		data.put("limit", result.getSize());
		data.put("data", voList);
		return new R<>(200, "成功", data);
	}
}
