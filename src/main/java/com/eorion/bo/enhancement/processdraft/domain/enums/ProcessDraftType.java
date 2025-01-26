package com.eorion.bo.enhancement.processdraft.domain.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum ProcessDraftType {

    DRAFT("1", "DRAFT"),
    TEMPLATE("2", "TEMPLATE"),
    OTHER("3", "other");

    private static final Map<String, ProcessDraftType> BY_VALUE = new HashMap<>();

    static {
        for (ProcessDraftType e : values()) {
            BY_VALUE.put(e.value, e);
        }
    }

    @EnumValue
    private final String value;
    private final String desc;

    ProcessDraftType(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @JsonCreator
    public static ProcessDraftType from(String s) {
        return BY_VALUE.get(s);
    }
}
