package ch.cheorges.instruction.variable;

import ch.cheorges.instruction.Instruction;
import ch.cheorges.instruction.InstructionVisitor;

public class GetVariableInstruction extends Instruction {
   private final String identifier;

   public GetVariableInstruction(String identifier) {
      this.identifier = identifier;
   }

   @Override
   public <R> R acceptVisitor(InstructionVisitor<R> visitor) {
      return visitor.handleGetVariable(this);
   }

   public String getIdentifier() {
      return identifier;
   }
}
