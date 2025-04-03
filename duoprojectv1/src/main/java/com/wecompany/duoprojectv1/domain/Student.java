package com.wecompany.duoprojectv1.domain;

import lombok.Data;

@Data
public class Student {
    private Integer stNum;
    private Integer accId;
    private String stName;
    private String stDepartment;
    private int stYear;
    private String stPhone;
    private String stAddr;
    private int depId;
}

