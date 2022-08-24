package ${package}.mapper;

import java.util.List;
import ${package}.domain.dto.${className}DTO;
import ${package}.domain.query.${className}ListQuery;
import org.apache.ibatis.annotations.Param;

/**
 * ${comment}
 * @author liubinwang
 *
 */
public interface ${className}Mapper{
	
	/**
     * 新增
     * @param $!{lowerName}DTO
     * @return
     */
    int insertSelective(${className}DTO $!{lowerName}DTO);

    /**
     * 修改
     * @param $!{lowerName}DTO
     * @return
     */
    int updateByPrimaryKeySelective(${className}DTO $!{lowerName}DTO);

    /**
     * 查询列表
     * @param $!{lowerName}ListQuery
     * @return
     */
    List<${className}DTO> getList(${className}ListQuery $!{lowerName}ListQuery);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    ${className}DTO getById(@Param("id")String id);

    /**
     * 删除
     * @param id
     * @return
     */
    int deleteById(@Param("id")String id);
}
