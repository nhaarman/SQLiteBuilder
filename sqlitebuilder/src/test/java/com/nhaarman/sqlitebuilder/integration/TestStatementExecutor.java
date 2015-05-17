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

package com.nhaarman.sqlitebuilder.integration;

import com.nhaarman.sqlitebuilder.FinishedDeleteStatement;
import com.nhaarman.sqlitebuilder.FinishedInsertStatement;
import com.nhaarman.sqlitebuilder.FinishedSelectStatement;
import com.nhaarman.sqlitebuilder.FinishedStatement;
import com.nhaarman.sqlitebuilder.FinishedUpdateStatement;
import com.nhaarman.sqlitebuilder.StatementConverter;
import com.nhaarman.sqlitebuilder.StatementExecutor;
import com.nhaarman.sqlitebuilder.impl.Statements;
import org.jetbrains.annotations.NotNull;

class TestStatementExecutor implements StatementExecutor<QueryResult> {

  @NotNull
  private final DummyDatabase mDatabase;

  @NotNull
  private final StatementConverter mStatementConverter;

  TestStatementExecutor(@NotNull final DummyDatabase database) {
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
  public QueryResult execute(@NotNull final FinishedSelectStatement finishedSelectStatement) {
    String sql = mStatementConverter.toSql(finishedSelectStatement);
    Object[] arguments = mStatementConverter.retrieveArguments(finishedSelectStatement);
    return mDatabase.executeSelectStatement(sql, toStringArray(arguments));
  }

  @Override
  public long execute(@NotNull final FinishedInsertStatement finishedInsertStatement) {
    String sql = mStatementConverter.toSql(finishedInsertStatement);
    Object[] arguments = mStatementConverter.retrieveArguments(finishedInsertStatement);
    mDatabase.executeStatement(sql, arguments);
    return 0;
  }

  @Override
  public long execute(@NotNull final FinishedUpdateStatement finishedUpdateStatement) {
    String sql = mStatementConverter.toSql(finishedUpdateStatement);
    Object[] arguments = mStatementConverter.retrieveArguments(finishedUpdateStatement);
    mDatabase.executeStatement(sql, arguments);
    return 0;
  }

  @Override
  public long execute(@NotNull final FinishedDeleteStatement finishedDeleteStatement) {
    String sql = mStatementConverter.toSql(finishedDeleteStatement);
    Object[] arguments = mStatementConverter.retrieveArguments(finishedDeleteStatement);
    mDatabase.executeStatement(sql, arguments);
    return 0;
  }

  @Override
  public void execute(@NotNull final FinishedStatement finishedStatement) {
    String sql = mStatementConverter.toSql(finishedStatement);
    Object[] arguments = mStatementConverter.retrieveArguments(finishedStatement);
    mDatabase.executeStatement(sql, arguments);
  }
}
