package ch.cheorges.instruction.function;

import java.util.List;

import ch.cheorges.instruction.Instruction;
import ch.cheorges.instruction.InstructionVisitor;

public class FunctionCallInstruction extends Instruction {
   private final String identifier;
   private final List<Instruction> values;

   public FunctionCallInstruction(String identifier, List<Instruction> values) {
      this.identifier = identifier;
      this.values = values;
   }

   @Override
   public <R> R acceptVisitor(InstructionVisitor<R> visitor) {
      return visitor.handleFunctionCall(this);
   }

   public String getIdentifier() {
      return identifier;
   }

   public List<Instruction> getValues() {
      return values;
   }
}
