package ch.cheorges.instruction.math;

import static ch.cheorges.instruction.type.TypeUtil.isNumber;
import static ch.cheorges.instruction.type.TypeUtil.isStringLiteral;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Stream;

import ch.cheorges.exception.IllegalTypeException;

public enum MathOperator {
   ADD("addition"), SUB("subtraction"), MUL("multiplication"), DIV("division");

   private final String value;

   MathOperator(String value) {
      this.value = value;
   }

   public Object handle(Object leftValue, Object rightValue) {
      return switch (this) {
         case ADD -> addition(leftValue, rightValue);
         case SUB -> subtraction(leftValue, rightValue);
         case MUL -> multiplication(leftValue, rightValue);
         case DIV -> division(leftValue, rightValue);
      };
   }

   private Object addition(Object leftValue, Object rightValue) {
      if (isNumber(leftValue) && isNumber(rightValue)) {
         return new BigDecimal(leftValue.toString()).add(new BigDecimal(rightValue.toString()));
      } else if (isStringLiteral(leftValue) || isStringLiteral(rightValue)) {
         return leftValue.toString() + rightValue.toString();
      }

      throw new IllegalTypeException(this.value);
   }


   private Object subtraction(Object leftValue, Object rightValue) {
      if (isNumber(leftValue) && isNumber(rightValue)) {
         return new BigDecimal(leftValue.toString()).subtract(new BigDecimal(rightValue.toString()));
      }

      throw new IllegalTypeException(this.value);
   }

   private Object multiplication(Object leftValue, Object rightValue) {
      if (isNumber(leftValue) && isNumber(rightValue)) {
         return new BigDecimal(leftValue.toString()).multiply(new BigDecimal(rightValue.toString()));
      } else if (isNumber(leftValue) && isStringLiteral(rightValue)) {
         int count = ((BigDecimal) leftValue).toBigInteger().intValue();
         return Stream.generate(rightValue::toString).limit(count).reduce((a, b) -> a + b).get();
      } else if (isStringLiteral(leftValue) && isNumber(rightValue)) {
         int count = ((BigDecimal) rightValue).toBigInteger().intValue();
         return Stream.generate(leftValue::toString).limit(count).reduce((a, b) -> a + b).get();
      }

      throw new IllegalTypeException(this.value);
   }

   private Object division(Object leftValue, Object rightValue) {
      if (isNumber(leftValue) && isNumber(rightValue)) {
         return new BigDecimal(leftValue.toString()).divide(new BigDecimal(rightValue.toString()), RoundingMode.HALF_UP);
      }

      throw new IllegalTypeException(this.value);
   }

}
