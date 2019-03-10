package com.droidcon.cleanrepository.presentation.pagedsource

import com.droidcon.cleanrepository.di.qualifier.Remote
import com.droidcon.cleanrepository.domain.repository.PagedRepository
import javax.inject.Inject

class PagedRemoteViewModel @Inject constructor(@Remote pagedRepository: PagedRepository) :
    PagedViewModel(pagedRepository)