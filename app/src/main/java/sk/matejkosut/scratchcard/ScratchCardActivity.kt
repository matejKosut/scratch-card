package sk.matejkosut.scratchcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import sk.matejkosut.scratchcard.data.ScratchCardRepository
import sk.matejkosut.scratchcard.ui.ScratchCardTheme
import javax.inject.Inject

@AndroidEntryPoint
class ScratchCardActivity : ComponentActivity() {

    @Inject
    lateinit var scratchCardRepository: ScratchCardRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val code = (0..100).random()
        scratchCardRepository.createScratchCard(code, 1) // 1 - unscratched, 2 - scratched, 3 - activated
        setContent {
            ScratchCardNavGraph()
        }
    }
}