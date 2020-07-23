package com.yogi.stockbit.login.presentation

import android.net.Uri
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.*
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.yogi.stockbit.login.InitModule
import com.yogi.stockbit.login.LoginViewModel
import com.yogi.stockbit.login.R
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private val loginViewModel: LoginViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        InitModule.init()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnLogin?.setOnClickListener {
            val uri = Uri.parse("features://HomeFragment")
//            findNavController().navigate(R.id.homeFragment)

//        val graphid = resources.getIdentifier("feature_home_nav_graph","navigation",context?.packageName)
//            var fragPageId = resources.getIdentifier("homeFragment","id",context?.packageName)
//            var graph = view.findNavController().getViewModelStoreOwner(fragPageId)
//
//            if (graph!=null){
//                graph.
//                val destination = NavOptions.Builder()
//                graph.navigate(null,destination.build())
//            }


//            findNavController().navigate(R.id.action_loginFragment_to_home_nav_graph)
        }
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}