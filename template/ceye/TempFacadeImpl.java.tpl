package ${package}.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ${package}.entity.${className};
import ${package}.entity.query.${className}Query;
import ${package}.facade.${className}Facade;
import ${package}.service.${className}Service;
import ${package}.entity.PageBean;
/**
 * ${comment}
 * @author liubinwang
 *
 */
@Component("$!{lowerName}FacadeImpl")
public class ${className}FacadeImpl implements ${className}Facade{

	
    @Autowired
	private  ${className}Service $!{lowerName}Service;

	/**
     * 新增
     * @param $!{lowerName}
     * @return
     */
    @Override
	public Integer add(${className} $!{lowerName}) throws Exception {	
		return $!{lowerName}Service.add($!{lowerName});
	}

	/**
     * 删除 
     * @param id
     * @return
    */
    @Override
	public Integer deleteById(String id) {
        return $!{lowerName}Service.deleteById(id);
	}

	/**
     * 修改
     * @param $!{lowerName}
     * @return
     */
    @Override
	public  Integer update(${className} $!{lowerName}) throws Exception {
        return $!{lowerName}Service.update($!{lowerName});
	}

	/**
     * 查询列表
     * @param $!{lowerName}Query
     * @return
     */
    @Override
	public PageBean<${className}> getList(${className}Query $!{lowerName}Query) {
        return $!{lowerName}Service.getList($!{lowerName}Query);
	}

	/**
     * 根据ID查询
     * @param id
     * @return
     */
    @Override
	public ${className} getById(String id) {
        return $!{lowerName}Service.getById(id);
	}	
}