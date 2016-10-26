/**
 * Android Simple ORM project.
 *
 * Copyright 2016 Arno Zhang <zyfgood12@163.com>
 *
 * Date 2015/08/19
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jungle.simpleorm.supporter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ORMDatabaseOpenHelper extends SQLiteOpenHelper {

    private ORMSupporter mOrmSupporter;
    private ORMDatabaseListener mDatabaseListener;


    public ORMDatabaseOpenHelper(
            Context context,
            String name, int version,
            SQLiteORMSupporter supporter,
            ORMDatabaseListener listener) {

        super(context, name, null, version);

        mOrmSupporter = supporter;
        mDatabaseListener = listener;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if (mDatabaseListener != null) {
            mDatabaseListener.onCreated(mOrmSupporter, db);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (mDatabaseListener != null) {
            mDatabaseListener.onUpgrade(mOrmSupporter, db, oldVersion, newVersion);
        }
    }
}