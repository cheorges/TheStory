package ch.cheorges.instruction.condition;

import ch.cheorges.instruction.Instruction;
import ch.cheorges.instruction.InstructionVisitor;

public class BooleanConditionInstruction extends Instruction {
   private final Instruction leftValue;
   private final Instruction rightValue;
   private final ConditionOperator conditionOperator;

   public BooleanConditionInstruction(Instruction leftValue, Instruction rightValue, ConditionOperator conditionOperator) {
      this.leftValue = leftValue;
      this.rightValue = rightValue;
      this.conditionOperator = conditionOperator;
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

   public ConditionOperator getCondition() {
      return conditionOperator;
   }
}
