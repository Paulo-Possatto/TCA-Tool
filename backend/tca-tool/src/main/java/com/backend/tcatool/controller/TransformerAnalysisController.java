package com.backend.tcatool.controller;

import com.backend.tcatool.application.TransformerAnalysisImpl;
import com.backend.tcatool.constants.PathConstants;
import com.backend.tcatool.dto.TransformerAnalysisDto;
import com.backend.tcatool.error.ErrorType;
import com.backend.tcatool.methods.commons.PossibleResult;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(PathConstants.TRANSFORMER_ANALYSIS_ENDPOINT)
public class TransformerAnalysisController {

    @Autowired
    private TransformerAnalysisImpl service;

    @PostMapping(PathConstants.ADD_DATA)
    public ResponseEntity<Object> addAnalysis(
            @Valid @RequestBody TransformerAnalysisDto analysis
            ){
        Object res = service.addNewTransformerAnalysis(analysis);
        if(res instanceof ErrorType){
            ErrorType err = (ErrorType) res;
            int errorCode = Integer.valueOf(err.getHttpCode());
            return new ResponseEntity<>(err.getMoreInformation(), HttpStatusCode.valueOf(errorCode));
        } else {
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        }
    }

    @GetMapping(PathConstants.GET_DATA_BY_ID)
    public ResponseEntity<Object> getAnalysisById(
            @PathVariable("id") long id
    ){
        Object res = service.getTransformerAnalysisById(id);
        if(res instanceof ErrorType){
            ErrorType err = (ErrorType) res;
            int errorCode = Integer.valueOf(err.getHttpCode());
            return new ResponseEntity<>(err.getMoreInformation(), HttpStatusCode.valueOf(errorCode));
        } else {
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
    }

    @GetMapping(PathConstants.GET_DATA_BY_CODE)
    public ResponseEntity<Object> getAnalysisByTransformerCode(
            @PathVariable("code") String transformerCode
    ){
        Object res = service.getTransformerAnalysisByTransformerCode(transformerCode);
        if(res instanceof ErrorType){
            ErrorType err = (ErrorType) res;
            int errorCode = Integer.valueOf(err.getHttpCode());
            return new ResponseEntity<>(err.getMoreInformation(), HttpStatusCode.valueOf(errorCode));
        } else {
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
    }
}
