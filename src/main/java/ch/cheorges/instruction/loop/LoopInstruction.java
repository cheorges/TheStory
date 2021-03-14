package ch.cheorges.instruction.loop;

import ch.cheorges.instruction.Instruction;
import ch.cheorges.instruction.InstructionVisitor;

public class LoopInstruction extends Instruction {

   private final Instruction condition;
   private final Instruction block;

   public LoopInstruction(Instruction condition, Instruction block) {
      this.condition = condition;
      this.block = block;
   }

   @Override
   public <R> R acceptVisitor(InstructionVisitor<R> visitor) {
      return visitor.handleLoop(this);
   }

   public Instruction getCondition() {
      return condition;
   }

   public Instruction getBlock() {
      return block;
   }
}
