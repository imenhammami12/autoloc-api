package tn.esprit.autoloc.service;

public interface IGenericService<T, ID> extends
        IReadService<T, ID>,
        IWriteService<T, ID>,
        ICheckService<T> {
}