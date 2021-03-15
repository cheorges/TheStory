package ch.cheorges.instruction.function;

import java.util.List;

import ch.cheorges.instruction.Instruction;
import ch.cheorges.instruction.InstructionVisitor;

public class FunctionDefinitionInstruction extends Instruction {
   private final String identifier;
   private final List<String> identifiers;
   private final Instruction block;

   public FunctionDefinitionInstruction(String identifier, List<String> identifiers, Instruction block) {
      this.identifier = identifier;
      this.identifiers = identifiers;
      this.block = block;
   }

   @Override
   public <R> R acceptVisitor(InstructionVisitor<R> visitor) {
      return visitor.handleFunctionDefinition(this);
   }

   public String getIdentifier() {
      return identifier;
   }

   public List<String> getIdentifiers() {
      return identifiers;
   }

   public Instruction getBlock() {
      return block;
   }
}
