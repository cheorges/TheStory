package ch.cheorges.instruction;

import ch.cheorges.instruction.flow.InstructionScript;
import ch.cheorges.instruction.math.MathOperationInstruction;
import ch.cheorges.instruction.type.NumberInstruction;
import ch.cheorges.instruction.type.StringLiteralInstruction;
import ch.cheorges.instruction.variable.GetVariableInstruction;
import ch.cheorges.instruction.variable.InstructionSetVariable;

public interface InstructionVisitor<R> {
   R visitSetVariable(InstructionSetVariable instruction);

   R handleNumberInstruction(NumberInstruction instruction);

   R handleScript(InstructionScript instruction);

   R handleResolveVariable(GetVariableInstruction instruction);

   R handleStringLiteral(StringLiteralInstruction instruction);

   R handleMathOperation(MathOperationInstruction instruction);
}
