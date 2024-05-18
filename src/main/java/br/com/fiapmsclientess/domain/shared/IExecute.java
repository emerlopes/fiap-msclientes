package br.com.fiapmsclientess.domain.shared;

public interface IExecute<T> {
    void execute(T domainObject);
}
