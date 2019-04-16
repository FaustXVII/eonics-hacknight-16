package examples.functionalVsImperative;

import examples.functionalVsImperative.data.Gender;
import examples.functionalVsImperative.data.Person;
import examples.functionalVsImperative.data.RandomPersonGenerator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TheFunctionalWay {
    Function<Predicate, Function<List<Person>, Long>> numberOfPeopleAfterFilter =
        predicate -> persons -> persons.stream()
            .parallel()
            .filter(predicate)
            .count();

    Function<Predicate<Person>,Function<List<Person>, BigDecimal> > averageAnnualSalary_WithFilter =
        predicate -> persons ->
        persons.stream()
            .parallel()
            .filter(predicate)
            .map(person -> person.getAnnualSalary())
            .reduce(BigDecimal.ZERO, BigDecimal::add)
            .divide(BigDecimal.valueOf(
                numberOfPeopleAfterFilter
                    .apply(predicate)
                    .apply(persons))
                , 2, RoundingMode.HALF_UP);

    Predicate<Person> noFilter = person -> true;
    Predicate<Person> onlyMan = person -> person.getGender().equals(Gender.MALE);
    Predicate<Person> onlyWoman = person -> person.getGender().equals(Gender.FEMALE);
    Predicate<Person> onlyOther = person -> person.getGender().equals(Gender.OTHER);
    Predicate<Person> onlyKids = person -> person.getAge() < 18;
    Predicate<Person> unemployed = person -> person.isUnemployed();

    Function<List<Person>, BigDecimal> averageAnnualSalary = averageAnnualSalary_WithFilter.apply(noFilter);
    Function<List<Person>, BigDecimal> averageAnnualSalaryMale = averageAnnualSalary_WithFilter.apply(onlyMan);
    Function<List<Person>, BigDecimal> averageAnnualSalaryFemale = averageAnnualSalary_WithFilter.apply(onlyWoman);
    Function<List<Person>, BigDecimal> averageAnnualSalaryUnder_18 = averageAnnualSalary_WithFilter.apply(onlyKids);

    Function<BigDecimal, Function<BigDecimal, BigDecimal>> percentageOf =
        value -> total -> value.divide(total).multiply(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_UP);

    Function<List<Person>, BigDecimal> percentageOfUnemployed = persons -> percentageOf
        .apply(BigDecimal.valueOf(
            numberOfPeopleAfterFilter
                .apply(unemployed)
                .apply(persons)))
        .apply(BigDecimal.valueOf(persons.size()));

    Function<Predicate<Person>, Function<Function<Person, String>, Function<List<Person>, String>>> name_thatIsMostCommon =
        predicate -> getName -> persons -> persons.stream()
            .filter(predicate)
            .collect(Collectors.groupingBy(getName, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Comparator.comparing(Map.Entry::getValue))
            .map(entry -> entry.getKey())
            .get();

    Function<Person, String> firstName = person -> person.getFirstName();
    Function<Person, String> lastName = person -> person.getLastName();
    Function<List<Person>, String> maleFirstName_thatIsMostCommon = name_thatIsMostCommon.apply(onlyMan).apply(firstName);
    Function<List<Person>, String> maleLastName_thatIsMostCommon = name_thatIsMostCommon.apply(onlyMan).apply(lastName);
    Function<List<Person>, String> femaleFirstName_thatIsMostCommon = name_thatIsMostCommon.apply(onlyWoman).apply(firstName );
    Function<List<Person>, String> femaleLastName_thatIsMostCommon = name_thatIsMostCommon.apply(onlyWoman).apply(lastName);

    Function<List<Person>, BigDecimal> largestAnnualSalary = persons -> persons.stream()
        .max(Comparator.comparing(Person::getAnnualSalary))
        .map(person -> person.getAnnualSalary())
        .get();

    Function<List<Person>, BigDecimal> largestHourlySalary = persons -> persons.stream()
        .max(Comparator.comparing(Person::getHourlyWage))
        .map(person -> person.getHourlyWage())
        .get();

    Function<BigDecimal, Predicate<Person>> filterAnnalSalary =
        salary -> person -> person.getAnnualSalary().equals(salary);

    Function<BigDecimal, Predicate<Person>> filterHourlyWage =
        hourlyWage -> person -> person.getHourlyWage().equals(hourlyWage);

    Function<Predicate<Person>, Function<List<Person>, List<String>>> fullNameAfterfilter =
        predicate -> persons -> persons.stream()
        .filter(predicate)
        .map(person -> person.getFirstName() + " " + person.getLastName())
        .collect(Collectors.toList());

    public TheFunctionalWay(){
        List<Person> persons = new RandomPersonGenerator().get(10_000_000);

        System.out.println("Started!\n");
        System.out.println("Average Annual Salary = " + averageAnnualSalary.apply(persons));
        System.out.println("Average Annual Salary for man = " + averageAnnualSalaryMale.apply(persons));
        System.out.println("Average Annual Salary for woman = " + averageAnnualSalaryFemale.apply(persons));
        System.out.println("Average Annual Salary for kids = " + averageAnnualSalaryUnder_18.apply(persons));

        System.out.println("Number of man = " + numberOfPeopleAfterFilter.apply(onlyMan).apply(persons));
        System.out.println("Number of woman = " + numberOfPeopleAfterFilter.apply(onlyWoman).apply(persons));
        System.out.println("Number of other-genders = " + numberOfPeopleAfterFilter.apply(onlyOther).apply(persons));

        System.out.println(percentageOfUnemployed.apply(persons) + "% of the people are unemployed.\n");

        System.out.println(maleFirstName_thatIsMostCommon.apply(persons) + " - is the most common first-name for man.");
        System.out.println(femaleFirstName_thatIsMostCommon.apply(persons) + " - is the most common first-name for woman.");
        System.out.println(maleLastName_thatIsMostCommon.apply(persons) + " - is the most common last-name for man.");
        System.out.println(femaleLastName_thatIsMostCommon.apply(persons) + " - is the most common last-name for woman.");

        BigDecimal largestAnnualSalary = this.largestAnnualSalary.apply(persons);
        System.out.println("\nThe following people have the highest annual salary [" + largestAnnualSalary + "]");
        List<String> namesOfThePeopleThatEarnTheMost = fullNameAfterfilter.apply(filterAnnalSalary.apply(largestAnnualSalary)).apply(persons);
        namesOfThePeopleThatEarnTheMost.subList(0, 10).forEach(System.out::println);
        System.out.println("And " + (namesOfThePeopleThatEarnTheMost.size() -10) + " More...");

        BigDecimal largestHourlySalary = this.largestHourlySalary.apply(persons);
        System.out.println("\nThe following people have the highest hourly wage of : " + largestHourlySalary + "/hour");
        List<String> namesOfThePeopleThatEarnTheMostPerHour = fullNameAfterfilter.apply(filterHourlyWage.apply(largestHourlySalary)).apply(persons);
        namesOfThePeopleThatEarnTheMostPerHour.subList(0, 10).forEach(System.out::println);
        System.out.println("And " + (namesOfThePeopleThatEarnTheMostPerHour.size() -10) + " More...");

        System.out.println("\nDone!");
    }

    // average annual salary
    // average annual salary male
    // average annual salary female
    // average annual salary under 18

    // Number of male's
    // Number of female's
    // Number of other's

    // Percentage of unemployed people

    // most common male first name
    // most common female first name
    // most common male last name
    // most common female last name

    // list of people by name that earn the most
    // list of people that earn the most per hour.
}
