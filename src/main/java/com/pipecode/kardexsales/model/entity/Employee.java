package com.pipecode.kardexsales.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name = "employees")
@NoArgsConstructor(force = true)
public class Employee implements Serializable {

    private static final long serialVersionUID = 2791992824773374299L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull(message = "el documento del empleado es un dato obligatorio")
    private String identification;

    @NotNull(message = "nombre del empleado es un dato obligatorio")
    private String name;

    @NotNull(message = "apellido del empleado es un dato obligatorio")
    private String lastName;

    @NotNull(message = "direccion es un dato obligatorio")
    private String address;

}
