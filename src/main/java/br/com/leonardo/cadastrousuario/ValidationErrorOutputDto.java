package br.com.leonardo.cadastrousuario;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorOutputDto {

    private List<String> globalErrorsMessages = new ArrayList<>();
    private List<FieldErrorsOutputDto> fieldErrors = new ArrayList<>();

    public void addError(String messages) {
        globalErrorsMessages.add(messages);
    }

    public void addFieldErrors(String field, String message) {
        FieldErrorsOutputDto fieldError = new FieldErrorsOutputDto(field, message);
        fieldErrors.add(fieldError);
    }

    public List<String> getGlobalErrorsMessages() {
        return globalErrorsMessages;
    }

    public List<FieldErrorsOutputDto> getFieldErrors() {
        return fieldErrors;
    }

    public int getNumberOfErrors() {
        return this.globalErrorsMessages.size() + this.fieldErrors.size();
    }
}
