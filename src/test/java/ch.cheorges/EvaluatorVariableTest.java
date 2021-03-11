package ch.cheorges;

import static com.google.common.truth.Truth.assertThat;

import org.junit.Ignore;
import org.junit.Test;

import java.math.BigDecimal;

public class EvaluatorVariableTest extends BaseEvaluatorTest {

   public static final BigDecimal PI = new BigDecimal("3.14");

   @Test
   public void let_variable_be_integer() throws Exception {
      assertThat(parseExpression("let foo be 1")).isEqualTo(BigDecimal.ONE);
   }

   @Test
   public void Let_variable_be_integer() throws Exception {
      assertThat(parseExpression("Let foo be 1")).isEqualTo(BigDecimal.ONE);
   }

   @Test
   public void let_variable_be_double() throws Exception {
      assertThat(parseExpression("let foo be 3.14")).isEqualTo(PI);
   }

   @Test
   public void Let_variable_be_double() throws Exception {
      assertThat(parseExpression("Let foo be 3.14")).isEqualTo(PI);
   }

   @Test
   public void put_integer_into_variable() throws Exception {
      assertThat(parseExpression("put 1 into foo")).isEqualTo(BigDecimal.ONE);
   }

   @Test
   public void Put_integer_into_variable() throws Exception {
      assertThat(parseExpression("Put 1 into foo")).isEqualTo(BigDecimal.ONE);
   }

   @Test
   public void put_double_into_variable() throws Exception {
      assertThat(parseExpression("put 3.14 into foo")).isEqualTo(PI);
   }

   @Test
   public void Put_double_into_variable() throws Exception {
      assertThat(parseExpression("Put 3.14 into foo")).isEqualTo(PI);
   }

   @Test
   public void let_foo_be_string() throws Exception {
      assertThat(parseExpression("let foo be \"Hello, World!\"")).isEqualTo("Hello, World!");
   }

   @Test
   public void put_string_into_variable() throws Exception {
      assertThat(parseExpression("put \"Hello, World!\" into bar")).isEqualTo("Hello, World!");
   }

   @Test
   public void let_variable_be_variable_integer() throws Exception {
      addVariable("bar", BigDecimal.ONE);
      assertThat(parseExpression("let foo be bar")).isEqualTo(BigDecimal.ONE);
   }

   @Test
   public void put_variable_integer_into_variable() throws Exception {
      addVariable("bar", BigDecimal.ONE);
      assertThat(parseExpression("put bar into foo")).isEqualTo(BigDecimal.ONE);
   }

   @Test
   public void let_variable_be_variable_double() throws Exception {
      addVariable("bar", PI);
      assertThat(parseExpression("let foo be bar")).isEqualTo(PI);
   }

   @Test
   public void put_variable_double_into_variable() throws Exception {
      addVariable("bar", PI);
      assertThat(parseExpression("let foo be bar")).isEqualTo(PI);
   }

   @Test
   public void let_variable_be_variable_string() throws Exception {
      addVariable("bar", "Hello, World!");
      assertThat(parseExpression("let foo be bar")).isEqualTo("Hello, World!");
   }

   @Test
   public void put_variable_string_into_variable() throws Exception {
      addVariable("bar", "Hello, World!");
      assertThat(parseExpression("put bar into foo")).isEqualTo("Hello, World!");
   }

   @Test
   public void set_new_value_to_existing_variable_with_let() throws Exception {
      addVariable("bar", BigDecimal.ONE);
      assertThat(parseExpression("let bar be 3.14")).isEqualTo(PI);
      assertThat(parseExpression("let bar be \"Hello, World!\"")).isEqualTo("Hello, World!");
      assertThat(parseExpression("let bar be 1")).isEqualTo(BigDecimal.ONE);
   }

   @Test
   public void set_new_value_to_existing_variable_with_put() throws Exception {
      addVariable("bar", BigDecimal.ONE);
      assertThat(parseExpression("put 3.14 into bar")).isEqualTo(PI);
      assertThat(parseExpression("put \"Hello, World!\" into bar")).isEqualTo("Hello, World!");
      assertThat(parseExpression("put 1 into bar")).isEqualTo(BigDecimal.ONE);
   }

   @Test
   public void put_value_into_a_variable() throws Exception {
      assertThat(parseExpression("put 10 into a foo")).isEqualTo(BigDecimal.TEN);
   }

   @Test
   public void put_value_into_an_variable() throws Exception {
      assertThat(parseExpression("put 10 into an foo")).isEqualTo(BigDecimal.TEN);
   }

   @Test
   public void put_value_into_the_variable() throws Exception {
      assertThat(parseExpression("put 10 into the foo")).isEqualTo(BigDecimal.TEN);
   }

   @Test
   public void put_value_into_my_variable() throws Exception {
      assertThat(parseExpression("put 10 into my foo")).isEqualTo(BigDecimal.TEN);
   }

   @Test
   public void put_value_into_your_variable() throws Exception {
      assertThat(parseExpression("put 10 into your foo")).isEqualTo(BigDecimal.TEN);
   }

   @Test
   public void let_a_variable_be_value() throws Exception {
      assertThat(parseExpression("let a foo be 10")).isEqualTo(BigDecimal.TEN);
   }

   @Test
   public void let_an_variable_be_value() throws Exception {
      assertThat(parseExpression("let an foo be 10")).isEqualTo(BigDecimal.TEN);
   }

   @Test
   public void let_the_variable_be_value() throws Exception {
      assertThat(parseExpression("let the foo be 10")).isEqualTo(BigDecimal.TEN);
   }

   @Test
   public void let_my_variable_be_value() throws Exception {
      assertThat(parseExpression("let my foo be 10")).isEqualTo(BigDecimal.TEN);
   }

   @Test
   public void let_your_variable_be_value() throws Exception {
      assertThat(parseExpression("let your foo be 10")).isEqualTo(BigDecimal.TEN);
   }

   @Test
   public void let_name_be_value() throws Exception {
      assertThat(parseExpression("let Black Pearl be 10")).isEqualTo(BigDecimal.TEN);
      assertThat(parseExpression("let Black Pearl be 3.14")).isEqualTo(PI);
      assertThat(parseExpression("let Black Pearl be \"Hello, World!\"")).isEqualTo("Hello, World!");
   }

   @Ignore("!!!Problem -> Scanner create wrong Tokes.")
   @Test
   public void Let_name_be_value() throws Exception {
      assertThat(parseExpression("Let Black Pearl be 10")).isEqualTo(BigDecimal.TEN);
      assertThat(parseExpression("Let Black Pearl be 3.14")).isEqualTo(PI);
      assertThat(parseExpression("Let Black Pearl be \"Hello, World!\"")).isEqualTo("Hello, World!");
   }

   @Test
   public void put_value_into_name() throws Exception {
      assertThat(parseExpression("put 10 into Black Pearl")).isEqualTo(BigDecimal.TEN);
      assertThat(parseExpression("put 3.14 into Black Pearl")).isEqualTo(PI);
      assertThat(parseExpression("put \"Hello, World!\" into Black Pearl")).isEqualTo("Hello, World!");
   }

   @Test
   public void Put_value_into_name() throws Exception {
      assertThat(parseExpression("Put 10 into Black Pearl")).isEqualTo(BigDecimal.TEN);
      assertThat(parseExpression("Put 3.14 into Black Pearl")).isEqualTo(PI);
      assertThat(parseExpression("Put \"Hello, World!\" into Black Pearl")).isEqualTo("Hello, World!");
   }
}
