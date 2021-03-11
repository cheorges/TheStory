package ch.cheorges;

import static com.google.common.truth.Truth.assertThat;

import org.junit.Test;

import java.math.BigDecimal;

public class EvaluatorVariableTest extends BaseEvaluatorTest {

   public static final BigDecimal PI = new BigDecimal("3.14");

   @Test
   public void set_variable_with_number_over_let_keyword() throws Exception {
      assertThat(parseExpression("let foo be 1")).isEqualTo(BigDecimal.ONE);
   }

   @Test
   public void set_variable_with_number_over_Let_keyword() throws Exception {
      assertThat(parseExpression("Let foo be 1")).isEqualTo(BigDecimal.ONE);
   }

   @Test
   public void set_variable_with_floating_number_over_let_keyword() throws Exception {
      assertThat(parseExpression("let foo be 3.14")).isEqualTo(PI);
   }

   @Test
   public void set_variable_with_floating_over_Let_keyword() throws Exception {
      assertThat(parseExpression("Let foo be 3.14")).isEqualTo(PI);
   }

   @Test
   public void set_variable_with_number_over_put_keyword() throws Exception {
      assertThat(parseExpression("put 1 into foo")).isEqualTo(BigDecimal.ONE);
   }

   @Test
   public void set_variable_with_number_over_Put_keyword() throws Exception {
      assertThat(parseExpression("Put 1 into foo")).isEqualTo(BigDecimal.ONE);
   }

   @Test
   public void set_variable_with_floating_number_over_put_keyword() throws Exception {
      assertThat(parseExpression("put 3.14 into foo")).isEqualTo(PI);
   }

   @Test
   public void set_variable_with_floating_over_Put_keyword() throws Exception {
      assertThat(parseExpression("Put 3.14 into foo")).isEqualTo(PI);
   }

   @Test
   public void set_variable_with_string_literal_with_let_keyword() throws Exception {
      assertThat(parseExpression("let foo be \"Hello, World!\"")).isEqualTo("Hello, World!");
   }

   @Test
   public void set_variable_with_string_literal_with_put_keyword() throws Exception {
      assertThat(parseExpression("put \"Hello, World!\" into bar")).isEqualTo("Hello, World!");
   }

   @Test
   public void set_variable_with_value_from_other_variable_with_let_keyword() throws Exception {
      addVariable("bar", PI);
      assertThat(parseExpression("let foo be bar")).isEqualTo(PI);
   }

   @Test
   public void set_variable_with_value_from_other_variable_with_put_keyword() throws Exception {
      addVariable("bar", PI);
      assertThat(parseExpression("put bar into foo")).isEqualTo(PI);
   }

   @Test
   public void set_variable_with_value_from_string_variable() throws Exception {
      addVariable("bar", "Hello, World!");
      assertThat(parseExpression("put bar into foo")).isEqualTo("Hello, World!");
   }

   @Test
   public void set_new_value_to_existing_variable() throws Exception {
      addVariable("bar", BigDecimal.ONE);
      assertThat(parseExpression("put 10 into bar")).isEqualTo(BigDecimal.TEN);
   }
}
