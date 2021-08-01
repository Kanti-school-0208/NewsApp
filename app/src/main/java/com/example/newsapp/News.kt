package com.example.newsapp

 class News{
         var title : String? = null
         var author : String? = null
         var url : String? = null
         var urlToImage : String? = null
         var description: String? = null
         constructor()
         constructor(title: String?, author: String?, url: String?, urlToImage: String?,description: String?) {
                 this.title = title
                 this.author = author
                 this.url = url
                 this.urlToImage = urlToImage
                 this.description = description
         }
 }



