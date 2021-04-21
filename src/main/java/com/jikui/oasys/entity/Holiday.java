package com.jikui.oasys.entity;

/**
 * @Author: zhaojq
 * @Description:
 * @Date:Create：in 2020/6/21 10:16
 * @Modified By：
 **/
public class Holiday {
    private int id;

    /**
     * 请假人
     */
    private String name;

    /**
     * 请假天数
     */
    private String days;

    /**
     * 请假时间节点
     */
    private String time_node;

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

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getTime_node() {
        return time_node;
    }

    public void setTime_node(String time_node) {
        this.time_node = time_node;
    }

    @Override
    public String toString() {
        return "Holiday{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", days='" + days + '\'' +
                ", time_node='" + time_node + '\'' +
                '}';
    }
}
