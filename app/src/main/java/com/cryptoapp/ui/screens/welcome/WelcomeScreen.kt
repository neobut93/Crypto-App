package com.cryptoapp.ui.screens.welcome


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cryptoapp.preferences.CryptoSharedPrefs
import com.cryptoapp.preferences.PreferencesConstants.WELCOME_KEY

@Composable
fun CryptoWelcomeScreen(
    viewModel: WelcomeScreenViewModel,
    navigate: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        CryptoWelcomePage()
        Spacer(modifier = Modifier.height(5.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 5.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    viewModel.saveData(WELCOME_KEY, true)
                    navigate()
                },
                shape = RoundedCornerShape(size = 6.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.inversePrimary
                ),
                modifier = Modifier.padding(end = 10.dp)
            ) {
                Text(text = "Continue")
            }
        }
    }
}


@Preview
@Composable
fun OnBoardingScreenPreview() {
    CryptoWelcomeScreen(
        viewModel = WelcomeScreenViewModel(
            prefs = object : CryptoSharedPrefs {
                override fun getData(key: String, defaultValue: Boolean): Boolean {
                    return true
                }

                override fun saveData(key: String, value: Boolean) {}
            }
        ),
        navigate = {}
    )
}
