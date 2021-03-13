package ch.cheorges.instruction.flow;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ch.cheorges.instruction.Instruction;
import ch.cheorges.instruction.InstructionVisitor;

public class InstructionScript extends Instruction {
   private final List<Instruction> instructions = new ArrayList<>();

   public InstructionScript(Instruction instruction, Instruction lastInstruction) {
      instructions.add(instruction);
      if (Objects.nonNull(lastInstruction)) {
         instructions.add(lastInstruction);
      }
   }

   @Override
   public <R> R acceptVisitor(InstructionVisitor<R> visitor) {
      return visitor.handleScript(this);
   }

   public List<Instruction> getInstructions() {
      return instructions;
   }

}
