package iut.priam.td_facture

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun calTTCDisplay(navController: NavHostController, montantHT: String?, tauxTVA: String?, remise: String?){
    val modifier = Modifier
    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        var montantTTC by remember {
            mutableStateOf("")
        }
        if (montantHT!!.isNotBlank() && tauxTVA!!.isNotBlank() && remise!!.isNotBlank()){
            montantTTC = if (montantHT.isNotBlank() && tauxTVA.isNotBlank() && remise.isNotBlank()){
                val montantHTValue = montantHT.toFloat()
                val tauxTVAValue = tauxTVA.toFloat()
                val remiseValue = remise.toFloat()
                calTTC(montantHTValue, tauxTVAValue, remiseValue).toString()
            } else {
                "0"
            }
            Text(
                text = "Montant TTC",
                color = Color.Red,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier.height(20.dp))
            Text("$montantTTC €")
        }
        Button(onClick = {navController.navigate("login")}){
            Text(text = "Retour", fontSize = 17.sp)
        }
    }
}
fun calTTC(value1 :Float, value2: Float, value3: Float):Float {
    return value1 + value1 * value2 / 100 - value1 * value3 / 100
}