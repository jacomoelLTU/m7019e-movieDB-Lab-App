package com.ltu.m7019e.moviedb.v24

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.themoviedbv24.R
import com.example.themoviedbv24.ui.screens.MovieDetailScreen
import com.example.themoviedbv24.ui.screens.MovieExpandedDetailScreen
import com.example.themoviedbv24.ui.screens.MovieGridScreen
import com.example.themoviedbv24.viewmodels.MovieDBViewModel


enum class MovieDBScreen(@StringRes val title: Int) {
    List(title = R.string.app_name),
    Detail(title = R.string.movie_detail),
    ExpandedDetails(title = R.string.movie_detail_expanded)
}


/**
 * Composable that displays the topBar and displays back button if back navigation is possible.
 */
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDBAppBar(
    currentScreen: MovieDBScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    movieDBViewModel: MovieDBViewModel
) {

    var menuExpanded by remember { mutableStateOf(false) }

    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        actions = {
                  IconButton(onClick = {
                      menuExpanded = !menuExpanded
                  }) {
                        Icon(
                            imageVector = Icons.Filled.MoreVert,
                            contentDescription = "Open Menu to select movie lists"
                        )
                  }
            DropdownMenu(expanded = menuExpanded, onDismissRequest = { menuExpanded = false }) {
                DropdownMenuItem(
                    onClick = {
                    movieDBViewModel.getPopularMovies()
                    menuExpanded = false
                    }, text = {
                        Text(text = stringResource(R.string.popular_movies))
                    }
                )
                DropdownMenuItem(
                    onClick = {
                        movieDBViewModel.getTopRatedMovies()
                        menuExpanded = false
                    }, text = {
                        Text(text = stringResource(R.string.top_rated_movies))
                    }
                )
                DropdownMenuItem(
                    onClick = {
                        movieDBViewModel.getFavoriteMovies()
                        menuExpanded = false
                    }, text = {
                        Text(text = stringResource(R.string.saved_movies))
                    }
                )
            }
        },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}


@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun MovieDBApp(
    navController: NavHostController = rememberNavController()
) {
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = MovieDBScreen.valueOf(
        backStackEntry?.destination?.route ?: MovieDBScreen.List.name
    )
    val movieDBViewModel: MovieDBViewModel = viewModel(factory = MovieDBViewModel.Factory)

    Scaffold(
        topBar = {
            MovieDBAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() },
                //movieDBViewModel = viewModel(factory = MovieDBViewModel.Factory)
                movieDBViewModel = movieDBViewModel
            )
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = MovieDBScreen.List.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(route = MovieDBScreen.List.name) {
                MovieGridScreen(
                    movieListUiState = movieDBViewModel.movieListUiState,
                    onMovieListItemClicked = {
                        movieDBViewModel.setSelectedMovie(it)
                        navController.navigate(MovieDBScreen.Detail.name)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                )
            }
            composable(route = MovieDBScreen.Detail.name) {
                MovieDetailScreen(
                    //selectedMovieUiState = movieDBViewModel.selectedMovieUiState,
                    movieDBViewModel = movieDBViewModel,
                    modifier = Modifier,
                    onExpandDetailsClicked = {
                        movieDBViewModel.setSelectedMovie(it)
                        navController.navigate(MovieDBScreen.ExpandedDetails.name)
                    }
                )
            }
            composable(route = MovieDBScreen.ExpandedDetails.name) {
                     //state for third screeen used here
                    MovieExpandedDetailScreen(
                        selectedMovieUiState = movieDBViewModel.selectedMovieUiState,
                        modifier = Modifier
                    )
                }
            }
    }
}
