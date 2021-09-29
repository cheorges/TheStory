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

   @Test
   public void function_and_variables() throws Exception {
      assertThat((BigDecimal) parseExpression("""
            Let foo be 2
            Square takes x
            x times x
                        
            Square taking foo
            """)).isEqualToIgnoringScale(4);
   }

   @Test
   public void function_and_value() throws Exception {
      assertThat((BigDecimal) parseExpression("""
            Square takes x
            x times x
                        
            Square taking 2
            """)).isEqualToIgnoringScale(4);
   }

}
