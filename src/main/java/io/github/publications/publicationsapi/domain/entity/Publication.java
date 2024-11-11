package io.github.publications.publicationsapi.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_publication")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    private String orgao;
    @Column
    private String unidade;
    @Column
    private String localidade;
    @Column
    private String tipo;
    @Column
    private String numeroProcesso;
    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;
    @Column(name = "documento", columnDefinition = "TSVECTOR")
    private String documento;
    @Column
    private LocalDateTime dataPublicacao;
}
