package cat.alexgluque.sqliteerror

import app.cash.sqldelight.Query
import app.cash.sqldelight.TransacterImpl
import app.cash.sqldelight.db.SqlDriver
import kotlin.Any
import kotlin.Long
import kotlin.String
import kotlin.Unit

public class AppDatabaseQueries(
  driver: SqlDriver,
) : TransacterImpl(driver) {
  public fun <T : Any> getAll(mapper: (
    id: Long,
    age: Long,
    name: String,
  ) -> T): Query<T> = Query(-55831600, arrayOf("Persons"), driver, "AppDatabase.sq", "getAll",
      "SELECT * FROM Persons") { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getLong(1)!!,
      cursor.getString(2)!!
    )
  }

  public fun getAll(): Query<Persons> = getAll { id, age, name ->
    Persons(
      id,
      age,
      name
    )
  }

  public fun insert(age: Long, name: String): Unit {
    driver.execute(9743390, """INSERT INTO Persons (age, name) VALUES (?, ?)""", 2) {
          bindLong(0, age)
          bindString(1, name)
        }
    notifyQueries(9743390) { emit ->
      emit("Persons")
    }
  }

  public fun updateAge(Persons: Persons): Unit {
    driver.execute(937854961, """REPLACE INTO Persons VALUES (?, ?, ?)""", 3) {
          bindLong(0, Persons.id)
          bindLong(1, Persons.age)
          bindString(2, Persons.name)
        }
    notifyQueries(937854961) { emit ->
      emit("Persons")
    }
  }
}
