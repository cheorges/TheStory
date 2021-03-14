package ch.cheorges;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import ch.cheorges.instruction.InstructionVisitor;
import ch.cheorges.instruction.condition.BooleanConditionInstruction;
import ch.cheorges.instruction.condition.ConditionalInstruction;
import ch.cheorges.instruction.flow.ProgramInstruction;
import ch.cheorges.instruction.flow.ScriptInstruction;
import ch.cheorges.instruction.flow.VoidObject;
import ch.cheorges.instruction.loop.LoopInstruction;
import ch.cheorges.instruction.math.MathOperationInstruction;
import ch.cheorges.instruction.type.BooleanInstruction;
import ch.cheorges.instruction.type.NumberInstruction;
import ch.cheorges.instruction.type.StringLiteralInstruction;
import ch.cheorges.instruction.variable.GetVariableInstruction;
import ch.cheorges.instruction.variable.SetVariableInstruction;

public class Evaluator implements InstructionVisitor<Object> {
   private final Map<String, Object> context;

   public Evaluator(Map<String, Object> context) {
      this.context = context;
   }

   public Evaluator() {
      this(new HashMap<>());
   }

   @Override
   public Object visitSetVariable(SetVariableInstruction instruction) {
      Object evaluatedValue = instruction.getValue().acceptVisitor(this);
      context.put(instruction.getIdentifier().toLowerCase(Locale.ROOT), evaluatedValue);
      // TODO: Is it better to return Type-Instruction?
      return evaluatedValue;
   }

   @Override
   public Object handleGetVariable(GetVariableInstruction instruction) {
      return context.get(instruction.getIdentifier().toLowerCase(Locale.ROOT));
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
   public Object handleBooleanInstruction(BooleanInstruction instruction) {
      return instruction.getValue();
   }

   @Override
   public Object handleScript(ScriptInstruction instruction) {
      final int indexOfLastInstruction = instruction.getInstructions().size() - 1;
      for (int index = 0; index < indexOfLastInstruction; index++) {
         instruction.getInstructions().get(index).acceptVisitor(this);
      }
      return instruction.getInstructions().get(indexOfLastInstruction).acceptVisitor(this);
   }

   @Override
   public Object handleMathOperation(MathOperationInstruction instruction) {
      return instruction.getOperator().handle(
            instruction.getLeftValue().acceptVisitor(this),
            instruction.getRightValue().acceptVisitor(this));
   }

   @Override
   public Object handleBooleanCondition(BooleanConditionInstruction instruction) {
      return instruction.getCondition().handle(
            instruction.getLeftValue().acceptVisitor(this),
            instruction.getRightValue().acceptVisitor(this));
   }

   @Override
   public Object handleConditional(ConditionalInstruction instruction) {
      if (instruction.getCondition().acceptVisitor(this).equals(Boolean.TRUE)) {
         return instruction.getTrueBlock().acceptVisitor(this);
      }
      return instruction.getFalseBlock().acceptVisitor(this);
   }

   @Override
   public Object handleLoop(LoopInstruction instruction) {
      while ((Boolean) instruction.getCondition().acceptVisitor(this)) {
         instruction.getBlock().acceptVisitor(this);
      }
      return new VoidObject();
   }

   @Override
   public Object handleProgram(ProgramInstruction instruction) {
      instruction.getInstructions().forEach(i -> instruction.acceptVisitor(this));
      if (!instruction.getInstructions().isEmpty()) {
         final int lastInstruction = instruction.getInstructions().size() - 1;
         return instruction.getInstructions().get(lastInstruction).acceptVisitor(this);
      }
      return new VoidObject();
   }

}
