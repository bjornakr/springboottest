package no.bjornakr.desertsnake.create_respondent;

public class ErrorResponseDto {
    private String errorMessage;

    public ErrorResponseDto() {}

    public ErrorResponseDto(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
