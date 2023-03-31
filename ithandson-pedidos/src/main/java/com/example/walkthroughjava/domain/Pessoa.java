package com.example.walkthroughjava.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "O nome é obrigatório")
    private String nome;
    @NotNull(message = "O sobrenome é obrigatório")
    private String sobrenome;
    @NotNull(message = "A data de nascimento é obrigatória")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name="data_nascimento", nullable = false)
    private LocalDate dataNascimento;
    @NotNull(message = "O CPF é obrigatório")
    @Column(name="CPF", unique = true)
    private String cpf;

    @JsonIgnore
    public String getDataNascimentoFormatada() {
        return dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
