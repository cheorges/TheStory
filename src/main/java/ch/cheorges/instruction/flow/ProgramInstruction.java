package ch.cheorges.instruction.flow;

import java.util.List;

import ch.cheorges.instruction.Instruction;
import ch.cheorges.instruction.InstructionVisitor;

public class ProgramInstruction extends Instruction {
   private final List<Instruction> instructions;

   public ProgramInstruction(List<Instruction> instructions) {
      this.instructions = instructions;
   }

   @Override
   public <T> T acceptVisitor(InstructionVisitor<T> visitor) {
      return visitor.handleProgram(this);
   }

   public List<Instruction> getInstructions() {
      return instructions;
   }
}
