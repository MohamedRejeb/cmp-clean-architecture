package home.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import note.di.NoteModule
import note.ui.AddNoteScreen

object HomeScreen : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val viewModel = rememberScreenModel { HomeViewModel(NoteModule.noteRepository) }

        val state by viewModel.state.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.onEvent(HomeEvent.RefreshNotes)
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Notes app"
                        )
                    },
                    actions = {
                        IconButton(
                            onClick = {
                                navigator.push(AddNoteScreen)
                            }
                        ) {
                            Icon(
                                Icons.Rounded.Add,
                                contentDescription = "Add note"
                            )
                        }
                    }
                )
            },
            modifier = Modifier
                .fillMaxSize()
                .safeDrawingPadding()
        ) {
            LazyColumn(
                contentPadding = PaddingValues(20.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                items(state.noteList, key = { it.id }) { note ->
                    ListItem(
                        headlineContent = {
                            Text(
                                text = note.title,
                            )
                        }
                    )
                }
            }
        }
    }

}