package com.backend.tcatool.methods.commons;

import lombok.Getter;

@Getter
public class Ratios {

    private final Integer ch4;
    private final Integer h2;
    private final Integer c2h2;
    private final Integer c2h4;
    private final Integer c2h6;
    private final Integer co;
    private final Integer co2;

    public Ratios(Integer ch4, Integer h2, Integer c2h2, Integer c2h4, Integer c2h6, Integer co, Integer co2) {
        this.ch4 = ch4;
        this.h2 = h2;
        this.c2h2 = c2h2;
        this.c2h4 = c2h4;
        this.c2h6 = c2h6;
        this.co = co;
        this.co2 = co2;
    }

    public Double getR1(){
        return this.ch4/(this.h2 == 0 ? 0.000_000_000_000_000_001 : this.h2);
    }

    public Double getR2(){
        return this.c2h2/(this.c2h4 == 0 ? 0.000_000_000_000_000_001 : this.c2h4);
    }

    public Double getR3(){
        return this.c2h2/(this.ch4 == 0 ? 0.000_000_000_000_000_001 : this.ch4);
    }

    public Double getR4(){
        return this.c2h6/(this.c2h2 == 0 ? 0.000_000_000_000_000_001 : this.c2h2);
    }

    public Double getR5(){
        return this.c2h4/(this.c2h6 == 0 ? 0.000_000_000_000_000_001 : this.c2h6);
    }

    public Double getR7(){
        return this.c2h6/(this.ch4 == 0 ? 0.000_000_000_000_000_001 : this.ch4);
    }

}
