package ch.cheorges.instruction.type;

import ch.cheorges.instruction.Instruction;
import ch.cheorges.instruction.InstructionVisitor;

import java.math.BigDecimal;
import java.math.MathContext;

public class NumberInstruction extends Instruction {
   private BigDecimal value;

   public NumberInstruction(String value) {
      this.value = new BigDecimal(value, MathContext.UNLIMITED);
   }

   @Override
   public <R> R acceptVisitor(InstructionVisitor<R> visitor) {
      return visitor.handleNumberInstruction(this);
   }

   public BigDecimal getValue() {
      return value;
   }
}
