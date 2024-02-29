package iut.priam.td_facture

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavHostController

@Composable
fun AccueilDisplay(navController: NavHostController, login: String?, mdp: String?) {
    val modifier = Modifier
    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var quantityValue by remember {
            mutableStateOf("")
        }
        var prixUnitaireValue by remember {
            mutableStateOf("")
        }
        var montantHTValue by remember {
            mutableStateOf("")
        }
        var tauxTVAValue by remember {
            mutableStateOf("")
        }
        var remiseValue by remember {
            mutableStateOf("")
        }
        var error by remember {
            mutableStateOf("")
        }
        if (login == "etudiant" && mdp == "AzertY") {
            Text(
                text = "Facture",
                color = Color.Red,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )
            TextField(
                value = quantityValue,
                onValueChange = { quantityValue = it },
                modifier.padding(5.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                placeholder = { Text("Quantité") })
            TextField(
                value = prixUnitaireValue,
                onValueChange = { prixUnitaireValue = it },
                modifier.padding(5.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                placeholder = { Text("Prix Unitaire") })
            montantHTValue = if (quantityValue.isNotBlank() && prixUnitaireValue.isNotBlank()){
                val quantity = quantityValue.toFloat()
                val prixUnitaire = prixUnitaireValue.toFloat()
                calHT(quantity, prixUnitaire).toString()
            } else {
                "0"
            }
            Text("Montant HT : $montantHTValue €")
            TextField(
                value = tauxTVAValue,
                onValueChange = { tauxTVAValue = it },
                modifier.padding(5.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                placeholder = { Text("Taux de TVA") })

            val selectedOption = remember { mutableStateOf("0") }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                RadioButton(
                    selected = selectedOption.value == "0",
                    onClick = { selectedOption.value = "0" }
                )
                Text("Non Fidèle", Modifier.padding(start = 8.dp))

                RadioButton(
                    selected = selectedOption.value == "1",
                    onClick = { selectedOption.value = "1" },
                    modifier = Modifier.padding(start = 16.dp) // Ajoute de l'espace entre les boutons
                )
                Text("Fidèle", Modifier.padding(start = 8.dp))
            }
            if(selectedOption.value == "0"){
                Text("Remise : 0%")
                remiseValue = "0"
            } else if (selectedOption.value == "1") {
                Text("Remise : 15%")
                remiseValue = "15"
            } else {
                Text("Remise : 0%")
                remiseValue = "0"
            }

            Button(onClick = {
                if (montantHTValue.isNotBlank() && tauxTVAValue.isNotBlank() && remiseValue.isNotBlank()){
                    navController.navigate("calTTC/$montantHTValue/$tauxTVAValue/$remiseValue")
                    error = ""
                } else{
                    error = "Un des champs est vide"
                }
            }
            ){
                Text(text = "Calculer montant TTC", fontSize = 17.sp)
            }
            Button(onClick = {
                quantityValue = ""
                prixUnitaireValue = ""
                tauxTVAValue = ""
                selectedOption.value = "0"
            }){
                Text(text = "Remise à Zéro", fontSize = 17.sp)
            }
            Text(text = "$error", fontSize = 15.sp)
        } else {
            navController.navigate("login")
        }
    }
}
fun calHT(value1 :Float, value2: Float):Float {
    return value1 * value2
}