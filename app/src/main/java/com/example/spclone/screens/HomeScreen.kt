package com.example.spclone.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Book
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight

@Composable
fun HomeScreen(
    onLogout: () -> Unit,
    onAlbumClick: (String) -> Unit = {} // Callback para manejar clicks en álbumes
) {
    Scaffold(
        bottomBar = { BottomNavigationBar(onLogout = onLogout) },
        containerColor = Color.Black
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            item {
                FilterRow()
                SectionTitle("Los éxitos del momento")
                MusicRow(
                    items = listOf(
                        "Éxitos Perú" to "Bad Bunny, Fuerza Regida, Beéle, Maluma...",
                        "Éxitos del Ayer" to "J Balvin, Myke Towers, Maluma, Don Omar...",
                        "Viva Latino" to "Fuerza Regida, Feid, J E..."
                    ),
                    onItemClick = onAlbumClick
                )
                SectionTitle("Listas")
                MusicRow(
                    items = listOf(
                        "Top 50 - PERU" to "Tu actualización diaria de las canciones más e...",
                        "Top 50 - GLOBAL" to "Tu actualización diaria de las canciones más e..."
                    ),
                    onItemClick = onAlbumClick
                )
                SectionTitle("Recientes")
                Spacer(modifier = Modifier.height(8.dp))
                Spacer(modifier = Modifier.height(80.dp))
            }
        }
    }
}

@Composable
fun FilterRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FilterChip(text = "Todas", selected = true)
        FilterChip(text = "Música", selected = false)
        FilterChip(text = "Podcasts", selected = false)
    }
}

@Composable
fun FilterChip(text: String, selected: Boolean) {
    Surface(
        shape = RoundedCornerShape(20.dp),
        color = if (selected) Color(0xFF1DB954) else Color.DarkGray
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = text, color = Color.White, fontSize = 14.sp)
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        color = Color.White,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(start = 4.dp, top = 16.dp, bottom = 8.dp)
    )
}

@Composable
fun MusicRow(
    items: List<Pair<String, String>>,
    onItemClick: (String) -> Unit = {} // Callback para manejar clicks
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(items.size) { index ->
            val (title, desc) = items[index]
            Column(
                modifier = Modifier
                    .width(140.dp)
                    .clickable { onItemClick(title) } // Hace clickeable todo el elemento
            ) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Color.DarkGray,
                    modifier = Modifier.size(140.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(text = title, color = Color.White, fontWeight = FontWeight.SemiBold)
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = desc,
                    color = Color.LightGray,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
        }
    }
}

@Composable
fun BottomNavigationBar(onLogout: () -> Unit) {
    NavigationBar(containerColor = Color.Black) {
        NavigationBarItem(
            selected = true,
            onClick = { },
            icon = { Icon(Icons.Filled.Home, contentDescription = "Inicio", tint = Color.White) },
            label = { Text("Inicio", color = Color.White) }
        )
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Icon(Icons.Filled.Search, contentDescription = "Buscar", tint = Color.White) },
            label = { Text("Buscar", color = Color.White) }
        )
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Icon(Icons.Filled.Book, contentDescription = "Tu biblioteca", tint = Color.White) },
            label = { Text("Tu biblioteca", color = Color.White) }
        )
        NavigationBarItem(
            selected = false,
            onClick = { onLogout() },
            icon = { Icon(Icons.Filled.ExitToApp, contentDescription = "Salir", tint = Color.White) },
            label = { Text("Salir", color = Color.White) }
        )
    }
}

