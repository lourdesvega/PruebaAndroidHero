package lourder.vega.pruebaandroidlcvm.flowSearchHeroes.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import lourder.vega.pruebaandroidlcvm.R
import lourder.vega.pruebaandroidlcvm.databinding.FragmentHeroListBinding
import lourder.vega.pruebaandroidlcvm.flowSearchHeroes.ui.adapter.HeroesAdapter
import lourder.vega.pruebaandroidlcvm.flowSearchHeroes.viewmodel.HeroViewModel
import lourder.vega.pruebaandroidlcvm.utils.NetworkResult
import android.widget.MultiAutoCompleteTextView
import android.widget.MultiAutoCompleteTextView.CommaTokenizer
import androidx.core.widget.doAfterTextChanged
import lourder.vega.pruebaandroidlcvm.domain.model.response.HeroList


class HeroListFragment : Fragment() {
    private lateinit var binding: FragmentHeroListBinding
    private val viewModel by activityViewModels<HeroViewModel>()
    private lateinit var adapter : HeroesAdapter
    private lateinit var adapterAutocomplete : ArrayAdapter<String>
    private var namesHero: List<String> = listOf()
    private var listHeroes: List<HeroList> = listOf()
    private  var tempList: List<HeroList> = listOf()
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
        binding.macHeroes.threshold = 1
        binding.macHeroes.doAfterTextChanged { editable->
            val tempList = listHeroes.filter {
                it.name.replace(" ", "").uppercase().contains(binding.macHeroes.text.toString().replace(" ", "").uppercase())
            }
            adapter.setList(tempList)

            if(tempList.isNullOrEmpty()) binding.tvEmptyList.visibility = View.VISIBLE
            else binding.tvEmptyList.visibility = View.GONE

        }

    }

    private fun initObservers() {
        viewModel.getHeroes()


        viewModel.getHeroes.observe(this) { result ->
            when(result){
                is NetworkResult.Success->{
                    listHeroes = result.data ?: listOf()
                    adapter.setList(listHeroes)
                    namesHero = result.data?.map { it.name } ?: listOf()
                    adapterAutocomplete = ArrayAdapter<String>(context!!, android.R.layout.simple_list_item_1, namesHero)
                    binding.macHeroes.setAdapter(adapterAutocomplete)
                    binding.pb.visibility = View.GONE
                }
                is NetworkResult.Error->{
                    binding.pb.visibility = View.GONE
                    Toast.makeText(context,"Error", Toast.LENGTH_LONG).show()
                }
                is NetworkResult.Loading->{
                    binding.pb.visibility = View.VISIBLE
                }
            }

        }
    }


}