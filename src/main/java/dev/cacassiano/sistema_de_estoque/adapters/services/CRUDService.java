package dev.cacassiano.sistema_de_estoque.adapters.services;

public interface CRUDService<T> {
    public T create();
    public void delete();
    public T update();
}
