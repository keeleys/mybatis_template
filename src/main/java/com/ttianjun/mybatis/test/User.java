/**
 * Created by wodetianjun on 15/5/28.
 */
package com.ttianjun.mybatis.test;

import com.ttianjun.mybatis.KField;
import com.ttianjun.mybatis.KId;
import com.ttianjun.mybatis.KParam;
import com.ttianjun.mybatis.KTable;

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
