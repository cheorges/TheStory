package ch.cheorges.exception;

public class TypeCompareException extends RuntimeException {
   public TypeCompareException() {
      super("Types cannot be compared.");
   }
}
