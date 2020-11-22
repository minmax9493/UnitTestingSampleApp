package app.pivo.android.unittestingdemoapp

import android.content.Context

/**
 * Created by murodjon on 2020/11/22
 */
class ResourceComparer {
    fun stringResourceIsSameAsGivenStringReturnsTrue(context: Context, resId:Int, text:String):Boolean{
        return context.getString(resId) == text
    }
}