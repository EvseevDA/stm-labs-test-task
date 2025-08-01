package com.github.evseevda.stmlabstesttask.businesslogicservice.core.data.request;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Positive;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PageRequest {

    @Positive
    private int page = 1;
    @Positive
    private int count = 10;

    @JsonIgnore
    public int getOffset() {
        return (page - 1) * count;
    }

}
