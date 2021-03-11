package ch.cheorges;

import ch.cheorges.instruction.InstructionVisitor;
import ch.cheorges.instruction.flow.InstructionScript;
import ch.cheorges.instruction.type.NumberInstruction;
import ch.cheorges.instruction.type.StringLiteralInstruction;
import ch.cheorges.instruction.variable.GetVariableInstruction;
import ch.cheorges.instruction.variable.InstructionSetVariable;

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
      // TODO: Is it better to return Type-Instruction?
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

}
