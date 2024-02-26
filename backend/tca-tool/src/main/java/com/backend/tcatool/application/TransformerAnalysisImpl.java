package com.backend.tcatool.application;

import com.backend.tcatool.Exception.NoRecordsFoundException;
import com.backend.tcatool.Exception.NoTransformerExistingException;
import com.backend.tcatool.domain.TransformerAnalysis;
import com.backend.tcatool.dto.TransformerAnalysisDto;
import com.backend.tcatool.error.ErrorType;
import com.backend.tcatool.mapper.TransformerAnalysisMapperImpl;
import com.backend.tcatool.methods.Results;
import com.backend.tcatool.repository.TransformerAnalysisRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TransformerAnalysisImpl implements TransformerAnalysisService{

    private final TransformerAnalysisRepository repository;

    public TransformerAnalysisImpl(TransformerAnalysisRepository repository) {
        this.repository = repository;
    }


    @Override
    public Object addNewTransformerAnalysis(TransformerAnalysisDto analysis) {
        Object res = null;
        boolean isTransformerExisting = true;
        try{
            isTransformerExisting = repository.isTransformerExisting(analysis.getTransformerDataDto().getTransformerCode());
            if(!isTransformerExisting){
                throw new NoTransformerExistingException(
                        String.format("There are no transformer with code %s", analysis.getTransformerDataDto().getTransformerCode())
                );
            }
            Map<String, String> results = analiseTransformerConditions(analysis);
            Map<String, Object> totalResults = new HashMap<>();
            totalResults.put("results", results);
            repository.save(TransformerAnalysisMapperImpl.fromDtoToEntityPost(analysis));
            totalResults.put("response", "Transformer Analysis Successfully added!");
            res = totalResults;
        } catch (NoTransformerExistingException e){
            res = new ErrorType(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e){
            res = new ErrorType(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return res;
    }

    @Override
    public Object getTransformerAnalysis() {
        Object res = null;
        try {
            List<TransformerAnalysis> data = repository.findAll();
            if (data.isEmpty()) {
                throw new NoRecordsFoundException("There are no records");
            }
            List<TransformerAnalysisDto> listAnalysis = new ArrayList<>();
            for (TransformerAnalysis value : data) {
                listAnalysis.add(TransformerAnalysisMapperImpl.fromEntityToDtoGet(value));
            }
            res = listAnalysis;
        } catch (NoRecordsFoundException e){
            res = new ErrorType(HttpStatus.NO_CONTENT, e.getMessage());
        } catch (Exception e){
            res = new ErrorType(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return res;
    }

    @Override
    public Object getTransformerAnalysisByTransformerCode(String transformerCode) {
        Object res = null;
        try{
            boolean isTransformerExisting = repository.isTransformerExisting(transformerCode);
            if(!isTransformerExisting){
                throw new NoRecordsFoundException("There are no transformers with code " + transformerCode);
            }
            res = TransformerAnalysisMapperImpl.fromEntityToDtoGet(repository.findByCode(transformerCode));
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
            res = TransformerAnalysisMapperImpl.fromEntityToDtoGet(data);
        } catch (NoRecordsFoundException e){
            res = new ErrorType(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e){
            res = new ErrorType(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return res;
    }

    @Override
    public Object updateTransformerAnalysis(long id, TransformerAnalysisDto analysis) {
        Object res = null;
        try{
            if(!Objects.equals(id, analysis.getId())){
                throw new IllegalArgumentException("The ids don't match");
            }
            TransformerAnalysis value = repository.findById(id).orElse(null);
            if (value == null) {
                throw new NoRecordsFoundException(String.format("There are no records with id %s", id));
            }
            value = TransformerAnalysisMapperImpl.fromDtoToEntityPut(analysis);
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
    public Object deleteTransformerAnalysis(long id) {
        Object res = null;
        try{
            TransformerAnalysis value = repository.findById(id).orElse(null);
            if (value == null) {
                throw new NoRecordsFoundException(String.format("There are no records with id %s", id));
            }
            repository.deleteById(id);
        } catch (NoRecordsFoundException e){
            res = new ErrorType(HttpStatus.NO_CONTENT, e.getMessage());
        } catch (Exception e){
            res = new ErrorType(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return res;
    }

    public Map<String, String> analiseTransformerConditions(TransformerAnalysisDto transformerAnalysisDto){
        Results results = new Results(
                transformerAnalysisDto.getCh4(),
                transformerAnalysisDto.getH2(),
                transformerAnalysisDto.getC2h2(),
                transformerAnalysisDto.getC2h4(),
                transformerAnalysisDto.getC2h6(),
                transformerAnalysisDto.getCo(),
                transformerAnalysisDto.getCo2()
        );
        return results.getResults();
    }
}
