package com.backend.tcatool.application;

import com.backend.tcatool.Exception.NoRecordsFoundException;
import com.backend.tcatool.Exception.RecordAlreadyExistsException;
import com.backend.tcatool.domain.TransformerData;
import com.backend.tcatool.domain.enums.VoltageType;
import com.backend.tcatool.dto.transformerdata.TransformerDataGetDto;
import com.backend.tcatool.dto.transformerdata.TransformerDataPostDto;
import com.backend.tcatool.dto.transformerdata.TransformerDataPutDto;
import com.backend.tcatool.error.ErrorType;
import com.backend.tcatool.repository.TransformerDataRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class TransformerDataImpl implements TransformerDataService {

    private final TransformerDataRepository repository;

    public TransformerDataImpl(TransformerDataRepository repository) {
        this.repository = repository;
    }

    @Override
    public Object addNewTransformer(TransformerDataPostDto transformer) {
        Object res = null;
        try{
            if(repository.isTransformerExisting(transformer.getTransformerCode())){
                throw new RecordAlreadyExistsException(
                        String.format("Tranformer with code '%s' already exists", transformer.getTransformerCode())
                );
            }
            TransformerData data = new TransformerData();
            Double volt = transformer.getVoltage();
            if(volt <= 1){
                data.setVoltageType(VoltageType.LOW);
            } else if(volt > 1 && volt <= 34.5){
                data.setVoltageType(VoltageType.MEDIUM);
            } else if (volt > 34.5 && volt <= 230){
                data.setVoltageType(VoltageType.HIGH);
            } else {
                data.setVoltageType(VoltageType.VERY_HIGH);
            }
            data.setTransformerCode(transformer.getTransformerCode());
            data.setTransformerType(transformer.getTransformerType());
            data.setInstallationType(transformer.getInstallationType());
            data.setVoltage(transformer.getVoltage());
            data.setPower(transformer.getPower());
            data.setSubstationName(transformer.getSubstationName());
            data.setCountry(transformer.getCountry());
            data.setRegion(transformer.getRegion());
            data.setCity(transformer.getCity());
            data.setLastAnalysis(null);
            data.setIsOperating(transformer.getIsOperating());
            data.setLastUpdate(new Date());
            res = repository.save(data);
        } catch (RecordAlreadyExistsException e) {
            res = new ErrorType(HttpStatus.CONFLICT, e.getMessage());
        } catch (Exception e){
            res = new ErrorType(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return res;
    }

    @Override
    public Object getTransformers() {
        Object res = null;
        try {
            List<TransformerData> data = repository.findAll();
            if (data.isEmpty()) {
                throw new NoRecordsFoundException("There are no records");
            }
            List<TransformerDataGetDto> listTransformers = new ArrayList<>();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            for (TransformerData value : data) {
                TransformerDataGetDto dataGetDto = new TransformerDataGetDto();
                dataGetDto.setTransformerId(value.getTransformerId());
                dataGetDto.setCity(value.getCity());
                dataGetDto.setPower(value.getPower());
                dataGetDto.setTransformerType(value.getTransformerType());
                dataGetDto.setTransformerCode(value.getTransformerCode());
                dataGetDto.setCountry(value.getCountry());
                dataGetDto.setRegion(value.getRegion());
                dataGetDto.setVoltage(value.getVoltage());
                dataGetDto.setLastAnalysis(value.getLastAnalysis() != null ? formatter.format(value.getLastAnalysis()) : null);
                dataGetDto.setInstallationType(value.getInstallationType());
                dataGetDto.setIsOperating(value.getIsOperating());
                dataGetDto.setSubstationName(value.getSubstationName());
                dataGetDto.setVoltageType(value.getVoltageType());
                dataGetDto.setLastUpdate(value.getLastUpdate() != null ? formatter.format(value.getLastUpdate()) : null);
                listTransformers.add(dataGetDto);
            }
            res = listTransformers;
        } catch (NoRecordsFoundException e){
            res = new ErrorType(HttpStatus.NO_CONTENT, e.getMessage());
        } catch (Exception e){
            res = new ErrorType(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return res;
    }

    @Override
    public Object getTransformerById(long id) {
        Object res = null;
        try {
            TransformerData value = repository.findById(id).orElse(null);
            if (value == null) {
                throw new NoRecordsFoundException(String.format("There are no records with id = %s", id));
            }
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            TransformerDataGetDto dataGetDto = new TransformerDataGetDto();
            dataGetDto.setTransformerId(value.getTransformerId());
            dataGetDto.setCity(value.getCity());
            dataGetDto.setPower(value.getPower());
            dataGetDto.setTransformerType(value.getTransformerType());
            dataGetDto.setTransformerCode(value.getTransformerCode());
            dataGetDto.setCountry(value.getCountry());
            dataGetDto.setRegion(value.getRegion());
            dataGetDto.setVoltage(value.getVoltage());
            dataGetDto.setLastAnalysis(value.getLastAnalysis() != null ? formatter.format(value.getLastAnalysis()) : null);
            dataGetDto.setInstallationType(value.getInstallationType());
            dataGetDto.setIsOperating(value.getIsOperating());
            dataGetDto.setSubstationName(value.getSubstationName());
            dataGetDto.setVoltageType(value.getVoltageType());
            dataGetDto.setLastUpdate(value.getLastUpdate() != null ? formatter.format(value.getLastUpdate()) : null);
            res = dataGetDto;
        } catch (NoRecordsFoundException e){
            res = new ErrorType(HttpStatus.NO_CONTENT, e.getMessage());
        } catch (Exception e){
            res = new ErrorType(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return res;
    }

    @Override
    public Object updateTransformer(long id, TransformerDataPutDto transformer) {
        Object res = null;
        try{
            TransformerData value = repository.findById(id).orElse(null);
            if (value == null) {
                throw new NoRecordsFoundException(String.format("There are no records with id %s", id));
            }
            if(!Objects.equals(id, transformer.getTransformerId())){
                throw new IllegalArgumentException("The ids don't match");
            }
            value.setTransformerId(transformer.getTransformerId());
            value.setCity(transformer.getCity());
            value.setCountry(transformer.getCountry());
            value.setInstallationType(transformer.getInstallationType());
            value.setIsOperating(transformer.getIsOperating());
            value.setLastUpdate(new Date());
            value.setPower(transformer.getPower());
            value.setRegion(transformer.getRegion());
            value.setSubstationName(transformer.getSubstationName());
            value.setTransformerCode(transformer.getTransformerCode());
            value.setTransformerType(transformer.getTransformerType());
            value.setVoltage(transformer.getVoltage());
            value.setLastAnalysis(transformer.getLastAnalysis());
            res = repository.save(value);
        } catch (NoRecordsFoundException e){
            res = new ErrorType(HttpStatus.NO_CONTENT, e.getMessage());
        } catch (IllegalArgumentException e){
            res = new ErrorType(HttpStatus.CONFLICT, e.getMessage());
        } catch (Exception e){
            res = new ErrorType(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return res;
    }

    @Override
    public Object deleteTransformer(long id) {
        Object res = null;
        try{
            TransformerData value = repository.findById(id).orElse(null);
            if (value == null) {
                throw new NoRecordsFoundException(String.format("There are no records with id %s", id));
            }
            repository.deleteById(id);
        } catch (NoRecordsFoundException e){
            res = new ErrorType(HttpStatus.NO_CONTENT, e.getMessage());
        }
        return res;
    }
}
