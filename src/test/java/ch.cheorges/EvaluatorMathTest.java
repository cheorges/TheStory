package ch.cheorges;

import static com.google.common.truth.Truth.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

import ch.cheorges.exception.IllegalTypeException;

public class EvaluatorMathTest extends BaseEvaluatorTest {

   @Test
   public void addition_integer() throws Exception {
      addVariable("foo", 1);
      addVariable("bar", 9);
      assertThat((BigDecimal) parseExpression("1 with 9")).isEqualToIgnoringScale(BigDecimal.TEN);
      assertThat((BigDecimal) parseExpression("1 plus 9")).isEqualToIgnoringScale(BigDecimal.TEN);
      assertThat((BigDecimal) parseExpression("foo plus 9")).isEqualToIgnoringScale(BigDecimal.TEN);
      assertThat((BigDecimal) parseExpression("1 plus bar")).isEqualToIgnoringScale(BigDecimal.TEN);
      assertThat((BigDecimal) parseExpression("foo plus bar")).isEqualToIgnoringScale(BigDecimal.TEN);
   }

   @Test
   public void addition_double() throws Exception {
      addVariable("foo", 0.9);
      addVariable("bar", 9.1);
      assertThat((BigDecimal) parseExpression("0.9 with 9.1")).isEqualToIgnoringScale(BigDecimal.TEN);
      assertThat((BigDecimal) parseExpression("0.9 plus 9.1")).isEqualToIgnoringScale(BigDecimal.TEN);
      assertThat((BigDecimal) parseExpression("foo plus 9.1")).isEqualToIgnoringScale(BigDecimal.TEN);
      assertThat((BigDecimal) parseExpression("0.9 plus bar")).isEqualToIgnoringScale(BigDecimal.TEN);
      assertThat((BigDecimal) parseExpression("foo plus bar")).isEqualToIgnoringScale(BigDecimal.TEN);
   }

   @Test
   public void subtraction_integer() throws Exception {
      addVariable("foo", 11);
      addVariable("bar", 1);
      assertThat((BigDecimal) parseExpression("11 without 1")).isEqualToIgnoringScale(BigDecimal.TEN);
      assertThat((BigDecimal) parseExpression("11 minus 1")).isEqualToIgnoringScale(BigDecimal.TEN);
      assertThat((BigDecimal) parseExpression("foo minus 1")).isEqualToIgnoringScale(BigDecimal.TEN);
      assertThat((BigDecimal) parseExpression("11 minus bar")).isEqualToIgnoringScale(BigDecimal.TEN);
      assertThat((BigDecimal) parseExpression("foo minus bar")).isEqualToIgnoringScale(BigDecimal.TEN);
   }

   @Test
   public void subtraction_double() throws Exception {
      addVariable("foo", 11.1);
      addVariable("bar", 1.1);
      assertThat((BigDecimal) parseExpression("11.1 without 1.1")).isEqualToIgnoringScale(BigDecimal.TEN);
      assertThat((BigDecimal) parseExpression("11.1 minus 1.1")).isEqualToIgnoringScale(BigDecimal.TEN);
      assertThat((BigDecimal) parseExpression("foo minus 1.1")).isEqualToIgnoringScale(BigDecimal.TEN);
      assertThat((BigDecimal) parseExpression("11.1 minus bar")).isEqualToIgnoringScale(BigDecimal.TEN);
      assertThat((BigDecimal) parseExpression("foo minus bar")).isEqualToIgnoringScale(BigDecimal.TEN);
   }

   @Test
   public void multiplication_integer() throws Exception {
      addVariable("foo", 2);
      addVariable("bar", 5);
      assertThat((BigDecimal) parseExpression("2 times 5")).isEqualToIgnoringScale(BigDecimal.TEN);
      assertThat((BigDecimal) parseExpression("2 of 5")).isEqualToIgnoringScale(BigDecimal.TEN);
      assertThat((BigDecimal) parseExpression("foo of 5")).isEqualToIgnoringScale(BigDecimal.TEN);
      assertThat((BigDecimal) parseExpression("2 of bar")).isEqualToIgnoringScale(BigDecimal.TEN);
      assertThat((BigDecimal) parseExpression("foo of bar")).isEqualToIgnoringScale(BigDecimal.TEN);
   }

