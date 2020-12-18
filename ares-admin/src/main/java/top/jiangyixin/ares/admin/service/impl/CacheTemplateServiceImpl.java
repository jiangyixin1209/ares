package top.jiangyixin.ares.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.jiangyixin.ares.admin.entity.CacheTemplate;
import top.jiangyixin.ares.admin.mapper.CacheTemplateMapper;
import top.jiangyixin.ares.admin.service.CacheTemplateService;

/**
 * key模板表 Service 接口实现类
 * @version 1.0
 * @author jiangyixin
 * @date 2020/12/18 下午5:16
 */
@Service
public class CacheTemplateServiceImpl extends ServiceImpl<CacheTemplateMapper, CacheTemplate>
		implements CacheTemplateService {
}
