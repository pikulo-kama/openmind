package com.arthurdrabazha.openmind.dto;

public enum NonActivePeriod {

    TEN_DAYS ("Ten days"),
    ONE_MONTH ("One month"),
    THREE_MONTH ("Three month"),
    SIX_MONTH ("Six month"),
    ONE_YEAR ("One year");

    private String title;

    NonActivePeriod(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }
}
