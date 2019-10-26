package managers.common;

import models.common.CommonDaoInterface;

import javax.inject.Inject;

public abstract class CommonManager<T> {

    @Inject
    private CommonDaoInterface<T> commonDao;

    protected T enrich(T object) {
        return object;
    }

    protected T findById(int id) {
        T object = commonDao.getById(id);
        return enrich(object);
    }

    protected void create(T object) {
        commonDao.insert(object);
    }

    protected void delete(T object) {
    }

}
