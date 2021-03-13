package ch.cheorges.instruction.condition;

import static ch.cheorges.instruction.type.TypeUtil.isNumber;
import static ch.cheorges.instruction.type.TypeUtil.isStringLiteral;

import java.math.BigDecimal;

import ch.cheorges.exception.TypeCompareException;

public enum ConditionOperator {
   GE, LE, GEQ, LEQ, EQ, NOT_EQ;

   public Boolean handle(Object leftValue, Object rightValue) {
      return switch (this) {
         case GE -> greaterThan(leftValue, rightValue);
         case LE -> lessThan(leftValue, rightValue);
         case GEQ -> greaterThanOrEqualTo(leftValue, rightValue);
         case LEQ -> lessThanOrEqualTo(leftValue, rightValue);
         case EQ -> equals(leftValue, rightValue);
         case NOT_EQ -> notEquals(leftValue, rightValue);
      };
   }

   private Boolean greaterThan(Object leftValue, Object rightValue) {
      if (isNumber(leftValue) && isNumber(rightValue)) {
         return new BigDecimal(leftValue.toString()).compareTo(new BigDecimal(rightValue.toString())) > 0;
      }

      throw new TypeCompareException();
   }

   private Boolean lessThan(Object leftValue, Object rightValue) {
      if (isNumber(leftValue) && isNumber(rightValue)) {
         return new BigDecimal(leftValue.toString()).compareTo(new BigDecimal(rightValue.toString())) < 0;
      }

      throw new TypeCompareException();
   }

   private Boolean greaterThanOrEqualTo(Object leftValue, Object rightValue) {
      if (isNumber(leftValue) && isNumber(rightValue)) {
         return new BigDecimal(leftValue.toString()).compareTo(new BigDecimal(rightValue.toString())) >= 0;
      }

      throw new TypeCompareException();
   }

   private Boolean lessThanOrEqualTo(Object leftValue, Object rightValue) {
      if (isNumber(leftValue) && isNumber(rightValue)) {
         return new BigDecimal(leftValue.toString()).compareTo(new BigDecimal(rightValue.toString())) <= 0;
      }

      throw new TypeCompareException();
   }

   private Boolean equals(Object leftValue, Object rightValue) {
      if (isNumber(leftValue) && isNumber(rightValue)) {
         return new BigDecimal(leftValue.toString()).equals(new BigDecimal(rightValue.toString()));
      } else if (isStringLiteral(leftValue) && isStringLiteral(rightValue)) {
         return leftValue.toString().equals(rightValue.toString());
      }

      throw new TypeCompareException();
   }

   private Boolean notEquals(Object leftValue, Object rightValue) {
      return !equals(leftValue, rightValue);
   }

}
