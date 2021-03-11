package humanly;

import humanly.instruction.GetVariableInstruction;
import humanly.instruction.InstructionScript;
import humanly.instruction.InstructionSetVariable;
import humanly.instruction.InstructionVisitor;
import humanly.instruction.MathOperationInstruction;
import humanly.instruction.NumberInstruction;
import humanly.instruction.StringLiteralInstruction;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Evaluator implements InstructionVisitor<Object> {
   private final Map<String, Object> context;

   public Evaluator(Map<String, Object> context) {
      this.context = context;
   }

   public Evaluator() {
      this(new HashMap<>());
   }

   @Override
   public Object visitSetVariable(InstructionSetVariable instruction) {
      Object evaluatedValue = instruction.getValue().acceptVisitor(this);
      context.put(instruction.getIdentifier(), evaluatedValue);
      // TODO: What would be better here?
      return evaluatedValue;
   }

   @Override
   public Object handleResolveVariable(GetVariableInstruction instruction) {
      return context.get(instruction.getIdentifier());

   }

   @Override
   public Object handleNumberInstruction(NumberInstruction instruction) {
      return instruction.getValue();
   }

   @Override
   public Object handleStringLiteral(StringLiteralInstruction instruction) {
      return instruction.getValue();
   }

   @Override
   public Object handleScript(InstructionScript instruction) {
      final int indexOfLastInstruction = instruction.getInstructions().size() - 1;
      for (int index = 0; index < indexOfLastInstruction; index++) {
         instruction.getInstructions().get(index).acceptVisitor(this);
      }
      return instruction.getInstructions().get(indexOfLastInstruction).acceptVisitor(this);
   }

   @Override
   public Object handleMathOperation(MathOperationInstruction instruction) {
      BigDecimal leftValue = new BigDecimal(String.valueOf(instruction.getLeftValue().acceptVisitor(this)));
      BigDecimal rightValue = new BigDecimal(String.valueOf(instruction.getRightValue().acceptVisitor(this)));
      return instruction.getOperator().handle(leftValue, rightValue);
   }

}
