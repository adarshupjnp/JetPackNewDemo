package com.example.jetpacknewdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.jetpackdemo.FootballPlayer
import com.example.jetpackdemo.FootballPlayersData
import com.example.jetpacknewdemo.ui.theme.JetPackNewDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackNewDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MainActivityLayout()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun MainActivityLayout() {
    FootballPlayerList()
}

@Composable
fun FootballPlayerList() {
    val listFootballPlayers = remember {
        FootballPlayersData.listPlayers
    }
    LazyColumn() {
        items(
            items = listFootballPlayers,
            itemContent = {
                FootballPlayerListItem(footballPlayer = it)
            })
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetPackNewDemoTheme {
        Greeting("Android")
    }
}

@Composable
fun FootballPlayerListItem(footballPlayer: FootballPlayer){
    Card(
        modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp).fillMaxWidth(),
        elevation = 2.dp,
        shape = RoundedCornerShape(corner = CornerSize(15.dp))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp)
        ){
            Image(
                painter = rememberImagePainter(footballPlayer.imgUrl),
                modifier = Modifier
                    .size(40.dp)
                    .clip(shape = CircleShape),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
            Column(
                modifier = Modifier.padding(start = 10.dp)
            ){
                Text(text = footballPlayer.name, fontSize = 22.sp)
                Text(text = footballPlayer.clubName + " " + footballPlayer.countryName, fontSize = 18.sp)
            }
        }
    }
}