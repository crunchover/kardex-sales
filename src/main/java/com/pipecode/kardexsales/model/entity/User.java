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
@Table(name = "users")
@NoArgsConstructor(force = true)
public class User implements Serializable {

    private static final long serialVersionUID = 279199283324299L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull(message = "Nombre de usuario es un dato obligatorio")
    private String userName;

    @NotNull(message = "el flag is active es un dato obligatorio")
    private Boolean active;


}
