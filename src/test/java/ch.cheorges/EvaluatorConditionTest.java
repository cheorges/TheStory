package ch.cheorges;

import static com.google.common.truth.Truth.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

public class EvaluatorConditionTest extends BaseEvaluatorTest {

   @Test
   public void if_flow() throws Exception {
      assertThat((BigDecimal) parseExpression("""
            If yes
            Put 1 into foo
                      
            """)).isEqualToIgnoringScale(BigDecimal.ONE);
   }

   @Test
   public void if_else_flow() throws Exception {
      assertThat((BigDecimal) parseExpression("""
            If no
            Put 1 into foo
            Else
            Put 0 into foo
                        
            """)).isEqualToIgnoringScale(BigDecimal.ZERO);
   }

   @Test
   public void neasted_if_flow() throws Exception {
      assertThat((BigDecimal) parseExpression("""
            If yes
            If yes
            Put 1 into foo
                        
                        
                        
            """)).isEqualToIgnoringScale(BigDecimal.ONE);
   }

   @Test
   public void if_else_with_neasted_flow() throws Exception {
      assertThat((BigDecimal) parseExpression("""
            If wrong
            Else
            If yes
            Put 0 into foo
                        
                        
                        
            """)).isEqualToIgnoringScale(BigDecimal.ZERO);
   }

   @Test
   public void if_with_neasted_if_else_flow() throws Exception {
      assertThat((BigDecimal) parseExpression("""
            If ok
            If lies
            Put 1 into foo
            Else
            Put 0 into foo
                        
            Else
            Put 2 into foo

            """)).isEqualToIgnoringScale(BigDecimal.ZERO);
   }

   @Test
   public void evaluate_number() throws Exception {
      assertThat((BigDecimal) parseExpression("""
            Put 1 into foo
            If foo
            Else
            Put 0 into bar

            """)).isEqualToIgnoringScale(BigDecimal.ZERO);
   }

   @Test
   public void evaluate_string() throws Exception {
      assertThat((BigDecimal) parseExpression("""
            Put "yes" into foo
            If foo
            Else
            Put 0 into bar

            """)).isEqualToIgnoringScale(BigDecimal.ZERO);
   }
}
