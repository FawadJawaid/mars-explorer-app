package com.fawadjmalik.marsexplorer.feature.detailscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.fawadjmalik.marsexplorer.core.utils.Network
import com.fawadjmalik.marsexplorer.core.utils.Constants.TEXT_OFFLINE
import kotlinx.coroutines.launch

/**
 * Author: Muhammad Fawad Jawaid Malik
 * These are the Composables for DogBreedDetails. We are not using any XML code for our UI.
 */
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MarsPhotoDetails(
    navController: NavController,
    marsPhotoDetailsViewModel: MarsPhotoDetailsViewModel = hiltViewModel(),
    image: String,
    name: String,
    launchDate: String,
    landingDate: String
) {
    val scaffoldState = rememberScaffoldState() // this contains the `SnackbarHostState`

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.text_details)) },
                backgroundColor = MaterialTheme.colors.background,
                contentColor = colorResource(id = R.color.text),
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp, 24.dp)
                            .clickable {
                                navController.navigateUp()
                            },
                        tint = colorResource(id = R.color.text)
                    )
                }
            )
        },
        scaffoldState = scaffoldState,
        content = {
            NoInternetConnectionView(scaffoldState)
            DetailsView(image, name, launchDate, landingDate, marsPhotoDetailsViewModel)
        }
    )
}

@Composable
fun DetailsView(
    image: String,
    name: String,
    launchDate: String,
    landingDate: String,
    marsPhotoDetailsViewModel: MarsPhotoDetailsViewModel
) {
    LazyColumn(
        modifier = Modifier
            .background(color = colorResource(id = R.color.background))
    ) {
        item {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp),
                model = image,
                alignment = Alignment.CenterStart,
                loading = {
                    CircularProgressIndicator()
                },
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
        }

        item {
            RoverNameCard(name)
        }

        item {
            Title(title = stringResource(id = R.string.launch_date))
            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = launchDate,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 0.dp, 0.dp, 0.dp),
                color = colorResource(id = R.color.text),
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.W300,
                textAlign = TextAlign.Start
            )
        }

        item {
            Spacer(modifier = Modifier.height(12.dp))
            Title(title = stringResource(id = R.string.landing_date))
            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = landingDate,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 0.dp, 0.dp, 0.dp),
                color = colorResource(id = R.color.text),
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.W300,
                textAlign = TextAlign.Start
            )
        }
    }
}

@Composable
fun Title(title: String) {
    Text(
        text = title,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp, 0.dp, 0.dp),
        color = colorResource(id = R.color.text),
        style = MaterialTheme.typography.subtitle1,
        fontWeight = FontWeight.W600,
        textAlign = TextAlign.Start
    )
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun NoInternetConnectionView(scaffoldState: ScaffoldState) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    if (!Network.isConnected(context)) {
        coroutineScope.launch {
            scaffoldState.snackbarHostState.showSnackbar(
                message = TEXT_OFFLINE
            )
        }
    }
}