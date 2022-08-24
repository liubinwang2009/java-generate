package ${package}.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.common.collect.Lists;
import ${package}.dao.${className}Mapper;
import ${package}.entity.${className};
import ${package}.entity.query.${className}Query;
import ${package}.service.${className}Service;
import ${package}.entity.PageBean;

/**
 * ${comment}
 * @author liubinwang
 *
 */
@Service
public class ${className}ServiceImpl implements ${className}Service{

    @Autowired
	private  ${className}Mapper $!{lowerName}Mapper;

	/**
     * 新增
     * @param $!{lowerName}
     * @return
     */
    @Override
	public Integer add(${className} $!{lowerName}) throws Exception {
		return $!{lowerName}Mapper.insertSelective($!{lowerName});
	}

	/**
     * 删除 
     * @param id
     * @return
    */
   	@Override
	public Integer deleteById(String id) {
        return $!{lowerName}Mapper.deleteById(id);
	}

	/**
     * 修改
     * @param $!{lowerName}
     * @return
     */
    @Override
	public Integer update(${className} $!{lowerName}) throws Exception {
        return $!{lowerName}Mapper.updateByPrimaryKeySelective($!{lowerName});
	}

	/**
     * 查询列表
     * @param $!{lowerName}Query
     * @return
     */
    @Override
	public PageBean<${className}> getList(${className}Query $!{lowerName}Query) {
		int count = $!{lowerName}Mapper.count($!{lowerName}Query);
		List<${className}> list = null;
		if (count == 0) {
			list = Lists.newArrayList();
		} else {
			list = $!{lowerName}Mapper.getList($!{lowerName}Query);
		}
		return new PageBean<${className}>(list, count);
	}

	/**
     * 根据ID查询
     * @param id
     * @return
     */
    @Override
	public ${className} getById(String id) {
        return $!{lowerName}Mapper.getById(id);
	}	
}