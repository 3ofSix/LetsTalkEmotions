/**
 * The View Model in the Model View View Model architecture
 * Accompanying class of Child and ChildDao
 */
package com.garrettvernon.letstalkemotions;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class ChildViewModel extends AndroidViewModel {
    //Reference to the AppDatabase
    //private AppDatabase mDb;

    //Reference database view a repository
    private ChildRepository mRepository;
    //Reference to the Child objects
    public final LiveData<List<Child>> children;
    
    public ChildViewModel(Application application){
        super(application);
        mRepository = new ChildRepository(application);
        children = mRepository.getAllChildren();
    }

    public LiveData<List<Child>> getChildren() {
        return children;
    }

    /**
     * Insert a child into the database
     */
    public void insert(Child child){
        mRepository.insert(child);
    }

}
