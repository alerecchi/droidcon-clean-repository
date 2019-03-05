package com.droidcon.cleanrepository.presentation.singlesource

import com.droidcon.cleanrepository.data.repository.SingleRepository
import com.droidcon.cleanrepository.presentation.base.BaseViewModel
import javax.inject.Inject

class SingleViewModel @Inject constructor(singleRepository: SingleRepository) : BaseViewModel(singleRepository)