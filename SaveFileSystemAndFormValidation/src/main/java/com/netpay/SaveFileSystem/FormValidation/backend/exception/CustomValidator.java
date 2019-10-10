package com.netpay.SaveFileSystem.FormValidation.backend.exception;

import com.netpay.SaveFileSystem.FormValidation.backend.data.ContactRequest;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class CustomValidator
{
    private List<String> errors = null;

    public void addErrors(String errorMessage){
        if (errors == null)
            errors = new ArrayList<>();

        if (!errors.contains(errorMessage))
            errors.add(errorMessage);
    }

    public boolean validateRequest(ContactRequest contactRequest)
    {
        boolean validRequest = true;
        if (contactRequest.getName() ==null || contactRequest.getName().matches(".*\\d.*")) {
            validRequest = false;
            addErrors("Username is either null or contains invalid characters");
        }
        if(!contactRequest.getPhone().matches("\\d{10}")) {
            validRequest = false;
            addErrors("Phone number is either null or contains invalid characters");
        }

        if(!contactRequest.getEmail().matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")) {
            validRequest = false;
            addErrors("Email number is either null or invalid");
        }

        if (errors !=null && errors.size() > 0) {
            throw new ApplicationException(errors, HttpStatus.BAD_REQUEST);
        }

        return validRequest;
    }
}
