package com.cpp.recipebook
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ImagesColumn(){
    Row{
        Column( //column 1
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            //for each recipe 1st half
            //show fragment
        }

        //column 2
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            //for each recipe 2nd half
        }

    }
}

