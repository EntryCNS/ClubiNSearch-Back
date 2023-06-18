package com.dgsw.cns.clubinsearch.domain.recruitment.domain.enums;

public enum EmploymentType {
    INTERN("인턴"),
    REGULAR("정규");

    private final String value;

    EmploymentType(String value){
        this.value = value;
    }
}
