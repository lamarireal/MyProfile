package com.example.myprofile

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.wear.compose.material.ContentAlpha
import androidx.wear.compose.material.LocalContentAlpha

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViewContainer()
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ViewContainer() {
    Scaffold(
        topBar = { CustomToolbar() },
        content = { paddingValues ->
            ProfileScreen(Modifier.padding(paddingValues))
        },
        bottomBar = {ToolbarBot()},
        floatingActionButton = {FAB()}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomToolbar() {
    val context = LocalContext.current
    TopAppBar(
        title = { Text(text = "About Me", color = Color.White) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(id = R.color.background)
        ),

        actions = {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
                IconButton(onClick = {
                    val shareIntent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain" // Tipo de dato que se va a compartir
                        putExtra(Intent.EXTRA_SUBJECT, "Mira mi sitio web")
                        putExtra(Intent.EXTRA_TEXT, "https://music.youtube.com/watch?v=6xJvDLIZf8o") // El contenido a compartir
                    }

                    context.startActivity(Intent.createChooser(shareIntent, "Happy core XD"))
                }) {
                    Icon(
                        imageVector = Icons.Outlined.Share,
                        contentDescription = "bbb", tint = Color.White
                    )
                }
            }
        }
    )

}

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProfileImage()
        Spacer(modifier = Modifier.height(16.dp))
        ProfileName(name = "Artem Pysmak")
        Spacer(modifier = Modifier.height(48.dp))
        ProfileDescription(
            description = "Hi, I am backend developer with 3 years experience from Ukraine. " +
                    "I'm studying in CIFP Txurdinaga - Desarollo de Aplicaciones Multiplataformas (DAM)"
        )
        Spacer(modifier = Modifier.height(46.dp))
        ProfileDetails()
        Spacer(modifier = Modifier.height(82.dp))
        SocialLinks()
    }
}

@Composable
fun ProfileImage() {
    Image(
        painter = painterResource(id = R.drawable.profile_image),
        contentDescription = "Profile Image",
        modifier = Modifier
            .size(170.dp)
            .clip(CircleShape),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun ProfileName(name: String) {
    Text(
        text = name,
        style = MaterialTheme.typography.titleLarge.copy(
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    )
}

@Composable
fun ProfileDescription(description: String) {
    Text(
        text = description,
        style = MaterialTheme.typography.bodyMedium,
        color = Color.Gray,
        modifier = Modifier.padding(horizontal = 16.dp),
        lineHeight = 20.sp
    )
}

@Composable
fun ProfileDetails() {
    ProfileItem(icon = R.drawable.ic_education, label = "Education", value = "Backend Developer")
    ProfileItem(icon = R.drawable.ic_sport, label = "Sport", value = "Basketball, gym")
    ProfileItem(icon = R.drawable.ic_food, label = "Favourite Food", value = "Milk with protein")
    ProfileItem(icon = R.drawable.ic_hobby, label = "Hobby", value = "Dota 2, walking")
}

@Composable
fun ProfileItem(icon: Int, label: String, value: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = label,
            tint = Color.Black,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            )
            Text(text = value, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun SocialLinks() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        SocialIcon(icon = R.drawable.ic_github, url = "https://github.com/lamarireal")
        SocialIcon(icon = R.drawable.ic_linkedin, url = "https://www.linkedin.com/in/artem-pysmak-a97313293")
        SocialIcon(icon = R.drawable.ic_instagram, url = "https://www.instagram.com/lamarirealll")
    }
}

@SuppressLint("QueryPermissionsNeeded")
@Composable
fun ToolbarBot() {

    val context = LocalContext.current

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.background))
            .height(60.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
            IconButton(onClick = {
                val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:") // Solo abre aplicaciones de correo
                    putExtra(Intent.EXTRA_EMAIL, arrayOf("170219a7@gmail.com")) // Correo destinatario
                    putExtra(Intent.EXTRA_SUBJECT, "Asunto del correo") // Asunto del correo
                }

                if (emailIntent.resolveActivity(context.packageManager) != null) {
                    context.startActivity(emailIntent)
                }
            }) {
                Icon(
                    imageVector = Icons.Outlined.MailOutline,
                    contentDescription = "bbb",
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
fun FAB() {
    val context = LocalContext.current
    FloatingActionButton(
        onClick = {
            Toast.makeText(
                context,
                "Uvuvwevwevwe Onyetenyevwe Ugwemuhwem Osas",
                Toast.LENGTH_SHORT
            ).show()
        },
        modifier = Modifier
            .padding(start = 15.dp, top = 15.dp, end = 8.dp, bottom = 15.dp),
        containerColor  = colorResource(id = R.color.yeee)
    ) {
        Image(
            painter = painterResource(
                id = R.drawable.baseline_favorite_24),
                contentDescription = "fab", contentScale = ContentScale.FillBounds,
                colorFilter = ColorFilter.tint(Color.Black))
    }
}

@Composable
fun SocialIcon(icon: Int, url: String) {
    val context = LocalContext.current
    Icon(
        painter = painterResource(id = icon),
        contentDescription = null,
        modifier = Modifier
            .size(36.dp)
            .clickable {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context.startActivity(intent)
            }
    )
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}
