package com.example.azimutlab.mvvm.view.activities

import androidx.appcompat.app.AppCompatActivity
import com.example.azimutlab.AzimutApp
import com.example.azimutlab.adapters.DataListAdapter
import com.example.azimutlab.dagger.components.DaggerServiceComponent

class MainActivity :   AppCompatActivity(){

    init {
        DaggerServiceComponent.builder()
            .appComponent(AzimutApp.appComponent)
            .build()
            .inject(this)
    }

    // Ссылка на viewModel, изменение состояния через подписку LiveData а не через колбек
    // в ВьюМоделе нужно ссылку на репозиторий и все возвращаемое оборачивать в обсервабле
    // BindingData dependencу с вьюшкой хмл

//    @InjectPresenter
//    lateinit var presenter: MainPresenter

//    @Inject
//    lateinit var mainRepos:MainRepositoryImpl

//    @ProvidePresenter
//    fun providePresenter(): MainPresenter{
//        return MainPresenter(mainRepos)
//    }

    private var adapter = DataListAdapter()


}
