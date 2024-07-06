package com.mkrdeveloper.retrofitpostjetpack

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mkrdeveloper.retrofitpostjetpack.models.UserListItem
import com.mkrdeveloper.retrofitpostjetpack.ui.theme.RetrofitPostJetpackTheme
import com.mkrdeveloper.retrofitpostjetpack.utils.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RetrofitPostJetpackTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                     var body by remember {
                    mutableStateOf("")
                }
                var id by remember {
                    mutableStateOf(0)
                }
                var title by remember {
                    mutableStateOf("")
                }
                var userId by remember {
                mutableStateOf(0)
            }
                    var responseCode by remember {
                mutableStateOf(0)
            }

                val scope = rememberCoroutineScope()

                LaunchedEffect(key1 = true) {
                    scope.launch(Dispatchers.IO) {
                        val response = try{
                           // val user = UserListItem("patch body",null, null, 12)
                           // RetrofitInstance.api.createUrlPost(44, "url title", "url body")

                          //  RetrofitInstance.api.patchData(35, user)
                            RetrofitInstance.api.deleteUser(35)

                        }catch (e: IOException){
                            Toast.makeText(
                                this@MainActivity,
                                "IO error: ${e.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                            return@launch
                        }catch (e: HttpException){
                            Toast.makeText(
                                this@MainActivity,
                                "Http error: ${e.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                            return@launch
                        }
                        if (response.isSuccessful && response.body() != null){
                            withContext(Dispatchers.Main){
                                body = response.body()!!.body.toString()
                                id = response.body()!!.id
                                title = response.body()!!.title.toString()
                                userId = response.body()!!.userId
                                responseCode= response.code()

                            }
                        }
                    }
                }
                    MyUi(id,body,title,userId, responseCode)
                }
            }
        }
    }
}

@Composable
fun MyUi(id: Int, body: String, title: String, userId: Int, responseCode: Int) {

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)) {


        Text(text = "code: $responseCode",fontSize = 30.sp)
        Text(text = "id: $id",fontSize = 20.sp)
        Text(text = "userId: $userId",fontSize = 20.sp)
        Text(text = "title: $title",fontSize = 20.sp)
        Text(text = "body: $body",fontSize = 20.sp, maxLines = 2)
        Divider(thickness = 2.dp, color = Color.Black)
    }
}