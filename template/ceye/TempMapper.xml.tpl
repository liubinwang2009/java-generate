<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package}.dao.${className}Mapper" >
 
	<!-- Result Map-->
	<resultMap id="BaseResultMap" type="${package}.entity.${className}" >
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
	<insert id="insertSelective" parameterType="${package}.entity.${className}" >
		$!{SQL.insertSelectiveSql}
	</insert>

	<!--修改-->
	<update id="updateByPrimaryKeySelective" parameterType="${package}.entity.${className}" >
		$!{SQL.updateSelective}
	</update>

	<!-- 删除 -->
	<update id="deleteById" parameterType="java.lang.String">
		UPDATE ${tableName} A  SET A.DEL_FLAG=${@${package}.db.DBStateFinal@STATUS_DELETED}  WHERE A.ID = #{id}
	</update>
 
	<!-- 查询详细 -->
	<select id="getById"  resultMap="BaseResultMap" parameterType="java.lang.String">
		$!{SQL.selectById}
	</select>
  	
  	
  	<sql id="Where_Clause">
		<where>
			A.DEL_FLAG = ${@${package}.db.DBStateFinal@STATUS_UNDELETED}
			<if test="id!=null and id!=''">
				AND A.ID = #{id}
			</if>
		</where>
	</sql>
	
  	<!-- 查询列表 -->
	<select id="getList"  resultMap="BaseResultMap" parameterType="${package}.entity.query.${className}Query">
		SELECT
        <include refid="Base_Column_List" /> FROM ${tableName} A
        <include refid="Where_Clause" />
        ORDER BY A.CREATE_DATE DESC
		LIMIT #{start},#{pageSize}
	</select>
	
	
	<!-- 查询总量 -->
	<select id="count" resultType="int"
		parameterType="${package}.entity.query.${className}Query">
		SELECT
		COUNT(1)
		FROM ${tableName} A
		<include refid="Where_Clause" />
	</select>
	
</mapper>   
