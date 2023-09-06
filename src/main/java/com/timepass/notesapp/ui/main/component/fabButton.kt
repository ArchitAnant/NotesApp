package com.timepass.notesapp.ui.main.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.timepass.notesapp.utils.backgroundColor
import com.timepass.notesapp.utils.fabColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FabButton(fabButtonColor:Color,onFabClick:()->Unit){
    Card(shape = RoundedCornerShape(11.dp),
        colors = CardDefaults.cardColors(containerColor = fabButtonColor),
        modifier = Modifier
            .size(47.dp),
        onClick = onFabClick
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                Icons.Default.Add,
                contentDescription = null,
                tint = backgroundColor,
                modifier = Modifier.size(35.dp)
            )
        }
    }
}

@Preview
@Composable
fun FabButtonPrev(){
    FabButton(Color.White){
    }
}