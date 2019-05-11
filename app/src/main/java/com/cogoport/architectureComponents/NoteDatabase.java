//It is the holder of our database means it stores things in it and saved in it.so it needs all the tables which are in the database and version
//of database so that we can keep updated with what is changed in database.this class must be abstract.
package com.cogoport.architectureComponents;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

//below line will give error in kotlin for that u need to import another kapt dependency for kotlin with room support
@Database(entities = Note.class,version = 1)
public abstract class NoteDatabase extends RoomDatabase {
    //we should have only single instance of our database so we should make our class singleton
    //so we are creating a new database object here which will be singleton
    public abstract NoteDao noteDao();

    private static volatile NoteDatabase noteRoomInstance;

    static NoteDatabase getDatabase(final Context context) {
        if (noteRoomInstance == null) {
            synchronized (NoteDatabase.class) {
                if (noteRoomInstance == null) {
                    noteRoomInstance = Room.databaseBuilder(context.getApplicationContext(),
                            NoteDatabase.class, "note_database")
                            .build();
                }
            }
        }
        return noteRoomInstance;
    }
}
