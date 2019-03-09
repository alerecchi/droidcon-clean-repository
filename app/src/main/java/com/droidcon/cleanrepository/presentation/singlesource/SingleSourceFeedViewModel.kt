package com.droidcon.cleanrepository.presentation.singlesource

import com.droidcon.cleanrepository.data.repository.SingleSourceFeedRepository
import com.droidcon.cleanrepository.presentation.base.BaseFeedViewModel
import javax.inject.Inject

class SingleSourceFeedViewModel @Inject constructor(singleRepository: SingleSourceFeedRepository) :
    BaseFeedViewModel(singleRepository)