package sample.model.exceptions;

public class HttpException extends Exception {
    public int code;
    public HttpException(int code) {
        this.code = code;
    }
}
