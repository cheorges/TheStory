package ch.cheorges.instruction.type;

import ch.cheorges.instruction.Instruction;
import ch.cheorges.instruction.InstructionVisitor;

public class StringLiteralInstruction extends Instruction implements TypeInstruction<String> {
   private final String value;

   public StringLiteralInstruction(String value) {
      this.value = value;
   }

   @Override
   public <R> R acceptVisitor(InstructionVisitor<R> visitor) {
      return visitor.handleStringLiteral(this);
   }

   @Override
   public String getValue() {
      return value;
   }

   @Override
   public String toString() {
      return value;
   }
}
