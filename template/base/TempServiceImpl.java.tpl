package ${package}.service.impl;

import java.util.List;
import com.github.pagehelper.PageHelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;
import ${package}.mapper.${className}Mapper;
import ${package}.domain.dto.${className}DTO;
import ${package}.domain.query.${className}Query;
import ${package}.service.${className}Service;
import ${package}.model.PageBean;
import ${package}.model.Result;


/**
 * ${comment}
 * @author liubinwang
 *
 */
@Service
public class ${className}ServiceImpl implements ${className}Service{

	private final static Logger logger= LoggerFactory.getLogger(${className}ServiceImpl.class);
	
    @Autowired
	private  ${className}Mapper $!{lowerName}Mapper;

	/**
     * 新增
     * @param dto
     * @return
     */
    @Override
	public Result add(${className}DTO dto) {
		if($!{lowerName}Mapper.insertSelective(dto)>0){
		  return Result.success(null);
		}
		return Result.failure();
	}

	/**
     * 删除 
     * @param id
     * @return
    */
    @Override
	public Result deleteById(String id) {
        if($!{lowerName}Mapper.deleteById(id)>0){
        	return Result.success(null);
        }
        return Result.failure();
	}

	/**
     * 修改
     * @param dto
     * @return
     */
    @Override
	public  Result update(${className}DTO dto) {
		if($!{lowerName}Mapper.updateByPrimaryKeySelective(dto)>0){
        	return Result.success(null);
        }
        return Result.failure();
	}

	/**
     * 查询列表
     * @param $!{lowerName}Query
     * @return
     */
    @Override
	public Result getList(${className}Query $!{lowerName}Query) {
		PageHelper.startPage($!{lowerName}Query.getPageNum(), $!{lowerName}Query.getPageSize());
        List<${className}DTO> list = $!{lowerName}Mapper.getList($!{lowerName}Query);
        final PageBean<${className}DTO> pageInfo = new PageBean<>(list);
        return Result.success(pageInfo);
	}

	/**
     * 根据ID查询
     * @param id
     * @return
     */
    @Override
	public Result getById(String id) {
        return Result.success($!{lowerName}Mapper.getById(id));
	}
}