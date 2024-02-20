package com.backend.tcatool.mapper;

import com.backend.tcatool.domain.TransformerData;
import com.backend.tcatool.domain.enums.VoltageType;
import com.backend.tcatool.dto.TransformerDataDto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TransformerDataMapperImpl {

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

    public static TransformerDataDto entityToDtoForGet(TransformerData transformerData) {
        TransformerDataDto transformerDataDto = new TransformerDataDto();
        transformerDataDto.setTransformerId(transformerData.getTransformerId());
        transformerDataDto.setTransformerCode(transformerData.getTransformerCode());
        transformerDataDto.setTransformerType(transformerData.getTransformerType());
        transformerDataDto.setInstallationType(transformerData.getInstallationType());
        transformerDataDto.setVoltageType(transformerData.getVoltageType());
        transformerDataDto.setVoltage(transformerData.getVoltage());
        transformerDataDto.setPower(transformerData.getPower());
        transformerDataDto.setSubstationName(transformerData.getSubstationName());
        transformerDataDto.setCountry(transformerData.getCountry());
        transformerDataDto.setRegion(transformerData.getRegion());
        transformerDataDto.setCity(transformerData.getCity());
        transformerDataDto.setLastAnalysis(fromDateToString(transformerData.getLastAnalysis()));
        transformerDataDto.setIsOperating(transformerData.getIsOperating());
        transformerDataDto.setLastUpdate(fromDateToString(transformerData.getLastUpdate()));
        return transformerDataDto;
    }

    public static TransformerData dtoToEntityForInherited(TransformerDataDto dto) throws Exception {
        TransformerData entity = new TransformerData();
        entity.setTransformerId(dto.getTransformerId());
        entity.setTransformerCode(dto.getTransformerCode());
        entity.setTransformerType(dto.getTransformerType());
        entity.setInstallationType(dto.getInstallationType());
        entity.setVoltageType(dto.getVoltageType());
        entity.setVoltage(dto.getVoltage());
        entity.setPower(dto.getPower());
        entity.setSubstationName(dto.getSubstationName());
        entity.setCountry(dto.getCountry());
        entity.setRegion(dto.getRegion());
        entity.setCity(dto.getCity());
        entity.setLastAnalysis(fromStringToDate(dto.getLastAnalysis()));
        entity.setIsOperating(dto.getIsOperating());
        entity.setLastUpdate(fromStringToDate(dto.getLastUpdate()));
        return entity;
    }

    public static TransformerData dtoToEntityForPost(TransformerDataDto dto) throws Exception {
        TransformerData transformerData = new TransformerData();
        transformerData.setTransformerCode(dto.getTransformerCode());
        transformerData.setCity(dto.getCity());
        transformerData.setCountry(dto.getCountry());
        transformerData.setInstallationType(dto.getInstallationType());
        transformerData.setIsOperating(dto.getIsOperating());
        transformerData.setLastAnalysis(fromStringToDate(dto.getLastAnalysis()));
        transformerData.setPower(dto.getPower());
        transformerData.setRegion(dto.getRegion());
        transformerData.setSubstationName(dto.getSubstationName());
        transformerData.setTransformerType(dto.getTransformerType());
        transformerData.setVoltage(dto.getVoltage());
        if(dto.getVoltage() <= 1){
            transformerData.setVoltageType(VoltageType.LOW);
        } else if(dto.getVoltage() > 1 && dto.getVoltage() <= 34.5){
            transformerData.setVoltageType(VoltageType.MEDIUM);
        } else if (dto.getVoltage() > 34.5 && dto.getVoltage() <= 230){
            transformerData.setVoltageType(VoltageType.HIGH);
        } else {
            transformerData.setVoltageType(VoltageType.VERY_HIGH);
        }
        transformerData.setLastUpdate(new Date());
        return transformerData;
    }

    public static TransformerData dtoToEntityForPut(TransformerDataDto dto) throws Exception {
        TransformerData transformerData = new TransformerData();
        transformerData.setTransformerCode(dto.getTransformerCode());
        transformerData.setCity(dto.getCity());
        transformerData.setCountry(dto.getCountry());
        transformerData.setInstallationType(dto.getInstallationType());
        transformerData.setIsOperating(dto.getIsOperating());
        transformerData.setLastAnalysis(fromStringToDate(dto.getLastAnalysis()));
        transformerData.setPower(dto.getPower());
        transformerData.setRegion(dto.getRegion());
        transformerData.setSubstationName(dto.getSubstationName());
        transformerData.setTransformerType(dto.getTransformerType());
        transformerData.setVoltage(dto.getVoltage());
        if(dto.getVoltage() <= 1){
            transformerData.setVoltageType(VoltageType.LOW);
        } else if(dto.getVoltage() > 1 && dto.getVoltage() <= 34.5){
            transformerData.setVoltageType(VoltageType.MEDIUM);
        } else if (dto.getVoltage() > 34.5 && dto.getVoltage() <= 230){
            transformerData.setVoltageType(VoltageType.HIGH);
        } else {
            transformerData.setVoltageType(VoltageType.VERY_HIGH);
        }
        transformerData.setLastUpdate(new Date());
        transformerData.setTransformerId(dto.getTransformerId());
        return transformerData;
    }
}
