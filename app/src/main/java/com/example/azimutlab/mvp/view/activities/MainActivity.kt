package com.example.azimutlab.mvp.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.azimutlab.R
import com.example.azimutlab.adapters.DataListAdapter
import com.example.azimutlab.mvp.models.PostModel
import com.example.azimutlab.mvp.presenters.MainPresenter
import com.example.azimutlab.mvp.view.interfaces.MainActivityView
import kotlinx.android.synthetic.main.activity_main.*
import moxy.presenter.InjectPresenter
import org.jetbrains.anko.toast

class MainActivity : BaseActivity(), MainActivityView {
    @InjectPresenter
    lateinit var presenter : MainPresenter

    private var adapter = DataListAdapter()
    override fun failedGetData(msg:String) {
        toast(msg)
    }

    override fun successGetData(list:List<PostModel>) {
        list_data.visibility = View.VISIBLE
        list_data.layoutManager = LinearLayoutManager(this)
        adapter.addDataList(list as ArrayList<PostModel>)
        list_data.adapter = adapter
    }

    override fun loadingData(loading:Boolean) {
        if(loading){
            progress_bar.visibility = View.VISIBLE
        } else{
            progress_bar.visibility = View.GONE
        }
    }

    override fun noInternetConnection() {
        toast("No internet!!")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        launch.setOnClickListener {
            launch.visibility = View.GONE
            presenter.getPosts()
        }
    }

    override fun onPause() {
        super.onPause()
        launch.visibility = View.VISIBLE
        list_data.visibility = View.GONE
    }


}
