package br.com.leonardo.cadastrousuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ValidationErrorHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ValidationErrorOutputDto handleValidatioError(MethodArgumentNotValidException exception) {
        List<ObjectError> globalErrors = exception.getBindingResult().getGlobalErrors();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        return buildValidationErros(globalErrors,fieldErrors);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ValidationErrorOutputDto handleValidatioError(BindException exception) {
        List<ObjectError> globalErrors = exception.getBindingResult().getGlobalErrors();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        return buildValidationErros(globalErrors,fieldErrors);
    }

    private ValidationErrorOutputDto buildValidationErros(List<ObjectError> globalErrors, List<FieldError> fieldErrors) {
        ValidationErrorOutputDto validationErrors = new ValidationErrorOutputDto();
        globalErrors.forEach(error -> validationErrors.addError(getErrorMessage(error)));
        fieldErrors.forEach(error -> {
            String errorMessage = getErrorMessage(error);
            validationErrors.addFieldErrors(error.getField(), errorMessage);
        });
        return validationErrors;
    }

    private String getErrorMessage(ObjectError error) {
        return messageSource.getMessage(error, LocaleContextHolder.getLocale());
    }
}
