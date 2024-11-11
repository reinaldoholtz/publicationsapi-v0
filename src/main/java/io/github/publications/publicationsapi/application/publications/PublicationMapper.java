package io.github.publications.publicationsapi.application.publications;

import io.github.publications.publicationsapi.domain.entity.Publication;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.time.LocalDateTime;


@Component
public class PublicationMapper {

    public Publication mapToPublication(String orgao, String unidade, String localidade, String tipo, String numeroProcesso,
                                        String desricao, LocalDateTime dataPublicacao) throws IOException {
        return Publication.builder()
                .orgao(orgao)
                .unidade(unidade)
                .localidade(localidade)
                .tipo(tipo)
                .numeroProcesso(numeroProcesso)
                .descricao(desricao)
                .dataPublicacao(dataPublicacao)
                .build();
    }

    public PublicationDTO publicationToDTO(Publication publication) {
        return PublicationDTO.builder()
                .id(publication.getId())
                .orgao(publication.getOrgao())
                .unidade(publication.getUnidade())
                .localidade(publication.getLocalidade())
                .tipo(publication.getTipo())
                .numeroProcesso(publication.getNumeroProcesso())
                .descricao(publication.getDescricao())
                .dataPublicacao(publication.getDataPublicacao())
                .documento(publication.getDocumento())
                .build();
    }
}
