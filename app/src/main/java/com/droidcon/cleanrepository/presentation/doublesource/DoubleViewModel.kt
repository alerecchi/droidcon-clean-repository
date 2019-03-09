package com.droidcon.cleanrepository.presentation.doublesource

import com.droidcon.cleanrepository.domain.repository.FeedRepository
import com.droidcon.cleanrepository.presentation.base.BaseViewModel
import com.hm.goe.base.di.qualifier.Double
import javax.inject.Inject

class DoubleViewModel @Inject constructor(@Double doubleRepository: FeedRepository) : BaseViewModel(doubleRepository)