package humanly.instruction;

public class GetVariableInstruction extends Instruction {
   private final String identifier;

   public GetVariableInstruction(String identifier) {
      this.identifier = identifier;
   }

   @Override
   public <R> R acceptVisitor(InstructionVisitor<R> visitor) {
      return visitor.handleResolveVariable(this);
   }

   public String getIdentifier() {
      return identifier;
   }
}
