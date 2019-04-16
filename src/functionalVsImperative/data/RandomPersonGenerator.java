package functionalVsImperative.data;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RandomPersonGenerator {
    private Random random = new Random();

    public List<Person> next(int n){
        return Stream.generate(this::next).limit(n).collect(Collectors.toList());
    }

    public Person next(){
        Person person = new Person();

        final Gender gender = randomGender();
        person.setGender(gender);
        person.setFirstName(randomGenderName(gender));
        person.setLastName(randomLastName());
        person.setAge(randomAge());

        final boolean isUnemployed = randomUnemployed();
        person.setUnemployed(isUnemployed );
        if (isUnemployed){
            person.setAnnualSalary(BigDecimal.ZERO);
            person.setHoursPerWeek(0);
        }else{
            person.setAnnualSalary(randomAnnualSalary());
            person.setHoursPerWeek(randomWorkingHours_perWeek());
        }

        return person;
    }

    private String randomGenderName(Gender gender){
        switch (gender){
            case MALE : return randomNameMan();
            case FEMALE: return randomNameWoman();
            default: return randomName();
        }
    }

    private String randomName(){
        return (random.nextBoolean())? randomNameWoman() : randomNameMan();
    }

    private String randomNameWoman(){
        List<String> names = Arrays.asList(
            "Emma", "Olivia", "Ava", "Isabella", "Sophia", "Mia", "Charlotte", "Amelia", "Evelyn", "Abigail", "Harper",
            "Emily", "Elizabeth", "Avery", "Sofia", "Ella", "Madison", "Scarlett", "Victoria", "Aria", "Grace", "Chloe",
            "Camila", "Penelope", "Riley", "Layla", "Lillian", "Nora", "Zoey", "Mila", "Aubrey", "Hannah", "Lily", "Addison",
            "Eleanor", "Natalie", "Luna", "Savannah", "Brooklyn", "Leah", "Zoe", "Stella", "Hazel", "Ellie", "Paisley",
            "Audrey", "Skylar", "Violet", "Claire", "Bella", "Aurora", "Lucy", "Anna", "Samantha", "Caroline", "Genesis",
            "Aaliyah", "Kennedy", "Kinsley", "Allison", "Maya", "Sarah", "Madelyn", "Adeline", "Alexa", "Ariana", "Elena",
            "Gabriella", "Naomi", "Alice", "Sadie", "Hailey", "Eva", "Emilia", "Autumn", "Quinn", "Nevaeh", "Piper", "Ruby",
            "Serenity", "Willow", "Everly", "Cora", "Kaylee", "Lydia", "Aubree", "Arianna", "Eliana", "Peyton", "Melanie",
            "Gianna", "Isabelle", "Julia", "Valentina", "Nova", "Clara", "Vivian", "Reagan", "Mackenzie", "Madeline");
        return names.get(random.nextInt(names.size()));
    }
    
    private String randomNameMan(){
        List<String> names = Arrays.asList(
            "Liam", "Noah", "William", "James", "Logan", "Benjamin", "Mason", "Elijah", "Oliver", "Jacob", "Lucas",
            "Michael", "Alexander", "Ethan", "Daniel", "Matthew", "Aiden", "Henry", "Joseph", "Jackson", "Samuel",
            "Sebastian", "David", "Carter", "Wyatt", "Jayden", "John", "Owen", "Dylan", "Luke", "Gabriel", "Anthony",
            "Isaac", "Grayson", "Jack", "Julian", "Levi", "Christopher", "Joshua", "Andrew", "Lincoln", "Mateo", "Ryan",
            "Jaxon", "Nathan", "Aaron", "Isaiah", "Thomas", "Charles", "Caleb", "Josiah", "Christian", "Hunter", "Eli",
            "Jonathan", "Connor", "Landon", "Adrian", "Asher", "Cameron", "Leo", "Theodore", "Jeremiah", "Hudson", "Robert",
            "Easton", "Nolan", "Nicholas", "Ezra", "Colton", "Angel", "Brayden", "Jordan", "Dominic", "Austin", "Ian", "Adam",
            "Elias", "Jaxson", "Greyson", "Jose", "Ezekiel", "Carson", "Evan", "Maverick", "Bryson", "Jace", "Cooper", "Xavier",
            "Parker", "Roman", "Jason", "Santiago", "Chase", "Sawyer", "Gavin", "Leonardo", "Kayden", "Ayden", "Jameson");

        return names.get(random.nextInt(names.size()));
    }
    
    private String randomLastName(){
        List<String> lastNames = Arrays.asList(
            "Smith", "Johnson", "Williams", "Jones", "Brown",
            "Davis", "Miller", "Wilson", "Moore", "Taylor", "Anderson", "Thomas", "Jackson", "White", "Harris",
            "Martin", "Thompson", "Garcia", "Martinez", "Robinson", "Clark", "Rodriguez", "Lewis", "Lee", "Walker",
            "Hall", "Allen", "Young", "Hernandez", "King", "Wright", "Lopez", "Hill", "Scott", "Green", "Adams", "Baker",
            "Gonzalez", "Nelson", "Carter", "Mitchell", "Perez", "Roberts", "Turner", "Phillips", "Campbell", "Parker",
            "Evans", "Edwards", "Collins", "Stewart", "Sanchez", "Morris", "Rogers", "Reed", "Cook", "Morgan", "Bell",
            "Murphy", "Bailey", "Rivera", "Cooper", "Richardson", "Cox", "Howard", "Ward", "Torres", "Peterson", "Gray",
            "Ramirez", "James", "Watson", "Brooks", "Kelly", "Sanders", "Price", "Bennett", "Wood", "Barnes", "Ross",
            "Henderson", "Coleman", "Jenkins", "Perry", "Powell", "Long", "Patterson", "Hughes", "Flores", "Washington",
            "Butler", "Simmons", "Foster", "Gonzales", "Bryant", "Alexander", "Russell", "Griffin", "Diaz", "Hayes");

        return lastNames.get(random.nextInt(lastNames.size()));
    }

    private Gender randomGender(){
        final int randomNumber = random.nextInt(21);

        if(randomNumber < 10){
            return Gender.MALE;
        }else if (randomNumber < 20){
            return  Gender.FEMALE;
        }else{
            return Gender.OTHER;
        }
    }

    private int randomWorkingHours_perWeek(){
        final int randomNumber = random.nextInt(21);

        if(randomNumber < 13){
            return 40;
        }else if (randomNumber < 17){
            return 36;
        }else if (randomNumber < 20){
            return 32;
        }else{
            return (random.nextInt(9) * (random.nextInt(3)+1)) + 8;
        }
    }

    private boolean randomUnemployed(){
        if(random.nextInt(21) < 20)
            return false;
        return true;
    }

    private BigDecimal randomAnnualSalary(){
       return BigDecimal.valueOf((random.nextInt(8)+2) * (random.nextInt(8)+2) * 1000);
    }

    private int randomAge(){
        return 15 + (random.nextInt(10) * random.nextInt(10));
    }
}
