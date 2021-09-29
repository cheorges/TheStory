package ch.cheorges;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.stream.Stream;

import ch.cheorges.instruction.Instruction;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.Symbol;
import v1.Parser;
import v1.Scanner;

public class Runner {

   public static void main(String[] args) {
      Stream.of(args).forEach(Runner::handleFile);
   }

   private static void handleFile(String filePath) {
      try (FileReader reader = new FileReader(filePath)) {
         Symbol symbol = new Parser(new Scanner(reader), new ComplexSymbolFactory()).parse();
         ((Instruction) symbol.value).acceptVisitor(new Evaluator());
      } catch (FileNotFoundException e) {
         System.out.println("No or invalid File found by given file path: " + filePath);
      } catch (Exception e) {
         System.out.println(e.getMessage());
      }

   }

}