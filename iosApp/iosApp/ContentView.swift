import SwiftUI
import shared

struct ContentView: View {
    @StateObject private var viewState = AppViewState()

	var body: some View {
        VStack {
            HStack {
                Text("\(viewState.state.name)'s age: ")
                
                Button("+") {
                    viewState.viewModel.updateAge(
                        person: Persons(
                            id: viewState.state.id,
                            age: viewState.state.age + 1,
                            name: viewState.state.name
                        )
                    )
                }
                
                Text("\(viewState.state.age)")
                
                Button("-") {
                    viewState.viewModel.updateAge(
                        person: Persons(
                            id: viewState.state.id,
                            age: viewState.state.age - 1,
                            name: viewState.state.name
                        )
                    )
                }
            }
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
