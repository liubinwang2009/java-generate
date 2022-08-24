package ${package}.controller;

import com.alibaba.fastjson.JSON;
import ${package}.domain.query.${className}Query;
import ${package}.domain.vo.${className}VO;
import ${package}.model.Result;
import ${package}.service.${className}Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * ${comment}
 * @author liubinwang
 *
 */
@RestController
@RequestMapping(value = "/$!{lowerName}")
@Api(tags={"这里写接口备注"})
public class ${className}Controller {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(${className}Controller.class);
    @Autowired
    private ${className}Service $!{lowerName}Service;

    @ApiOperation(value="新增", notes="新增")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "$!{lowerName}VO", value = "{}" , required = true, paramType = "body", dataType = "${className}VO")
    })
    @PostMapping
    public Result add(@RequestBody ${className}VO $!{lowerName}VO){
        try {
            return $!{lowerName}Service.add($!{lowerName}VO);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return Result.failure();
        }
    }

	@ApiOperation(value="删除", notes="删除")
    @DeleteMapping("{id}")
    public Result delete(@PathVariable(name = "id") String id){
        try {
            return $!{lowerName}Service.deleteById(id);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return Result.failure();
        }
    }
	
	@ApiOperation(value="修改", notes="修改")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "$!{lowerName}VO", value = "{}" , required = true, paramType = "body", dataType = "${className}VO")
    })
    @PostMapping("update")
    public Result update(@RequestBody ${className}VO $!{lowerName}VO){
        try {
            return $!{lowerName}Service.update($!{lowerName}VO);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return Result.failure();
        }
    }

    @ApiOperation(value="查询列表", notes="查询列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "$!{lowerName}Query", value = "{\n"
            + "  \"pageNo\": 1,\n"
            + "  \"pageSize\": 10\n"
            + "}" , required = true, paramType = "body", dataType = "${className}Query")
    })
    @PostMapping("list")
    public Result list(@RequestBody ${className}Query $!{lowerName}Query){
        try {
            return $!{lowerName}Service.getList($!{lowerName}Query);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return Result.failure();
        }
    }

    @ApiOperation(value="根据id查询", notes="根据id查询")
   	@GetMapping("{id}")
    public Result get(@PathVariable(name = "id") String id){
        try {
            return $!{lowerName}Service.getById(id);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return Result.failure();
        }
    }    
}
