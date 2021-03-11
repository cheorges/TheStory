package ch.cheorges.instruction.type;

import ch.cheorges.instruction.Instruction;
import ch.cheorges.instruction.InstructionVisitor;

public class StringLiteralInstruction extends Instruction {
   private final String value;

   public StringLiteralInstruction(String value) {
      this.value = value;
   }

   @Override
   public <R> R acceptVisitor(InstructionVisitor<R> visitor) {
      return visitor.handleStringLiteral(this);
   }

   public String getValue() {
      return value;
   }
}
