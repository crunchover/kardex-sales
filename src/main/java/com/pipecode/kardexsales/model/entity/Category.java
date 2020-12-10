package com.pipecode.kardexsales.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Getter
@Builder
@AllArgsConstructor
@Table(name = "categories")
@NoArgsConstructor(force = true)
public class Category implements Serializable {

    private static final long serialVersionUID = 2791992824773374299L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull(message = "nombre de la categoria es un dato obligatorio")
    private String name;


}
