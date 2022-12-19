//
//  AppViewState.swift
//  iosApp
//
//  Created by Àlex G. Luque on 18/12/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

class AppViewState: ObservableObject {
    
    @Published var viewModel: AppViewModel
    @Published var state: Persons
    
    init() {
        self.viewModel = AppViewModel(driver: DatabaseDriverFactory())
        self.state = Persons(id: 0, age: 0, name: "")
        
        self.viewModel.observeState { state in
            self.state = state
        }
    }
    
}
