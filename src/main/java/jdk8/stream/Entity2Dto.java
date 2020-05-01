package jdk8.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description:
 *
 * @author zhangr
 * 2020/1/14 15:38
 * }
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
class User {
    private int id;
    private String name;
    private String passWd;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class UserDto {
    private int id;
    private String name;
    private String passWd;

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", passWd='" + passWd + '\'' +
                '}';
    }
}

public class Entity2Dto {
    public static void main(String[] args) {
        List<User> users = Arrays.asList(new User(1, "jack", "123456"),
                new User(2, "tom", "1234"),
                new User(3, "jerry", "123456"));
        List<UserDto> userList = users.stream().map(user ->
//                new UserDto(user.getId(), user.getName(), Base64.getEncoder().encodeToString(user.getPassWd().getBytes()))
                {
                    return new UserDto(user.getId(), user.getName(), Base64.getEncoder().encodeToString(user.getPassWd().getBytes()));
                }
        ).collect(Collectors.toList());
        System.out.println(userList);
    }
}
