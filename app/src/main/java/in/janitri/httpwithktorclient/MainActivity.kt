package `in`.janitri.httpwithktorclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dagger.hilt.android.AndroidEntryPoint
import `in`.janitri.httpwithktorclient.data.remote.PostsService
import `in`.janitri.httpwithktorclient.data.remote.dto.PostResponse
import `in`.janitri.httpwithktorclient.ui.theme.HttpWithKtorClientTheme
import javax.inject.Inject

@AndroidEntryPoint

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var postsService: PostsService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val posts = produceState<List<PostResponse>>(initialValue = emptyList(),
                producer ={
                    value = postsService.getPosts()
                } )
            HttpWithKtorClientTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LazyColumn{
                        items(posts.value){
                            Column(modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                            ) {
                                Text(text = it.title, fontSize = 20.sp)

                                Spacer(modifier = Modifier.height(5.dp))
                                Text(text = it.body, fontSize = 12.sp)
                                Spacer(modifier = Modifier.height(10.dp))
                                
                            }
                        }
                    }
                }
            }
        }
    }
}

