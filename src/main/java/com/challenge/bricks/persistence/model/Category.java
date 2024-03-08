package com.challenge.bricks.persistence.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "CATEGORY")
public class Category implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NM_CATEGORY_ID", length = 25, nullable = false)
    private Long id;

    @Column(name = "VA_CODE", length = 250, nullable = false)
    private String code;

    @Column(name = "VA_NAME", length = 250, nullable = false)
    private String name;

    @Column(name = "VA_DESCRIPTION", length = 250, nullable = true)
    private String description;

    @Column(name = "VA_ICON", length = 4000, nullable = false)
    private String icon;

}
