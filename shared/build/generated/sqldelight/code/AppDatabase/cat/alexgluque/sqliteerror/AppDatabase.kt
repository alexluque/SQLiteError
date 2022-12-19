package cat.alexgluque.sqliteerror

import app.cash.sqldelight.Transacter
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import cat.alexgluque.sqliteerror.shared.newInstance
import cat.alexgluque.sqliteerror.shared.schema

public interface AppDatabase : Transacter {
  public val appDatabaseQueries: AppDatabaseQueries

  public companion object {
    public val Schema: SqlSchema
      get() = AppDatabase::class.schema

    public operator fun invoke(driver: SqlDriver): AppDatabase =
        AppDatabase::class.newInstance(driver)
  }
}
