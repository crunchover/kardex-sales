package com.pipecode.kardexsales.model.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor(force = true)
public class Product implements Serializable {

    private static final long serialVersionUID = 7536572304956144541L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    @JsonProperty(access = Access.READ_ONLY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    @NotNull(message = "nombre de producto es un dato obligatorio")
    private String name;


    @NotNull(message = "marca de producto es un dato obligatorio")
    private String brand;

    @NotNull(message = "description de producto es un dato obligatorio")
    private String description;

    @NotNull(message = "precio del producto es un dato obligatorio")
    private BigDecimal price;

    @NotNull(message = "cantidad de la operacion es un dato obligatorio")
    private int qty;



    @NotNull(message = "fecha de creacion es un dato obligatorio")
    @Column(name = "createdDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "updatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    @PrePersist
    public void prePersist() {
        if (createdDate == null) {
            createdDate = new Date();
        }
    }

    @PreUpdate
    public void preUpdate() {
        updatedDate = new Date();
    }


}
