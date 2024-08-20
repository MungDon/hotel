package com.example.demo.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserDeleteYN {

    DELETE("Y","탈퇴회원"),
    NOT_DELETE("N","회원");

    private final String type;

    private final String name;

    public static String typeToName(String type) {
        for(UserDeleteYN userDeleteYNEnum  : UserDeleteYN.values()) {
            if(userDeleteYNEnum.getType().equals(type)) {
                return userDeleteYNEnum.getName();
            }
        }
        return "없음";
    }

}
