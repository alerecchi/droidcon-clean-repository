package com.droidcon.cleanrepository.presentation.pagedsource

import com.droidcon.cleanrepository.di.qualifier.Local
import com.droidcon.cleanrepository.domain.repository.PagedRepository
import javax.inject.Inject

class PagedLocalViewModel @Inject constructor(@Local pagedRepository: PagedRepository) :
    PagedViewModel(pagedRepository)