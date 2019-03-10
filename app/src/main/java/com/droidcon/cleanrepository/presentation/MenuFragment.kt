package com.droidcon.cleanrepository.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.droidcon.cleanrepository.R
import com.droidcon.cleanrepository.data.persistence.dao.RoomFeedDao
import com.droidcon.cleanrepository.kx.bindToLifecycle
import com.droidcon.cleanrepository.presentation.doublesource.DoubleSourceFragment
import com.droidcon.cleanrepository.presentation.pagedsource.PagedSourceFragment
import com.droidcon.cleanrepository.presentation.pagedsource.PagedType
import com.droidcon.cleanrepository.presentation.singlesource.SingleSourceFragment
import dagger.android.support.DaggerFragment
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.menu_fragment.*
import javax.inject.Inject

class MenuFragment : DaggerFragment() {

    @Inject
    lateinit var feedDao: RoomFeedDao

    companion object {
        fun newInstance() = MenuFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Only for conference purposes
        Completable.fromCallable { feedDao.deleteAll() }
            .onErrorComplete()
            .subscribeOn(Schedulers.io())
            .subscribe()
            .bindToLifecycle(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.menu_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindButton(singleSourceButton, SingleSourceFragment.newInstance())
        bindButton(doubleSourceButton, DoubleSourceFragment.newInstance())
        bindButton(pagedLocalSourceButton, PagedSourceFragment.newInstance(PagedType.LOCAL))
        bindButton(pagedRemoteSourceButton, PagedSourceFragment.newInstance(PagedType.REMOTE))
    }

    private fun bindButton(button: Button, fragment: Fragment) {
        button.setOnClickListener {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.addToBackStack(null)
                ?.replace(
                    R.id.container,
                    fragment
                )
                ?.commit()
        }
    }
}