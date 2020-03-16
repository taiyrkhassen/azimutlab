package com.example.azimutlab.mvvm.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.azimutlab.AzimutApp
import com.example.azimutlab.R
import com.example.azimutlab.adapters.DataListAdapter
import com.example.azimutlab.custom_errors.NoInternetException
import com.example.azimutlab.dagger.components.DaggerServiceComponent
import com.example.azimutlab.helpers.toast
import com.example.azimutlab.hide
import com.example.azimutlab.mvvm.models.PostModel
import com.example.azimutlab.mvvm.viewmodels.MainViewModel
import com.example.azimutlab.show
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.find
import org.jetbrains.anko.toast
import javax.inject.Inject

class MainActivity : AppCompatActivity(), LifecycleOwner {


    // Ссылка на viewModel, изменение состояния через подписку LiveData а не через колбек
    // в ВьюМоделе нужно ссылку на репозиторий и все возвращаемое оборачивать в обсервабле
    // BindingData dependencу с вьюшкой хмл
    //provide viewModel factory

    private var adapter = DataListAdapter()
    lateinit var recyclerView:RecyclerView


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindViews()
        getList()
    }

    private fun bindViews(){
        recyclerView = findViewById(R.id.list_data)
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.listPostLiveData.observe(this, Observer {
            adapter.addDataList(it as ArrayList<PostModel>)
        })
        viewModel.error.observe(this, Observer {
            this.toast(it)
        })

        viewModel.isLoading.observe(this, Observer{
            if(it==false){
                progress_bar.hide()
            } else{
                progress_bar.show()
            }
        })
    }

    private fun getList(){
        viewModel.getData()

    }

}
