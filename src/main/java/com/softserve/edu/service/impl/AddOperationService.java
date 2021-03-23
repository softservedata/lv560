package com.softserve.edu.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.softserve.edu.service.OperationService;

@Service
@Qualifier("AddOperationService")
public class AddOperationService implements OperationService {

    public String operation(String arg0, String arg1) {
        String result = null;
        try {
            result = String.valueOf(Integer.valueOf(arg0) + Integer.valueOf(arg1));
        } catch (NumberFormatException e) {
            result = e.toString();
        }
        return result;
    }

}
