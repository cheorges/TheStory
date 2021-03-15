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
             Modulus takes Number and Divisor
             While Number is as high as Divisor
             Put Number without Divisor into Number
             
             
             Let Limit be 100
             Let Counter be 0
             Let Hugo be 0
             While Counter is not Limit
             Put Modulus taking Counter and 7 into result
             If result is 0
             Now Huge will be Hugo plus 1
             
             
             """;

      assertThat((BigDecimal) parseExpression(code)).isEqualToIgnoringScale(10);
   }
}
