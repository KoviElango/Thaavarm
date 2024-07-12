package com.example.thaavarm

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thaavarm.ui.theme.RedB
import com.example.thaavarm.ui.theme.YellowB

@Composable
fun MainButton(
    onClick: () -> Unit,
    isCamera: Boolean,
    modifier: Modifier = Modifier
) {
    val transition = updateTransition(targetState = isCamera, label = "")
    val backgroundColor by transition.animateColor(label = "") { state ->
        if (state) YellowB else Color.Gray
    }

    val iconTintColor by transition.animateColor(label = "") { state ->
        if (state) RedB else Color.White
    }

    val icon = if (isCamera) Icons.Filled.Star else Icons.Filled.ArrowBack

    Button(
        onClick = onClick,
        modifier = modifier.size(64.dp),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor
        )
    ) {
        Icon(
            imageVector = icon,
            contentDescription = if (isCamera) "Take Photo" else "Go Back",
            modifier = Modifier.size(32.dp),
            tint = iconTintColor
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainButtonPreview() {
    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        MainButton(onClick = {}, isCamera = true)
        MainButton(onClick = {}, isCamera = false)
    }
}
