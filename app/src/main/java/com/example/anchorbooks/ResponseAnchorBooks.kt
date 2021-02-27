package com.example.anchorbooks

import com.google.gson.annotations.SerializedName

data class ResponseAnchorBooks( val list: List<DataClassBooks>)

data class ResponseBookDetail ( val list: List<DataClassDetail>)

