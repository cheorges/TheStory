package ch.cheorges;

import static com.google.common.truth.Truth.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

public class EvaluatorConditionTest extends BaseEvaluatorTest {

   @Test
   public void if_statement() throws Exception {
      assertThat((BigDecimal) parseExpression("""
            If yes
            Put 1 into x
            
            """)).isEqualToIgnoringScale(BigDecimal.ONE);
   }

   @Test
   public void if_else_statement() throws Exception {
      assertThat((BigDecimal) parseExpression("""
            If no
            Put 1 into x
            Else
            Put 0 into x

            """)).isEqualToIgnoringScale(BigDecimal.ZERO);
   }

   @Test
   public void if_in_if_statement() throws Exception {
      assertThat((BigDecimal) parseExpression("""
            If yes
            If yes
            Put 1 into x



            """)).isEqualToIgnoringScale(BigDecimal.ONE);
   }

   @Test
   public void if_else_with_if_statement() throws Exception {
      assertThat((BigDecimal) parseExpression("""
            If wrong
            Put 1 into x
            Else
            If yes
            Put 0 into x



            """)).isEqualToIgnoringScale(BigDecimal.ZERO);
   }

   @Test
   public void if_with_if_else_statement() throws Exception {
      assertThat((BigDecimal) parseExpression("""
            If ok
            If lies
            Put 1 into x
            Else
            Put 0 into x
            
            
            Else
            Put 2 into x

            """)).isEqualToIgnoringScale(BigDecimal.ZERO);
   }

   @Test
   public void if_statement_with_non_boolean_variable() throws Exception {
      addVariable("foo", 1);
      addVariable("bar", "wrong");
      addVariable("bat", "\"x\"");
      assertThat((BigDecimal) parseExpression("""
            If foo
            Put 1 into x
            Else
            Put 0 into x

            """)).isEqualToIgnoringScale(BigDecimal.ZERO);
      assertThat((BigDecimal) parseExpression("""
            If bar
            Put 1 into x
            Else
            Put 0 into x

            """)).isEqualToIgnoringScale(BigDecimal.ZERO);
      assertThat((BigDecimal) parseExpression("""
            If bat
            Put 1 into x
            Else
            Put 0 into x

            """)).isEqualToIgnoringScale(BigDecimal.ZERO);
   }
}
