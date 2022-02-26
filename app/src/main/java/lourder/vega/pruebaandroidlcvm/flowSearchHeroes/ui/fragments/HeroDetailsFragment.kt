package lourder.vega.pruebaandroidlcvm.flowSearchHeroes.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import lourder.vega.pruebaandroidlcvm.R
import lourder.vega.pruebaandroidlcvm.databinding.FragmentHeroDetailsBinding
import lourder.vega.pruebaandroidlcvm.flowSearchHeroes.ui.adapter.DataAdapter
import lourder.vega.pruebaandroidlcvm.flowSearchHeroes.ui.adapter.HeroesAdapter
import lourder.vega.pruebaandroidlcvm.flowSearchHeroes.viewmodel.HeroViewModel
import lourder.vega.pruebaandroidlcvm.utils.NetworkResult


class HeroDetailsFragment : Fragment() {
    private lateinit var binding: FragmentHeroDetailsBinding
    private val viewModel by activityViewModels<HeroViewModel>()
    private lateinit var adapterHeight : DataAdapter
    private lateinit var adapterWeight : DataAdapter
    private lateinit var adapterAlias : DataAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHeroDetailsBinding.inflate(layoutInflater)
        iniView()
        initObservers()
        return binding.root
    }

    private fun iniView() {
        adapterAlias = DataAdapter()
        adapterHeight = DataAdapter()
        adapterWeight = DataAdapter()

        binding.apply {
            rvHeight.adapter = adapterHeight
            rvWeight.adapter = adapterWeight
            rvAliases.adapter = adapterAlias

        }
    }

    private fun initObservers() {
        viewModel.getHero.observe(this) { result ->
            when(result){
                is NetworkResult.Success->{

                    Toast.makeText(context, "Correcto", Toast.LENGTH_LONG).show()
                    adapterWeight.setList(result.data?.appearance?.weight ?: listOf())
                    adapterAlias.setList(result.data?.biography?.aliases ?: listOf())
                    adapterHeight.setList(result.data?.appearance?.height ?: listOf())


                    binding.apply {


                        Glide.with(ivHero)
                            .load(result.data?.images?.md)
                            .centerCrop()
                            .into(ivHero)

                        tvNameHero.text = result.data?.name

                        tvFullNameDescription.text = result.data?.biography?.fullName
                        tvAlterEgosDescription.text = result.data?.biography?.alterEgos
                        tvPlaceOfBirthDescription.text = result.data?.biography?.placeOfBirth
                        tvFirstAppearanceDescription.text = result.data?.biography?.firstAppearance
                        tvPublisherDescription.text = result.data?.biography?.publisher
                        tvAlignmentDescription.text = result.data?.biography?.alignment

                        tvIntelligencePercent.text = result.data?.powerStats?.intelligence.toString()
                        tvStrengthPercent.text = result.data?.powerStats?.strength.toString()
                        tvSpeedPercent.text = result.data?.powerStats?.speed.toString()
                        tvDurabilityPercent.text = result.data?.powerStats?.durability.toString()
                        tvPowerPercent.text = result.data?.powerStats?.power.toString()
                        tvCombatPercent.text = result.data?.powerStats?.combat.toString()


                        tvGenderDescription.text = result.data?.appearance?.gender
                        tvRaceDescription.text = result.data?.appearance?.race
                        tvEyeColorDescription.text = result.data?.appearance?.eyeColor
                        tvHairColorDescription.text = result.data?.appearance?.hairColor

                        tvOccupationDescription.text = result.data?.work?.occupation
                        tvBaseDescription.text = result.data?.work?.base

                        tvGroupAffiliationDescription.text = result.data?.connections?.groupAffiliation
                        tvRelativesDescription.text = result.data?.connections?.relatives

                    }

                }
                is NetworkResult.Error->{
                    Toast.makeText(context,"Error", Toast.LENGTH_LONG).show()
                }
                is NetworkResult.Loading->{
                }
            }

        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}