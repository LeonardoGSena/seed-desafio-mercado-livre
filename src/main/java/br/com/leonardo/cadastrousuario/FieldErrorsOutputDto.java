package br.com.leonardo.cadastrousuario;

public class FieldErrorsOutputDto {
    private String field;
    private String message;

    public FieldErrorsOutputDto() {
    }

    public FieldErrorsOutputDto(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
