package com.example.activetext

class AutoLinkItem(var startPoint: Int,
                   var endPoint: Int,
                   var originalText: String,
                   var transformedText: String,
                   val mode: Mode) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as AutoLinkItem
        if (endPoint != other.endPoint) return false
        return true
    }

    override fun hashCode() = endPoint
}
