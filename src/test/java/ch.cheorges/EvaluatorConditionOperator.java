package ch.cheorges;

import static com.google.common.truth.Truth.assertThat;

import org.junit.Test;

import ch.cheorges.exception.IllegalTypeException;
import ch.cheorges.exception.TypeCompareException;

public class EvaluatorConditionOperator extends BaseEvaluatorTest {
   
   @Test
   public void is_exactly_the_same_as() throws Exception {
      addVariable("foo", 1);
      addVariable("bar", 2);
      assertThat((Boolean) parseExpression("1 is exactly the same as 1")).isTrue();
      assertThat((Boolean) parseExpression("1.1 is exactly the same as 1.1")).isTrue();
      assertThat((Boolean) parseExpression("1 is exactly the same as 2")).isFalse();
      assertThat((Boolean) parseExpression("1.2 is exactly the same as 1.1")).isFalse();
      assertThat((Boolean) parseExpression("foo is exactly the same as foo")).isTrue();
      assertThat((Boolean) parseExpression("foo is exactly the same as bar")).isFalse();
      assertThat((Boolean) parseExpression("\"x\" is exactly the same as \"x\"")).isTrue();
      assertThat((Boolean) parseExpression("\"x\" is exactly the same as \"y\"")).isFalse();
   }

   @Test
   public void is_not_the_same_as() throws Exception {
      addVariable("foo", 1);
      addVariable("bar", 2);
      assertThat((Boolean) parseExpression("1 is not the same as 1")).isFalse();
      assertThat((Boolean) parseExpression("1.1 is not the same as 1.1")).isFalse();
      assertThat((Boolean) parseExpression("1 is not the same as 2")).isTrue();
      assertThat((Boolean) parseExpression("1.2 is not the same as 1.1")).isTrue();
      assertThat((Boolean) parseExpression("foo is not the same as foo")).isFalse();
      assertThat((Boolean) parseExpression("foo is not the same as bar")).isTrue();
      assertThat((Boolean) parseExpression("\"x\" is not the same as \"x\"")).isFalse();
      assertThat((Boolean) parseExpression("\"x\" is not the same as \"y\"")).isTrue();
   }

   @Test
   public void is_higher_than() throws Exception {
      addVariable("foo", 1);
      addVariable("bar", 2);
      assertThat((Boolean) parseExpression("1 is higher than 1")).isFalse();
      assertThat((Boolean) parseExpression("1.1 is higher than 1.1")).isFalse();
      assertThat((Boolean) parseExpression("1 is higher than 2")).isFalse();
      assertThat((Boolean) parseExpression("1.2 is higher than 1.1")).isTrue();
      assertThat((Boolean) parseExpression("foo is higher than foo")).isFalse();
      assertThat((Boolean) parseExpression("foo is higher than bar")).isFalse();
   }

   @Test
   public void is_lower_than() throws Exception {
      addVariable("foo", 1);
      addVariable("bar", 2);
      assertThat((Boolean) parseExpression("1 is lower than 1")).isFalse();
      assertThat((Boolean) parseExpression("1.1 is lower than 1.1")).isFalse();
      assertThat((Boolean) parseExpression("1 is lower than 2")).isTrue();
      assertThat((Boolean) parseExpression("1.2 is lower than 1.1")).isFalse();
      assertThat((Boolean) parseExpression("foo is lower than foo")).isFalse();
      assertThat((Boolean) parseExpression("foo is lower than bar")).isTrue();
   }

   @Test
   public void is_as_high_as() throws Exception {
      addVariable("foo", 1);
      addVariable("bar", 2);
      assertThat((Boolean) parseExpression("1 is as high as 1")).isTrue();
      assertThat((Boolean) parseExpression("1.1 is as high as 1.1")).isTrue();
      assertThat((Boolean) parseExpression("1 is as high as 2")).isFalse();
      assertThat((Boolean) parseExpression("1.2 is as high as 1.1")).isTrue();
      assertThat((Boolean) parseExpression("foo is as high as foo")).isTrue();
      assertThat((Boolean) parseExpression("foo is as high as bar")).isFalse();
   }

   @Test
   public void is_as_low_as() throws Exception {
      addVariable("foo", 1);
      addVariable("bar", 2);
      assertThat((Boolean) parseExpression("1 is as low as 1")).isTrue();
      assertThat((Boolean) parseExpression("1.1 is as low as 1.1")).isTrue();
      assertThat((Boolean) parseExpression("1 is as low as 2")).isTrue();
      assertThat((Boolean) parseExpression("1.2 is as low as 1.1")).isFalse();
      assertThat((Boolean) parseExpression("foo is as low as foo")).isTrue();
      assertThat((Boolean) parseExpression("foo is as low as bar")).isTrue();
   }

   @Test
   public void keyword_test() throws Exception {
      assertThat((Boolean) parseExpression("1 is higher than 1")).isFalse();
      assertThat((Boolean) parseExpression("1 is greater than 2")).isFalse();
      assertThat((Boolean) parseExpression("1 is bigger than 2")).isFalse();
      assertThat((Boolean) parseExpression("1 is stronger than 2")).isFalse();
      assertThat((Boolean) parseExpression("1 is lower than 2")).isTrue();
      assertThat((Boolean) parseExpression("1 is less than 2")).isTrue();
      assertThat((Boolean) parseExpression("1 is smaller than 2")).isTrue();
      assertThat((Boolean) parseExpression("1 is weaker than 2")).isTrue();
      assertThat((Boolean) parseExpression("1 is as high as 2")).isFalse();
      assertThat((Boolean) parseExpression("1 is as great as 2")).isFalse();
      assertThat((Boolean) parseExpression("1 is as big as 2")).isFalse();
      assertThat((Boolean) parseExpression("1 is as strong as 2")).isFalse();
      assertThat((Boolean) parseExpression("1 is as low as 2")).isTrue();
      assertThat((Boolean) parseExpression("1 is as little as 2")).isTrue();
      assertThat((Boolean) parseExpression("1 is as small as 2")).isTrue();
      assertThat((Boolean) parseExpression("1 is as weak as 2")).isTrue();
   }

   @Test(expected = TypeCompareException.class)
   public void wrong_type_string() throws Exception {
      assertThat(parseExpression("\"x\" is as big as 1"));
   }

   @Test(expected = TypeCompareException.class)
   public void wrong_type_boolean() throws Exception {
      assertThat(parseExpression("yes is as big as yes"));
   }
}
