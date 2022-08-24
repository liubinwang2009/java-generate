package ${package}.web;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ${package}.entity.query.${className}Query;
import ${package}.facade.${className}Facade;
import ${package}.entity.${className};
import com.govnet.authenCenter.entity.UserInfo;
import com.govnet.util.UserUtil;
import com.govnet.common.ResultStatus;
import com.govnet.common.Results;
import com.govnet.common.UserInfo;
import com.govnet.common.utils.IdGen;
/**
 * ${comment}
 * @author liubinwang
 *
 */
@RestController
@RequestMapping(value = "/$!{lowerName}")
public class ${className}Controller {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(${className}Controller.class);
    @Autowired
    private ${className}Facade $!{lowerName}Facade;

	/**
     * 新增
     * @param $!{lowerName}
     * @return
     */
    @PostMapping
    public Results add(@RequestBody ${className} $!{lowerName}){
    	if ($!{lowerName} == null) {
			return Results.error(ResultStatus.PARAMS_EXCEPTION);
		}
        final UserInfo user = UserUtil.getCurrentUser();
		$!{lowerName}.setId(IdGen.uuid());
		$!{lowerName}.setCreateBy(user.getUserNo());
		$!{lowerName}.setCreateUnit(user.getOrgCode());
        try {
            Integer r = $!{lowerName}Facade.add($!{lowerName});
            if (r > 0) {
				return Results.ok();
			}
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return Results.error();
    }

	/**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public Results delete(@PathVariable(name = "id") String id){
    	if (StringUtils.isEmpty(id)) {
			return Results.error(ResultStatus.PARAMS_EXCEPTION);
		}
        try {
            Integer r = $!{lowerName}Facade.deleteById(id);
            if (r > 0) {
				return Results.ok();
			}
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return Results.error();
    }
	

	/**
     * 修改
     * @param $!{lowerName}
     * @return
     */
    @PostMapping("update")
    public Results update(@RequestBody ${className} $!{lowerName}){
    	if ($!{lowerName} == null) {
			return Results.error(ResultStatus.PARAMS_EXCEPTION);
		}
        final UserInfo user = UserUtil.getCurrentUser();
		$!{lowerName}.setUpdateBy(user.getUserNo());
		$!{lowerName}.setUpdateUnit(user.getOrgCode());
        try {
            Integer r = $!{lowerName}Facade.update($!{lowerName});
            if (r > 0) {
				return Results.ok();
			}
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return Results.error();
    }

	/**
     * 查询列表
     * @param $!{lowerName}Query
     * @return
     */
    @PostMapping("list")
    public Results list(@RequestBody ${className}Query $!{lowerName}Query){
    	if ($!{lowerName}Query == null || $!{lowerName}Query.getPageNum() == null || $!{lowerName}Query.getPageSize() == null) {
			return Results.error(ResultStatus.PARAMS_EXCEPTION);
		}
        try {
            return Results.ok($!{lowerName}Facade.getList($!{lowerName}Query));
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return Results.error();
    }

	/**
     * 根据id查询
     * @param id
     * @return
     */
   	@GetMapping("{id}")
    public Results get(@PathVariable(name = "id") String id){
    	if (StringUtils.isEmpty(id)) {
			return Results.error(ResultStatus.PARAMS_EXCEPTION);
		}
        try {
            return Results.ok($!{lowerName}Facade.getById(id));
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return Results.error();
    }    
}
