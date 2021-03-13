package ch.cheorges.exception;

public class IllegalTypeException extends RuntimeException {
   public IllegalTypeException(String operation) {
      super("Illegal type for " + operation + ".");
   }
}
