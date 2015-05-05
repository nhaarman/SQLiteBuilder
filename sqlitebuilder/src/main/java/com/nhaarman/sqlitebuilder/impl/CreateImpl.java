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

import com.nhaarman.sqlitebuilder.Create;
import com.nhaarman.sqlitebuilder.CreateTable;
import com.nhaarman.sqlitebuilder.CreateTableIfNotExists;
import com.nhaarman.sqlitebuilder.RawSqlBuilder;
import com.nhaarman.sqlitebuilder.SqlPart;
import com.nhaarman.sqlitebuilder.Temporary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class CreateImpl extends BaseSqlPart implements Create {

  @Override
  public void prependTo(@NotNull final RawSqlBuilder builder) {
    builder.prepend("CREATE");
  }

  @Nullable
  @Override
  public SqlPart previous() {
    return null;
  }

  @NotNull
  @Override
  public Temporary temp() {
    return new TemporaryImpl(this);
  }

  @NotNull
  @Override
  public Temporary temporary() {
    return new TemporaryImpl(this);
  }

  @NotNull
  @Override
  public CreateTable table(@NotNull final String tableName) {
    return table(null, tableName);
  }

  @NotNull
  @Override
  public CreateTable table(@Nullable final String databaseName, @NotNull final String tableName) {
    return new CreateTableImpl(databaseName, tableName, this);
  }

  @NotNull
  @Override
  public CreateTableIfNotExists tableIfNotExists(@NotNull final String tableName) {
    return tableIfNotExists(null, tableName);
  }

  @NotNull
  @Override
  public CreateTableIfNotExists tableIfNotExists(@Nullable final String databaseName, @NotNull final String tableName) {
    return new CreateTableIfNotExistsImpl(databaseName, tableName, this);
  }
}
