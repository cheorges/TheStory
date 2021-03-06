package ch.cheorges.instruction;

import ch.cheorges.instruction.condition.BooleanConditionInstruction;
import ch.cheorges.instruction.condition.ConditionalInstruction;
import ch.cheorges.instruction.flow.ProgramInstruction;
import ch.cheorges.instruction.function.FunctionCallInstruction;
import ch.cheorges.instruction.function.FunctionDefinitionInstruction;
import ch.cheorges.instruction.loop.LoopInstruction;
import ch.cheorges.instruction.math.MathOperationInstruction;
import ch.cheorges.instruction.sout.PrintInstruction;
import ch.cheorges.instruction.type.BooleanInstruction;
import ch.cheorges.instruction.type.NumberInstruction;
import ch.cheorges.instruction.type.StringLiteralInstruction;
import ch.cheorges.instruction.variable.GetVariableInstruction;
import ch.cheorges.instruction.variable.SetVariableInstruction;

public interface InstructionVisitor<R> {

   R visitSetVariable(SetVariableInstruction instruction);

   R handleGetVariable(GetVariableInstruction instruction);

   R handleNumberInstruction(NumberInstruction instruction);

   R handleStringLiteral(StringLiteralInstruction instruction);

   R handleBooleanInstruction(BooleanInstruction instruction);

   R handleMathOperation(MathOperationInstruction instruction);

   R handleBooleanCondition(BooleanConditionInstruction instruction);

   R handleConditional(ConditionalInstruction instruction);

   R handleLoop(LoopInstruction instruction);

   R handleProgram(ProgramInstruction instruction);

   R handleFunctionCall(FunctionCallInstruction instruction);

   R handleFunctionDefinition(FunctionDefinitionInstruction instruction);

   R handlePrintOut(PrintInstruction value);
}
