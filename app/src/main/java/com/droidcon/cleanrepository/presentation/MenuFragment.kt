package com.droidcon.cleanrepository.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.droidcon.cleanrepository.R
import com.droidcon.cleanrepository.data.persistence.dao.RoomFeedDao
import com.droidcon.cleanrepository.presentation.doublesource.DoubleSourceFragment
import com.droidcon.cleanrepository.presentation.pagedsource.PagedSourceFragment
import com.droidcon.cleanrepository.presentation.singlesource.SingleSourceFragment
import dagger.android.support.DaggerFragment
import io.reactivex.Single
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
        Single.fromCallable {
            feedDao.deleteAll()
        }
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.menu_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        singleSourceButton.setOnClickListener {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.addToBackStack(null)
                ?.replace(
                    R.id.container,
                    SingleSourceFragment.newInstance()
                )
                ?.commit()
        }

        doubleSourceButton.setOnClickListener {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.addToBackStack(null)
                ?.replace(
                    R.id.container,
                    DoubleSourceFragment.newInstance()
                )
                ?.commit()
        }

        pagedSourceButton.setOnClickListener {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.addToBackStack(null)
                ?.replace(
                    R.id.container,
                    PagedSourceFragment.newInstance()
                )
                ?.commit()
        }
    }
}