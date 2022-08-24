package ${package}.service;

import ${package}.domain.dto.${className}DTO;
import ${package}.domain.query.${className}Query;
import ${package}.model.Result;

/**
 * ${comment}
 * @author liubinwang
 *
 */
public interface ${className}Service{

	/**
     * 新增
     * @param dto
     * @return
     */
    Result add(${className}DTO dto);
    
    /**
     * 删除 
     * @param id
     * @return
     */
    Result deleteById(String id);
    
 	/**
     * 修改
     * @param dto
     * @return
     */
    Result update(${className}DTO dto);
    
    /**
     * 查询列表
     * @param $!{lowerName}Query
     * @return
     */
    Result getList(${className}Query $!{lowerName}Query);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    Result getById(String id);
  
}
