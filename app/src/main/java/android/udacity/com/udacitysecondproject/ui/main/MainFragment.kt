package android.udacity.com.udacitysecondproject.ui.main

import android.os.Bundle
import android.udacity.com.udacitysecondproject.R
import android.udacity.com.udacitysecondproject.api.parseAsteroidsJsonResult
import android.udacity.com.udacitysecondproject.databinding.FragmentMainBinding
import android.udacity.com.udacitysecondproject.network.AsteroidApiFilter
import android.udacity.com.udacitysecondproject.network.Resource
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONObject

@AndroidEntryPoint
class MainFragment : Fragment() {

    lateinit var binding: FragmentMainBinding

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    lateinit var viewModelAdapter: AsteroidAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.pictureOfDay.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), "Failure", Toast.LENGTH_SHORT).show()
                }
                Resource.Loading -> {}
                is Resource.Success -> {
                    if (it.value.media_type == "image") {
                        Picasso.with(requireContext()).load(it.value.url)
                            .into(binding.activityMainImageOfTheDay)
                        binding.activityMainImageOfTheDay.contentDescription = requireContext().getString(R.string.nasa_picture_of_day_content_description_format,it.value.title)
                    }
                }
            }
        }

        viewModel.getAsteroidListFromDB(AsteroidApiFilter.SHOW_ALL)
            .observe(viewLifecycleOwner) {
                if (it.isEmpty()) {
                     viewModel.refreshAsteroidList()
                } else {
                    viewModelAdapter.submitList(it)
                }
            }

        viewModel.asteroidList.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Failure -> {
                    Toast.makeText(
                        requireContext(),
                        "${it.errorBody?.error?.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
                Resource.Loading -> {}
                is Resource.Success -> {
                    parseAsteroidsJsonResult(JSONObject(it.value), viewModel)
                }
            }
        }

        viewModel.navigateToAsteroidDetails.observe(viewLifecycleOwner) {
            if (null != it) {
                // Must find the NavController from the Fragment
                this.findNavController().navigate(MainFragmentDirections.actionShowDetail(it))
                // Tell the ViewModel we've made the navigate call to prevent multiple navigation
                viewModel.asteroidDetailsNavigationComplete()
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModelAdapter = AsteroidAdapter(AsteroidAdapter.OnClickListener {
            viewModel.navigateToAsteroidDetails(it)
        })

        binding.asteroidRecycler.apply {
            adapter = viewModelAdapter
        }


        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.getAsteroidListFromDB(
            when (item.itemId) {
                R.id.show_week_list -> AsteroidApiFilter.SHOW_SEVEN_DAYS
                R.id.show_today -> AsteroidApiFilter.SHOW_TODAY
                else -> AsteroidApiFilter.SHOW_ALL
            }
        ).observe(viewLifecycleOwner) {
            viewModelAdapter.submitList(it)
        }
        return true
    }

}
