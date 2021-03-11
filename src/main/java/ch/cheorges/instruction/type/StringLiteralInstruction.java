package humanly.instruction;

public class StringLiteralInstruction extends Instruction {
   private String value;

   public StringLiteralInstruction(String value) {
      this.value = value;
   }

   @Override
   public <R> R acceptVisitor(InstructionVisitor<R> visitor) {
      return visitor.handleStringLiteral(this);
   }

   public String getValue() {
      return value;
   }

   public void setValue(String value) {
      this.value = value;
   }
}
