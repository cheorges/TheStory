package ch.cheorges;

import static com.google.common.truth.Truth.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

public class EvaluatorLoopTest extends BaseEvaluatorTest {

   @Test
   public void while_flow() throws Exception {
      addVariable("foo", 0);
      parseExpression("""
            While foo is smaller than 10
            Let foo be 2 with foo
            
            """);
      assertThat((BigDecimal) parseExpression("let foo be foo")).isEqualToIgnoringScale(BigDecimal.TEN);
   }

}
