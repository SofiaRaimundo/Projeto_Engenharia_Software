package pt.ufp.info.esof.projeto.dtos.conversores;

public interface Conversor<O,I> {
    O converter(I i);
}