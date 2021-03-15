package ch.cheorges;

import static com.google.common.truth.Truth.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

public class EvaluatorFunctionTest extends BaseEvaluatorTest {

   @Test
   public void function_definition_not_called() throws Exception {
      assertThat((BigDecimal) parseExpression("""
            Let foo be 1
            bar takes foo
            Let foo be 2
            
            Let foo be foo
            """)).isEqualToIgnoringScale(1);
   }

   @Test
   public void function_definition_and_called() throws Exception {
      assertThat((BigDecimal) parseExpression("""
            Let foo be 1
            bar takes foo
            Let foo be 2
            
            bar taking foo
            """)).isEqualToIgnoringScale(2);
   }

   /* TODO: Fix variable context bug. */
   @Test
   public void function_and_variables() throws Exception {
      assertThat((BigDecimal) parseExpression("""
            Let foo be 2
            Square takes foo
            foo times foo
            
            Square taking foo
            """)).isEqualToIgnoringScale(4);
   }

}
