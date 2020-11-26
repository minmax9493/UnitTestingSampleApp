package app.utesting.android.unittestingdemoapp

import com.google.common.truth.Truth.assertThat
import org.junit.Test


/**
 * Created by murodjon on 2020/11/22
 */
class RegistrationUtilTest{

    @Test
    fun `empty user name returns false`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "",
            "1234",
            "1234"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `valid username and correctly repeated password returns true`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "Vali",
            "1234",
            "1234"
        )
        assertThat(result).isTrue()
    }

    @Test
    fun `incorrectly confirmed password returns false`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "Vali",
            "1234",
            "1244"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `username already taken returns false`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "Botir",
            "1234",
            "1234"
        )
        assertThat(result).isFalse()
    }

}