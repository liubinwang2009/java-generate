package ${package}.service.impl;

import java.util.List;
import com.github.pagehelper.PageHelper;
import ${package}.model.PageBean;
import ${package}.model.StateCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;
import org.apache.commons.lang.StringUtils;
import com.govnet.crime.common.util.HibernateValidateUtils;
import ${package}.mapper.${className}Mapper;
import ${package}.domain.dto.${className}DTO;
import ${package}.domain.query.${className}EditQuery;
import ${package}.domain.query.${className}ListQuery;
import ${package}.service.${className}Service;
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
     * @param $!{lowerName}EditQuery
     * @return
     */
    @Override
	public Result add(${className}EditQuery $!{lowerName}EditQuery) {
		String errorMsg = HibernateValidateUtils.getErrors($!{lowerName}EditQuery);
		if (StringUtils.isNotEmpty(errorMsg)) {
			return Result.failure(StateCode.PARAMS_EXCEPTION_MSG, errorMsg);
		}
		final ${className}DTO $!{lowerName}DTO = new ${className}DTO();
		BeanUtils.copyProperties($!{lowerName}EditQuery, $!{lowerName}DTO);
		if($!{lowerName}Mapper.insertSelective($!{lowerName}DTO)>0){
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
     * @param $!{lowerName}EditQuery
     * @return
     */
    @Override
	public  Result update(${className}EditQuery $!{lowerName}EditQuery) {
	String errorMsg = HibernateValidateUtils.getErrors($!{lowerName}EditQuery);
		if (StringUtils.isNotEmpty(errorMsg) || StringUtils.isEmpty($!{lowerName}EditQuery.getId())) {
			return Result.failure(StateCode.PARAMS_EXCEPTION_MSG, errorMsg);
		}
		final ${className}DTO $!{lowerName}DTO = new ${className}DTO();
		BeanUtils.copyProperties($!{lowerName}EditQuery, $!{lowerName}DTO);
		if($!{lowerName}Mapper.updateByPrimaryKeySelective($!{lowerName}DTO)>0){
        	return Result.success(null);
        }
        return Result.failure();
	}

	/**
     * 查询列表
     * @param $!{lowerName}ListQuery
     * @return
     */
    @Override
	public Result getList(${className}ListQuery $!{lowerName}ListQuery) {
		if ($!{lowerName}ListQuery.getPageNum() == null) {
            $!{lowerName}ListQuery.setPageNum(PageBean.PAGENUM);
        }
        if ($!{lowerName}ListQuery.getPageSize() == null) {
            $!{lowerName}ListQuery.setPageSize(PageBean.PAGESIZE);
        }
		PageHelper.startPage($!{lowerName}ListQuery.getPageNum(), $!{lowerName}ListQuery.getPageSize());
        List<${className}DTO> list = $!{lowerName}Mapper.getList($!{lowerName}ListQuery);
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