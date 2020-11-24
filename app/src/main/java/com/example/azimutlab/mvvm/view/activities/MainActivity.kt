package com.example.azimutlab.mvvm.view.activities

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.azimutlab.AzimutApp
import com.example.azimutlab.R
import com.example.azimutlab.adapters.DataListAdapter
import com.example.azimutlab.dagger.components.DaggerServiceComponent
import com.example.azimutlab.helpers.toast
import com.example.azimutlab.hide
import com.example.azimutlab.mvvm.models.PostModel
import com.example.azimutlab.mvvm.viewmodels.MainViewModel
import com.example.azimutlab.mvvm.viewmodels.ViewModelTestKoin
import com.example.azimutlab.show
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.compat.ScopeCompat.viewModel
import org.koin.android.viewmodel.ext.android.viewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity(), LifecycleOwner {


    // Ссылка на viewModel, изменение состояния через подписку LiveData а не через колбек
    // в ВьюМоделе нужно ссылку на репозиторий и все возвращаемое оборачивать в обсервабле
    // BindingData dependencу с вьюшкой хмл
    //provide viewModel factory

    private var adapter = DataListAdapter()
    lateinit var recyclerView:RecyclerView
//    init {
//        DaggerServiceComponent.builder()
//            .appComponent(AzimutApp.appComponent)
//            .build()
//            .inject(this)
//    }


    private val mainViewModel : ViewModelTestKoin by viewModel()

    private val sharedPrefs: SharedPreferences by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindViews()

        launch.setOnClickListener {
            launch.hide()
            getList()
        }
    }

    override fun onPause() {
        super.onPause()
        launch.show()
        list_data.hide()
        progress_bar.hide()
    }

    private fun bindViews(){


        recyclerView = findViewById(R.id.list_data)
        recyclerView.layoutManager = LinearLayoutManager(this)

        mainViewModel.listPostLiveData.observe(this, Observer {
            adapter.addDataList(it as ArrayList<PostModel>)
            recyclerView.adapter = adapter
            recyclerView.show()
        })

        mainViewModel.error.observe(this, Observer {
            this.toast(it)
        })

        mainViewModel.isLoading.observe(this, Observer{
            if(it==false){
                progress_bar.hide()
            } else{
                progress_bar.show()
            }
        })
    }

    private fun getList(){
        mainViewModel.getData()

    }

}
