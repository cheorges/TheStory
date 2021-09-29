package ch.cheorges.instruction.function;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import ch.cheorges.exception.IdentifierNotDefinedException;

public abstract class ScopeContext {
   private final Map<String, Object> globalContext;
   private Map<String, Object> context;
   private String lastUsedIdentifier;

   public ScopeContext(Map<String, Object> context) {
      this.globalContext = context;
      this.context = context;
   }

   public ScopeContext() {
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

   protected void setValueToContext(String identifier, Object value) {
      lastUsedIdentifier = identifier;
      context.put(identifier.toLowerCase(Locale.ROOT), value);
   }

   protected Object getValueFromContext(String identifier) {
      if (Arrays.asList("he", "she", "it", "her", "his", "they").contains(identifier.toLowerCase(Locale.ROOT))) {
         return context.get(lastUsedIdentifier);
      }
      context.computeIfAbsent(identifier.toLowerCase(Locale.ROOT), i -> {
         throw new IdentifierNotDefinedException(i);
      });
      return context.get(identifier.toLowerCase(Locale.ROOT));
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
