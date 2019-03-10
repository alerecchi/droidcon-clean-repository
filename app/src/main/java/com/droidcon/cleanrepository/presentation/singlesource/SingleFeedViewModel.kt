package com.droidcon.cleanrepository.presentation.singlesource

import com.droidcon.cleanrepository.di.qualifier.Single
import com.droidcon.cleanrepository.domain.repository.FeedRepository
import com.droidcon.cleanrepository.presentation.base.BaseFeedViewModel
import javax.inject.Inject

class SingleFeedViewModel @Inject constructor(@Single singleRepository: FeedRepository) :
    BaseFeedViewModel(singleRepository)