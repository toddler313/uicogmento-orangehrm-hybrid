package actions.util;

import actions.commons.GlobalConstants;
import com.github.javafaker.CreditCardType;
import com.github.javafaker.Faker;

import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class FakerDataHelper {
    private Locale locale = new Locale("en");
    private Faker faker = new Faker(locale);
    private Random random = new Random();

    public static FakerDataHelper getDataHelper() {
        return new FakerDataHelper();
    }
    public String getFirstName() {
        return faker.name().firstName();
    }
    public String getLastName() {
        return faker.name().lastName();
    }

    public String getMiddleName() {
        return faker.name().lastName();
    }

    public String getFullName() {
        return faker.name().fullName();
    }
    public String getEmailAddress() {
        return faker.internet().emailAddress();
    }
    public String getStreetAddress() {
        return faker.address().fullAddress();
    }
    public String getPhoneNumber() {
        return faker.phoneNumber().phoneNumber();
    }
    public String getCreditCard() {
        return faker.finance().creditCard(CreditCardType.VISA);
    }
    public String getAvatar () {
        return faker.avatar().image();
    }
    public String getPassword () {
        return faker.internet().password(true);
    }
    public String getCompanyName() {
        return faker.company().name();
    }
    public String getRandomDate() {
        return String.valueOf(1 + random.nextInt(29));
    }
    public String getRandomMonth() {
        return String.valueOf(1 + random.nextInt(12));
    }
    public String getRandomYear() {
        return String.valueOf(1975 + random.nextInt(31));
    }
    public String getJobTitle() {
        return faker.job().title() + " " + random.nextInt(1000000);
    }
    public String getJobDescription() {
        return faker.shakespeare().asYouLikeItQuote();
    }
    public String getJobNotes() {
        return faker.shakespeare().kingRichardIIIQuote();
    }
    public static String randomEmailWithGivenDomain(String domain) {
        StringBuilder email = new StringBuilder();
        String[] characters = GlobalConstants.get().getAlphaNumericString().split("");
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            email.append(characters[random.nextInt(characters.length)]);
        }
        email.append("@").append(domain);
        return email.toString();
    }
    public static String randomWords(int numberOfWord, int lengthOfWord) {
        StringBuilder words = new StringBuilder();
        String[] characters = GlobalConstants.get().getCharacterString().split("");
        Random random = new Random();
        for (int i = 0; i < numberOfWord; i++) {
            if (words.length() != 0)
                words.append(" ");
            for (int j = 0; j < lengthOfWord; j++) {
                words.append(characters[random.nextInt(characters.length)]);
            }
        }
        return words.toString();
    }
    public static String randomString(int numberOfWord, int lengthOfWord) {
        StringBuilder words = new StringBuilder();
        String[] characters = GlobalConstants.get().getAlphaNumericString().split("");
        Random random = new Random();
        for (int i = 0; i < numberOfWord; i++) {
            if (words.length() != 0)
                words.append(" ");
            for (int j = 0; j < lengthOfWord; j++) {
                words.append(characters[random.nextInt(characters.length)]);
            }
        }
        return words.toString();
    }
    public static long generateRandomNumberWithNDigits(int numberOfDigits) {
        long min = (long) Math.pow(10, numberOfDigits - 1);
        long max = (long) Math.pow(10, numberOfDigits) - 1;
        return ThreadLocalRandom.current().nextLong(min, max + 1);
    }
}
