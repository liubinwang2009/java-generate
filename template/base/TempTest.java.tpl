package com.csair.soc.lps.remote.xx.test;

import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.csair.soc.common.exception.SocException;
import com.csair.soc.lps.remote.xx.services.impl.${className}ServiceImpl;
import com.csair.soc.lps.xx.pojo.${className};

public class Test${className} extends TestCase {

	// 创建Spring应用上下文
	private ApplicationContext context = new ClassPathXmlApplicationContext(
			new String[] { "com/csair/soc/lps/remote/config/applicationContext-databases.xml","com/csair/soc/lps/remote/config/applicationContext-mybatis.xml" });

	public static Logger log = Logger.getLogger(Test${className}.class);
	
	${className}ServiceImpl $!{lowerName}Service = (${className}ServiceImpl) context.getBean(${className}Service.class.getName());
	

	/**删除*/
	public void testClear(){
		String[] strs = new String[]{"002"};
		try {
			Assert.assertTrue($!{lowerName}Service.remove(strs));
		} catch (SocException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}
	/**保存*/
	public void testSave(){
		${className} $!{lowerName} = new ${className}();
		$!{lowerName}.setID("002");
		try {
			Assert.assertTrue($!{lowerName}Service.save($!{lowerName}));
		} catch (SocException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}

	/**精确查找*/
	public void testGet(){
		String primarykey ="002";
		try {
			${className} $!{lowerName} = $!{lowerName}Service.get(primarykey);
			Assert.assertEquals(primarykey, $!{lowerName}.getID());
		} catch (SocException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}
	
	/**更新*/
	public void testUpdate(){
		String primarykey ="002";
		${className} $!{lowerName};
		try {
			$!{lowerName} = $!{lowerName}Service.get(primarykey);
			Assert.assertTrue($!{lowerName}Service.update($!{lowerName}));
		} catch (SocException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}

	/**获取所有数据,含分页*/
	public void testGetAllByModel(){
		${className} $!{lowerName} = new ${className}();
		$!{lowerName}.setID("002");
		try {
			List<${className}> $!{lowerName}List = $!{lowerName}Service.getAllByModel($!{lowerName});
			Assert.assertNotNull($!{lowerName}List);
			Assert.assertEquals($!{lowerName}List.size(), 1);
		} catch (SocException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}

	/**删除*/
	public void testRemove(){
		String[] strs = new String[]{"002"};
		try {
			Assert.assertTrue($!{lowerName}Service.remove(strs));
		} catch (SocException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}


}
