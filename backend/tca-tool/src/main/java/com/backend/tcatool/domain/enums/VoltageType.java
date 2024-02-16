package com.backend.tcatool.domain.enums;

public enum VoltageType {
    /**
     * LOW: Voltages below 1kV
     * MEDIUM: Voltages between 1kV and 34.5kV
     * HIGH: Voltages between 34.5kV and 230kV
     * VERY_HIGH: Voltages above 230kV
     */
    LOW, MEDIUM, HIGH, VERY_HIGH;
}
