package `in`.janitri.httpwithktorclient.data.remote

import `in`.janitri.httpwithktorclient.data.remote.dto.PostRequest
import `in`.janitri.httpwithktorclient.data.remote.dto.PostResponse

interface PostsService {
    suspend fun getPosts():List<PostResponse>

    suspend fun createPost(postRequest: PostRequest):PostResponse?
}