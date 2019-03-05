package com.droidcon.cleanrepository.presentation.doublesource

import com.droidcon.cleanrepository.data.repository.DoubleRepository
import com.droidcon.cleanrepository.presentation.base.BaseViewModel
import javax.inject.Inject

class DoubleViewModel @Inject constructor(doubleRepository: DoubleRepository) : BaseViewModel(doubleRepository)