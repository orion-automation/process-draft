package com.eorion.bo.enhancement.processdraft.domain.dto.outbound;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdDTO<T> {
    private T id;
}