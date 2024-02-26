package com.backend.tcatool.methods.commons;

import lombok.Getter;

@Getter
public enum PossibleResult {
    N ("Normal", "N"),
    DT("Combination of Thermal Faults and Discharges", "DT"),
    PD2("High Energy-Corona Partial Discharge", "PD2"),
    PD1("Low Energy-Corona Partial Discharge", "PD1"),
    PD ("Partial Discharge", "PD"),
    D1 ("Discharge With Low Energy", "D1"),
    D2 ("Discharge With High Energy", "D2"),
    T0("Thermal Fault below 150°C", "T0"),
    T1 ("Thermal Fault between 150°C and 300°C", "T1"),
    T2 ("Thermal Fault between 300°C and 700°C", "T2"),
    T3 ("Thermal Fault above 700°C", "T3");

    private final String reason;
    private final String code;

    PossibleResult(String reason, String code) {
        this.reason = reason;
        this.code = code;
    }
}
