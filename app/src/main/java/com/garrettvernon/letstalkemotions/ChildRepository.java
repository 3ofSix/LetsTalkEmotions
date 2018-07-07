package com.garrettvernon.letstalkemotions;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

/**
 * Class to instantiate a database and provide Asynchronous
 * calls to the database for ChildDao
 */
public class ChildRepository {

    private ChildDao mChildDao;
    private LiveData<List<Child>> allChildren;

    public ChildRepository(Application application) {
        AppDatabase mDb = AppDatabase.getDatabase(application);
        mChildDao = mDb.childDao();
        allChildren = mChildDao.getAllChildren();
    }

    public LiveData<List<Child>> getAllChildren() {
        return allChildren;
    }

    /**
     * Inserts a Child into the database.
     * This implements an AsyncTask for performance
     * @param child
     */
    public void insert(Child child) {
        new insertAsyncTask(mChildDao).execute(child);
    }

    private class insertAsyncTask extends AsyncTask<Child, Void, Void> {
        private ChildDao mAsyncTaskDao;

        public insertAsyncTask(ChildDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Child... children) {
            mAsyncTaskDao.insert(children[0]);
            return null;
        }
    }
}
