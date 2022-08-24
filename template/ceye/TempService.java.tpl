package ${package}.service;

import ${package}.entity.${className};
import ${package}.entity.query.${className}Query;
import ${package}.entity.PageBean;

/**
 * ${comment}
 * @author liubinwang
 *
 */
public interface ${className}Service{

	/**
     * 新增
     * @param $!{lowerName}
     * @return
     */
    Integer add(${className} $!{lowerName}) throws Exception;
    
    /**
     * 删除 
     * @param id
     * @return
     */
    Integer deleteById(String id);
    
 	/**
     * 修改
     * @param $!{lowerName}
     * @return
     */
    Integer update(${className} $!{lowerName}) throws Exception;
    
    /**
     * 查询列表
     * @param $!{lowerName}Query
     * @return
     */
    PageBean<${className}> getList(${className}Query $!{lowerName}Query);

    /**
     * 根据ID查询
     * @param String id
     * @return
     */
    ${className} getById(String id);
  
}
