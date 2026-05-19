package org.ess.module.user.repository;

import com.github.javafaker.Faker;
import org.ess.module.user.model.UserModel;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

public class UserFakeRepository {

    public static List<UserModel> getUsers(int count) {

        Function<LocalDate, String> formatDate = (LocalDate date) -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return date.format(formatter);
        };

        return IntStream.rangeClosed(1, count)
                .boxed()
                .toList().stream()
                .map(i -> new UserModel()
                        .setUsername(Faker.instance().name().username())
                        .setFirstName(Faker.instance().name().firstName())
                        .setLastName(Faker.instance().name().lastName())
                        .setDateOfBirth(formatDate.apply(Faker.instance().date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()))
                        .setCreatedAt(formatDate.apply(Faker.instance().date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()))
                        .setUpdatedAt(formatDate.apply(Faker.instance().date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()))
                        .setRole(Faker.instance().random().nextBoolean() ? "Admin" : "User"))
                .toList();
    }
}
