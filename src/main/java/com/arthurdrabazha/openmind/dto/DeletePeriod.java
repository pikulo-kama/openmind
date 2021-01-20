package com.arthurdrabazha.openmind.dto;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public enum DeletePeriod {

    ONE_MONTH("One month", 30),
    THREE_MONTH("Three month", 90),
    SIX_MONTH("Six month", 180),
    ONE_YEAR("One year", 364),
    NEVER("Never", Integer.MAX_VALUE);

    private String verboseName;
    private Integer numericalValue;


    public String getVerboseName() {
        return verboseName;
    }

    public Integer getNumericalValue() {
        return numericalValue;
    }
}
