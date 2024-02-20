package com.backend.tcatool.controller;

import com.backend.tcatool.application.TransformerDataImpl;
import com.backend.tcatool.constants.PathConstants;
import com.backend.tcatool.dto.TransformerDataDto;
import com.backend.tcatool.error.ErrorType;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(PathConstants.TRANSFORMER_DATA_ENDPOINT)
public class TransformerDataController {

    @Autowired
    private TransformerDataImpl service;

    @PostMapping(PathConstants.ADD_DATA)
    public ResponseEntity<Object> addTransformerData(
            @Valid @RequestBody TransformerDataDto dataDto
            ){
        Object res = service.addNewTransformer(dataDto);
        if(res instanceof ErrorType){
            ErrorType err = (ErrorType) res;
            int errorCode = Integer.valueOf(err.getHttpCode());
            return new ResponseEntity<>(err.getMoreInformation(), HttpStatusCode.valueOf(errorCode));
        } else {
            return new ResponseEntity<>("Transformer Successfully Added!", HttpStatus.CREATED);
        }
    }

    @GetMapping(PathConstants.GET_DATA)
    public ResponseEntity<Object> getTransformers(){
        Object res = service.getTransformers();
        if(res instanceof ErrorType){
            ErrorType err = (ErrorType) res;
            int errorCode = Integer.valueOf(err.getHttpCode());
            return new ResponseEntity<>(err.getMoreInformation(), HttpStatusCode.valueOf(errorCode));
        } else {
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
    }

    @GetMapping(PathConstants.GET_DATA_BY_ID)
    public ResponseEntity<Object> getTransformerById(@PathVariable("id") Integer id){
        Object res = service.getTransformerById(id);
        if(res instanceof ErrorType){
            ErrorType err = (ErrorType) res;
            int errorCode = Integer.valueOf(err.getHttpCode());
            return new ResponseEntity<>(err.getMoreInformation(), HttpStatusCode.valueOf(errorCode));
        } else {
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
    }

    @PutMapping(PathConstants.UPDATE_DATA)
    public ResponseEntity<Object> updateTransformer(
            @PathVariable("id") Integer id,
            @Valid @RequestBody TransformerDataDto transformer
            ){
        Object res = service.updateTransformer(id, transformer);
        if(res instanceof ErrorType){
            ErrorType err = (ErrorType) res;
            int errorCode = Integer.valueOf(err.getHttpCode());
            return new ResponseEntity<>(err.getMoreInformation(), HttpStatusCode.valueOf(errorCode));
        } else {
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
    }

    @DeleteMapping(PathConstants.DELETE_DATA)
    public ResponseEntity<Object> deleteTransformer(
            @PathVariable("id") Integer id
    ){
        Object res = service.deleteTransformer(id);
        if(res instanceof ErrorType){
            ErrorType err = (ErrorType) res;
            int errorCode = Integer.valueOf(err.getHttpCode());
            return new ResponseEntity<>(err.getMoreInformation(), HttpStatusCode.valueOf(errorCode));
        } else {
            return new ResponseEntity<>("Transformer Successfully Deleted", HttpStatus.OK);
        }
    }
}
