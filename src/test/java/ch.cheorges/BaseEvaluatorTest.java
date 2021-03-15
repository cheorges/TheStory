package ch.cheorges;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;

import ch.cheorges.instruction.Instruction;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.Symbol;
import v1.Parser;
import v1.Scanner;

public class BaseEvaluatorTest {
   private final Map<String, Object> context = new HashMap<>();

   protected void addVariable(String identifier, Object value) {
      context.put(identifier, value);
   }

   protected Object parseExpression(String expression) throws Exception {
      Symbol symbol = new Parser(new Scanner(new StringReader(expression)), new ComplexSymbolFactory()).parse();
      return ((Instruction) symbol.value).acceptVisitor(new Evaluator(context));
   }

   @AfterEach
   protected void cleanup() {
      context.clear();
   }

}
