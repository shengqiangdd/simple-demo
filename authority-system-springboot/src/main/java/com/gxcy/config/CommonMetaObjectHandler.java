package com.gxcy.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class CommonMetaObjectHandler implements MetaObjectHandler {
    /**
     * 新增
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {

        //参数1：元数据对象
        //参数2：属性名称
        //参数3：类对象
        //参数4：当前系统时间
        this.strictInsertFill(metaObject,"creatTime", Date.class,new Date());
        this.strictInsertFill(metaObject,"updateTime",Date.class,new Date());
    }

    /**
     * 修改
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject,"updateTime",Date.class,new Date());
    }
}
