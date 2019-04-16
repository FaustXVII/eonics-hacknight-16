package examples.functionalVsImperative;

import examples.functionalVsImperative.data.Person;
import examples.functionalVsImperative.data.RandomPersonGenerator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ImperativeToFunctional {
   private final List<Person> persons = new RandomPersonGenerator().get(1000);

   public ImperativeToFunctional(){
      System.out.println("Start!");
      imperative_EarnPerYear();
      imperative_EarnPerHour();
      System.out.println("Done!");

      // Goal, implement the same logic in a functional way.
      // Without duplicate code.
      // Easy maintainable.
      // Simple to extend.

      // Looking for inspiration? In the package `snippets`
      // Stuck? Take a look at `TheFunctionalWay.java`

      // Done and looking for more?
      // Try to get the most common first and last names by gender.
   }

   private void imperative_EarnPerYear(){
      BigDecimal lastHighestSalary = BigDecimal.ZERO;
      List<String> bigEarnersNames = new ArrayList<>();

      System.out.println("\nThe following people earn the most!");
      for(Person person : persons){
         int compare = person.getAnnualSalary().compareTo(lastHighestSalary);

         if(compare == 1){
            lastHighestSalary = person.getAnnualSalary();
            bigEarnersNames = new ArrayList<>();
            bigEarnersNames.add(person.getFirstName() + " " + person.getLastName());
         }else if (compare == 0){
            bigEarnersNames.add(person.getFirstName() + " " + person.getLastName());
         }
      }

      for(String name : bigEarnersNames){
         System.out.println(name);
      }
   }

   private void imperative_EarnPerHour(){
      BigDecimal lastHighestHourlyWage = BigDecimal.ZERO;
      List<String> bigEarnersNames = new ArrayList<>();

      System.out.println("\nThe following people have to highest hourly wage!");
      for(Person person : persons){
         int compare = person.getHourlyWage().compareTo(lastHighestHourlyWage);

         if(compare == 1){
            lastHighestHourlyWage = person.getHourlyWage();
            bigEarnersNames = new ArrayList<>();
            bigEarnersNames.add(person.getFirstName() + " " + person.getLastName());
         }else if (compare == 0){
            bigEarnersNames.add(person.getFirstName() + " " + person.getLastName());
         }
      }

      for(String name : bigEarnersNames){
         System.out.println(name);
      }
   }
}
