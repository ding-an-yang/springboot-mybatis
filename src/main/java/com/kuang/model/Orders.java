package com.kuang.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author ：yangan
 * @date ：2023/5/29 下午2:34
 * @description：
 * @version:
 */
@Data
@TableName("orders")
public class Orders {
    private Integer id;
    private String no;
    private Short type;
    private String name;
}