package ch.cheorges.exception;

public class FunctionIdentifierMissmatchException extends RuntimeException {
   public FunctionIdentifierMissmatchException() {
      super("Number of params not matching");
   }
}
