package com.droidcon.cleanrepository.presentation.paginatedsource

import com.droidcon.cleanrepository.data.repository.DoubleRepository
import com.droidcon.cleanrepository.presentation.base.BaseViewModel
import javax.inject.Inject

class PaginatedViewModel @Inject constructor(doubleRepository: DoubleRepository) : BaseViewModel(doubleRepository)