   @Test
   public void multiplication_double() throws Exception {
      addVariable("foo", 2.5);
      addVariable("bar", 4.0);
      assertThat((BigDecimal) parseExpression("2.5 times 4.0")).isEqualToIgnoringScale(BigDecimal.TEN);
      assertThat((BigDecimal) parseExpression("2.5 of 4.0")).isEqualToIgnoringScale(BigDecimal.TEN);
      assertThat((BigDecimal) parseExpression("foo of 4.0")).isEqualToIgnoringScale(BigDecimal.TEN);
      assertThat((BigDecimal) parseExpression("2.5 of bar")).isEqualToIgnoringScale(BigDecimal.TEN);
      assertThat((BigDecimal) parseExpression("foo of bar")).isEqualToIgnoringScale(BigDecimal.TEN);
   }

   @Test
   public void division_integer() throws Exception {
      addVariable("foo", 100);
      addVariable("bar", 10);
      assertThat((BigDecimal) parseExpression("100 over 10")).isEqualToIgnoringScale(BigDecimal.TEN);
      assertThat((BigDecimal) parseExpression("foo over 10")).isEqualToIgnoringScale(BigDecimal.TEN);
      assertThat((BigDecimal) parseExpression("100 over bar")).isEqualToIgnoringScale(BigDecimal.TEN);
      assertThat((BigDecimal) parseExpression("foo over bar")).isEqualToIgnoringScale(BigDecimal.TEN);
   }

   @Test
   public void division_double() throws Exception {
      addVariable("foo", 25.0);
      addVariable("bar", 2.5);
      assertThat((BigDecimal) parseExpression("25.0 over 2.5")).isEqualToIgnoringScale(BigDecimal.TEN);
      assertThat((BigDecimal) parseExpression("foo over 2.5")).isEqualToIgnoringScale(BigDecimal.TEN);
      assertThat((BigDecimal) parseExpression("25.0 over bar")).isEqualToIgnoringScale(BigDecimal.TEN);
      assertThat((BigDecimal) parseExpression("foo over bar")).isEqualToIgnoringScale(BigDecimal.TEN);
   }

   @Test
   public void addition_string() throws Exception {
      assertThat((String) parseExpression("\"x\" with \"y\"")).isEqualTo("xy");
      assertThat((String) parseExpression("\"x\" plus \"y\"")).isEqualTo("xy");
   }

   @Test
   public void hang_together_string_integer() throws Exception {
      assertThat((String) parseExpression("\"x\" with 1")).isEqualTo("x1");
      assertThat((String) parseExpression("\"x\" plus 1")).isEqualTo("x1");
   }

   @Test
   public void hang_together_string_double() throws Exception {
      assertThat((String) parseExpression("\"x\" with 3.14")).isEqualTo("x3.14");
      assertThat((String) parseExpression("\"x\" plus 3.14")).isEqualTo("x3.14");
   }

   @Test
   public void hang_together_integer_string() throws Exception {
      assertThat((String) parseExpression("1 with \"x\"")).isEqualTo("1x");
      assertThat((String) parseExpression("1 plus \"x\"")).isEqualTo("1x");
   }

   @Test
   public void hang_together_double_string() throws Exception {
      assertThat((String) parseExpression("3.14 with \"x\"")).isEqualTo("3.14x");
      assertThat((String) parseExpression("3.14 plus \"x\"")).isEqualTo("3.14x");
   }

   @Test
   public void multiplication_string() throws Exception {
      assertThat((String) parseExpression("\"x\" of 3")).isEqualTo("xxx");
      assertThat((String) parseExpression("\"x\" times 3")).isEqualTo("xxx");
      assertThat((String) parseExpression("3 of \"x\"")).isEqualTo("xxx");
      assertThat((String) parseExpression("3 times \"x\"")).isEqualTo("xxx");
   }

   @Test(expected = IllegalTypeException.class)
   public void wrong_type_boolean() throws Exception {
      assertThat(parseExpression("yes with 3"));
   }

   @Test(expected = IllegalTypeException.class)
   public void wrong_type_string() throws Exception {
      assertThat(parseExpression("\"x\" over 3"));
   }
}
