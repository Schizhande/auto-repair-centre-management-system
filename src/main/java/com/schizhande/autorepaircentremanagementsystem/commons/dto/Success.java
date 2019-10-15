package com.schizhande.autorepaircentremanagementsystem.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Success {
    private Long Id;
    private String message;
}
