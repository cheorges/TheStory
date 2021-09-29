package ch.cheorges;

import static com.google.common.truth.Truth.assertThat;

import org.junit.Test;

public class EvaluatorPrintTest extends BaseEvaluatorTest {

   @Test
   public void while_flow() throws Exception {
      addVariable("foo", 42);
      assertThat(parseExpression("Say \"Hello, World!\"")).isNull();
      assertThat(parseExpression("Say 3.14")).isNull();
      assertThat(parseExpression("Say 42")).isNull();
      assertThat(parseExpression("Say foo")).isNull();
   }
}
