package com.okta.springbootvue.controller.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentData{

    private Long selecttable;
    private Long selectmember;
    private Long selectemployee;
    private String change;
    private String money;
    private String total;
    private String date;
    
    public Long getTable(){
        return selecttable;
    }
    public Long getMember(){
        return selectmember;
    }
    public Long getEmployee(){
        return selectemployee;
    }
    public Double getMoney(){
        return Double.valueOf(money);
    }
    public Double getChange(){
        return Double.valueOf(change);
    }
    public Double getTotal(){
        return Double.valueOf(total);
    }
    public LocalDateTime getDate(){
        String so = date.substring(0,date.length()-1);
        LocalDateTime tdate = LocalDateTime.parse(so);

        return tdate;
    }
}
