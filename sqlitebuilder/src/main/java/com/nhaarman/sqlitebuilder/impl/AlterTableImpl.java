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
 *   limitations under the License.
 */

package com.nhaarman.sqlitebuilder.impl;

import com.nhaarman.sqlitebuilder.AddColumn;
import com.nhaarman.sqlitebuilder.AlterTable;
import com.nhaarman.sqlitebuilder.Column;
import com.nhaarman.sqlitebuilder.FinishedColumn;
import com.nhaarman.sqlitebuilder.RawSqlBuilder;
import com.nhaarman.sqlitebuilder.RenameTo;
import com.nhaarman.sqlitebuilder.SqlPart;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

final class AlterTableImpl extends BaseSqlPart implements AlterTable {

  @Nullable
  private final String mDatabaseName;

  @NotNull
  private final String mTableName;

  @NotNull
  private final SqlPart mPrevious;

  AlterTableImpl(@Nullable final String databaseName, @NotNull final String tableName, @NotNull final SqlPart previous) {
    mDatabaseName = databaseName;
    mTableName = tableName;
    mPrevious = previous;
  }

  @NotNull
  @Override
  public RenameTo renameTo(@NotNull final String newTableName) {
    return new RenameToImpl(newTableName, this);
  }

  @NotNull
  @Override
  public AddColumn add(@NotNull final FinishedColumn column) {
    return addColumn(column);
  }

  @NotNull
  @Override
  public AddColumn addColumn(@NotNull final FinishedColumn column) {
    return new AddColumnImpl(column, this);
  }

  @Override
  public void prependTo(@NotNull final RawSqlBuilder builder) {
    builder.prepend(mTableName);

    if (mDatabaseName != null) {
      builder.prepend('.').prepend(mDatabaseName);
    }

    builder.prepend("TABLE ");
  }

  @Nullable
  @Override
  public SqlPart previous() {
    return mPrevious;
  }
}
