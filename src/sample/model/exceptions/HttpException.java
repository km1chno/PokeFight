package sample.model.exceptions;

public class HttpException extends RuntimeException {
    public int code;
    public HttpException(int code) {
        this.code = code;
    }
}
