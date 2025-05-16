@file:Suppress("unused", "ConstPropertyName")

package io.github.yangentao.charcode

object CharCode {
    val CrLf: List<Char> = listOf(CR, LF)
    val SpTab: List<Char> = listOf(SP, HTAB)
    val SpTabCrLf: List<Char> = listOf(SP, HTAB, CR, LF)

    fun isUnicodeLead(code: Char): Boolean {
        return code >= Character.MIN_HIGH_SURROGATE && code <= Character.MAX_HIGH_SURROGATE
    }

    fun isUnicodeTrail(code: Char): Boolean {
        return code >= Character.MIN_LOW_SURROGATE && code <= Character.MAX_LOW_SURROGATE
    }

    fun isNum(code: Char): Boolean {
        return code >= NUM0 && code <= NUM9;
    }

    fun isAlpha(code: Char): Boolean {
        return (code >= a && code <= z) || (code >= A && code <= Z);
    }

    fun isIdent(code: Char): Boolean {
        return (code >= a && code <= z) || (code >= A && code <= Z) || (code >= NUM0 && code <= NUM9) || code == LOWBAR;
    }

    fun isHex(code: Char): Boolean {
        return (code >= a && code <= f) || (code >= A && code <= F) || (code >= NUM0 && code <= NUM9);
    }

    fun isPrintable(code: Char): Boolean = code >= SP

    /// Null character
    const val NUL: Char = '\u0000'

    /// Start of Heading
    const val SOH: Char = '\u0001'

    /// Start of Text
    const val STX: Char = '\u0002'

    /// End of Text
    const val ETX: Char = '\u0003'

    /// End of Transmission
    const val EOT: Char = '\u0004'

    /// Enquiry
    const val ENQ: Char = '\u0005'

    /// Acknowledge
    const val ACK: Char = '\u0006'

    /// Bell, Alert
    const val BEL: Char = '\u0007'

    /// Backspace \b
    const val BS: Char = '\u0008'

    /// \t  Horizontal Tab
    const val HTAB: Char = '\u0009'

    /// \t  Horizontal Tab, same as HTAB
    const val TAB: Char = '\u0009'

    /// \n  Line Feed
    const val LF: Char = '\n' //'\u000A'

    /// Vertical Tabulation
    const val VTAB: Char = '\u000B'

    /// Form Feed
    const val FF: Char = '\u000C'

    /// \r  Carriage Return
    const val CR: Char = '\r' // '\u000D'

    /// Shift Out
    const val SD: Char = '\u000E'

    /// Shift In
    const val SI: Char = '\u000F'

    /// Data Link Escape
    const val DLE: Char = '\u0010'

    /// Device Control One (XON)
    const val DC1: Char = '\u0011'

    /// Device Control Two
    const val DC2: Char = '\u0012'

    /// Device Control Three (XOFF)
    const val DC3: Char = '\u0013'

    /// Device Control Four
    const val DC4: Char = '\u0014'

    /// Negative Acknowlege
    const val NAK: Char = '\u0015'

    /// Synchronous Idle
    const val SYN: Char = '\u0016'

    /// End of Transmission Block
    const val ETB: Char = '\u0017'

    /// Cancel
    const val CAN: Char = '\u0018'

    /// End of medium
    const val EM: Char = '\u0019'

    /// Substitute
    const val SUB: Char = '\u001A'

    /// Escape
    const val ESC: Char = '\u001B'

    /// File Separator
    const val FS: Char = '\u001C'

    /// Group Separator
    const val GS: Char = '\u001D'

    /// Record Separator
    const val RS: Char = '\u001E'

    /// Unit Separator
    const val US: Char = '\u001F'

    /// Space
    const val SP: Char = ' ' //'\u0020'

    /// ! Exclamation mark
    const val EXCL: Char = '!' //'\u0021'

    /// " Double Quotes
    const val QUOTE: Char = '"' //'\u0022'

    /// # Number Sign
    const val NUM: Char = '#' //'\u0023'

    /// # Number Sign, same as NUM
    const val SHARP: Char = '#' //'\u0023'

    /// $, Dollar
    const val DOLLAR: Char = '$' //'\u0024'

    /// % Percent Sign
    const val PERCENT: Char = '%' //'\u0025'

    /// &, Ampersand
    const val AMP: Char = '&' //'\u0026'

    /// '  Single Quote
    const val SQUOTE: Char = '\'' //'\u0027'

