package ch.cheorges.instruction.type;

import java.math.BigDecimal;

public class TypeUtil {
   public static boolean isNumber(Object value) {
      return value instanceof BigDecimal ||
            value instanceof Short ||
            value instanceof Byte ||
            value instanceof Long ||
            value instanceof Integer ||
            value instanceof Double ||
            value instanceof Float;
   }

   public static boolean isStringLiteral(Object value) {
      return value instanceof String;
   }
}
