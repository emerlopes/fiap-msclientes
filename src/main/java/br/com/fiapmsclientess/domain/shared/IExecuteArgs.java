package br.com.fiapmsclientess.domain.shared;

public interface IExecuteArgs<T, J> {
    T execute(J domainObject);
}
