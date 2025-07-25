package br.com.portfolio.blogapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostFormDTO(
        @NotBlank(message = "Título é obrigatório")
        @Size(min = 5, message = "Título deve ter no mínimo 5 caracteres")
        String title,

        @NotBlank(message = "Conteúdo é obrigatório")
        String content
) {
}