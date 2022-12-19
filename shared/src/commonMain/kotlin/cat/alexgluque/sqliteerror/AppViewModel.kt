package cat.alexgluque.sqliteerror

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class AppViewModel(driver: DatabaseDriverFactory) {

    private val dispatcher = Dispatchers.Main
    private val baseViewModelScope = CoroutineScope(dispatcher)
    private val database: Database = Database(driver)

    private val _state = MutableSharedFlow<Persons>(replay = 1)
    val state = _state.asSharedFlow()

    init {
        fillDatabase()
        observeState()
    }

    private fun fillDatabase() {
        baseViewModelScope.launch {
            if (database.getAll().isEmpty()) {
                database.insert(35, "John")
            }
        }
    }

    private fun observeState() {
        baseViewModelScope.launch {
            database.people.collect {
                println(">>>>>> received values: $it")
                if (it.isNotEmpty()) {
                    _state.emit(it.first())
                }
            }
        }
    }

    fun updateAge(person: Persons) {
        baseViewModelScope.launch {
            database.updateAge(person)
        }
    }

    fun observeState(onChange: (Persons) -> Unit) {
        state.onEach {
            onChange(it)
        }.launchIn(baseViewModelScope)
    }
}