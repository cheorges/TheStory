package ch.cheorges.instruction;

import ch.cheorges.instruction.condition.BooleanConditionInstruction;
import ch.cheorges.instruction.flow.InstructionScript;
import ch.cheorges.instruction.math.MathOperationInstruction;
import ch.cheorges.instruction.type.BooleanInstruction;
import ch.cheorges.instruction.type.NumberInstruction;
import ch.cheorges.instruction.type.StringLiteralInstruction;
import ch.cheorges.instruction.variable.GetVariableInstruction;
import ch.cheorges.instruction.variable.InstructionSetVariable;

public interface InstructionVisitor<R> {
   R visitSetVariable(InstructionSetVariable instruction);

   R handleScript(InstructionScript instruction);

   R handleResolveVariable(GetVariableInstruction instruction);

   R handleNumberInstruction(NumberInstruction instruction);

   R handleStringLiteral(StringLiteralInstruction instruction);

   R handleBooleanInstruction(BooleanInstruction instruction);

   R handleMathOperation(MathOperationInstruction instruction);

   R handleBooleanCondition(BooleanConditionInstruction instruction);

}
