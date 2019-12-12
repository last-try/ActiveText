package com.example.activetext

import android.util.Log
import java.util.regex.Pattern

sealed class Mode(val modeName: String) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Mode) return false

        if (modeName != other.modeName) return false

        return true
    }

    override fun hashCode(): Int {
        return modeName.hashCode()
    }
}

fun Mode.toPattern(): Pattern {
    return when (this) {
        is MODE_HASHTAG -> HASH_TAG_PATTERN
        is MODE_MENTION -> MENTION_PATTERN
        is MODE_PHONE -> PHONE_PATTERN
        is MODE_EMAIL -> EMAIL_PATTERN
        is MODE_URL -> URL_PATTERN
        is MODE_CUSTOM -> {
            if (regex.length > 2) {
                Pattern.compile(regex)
            } else {
                Log.w(AutoLinkTextView.TAG, "Your custom regex is null, returning URL_PATTERN")
                URL_PATTERN
            }
        }
        is ModeWithName -> {
            if (name == "Hashtag"){
                HASH_TAG_PATTERN
            }else if (name == "Mention"){
                MENTION_PATTERN
            }else{
                URL_PATTERN
            }
        }
    }
}

object MODE_HASHTAG : Mode("Hashtag")
object MODE_MENTION : Mode("Mention")
object MODE_URL : Mode("Url")
object MODE_PHONE : Mode("Phone")
object MODE_EMAIL : Mode("Email")
class MODE_CUSTOM(val regex: String) : Mode("Custom")
class ModeWithName(val name : String) : Mode(name)

