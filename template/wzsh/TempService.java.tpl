package ${package}.service;

import ${package}.domain.query.${className}EditQuery;
import ${package}.domain.query.${className}ListQuery;
import ${package}.model.Result;

/**
 * ${comment}
 * @author liubinwang
 *
 */
public interface ${className}Service{

	/**
     * 新增
     * @param $!{lowerName}EditQuery
     * @return
     */
    Result add(${className}EditQuery $!{lowerName}EditQuery);
    
    /**
     * 删除 
     * @param id
     * @return
     */
    Result deleteById(String id);
    
 	/**
     * 修改
     * @param $!{lowerName}EditQuery
     * @return
     */
    Result update(${className}EditQuery $!{lowerName}EditQuery);
    
    /**
     * 查询列表
     * @param $!{lowerName}ListQuery
     * @return
     */
    Result getList(${className}ListQuery $!{lowerName}ListQuery);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    Result getById(String id);
  
}
