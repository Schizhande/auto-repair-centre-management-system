package com.schizhande.autorepaircentremanagementsystem.commons.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorBody {
    private String message;
    private String debugMessage;
}
