package ch.cheorges.instruction.condition;

import ch.cheorges.instruction.Instruction;
import ch.cheorges.instruction.InstructionVisitor;

public class BooleanConditionInstruction extends Instruction {
   private final Instruction leftValue;
   private final Instruction rightValue;
   private final ComparatorOperator comparatorOperator;

   public BooleanConditionInstruction(Instruction leftValue, Instruction rightValue, ComparatorOperator comparatorOperator) {
      this.leftValue = leftValue;
      this.rightValue = rightValue;
      this.comparatorOperator = comparatorOperator;
   }

   @Override
   public <R> R acceptVisitor(InstructionVisitor<R> visitor) {
      return visitor.handleBooleanCondition(this);
   }

   public Instruction getLeftValue() {
      return leftValue;
   }

   public Instruction getRightValue() {
      return rightValue;
   }

   public ComparatorOperator getCondition() {
      return comparatorOperator;
   }
}
