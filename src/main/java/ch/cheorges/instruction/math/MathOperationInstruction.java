package ch.cheorges.instruction.math;

import ch.cheorges.instruction.Instruction;
import ch.cheorges.instruction.InstructionVisitor;

public class MathOperationInstruction extends Instruction {
   private final Instruction leftValue;
   private final Instruction rightValue;
   private final MathOperator operator;

   public MathOperationInstruction(Instruction leftValue, Instruction rightValue, MathOperator operator) {
      this.leftValue = leftValue;
      this.rightValue = rightValue;
      this.operator = operator;
   }

   @Override
   public <R> R acceptVisitor(InstructionVisitor<R> visitor) {
      return visitor.handleMathOperation(this);
   }

   public Instruction getLeftValue() {
      return leftValue;
   }

   public Instruction getRightValue() {
      return rightValue;
   }

   public MathOperator getOperator() {
      return operator;
   }
}