    /// ' Single Quote, same as SQUOTE
    const val APOS: Char = '\'' //'\u0027'

    /// (  Open Parenthesis
    const val LPAREN: Char = '(' //'\u0028'

    /// )  Close parenthesis
    const val RPAREN: Char = ')' //'\u0029'

    /// * Asterisk
    const val AST: Char = '*' //'\u002A'

    /// +
    const val PLUS: Char = '+' //'\u002B'

    /// ,
    const val COMMA: Char = ',' // 44

    /// -
    const val MINUS: Char = '-' // 45

    /// .
    const val DOT: Char = '.' // 46

    /// .
    const val PERIOD: Char = '.' // 46

    /// /
    const val SLASH: Char = '/' //47
    const val SOL: Char = '/' //47
    const val NUM0: Char = '0' //48
    const val NUM1: Char = '1' //49
    const val NUM2: Char = '2' //50
    const val NUM3: Char = '3' //51
    const val NUM4: Char = '4' //52
    const val NUM5: Char = '5' //53
    const val NUM6: Char = '6' //54
    const val NUM7: Char = '7' //55
    const val NUM8: Char = '8' //56
    const val NUM9: Char = '9' //67

    /// :
    const val COLON: Char = ':' // 58

    /// ;
    const val SEMI: Char = ';' // 59

    /// <
    const val LT: Char = '<' // 60

    /// =
    const val EQUAL: Char = '=' // 61

    /// >
    const val GT: Char = '>' // 62

    /// ?
    const val QUEST: Char = '?' // 63

    /// @
    const val COMMAT: Char = '@' //64
    const val AT: Char = '@' //64

    const val A: Char = 'A' //65
    const val B: Char = 'B' //66
    const val C: Char = 'C' //67
    const val D: Char = 'D' //68
    const val E: Char = 'E' //69
    const val F: Char = 'F' //70
    const val G: Char = 'G' //71
    const val H: Char = 'H' //72
    const val I: Char = 'I' //73
    const val J: Char = 'J' //74
    const val K: Char = 'K' //75
    const val L: Char = 'L' //76
    const val M: Char = 'M' //77
    const val N: Char = 'N' //78
    const val O: Char = 'O' //79
    const val P: Char = 'P' //80
    const val Q: Char = 'Q' //81
    const val R: Char = 'R' //82
    const val S: Char = 'S' //83
    const val T: Char = 'T' //84
    const val U: Char = 'U' //85
    const val V: Char = 'V' //86
    const val W: Char = 'W' //87
    const val X: Char = 'X' //88
    const val Y: Char = 'Y' //89
    const val Z: Char = 'Z' //90

    /// [
    const val LSQB: Char = '[' //91

    /// \  0x5c
    const val BSLASH: Char = '\\' //92

    /// ]
    const val RSQB: Char = ']' // 93

    /// ^ Caret
    const val HAT: Char = '^' // 94

    /// _
    const val LOWBAR: Char = '_' // 95

    /// `  Grave accent
    const val GRAVE: Char = '`' // 96

    const val a: Char = 'a'  // 97
    const val b: Char = 'b'  // 98
    const val c: Char = 'c'  // 99
    const val d: Char = 'd'  // 100
    const val e: Char = 'e'  // 101
    const val f: Char = 'f'  // 102
    const val g: Char = 'g'  // 103
    const val h: Char = 'h'  // 104
    const val i: Char = 'i'  // 105
    const val j: Char = 'j'  // 106
    const val k: Char = 'k'  // 107
    const val l: Char = 'l'  // 108
    const val m: Char = 'm'  // 109
    const val n: Char = 'n'  // 110
    const val o: Char = 'o'  // 111
    const val p: Char = 'p'  // 112
    const val q: Char = 'q'  // 113
    const val r: Char = 'r'  // 114
    const val s: Char = 's'  // 115
    const val t: Char = 't'  // 116
    const val u: Char = 'u'  // 117
    const val v: Char = 'v'  // 118
    const val w: Char = 'w'  // 119
    const val x: Char = 'x'  // 120
    const val y: Char = 'y'  // 121
    const val z: Char = 'z'  // 122

    /// {
    const val LCUB: Char = '{' // 123

    /// |
    const val VBAR: Char = '|' //124

    /// }
    const val RCUB: Char = '}' //125

    /// ~ TILDE
    const val TILDE: Char = '~' //126

    /// DEL
    const val DEL: Char = '\u007F' // 127
}