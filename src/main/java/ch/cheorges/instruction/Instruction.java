package humanly.instruction;

public abstract class Instruction {
   public abstract <R> R acceptVisitor(InstructionVisitor<R> visitor);
}
