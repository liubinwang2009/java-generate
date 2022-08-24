package ${package}.controller;

import ${package}.domain.query.${className}ListQuery;
import ${package}.domain.query.${className}EditQuery;
import ${package}.model.Result;
import ${package}.service.${className}Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * ${comment}
 * @author liubinwang
 *
 */
@RestController
@RequestMapping(value = "/$!{lowerName}")
@Api(tags={"${comment}"})
public class ${className}Controller {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(${className}Controller.class);
    @Autowired
    private ${className}Service $!{lowerName}Service;

    @ApiOperation(value="新增", notes="新增")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "$!{lowerName}EditQuery", value = "{}" , required = true, paramType = "body", dataType = "${className}EditQuery")
    })
    @PostMapping("add")
    public Result add(@RequestBody ${className}EditQuery $!{lowerName}EditQuery){
        try {
            return $!{lowerName}Service.add($!{lowerName}EditQuery);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return Result.failure();
        }
    }

	@ApiOperation(value="删除", notes="删除")
    @PostMapping("delete")
    public Result delete(@RequestParam("id") String id){
        try {
            return $!{lowerName}Service.deleteById(id);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return Result.failure();
        }
    }
	
	@ApiOperation(value="修改", notes="修改")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "$!{lowerName}EditQuery", value = "{}" , required = true, paramType = "body", dataType = "${className}EditQuery")
    })
    @PostMapping("update")
    public Result update(@RequestBody ${className}EditQuery $!{lowerName}EditQuery){
        try {
            return $!{lowerName}Service.update($!{lowerName}EditQuery);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return Result.failure();
        }
    }

    @ApiOperation(value="查询列表", notes="查询列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "$!{lowerName}Query", value = "{\n"
            + "  \"pageNum\": 1,\n"
            + "  \"pageSize\": 10\n"
            + "}" , required = true, paramType = "body", dataType = "${className}ListQuery")
    })
    @PostMapping("list")
    public Result list(@RequestBody ${className}ListQuery $!{lowerName}ListQuery){
        try {
            return $!{lowerName}Service.getList($!{lowerName}ListQuery);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return Result.failure();
        }
    }

    @ApiOperation(value="根据id查询", notes="根据id查询")
   	@GetMapping("getById")
    public Result getById(@RequestParam("id") String id){
        try {
            return $!{lowerName}Service.getById(id);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return Result.failure();
        }
    }    
}
