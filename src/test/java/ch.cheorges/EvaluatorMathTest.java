package ch.cheorges;

import static com.google.common.truth.Truth.assertThat;

import org.junit.Test;

import java.math.BigDecimal;

public class EvaluatorMathTest extends BaseEvaluatorTest {

   @Test
   public void addition_integer() throws Exception {
      assertThat((BigDecimal) parseExpression("1 with 9")).isEqualToIgnoringScale(BigDecimal.TEN);
      assertThat((BigDecimal) parseExpression("1 plus 9")).isEqualToIgnoringScale(BigDecimal.TEN);
   }

   @Test
   public void addition_double() throws Exception {
      assertThat((BigDecimal) parseExpression("0.9 with 9.1")).isEqualToIgnoringScale(BigDecimal.TEN);
      assertThat((BigDecimal) parseExpression("0.9 plus 9.1")).isEqualToIgnoringScale(BigDecimal.TEN);
   }

   @Test
   public void subtraction_integer() throws Exception {
      assertThat((BigDecimal) parseExpression("11 without 1")).isEqualToIgnoringScale(BigDecimal.TEN);
      assertThat((BigDecimal) parseExpression("11 minus 1")).isEqualToIgnoringScale(BigDecimal.TEN);
   }

   @Test
   public void subtraction_double() throws Exception {
      assertThat((BigDecimal) parseExpression("11.1 without 1.1")).isEqualToIgnoringScale(BigDecimal.TEN);
      assertThat((BigDecimal) parseExpression("11.1 minus 1.1")).isEqualToIgnoringScale(BigDecimal.TEN);
   }

   @Test
   public void multiplication_integer() throws Exception {
      assertThat((BigDecimal) parseExpression("2 times 5")).isEqualToIgnoringScale(BigDecimal.TEN);
      assertThat((BigDecimal) parseExpression("2 of 5")).isEqualToIgnoringScale(BigDecimal.TEN);
   }

   @Test
   public void multiplication_double() throws Exception {
      assertThat((BigDecimal) parseExpression("2.5 times 4.0")).isEqualToIgnoringScale(BigDecimal.TEN);
      assertThat((BigDecimal) parseExpression("2.5 of 4.0")).isEqualToIgnoringScale(BigDecimal.TEN);
   }

   @Test
   public void division_integer() throws Exception {
      assertThat((BigDecimal) parseExpression("100 over 10")).isEqualToIgnoringScale(BigDecimal.TEN);
   }

   @Test
   public void division_double() throws Exception {
      assertThat((BigDecimal) parseExpression("25 over 2.5")).isEqualToIgnoringScale(BigDecimal.TEN);
   }
}
