/*
 *  Copyright 2015 Niek Haarman
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.nhaarman.sqlitebuilder.android;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.nhaarman.sqlitebuilder.FinishedQuery;
import com.nhaarman.sqlitebuilder.FinishedStatement;
import com.nhaarman.sqlitebuilder.StatementConverter;
import com.nhaarman.sqlitebuilder.StatementExecutor;
import com.nhaarman.sqlitebuilder.impl.Statements;
import org.jetbrains.annotations.NotNull;

public class AndroidStatementExecutor implements StatementExecutor<Cursor> {

  @NotNull
  private final SQLiteDatabase mDatabase;

  @NotNull
  private final StatementConverter mStatementConverter;

  public AndroidStatementExecutor(@NotNull final SQLiteDatabase database) {
    mDatabase = database;
    mStatementConverter = Statements.converter();
  }

  private static String[] toStringArray(@NotNull final Object[] objects) {
    String[] results = new String[objects.length];
    for (int i = 0; i < results.length; i++) {
      results[i] = objects[i].toString();
    }
    return results;
  }

  @Override
  public Cursor executeQuery(@NotNull final FinishedQuery finishedQuery) {
    String sql = mStatementConverter.toSql(finishedQuery);
    Object[] arguments = mStatementConverter.retrieveArguments(finishedQuery);
    return mDatabase.rawQuery(sql, toStringArray(arguments));
  }

  @Override
  public void execute(@NotNull final FinishedStatement finishedStatement) {
    String sql = mStatementConverter.toSql(finishedStatement);
    Object[] arguments = mStatementConverter.retrieveArguments(finishedStatement);
    mDatabase.execSQL(sql, arguments);
  }
}
