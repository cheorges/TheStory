package ch.cheorges.instruction.sout;

import ch.cheorges.instruction.Instruction;
import ch.cheorges.instruction.InstructionVisitor;

public class PrintInstruction extends Instruction {
   private final Instruction value;

   public PrintInstruction(Instruction value) {
      this.value = value;
   }

   @Override
   public <R> R acceptVisitor(InstructionVisitor<R> visitor) {
      return visitor.handlePrintOut(this);
   }

   public Instruction getValue() {
      return value;
   }
}
