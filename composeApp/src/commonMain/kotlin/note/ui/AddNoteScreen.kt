package note.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import note.di.NoteModule
import note.domain.NoteModel

object AddNoteScreen : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel { AddNoteViewModel(NoteModule.noteRepository) }

        val state by viewModel.state.collectAsState()

        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(state.isSuccess) {
            if (state.isSuccess)
                navigator.pop()
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                navigator.pop()
                            }
                        ) {
                            Icon(
                                Icons.Rounded.ArrowBack,
                                contentDescription = "Navigate back",
                            )
                        }
                    },
                    title = {
                        Text(text = "Add note")
                    }
                )
            },
            modifier = Modifier
                .fillMaxSize()
                .safeDrawingPadding()
        ) {
            AddNoteScreenContent(
                state = state,
                onEvent = viewModel::onEvent,
                modifier = Modifier
                    .padding(it)
            )
        }
    }

}

@Composable
private fun AddNoteScreenContent(
    state: AddNoteState,
    onEvent: (AddNoteEvent) -> Unit,
    modifier: Modifier = Modifier,
) {

    var title by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        TextField(
            value = title,
            onValueChange = { title = it },
            label = {
                Text(
                    text = "Title"
                )
            },
            modifier = Modifier
                .fillMaxWidth()
        )

        TextField(
            value = description,
            onValueChange = { description = it },
            label = {
                Text(
                    text = "Description"
                )
            },
            modifier = Modifier
                .fillMaxWidth()
        )

        if (state.isLoading)
            CircularProgressIndicator()
        else
            Button(
                onClick = {
                    val note = NoteModel(
                        id = "",
                        title = title,
                        description = description,
                    )
                    onEvent(AddNoteEvent.AddNote(note = note))
                }
            ) {
                Text(
                    text = "Add note"
                )
            }
    }
}