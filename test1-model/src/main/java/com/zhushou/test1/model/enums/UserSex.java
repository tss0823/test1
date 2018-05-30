package com.zhushou.test1.model.enums;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.yuntao.platform.common.annotation.EnumComment;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@EnumComment("用户性别")
public enum UserSex {

    woman(0, "女"),

    man(1, "男"),;

    private int code;
    private String description;

    UserSex(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static UserSex getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (UserSex s : UserSex.values()) {
            if (s.getCode() == code) {
                return s;
            }
        }
        return null;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
