<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package}.mapper.${className}Mapper" >
 
	<!-- Result Map-->
	<resultMap id="BaseResultMap" type="${package}.domain.dto.$!{className}DTO" >
	#foreach($item in $!{columnDatas})
	     #if($item.pri)
	    <id column="$!item.columnName" jdbcType="$item.jdbcType" property="$item.propName"/>
	    #else
		<result column="$!item.columnName" jdbcType="$item.jdbcType" property="$item.propName"/>
		#end
	#end
	</resultMap> 	
	
	<!-- $!{tableName} table all fields -->
	<sql id="Base_Column_List" >
			$!{SQL.columnFields}
	</sql>
	   
	<!-- 新增 -->
	<insert id="insertSelective" parameterType="${package}.domain.dto.${className}DTO" >
		$!{SQL.insertSelectiveSql}
	</insert>
	
	<!--批量新增-->
	<insert id="insertBatch" parameterType="java.util.List">
      INSERT INTO $!{tableName} ($!{SQL.insertColumnFields}) $!{SQL.insertBatchSql}
  	</insert>

	<!--修改-->
	<update id="updateByPrimaryKeySelective" parameterType="${package}.domain.dto.${className}DTO" >
		$!{SQL.updateSelective}
	</update>

	<!-- 删除 -->
	<update id="deleteById" parameterType="java.lang.String">
		UPDATE ${tableName} SET DELETE_FLAG = 1 WHERE RECORD_ID = #{id}
	</update>
 
	<!-- 查询详细 -->
	<select id="getById"  resultMap="BaseResultMap" parameterType="java.lang.String">
		$!{SQL.selectById}
	</select>
  	
  	<!-- 查询列表 -->
	<select id="getList"  resultMap="BaseResultMap" parameterType="${package}.domain.query.${className}Query">
		SELECT
        <include refid="Base_Column_List" /> FROM ${tableName} A
        WHERE A.DELETE_FLAG = 0 
        ORDER BY A.INSERT_TIME DESC
	</select>
	
	
	
</mapper>   
