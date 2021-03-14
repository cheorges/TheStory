package ch.cheorges;

import static com.google.common.truth.Truth.assertThat;

import java.math.BigDecimal;

import org.junit.Ignore;
import org.junit.Test;

public class EvaluatorLoopTest extends BaseEvaluatorTest {

   @Ignore
   @Test
   public void while_flow() throws Exception {
      addVariable("foo", 0);
      assertThat((BigDecimal) parseExpression("""
            While foo is smaller than 10
            Let foo be 2 with foo
            
            """)).isEqualToIgnoringScale(BigDecimal.TEN);
   }
}
