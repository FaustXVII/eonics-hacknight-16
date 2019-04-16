package examples;

import functionalVsImperative.data.Gender;
import functionalVsImperative.data.Person;
import functionalVsImperative.data.RandomPersonGenerator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TheFunctionalWay {
    private Function<Predicate<Person>, Function<List<Person>, Long>> numberOfPeopleAfterFilter =
        predicate -> persons -> persons.stream()
            .parallel()
            .filter(predicate)
            .count();

    private Function<Predicate<Person>,Function<List<Person>, BigDecimal> > averageAnnualSalary_WithFilter =
        predicate -> persons ->
        persons.stream()
            .parallel()
            .filter(predicate)
            .map(Person::getAnnualSalary)
            .reduce(BigDecimal.ZERO, BigDecimal::add)
            .divide(BigDecimal.valueOf(
                numberOfPeopleAfterFilter
                    .apply(predicate)
                    .apply(persons))
                , 2, RoundingMode.HALF_UP);

    private Predicate<Person> noFilter = person -> true;
    private Predicate<Person> onlyMan = person -> person.getGender().equals(Gender.MALE);
    private Predicate<Person> onlyWoman = person -> person.getGender().equals(Gender.FEMALE);
    private Predicate<Person> onlyOther = person -> person.getGender().equals(Gender.OTHER);
    private Predicate<Person> onlyKids = person -> person.getAge() < 18;
    private Predicate<Person> unemployed = Person::isUnemployed;

    private Function<List<Person>, BigDecimal> averageAnnualSalary = averageAnnualSalary_WithFilter.apply(noFilter);
    private Function<List<Person>, BigDecimal> averageAnnualSalaryMale = averageAnnualSalary_WithFilter.apply(onlyMan);
    private Function<List<Person>, BigDecimal> averageAnnualSalaryFemale = averageAnnualSalary_WithFilter.apply(onlyWoman);
    private Function<List<Person>, BigDecimal> averageAnnualSalaryUnder_18 = averageAnnualSalary_WithFilter.apply(onlyKids);

    private Function<BigDecimal, Function<BigDecimal, BigDecimal>> percentageOf =
        value -> total -> value.divide(total).multiply(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_UP);

    private Function<List<Person>, BigDecimal> percentageOfUnemployed = persons -> percentageOf
        .apply(BigDecimal.valueOf(
            numberOfPeopleAfterFilter
                .apply(unemployed)
                .apply(persons)))
        .apply(BigDecimal.valueOf(persons.size()));

    private Function<Predicate<Person>, Function<Function<Person, String>, Function<List<Person>, String>>> name_thatIsMostCommon =
        predicate -> getName -> persons -> persons.stream()
            .filter(predicate)
            .collect(Collectors.groupingBy(getName, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Comparator.comparing(Map.Entry::getValue))
            .map(Map.Entry::getKey)
            .get();

    private Function<Person, String> firstName = Person::getFirstName;
    private Function<Person, String> lastName = Person::getLastName;
    private Function<List<Person>, String> maleFirstName_thatIsMostCommon = name_thatIsMostCommon.apply(onlyMan).apply(firstName);
    private Function<List<Person>, String> maleLastName_thatIsMostCommon = name_thatIsMostCommon.apply(onlyMan).apply(lastName);
    private Function<List<Person>, String> femaleFirstName_thatIsMostCommon = name_thatIsMostCommon.apply(onlyWoman).apply(firstName );
    private Function<List<Person>, String> femaleLastName_thatIsMostCommon = name_thatIsMostCommon.apply(onlyWoman).apply(lastName);

    private Function<List<Person>, BigDecimal> largestAnnualSalary = persons -> persons.stream()
        .max(Comparator.comparing(Person::getAnnualSalary))
        .map(Person::getAnnualSalary)
        .get();

    private Function<List<Person>, BigDecimal> largestHourlySalary = persons -> persons.stream()
        .max(Comparator.comparing(Person::getHourlyWage))
        .map(Person::getHourlyWage)
        .get();

    private Function<BigDecimal, Predicate<Person>> filterAnnalSalary =
        salary -> person -> person.getAnnualSalary().equals(salary);

    private Function<BigDecimal, Predicate<Person>> filterHourlyWage =
        hourlyWage -> person -> person.getHourlyWage().equals(hourlyWage);

    private Function<Predicate<Person>, Function<List<Person>, List<String>>> fullNameAfterFilter =
        predicate -> persons -> persons.stream()
        .filter(predicate)
        .map(person -> person.getFirstName() + " " + person.getLastName())
        .collect(Collectors.toList());

    public void run(){
        List<Person> persons = new RandomPersonGenerator().next(10_000_000);

        System.out.println("Started!\n");
        // average annual salary
        // average annual salary male
        // average annual salary female
        // average annual salary under 18
        System.out.println("Average Annual Salary = " + averageAnnualSalary.apply(persons));
        System.out.println("Average Annual Salary for man = " + averageAnnualSalaryMale.apply(persons));
        System.out.println("Average Annual Salary for woman = " + averageAnnualSalaryFemale.apply(persons));
        System.out.println("Average Annual Salary for kids = " + averageAnnualSalaryUnder_18.apply(persons));

        // Number of male's
        // Number of female's
        // Number of other's
        System.out.println("Number of man = " + numberOfPeopleAfterFilter.apply(onlyMan).apply(persons));
        System.out.println("Number of woman = " + numberOfPeopleAfterFilter.apply(onlyWoman).apply(persons));
        System.out.println("Number of other-genders = " + numberOfPeopleAfterFilter.apply(onlyOther).apply(persons));

        // Percentage of unemployed people
        System.out.println(percentageOfUnemployed.apply(persons) + "% of the people are unemployed.\n");

        // most common male first name
        // most common female first name
        // most common male last name
        // most common female last name
        System.out.println(maleFirstName_thatIsMostCommon.apply(persons) + " - is the most common first-name for man.");
        System.out.println(femaleFirstName_thatIsMostCommon.apply(persons) + " - is the most common first-name for woman.");
        System.out.println(maleLastName_thatIsMostCommon.apply(persons) + " - is the most common last-name for man.");
        System.out.println(femaleLastName_thatIsMostCommon.apply(persons) + " - is the most common last-name for woman.");

        // list of people by name that earn the most
        // list of people that earn the most per hour.
        BigDecimal largestAnnualSalary = this.largestAnnualSalary.apply(persons);
        System.out.println("\nThe following people have the highest annual salary [" + largestAnnualSalary + "]");
        List<String> namesOfThePeopleThatEarnTheMost = fullNameAfterFilter.apply(filterAnnalSalary.apply(largestAnnualSalary)).apply(persons);
        namesOfThePeopleThatEarnTheMost.subList(0, 10).forEach(System.out::println);
        System.out.println("And " + (namesOfThePeopleThatEarnTheMost.size() -10) + " More...");

        BigDecimal largestHourlySalary = this.largestHourlySalary.apply(persons);
        System.out.println("\nThe following people have the highest hourly wage of : " + largestHourlySalary + "/hour");
        List<String> namesOfThePeopleThatEarnTheMostPerHour = fullNameAfterFilter.apply(filterHourlyWage.apply(largestHourlySalary)).apply(persons);
        namesOfThePeopleThatEarnTheMostPerHour.subList(0, 10).forEach(System.out::println);
        System.out.println("And " + (namesOfThePeopleThatEarnTheMostPerHour.size() -10) + " More...");

        System.out.println("\nDone!");
    }
}
