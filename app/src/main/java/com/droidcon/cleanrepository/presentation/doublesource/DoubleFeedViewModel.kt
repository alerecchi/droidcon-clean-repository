package com.droidcon.cleanrepository.presentation.doublesource

import com.droidcon.cleanrepository.domain.repository.FeedRepository
import com.droidcon.cleanrepository.presentation.base.BaseFeedViewModel
import com.hm.goe.base.di.qualifier.Double
import javax.inject.Inject

class DoubleFeedViewModel @Inject constructor(@Double doubleRepository: FeedRepository) :
    BaseFeedViewModel(doubleRepository)