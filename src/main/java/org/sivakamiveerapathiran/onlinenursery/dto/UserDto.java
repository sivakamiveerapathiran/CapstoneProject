package org.sivakamiveerapathiran.onlinenursery.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDto {
    private String Firstname;
    private String Lastname;

    private String Email;
}
