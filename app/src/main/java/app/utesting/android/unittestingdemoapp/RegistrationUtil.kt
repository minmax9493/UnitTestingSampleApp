package app.utesting.android.unittestingdemoapp

/**
 * Created by murodjon on 2020/11/22
 */
object RegistrationUtil {

    private val existingUsers = listOf("Botir", "Bahodir")

    fun validateRegistrationInput(
        userName:String,
        password:String,
        confirmPassword:String
    ):Boolean{
        if (userName.isEmpty() || password.isEmpty()){
            return false
        }

        if (userName in existingUsers){
            return false
        }

        if (password!=confirmPassword){
            return false
        }

        if (password.count { it.isDigit() } < 2){
            return false
        }

        return true
    }

}