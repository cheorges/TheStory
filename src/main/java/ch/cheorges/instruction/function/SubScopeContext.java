package ch.cheorges.instruction.function;

import java.util.HashMap;
import java.util.Map;

import ch.cheorges.exception.IdentifierNotDefinedException;

public abstract class SubScopeContext {
   private final Map<String, Object> globalContext;
   private Map<String, Object> context;

   public SubScopeContext(Map<String, Object> context) {
      this.globalContext = context;
      this.context = context;
   }

   public SubScopeContext() {
      this(new HashMap<>());
   }

   protected void addVariable(String identifier, Object value) {
      context.put(identifier, value);
   }

   protected void addFunction(String identifier, FunctionDefinitionInstruction function) {
      this.context.put(identifier, function);
   }


   protected Object getVariable(String identifier) {
      context.computeIfAbsent(identifier, i -> {
         throw new IdentifierNotDefinedException(i);
      });
      return context.get(identifier);
   }

   protected FunctionDefinitionInstruction getFunction(String identifier) {
      context.computeIfAbsent(identifier, i -> {
         throw new IdentifierNotDefinedException(i);
      });
      return (FunctionDefinitionInstruction) context.get(identifier);
   }

   protected void createContext(Map<String, Object> context) {
      this.context = context;
   }

   protected void destroyContext() {
      if (!this.globalContext.equals(context)) {
         this.context = globalContext;
      }
   }
}
