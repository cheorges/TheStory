package humanly.instruction;

public class InstructionSetVariable extends Instruction {
   private final String identifier;
   private final Instruction value;

   public InstructionSetVariable(String identifier, Instruction value) {
      this.identifier = identifier;
      this.value = value;
   }

   public <R> R acceptVisitor(InstructionVisitor<R> visitor) {
      return visitor.visitSetVariable(this);
   }

   public String getIdentifier() {
      return identifier;
   }

   public Instruction getValue() {
      return value;
   }
}
