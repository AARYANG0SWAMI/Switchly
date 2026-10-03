package live.switchly.api.exception;

//Two flags in the same project can't share a key.
public class ConflictException extends RuntimeException{

        public ConflictException(String message){
        super(message);
    }
}
