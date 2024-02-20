package com.backend.tcatool.application;

import com.backend.tcatool.Exception.NoRecordsFoundException;
import com.backend.tcatool.Exception.RecordAlreadyExistsException;
import com.backend.tcatool.domain.TransformerData;
import com.backend.tcatool.dto.TransformerDataDto;
import com.backend.tcatool.error.ErrorType;
import com.backend.tcatool.mapper.TransformerDataMapperImpl;
import com.backend.tcatool.repository.TransformerDataRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TransformerDataImpl implements TransformerDataService {

    private final TransformerDataRepository repository;

    public TransformerDataImpl(TransformerDataRepository repository) {
        this.repository = repository;
    }

    @Override
    public Object addNewTransformer(TransformerDataDto transformer) {
        Object res = null;
        try{
            if(repository.isTransformerExisting(transformer.getTransformerCode())){
                throw new RecordAlreadyExistsException(
                        String.format("Tranformer with code '%s' already exists", transformer.getTransformerCode())
                );
            }
            res = repository.save(TransformerDataMapperImpl.dtoToEntityForPost(transformer));
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
            List<TransformerDataDto> listTransformers = new ArrayList<>();
            for (TransformerData value : data) {
                listTransformers.add(TransformerDataMapperImpl.entityToDtoForGet(value));
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
            res = TransformerDataMapperImpl.entityToDtoForGet(value);
        } catch (NoRecordsFoundException e){
            res = new ErrorType(HttpStatus.NO_CONTENT, e.getMessage());
        } catch (Exception e){
            res = new ErrorType(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return res;
    }

    @Override
    public Object updateTransformer(long id, TransformerDataDto transformer) {
        Object res = null;
        try{
            TransformerData value = repository.findById(id).orElse(null);
            if (value == null) {
                throw new NoRecordsFoundException(String.format("There are no records with id %s", id));
            }
            if(!Objects.equals(id, transformer.getTransformerId())){
                throw new IllegalArgumentException("The ids don't match");
            }
            value = TransformerDataMapperImpl.dtoToEntityForPut(transformer);
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
        } catch (Exception e){
            res = new ErrorType(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return res;
    }
}
