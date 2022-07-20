package com.example.narutochar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.narutochar.ui.theme.NarutoCharTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            
            Scaffold(bottomBar = { AppBottomNavigation() }) {
                padding ->  HomePage()
            }


        }
    }
}


data class Characters(val img : Int,val name : String)

val narutoPeople = listOf(
    Characters(R.drawable.guy,"Might Guy") ,
Characters(R.drawable.kakshi,"Kakashi"),
Characters(R.drawable.itachi,"Itachi"),
Characters(R.drawable.naruto,"Naruto"),
Characters(R.drawable.sasuke,"Sasuke"),
Characters(R.drawable.pain,"Pain"),
        Characters(R.drawable.jiraya,"Jiraiya")
)

data class ClanList(val id: Int,val name : String)

val clans = listOf(
    ClanList(R.drawable.uchiha,"Uchiha Clan"),
            ClanList(R.drawable.senju,"Senju Clan"),
ClanList(R.drawable.uzumaki,"Uzumaki Clan"),
        ClanList(R.drawable.nara,"Naara Clan"),
    ClanList(R.drawable.hatake,"Hatake Clan"),
    ClanList(R.drawable.huyga,"Huyga Clan"),
    ClanList(R.drawable.yamanaka,"Yamanaka Clan"),
    ClanList(R.drawable.akimichi,"Akamichi Clan"),
    ClanList(R.drawable.inazuka,"Inazuka Clan")
)


@Composable
fun HomePage(){
    Column {
        SearchBar()
        Text(text = "Popular Characters (Male)", modifier = Modifier.padding(10.dp), fontWeight = FontWeight.SemiBold, fontSize = 20.sp)

        AlignYourBodyRow()
        
        Text(text = "Browse Clans", modifier = Modifier.padding(10.dp), fontWeight = FontWeight.SemiBold, fontSize = 20.sp)

        ClansGrid()

        AppBottomNavigation()

    }

}


@Composable
fun SearchBar(modifier: Modifier = Modifier){
        OutlinedTextField(value = "", onValueChange = {},modifier = modifier
            .fillMaxWidth()
            .heightIn(56.dp)
            .padding(10.dp),
            placeholder = { Text(text = "Search Characters here")},
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface
            ),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = null )
            }

            )

}


@Composable
fun AlignYourBodyElement(modifier: Modifier = Modifier,image: Int,name:String){
                Column(modifier = Modifier,       horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                        Image(painter = painterResource(id = image), contentDescription = null,contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(5.dp)
                            .size(88.dp)
                            .clip(CircleShape)

                        )
                    Text(text =name, textAlign = TextAlign.Center, fontWeight = FontWeight.SemiBold)
                }

}

@Composable
fun Clans(modifier: Modifier = Modifier,id: Int,name: String){
        Row( verticalAlignment = Alignment.CenterVertically,modifier = Modifier
            .width(192.dp)
            .padding(10.dp)
        ) {
            Image(painter = painterResource(id = id), contentDescription = null,contentScale = ContentScale.Crop,
                modifier = Modifier.size(56.dp)
            )
            Text(text = name, modifier = Modifier.padding(10.dp))
        }
}


@Composable
fun AlignYourBodyRow(modifier: Modifier = Modifier){
    LazyRow(modifier = modifier){
        items(narutoPeople){ item->
            AlignYourBodyElement(image = item.img, name = item.name)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ClansGrid(modifier: Modifier = Modifier){
    LazyVerticalGrid(cells =GridCells.Fixed(count = 2)){
            items(clans){
                item->
                Clans(id = item.id, name =item.name )
            }
    }
}


@Composable
private fun AppBottomNavigation(modifier: Modifier  = Modifier){
    BottomNavigation(modifier=modifier, backgroundColor = MaterialTheme.colors.background) {
        BottomNavigationItem( icon={
            Icon(imageVector = Icons.Default.Home, contentDescription =null )
        },
            label = { Text(text = "Home")}, selected = true, onClick = {}
        )
        BottomNavigationItem( icon={
            Icon(imageVector = Icons.Default.AccountBox, contentDescription =null )
        },
            label = { Text(text = "Profile")}, selected = true, onClick = {}
        )
        
    }
}