package pt.ufp.info.esof.projeto.DesignPatterns;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public class TarefaEfetiva {

        private String nome;
        private float progresso;
        private float tempo_trabalhado;

}