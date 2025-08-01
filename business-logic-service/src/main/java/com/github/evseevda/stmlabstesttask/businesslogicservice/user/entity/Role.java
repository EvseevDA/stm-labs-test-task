package com.github.evseevda.stmlabstesttask.businesslogicservice.user.entity;

import com.github.evseevda.stmlabstesttask.businesslogicservice.core.entity.Entity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Role implements Entity<Integer> {

    private Integer id;
    private String name;

}
