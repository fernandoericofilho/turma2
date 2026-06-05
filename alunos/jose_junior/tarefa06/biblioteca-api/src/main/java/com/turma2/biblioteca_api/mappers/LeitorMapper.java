package com.turma2.biblioteca_api.mappers;

import com.turma2.biblioteca_api.controllers.request.LeitorRequest;
import com.turma2.biblioteca_api.controllers.response.LeitorResponse;
import com.turma2.biblioteca_api.models.Leitor;
import org.springframework.stereotype.Component;

@Component
public class LeitorMapper {

    public LeitorResponse entityToResponse(Leitor leitor) {
        return new LeitorResponse(
                leitor.getId(),
                leitor.getNome(),
                leitor.getEmail());
    }

    public Leitor requestToEntity(LeitorRequest leitorRequest) {
        Leitor leitor = new Leitor();
        leitor.setNome(leitorRequest.nome());
        leitor.setEmail(leitorRequest.email());
        return leitor;
    }
}