package sk.matejkosut.scratchcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import sk.matejkosut.scratchcard.ui.ScratchCardTheme

@AndroidEntryPoint
class ScratchCardActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
             ScratchCardTheme {
                ScratchCardNavGraph()
            }
        }
    }
}