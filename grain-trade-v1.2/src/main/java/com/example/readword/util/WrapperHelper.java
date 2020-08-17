package com.example.readword.util;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: WangGQ
 * @Date: 2020/6/28 9:48
 * @Desc: wrapper 工具类
 * <p>
 * 通过传入的实体类封装对应的wrapperQuery对象，若对象的属性值是‘’或这null 则不会封装该条件
 * @<code> A a = new A();
 * a.setId(1）;
 * a.setName("Bob");
 * a.setAddress(null);
 * <p>
 * QueryWrapper<A> wrapper = new WrapperHelper<A>().getBaseQueryWrapper(a);
 * //封装后的sql：
 * select * from TableA where id = 1 and name = 'Bob'
 * </code>
 * @since 1.1
 * <p>
 * 建议用法构造器传对象即可增加代码复用性
 */
public class WrapperHelper<E> {

    private E e;

    /**
     * 基础列字段
     */
    StringBuffer baseColumn;

    String tableName;


    private ConcurrentHashMap<String, Object> fieldMap;


    public WrapperHelper(E e) {
        this.e = e;
        initEnv(e);
        initEntityFields();
    }

    /**
     * 初始化实体类基本属性
     */
    public void initEntityFields() {
        //初始化表名
        TableName tn = e.getClass().getAnnotation(TableName.class);
        if (tn != null)
            this.tableName = tn.value();
        else
            throw new MybatisPlusException("实体类必加@TableName注解!");
    }

    private void initEnv(E e) {
        this.fieldMap = new ConcurrentHashMap();
        Field[] fields = e.getClass().getFields();
        if (fields.length == 0) {
            return;
        }
        for (Field field : fields) {

            field.setAccessible(true);
            //初始化基本列
            baseColumn.append("   " + field.getName() + " , ");
            TableField tableField = field.getAnnotation(TableField.class);
            if (tableField != null) {
                Object o = null;
                try {
                    o = field.get(e);
                } catch (IllegalAccessException e1) {
                    continue;
                }
                if (null == o) {
                    continue;
                }
                if (o instanceof String) {
                    if (StringUtils.isEmpty((String) o)) {
                        continue;
                    }
                }
                fieldMap.put(tableField.value(), o);
                continue;
            }
            TableId tableId = field.getAnnotation(TableId.class);
            if (null != tableId) {
                Object o = null;
                try {
                    o = field.get(e);
                } catch (IllegalAccessException e1) {
                    continue;
                }
                if (null == o) {
                    continue;
                }
                if (StringUtils.isEmpty(tableId.value())) {
                    if (null == o) {
                        continue;
                    }
                    fieldMap.put(field.getName(), o);
                    continue;
                }
                if (o instanceof String) {
                    String a = (String) o;
                    if (StringUtils.isEmpty(a)) {
                        continue;
                    }
                }
                fieldMap.put(tableId.value(), o);
                continue;
            }
        }
    }

    /**
     * 基础动态sql拼装
     * 仅能获取动态sql select * from table where a = 1 and b = 2 这种
     * 类似于like,or,between ... and ... 之类的复杂语法不能使用这个方法获取wrapper对象
     *
     * @return 封装后的QueryWrapper对象
     * @date : 2020/7/6 不推荐使用这种方式
     * 替换为：
     * <code>
     * new WrapperHelper<ContractPO>(contractPO).getQueryWrapper();
     * </code>
     */


    @Deprecated
    private QueryWrapper<E> createQueryWrapper(Map fieldMap) {
        QueryWrapper<E> queryWrapper = new QueryWrapper<>();
        fieldMap.keySet().forEach(var1 -> queryWrapper.eq((String) var1, fieldMap.get(var1)));
        return queryWrapper;
    }


    /**
     * 动态查询sql wrapper
     *
     * @return
     */
    public QueryWrapper<E> getQueryWrapper() {
        QueryWrapper<E> queryWrapper = new QueryWrapper<>();
        this.fieldMap.keySet().forEach(var1 -> queryWrapper.eq(var1, this.fieldMap.get(var1)));
        return queryWrapper;
    }

    /**
     * 动态修改的sql wrapper
     *
     * @return
     */
    public UpdateWrapper<E> getUpdateWrapper() {
        UpdateWrapper<E> updateWrapper = new UpdateWrapper<>();
        this.fieldMap.keySet().forEach(var1 -> updateWrapper.set(var1, this.fieldMap.get(var1)));
        return updateWrapper;
    }

    /**
     * 生成批量增加的sql，mybatis-plus批量增加效率过低
     *
     * @return sql
     */
    public String batchInsert() {
        StringBuffer sql = new StringBuffer();
        sql.append("insert into  ").append(tableName).append(" (  ").append(baseColumn).append("   )  ");
        sql.append(" values ");

        //循环赋值


        return sql.toString();
    }
}





