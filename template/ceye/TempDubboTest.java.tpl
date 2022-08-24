package ${package}.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.Date;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.govnet.common.utils.IdGen;
import ${package}.entity.${className};
import ${package}.entity.query.${className}Query;
import ${package}.facade.${className}Facade;

/**
 * ${comment}测试
 * 
 * @author liubinwang
 *
 */
public class Dubbo${className}Test {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext cxt = null;
		try {
			cxt = new ClassPathXmlApplicationContext("classpath:dubbo-consumer.xml");
			cxt.start();

			${className}Facade service = (${className}Facade) cxt.getBean("$!{lowerName}Facade");

			// //添加
			 String id = IdGen.uuid();
			 ${className} $!{lowerName} = new ${className}();
			 ${setDefaultValue}
			 service.add($!{lowerName});
			
			 //修改
			 ${setDefaultValue}
			 service.update($!{lowerName});

			// 删除
			// service.deleteById("daeb000e199c47888376b5c3751068ff");

			${className}Query query = new ${className}Query();
			query.setPageNum(1);
			query.setPageSize(2);
			System.out.println(JSONObject.toJSONString(service.getList(query),SerializerFeature.WriteMapNullValue));

			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		} finally {
			if (cxt != null) {
				cxt.close();
			}
		}
	}
}
