package ch.cheorges.exception;

public class IdentifierNotDefinedException extends RuntimeException {
   public IdentifierNotDefinedException(String identifier) {
      super("Identifier '" + identifier + "' is not defined.");
   }
}
