package com.droidcon.cleanrepository.presentation.singlesource

import com.droidcon.cleanrepository.domain.repository.FeedRepository
import com.droidcon.cleanrepository.presentation.base.BaseFeedViewModel
import com.hm.goe.base.di.qualifier.Single
import javax.inject.Inject

class SingleFeedViewModel @Inject constructor(@Single singleRepository: FeedRepository) :
    BaseFeedViewModel(singleRepository)