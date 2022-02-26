package lourder.vega.pruebaandroidlcvm.flowSearchHeroes.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import lourder.vega.pruebaandroidlcvm.R
import lourder.vega.pruebaandroidlcvm.databinding.FragmentHeroListBinding
import lourder.vega.pruebaandroidlcvm.flowSearchHeroes.ui.adapter.HeroesAdapter
import lourder.vega.pruebaandroidlcvm.flowSearchHeroes.viewmodel.HeroViewModel
import lourder.vega.pruebaandroidlcvm.utils.NetworkResult


class HeroListFragment : Fragment() {
    private lateinit var binding: FragmentHeroListBinding
    private val viewModel by activityViewModels<HeroViewModel>()
    private lateinit var adapter : HeroesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHeroListBinding.inflate(layoutInflater)
        initView()
        initObservers()
        return binding.root
    }

    private fun initView() {
        adapter = HeroesAdapter{ id ->
            Navigation.findNavController(view!!).navigate(R.id.heroDetailsFragment)
            viewModel.getHero(id)
        }
        binding.rvHeroes.adapter = adapter
    }

    private fun initObservers() {
        viewModel.getHeroes()


        viewModel.getHeroes.observe(this) { result ->
            when(result){
                is NetworkResult.Success->{

                    Toast.makeText(context, "Correcto", Toast.LENGTH_LONG).show()
                    adapter.setList(result.data ?: listOf())
                }
                is NetworkResult.Error->{
                    Toast.makeText(context,"Error", Toast.LENGTH_LONG).show()
                }
                is NetworkResult.Loading->{
                }
            }

        }
    }


}