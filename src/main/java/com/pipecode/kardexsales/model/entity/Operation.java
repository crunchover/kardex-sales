package com.pipecode.kardexsales.model.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;




@Entity
@Getter
@Setter
@Builder
@Table(name = "operations")
@NoArgsConstructor
@AllArgsConstructor
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

    @Builder.Default
    @OneToMany(mappedBy = "operation", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<Product> products = new HashSet<>();


}
