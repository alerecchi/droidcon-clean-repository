package com.droidcon.cleanrepository.presentation.doublesource

import com.droidcon.cleanrepository.data.repository.DoubleSourceFeedRepository
import com.droidcon.cleanrepository.presentation.base.BaseFeedViewModel
import javax.inject.Inject

class DoubleSourceFeedViewModel @Inject constructor(doubleRepository: DoubleSourceFeedRepository) :
    BaseFeedViewModel(doubleRepository)