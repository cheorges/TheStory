package ch.cheorges.instruction.math;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public enum MathOperator {
   ADD, MIN, MUL, DIV;

   public BigDecimal handle(BigDecimal leftValue, BigDecimal rightValue) {
      switch (this) {
         case ADD: return leftValue.add(rightValue);
         case MIN: return leftValue.subtract(rightValue);
         case MUL: return leftValue.multiply(rightValue);
         case DIV: return leftValue.divide(rightValue, RoundingMode.HALF_UP);
      }
      return null;
   }
}
