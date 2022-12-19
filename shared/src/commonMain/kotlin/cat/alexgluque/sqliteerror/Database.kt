package cat.alexgluque.sqliteerror

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

internal class Database(driver: DatabaseDriverFactory) {

    private val database = AppDatabase(driver.createDriver())
    private val dbDispatcher = Dispatchers.Default
    private val queries = database.appDatabaseQueries

    val people: Flow<List<Persons>> = queries
        .getAll()
        .asFlow()
        .mapToList(dbDispatcher)

    suspend fun getAll(): List<Persons> =
        withContext(dbDispatcher) {
            return@withContext queries
                .getAll()
                .executeAsList()
        }

    suspend fun insert(age: Long, name: String) {
        withContext(dbDispatcher) {
            queries.insert(age, name)
        }
    }

    suspend fun updateAge(person: Persons) {
        withContext(dbDispatcher) {
            queries.updateAge(person)
        }
    }
}