package humanly.instruction;

public interface InstructionVisitor<R> {
   R visitSetVariable(InstructionSetVariable instruction);
   R handleNumberInstruction(NumberInstruction instruction);
   R handleScript(InstructionScript instruction);
   R handleMathOperation(MathOperationInstruction instruction);
   R handleResolveVariable(GetVariableInstruction instruction);
   R handleStringLiteral(StringLiteralInstruction instruction);
}
