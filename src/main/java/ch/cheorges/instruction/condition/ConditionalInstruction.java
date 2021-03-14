package ch.cheorges.instruction.condition;

import ch.cheorges.instruction.Instruction;
import ch.cheorges.instruction.InstructionVisitor;
import ch.cheorges.instruction.flow.ScriptInstruction;

public class ConditionalInstruction extends Instruction {
   private final Instruction condition;
   private final Instruction trueBlock;
   private final Instruction falseBlock;

   public ConditionalInstruction(Instruction condition, Instruction trueBlock) {
      this.condition = condition;
      this.trueBlock = trueBlock;
      this.falseBlock = new ScriptInstruction();
   }

   public ConditionalInstruction(Instruction condition, Instruction trueBlock, Instruction falseBlock) {
      this.condition = condition;
      this.trueBlock = trueBlock;
      this.falseBlock = falseBlock;
   }

   @Override
   public <R> R acceptVisitor(InstructionVisitor<R> visitor) {
      return visitor.handleConditional(this);
   }

   public Instruction getCondition() {
      return condition;
   }

   public Instruction getTrueBlock() {
      return trueBlock;
   }

   public Instruction getFalseBlock() {
      return falseBlock;
   }
}
