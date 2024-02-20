package com.backend.tcatool.mapper;

import com.backend.tcatool.domain.TransformerAnalysis;
import com.backend.tcatool.dto.TransformerAnalysisDto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TransformerAnalysisMapperImpl {

    private static final String DATE_FORMAT_STRING = "dd-MM-yyyy";

    private static String fromDateToString(Date date){
        if(date == null){
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_STRING);
        return formatter.format(date);
    }

    private static Date fromStringToDate(String date) throws Exception{
        if(date == null){
            return null;
        }
        if(date.isBlank() || date.isEmpty()){
            return null;
        }
        return new SimpleDateFormat(DATE_FORMAT_STRING).parse(date);
    }

    public static TransformerAnalysisDto fromEntityToDtoGet(TransformerAnalysis entity){
        TransformerAnalysisDto transformerAnalysisDto = new TransformerAnalysisDto();
        transformerAnalysisDto.setId(entity.getId());
        transformerAnalysisDto.setC2h2(entity.getC2h2());
        transformerAnalysisDto.setC2h4(entity.getC2h4());
        transformerAnalysisDto.setC2h6(entity.getC2h6());
        transformerAnalysisDto.setCh4(entity.getCh4());
        transformerAnalysisDto.setCo(entity.getCo());
        transformerAnalysisDto.setCo2(entity.getCo2());
        transformerAnalysisDto.setH2(entity.getH2());
        transformerAnalysisDto.setO2(entity.getO2());
        transformerAnalysisDto.setN2(entity.getN2());
        transformerAnalysisDto.setOilTemperature(entity.getOilTemperature());
        transformerAnalysisDto.setSampleDate(fromDateToString(entity.getSampleDate()));
        transformerAnalysisDto.setTransformerDataDto(TransformerDataMapperImpl.entityToDtoForGet(entity.getTransformerData()));
        return transformerAnalysisDto;
    }

    public static TransformerAnalysis fromDtoToEntityPost(TransformerAnalysisDto dto) throws Exception {
        TransformerAnalysis entity = new TransformerAnalysis();
        entity.setC2h2(dto.getC2h2());
        entity.setC2h4(dto.getC2h4());
        entity.setC2h6(dto.getC2h6());
        entity.setCh4(dto.getCh4());
        entity.setCo(dto.getCo());
        entity.setCo2(dto.getCo2());
        entity.setH2(dto.getH2());
        entity.setO2(dto.getO2());
        entity.setN2(dto.getN2());
        entity.setOilTemperature(dto.getOilTemperature());
        entity.setSampleDate(fromStringToDate(dto.getSampleDate()));
        entity.setTransformerData(TransformerDataMapperImpl.dtoToEntityForInherited(dto.getTransformerDataDto()));
        return entity;
    }

    public static TransformerAnalysis fromDtoToEntityPut(TransformerAnalysisDto dto) throws Exception{
        TransformerAnalysis entity = new TransformerAnalysis();
        entity.setC2h2(dto.getC2h2());
        entity.setC2h4(dto.getC2h4());
        entity.setC2h6(dto.getC2h6());
        entity.setCh4(dto.getCh4());
        entity.setCo(dto.getCo());
        entity.setCo2(dto.getCo2());
        entity.setH2(dto.getH2());
        entity.setO2(dto.getO2());
        entity.setN2(dto.getN2());
        entity.setOilTemperature(dto.getOilTemperature());
        entity.setSampleDate(fromStringToDate(dto.getSampleDate()));
        entity.setTransformerData(TransformerDataMapperImpl.dtoToEntityForInherited(dto.getTransformerDataDto()));
        entity.setId(dto.getId());
        return entity;
    }
}
