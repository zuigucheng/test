package com.zui.test.task;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zui
 * @date 2019.12.13 14:05
 * 实体查询类
 */
public class User {
    private int id;
    private String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
