package ch.cheorges.instruction.type;

import java.math.BigDecimal;
import java.math.MathContext;

import ch.cheorges.instruction.Instruction;
import ch.cheorges.instruction.InstructionVisitor;

public class NumberInstruction extends Instruction implements TypeInstruction<BigDecimal> {
   private final BigDecimal value;

   public NumberInstruction(String value) {
      this.value = new BigDecimal(value, MathContext.UNLIMITED);
   }

   @Override
   public <R> R acceptVisitor(InstructionVisitor<R> visitor) {
      return visitor.handleNumberInstruction(this);
   }

   @Override
   public BigDecimal getValue() {
      return value;
   }

   @Override
   public String toString() {
      return value.toString();
   }
}
