package ${package}.dao;

import java.util.List;
import ${package}.entity.${className};
import ${package}.entity.query.${className}Query;
import org.apache.ibatis.annotations.Param;

/**
 * ${comment}
 * @author liubinwang
 *
 */
public interface ${className}Mapper {
	
	/**
     * 新增
     * @param $!{lowerName}
     * @return
     */
    int insertSelective(${className} $!{lowerName});

    /**
     * 修改
     * @param $!{lowerName}
     * @return
     */
    int updateByPrimaryKeySelective(${className} $!{lowerName});

    /**
     * 查询列表
     * @param $!{lowerName}Query
     * @return
     */
    List<${className}> getList(${className}Query $!{lowerName}Query);


 	/**
     *  查询总量
     * @param swGroupQuery
     * @return
     */
    int count(${className}Query $!{lowerName}Query);
    
    /**
     * 根据ID查询
     * @param id
     * @return
     */
    ${className} getById(@Param("id")String id);

    /**
     * 删除
     * @param id
     * @return
     */
    int deleteById(@Param("id")String id);
}
