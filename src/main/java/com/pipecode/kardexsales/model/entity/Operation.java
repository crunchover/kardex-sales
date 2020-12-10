package com.pipecode.kardexsales.model.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Entity
@Getter
@Setter
@Table(name = "operations")
@NoArgsConstructor(force = true)
public class Operation implements Serializable {

    private static final long serialVersionUID = 7617387803678584749L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private OperationType type;

    @ManyToOne(optional = true,cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id", nullable = true)
    private Employee employee;

    @ManyToOne(optional = true,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

}
