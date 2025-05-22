package entao

import io.github.yangentao.charcode.TextScanner
import kotlin.test.Test

class TextTest {

    @Test
    fun accterm() {
        val s = "ab,]"
        val ts = TextScanner(s)
        val buf = ts.moveAcceptTerminate(acceptor = {
            it.isLetter() || it == ','
        }, terminator = {
            it == ','
        }, terminateFirst = true )
        println(ts.position)
        println(String(buf.toCharArray()))
    }
}