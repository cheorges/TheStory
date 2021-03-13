package ch.cheorges.instruction.type;

import ch.cheorges.instruction.Instruction;
import ch.cheorges.instruction.InstructionVisitor;

public class BooleanInstruction extends Instruction implements TypeInstruction<Boolean> {
   private final Boolean value;

   public BooleanInstruction(Boolean value) {
      this.value = value;
   }

   @Override
   public <R> R acceptVisitor(InstructionVisitor<R> visitor) {
      return visitor.handleBooleanInstruction(this);
   }

   @Override
   public Boolean getValue() {
      return value;
   }

   @Override
   public String toString() {
      return value.toString();
   }
}
