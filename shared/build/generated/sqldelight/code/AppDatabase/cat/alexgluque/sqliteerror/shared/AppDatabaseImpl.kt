package cat.alexgluque.sqliteerror.shared

import app.cash.sqldelight.TransacterImpl
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import cat.alexgluque.sqliteerror.AppDatabase
import cat.alexgluque.sqliteerror.AppDatabaseQueries
import kotlin.Int
import kotlin.Unit
import kotlin.reflect.KClass

internal val KClass<AppDatabase>.schema: SqlSchema
  get() = AppDatabaseImpl.Schema

internal fun KClass<AppDatabase>.newInstance(driver: SqlDriver): AppDatabase =
    AppDatabaseImpl(driver)

private class AppDatabaseImpl(
  driver: SqlDriver,
) : TransacterImpl(driver), AppDatabase {
  public override val appDatabaseQueries: AppDatabaseQueries = AppDatabaseQueries(driver)

  public object Schema : SqlSchema {
    public override val version: Int
      get() = 1

    public override fun create(driver: SqlDriver): QueryResult<Unit> {
      driver.execute(null, """
          |CREATE TABLE Persons (
          |    id INTEGER NOT NULL PRIMARY KEY,
          |    age INTEGER NOT NULL,
          |    name TEXT NOT NULL
          |)
          """.trimMargin(), 0)
      return QueryResult.Unit
    }

    public override fun migrate(
      driver: SqlDriver,
      oldVersion: Int,
      newVersion: Int,
    ): QueryResult<Unit> = QueryResult.Unit
  }
}
