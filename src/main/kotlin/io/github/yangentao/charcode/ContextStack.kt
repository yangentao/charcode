package io.github.yangentao.charcode

class ScanContext(val name: String) {
    val map = HashMap<String, Any>()
    fun put(key: String, value: Any) {
        map[key] = value
    }

    fun get(key: String): Any? = map[key]
}

class ContextStack {
    val contexts: ArrayList<ScanContext> = ArrayList()

    val isEmpty: Boolean get() = contexts.isEmpty()
    val isNotEmpty: Boolean get() = contexts.isNotEmpty()

    fun push(context: ScanContext) {
        contexts.add(context)
    }

    fun pop(): ScanContext {
        return contexts.removeAt(contexts.lastIndex)
    }

    fun peek(): ScanContext? {
        return contexts.getOrNull(contexts.lastIndex)
    }

    fun isContext(name: String): Boolean {
        return peek()?.name == name
    }

    fun has(name: String): Boolean {
        for (i in contexts.indices.reversed()) {
            if (contexts[i].name == name) return true
        }
        return false
    }
}

