# mybatis xml生成

### 用法

```java
@KTable("用户表")
public class User {
    @KId("编号")
    private String id;
    @KField("姓名")
    private String name;
    @KField("密码")
    private String password;

    @KParam("订单编号")
    private String orderCode;
}
```

```java
 String xml=MyBatisUtil.createXml(BlogTag.class);
 System.out.println(xml);
```

### 生成结果
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="com.ttianjun.mybatis.test.UserDao" >
<resultMap type="com.ttianjun.mybatis.test.User" id="resultMap">
    <id property="id" column="编号" jdbcType="VARCHAR" />
    <result property="name" column="姓名" jdbcType="VARCHAR" />
    <result property="password" column="密码" jdbcType="VARCHAR" />
</resultMap>

<select id="list" resultMap="resultMap">
	select * from 用户表 where 1=1
    <if test="orderCode!=null and orderCode!=''">and 订单编号 = #{orderCode}</if>
</select>

<insert id="insert" parameterType="com.ttianjun.mybatis.test.User">
    insert into 用户表
    <trim prefix="(" suffix=")" suffixOverrides=",">
        编号,
        <if test="name!=null and name!=''">姓名,</if>
        <if test="password!=null and password!=''">密码,</if>
    </trim>
    <trim prefix="values(" suffix=")" suffixOverrides=",">
        #{id},
        <if test="name!=null and name!=''"> #{name},</if>
        <if test="password!=null and password!=''"> #{password},</if>
    </trim>
</insert>

<update id="update">
    update  用户表 set
    <trim   suffixOverrides="," >
       <if test="name!=null and name!=''"> 姓名=#{name},</if>
       <if test="password!=null and password!=''"> 密码=#{password},</if>
    </trim>
    where 编号 = #{id}
</update>
</mapper>
```
