package com.example.azimutlab.mvp.view.activities

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.azimutlab.AzimutApp
import com.example.azimutlab.R
import com.example.azimutlab.adapters.DataListAdapter
import com.example.azimutlab.dagger.components.DaggerServiceComponent
import com.example.azimutlab.hide
import com.example.azimutlab.mvp.models.PostModel
import com.example.azimutlab.mvp.presenters.MainPresenter
import com.example.azimutlab.mvp.repository.MainRepositoryImpl
import com.example.azimutlab.mvp.view.interfaces.MainActivityView
import com.example.azimutlab.show
import kotlinx.android.synthetic.main.activity_main.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import org.jetbrains.anko.toast
import javax.inject.Inject

class MainActivity : BaseActivity(), MainActivityView {

    init {
        DaggerServiceComponent.builder()
            .appComponent(AzimutApp.appComponent)
            .build()
            .inject(this)
    }

    @InjectPresenter
    lateinit var presenter: MainPresenter

    @Inject
    lateinit var mainRepos:MainRepositoryImpl

    @ProvidePresenter
    fun providePresenter(): MainPresenter{
        return MainPresenter(mainRepos)
    }

    private var adapter = DataListAdapter()
    override fun failedGetData(msg: String) {
        toast(msg)
    }

    override fun successGetData(list: List<PostModel>) {
        toast("Урааа!")
        list_data.show()
        list_data.layoutManager = LinearLayoutManager(this)
        adapter.addDataList(list as ArrayList<PostModel>)
        list_data.adapter = adapter
    }

    override fun loadingData(loading: Boolean) {
        if (loading) {
            progress_bar.show()
        } else {
            progress_bar.hide()
        }
    }

    override fun noInternetConnection() {
        toast("No internet!!")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        launch.setOnClickListener {
            launch.hide()
            presenter.getPosts()
//            list_data.show()
//            list_data.layoutManager = LinearLayoutManager(this)
//            adapter.addDataList(testData())
//            list_data.adapter = adapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dispose()
    }

    override fun onPause() {
        super.onPause()
        launch.show()
        list_data.hide()
        progress_bar.hide()
    }

    private fun testData(): ArrayList<PostModel> {
        return arrayListOf(
            PostModel(12, 2352, "Taiyr", "afjasjdfnsljnfssd"),
            PostModel(13, 1323, "Taiyr", "afjasjdfnsljnfssd"),
            PostModel(14, 2342, "Taiyr", "afjasjdfnsljnfssd"),
            PostModel(16, 8478, "Taiyr", "afjasjdfnsljnfssd")
        )
    }



}
