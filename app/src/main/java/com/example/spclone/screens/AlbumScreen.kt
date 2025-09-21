package com.example.spclone.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Song(
    val title: String,
    val artist: String,
    val duration: String,
    val isPlaying: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumScreen(
    albumTitle: String,
    onBackClick: () -> Unit,
    onSongClick: (Song) -> Unit = {}
) {

    val songs = getSongsForAlbum(albumTitle)
    var currentPlayingSong by remember { mutableStateOf<Song?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(albumTitle, color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Volver", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        },
        containerColor = Color.Black
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            item {
                AlbumHeader(albumTitle)
                Spacer(modifier = Modifier.height(16.dp))
            }


            itemsIndexed(songs) { index, song ->
                SongItem(
                    song = song,
                    trackNumber = index + 1,
                    isCurrentlyPlaying = currentPlayingSong?.title == song.title,
                    onClick = {
                        currentPlayingSong = if (currentPlayingSong?.title == song.title) null else song
                        onSongClick(song)
                    }
                )
            }
        }
    }
}

@Composable
fun AlbumHeader(albumTitle: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Surface(
            shape = RoundedCornerShape(8.dp),
            color = Color.DarkGray,
            modifier = Modifier.size(200.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(
                    text = albumTitle,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = albumTitle,
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Playlist • Spotify",
            color = Color.LightGray,
            fontSize = 14.sp
        )
    }
}

@Composable
fun SongItem(
    song: Song,
    trackNumber: Int,
    isCurrentlyPlaying: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier.size(40.dp),
            contentAlignment = Alignment.Center
        ) {
            if (isCurrentlyPlaying) {
                Icon(
                    Icons.Filled.Pause,
                    contentDescription = "Pausar",
                    tint = Color(0xFF1DB954),
                    modifier = Modifier.size(24.dp)
                )
            } else {
                Text(
                    text = trackNumber.toString(),
                    color = Color.LightGray,
                    fontSize = 16.sp
                )
            }
        }

        Spacer(modifier = Modifier.width(12.dp))


        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = song.title,
                color = if (isCurrentlyPlaying) Color(0xFF1DB954) else Color.White,
                fontSize = 16.sp,
                fontWeight = if (isCurrentlyPlaying) FontWeight.Bold else FontWeight.Normal
            )
            Text(
                text = song.artist,
                color = Color.LightGray,
                fontSize = 14.sp
            )
        }


        Text(
            text = song.duration,
            color = Color.LightGray,
            fontSize = 14.sp
        )
    }
}


fun getSongsForAlbum(albumTitle: String): List<Song> {
    return when (albumTitle) {
        "Éxitos Perú" -> listOf(
            Song("No tiene Sentido", "Beéle", "2:37"),
            Song("BAILE INoLVIDABLE", "Bad Bunny", "6:07"),
            Song("COQUETA", "Fuerza Regida, Grupo Frontera", "4:01"),
            Song("La Plena - W Sound 05", "W Sound, Beéle, Ovy On The Drums", "2:29"),
            Song("YO y TÚ", "Ovy On The Drums, Quevedo, Beéle", "3:17")
        )

        "Éxitos del Ayer" -> listOf(
            Song("Hace Mucho Timepo", "Arcángel", "2:51"),
            Song("Borro Cassette", "Maluma", "3:26"),
            Song("UN PESO", "J Balvin, Bad Bunny, Marciano Cantero", "4:36"),
            Song("Primera Cita", "Carín León", "3:06"),
            Song("Sola(Remix)", "AnueL AA, Daddy Yankee, Zion&Lennox, Farruko, Wisin", "5:07")
        )

        "Viva Latino" -> listOf(
            Song("Culpable", "Junior H", "4:21"),
            Song("Chula Vente", "Luis R Conriquez, Fuerza Regida, Neton Vega", "4:01"),
            Song("TU VAS SIN", "Rels B", "1:49"),
            Song("TODO KE VER", "Jere Klein, Katteyes, Mateo on the Beatz", "2:37"),
            Song("ESTA ES TU CASA NENA", "Kapo, Camilo", "3:28")
        )

        "Top 50 - PERU" -> listOf(
            Song("No tiene Sentido", "Beéle", "2:37"),
            Song("La Plena - W Sound 05", "W Sound, Beéle, Ovy On The Drums", "2:29"),
            Song("Shiny", "Easykid", "2:38"),
            Song("si te pillara", "Beéle", "3:04"),
            Song("YO y TÚ", "Ovy On The Drums, Quevedo, Beéle", "3:17")
        )

        "Top 50 - GLOBAL" -> listOf(
            Song("Golden", "HUNTR/X, EJAE, AUDREY NUNA, REI AMI, KPop Demon Hunters Cast", "3:25"),
            Song("Back to friends", "sombr", "3:17"),
            Song("Ordinary", "Alex Warren", "3:06"),
            Song("Don´t Say You Love Me", "Jin", "3:00"),
            Song("Man I Need", "Olivia Dean", "3:03")
        )

        else -> listOf(
            Song("Canción Ejemplo", "Artista Desconocido", "3:30")
        )
    }
}

