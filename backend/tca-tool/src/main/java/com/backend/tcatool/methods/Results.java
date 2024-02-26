package com.backend.tcatool.methods;

import com.backend.tcatool.methods.commons.PossibleResult;
import com.backend.tcatool.methods.commons.Ratios;

import java.util.HashMap;
import java.util.Map;

public class Results extends Ratios {

    public Results(Integer ch4, Integer h2, Integer c2h2, Integer c2h4, Integer c2h6, Integer co, Integer co2) {
        super(ch4, h2, c2h2, c2h4, c2h6, co, co2);
    }

    public Map<String, String> getResults(){
        Map<String, String> result =  new HashMap<>();
        result.put("duval", getDuvalResult() != null ? getDuvalResult().getReason() : "No Data!");
        result.put("threeRatiosTechnique", threeRatiosTechnique() != null ? threeRatiosTechnique().getReason() : "No Data!");
        result.put("iec", iecMethod() != null ? iecMethod().getReason() : "No Data!");
        result.put("rogers", rogersRatioMethod() != null ? rogersRatioMethod().getReason() : "No Data!");
        result.put("doernenburg", doernenburgMethod() != null ? doernenburgMethod().getReason() : "No Data!");
        return result;
    }

    private PossibleResult getDuvalResult(){
        int total = super.getC2h2() + super.getC2h4() + super.getCh4();
        double pC2H4 = (super.getC2h4()/(total == 0 ? 0.000_000_000_000_000_001 : total))*100;
        double pC2H2 = (super.getC2h2()/(total == 0 ? 0.000_000_000_000_000_001 : total))*100;
        double pCh4 = (super.getCh4()/(total == 0 ? 0.000_000_000_000_000_001 : total))*100;
        if(pCh4 >= 98){
            return PossibleResult.PD;
        }
        if(pC2H2 <= 4 && pC2H4 <= 20){
            return PossibleResult.T1;
        }
        if(pC2H2 <=4 && (pC2H4 > 20 && pC2H4 <= 50)){
            return PossibleResult.T2;
        }
        if(pC2H4 > 50 && pC2H2 <= 15){
            return PossibleResult.T3;
        }
        if(
            (pC2H2 > 4 && pC2H2 <= 13 && pC2H4 <= 40) ||
            (pC2H2 > 4 && pC2H2 <= 29 && pC2H4 > 40 && pC2H4 <=50) ||
            (pC2H4 > 50 && pC2H2 <= 29 && pC2H2 > 15)
        ){
            return PossibleResult.DT;
        }
        if(
            (pC2H4 <= 40 && pC2H4 > 23 && pC2H2 <= 29 && pC2H2 > 13) ||
            (pC2H2 > 29 && pC2H4 > 23)
        ){
            return PossibleResult.D2;
        }
        if(pC2H2 > 13 && pC2H4 <= 23){
            return PossibleResult.D1;
        }
        return null;
    }

    private PossibleResult threeRatiosTechnique(){
        double ratio1 = (double) (this.getC2h6() + this.getC2h4())/(this.getH2()+this.getC2h2() != 0 ? this.getH2()+this.getC2h2() : 0.000_000_000_000_000_001);
        double ratio2 = (double) ((this.getC2h2()+this.getCh4()))/(this.getC2h4() != 0 ? this.getC2h4() : 0.000_000_000_000_000_001);
        double ratio3 = (double) (this.getC2h2())/(this.getC2h4() != 0 ? this.getC2h4() : 0.000_000_000_000_000_001);

        if(
                this.getH2() <= 100 && this.getCh4() <= 120 && this.getC2h4() <= 50 && this.getC2h6() <= 65 &&
                        this.getC2h2() <= 1 && this.getCo() <= 350 && this.getCo2() <= 2500
        ){
            return PossibleResult.N;
        }
        if(ratio3 < 0.5 && ratio3 >= 0.05){
            if(ratio1 >= 0.05){
                if(ratio2 < 1){
                    return PossibleResult.T3;
                } else if(ratio2 >= 1 && ratio2 < 3.5){
                    return PossibleResult.T2;
                } else {
                    return PossibleResult.T1;
                }
            } else {
                return PossibleResult.PD1;
            }
        } else if(ratio3 < 0.05){
            if(ratio1 >= 0.05 && ratio1 < 0.9){
                return PossibleResult.T0;
            }
        } else if(ratio3 >= 0.5){
            if(ratio1 < 0.05){
                if(ratio2 > 1 && ratio2 < 3.5){
                    return PossibleResult.PD2;
                } else {
                    return PossibleResult.D2;
                }
            } else if(ratio1 >= 0.05 && ratio1 < 0.9){
                if(ratio2 <= 3.5){
                    return PossibleResult.D2;
                } else {
                    return PossibleResult.D1;
                }
            } else {
                if(ratio2 > 3.5){
                    return PossibleResult.D1;
                } else {
                    return PossibleResult.DT;
                }
            }
        }
        return null;
    }

