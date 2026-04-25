package com.backend.virality.features.bot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bots")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "bot name is required!")
    @Column(unique = true, nullable = false)
    private String name;
    @NotBlank(message = "Bot description is required!")
    @Column(nullable = false)
    private String description;
}