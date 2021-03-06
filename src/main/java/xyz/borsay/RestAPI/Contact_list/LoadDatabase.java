package xyz.borsay.RestAPI.Contact_list;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class is to look at for eventually adding standalone database.
 */
@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    /**
     * below just adds fake data to the database for testing
     * @param repository repository of the mem based database
     * @return logs
     */
    @Bean
    CommandLineRunner initDatabase(ContactRepository repository) {

        return args -> {
            log.info("Proloadiung " +
                    repository.save(new Contact("Samwise","Sam", "Gamgee", "411 Mushrooms Dr", "",
                            "Shire","Middle Earth","77700",
                            "567-545-2323","mobile","567-969-2727","home","samiswise@shire.me")));
            log.info("Proloadiung " +
                    repository.save(new Contact("Bilbo","", "Baggins", "111 Shire ln","",
                            "Shire","Middle Earth","77700",
                            "567-211-9840","home","567-914-0001","home", "")));
            log.info("Proloadiung " +
                    repository.save(new Contact("Frodo","", "Baggins","111 Shire ln","",
                            "Shire","Middle Earth","77700",
                            "567-211-0024","home","567-914-6574","mobile","frodo@undyinglands.me")));

            log.info("Proloadiung " +
                    repository.save(new Contact("Meriadoc  ","Merry", "Brandybuck","975 Maggots St", "",
                            "Shire","Middle Earth","77700",
                            "567-211-1111","home","567-838-9257","work", "Merbuck@shire.me")));

            log.info("Proloadiung " +
                    repository.save(new Contact("Peregrin  ","Pippin", "Took","471 Ferry ln", "",
                            "Shire","Middle Earth","77700",
                            "567-211-1111","home","567-838-9257","work","pip.took@shire.me")));


        };
    }
}