package com.rishabh.fillrtask.domain.model

data class Photo(
    val index: Int,
    val farm: Int,
    val id: String,
    val isfamily: Int,
    val isfriend: Int,
    val ispublic: Int,
    val owner: String,
    val secret: String,
    val server: String,
    val title: String,
    val imageUrl: String
) {
    fun toText(): String {
        return "Index: $index\n" +
                "Farm: $farm\n" +
                "Id: $id\n" +
                "Isfamily: $isfamily\n" +
                "Isfriend: $isfriend\n" +
                "Ispublic: $ispublic\n" +
                "Owner: $owner\n" +
                "Secret: $secret\n" +
                "Server: $server\n" +
                "Title: $title\n" +
                "ImageUrl: $imageUrl"
    }
}
