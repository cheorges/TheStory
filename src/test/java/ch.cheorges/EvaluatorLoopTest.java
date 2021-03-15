package ch.cheorges;

import static com.google.common.truth.Truth.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

public class EvaluatorLoopTest extends BaseEvaluatorTest {

   @Test
   public void while_flow() throws Exception {
      addVariable("foo", 0);
      assertThat((BigDecimal) parseExpression("""
            While foo is smaller than 10
            Let foo be 2 with foo
                  
            let foo be foo
            """)).isEqualToIgnoringScale(BigDecimal.TEN);
   }

   @Test
   public void while_condition_with_boolean_variable() throws Exception {
      assertThat((BigDecimal) parseExpression("""
            Let foo be yes
            While foo
            Let foo be no
            Put 10 into bar
                              
            let bar be bar
            """)).isEqualToIgnoringScale(BigDecimal.TEN);
   }

   @Test
   public void while_with_if() throws Exception {
      assertThat((BigDecimal) parseExpression("""
            Let foo be yes
            While foo
            If foo
            Put 10 into bar
                              
            Put no into foo
                              
            let bar be bar
            """)).isEqualToIgnoringScale(BigDecimal.TEN);
   }

}
