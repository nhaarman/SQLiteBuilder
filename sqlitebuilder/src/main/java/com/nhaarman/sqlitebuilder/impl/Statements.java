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

import com.nhaarman.sqlitebuilder.Alter;
import com.nhaarman.sqlitebuilder.Analyze;
import com.nhaarman.sqlitebuilder.Begin;
import com.nhaarman.sqlitebuilder.Column;
import com.nhaarman.sqlitebuilder.Commit;
import com.nhaarman.sqlitebuilder.Create;
import com.nhaarman.sqlitebuilder.Delete;
import com.nhaarman.sqlitebuilder.Drop;
import com.nhaarman.sqlitebuilder.End;
import com.nhaarman.sqlitebuilder.Insert;
import com.nhaarman.sqlitebuilder.Replace;
import com.nhaarman.sqlitebuilder.Rollback;
import com.nhaarman.sqlitebuilder.Select;
import com.nhaarman.sqlitebuilder.StatementConverter;
import com.nhaarman.sqlitebuilder.Update;
import com.nhaarman.sqlitebuilder.Vacuum;
import org.jetbrains.annotations.NotNull;

public class Statements {

  static {
    //noinspection InstantiationOfUtilityClass,ResultOfObjectAllocationIgnored
    new Statements();
  }

  private Statements() {
  }

  @NotNull
  public static Alter alter() {
    return new AlterImpl();
  }

  @NotNull
  public static Analyze analyze() {
    return new AnalyzeImpl();
  }

  @NotNull
  public static Column column(@NotNull final String newColumnName) {
    return new ColumnImpl(newColumnName);
  }

  @NotNull
  public static Begin begin() {
    return new BeginImpl();
  }

  @NotNull
  public static End end() {
    return new EndImpl();
  }

  @NotNull
  public static Commit commit() {
    return new CommitImpl();
  }

  @NotNull
  public static Drop drop() {
    return new DropImpl();
  }

  @NotNull
  public static Rollback rollback() {
    return new RollbackImpl();
  }

  @NotNull
  public static Create create() {
    return new CreateImpl();
  }

  @NotNull
  public static Delete delete() {
    return new DeleteImpl();
  }

  @NotNull
  public static Replace replace() {
    return new ReplaceImpl();
  }

  @NotNull
  public static Select select(@NotNull final String... columns) {
    return new SelectImpl(columns);
  }

  @NotNull
  public static Insert insert() {
    return new InsertImpl();
  }

  @NotNull
  public static Insert insertOrAbort() {
    return new InsertOrAbortImpl();
  }

  @NotNull
  public static Insert insertOrFail() {
    return new InsertOrFailImpl();
  }

  @NotNull
  public static Insert insertOrIgnore() {
    return new InsertOrIgnoreImpl();
  }

  @NotNull
  public static Insert insertOrReplace() {
    return new InsertOrReplaceImpl();
  }

  @NotNull
  public static Insert insertOrRollback() {
    return new InsertOrRollbackImpl();
  }

  @NotNull
  public static Update update(@NotNull final String tableName) {
    return new UpdateImpl(tableName);
  }

  @NotNull
  public static Vacuum vacuum() {
    return new VacuumImpl();
  }

  @NotNull
  public static StatementConverter converter() {
    return StatementConverterImpl.getInstance();
  }
}
