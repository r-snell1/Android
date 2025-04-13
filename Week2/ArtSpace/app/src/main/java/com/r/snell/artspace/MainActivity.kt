package com.r.snell.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.r.snell.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtSpaceApp(innerPadding)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtSpaceApp(innerPadding: PaddingValues = PaddingValues()) {
    var currentStep by remember { mutableIntStateOf(1) }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(MaterialTheme.colorScheme.tertiaryContainer),
        color = MaterialTheme.colorScheme.background
    ) {
        val onNext = { currentStep = if (currentStep < 3) currentStep + 1 else 1 }
        val onPrevious = { currentStep = if (currentStep > 1) currentStep - 1 else 3 }

        when (currentStep) {
            1 -> ArtSpace(
                textLabelResourceId = R.string.Art_1,
                drawableResourceId = R.drawable.art_1,
                contentDescriptionResourceId = R.string.art_1_description,
                onNext = onNext,
                onPrevious = onPrevious
            )
            2 -> ArtSpace(
                textLabelResourceId = R.string.Art_2,
                drawableResourceId = R.drawable.art_2,
                contentDescriptionResourceId = R.string.art_2_description,
                onNext = onNext,
                onPrevious = onPrevious
            )
            3 -> ArtSpace(
                textLabelResourceId = R.string.Art_3,
                drawableResourceId = R.drawable.art_3,
                contentDescriptionResourceId = R.string.art_3_description,
                onNext = onNext,
                onPrevious = onPrevious
            )
        }
    }
}

@Composable
fun ArtSpace(
    textLabelResourceId: Int,
    drawableResourceId: Int,
    contentDescriptionResourceId: Int,
    onNext: () -> Unit,
    onPrevious: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(drawableResourceId),
                contentDescription = stringResource(contentDescriptionResourceId),
                modifier = Modifier
                    .width(dimensionResource(R.dimen.image_width))
                    .height(dimensionResource(R.dimen.image_height))
                    .padding(dimensionResource(R.dimen.interior_padding))
            )
            Text(
                text = stringResource(textLabelResourceId),
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_vertical)))
            Row {
                Button(
                    onClick = onPrevious,
                    shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer
                    )
                ) {
                    Text("Previous")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = onNext,
                    shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer
                    )
                ) {
                    Text("Next")
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}