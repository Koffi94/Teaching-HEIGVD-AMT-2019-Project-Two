package ch.heigvd.amt.project2.api.exceptions;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-16T19:36:34.802Z")

public class EntityNotFoundException extends Exception {
    private int code;
    public EntityNotFoundException (int code, String msg) {
        super(msg);
        this.code = code;
    }
}
