package com.example.newsappinkotlin.Database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "newsdata")
data class DataModel ( @PrimaryKey(autoGenerate = true)
                       val id: Int
                      ,val title : String
                      ,val description : String
                      ,val publish : String
                      ,val content : String
                      ,val source : String)

