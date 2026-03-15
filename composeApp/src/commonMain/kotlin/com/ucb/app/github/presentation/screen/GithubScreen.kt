package com.ucb.app.github.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import coil3.compose.AsyncImage
import com.ucb.app.github.presentation.state.GithubEvent
import com.ucb.app.github.presentation.viewmodel.GithubViewModel
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.github_find
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun GithubScreen( viewModel: GithubViewModel = koinViewModel()) {

    val state by viewModel.state.collectAsState()

    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier.verticalScroll(scrollState)
    ) {
        TextField( value = state.nickname, onValueChange = {
            viewModel.onEvent(GithubEvent.OnChangeAvatar(it))
        })
        OutlinedButton(
            enabled = !state.isLoading,
            onClick = {
            viewModel.onEvent(GithubEvent.OnClickFind)
        }) {
            Text(stringResource(Res.string.github_find))
        }

        Text(state.model.avatar)
        Text(state.model.urlImage)
        AsyncImage(model = state.model.urlImage, contentDescription = null)


    }
}