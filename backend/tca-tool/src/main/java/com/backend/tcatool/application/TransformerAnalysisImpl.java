package com.backend.tcatool.application;

import com.backend.tcatool.Exception.NoRecordsFoundException;
import com.backend.tcatool.Exception.NoTransformerExistingException;
import com.backend.tcatool.domain.TransformerAnalysis;
import com.backend.tcatool.domain.TransformerData;
import com.backend.tcatool.dto.transformeranalysis.TransformerAnalysisGetDto;
import com.backend.tcatool.dto.transformeranalysis.TransformerAnalysisPostDto;
import com.backend.tcatool.dto.transformeranalysis.TransformerAnalysisPutDto;
import com.backend.tcatool.dto.transformerdata.TransformerDataGetDto;
import com.backend.tcatool.error.ErrorType;
import com.backend.tcatool.repository.TransformerAnalysisRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Service
public class TransformerAnalysisImpl implements TransformerAnalysisService{

    private final TransformerAnalysisRepository repository;

    public TransformerAnalysisImpl(TransformerAnalysisRepository repository) {
        this.repository = repository;
    }


    @Override
    public Object addNewTransformerAnalysis(TransformerAnalysisPostDto analysis) {
        Object res = null;
        boolean isTransformerExisting = true;
        try{
            isTransformerExisting = repository.isTransformerExisting(analysis.getTransformerDataGetDto().getTransformerCode());
            if(!isTransformerExisting){
                throw new NoTransformerExistingException(
                        String.format("There are no transformer with code %s", analysis.getTransformerDataGetDto().getTransformerCode())
                );
            }
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
            Date dateLastAnalysis = null;
            Date dateLastUpdate = null;
            if(analysis.getTransformerDataGetDto().getLastAnalysis()!=null){
                dateLastAnalysis = formatter.parse(analysis.getTransformerDataGetDto().getLastAnalysis());
            }
            if(analysis.getTransformerDataGetDto().getLastUpdate()!=null){
                dateLastUpdate = formatter.parse(analysis.getTransformerDataGetDto().getLastUpdate());
            }

            TransformerAnalysis entity = new TransformerAnalysis();
            TransformerData data = new TransformerData();

            data.setLastAnalysis(dateLastAnalysis);
            data.setTransformerType(analysis.getTransformerDataGetDto().getTransformerType());
            data.setTransformerCode(analysis.getTransformerDataGetDto().getTransformerCode());
            data.setRegion(analysis.getTransformerDataGetDto().getRegion());
            data.setPower(analysis.getTransformerDataGetDto().getPower());
            data.setVoltage(analysis.getTransformerDataGetDto().getVoltage());
            data.setSubstationName(analysis.getTransformerDataGetDto().getSubstationName());
            data.setTransformerId(analysis.getTransformerDataGetDto().getTransformerId());
            data.setInstallationType(analysis.getTransformerDataGetDto().getInstallationType());
            data.setVoltageType(analysis.getTransformerDataGetDto().getVoltageType());
            data.setCountry(analysis.getTransformerDataGetDto().getCountry());
            data.setCity(analysis.getTransformerDataGetDto().getCity());
            data.setIsOperating(analysis.getTransformerDataGetDto().getIsOperating());
            data.setLastUpdate(dateLastUpdate);

            entity.setC2h2(analysis.getC2h2());
            entity.setC2h4(analysis.getC2h4());
            entity.setC2h6(analysis.getC2h6());
            entity.setCh4(analysis.getCh4());
            entity.setCo(analysis.getCo());
            entity.setCo2(analysis.getCo2());
            entity.setH2(analysis.getH2());
            entity.setN2(analysis.getN2());
            entity.setO2(analysis.getO2());
            entity.setOilTemperature(analysis.getOilTemperature());
            entity.setSampleDate(analysis.getSampleDate());
            entity.setTransformerData(data);

            res = repository.save(entity);
        } catch (NoTransformerExistingException e){
            res = new ErrorType(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e){
            res = new ErrorType(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return res;
    }

    @Override
    public Object getTransformerAnalysis() {
        return null;
    }

    @Override
    public Object getTransformerAnalysisByTransformerCode(String transformerCode) {
        Object res = null;
        try{
            boolean isTransformerExisting = repository.isTransformerExisting(transformerCode);
            if(!isTransformerExisting){
                throw new NoRecordsFoundException("There are no transformers with code " + transformerCode);
            }
            TransformerAnalysis data = repository.findByCode(transformerCode);
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

            TransformerAnalysisGetDto getDto = new TransformerAnalysisGetDto();
            TransformerDataGetDto dataGetDto = new TransformerDataGetDto();

            dataGetDto.setTransformerId(data.getTransformerData().getTransformerId());
            dataGetDto.setTransformerCode(data.getTransformerData().getTransformerCode());
            dataGetDto.setTransformerType(data.getTransformerData().getTransformerType());
            dataGetDto.setLastAnalysis(
                    data.getTransformerData().getLastAnalysis() != null ?
                        formatter.format(data.getTransformerData().getLastAnalysis()) :
                        null
            );
            dataGetDto.setVoltage(data.getTransformerData().getVoltage());
            dataGetDto.setCountry(data.getTransformerData().getCountry());
            dataGetDto.setCity(data.getTransformerData().getCity());
            dataGetDto.setInstallationType(data.getTransformerData().getInstallationType());
            dataGetDto.setIsOperating(data.getTransformerData().getIsOperating());
            dataGetDto.setPower(data.getTransformerData().getPower());
            dataGetDto.setRegion(data.getTransformerData().getRegion());
            dataGetDto.setSubstationName(data.getTransformerData().getSubstationName());
            dataGetDto.setVoltageType(data.getTransformerData().getVoltageType());
            dataGetDto.setLastUpdate(
                    data.getTransformerData().getLastUpdate() != null ?
                            formatter.format(data.getTransformerData().getLastUpdate()) :
                            null
            );

            getDto.setId(data.getId());
            getDto.setC2h2(data.getC2h2());
            getDto.setC2h4(data.getC2h4());
            getDto.setC2h6(data.getC2h6());
            getDto.setCo(data.getCo());
            getDto.setCh4(data.getCh4());
            getDto.setCo2(data.getCo2());
            getDto.setH2(data.getH2());
            getDto.setO2(data.getO2());
            getDto.setN2(data.getN2());
            getDto.setOilTemperature(data.getOilTemperature());
            getDto.setSampleDate(
                    data.getSampleDate() != null ?
                            formatter.format(data.getSampleDate()) :
                            null);
            getDto.setTransformerDataGetDto(dataGetDto);

            res = getDto;
        } catch (NoRecordsFoundException e){
            res = new ErrorType(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e){
            res = new ErrorType(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return res;
    }

    @Override
    public Object getTransformerAnalysisById(long id) {
        Object res = null;
        try{
            TransformerAnalysis data = repository.findById(id).orElse(null);
            if(data == null){
                throw new NoRecordsFoundException("No analysis with id " + id);
            }
            TransformerAnalysisGetDto getDto = new TransformerAnalysisGetDto();

            TransformerDataGetDto dataGetDto = new TransformerDataGetDto();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

            dataGetDto.setLastAnalysis(
                    data.getTransformerData().getLastAnalysis() != null ? formatter.format(data.getTransformerData().getLastAnalysis()) : null);
            dataGetDto.setTransformerType(data.getTransformerData().getTransformerType());
            dataGetDto.setTransformerCode(data.getTransformerData().getTransformerCode());
            dataGetDto.setRegion(data.getTransformerData().getRegion());
            dataGetDto.setPower(data.getTransformerData().getPower());
            dataGetDto.setVoltage(data.getTransformerData().getVoltage());
            dataGetDto.setSubstationName(data.getTransformerData().getSubstationName());
            dataGetDto.setTransformerId(data.getTransformerData().getTransformerId());
            dataGetDto.setInstallationType(data.getTransformerData().getInstallationType());
            dataGetDto.setVoltageType(data.getTransformerData().getVoltageType());
            dataGetDto.setCountry(data.getTransformerData().getCountry());
            dataGetDto.setCity(data.getTransformerData().getCity());
            dataGetDto.setIsOperating(data.getTransformerData().getIsOperating());
            dataGetDto.setLastUpdate(data.getTransformerData().getLastUpdate() != null ? formatter.format(data.getTransformerData().getLastUpdate()) : null);

            getDto.setCo(data.getCo());
            getDto.setId(data.getId());
            getDto.setCh4(data.getCh4());
            getDto.setCo2(data.getCo2());
            getDto.setH2(data.getH2());
            getDto.setC2h2(data.getC2h2());
            getDto.setO2(data.getO2());
            getDto.setN2(data.getN2());
            getDto.setC2h6(data.getC2h6());
            getDto.setC2h4(data.getC2h4());
            getDto.setOilTemperature(data.getOilTemperature());
            getDto.setSampleDate(data.getSampleDate() != null ? formatter.format(data.getSampleDate()) : null);
            getDto.setTransformerDataGetDto(dataGetDto);

            res = getDto;
        } catch (NoRecordsFoundException e){
            res = new ErrorType(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e){
            res = new ErrorType(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return res;
    }

    @Override
    public Object updateTransformerAnalysis(long id, TransformerAnalysisPutDto analysis) {
        return null;
    }

    @Override
    public Object deleteTransformerAnalysis(long id) {
        return null;
    }
}
