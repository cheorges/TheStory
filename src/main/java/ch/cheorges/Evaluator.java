package ch.cheorges;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import ch.cheorges.exception.FunctionIdentifierMissmatchException;
import ch.cheorges.instruction.InstructionVisitor;
import ch.cheorges.instruction.condition.BooleanConditionInstruction;
import ch.cheorges.instruction.condition.ConditionalInstruction;
import ch.cheorges.instruction.flow.ProgramInstruction;
import ch.cheorges.instruction.function.FunctionCallInstruction;
import ch.cheorges.instruction.function.FunctionDefinitionInstruction;
import ch.cheorges.instruction.function.ScopeContext;
import ch.cheorges.instruction.loop.LoopInstruction;
import ch.cheorges.instruction.math.MathOperationInstruction;
import ch.cheorges.instruction.sout.PrintInstruction;
import ch.cheorges.instruction.type.BooleanInstruction;
import ch.cheorges.instruction.type.NumberInstruction;
import ch.cheorges.instruction.type.StringLiteralInstruction;
import ch.cheorges.instruction.variable.GetVariableInstruction;
import ch.cheorges.instruction.variable.SetVariableInstruction;

public class Evaluator extends ScopeContext implements InstructionVisitor<Object> {
   private final PrintStream output;

   public Evaluator(Map<String, Object> context, PrintStream output) {
      super(context);
      this.output = output;
   }

   public Evaluator(Map<String, Object> context) {
      super(context);
      output = System.out;
   }

   public Evaluator() {
      this(new HashMap<>());
   }

   @Override
   public Object visitSetVariable(SetVariableInstruction instruction) {
      Object evaluatedValue = instruction.getValue().acceptVisitor(this);
      setValueToContext(instruction.getIdentifier(), evaluatedValue);
      return evaluatedValue;
   }

   @Override
   public Object handleGetVariable(GetVariableInstruction instruction) {
      return getValueFromContext(instruction.getIdentifier());
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
      return null;
   }

   @Override
   public Object handleProgram(ProgramInstruction instruction) {
      final int indexOfLastInstruction = instruction.getInstructions().size() - 1;
      for (int index = 0; index < indexOfLastInstruction; index++) {
         instruction.getInstructions().get(index).acceptVisitor(this);
      }
      return instruction.getInstructions().get(indexOfLastInstruction).acceptVisitor(this);
   }

   @Override
   public Object handleFunctionCall(FunctionCallInstruction instruction) {
      FunctionDefinitionInstruction function = getFunction(instruction.getIdentifier());

      if (function.getIdentifiers().size() != instruction.getValues().size()) {
         throw new FunctionIdentifierMissmatchException();
      }

      Map<String, Object> context = new HashMap<>();
      for (int index = 0; index < instruction.getValues().size(); index++) {
         context.put(
               function.getIdentifiers().get(index).toLowerCase(Locale.ROOT),
               instruction.getValues().get(index).acceptVisitor(this));
      }

      createContext(context);
      Object result = function.getBlock().acceptVisitor(this);
      destroyContext();
      return result;
   }

   @Override
   public Object handleFunctionDefinition(FunctionDefinitionInstruction instruction) {
      addFunction(instruction.getIdentifier(), instruction);
      return null;
   }

   @Override
   public Object handlePrintOut(PrintInstruction value) {
      output.println(value.getValue().acceptVisitor(this));
      return null;
   }
}
