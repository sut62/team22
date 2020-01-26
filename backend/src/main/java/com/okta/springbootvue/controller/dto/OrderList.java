package com.okta.springbootvue.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderList{

    private Long selecttable;
    private Long selectmember;
    private Long selectemployee;
    private Long selectmemberships;
    private Integer change;
    private Integer money;
    private String total;
    
    public Long getTable(){
        return selecttable;
    }
    public Long getMember(){
        return selectmember;
    }
    public Long getEmployee(){
        return selectemployee;
    }
    public Long getMemberships(){
        return selectmemberships;
    }
    public Integer getMoney(){
        return money;
    }
    public Integer getChange(){
        return change;
    }
    public Double getTotal(){
        return Double.valueOf(total);
    }
}
