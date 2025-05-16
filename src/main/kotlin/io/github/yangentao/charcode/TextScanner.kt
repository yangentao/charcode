@file:Suppress("unused")

package io.github.yangentao.charcode

import kotlin.math.max
import kotlin.math.min

class TextScanner(val text: String) {
    val codeList: CharArray = text.toCharArray()
    var position: Int = 0
    var lastBuf: ArrayList<Char> = ArrayList()

    val contexts: ContextStack = ContextStack()

    val isEnd: Boolean get() = position >= codeList.size
    val notEnd: Boolean get() = position < codeList.size

    val isStart: Boolean get() = position == 0
    val nowChar: Char get() = codeList[position]
    val preChar: Char? get() = if (position >= 1) codeList[position - 1] else null
    val lastMatch: String get() = if (lastBuf.isEmpty()) "" else String(lastBuf.toCharArray())

    var nextCallback: (() -> Unit)? = null

    fun nowIsAny(vararg cs: Char): Boolean {
        return notEnd && nowChar in cs
    }

    fun savePosition(): ScanPos = ScanPos(this, position)

    fun printLastBuf() = println(lastMatch)

    fun back(size: Int = 1) {
        if (position > 0) position -= 1
    }

    fun skipWhites(): List<Char> {
        return skipChars(CharCode.SpTabCrLf)
    }

    fun skipSpaceTabCrLf(): List<Char> {
        return skipChars(CharCode.SpTabCrLf)
    }

    fun skipSpaceTab(): List<Char> {
        return skipChars(CharCode.SpTab)
    }

    fun skipChars(chars: Collection<Char>): List<Char> {
        return skip(acceptor = { chars.contains(it) })
    }

    fun skip(size: Int? = null, acceptor: CharPredicator? = null, terminator: CharPredicator? = null): List<Char> {
        return moveNext(size, acceptor, terminator, false)
    }

    fun moveUntil(chars: List<Char>): List<Char> {
        assert(chars.isNotEmpty())
        return moveNext(terminator = { chars.contains(it) })
    }

    fun expectAnyChar(chars: Collection<Char>): List<Char> {
        assert(chars.isNotEmpty())
        val ls = moveNext(acceptor = { chars.contains(it) })
        if (ls.isEmpty()) raise("Expect chars: $chars ")
        return ls
    }

    fun tryExpectChar(ch: Char): Boolean {
        val ls = moveNext(acceptor = { it == ch && lastBuf.isEmpty() })
        return ls.size == 1 && ls.first() == ch
    }

    fun expectChar(ch: Char) {
        val ls = moveNext(acceptor = { it == ch && lastBuf.isEmpty() })
        if (ls.size != 1 || ls.first() != ch) raise("Expect char: $ch .")
    }

    fun expectString(s: String) {
        assert(s.isNotEmpty())
        val cs = s.toCharArray()
        val ls = moveNext(acceptor = { e ->
            lastBuf.size < cs.size && e == cs[lastBuf.size]
        })
        if (ls.size != cs.size) raise("expect $s.")
    }

    fun tryExpectString(s: String): Boolean {
        assert(s.isNotEmpty())
        val sp = savePosition()
        val cs = s.toCharArray()
        val ls = moveNext(acceptor = { e ->
            lastBuf.size < cs.size && e == cs[lastBuf.size]
        })
        val ok = ls.size == cs.size
        if (!ok) sp.restore()
        return ok
    }

    fun tryExpectAnyString(slist: Collection<String>): Boolean {
        assert(slist.isNotEmpty())
        val ls = slist.sortedByDescending { it.length }
        for (s in ls) {
            if (tryExpectString(s)) return true
        }
        return false
    }

    fun expectIdent(): List<Char> {
        val ls = moveNext(acceptor = {
            if (lastBuf.isEmpty()) {
                CharCode.isAlpha(it) || it == CharCode.LOWBAR
            } else {
                CharCode.isIdent(it)
            }
        })
        if (ls.isEmpty()) raise()
        return ls
    }

    /// if size,acceptor,terminator all is null, moveNext(size = 1)
    fun moveNext(size: Int? = null, acceptor: CharPredicator? = null, terminator: CharPredicator? = null, buffered: Boolean = true): List<Char> {
        val buf: ArrayList<Char> = ArrayList()
        if (buffered) {
            lastBuf = buf
        }
        if (acceptor != null) {
            while (!isEnd) {
                val ch = nowChar
                if (acceptor.accept(ch)) {
                    buf.add(ch)
                    forward(1, fire = true)
                } else {
                    return buf
                }
            }
            if (isEnd) return buf
        } else if (terminator != null) {
            while (!isEnd) {
                val ch = nowChar
                if (terminator.accept(ch)) {
                    return buf
                } else {
                    buf.add(ch)
                    forward(1, fire = true)
                }
            }
            if (isEnd) return buf
        } else {
            val sz = size ?: 1
            if (position + sz > codeList.size) {
                raise("Exceed max length: $sz ")
            }
            buf.addAll(codeList.slice(position..<position + sz))
            forward(sz)
        }
        return buf
    }

    private fun forward(size: Int, fire: Boolean = false) {
        position += size
        if (fire) nextCallback?.invoke()
    }

    fun raise(msg: String = "Scan error"): Nothing {
        error("$msg. $position, $leftText")
    }

    val leftText: String
        get() {
            return if (position >= 0 && position < text.length) {
                text.substring(position, min(text.length, 64))
            } else {
                text.substring(max(0, text.length - 64))
            }
        }
}

class ScanPos(val scanner: TextScanner, val pos: Int) {
    fun restore() {
        scanner.position = pos
    }
}

fun interface CharPredicator {
    fun accept(ch: Char): Boolean
}