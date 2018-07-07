/**
 * The Database class that handles the room database
 * <p>
 * This is a Singleton
 */
package com.garrettvernon.letstalkemotions;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

/**
 * Include in the @Database annotation any tables that are to be
 * implemented. Eg User.class, Book.class etc
 */
@Database(entities = {Child.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    //Implement as a Singleton
    private static AppDatabase INSTANCE;

    /**
     * Reference to the Data Access Objects
     * corresponding to the included tables (entities)
     */
    public abstract ChildDao childDao();

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            /**
             * Database Creation
             */
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "lte_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };


    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final ChildDao mDao;

        public PopulateDbAsync(AppDatabase database) {
            mDao = database.childDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mDao.deleteAll();
            Child sean = new Child();
            sean.setFirstName("Sean");
            sean.setLastName("Vernon");
            mDao.insert(sean);

            Child garrett = new Child();
            garrett.setFirstName("Garrett");
            garrett.setLastName("Vernon");
            mDao.insert(garrett);

            return null;
        }
    }
}
