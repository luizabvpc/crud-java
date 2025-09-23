package com.luiza.cadastro_usuario.infrastructure.entitys;

import jakarta.persistence.*;
import lombok.*;
// @ como se fossem as migrations do laravel
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "usuario")
@Entity

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //auto incremento
    private Integer id;

    @Column(name = "email", unique = true) //notacoes de geracao automatica no banco de dados, n é java
    private String email;

    @Column(name = "nome")
    private String nome;
}
