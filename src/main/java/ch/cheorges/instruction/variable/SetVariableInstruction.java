package ch.cheorges.instruction.variable;

import ch.cheorges.instruction.Instruction;
import ch.cheorges.instruction.InstructionVisitor;

public class SetVariableInstruction extends Instruction {
   private final String identifier;
   private final Instruction value;

   public SetVariableInstruction(String identifier, Instruction value) {
      this.identifier = identifier;
      this.value = value;
   }

   public <R> R acceptVisitor(InstructionVisitor<R> visitor) {
      return visitor.visitSetVariable(this);
   }

   public String getIdentifier() {
      return identifier;
   }

   public Instruction getValue() {
      return value;
   }
}
