package humanly.instruction;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
