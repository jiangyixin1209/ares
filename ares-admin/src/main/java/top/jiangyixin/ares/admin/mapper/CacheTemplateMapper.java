package top.jiangyixin.ares.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import top.jiangyixin.ares.admin.pojo.entity.CacheTemplate;

/**
 * key模板表 Mapper 接口
 * @version 1.0
 * @author jiangyixin
 * @date 2020/12/18 下午5:14
 */
public interface CacheTemplateMapper extends BaseMapper<CacheTemplate> {
	
	/**
	 * 根据条件查询
	 * @param page          分页信息
	 * @param key           关键字
	 * @return              CacheTemplate 列表
	 */
	IPage<CacheTemplate> listByKey(IPage<CacheTemplate> page, @Param("key") String key);
}
