package ch.cheorges;

import static com.google.common.truth.Truth.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

public class EvaluatorConditionTest extends BaseEvaluatorTest {

   @Test
   public void if_flow() throws Exception {
      assertThat((BigDecimal) parseExpression("""
            If yes
            Put 1 into x
          
            """)).isEqualToIgnoringScale(BigDecimal.ONE);
   }

   @Test
   public void if_else_flow() throws Exception {
      assertThat((BigDecimal) parseExpression("""
            If no
            Put 1 into x
            Else
            Put 0 into x
            
            """)).isEqualToIgnoringScale(BigDecimal.ZERO);
   }

   @Test
   public void neasted_if_flow() throws Exception {
      assertThat((BigDecimal) parseExpression("""
            If yes
            If yes
            Put 1 into x



            """)).isEqualToIgnoringScale(BigDecimal.ONE);
   }

   @Test
   public void if_else_with_neasted_flow() throws Exception {
      assertThat((BigDecimal) parseExpression("""
            If wrong
            Put 1 into x
            Else
            If yes
            Put 0 into x



            """)).isEqualToIgnoringScale(BigDecimal.ZERO);
   }

   @Test
   public void if_with_neasted_if_else_flow() throws Exception {
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

}
