package ch.cheorges;

import static com.google.common.truth.Truth.assertThat;

import java.math.BigDecimal;

import org.junit.Ignore;
import org.junit.Test;

public class EvaluatorExampleProgram extends BaseEvaluatorTest {

   /*
   Counts the Hugo words.
    */
   @Ignore
   @Test
   public void hugo() throws Exception {
      String code = """
             Modulus takes number and divisor
             While number is as high as divisor
             Put number without divisor into number
             
             
             
             5 with 5
             """;

      assertThat((BigDecimal) parseExpression(code)).isEqualToIgnoringScale(10);
   }
}