    private PossibleResult doernenburgMethod(){
        if(this.getH2() <= 100 && this.getCh4() <= 150 && this.getCo() <= 350 && this.getC2h2() <= 35
        && this.getC2h4() <= 50 && this.getC2h6() <= 65){
            return null;
        } else {
            if(this.getR1() > 1 && this.getR2() < 0.75 && this.getR4() < 0.3 && this.getR3() > 0.4){
                return PossibleResult.DT;
            }
            if(this.getR1() < 0.1 && this.getR4() < 0.3 && this.getR3() > 0.4) {
                return PossibleResult.D1;
            }
            if((this.getR1() >= 0.1 && this.getR1() <= 1) && this.getR2() >= 0.75 && this.getR4() >= 0.3 && this.getR3() <= 0.4){
                return PossibleResult.D2;
            }
        }
        return null;
    }

    private PossibleResult rogersRatioMethod(){
        if(this.getR2() < 0.1 && this.getR1() > 0.1 && this.getR1() < 1 && this.getR5() < 1){
            return PossibleResult.N;
        }
        if(this.getR2() < 0.1 && this.getR1() < 0.1 && this.getR5() < 1){
            return PossibleResult.PD1;
        }
        if(this.getR2() >= 0.1 && this.getR2() <= 3 && this.getR1() >= 0.1 && this.getR1() <= 1 && this.getR5() > 3){
            return PossibleResult.PD2;
        }
        if(this.getR2() < 0.1 && this.getR1() > 0.1 && this.getR1() < 1 && this.getR5() >= 1 && this.getR5() <= 3){
            return PossibleResult.T1;
        }
        if(this.getR2() < 0.1 && this.getR1() > 1 && this.getR5() >= 1 && this.getR5() <= 3){
            return PossibleResult.T2;
        }
        if(this.getR2() < 0.1 && this.getR1() > 1 && this.getR5() > 3){
            return PossibleResult.T3;
        }
        return null;
    }

    private PossibleResult iecMethod(){
        int codR2=0, codR1=0, codR5=0, result;
        if(this.getR2() >= 0.1 && this.getR2() <= 3){
            codR2 = 100;
        } else if(this.getR2() > 3){
            codR2 = 200;
        }
        if(this.getR1() < 0.1){
            codR1 = 10;
        } else if(this.getR1() >= 1){
            codR1 = 20;
        }
        if(this.getR5() >= 1 && this.getR5() <= 3){
            codR5 = 1;
        } else if(this.getR5() > 3){
            codR5 = 2;
        }
        result = codR2 + codR1 + codR5;
        return switch (result) {
            case 0 -> PossibleResult.N;
            case 10 -> PossibleResult.PD1;
            case 110 -> PossibleResult.PD2;
            case 101, 201, 202 -> PossibleResult.D1;
            case 102 -> PossibleResult.D2;
            case 1 -> PossibleResult.T0;
            case 20 -> PossibleResult.T1;
            case 21 -> PossibleResult.T2;
            case 22 -> PossibleResult.T3;
            default -> null;
        };
    }
}
