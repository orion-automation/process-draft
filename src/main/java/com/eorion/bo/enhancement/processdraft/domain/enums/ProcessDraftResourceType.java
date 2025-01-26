package com.eorion.bo.enhancement.processdraft.domain.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum ProcessDraftResourceType {

    PROCESS("1", "process"),
    TASK("2", "task"),
    OTHER("3", "other");

    private static final Map<String, ProcessDraftResourceType> BY_VALUE = new HashMap<>();

    static {
        for (ProcessDraftResourceType e : values()) {
            BY_VALUE.put(e.value, e);
        }
    }

    @EnumValue
    private final String value;
    private final String desc;

    ProcessDraftResourceType(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @JsonCreator
    public static ProcessDraftResourceType from(String s) {
        return BY_VALUE.get(s);
    }
}
