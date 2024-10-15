package com.example.artspace

import android.media.Image
import android.os.Bundle
import android.os.Message
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Label
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.booleanResource
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ){
                    ArtSpace(modifier = Modifier)
                }
            }
        }
    }
}

@Composable
fun ArtSpace(modifier: Modifier){
    val context = LocalContext.current
    var cardColor by remember {
        mutableStateOf(Color.LightGray)
    }
    var next by remember {
        mutableIntStateOf(1)
    }

    Column(
        modifier= Modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){//AQUI VA IMAGEN
        ImageArt(next = next)
        ElevatedCard (
            onClick = {
                Toast.makeText(context,"Art Gallery", Toast.LENGTH_SHORT).show()
                cardColor = when(cardColor){
                    Color.LightGray -> Color.Magenta
                    else -> Color.LightGray
                }
            },
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp,
            ),
            colors = CardDefaults.cardColors(
                containerColor = cardColor
            ),
            modifier = Modifier
                .size(
                    width = 240.dp, height = 95.dp
                )
                .align(Alignment.CenterHorizontally)
        ){
            TextArt(next = next)
        }
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp, top = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.Bottom
        ){
            ElevatedButton(
                onClick = {
                    next--
                    if (next == 0) next = 10
                },
                modifier = Modifier
                    .width(120.dp)
                    .height(36.dp)
            ) {
                Text(text = "Previous")
            }
            ElevatedButton(
                onClick =  {
                  if(next in 1 .. 9) next++
                    else if(next == 10) next = 1
                },
                modifier = Modifier
                    .width(120.dp)
                    .height(36.dp)
            ) {
                Text(text = "Next")
            }
        }
    }
}

@Composable
fun ImageArt(next: Int){
    val rainbowColorsBrush = remember {
        Brush.sweepGradient(
            listOf(
                Color(0xFF9575CD),
                Color(0xFFBA68C8),
                Color(0xFFE57373),
                Color(0xFF4DD0E1),
                Color(0xFF9575CD)
            )
        )
    }
    val imageResource = when(next){
        1->R.drawable.gor1
        2->R.drawable.gor2
        3->R.drawable.gor3
        4->R.drawable.gor4
        5->R.drawable.gor5
        6->R.drawable.gor6
        7->R.drawable.gor7
        8->R.drawable.gor8
        9->R.drawable.gor9
        else->R.drawable.gor10
    }
    Image(
        painter = painterResource(imageResource),
        contentDescription = next.toString(),
        modifier = Modifier
            .size(550.dp)
            .padding(16.dp)
            .border(BorderStroke(16.dp, rainbowColorsBrush))
            .shadow(10.dp, shape = RectangleShape),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun TextArt(next: Int){
    val stringResources = when(next){
        1 ->R.string.gor1_author
        2 ->R.string.gor2_author
        3->R.string.gore3_author
        4->R.string.gore4_author
        5->R.string.gore5_author
        6->R.string.gore6_author
        7->R.string.gore7_author
        8->R.string.gore8_author
        9->R.string.gore9_author
        else ->R.string.gore10_author
    }

    val stringResourcesTitle = when(next){
        1 ->R.string.gor1_artwork
        2 ->R.string.gor2_artwork
        3->R.string.gore3_artwork
        4->R.string.gore4_artwork
        5->R.string.gore5_artwork
        6->R.string.gore6_artwork
        7->R.string.gore7_artwork
        8->R.string.gor8_artwork
        9->R.string.gor9_artwork
        else ->R.string.gor10_artwork
    }
    val gradientColors = listOf(Color.Blue,Color.Black, Color.Magenta)
    Text(
        text = stringResource(stringResources),
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        style = TextStyle(
            brush = Brush.linearGradient(
                colors = gradientColors
            )
        ),
        modifier = Modifier.padding(top = 8.dp,bottom = 8.dp, start = 32.dp)
    )
    Text(
        text = stringResource(stringResourcesTitle),
        fontSize = 18.sp,
        fontStyle = FontStyle.Italic,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(start = 64.dp)
    )
}

@Preview(
    showBackground = true,
)
@Composable
fun ArtSpacePreview(){
    ArtSpace(modifier = Modifier)
}